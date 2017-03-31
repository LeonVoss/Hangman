package com.flowfact.controller;



import java.awt.event.InputEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.flowfact.bufimg.ImageWriter;
import com.flowfact.buttons.HighscoreButton;
import com.flowfact.buttons.MemoryButton;
import com.flowfact.bufimg.Body;
import com.flowfact.clients.GameGui;
import com.flowfact.clients.GuiClient;
import com.flowfact.clients.HighscoreGui;
import com.flowfact.games.Game;
import com.flowfact.highscore.Highscore;
import com.flowfact.highscore.ImplDAODatabase;
import com.flowfact.highscore.Memory;
import com.flowfact.jsoup.GetWord;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.WritableImage;


@SuppressWarnings("restriction")
public class Controller {
	private GameGui gameGui;
	public Game game;
	public GuiClient client;
	public boolean started = false;
	public static String word = "";
	public boolean threadis = false;
	private EventHandler keyEvent;
	private ImplDAODatabase hsList;
	private HighscoreGui hsGui;
	private boolean firstStart = true;
	private boolean keyListenerOk = false;
	
	public Controller(Game game,ImplDAODatabase hsList) {
		client = new GuiClient("1.0",hsList);
		this.game = game;
		started = true;
		gameGui = new GameGui();
		GuiClient.setGameGui(gameGui);
		this.hsList = hsList;
		hsGui = new HighscoreGui(hsList);
		firstStart = true;
	}
	
	
	
	
	public String tip = "";
	public void startgame() {
		if(started&&!threadis) {
			threadis=true;
			
			Threads.ControlThread = new Thread(new Runnable() {public void run() {
				
				// each new game
				while(true) {
					System.out.println("new game");
					
					try {
						
				word = GetWord.getword().toUpperCase();
				game.refresh();
				Platform.runLater(new Runnable() {public void run() {
		        gameGui.refreshing();
		        
				}
				});
				
				game.setword(word);
				Platform.runLater(new Runnable() {public void run() {
					gameGui.removechildrens();
					client.startgame(game);
					client.setstate(4);
					//client.showgame();
				}
				});
				
				
				
				if(firstStart) {
					Platform.runLater(new Runnable() {public void run() {
					closeHandler();
					}
					});
				}
				
				if(firstStart&&hsList.memoryExist()) {
					firstStart = false;
					Platform.runLater(new Runnable() {public void run() {
						gameGui.removechildrens();
						client.startgame(game);
						client.showMemorySaves();
					}
					});
					
					Memory loadedScore = getLoadedScore();
					if(loadedScore!=null) {
						game.setScore(loadedScore.getScore());
						game.setLodedName(loadedScore.getName());
						Platform.runLater(new Runnable() {public void run() {
							client.setstate(4);
							client.showgame();
							
						}
						});
					}else {
						Platform.runLater(new Runnable() {public void run() {
							client.setstate(4);
							client.showgame();
							
						}
						});
					}
					
					
				}else {
					Platform.runLater(new Runnable() {public void run() {
						client.setstate(4);
						client.showgame();
						
					}
					});
					
				}
				
				if(keyEvent == null) {
					try {
					Platform.runLater(new Runnable() {public void run() {
						keyListener();
						keyListenerNewInput();
						sceneClickEvent();
						scoreButtonHandler();
					}
					});
					}catch(Exception e) {}
					}
				
				int currentTime = 0;
				int extraPoints = 50;
				
				
				game.addtipped("");
				while(gameIsRunning()) {
					keyListenerOk = true;
					game = refreshGameContents(game);
					writeImage();
					refreshGameGui();
					Platform.runLater(new Runnable() {public void run() {
					gameGui.refreshScoreLabel();
					}
					});
					try {Thread.sleep(100);} catch (Exception e) {}
					
					if(charIsTiped()) {
					currentTime++;
					}
					
					
				}
				keyListenerOk = false;
				writeImage();
				refreshGameGui();
				Platform.runLater(new Runnable() {public void run() {
				gameGui.refreshScoreLabel();
				}
				});
				extraPoints = extraPoints - (currentTime/10);
				if(extraPoints>0&&game.isfinish()) {
					game.setScore(game.getScore()+extraPoints);
				}
				
				Platform.runLater(new Runnable() {public void run() {
					gameGui.refreshScoreLabel();
					gameGui.removeBottom3();
					}
					});
				showEnd();
				if(game.isfinish()) {
				saveMemory();
				}
				int replay = waitForReplay();
				
				
			    if(replay==2) {
			    	game.setSaveDatas(false);
			    	Platform.runLater(new Runnable() {public void run() {
			    		gameGui.saveDatasGui();
			    		saveScore();
			    		
			    	}
			    	});
			    	
			    	
			    	while(!clickedAnyButton()) {Thread.sleep(100);}
			    	if(!game.isfinish()) {
			    		game.setScore(0);
			    	}
			    	game.setSaveDatas(false);
			    }else if(replay==3) {
			    	while(!clickedAnyButton()) {Thread.sleep(100);}
			    	game.setSaveMemory(false);
			    }else if(replay==1) {
			    	if(!game.isfinish()) {
			    		game.setScore(0);
			    	}
			    }
			    
			    
			    clicked = false;
					}catch(Exception e) {}
				
				}
			
			}
			});
			Threads.ControlThread.start();
			
			
		}
	}
	
