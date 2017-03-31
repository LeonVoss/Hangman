package com.flowfact.highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImplDAODatabase implements HighscoreDAO {

	List<Highscore> highscores = new ArrayList<Highscore>();

	private Connection connect;
	
	public boolean memoryExist() {
		boolean exist = false;
		
		try {
			List<Memory> memorys = getMemoryByDatabase();
			if(memorys.size()>0) {
				exist=true;
			}
			
		}catch(Exception e) {}
		
		return exist;
	}
	
	public ImplDAODatabase() {
		
		try {
		 connect = DriverManager
			        .getConnection("jdbc:mysql://localhost/hangman?"
			                + "user=root&password=11LeNV0$");
		}catch(Exception e) {System.out.println("Connection failed!");}
		highscores = getHighscoresByDatabase();
	}

	@Override
	public void addHighscore(Highscore hs) {
		if (hs != null) {
			highscores = getHighscoresByDatabase();
			highscores.add(hs);
			hsAdd(hs);
		}
	
		
	}

	@Override
	public void removeHighscore(Highscore hs) {
		highscores = getHighscoresByDatabase();
		highscores.remove(hs);
	    hsRemove(hs);
	}

	@Override
	public List<Highscore> getHighscores() {
		highscores = getHighscoresByDatabase();
		return highscores;
	}
	
	private List<String> getHighscoreNames() {
		List<String> names = new ArrayList<String>();
		
try {
	Statement statement = connect.createStatement();
	ResultSet resultSet = statement
            .executeQuery("select * from highscorenames");
	while (resultSet.next()) {
		names.add(resultSet.getString("name"));
	}
	resultSet.close();
	statement.close();
			
		}catch(Exception e) {}
		
		return names;
	}
	
	private void hsAdd(Highscore hs) {
		try {
			
			if(!getHighscoreNames().contains(hs.getName())) {
				PreparedStatement preparedStatement = connect
	                    .prepareStatement("insert into highscorenames (name) values ('"+hs.getName()+"')");
				preparedStatement.execute();
				preparedStatement.close();
				
			}
			
			PreparedStatement preparedStatement = connect
                    .prepareStatement("insert into highscoreusers (name,score,date) values ('"+hs.getName()+"','"+hs.getScore()+"','"+hs.getDate()+"')");
			preparedStatement.execute();
			preparedStatement.close();
			
		}catch(Exception e) {}
	}
	
    private void hsRemove(Highscore hs) {
try {
			
			PreparedStatement preparedStatement = connect
                    .prepareStatement("delete from highscores WHERE name="+hs.getName()+" and score="+hs.getScore()+"");
			preparedStatement.execute();
			preparedStatement.close();
			
		}catch(Exception e) {}
	}

    public List<Memory> getMemoryByDatabase() {
    	List<Memory> listed = new ArrayList<Memory>();
		try {
		Statement statement = connect.createStatement();
		
		ResultSet resultSet = statement
                .executeQuery("select * from memory");
		while (resultSet.next()) {
	    String name = "";
	    int score = 0;
	    try {
	    	name = resultSet.getString("name");
	    	score = resultSet.getInt("score");
	    }catch(Exception e) {}
	    if(!name.isEmpty()) {
	    	Memory hScore = new Memory(name,score);
	    	listed.add(hScore);
	    }
		}
		
		resultSet.close();
		statement.close();
		}catch(Exception e) {System.out.println("Highscore get Failed!");}
		
		
		
		
		return listed;
    }
    
    
    public List<Highscore> getHighscoresByUser(String name) {
    	List<Highscore> list = new ArrayList<Highscore>();
    	
    	try {
    		Statement statement = connect.createStatement();
    		ResultSet resultSet = statement
    	            .executeQuery("select * from highscoreusers where name='"+name+"'");
    		while (resultSet.next()) {
    			
    		list.add(new Highscore(resultSet.getString("name"),resultSet.getInt("score"),resultSet.getString("date")));
    		}
    		resultSet.close();
    		statement.close();
    	}catch(Exception e) {}
    	
    	return list;
    }
    
    public List<Highscore> getHighscoresByDatabase() {
    	List<Highscore> listed = new ArrayList<Highscore>();
    	try {
    	
    		List<Highscore> allHighscores = new ArrayList<Highscore>();
    		
    		List<String> names = getHighscoreNames();
    		
    		for(String currentName : names)
    		{
    			Highscore max = getHighestScore(currentName);
    			if(max!=null) {
    				allHighscores.add(max);
    			}
    		}
    		
    	
    		while(allHighscores.size()>0) {
    		int max = -1;
    		Highscore tod = null;
    		for(Highscore s : allHighscores) {
    			if(s.getScore()>max) {
    				tod = s;
    				max = s.getScore();
    			}
    		}
    		
    		listed.add(tod);
    		allHighscores.remove(tod);
    			
    		if(listed.size()>=15) {
    			break;
    		}
    			
    			
    		}
    	
    	}catch(Exception e) {System.out.println("Highscore get Failed!");}
    	return listed;
    }
    
    
    private Highscore getHighestScore(String name) {
    	Highscore score = null;
    	
    	try {
    		Statement statement = connect.createStatement();
    		ResultSet resultSet = statement
    	            .executeQuery("select * from highscoreusers where name='"+name+"' order by score desc limit 1");
    		while (resultSet.next()) {
    		score = new Highscore(name,resultSet.getInt("score"),resultSet.getString("date"));
    		break;
    		}
    		resultSet.close();
    		statement.close();
    	}catch(Exception e) {}
    	
    	return score;
    }
    
	

	public void deleteMemory(String name) {
try {
			
			PreparedStatement preparedStatement = connect
                    .prepareStatement("delete from memory where name='"+name+"'");
			preparedStatement.execute();
			preparedStatement.close();
			
		}catch(Exception e) {}
	}
	
	public void addMemory(String name,int score) {
try {
			
			PreparedStatement preparedStatement = connect
                    .prepareStatement("insert into memory (name,score) values ('"+name+"','"+score+"')");
			preparedStatement.execute();
			preparedStatement.close();
			
		}catch(Exception e) {}
		
	}

}
