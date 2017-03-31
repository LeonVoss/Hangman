package com.flowfact.daos;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class Variables implements Dao {

	
	Button button;
	TextField textField;
	List<String> charNum;
	WritableImage hangmanImage;
	
	
	@Override
	public Button getSaveButton() {
	return button;
	}

	@Override
	public void setSaveButton(Button button) {
	this.button = button;
	}

	@Override
	public TextField getTextField() {
		return textField;
	}

	@Override
	public void setTextField(TextField textField) {
	this.textField = textField;
	}

	@Override
	public List<String> getCharNum() {
	return charNum;
	}

	@Override
	public void setCharNum(List<String> charNum) {
	this.charNum = charNum;
	}

	@Override
	public WritableImage getHangmanImage() {
    return hangmanImage;
	}

	@Override
	public void setHangmanImage(WritableImage hangmanImage) {
	this.hangmanImage = hangmanImage;
	}

	


}