	private boolean charIsTiped() {
		return game.getcharshave().size()>0;
	}
	
	private Memory memoryScore = null;
	private boolean finishedGet = false;
	
	
	private boolean clicked = false;
	
	private boolean clickedAnyButton() {
return clicked;
	}
	
	
	private Memory getLoadedScore() {
		while(!client.getFinished()) {try {Thread.sleep(100);} catch (InterruptedException e) {}}
		
		memoryScore = null;
		finishedGet = false;
		
		client.getCancelButton().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
            finishedGet=true;
			}
		});
		
		
		
		List<MemoryButton> buttons = client.getMemoryButtons();
		for(MemoryButton currentButton : buttons) {
			
			currentButton.getBtn().setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
		        memoryScore = new Memory(currentButton.getName(),currentButton.getScore());
		        finishedGet = true;
				}
				});
			
		}
		
		
		while(!finishedGet) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
		}
		
		return memoryScore;
	}
	
	private int state = 3;
	@SuppressWarnings("unchecked")
	public void keyListener() {
	try {
		keyEvent = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {if(keyListenerOk) {
				try {
				if(!f.isFocused()) {
	            	  if(game.getVaris().getCharNum().contains(event.getCode().getName().toUpperCase())) {
           		  tip = (new String(event.getCode().getName().toUpperCase()));
	            	  }
				}
             
			}catch(Exception e) {}
			}
			}
		
	};
	}catch(Exception e) {}
	Scene sc = client.getScene();
	sc.addEventHandler(KeyEvent.KEY_RELEASED,keyEvent);
	
	}
	
	private TextField f;
	private void keyListenerNewInput() {
		f = gameGui.getNewInput();
		
		f.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            if(checkString(f.getText())) {
	            	String text = f.getText();
	            	f.setText("");
	            	if(game.getword().equalsIgnoreCase(text)) {
	            	for(int a = 0; a < text.length(); a++) {
	            		game.addwordhave(text.charAt(a)+"");
	            		game.addchar(text.charAt(a)+"");
	            	}
	            	}
	            	while(game.momversuche()<11) {
	            	game.addmomversuche();
	            	}
	            	
	            	if(game.isfinish()) {
	            
	            	}else {
	            	game.setScore(game.getScore()-40);	
	            	}
	            	
	            }
	            }
	        }
	    });
		
		
	}
	
	private boolean checkString(String txt) {
		boolean is = false;
		
		if(txt.length()==game.getword().length()) {
			boolean b2 = true;
			for(int a = 0; a < txt.length(); a++) {
				String c = txt.charAt(a)+"";
				if(!game.getVaris().getCharNum().contains(c.toUpperCase())) {b2 = false;break;}
			}
			if(b2)
			{is=true;}
		}
		
		return is;
	}
	
	private void sceneClickEvent() {
		VBox sc = client.getMain();
		sc.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    sc.setFocusTraversable(true);
		    sc.requestFocus();
		    } 
		});
		
	}
	

	private void scoreButtonHandler() {
		gameGui.getScoreButton().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			
				if(!hsGui.isOpen()) {
					hsGui.show(300, (int)client.getStage().getHeight(), hsList.getHighscores());
					hsGui.setPosition((int)(client.getStage().getX()+client.getStage().getWidth()), (int)client.getStage().getY());
					ListGuiManager();
				}
				
			
				
			}
		});
	}
	
	private void saveMemory() {
		Platform.runLater(new Runnable() {public void run() {
		gameGui.getMemoryButton().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gameGui.saveMemoryGui();
				game.setSaveMemory(true);
				
saveMemoryFinished();
				
				
			}
		});
	}
	});
	}

	private void ListGuiManager() {
		
	while(!hsGui.isFinished()) {try { Thread.sleep(100); }catch(Exception e) {}}
		
	List<HighscoreButton> btns = hsGui.getHighScoreButtons();
		
	for(HighscoreButton btn : btns) {
		
		btn.getBtn().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
	        	Button backButton = hsGui.showUser(new Highscore(btn.getName(),btn.getScore(),btn.getDate()));
	        	
	        	backButton.setOnAction(new EventHandler<ActionEvent>() {
	                public void handle(ActionEvent event) {
	                hsGui.addButtons();
	                ListGuiManager();
	                }
	            });
	        	
			}
			});
		
	}
	
	}
	
	private void saveMemoryFinished() {
		gameGui.getSaveButton2().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			String name = gameGui.getText2();
				if(!name.isEmpty()) {
					
					if(!game.getLoadedName().isEmpty()) {
						
						hsList.deleteMemory(name);
						hsList.addMemory(name, (int)game.getScore());
						clicked = true;
						
					}else {
						boolean c = false;
						List<Memory> mem = hsList.getMemoryByDatabase();
						for(Memory currentScore : mem) {
							if(currentScore.getName().equalsIgnoreCase(name)) {
								c=true;
							}
						}
						if(!c) {
							clicked = true;
							game.setLodedName(name);
							hsList.addMemory(name, (int)game.getScore());
						}
						
					}
					
		        	  
		        	  
				}
			}
		});
	}
	
	private void saveScore() {
		Button save = game.getVaris().getSaveButton();
		TextField input = game.getVaris().getTextField();
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(!input.getText().isEmpty()) {
				save.setVisible(false);
			    
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				Highscore score = new Highscore(input.getText(),(int)game.getScore(),dateFormat.format(date));
				hsList.addHighscore(score);
				clicked = true;
				game.setLodedName(input.getText());
				}
			}
		});
		
	}
	
	private void closeHandler() {
		Stage s = client.getStage();
		s.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  
	        	  try {Threads.ControlThread.stop();}catch(Exception e) {}
	        	  try {Threads.KeyListenerThread.stop();}catch(Exception e) {}
	        	  
	              Platform.exit();
	          }
	      });   
	}
	
	private void refreshGameGui() {
		Platform.runLater(new Runnable() {public void run() {
			gameGui.refresh();
		}
		});
	}
	
	private void showEnd() {
		if(game.isfinish()) {
			Platform.runLater(new Runnable() {public void run() {
				gameGui.won();
			}
			});
		}else {
			if(!game.getLoadedName().isEmpty()) {
				hsList.deleteMemory(game.getLoadedName());
			}
			Platform.runLater(new Runnable() {public void run() {
				gameGui.loose();
			}
			});
			
		}
	}
	
	private int waitForReplay() {
		int c = 0;
		while(c==0) {
		if(game.isSaveDatas()) {
			c=2;
		}else if(game.isSaveMemory()) {
			c=3;
		}else if(word.isEmpty())
		{
			c=1;
		}
		
		try {Thread.sleep(100);}catch(Exception e) {}
		}
		return c;
	}
	
	private Game refreshGameContents(Game game) {
		
		if(!tip.isEmpty()) {
			
			
			
			
			if(game.addwordhave(tip)) {
				
			}else {
				if(!game.charcontains(tip)) {
         			game.addmomversuche();
         			
         		}
         	}
         	game.addchar(tip);
         	tip = "";
		}
		
		
		return game;
	}
	
	private void writeImage() {
		Platform.runLater(new Runnable() {public void run() {
		
		try {
		WritableImage himg = game.getVaris().getHangmanImage();
		ImageWriter writer = new ImageWriter(himg);
		himg = writer.writeImage(getPixels());
		
	}catch(Exception e) {}
	}
	});
	} 
	
	private List<String> getPixels() {
		List<String> cor = new ArrayList<String>();
		Body männchen = new Body();
		for(int a = 1; a <=game.momversuche(); a++) {
			if(a==1) {for(String c : männchen.getStand()) {cor.add(c);}}
			if(a==2) {for(String c : männchen.getLinkerBalken()) {cor.add(c);}}
			if(a==3) {for(String c : männchen.getOberenBalken()) {cor.add(c);}}
			if(a==4) {for(String c : männchen.getStütze()) {cor.add(c);}}
			if(a==5) {for(String c : männchen.getSeil()) {cor.add(c);}}
			if(a==6) {for(String c : männchen.getKopf()) {cor.add(c);}}
			if(a==7) {for(String c : männchen.getKörper()) {cor.add(c);}}
			if(a==8) {for(String c : männchen.getLinkerArm()) {cor.add(c);}}
			if(a==9) {for(String c : männchen.getRechterArm()) {cor.add(c);}}
			if(a==10) {for(String c : männchen.getLinkesBein()) {cor.add(c);}}
			if(a==11) {for(String c : männchen.getRechtesBein()) {cor.add(c);}}
		}
		return cor;
	}
	
	private boolean gameIsRunning() {
		if(game.isfinish()) {
			return false;
		}else if(game.momversuche()>game.maxversuche()) {
			return false;
		}else {return true;}
	}
	
	
	
	
}
