package com.flowfact.highscore;

import java.util.List;

public interface HighscoreDAO {

	public void addHighscore(Highscore hs);
	public void removeHighscore(Highscore hs);
	public List<Highscore> getHighscores();
	
}
