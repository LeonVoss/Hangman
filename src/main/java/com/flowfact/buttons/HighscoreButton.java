package com.flowfact.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HighscoreButton {

	String name;
	int score;
	VBox btn;
	String date;
	
	public HighscoreButton(String name,int score,VBox btn,String date) {
		this.name = name;
		this.score = score;
		this.btn = btn;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public VBox getBtn() {
		return btn;
	}

	public void setBtn(VBox btn) {
		this.btn = btn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
}
