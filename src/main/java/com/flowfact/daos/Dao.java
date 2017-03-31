package com.flowfact.daos;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public interface Dao {

	
public Button getSaveButton();
public void setSaveButton(Button button);

public TextField getTextField();
public void setTextField(TextField textField);

public List<String> getCharNum();
public void setCharNum(List<String> charNum);

public WritableImage getHangmanImage();
public void setHangmanImage(WritableImage hangmanImage);


}
	
	
	
	

