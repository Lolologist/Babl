package com.engma.Babl;

import java.io.*;
import java.util.*;

public class Load
{
	Dictionary							loadedDictionary;

	TreeMap<String, ArrayList<String>>	englishDictionary		= new TreeMap<String, ArrayList<String>>();

//	HashMap<String, String>				wordsToGenerate		= new HashMap<String, String>();			//english word / pos
//	HashMap<String, String>				wordsToRegenerate	= new HashMap<String, String>();			//english word / pos
//	HashMap<String, String>				specificWords		= new HashMap<String, String>();			//english word / new language word / POS
	
	boolean translator = false;
	public void load() throws ClassNotFoundException, IOException
	{
		System.out.println("LOAD: Attempting to load an existing multi-language dictionary (and extra words and junk)");
		try {
		FileInputStream existingDualLanguagedictionaryInputFile = new FileInputStream("Resources\\dictionary.ser");
		ObjectInputStream obj_in = new ObjectInputStream(existingDualLanguagedictionaryInputFile);
		loadedDictionary = (Dictionary) obj_in.readObject();
		
		System.out.println("De-serialized existing dictionary successfully.");
		existingDualLanguagedictionaryInputFile.close();
		} catch (Exception e)
		{
			System.out.println("No existing dual-language dictionary detected.");
		}
		
	}
	
	public Load() throws IOException, ClassNotFoundException
	{
		
	}
	

	@SuppressWarnings("unchecked")
	public void prepSerializedEnglishDictionary()
	{
		System.out.println("Loading existing English dictionary.");
		try
		{
			FileInputStream englishDicitonaryInputFile = new FileInputStream("Resources\\PTBDictionary.ser");
			ObjectInputStream in = new ObjectInputStream(englishDicitonaryInputFile);
			this.englishDictionary = (TreeMap<String, ArrayList<String>>) in.readObject();
			in.close();
			englishDicitonaryInputFile.close();
			System.out.println("English dictionary loaded.");
		} catch (IOException i)
		{
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c)
		{
			System.out.println("Existing English dictionary not found");
			c.printStackTrace();
			return;
		}
	}

//	public HashMap<String, String> wordsToAdd() throws UnsupportedEncodingException, FileNotFoundException, IOException
//	{
//		System.out.println("Attempting to add to lists of user-defined words to add and words to regenerate.");
//		//format:
//		//regex pattern:
//		//n obex nn //make a new word (if there isn't yet one, alerting you if it already exists)
//		//c potato nn //regenerate the given word/pos combo
//		//d potato nn //delete this word.
//		//s potato potato //set the english word on the left to the specific character sequence on the right
//
//		//HashMap<String, String> loaded = new HashMap<String, String>();
//		InputStreamReader dictionaryEditsInputFile = new InputStreamReader(new FileInputStream(
//				"Resources\\dictionaryChanges.txt"), "UTF-8");
//		BufferedReader br = new BufferedReader(dictionaryEditsInputFile);
//		String line;
//		Pattern p = Pattern.compile("([ncds]) (.+?) (.+?)$");//1=operator, 2=english word, 3=pos it needs to be/non-english word you want it set as
//
//		while ((line = br.readLine()) != null)
//		{
//			// process the line.
//			line = line.toLowerCase();
//			Matcher m = p.matcher(line);
//			while (m.find())
//			{
//				System.out.println(m.group(1) + ":" + m.group(2) + ":" + m.group(3));
//				if (m.group(1).equals("s"))
//				{
//					String englishWord = m.group(2);
//					String setNewWord = m.group(3);
//					//test if the new word you want to set things as is allowable,UNLESS properties file allows loan words outside of normal pronunciation
//
//					//end test
//					specificWords.put(englishWord, setNewWord);
//
//				} else if (m.group(1).equals("n"))
//				{
//					String englishWord = m.group(2);
//					String setPOS = m.group(3);
//					wordsToGenerate.put(englishWord, setPOS);
//
//				} else if (m.group(1).equals("c"))
//				{
//					String englishWord = m.group(2);
//					String setPOS = m.group(3);
//					//englishWords.remove(englishWord); //remove so we can add it no problem later
//					wordsToRegenerate.put(englishWord, setPOS); // add to the list of things to generate
//				} else
//				{ //it's D for delete
//					String englishWord = m.group(2);
//					//String setNewWord = m.group(3);
//					englishDictionary.remove(englishWord);
//				}
//			}
//		}
//
//		br.close();
//
//		return null;
//	}

	
	
}
