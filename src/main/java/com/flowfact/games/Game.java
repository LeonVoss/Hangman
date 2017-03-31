package com.flowfact.games;

import java.util.ArrayList;
import java.util.List;

import com.flowfact.daos.Variables;

public class Game {
	
	private String inputWord = "";
	private List<String> wordhave = new ArrayList<String>();
	private int mommentanversuche = 0;
	public int maximalversuche = 10;
	private List<String> charshave = new ArrayList<String>();
	private String tipped = "";
	private long Score = 0;
	private boolean saveDatas = false;
	private boolean saveMemory = false;
	public boolean isSaveMemory() {
		return saveMemory;
	}
	public void setSaveMemory(boolean saveMemory) {
		this.saveMemory = saveMemory;
	}

	private Variables varis;
	private String loadedName;
	public Game(int maxversuche,Variables varis) {
		tipped = "";
		maximalversuche = maxversuche;
		saveDatas = false;
		this.varis = varis;
		loadedName = "";
	}
	public String getLoadedName() {
		return loadedName;
	}
	
	public void setLodedName(String loadedName) {
		this.loadedName = loadedName;
	}
	
	public Variables getVaris() {
		return varis;
	}
	
	public boolean isSaveDatas() {
		return saveDatas;
	}
	
	public void setSaveDatas(boolean saveDatas) {
		this.saveDatas = saveDatas;
	}
	
	public void refreshScore() {
		Score = 0;
	}
	
	public void setScore(long Score) {
		this.Score = Score;
		if(this.Score<0) {this.Score = 0;}
	}
	
	public long getScore() {
		return Score;
	}
	
	
	public boolean isfree() {
		return tipped.isEmpty();
	}
	
	public void addtipped(String c) {
		tipped = c;
	}
	
	public String gettipped() {
		return tipped;
	}
	
	public void setword(String input) {
		inputWord = input;
		setwordhave();
	}
	
	public void refresh() {
		inputWord = "";
		wordhave = new ArrayList<String>();
		mommentanversuche = 0;
		charshave = new ArrayList<String>();
		tipped = "";
		
		
	}
	
	private void setwordhave() {
		for(int a = 0; a < inputWord.length(); a++) {
			wordhave.add("_");
		}
	}
	
	public String getword() {
		return inputWord;
	}
	
	public int momversuche() {
		return mommentanversuche;
	}
	
	public int maxversuche() {
		return maximalversuche;
	}

	public List<String> getwordhave() {
		return wordhave;
	}
	
	public List<String> getcharshave() {
		return charshave;
	}
	
	
	public void addmomversuche() {
		mommentanversuche++;
	}
	
	public void addchar(String c) {
		charshave.add(c);
	}
	
	public boolean charcontains(String c) {
		if(charshave.contains(c)) {
			return true;
			}else {
			return false;
			}
	}
	
	public boolean isfinish() {
		
		if(wordhave.contains("_")) {
    		return false;
    		} else {
    		return true;
    	    }
		
	}
	
	public boolean addwordhave(String c) {
		boolean has = false;
		int wieviele = 0;
		for(int a = 0; a < inputWord.length(); a++) {
			String m = inputWord.charAt(a)+"";
			if(m.equalsIgnoreCase(c)) {
				has = true;
				wordhave.set(a, m);
				wieviele++;
			}
			
		}
		if(has) {if(!charcontains(c)) {
			Score = Score+(wieviele*2);
		}
		}else {
			if(!charcontains(c)) {
			Score = Score-1;
		}
		}
		if(Score<0) {Score = 0;}
		
		return has;
	}
	
	
	
	
	
	

}
