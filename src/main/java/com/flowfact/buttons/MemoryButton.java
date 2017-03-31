package com.flowfact.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MemoryButton {

	String name;
	int score;
	VBox btn;
	
	public MemoryButton(String name,int score,VBox btn) {
		this.name = name;
		this.score = score;
		this.btn = btn;
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
