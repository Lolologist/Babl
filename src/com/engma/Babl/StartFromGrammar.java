package com.engma.Babl;

import java.io.IOException;
import java.util.*;

public class StartFromGrammar
{
	static Dictionary dictionary;
	static HashMap<String,Word> dict;
	
	public static void startFromGrammar(Dictionary d) throws ClassNotFoundException, IOException
	{
		dictionary = d;
		dict = dictionary.entries;
		doAllTheWork();
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		Load load = new Load();
		load.load();
		dictionary = load.loadedDictionary;
		dict = dictionary.entries;
		doAllTheWork();
		
	}
	
	public static void doAllTheWork() throws ClassNotFoundException, IOException
	{
		//Iterator it = dict.entrySet().iterator();
//	    while (it.hasNext()) {
//	        Map.Entry pairs = (Map.Entry)it.next();
//	        if (pairs.getKey().equals("potato"))
//	        {
//	        	System.out.println(pairs.getKey() + " = " + pairs.getValue());
//	        }
//	        if (pairs.getKey().equals("obex"))
//	        {
//	        	System.out.println(pairs.getKey() + " = " + pairs.getValue());	
//	        }
//	        //it.remove(); // avoids a ConcurrentModificationException
//	    }

	    //System.out.println("single syllable words to use: "+dictionary.singleSyllableWords.size());
//	    for (BaseWordForm each : dictionary.singleSyllableWords)
//	    {
//	    	System.out.println(each);
//	    	
//	    }
	    //System.out.println("fsize1"+dictionary.entries.size());
	    makeGlosses();
	}

	private static void makeGlosses() throws ClassNotFoundException, IOException
	{
		Random rand = new Random();
		ArrayList<BaseWordForm> shortestWords = new ArrayList<BaseWordForm>();
		ArrayList<BaseWordForm> shortWords = new ArrayList<BaseWordForm>();
		ArrayList<BaseWordForm> mediumWords = new ArrayList<BaseWordForm>();
		ArrayList<BaseWordForm> longWords = new ArrayList<BaseWordForm>();
		
		for (BaseWordForm each : dictionary.singleSyllableWords)
		{
			if (each.size() == 1)
			{
				shortestWords.add(each);
			} else if (each.size() == 2) {
				shortWords.add(each);
			} else if (each.size() == 3) {
				mediumWords.add(each);
			} else if (each.size() >= 4) {
				longWords.add(each);
			}
		}
//		System.out.println("shortest:");
//		for (BaseWordForm each : shortestWords)
//		{
//			System.out.println(each.toString());
//		}
//		System.out.println("short:");
//		for (BaseWordForm each : shortWords)
//		{
//			System.out.println(each.toString());
//		}
		ArrayList<BaseWordForm> soFar = new ArrayList<BaseWordForm>();
		ArrayList<String> glosses = new ArrayList<String>(
			    Arrays.asList("AUG", "1SG", "2SG", "3SG", "1PL", "2PL", "3PL", "PL", "INF", "EMP", "FUT", "GEN", "IMP", "MOD", "NEG", "PRF", "PRES", "PST", "WH.Q", "DIM", "SUPL", "COMP"));
		//objective==accusative, possessive = genitive, make them short when possible?
		
		int r = rand.nextInt(dictionary.singleSyllableWords.size());
		for (String each : glosses)
		{
			int added = 0;
			while (added == 0)
			{
				if (!soFar.contains(dictionary.singleSyllableWords.get(r)))
				{
					added = 1;
					soFar.add(dictionary.singleSyllableWords.get(r));
					Word w = new Word(dictionary.singleSyllableWords.get(r), each, each);
					dictionary.entries.put(each, w);
					//System.out.println(each+" added");
					r = rand.nextInt(dictionary.singleSyllableWords.size());
				}
				else {
					System.out.println("tried to add a word that already existed, let's try that again");
					r = rand.nextInt(dictionary.singleSyllableWords.size());
				}
				
			}
			
			
		}
		System.out.println("Made glosses and other grammatical goodies. Probably.");
//		System.out.println("fsize2"+dictionary.entries.size());
		new Save(dictionary);
//		System.out.println(dictionary.entries.get("potato"));
//		System.out.println(dictionary.entries.get("-PL-"));
//		System.out.println(dictionary.entries.get("potato").toString() + dictionary.entries.get("-PL-").toString());
		
	}

}
