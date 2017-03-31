package com.flowfact.highscore;

public class Highscore {
	
	String name;
	int score;
	
	String date;
	
	public Highscore(String name,int score,String date) {
		this.name = name;
		this.score = score;
		this.date = date;
	}
	
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	

}
