package com.flowfact.clients;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;


import com.flowfact.controller.Controller;
import com.flowfact.controller.Threads;
import com.flowfact.games.Game;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameGui {

	private Game game;

	private ImageView im;

	private Stage stage;
	private VBox mainbox = new VBox();

	private String enteredWord = "";

	private int state = 0;
	private VBox toadd;

	private VBox top;
	private VBox bottom = new VBox();

	private List<String> momWord = new ArrayList<String>();

	private List<String> charnum = new ArrayList<String>();
	private WritableImage hangmanImage = new WritableImage(100,100);

	private Button scoreButton = new Button("0");
	
	
	
	

	public void startgui(Game game, Stage stage2, VBox mainbox2, String enteredWord2) {
		this.game = game;
		this.state = 1;

		game.getVaris().setHangmanImage(hangmanImage);
		
		charnum = new ArrayList<String>();

		String toa = "abcdefghijklmnopqrstuvwxyz";

		for (int a = 0; a < toa.length(); a++) {
			if (!charnum.contains((toa.charAt(a) + "").toUpperCase())) {
				charnum.add((toa.charAt(a) + "").toUpperCase());
			}
		}
		game.getVaris().setCharNum(charnum);

		stage = stage2;
		bottom = new VBox();
		toadd = null;
		im = null;
		bottom.setAlignment(Pos.CENTER);

		mainbox = mainbox2;
		enteredWord = enteredWord2;
		top = null;
		showgui();
	}

	private void setMomWord() {
		for (int a = 0; a < enteredWord.length(); a++) {
			momWord.add("_");
		}
	}

	private TextField newInput;
	
	public TextField getNewInput() {
		return newInput;
	}
	
	private HBox bottom3;
	private void showgui() {
		removechildrens();

		
		VBox oberteil = new VBox();
		oberteil.setMinSize(600, 180);
		oberteil.setMaxSize(600, 180);
		oberteil.setStyle("-fx-background-color:gray;");
		oberteil.setAlignment(Pos.TOP_CENTER);
		oberteil.getChildren().add(top());

		VBox unterteil = new VBox();
		unterteil.setMinSize(600, 200);
		unterteil.setMaxSize(600, 200);
		unterteil.setStyle("-fx-background-color:gray");
		unterteil.setAlignment(Pos.CENTER);
		unterteil.getChildren().add(bottom);
		
		
		Label ph = new Label("");
		ph.setFont(new Font("Arial",50));
		Label ph2 = new Label("      ");
		
		bottom3 = new HBox();
		bottom3.setAlignment(Pos.CENTER);
		
		Label know = new Label("Du kennst das Wort?");
		know.setFont(new Font("Arial",22));
		know.setTextFill(Color.WHITE);
if(newInput==null) {
        newInput = new TextField();
		newInput.setStyle("-fx-background-color:transparent;-fx-border-width:1;-fx-border-color:white;-fx-text-fill:white;-fx-padding:0 0 0 5;");
		newInput.setFont(new Font("Arial",20));
		newInput.setAlignment(Pos.CENTER);
		newInput.setMinSize(200, 30);newInput.setMaxSize(200, 30);
		newInput.setFocusTraversable(false);
		newInput.setCursor(Cursor.HAND);
		
		
		 newInput.textProperty().addListener(new ChangeListener<String>() {
		        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
		            if (newInput.getText().length() > game.getword().length()) {
		                String s = newInput.getText().substring(0, game.getword().length());
		                newInput.setText(s);
		            }else {
		            if(newValue!=""&&newValue.length()>oldValue.length()) {
		            	String last = newValue.charAt(newValue.length()-1)+"";		            
		            if(!game.getVaris().getCharNum().contains(last.toUpperCase())) {
		            	try {
		            	newInput.setText(oldValue);
		            	}catch(Exception e) {}
		            }
		            }

		            	
		            }
		            	
		            
		            
		        }
		    });
		
}
		bottom3.getChildren().addAll(know,ph2,newInput);
		
		unterteil.getChildren().addAll(ph,bottom3);

		VBox middleline = new VBox();
		middleline.setMinSize(500, 1);
		middleline.setMaxSize(500, 1);
		middleline.setStyle("-fx-background-color:lightgray;");
		middleline.setOpacity(0.4);
		middleline.setAlignment(Pos.CENTER);
		Label ph3 = new Label("");
		ph3.setFont(new Font("Arial",30));

		mainbox.getChildren().addAll(oberteil, middleline, ph3,unterteil);

		toadd = unterteil;

	}
	
	public void removeBottom3() {
		bottom3.getChildren().removeAll(bottom3.getChildren());
	}

	public void refreshing() {try {
		hangmanImage = new WritableImage(100,100);
		game.getVaris().setHangmanImage(hangmanImage);
	}catch(Exception e) {}
	}

	public void refresh() {
		if(game!=null)  {
		toshow.getChildren().removeAll(toshow.getChildren());
		toshow.getChildren().add(getMomWord());

		bottom.getChildren().removeAll(bottom.getChildren());
		bottom.getChildren().add(getBottom());
	
		
		}
	}
	

	public Button getScoreButton() {
		return scoreButton;
	}

	private VBox top() {
		toshow.setAlignment(Pos.CENTER);
		top = new VBox();
		top.setAlignment(Pos.TOP_CENTER);
		refresh();

		Label ph = new Label("");
		Label ph2 = new Label("");

		Label toenter = new Label("Errate das Wort");
		toenter.setFont(new Font("Arial", 25));
		toenter.setTextFill(Color.LIGHTGRAY);

		
		scoreButton.setText(game.getScore()+"");
		scoreButton.setTextFill(Color.BLACK);
		scoreButton.setOpacity(0.8);
		scoreButton.setFont(new Font("Arial",24));
		scoreButton.setStyle("-fx-background-color:transparent;-fx-padding:0 0 0 0;");
		scoreButton.setCursor(Cursor.HAND);
		
		scoreButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				
				scoreButton.setTextFill(Color.WHITE);

			
			}

		});
		scoreButton.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

						scoreButton.setTextFill(Color.BLACK);

					}
				});

		
		
		
		VBox bef = new VBox();
		bef.autosize();
		bef.setAlignment(Pos.TOP_RIGHT);
		bef.setStyle("-fx-padding: 0 20 0 0;"); 
		bef.getChildren().add(scoreButton);
		
		Label lh = new Label("");
		lh.setFont(new Font("Arial",22));
		
		top.getChildren().addAll(bef,toenter, ph2, toshow);

		return top;
	}
	
	public void refreshScoreLabel() {
		
		scoreButton.setText(""+game.getScore());
	}

	public boolean haswin() {
		return !momWord.contains("_");
	}

	public boolean hasloose() {
		return ((game.maxversuche() - game.momversuche()) <= 0);
	}

	private HBox toshow = new HBox();

	private HBox getMomWord() {
		HBox showWord = new HBox();
		showWord.setAlignment(Pos.CENTER);

		int a = 0;
		
		List<String> wordh = game.
				getwordhave();
		for (String c : wordh) {

			if (c.equalsIgnoreCase("_")) {

				Label currentchar = new Label("   ");
				currentchar.setUnderline(true);
				currentchar.setFont(new Font("Arial", 25));
				
				
				
				
				if(newInput!=null&&newInput.isFocused()&&a<=(newInput.getText().length()-1)) {
					
					currentchar.setTextFill(Color.LIGHTGRAY);
					currentchar.setOpacity(0.5);
					
					
					
				}else {
				currentchar.setTextFill(Color.WHITE);
				
				}
				
				
				
				
				
				if (a > 0) {
					Label placeholder = new Label("  ");
							
					showWord.getChildren().add(placeholder);
				}
				showWord.getChildren().add(currentchar);

			} else {

				Label currentchar = new Label(new String(c) + "");
				currentchar.setUnderline(true);
				currentchar.setFont(new Font("Arial", 25));
				
				

				if(newInput!=null&&newInput.isFocused()&&a<=(newInput.getText().length()-1)) {
					
					if((newInput.getText().charAt(a)+"").equalsIgnoreCase(c)) {
						currentchar.setTextFill(Color.LIGHTGREEN);
					}else {
						currentchar.setTextFill(Color.LIGHTCORAL);
					}
					
					
					
				}else {
				currentchar.setTextFill(Color.WHITE);
				
				}

				
				
				if(game.isfinish()) {currentchar.setTextFill(Color.LIGHTGREEN);}
				

				if (a > 0) {
					Label placeholder = new Label("  ");
					showWord.getChildren().add(placeholder);
				}
				showWord.getChildren().add(currentchar);
			}

			a++;
			
		}

		return showWord;
	}

	private VBox getBottom() {
		VBox bottom = new VBox();

		VBox beforeImage = new VBox();
		// beforeImage.autosize();
		beforeImage.setStyle("-fx-border-width:1;-fx-border-color:lightblue;");
		beforeImage.setMinSize(120, 120);
		beforeImage.setMaxSize(120, 120);
		beforeImage.setAlignment(Pos.CENTER);

		if(im==null) {im = new ImageView();}
		if(hangmanImage!=null&&hangmanImage.getWidth()==100) {
			try {
		im.setImage(hangmanImage);
			}catch(Exception e) {}
		}
		bottom.setAlignment(Pos.CENTER);
		beforeImage.getChildren().add(im);

		HBox bottom2 = new HBox();
		bottom2.setAlignment(Pos.CENTER);
		bottom2.getChildren().add(beforeImage);

		VBox showchar = new VBox();

		int start = 0;
		boolean alchar = false;
		int perline = 10;
		while (!alchar) {
			HBox to = new HBox();
			int max = start + perline;

			for (int a = start; a < max; a++) {
				String cc = "";
				try {
					cc = new String(charnum.get(a));
				} catch (Exception e) {
				}
				if (cc.isEmpty()) {
					alchar = true;
				} else {
					if (to.getChildren().size() > 0) {
						Label ph = new Label("   ");
						to.getChildren().add(ph);
					}
					if (!game.getcharshave().contains(cc.toUpperCase())) {
						Label tt = new Label(new String(cc.toUpperCase()));
						tt.setFont(new Font("Arial", 20));
						tt.setTextFill(Color.DARKKHAKI);
						to.getChildren().add(tt);
					} else {
						Label tt = new Label(new String(cc.toUpperCase()));
						tt.setFont(new Font("Arial", 20));
						tt.setTextFill(Color.LIGHTCORAL);
						tt.setStyle("-fx-opacity:0.6;");
						to.getChildren().add(tt);
					}
				}
			}

			if (to.getChildren().size() > 0) {
				if (showchar.getChildren().size() > 0) {
					Label ph2 = new Label("");
					ph2.setFont(new Font("Arial", 10));
					showchar.getChildren().add(ph2);
				}
				showchar.getChildren().add(to);
			}

			start = start + perline;

		}
		bottom2.getChildren().addAll(new Label("                       "), showchar);

		
		
		
		
		bottom.getChildren().addAll(bottom2);
		return bottom;
	}
	

	public void loose() {
		bottom.getChildren().removeAll(bottom.getChildren());
		Label lh = new Label("");
		Label st = new Label("Du hast verloren! Das Wort war : ");
		st.setFont(new Font("Arial", 26));
		st.setTextFill(Color.BLACK);
		Label st2 = new Label(enteredWord);
		st2.setFont(new Font("Arial", 25));
		st2.setTextFill(Color.GOLD);
		bottom.getChildren().addAll(lh, st, st2);
		
		replay2();
		
		
	}

	public void won() {
		bottom.getChildren().removeAll(bottom.getChildren());
		Label lh = new Label("");
		Label st = new Label("Du hast gewonnen!");
		st.setFont(new Font("Arial", 26));
		st.setTextFill(Color.WHITE);
		bottom.getChildren().addAll(lh, st);
		replay();
	}

	private Button startbutton;
	private Button savebutton;
	private Button memoryButton;

	public Button getMemoryButton() {
		return memoryButton;
	}
	
	public void replay() {
		startbutton = new Button("Neues Wort");
		startbutton.setCursor(Cursor.HAND);
		startbutton.setStyle("-fx-background-color:gray;" + "-fx-border-width: 1;" + "-fx-border-color:white;" + "");
		startbutton.setEffect(new DropShadow());
		startbutton.setTextFill(Color.WHITE);
		startbutton.setFont(new Font("Arial",20));
		
		savebutton = new Button("Score Speichern");
		savebutton.setCursor(Cursor.HAND);
		savebutton.setStyle("-fx-background-color:gray;-fx-padding:0;");
		savebutton.setEffect(null);
		savebutton.setTextFill(Color.GOLD);
		savebutton.setFont(new Font("Arial",18));
		
		memoryButton = new Button("Spielstand sichern");
		memoryButton.setCursor(Cursor.HAND);
		memoryButton.setStyle("-fx-background-color:gray;-fx-padding:0;");
		memoryButton.setEffect(null);
		memoryButton.setTextFill(Color.GOLD);
		memoryButton.setFont(new Font("Arial",18));
		
		

		startbutton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				startbutton.setStyle(
						"-fx-background-color:coral;" + "-fx-border-width: 1;" + "-fx-border-color:white;" + "");

				startbutton.setTextFill(Color.WHITE);

			
			}

		});
		startbutton.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

						startbutton.setStyle(
								"-fx-background-color:gray;" + "-fx-border-width: 1;" + "-fx-border-color:white;" + "");

						startbutton.setTextFill(Color.WHITE);

					}
				});

		startbutton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Controller.word = "";
				startbutton.setVisible(false);
				savebutton.setVisible(false);
			}
		});
		
		savebutton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				

				savebutton.setTextFill(Color.YELLOW);
				savebutton.setUnderline(true);

			}

		});
		savebutton.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

					

						savebutton.setTextFill(Color.GOLD);
						savebutton.setUnderline(false);
					}
				});

		savebutton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				game.setSaveDatas(true);
				savebutton.setVisible(false);
				startbutton.setVisible(false);
			}
		});
		
		memoryButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				

				memoryButton.setTextFill(Color.YELLOW);
				memoryButton.setUnderline(true);

			}

		});
		memoryButton.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

					

						memoryButton.setTextFill(Color.GOLD);
						memoryButton.setUnderline(false);
					}
				});

		

		Label lh = new Label("");
		lh.setFont(new Font("Arial",25));
		Label lh2 = new Label("");
		bottom.getChildren().addAll(lh, startbutton,lh2,savebutton,new Label(""),memoryButton,new Label(""));
	}
	
	
	public void replay2() {
		startbutton = new Button("Neues Spiel");
		startbutton.setCursor(Cursor.HAND);
		startbutton.setStyle("-fx-background-color:gray;" + "-fx-border-width: 1;" + "-fx-border-color:white;" + "");
		startbutton.setEffect(new DropShadow());
		startbutton.setTextFill(Color.WHITE);
		startbutton.setFont(new Font("Arial",20));
		
		savebutton = new Button("Score Speichern");
		savebutton.setCursor(Cursor.HAND);
		savebutton.setStyle("-fx-background-color:gray;-fx-padding:0;");
		savebutton.setEffect(null);
		savebutton.setTextFill(Color.GOLD);
		savebutton.setFont(new Font("Arial",18));
		
		memoryButton = new Button("Spielstand sichern");
		memoryButton.setCursor(Cursor.HAND);
		memoryButton.setStyle("-fx-background-color:gray;-fx-padding:0;");
		memoryButton.setEffect(null);
		memoryButton.setTextFill(Color.GOLD);
		memoryButton.setFont(new Font("Arial",18));
		
		

		startbutton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				startbutton.setStyle(
						"-fx-background-color:coral;" + "-fx-border-width: 1;" + "-fx-border-color:white;" + "");

				startbutton.setTextFill(Color.WHITE);

			
			}

		});
		startbutton.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

						startbutton.setStyle(
								"-fx-background-color:gray;" + "-fx-border-width: 1;" + "-fx-border-color:white;" + "");

						startbutton.setTextFill(Color.WHITE);

					}
				});

		startbutton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Controller.word = "";
				startbutton.setVisible(false);
				savebutton.setVisible(false);
			}
		});
		
		savebutton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				

				savebutton.setTextFill(Color.YELLOW);
				savebutton.setUnderline(true);

			}

		});
		savebutton.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

					

						savebutton.setTextFill(Color.GOLD);
						savebutton.setUnderline(false);
					}
				});

		savebutton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				game.setSaveDatas(true);
				savebutton.setVisible(false);
				startbutton.setVisible(false);
			}
		});
		
		memoryButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				

				memoryButton.setTextFill(Color.YELLOW);
				memoryButton.setUnderline(true);

			}

		});
		memoryButton.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

					

						memoryButton.setTextFill(Color.GOLD);
						memoryButton.setUnderline(false);
					}
				});

		

		Label lh = new Label("");
		lh.setFont(new Font("Arial",25));
		Label lh2 = new Label("");
		bottom.getChildren().addAll(lh, startbutton,lh2,savebutton,new Label(""));
	}
	
	private Button save;
	private TextField input;
	private Button save2;
	private TextField input2;
	
	public void saveMemoryGui() {
		removechildrens();
		Label input2Name = new Label("Gebe deinen Namen ein");
		input2Name.setFont(new Font("Arial",24));
		input2Name.setTextFill(Color.WHITE);
		
		
		input2 = new TextField();
		input2.setMinSize(200, 30);input2.setMaxSize(200, 30);
		input2.setBorder(null);
		input2.setPadding(Insets.EMPTY);
		input2.setStyle("-fx-background-color:transparent;-fx-border-width:1px;-fx-border-color:lightgray;-fx-text-fill:gold;-fx-padding: 0 0 0 5;");
		input2.setFont(new Font("Arial",18));
		input2.setCursor(Cursor.HAND);
		
		if(!game.getLoadedName().isEmpty()) {

		input2.setText(game.getLoadedName());
		input2.setEditable(false);
		
		}
		
		save2 = new Button("Speichern");
		save2.setFont(new Font("Arial",20));
		save2.setTextFill(Color.LIGHTGRAY);
		save2.setStyle("-fx-background-color:transparent;-fx-border-width:2px;-fx-border-color:white;");
		
		save2.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				

				save2.setTextFill(Color.WHITE);
				save2.setUnderline(true);


			}

		});
		save2.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

					

						save2.setTextFill(Color.LIGHTGRAY);
						save2.setUnderline(false);
					}
				});
		
		
		
		Label lh = new Label("");
		Label lh2 = new Label("");
		
		mainbox.getChildren().addAll(input2Name,lh,input2,lh2,save2);
		
		
	}
	
	public Button getSaveButton2() {
		return save2;
	}
	
	public String getText2() {
		return input2.getText();
	}
	
	public void saveDatasGui() {
		removechildrens();
		Label inputName = new Label("Gebe deinen Namen ein");
		inputName.setFont(new Font("Arial",24));
		inputName.setTextFill(Color.WHITE);
		
		input = new TextField();
		input.setMinSize(200, 30);input.setMaxSize(200, 30);
		input.setBorder(null);
		input.setPadding(Insets.EMPTY);
		input.setStyle("-fx-background-color:transparent;-fx-border-width:1px;-fx-border-color:lightgray;-fx-text-fill:gold;-fx-padding: 0 0 0 5;");
		input.setFont(new Font("Arial",18));
		input.setCursor(Cursor.HAND);
		
		if(!game.getLoadedName().isEmpty()) {

			input.setText(game.getLoadedName());
			input.setEditable(false);
			
			}
		
		save = new Button("Speichern");
		save.setFont(new Font("Arial",20));
		save.setTextFill(Color.LIGHTGRAY);
		save.setStyle("-fx-background-color:transparent;-fx-border-width:2px;-fx-border-color:white;");
		
		save.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent t) {

				

				save.setTextFill(Color.WHITE);
				save.setUnderline(true);


			}

		});
		save.setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent t) {

					

						save.setTextFill(Color.LIGHTGRAY);
						save.setUnderline(false);
					}
				});
		
		
		
		Label lh = new Label("");
		Label lh2 = new Label("");
		
		mainbox.getChildren().addAll(inputName,lh,input,lh2,save);
		
		game.getVaris().setSaveButton(save);
		game.getVaris().setTextField(input);
		
	}
	
	

	public void removechildrens() {
		try {
			mainbox.getChildren().removeAll(mainbox.getChildren());
		} catch (Exception e) {
		}
	}

}
