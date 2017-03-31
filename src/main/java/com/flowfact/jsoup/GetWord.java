package com.flowfact.jsoup;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.flowfact.clients.GameGui;
import com.flowfact.controller.Controller;

public class GetWord {

	
	public static String getword() {
		
		String word = "";
		
		while(!checkword(word)) {
			
			try {
				
				Document d = Jsoup.connect("http://api.wordnik.com/v4/words.json/randomWord?hasDictionaryDef=true&includePartOfSpeech=noun&minCorpusCount=8000&maxCorpusCount=-1&minDictionaryCount=3&maxDictionaryCount=-1&minLength=6&maxLength=15&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5").ignoreContentType(true).userAgent("Mozilla/5.0").timeout(8000).get();
				word = d.body().html().split("\"}")[0].split(":\"")[1].toUpperCase();
				
			}catch(Exception e) {}
			
			
		}
		
		System.out.println(word);
	return word;
	}
    
	
	private static boolean checkword(String word) {
		List<String> charnum = new ArrayList<String>();
		
		String toa = "abcdefghijklmnopqrstuvwxyz";
		
		for(int a = 0; a < toa.length(); a++) {
			if(!charnum.contains(toa.charAt(a)+"".toUpperCase())) {
			charnum.add(toa.charAt(a)+"".toUpperCase());
			}
		}
		
		boolean isok = false;
		
if(!word.isEmpty()&&word.length()<=15) {
	List<String> splittedword = new ArrayList<String>();
	for(int a = 0; a < word.length(); a++)
	{
		splittedword.add(word.charAt(a)+"".toUpperCase());
	}
	
	for(String c : charnum) {
		while(splittedword.contains(c.toUpperCase())) {
		splittedword.remove(c.toUpperCase());
		}
		
	}
	
	if(splittedword.size()<=0) {
		isok=true;
	}
	
	
}
		
		
		return isok;
		
	}
	
	
	
}
