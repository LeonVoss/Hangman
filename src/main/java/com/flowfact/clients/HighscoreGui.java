package com.flowfact.clients;

import java.io.File;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.flowfact.app.App;
import com.flowfact.buttons.HighscoreButton;
import com.flowfact.buttons.MemoryButton;
import com.flowfact.controller.Threads;
import com.flowfact.highscore.Highscore;
import com.flowfact.highscore.ImplDAODatabase;
import com.flowfact.highscore.Memory;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HighscoreGui {
	
	private boolean isOpen = false;
	private Stage stage;
	private List<Highscore> highscores = new ArrayList<Highscore>();
	private VBox mainbox;
	private ScrollPane pane;
	private VBox showView;
	private ImplDAODatabase hsList;
	
	public HighscoreGui(ImplDAODatabase hsList) {
		stage = new Stage();
		isFinished=false;
		this.hsList = hsList;
	}
	
	
	public void show(int width,int height,List<Highscore> highscores) {
		buttons = new ArrayList<HighscoreButton>();
		isFinished = false;
		isOpen = true;
		this.highscores = highscores;
		
		stage.setMinWidth(width);stage.setMaxWidth(width);
		stage.setMinHeight(height);stage.setMaxHeight(height);
		
		
	    mainbox = new VBox();
		mainbox.setStyle("-fx-background-color:gray;");

		
		
		Scene sc = new Scene(mainbox);
		try {
		sc.getStylesheets().add(App.class.getResource("style.css").toExternalForm());
		}catch(Exception e) {}
		
		
		stage.setScene(sc);
		
		stage.setTitle("HighScores");
		stage.getIcons().add(new Image(App.class.getResourceAsStream("ListImage.png")));
		
		stage.show();
		
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  close();
	          }
	      }); 
		addPane();
		
	}
	
	private void addPane() {
		showView = new VBox();
		showView.setAlignment(Pos.TOP_CENTER);
		showView.setMinSize(stage.getWidth(),stage.getHeight()-38);
		
		showView.setMaxWidth(stage.getWidth());
		showView.setStyle("-fx-background-color:gray;");
		
		addButtons();
		
		pane = new ScrollPane(showView);
		
		pane.setMinSize(stage.getWidth()-14, stage.getHeight()-36);
		pane.setMaxSize(stage.getWidth()-14, stage.getHeight()-36);
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		pane.setFocusTraversable(true);
		
		mainbox.getChildren().add(pane);
		
	}
	
	
	private boolean isFinished = false;
	public boolean isFinished() {
		return isFinished;
	}
	
	private List<HighscoreButton> buttons = new ArrayList<HighscoreButton>();
	
	public List<HighscoreButton> getHighScoreButtons()
	{
	return buttons;	
	}
	
	
	public Button showUser(Highscore score) {
		removeChildrens();
		
		List<String> dateTimeAlreadyHas = new ArrayList<String>();
		
		dateTimeAlreadyHas.add(score.getDate());
		
		HBox topView = new HBox();
		topView.setAlignment(Pos.CENTER_LEFT);
		
		Button backButton = new Button();
		backButton.setAlignment(Pos.CENTER);
		backButton.setPadding(Insets.EMPTY);
		backButton.setBorder(Border.EMPTY);
		int size = 36;
		backButton.setStyle("-fx-background-color:transparent;-fx-background-image: url('https://www.materialui.co/materialIcons/navigation/arrow_back_black_"+size+"x"+size+".png');");
		backButton.setMinSize(size, size);backButton.setMaxSize(size,size);
		backButton.setCursor(Cursor.HAND);
		
		Label lh = new Label("");
		lh.setFont(new Font("Arial",8));
		Label lh2 = new Label("");
		lh2.setFont(new Font("Arial",8));
		
		
		Label name = new Label(score.getName());
		name.setFont(new Font("Arial",25));
		name.setTextFill(Color.DARKKHAKI);
		
		
		
		topView.setMinWidth(stage.getWidth());
		topView.setMaxWidth(stage.getWidth());
		
		topView.getChildren().add(backButton);
		
		HBox dateAndScore = new HBox();
	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		String currentDate = dateFormat.format(date);
	    String thatDate = score.getDate().split(" ")[0];
		String toPut = currentDate.equalsIgnoreCase(thatDate) ? "Heute" : thatDate;
	    
		Label dateLabel = new Label(toPut);
		dateLabel.setTextFill(Color.DARKGRAY);
		dateLabel.setFont(new Font("Arial",16));
		
		Label timeLabel = new Label(score.getDate().split(" ")[1]);
		timeLabel.setTextFill(Color.DARKGRAY);
		timeLabel.setFont(new Font("Arial",16));
		
		Label scoreLabel = new Label(score.getScore()+"");
		scoreLabel.setTextFill(Color.LIGHTGRAY);
		scoreLabel.setFont(new Font("Arial",22));
		
		VBox dateTime = new VBox();
		dateTime.setAlignment(Pos.CENTER);
		dateTime.getChildren().addAll(dateLabel,timeLabel);
		
		dateAndScore.getChildren().addAll(dateTime,new Label("    "),scoreLabel);
		dateAndScore.setAlignment(Pos.CENTER);
		
		VBox middleline = new VBox();
		middleline.setMinSize(200, 1);
		middleline.setMaxSize(200, 1);
		middleline.setStyle("-fx-background-color:lightgray;");
		middleline.setOpacity(0.4);
		middleline.setAlignment(Pos.CENTER);
		Label lh3 = new Label("");
		lh3.setFont(new Font("Arial",5));
		
		showView.getChildren().addAll(topView,name,lh,dateAndScore,lh2,middleline,lh3);
		
		
		List<Highscore> scoresUser = hsList.getHighscoresByUser(score.getName());
		Collections.reverse(scoresUser);
		
		for(Highscore currentScore : scoresUser) {
			
			if(!dateTimeAlreadyHas.contains(currentScore.getDate())) 
			{
				dateTimeAlreadyHas.add(currentScore.getDate());
				
				
				HBox dateAndScore2 = new HBox();
				
				String thatDate2 = currentScore.getDate().split(" ")[0];
				String toPut2 = currentDate.equalsIgnoreCase(thatDate2) ? "Heute" : thatDate2;
				
				Label dateLabel2 = new Label(toPut2);
				dateLabel2.setTextFill(Color.DARKGRAY);
				dateLabel2.setFont(new Font("Arial",15));
				
				Label timeLabel2 = new Label(currentScore.getDate().split(" ")[1]);
				timeLabel2.setTextFill(Color.DARKGRAY);
				timeLabel2.setFont(new Font("Arial",15));
				
				Label scoreLabel2 = new Label(currentScore.getScore()+"");
				scoreLabel2.setTextFill(Color.LIGHTGRAY);
				scoreLabel2.setFont(new Font("Arial",21));
				
				VBox dateTime2 = new VBox();
				dateTime2.setAlignment(Pos.CENTER);
				dateTime2.getChildren().addAll(dateLabel2,timeLabel2);
				
				dateAndScore2.getChildren().addAll(dateTime2,new Label("    "),scoreLabel2);
				dateAndScore2.setAlignment(Pos.CENTER);
				
				Label lhh = new Label("");
				lhh.setFont(new Font("Arial",6));
				showView.getChildren().addAll(dateAndScore2,lhh);
				
				
				
			}
			
		}
		
		
		
		
		
		backButton.setOnMouseEntered(new EventHandler<MouseEvent>
	    () {

	        public void handle(MouseEvent t) {
	       backButton.setUnderline(true);
	        	
	        }
	    
		});
		backButton.setOnMouseExited(new EventHandler<MouseEvent>
	    () {

	        public void handle(MouseEvent t) {
	       backButton.setUnderline(false);
	        	
	        }
	    
		});
		
		return backButton;
		
	}
	
