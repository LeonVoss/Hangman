package com.flowfact.clients;

import java.util.ArrayList;
import java.util.List;

import com.flowfact.app.App;
import com.flowfact.buttons.MemoryButton;
import com.flowfact.controller.Controller;
import com.flowfact.controller.Threads;
import com.flowfact.games.Game;
import com.flowfact.highscore.Highscore;
import com.flowfact.highscore.ImplDAODatabase;
import com.flowfact.highscore.Memory;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiClient {
	
	private Game game;
	
	private ImplDAODatabase hsList;
	
	private Stage stage = new Stage();
	private VBox mainbox = new VBox();
	
	private String version = "";
	private String enteredWord = "";
	
	private int state = 0;
	
	private DropShadow shadow = new DropShadow();
	private static GameGui GameGui;
	// -- Input
	private String momword = "";
	private Button startbutton;
	private VBox mainInput;
	private HBox showWord;
	
	// --
	
	private List<MemoryButton> memoryButtons = new ArrayList<MemoryButton>();
	
	
	public GuiClient(String version,ImplDAODatabase hsList) {
		this.version = version;
		this.hsList = hsList;
	
	}
	// Controlls
	
	public Scene getScene() {
		return sc;
	}
	
	
	public void stopgame2() {
		state = 0;
		close();
		stage = null;
		mainbox = null;
		game = null;
		enteredWord = null;
		version = null;
	}
	public void setstate(int i) {
		state = i;
	}
	private void close() {
		stage.close();
	}
	
	public void refreshScene() {
		sc = new Scene(mainbox);
		stage.setScene(sc);
		
	}
	
	public void startgame(Game game) {
		
  	  try {Threads.KeyListenerThread.stop();}catch(Exception e) {}
		this.game = game;
		state = 1;
		beginn();
		
		
		//input();
	}
	private KeyEvent event2;
	@SuppressWarnings("restriction")
	private void keylistener(Scene scene)
	{
	 //try {
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {try {
            	event2 = event;
            	
              if(state==2) {
            	  if(event.getCode().getName().equalsIgnoreCase("Enter")) {
            		  if(momword.length()>0) {
                      	
                      	// CHANGE STAGE AND START GAME
                      	
                      	
                      	enteredWord = momword;
                      	state = 3;
                      }
            		  
            		  
            	  }
            	  if(event.getCode().getName().equalsIgnoreCase("Backspace")) {
            		  if(momword.length()>0) {
            			  String copy = momword;
            			  momword = "";
            			  for(int a = 0; a < copy.length()-1; a++) {
            				  momword += copy.charAt(a);
            			  }
            			  refreshword();
            		  }
            	  }else if((event.getCode().isLetterKey()||event.getCode().isDigitKey())&&!event.getCode().getName().contains(" ")) {
            		  if(momword.length()<15) {
            	  momword += event.getCode().getName().toUpperCase();
            	  refreshword();
            	  }
            	  }
            	  
            	  if(momword.length()>0) {if(!startbutton.isHover()) {
            		  startbutton.setStyle("-fx-background-color:gray;"
            					+ "-fx-border-width: 1;"
            					+ "-fx-border-color:black;"
            					+ "");
            		  startbutton.setEffect(shadow);
            		  startbutton.setTextFill(Color.WHITE);
            	  }else {
            		  startbutton.setStyle("-fx-background-color:lightgray;"
          					+ "-fx-border-width: 1;"
          					+ "-fx-border-color:black;"
          					+ "");
          		  startbutton.setEffect(shadow);
          		  startbutton.setTextFill(Color.WHITE);
            	  }
            	  }else {
            		  startbutton.setStyle("-fx-background-color:gray;"
          					+ "-fx-border-width: 1;"
          					+ "-fx-border-color:black;"
          					+ "");
          		  startbutton.setEffect(null);
          		startbutton.setTextFill(Color.BLACK);
            		  
            	  }
            	  
              }else if(state==4) {
            	  
            	  if((event.getCode().isLetterKey()||event.getCode().isDigitKey())&&!event.getCode().getName().contains(" ")) {
            	  
            		  game.addtipped(new String(event2.getCode().getName().toUpperCase()));
            	
            	  
              }
              }
            }catch(Exception e) {}
              
            }
        });
	 /*} catch(java.lang.NullPointerException ex) {
		 System.out.println(ex.getMessage());
	 }*/
	}	
	
	
	// return
	public static void setGameGui(GameGui GameGui2) {
		GameGui = GameGui2;
	}
	
	public boolean wordentered() {
		return !enteredWord.isEmpty();
	}
	
	public String getword() {
		return enteredWord;
	}
	
	
	// Anzeige
	
	@SuppressWarnings("restriction")
	private void refreshword() {
		showWord.getChildren().removeAll(showWord.getChildren());
		
		
		for(int a = 0; a < momword.length(); a++) {
			
			Label currentchar = new Label(new String(momword.charAt(a)+""));
			currentchar.setUnderline(true);
			currentchar.setFont(new Font("Arial",25));
			currentchar.setTextFill(Color.WHITE);
			
			
			if(a>0) {
				Label placeholder = new Label("  ");
				showWord.getChildren().add(placeholder);
			}
			
			showWord.getChildren().add(currentchar);
			
			
			
			
		}
		
		
		
		
	}
	
	
	public Stage getStage() {
		return stage;
	}
	
	public VBox getMain() {
		return mainbox;
	}
	
	private boolean al = true;
	@SuppressWarnings("restriction")
	private Scene sc = null;
	@SuppressWarnings("restriction")
	
	
	
	private void beginn() {
		try {
		removechildrens();
		}catch(Exception e) {}
		
		
		
		mainbox.setStyle("-fx-background-color:gray;");
		mainbox.setAlignment(Pos.CENTER);
		mainbox.setFocusTraversable(true);
		
		if(!al) {
			
		}else {
			
			al=false;
			stage.setTitle("Hangman "+version);
			stage.setMinWidth(600);
			stage.setMaxWidth(600);
			stage.setMinHeight(500);
			stage.setMaxHeight(500);
			
			sc = new Scene(mainbox);
			try {
				sc.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
				}catch(Exception e) {}
			stage.setScene(sc);
			stage.getIcons().add(new Image(App.class.getResourceAsStream("Hangman-Game-grey.png")));
			stage.show();
			
			
		   
			
			
		}
		
		 
		
		
	}
	
	private ScrollPane pane;
	private VBox showView;
	private Button cancel;
	
	public Button getCancelButton() {
		return cancel;
	}
	
	public void showMemorySaves() {
		Label showText = new Label("Spielstand laden ?");
		showText.setFont(new Font("Arial",22));
		showText.setTextFill(Color.WHITE);
		
		showView = new VBox();
		showView.setStyle("-fx-background-color:gray;");
		showView.setAlignment(Pos.TOP_CENTER);
		
		
		
		
		
		pane = new ScrollPane(showView);
int height = 300;
		pane.setMinSize(stage.getWidth()-13, height);
		pane.setMaxSize(stage.getWidth()-13, height);
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		pane.setFocusTraversable(false);
		pane.setBorder(Border.EMPTY);
		pane.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
		
		showView.setMinSize(stage.getWidth(), pane.getMaxHeight()-2);
		showView.setMaxWidth(stage.getWidth());
		
		
		VBox beforePane = new VBox();
		beforePane.setAlignment(Pos.CENTER);
		beforePane.setStyle("-fx-background-color:lightblue;");
		beforePane.setMinSize(pane.getMaxWidth(), pane.getMaxHeight()+2);
		beforePane.setMaxSize(pane.getMaxWidth(), pane.getMaxHeight()+2);
		beforePane.getChildren().add(pane);
		
		
		Label lh = new Label("");
		Label lh2 = new Label("");
		
		cancel = new Button("Abbrechen");
		cancel.setStyle("-fx-background-color:transparent;");
		cancel.setBorder(Border.EMPTY);
		cancel.setFont(new Font("Arial",18));
		cancel.setTextFill(Color.BLACK);
		cancel.setCursor(Cursor.HAND);
		
		cancel.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				
				cancel.setUnderline(true);
				

			
			}

		});
		cancel.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

						cancel.setUnderline(false);

					}
				});
		
		mainbox.getChildren().addAll(showText,lh,pane,lh2,cancel);
		
		addButtons(showView);
	}
	
	private void addButtons(VBox showView) {
		
		List<Memory> hs = hsList.getMemoryByDatabase();
		for(Memory currentScore : hs) {
			
			VBox toadd = new VBox();
        	toadd.setCursor(Cursor.HAND);
			toadd.setAlignment(Pos.CENTER);
			Label name = new Label(currentScore.getName());
			
			
			Label score = new Label(currentScore.getScore()+"");
			
			name.setFont(new Font("Arial",20));
			name.setTextFill(Color.DARKKHAKI);
			
			Label lh = new Label("");
			lh.setFont(new Font("Arial",8));
			Label lh2 = new Label("");
			
			score.setFont(new Font("Arial",20));
			score.setTextFill(Color.LIGHTGRAY);
			toadd.getChildren().add(lh2);
			toadd.getChildren().addAll(name,lh,score);
			if(hs.get(hs.size()-1)!=currentScore) {
				Label lh3 = new Label("");	
				VBox line = new VBox();
				line.setMinSize(450, 1);
				line.setMaxSize(450, 1);
				line.setStyle("-fx-background-color:lightgray;");
				line.setOpacity(0.4);
				line.setAlignment(Pos.CENTER);
				toadd.getChildren().addAll(lh3,line);
				}else {
					Label lh3 = new Label("");
					toadd.getChildren().addAll(lh3);
				}
			showView.getChildren().add(toadd);
			
			
			MemoryButton mb = new MemoryButton(currentScore.getName(),currentScore.getScore(),toadd);
			memoryButtons.add(mb);
			
			toadd.hoverProperty().addListener(new ChangeListener<Boolean>() {

	            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
	            if(newValue) {
	            	toadd.setStyle("-fx-background-color:#8c8c8c");
	            }else {
	            	toadd.setStyle("-fx-background-color:transparent;");
	            }
	            }
	        });
			
		}
		finished=true;
	}
	
	public List<MemoryButton> getMemoryButtons() {
		return memoryButtons;
	}
	
	private boolean finished = false;
	
	public boolean getFinished() {
		return finished;
	}
	
	
	private void removechildrens() {
		mainbox.getChildren().removeAll(mainbox.getChildren());
		
	}
	
	private void input() {
		
		// Nur mainbox ändern (bzw. Inhalt)
		removechildrens();
		mainInput = new VBox();
		mainInput.setMinSize(600, 400);
		mainInput.setMaxSize(600, 400);
		mainInput.setAlignment(Pos.CENTER);
		mainInput.setStyle("-fx-background-color:gray;");
		
		Label enterWord = new Label("Wort eingeben");
		enterWord.setFont(new Font("Arial",25));
		enterWord.setTextFill(Color.LIGHTGREY);
		
		Label ph1 = new Label("");
		Label ph2 = new Label(" ");
		ph2.setFont(new Font("Arial",100));
		
		
		showWord = new HBox();
		showWord.setMinSize(600, 50);showWord.setMaxSize(600, 50);
		showWord.setAlignment(Pos.CENTER);
		//showWord.setStyle("-fx-background-color:lightgrey;");
		
		startbutton = new Button("Starte Spiel");
		startbutton.setStyle("-fx-background-color:gray;"
				+ "-fx-border-width: 1;"
				+ "-fx-border-color:black;"
				+ "");
		startbutton.setEffect(null);
		startbutton.setFont(new Font("Arial",18));
		startbutton.setTextFill(Color.BLACK);
		
		startbutton.setOnMouseEntered(new EventHandler<MouseEvent>
	    () {

	        public void handle(MouseEvent t) {
	        if(momword.length()>0) {
	        	startbutton.setStyle("-fx-background-color:lightgrey;"
    					+ "-fx-border-width: 1;"
    					+ "-fx-border-color:black;"
    					+ "");
    		  startbutton.setEffect(shadow);
	        }
	        	
	        }
	    
		});

		startbutton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            if(momword.length()>0) {	
            	enteredWord = momword;
            	state = 3;
            }
            }
        });
		
	    startbutton.setOnMouseExited(new EventHandler<MouseEvent>
	    () {
	        public void handle(MouseEvent t) {
	        	if(momword.length()>0) {
	        		startbutton.setStyle("-fx-background-color:gray;"
        					+ "-fx-border-width: 1;"
        					+ "-fx-border-color:black;"
        					+ "");
        		  startbutton.setEffect(shadow);
        		  startbutton.setTextFill(Color.WHITE);
		        }else {
		        	startbutton.setStyle("-fx-background-color:gray;"
		    				+ "-fx-border-width: 1;"
		    				+ "-fx-border-color:black;"
		    				+ "");
		    		startbutton.setEffect(null);
		    		startbutton.setTextFill(Color.BLACK);
		        }
	        	
	        }
	    });
		
		//mainInput.getChildren().addAll(enterWord,ph1,showWord,ph2,startbutton);
		
		
		//mainbox.getChildren().add(mainInput);
		
		
		state = 2;
	}
	public void closes() {
		stage.close();
	}
	
	public void showgame() {
		removechildrens();
		this.GameGui.startgui(game, stage, mainbox, Controller.word);
	}
	
	
	
	
	
	
	
	

}
