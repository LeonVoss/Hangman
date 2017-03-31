package com.flowfact.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flowfact.clients.Client;
import com.flowfact.clients.GuiClient;

import com.flowfact.controller.Controller;
import com.flowfact.daos.Variables;
import com.flowfact.controller.Controller;
import com.flowfact.games.Game;
import com.flowfact.highscore.ImplDAODatabase;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class App extends Application
{
	
	public static void main(String args[]) {
		Platform.runLater(new Runnable() {public void run() {
Variables varis = new Variables();
		
		Game game = new Game(10,varis);
		ImplDAODatabase hsList = new ImplDAODatabase();
		Controller control = new Controller(game,hsList);
		control.startgame();
		}
		});
		
	}
	
	@Override
	public void start(Stage primaryStage)  {
		
		
		
		
	}
  
}