public void addButtons() {
		removeChildrens();
		
		for(Highscore currentScore : highscores) {
			
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
			if(highscores.get(highscores.size()-1)!=currentScore) {
				Label lh3 = new Label("");	
				VBox line = new VBox();
				line.setMinSize(250, 1);
				line.setMaxSize(250, 1);
				line.setStyle("-fx-background-color:lightgray;");
				line.setOpacity(0.4);
				line.setAlignment(Pos.CENTER);
				toadd.getChildren().addAll(lh3,line);
				}else {
					Label lh3 = new Label("");
					toadd.getChildren().addAll(lh3);
				}
			showView.getChildren().add(toadd);
			
			
			 HighscoreButton btn = new HighscoreButton(currentScore.getName(),currentScore.getScore(),toadd,currentScore.getDate());
			buttons.add(btn);
			 
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
		isFinished=true;
	}
	
	private List<Highscore> orderList() {
		List<Highscore> orded = new ArrayList<Highscore>();
		
		
		List<Highscore> copy = new ArrayList<Highscore>();
		for(Highscore currentScore : highscores) {
			copy.add(currentScore);
		}
		
		while(copy.size()>0) {
			int max = 0;
			List<Highscore> addDelete = new ArrayList<Highscore>();
			for(Highscore currentScore : copy)
			{
				if(currentScore.getScore()>max) {
					max = currentScore.getScore();
				}
			}
			
			for(Highscore currentScore : copy) {
				if(currentScore.getScore()==max) {
					addDelete.add(currentScore);
				}
			}
			
			for(Highscore currentScore : addDelete) {
				copy.remove(currentScore);
				orded.add(currentScore);
			}
			
			
		}
				
		
		
		return orded;
	}
	
	
	
	public void close() {try {
		stage.close();
	}catch(Exception e) {}
		stage = new Stage();
		refresh();
	}
	
	private void refresh() {
		highscores = new ArrayList<Highscore>();
		isOpen = false;
		mainbox = null;
		pane = null;
	}
	
	private void removeChildrens() {
		showView.getChildren().removeAll(showView.getChildren());
	}
	
	
	public void setPosition(int x,int y) {
		if(isOpen) {
		stage.setX(x);
		stage.setY(y);
		}
	}
	
	
	
	
	
	
	public boolean isOpen() {
		return isOpen;
	}

}
