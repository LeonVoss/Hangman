package com.flowfact.clients;

import java.util.Scanner;

import com.flowfact.games.Game;

public class Client {

	public String inputstream(boolean charis) {
		if(charis) {
			String b = new Scanner(System.in).next().trim().charAt(0)+"";
			return b;
		}else {
			String inputWord = (String) new Scanner(System.in).nextLine();
			return inputWord;
		}
	}
	
	public void refresh(Game game)
	{
		
    schleife();
		
    System.out.println("Du Hast "+(game.maxversuche()-game.momversuche())+" Versuch/e!");
    System.out.println("");
    System.out.println("");
    
    String toadd = "";
    for(int a = 0; a < game.getwordhave().size(); a++) {
    	if(toadd.isEmpty()) {
    		toadd=game.getwordhave().get(a);
    	}else {
    		toadd += " "+game.getwordhave().get(a);
    	}
    }
    
    System.out.println("     "+toadd);
    
    System.out.println("");
    System.out.println("");
    System.out.println("Bustabe : ");
    
    
	}
	
	
	public void verloren(Game game) {
		String giveout = "";
    	for(int a = 0; a < game.getword().length(); a++) {
    		if(giveout.isEmpty()) {
    			giveout = game.getword().charAt(a)+"";
    		}else {
    			giveout += " "+game.getword().charAt(a);
    		}
    	}
		schleife();
		System.out.println("Du hast verloren! Das Wort war : ");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("           "+giveout);
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
	}
	
	public void gewonnen(Game game) {
		String giveout = "";
    	for(int a = 0; a < game.getword().length(); a++) {
    		if(giveout.isEmpty()) {
    			giveout = game.getword().charAt(a)+"";
    		}else {
    			giveout += " "+game.getword().charAt(a);
    		}
    	}
		schleife();
		System.out.println("Du hast das Wort mit "+game.momversuche()+" Versuch/en erraten!");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("           "+giveout);
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
    	System.out.println("");
	}
	
	
	
	private void schleife() {
		for(int a = 0; a < 10; a++) {
			System.out.println("");
		}
		
	}
	
	
	
	
	
}
