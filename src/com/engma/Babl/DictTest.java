package com.engma.Babl;

import java.util.*;

public class DictTest
{
	

	public static void main(String[] args) throws Exception
	{
		Dictionary				dictionary;
		System.out.println("Dictionary mutability test");
		Load load = new Load();
		load.load();

		dictionary = load.loadedDictionary;
		HashMap<String, Word> dict = dictionary.entries;

		System.out.println("original value of archaic:"+dict.get("archaic"));

		Word archaic = new Word(dict.get("finish").getWord(), dict.get("archaic").getEnglish(), dict.get("archaic").getPos());
		Word finish = new Word(dict.get("archaic").getWord(), dict.get("finish").getEnglish(), dict.get("finish").getPos());
		dict.put("archaic",archaic);
		dict.put("finish", finish);	
		
		new Save(dictionary);
	}

}