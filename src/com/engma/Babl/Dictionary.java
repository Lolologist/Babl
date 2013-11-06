package com.engma.Babl;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Dictionary implements Serializable
{

	/**
	 * 
	 */
	private static final long				serialVersionUID			= -580628519481018517L;

	public HashMap<String, Word>			entries						= new HashMap<String, Word>();	//redundant as word.english is the same as String

	public ArrayList<BaseWordForm>			singleSyllableWords			= new ArrayList<BaseWordForm>();

	public ArrayList<BaseWordForm>			extraSingleSyllableWords	= new ArrayList<BaseWordForm>();
	public ArrayList<BaseWordForm>			extraTwoSyllableWords		= new ArrayList<BaseWordForm>();
	public ArrayList<BaseWordForm>			extraThreeSyllableWords		= new ArrayList<BaseWordForm>();

	public HashMap<String, BaseWordForm>	glosses						= new HashMap<String, BaseWordForm>();

	public Dictionary()
	{
		this.entries = null;
		//this.extraWords=null;
		this.singleSyllableWords = null;

	}

	public Dictionary(ArrayList<Word> words, ArrayList<BaseWordForm> onesyllables, ArrayList<BaseWordForm> extra1, ArrayList<BaseWordForm> extra2, ArrayList<BaseWordForm> extra3)
	{
		for (Word word : words)
		{
			entries.put(word.english, word);
		}
		//extraWords = extras;
		singleSyllableWords = onesyllables;
		extraSingleSyllableWords = extra1;
		extraTwoSyllableWords = extra2;
		extraThreeSyllableWords = extra3;

	}

	public Word lookup(String word)
	{
		word = word.toLowerCase();
		return entries.get(word);
	}

	public String verboseLookup(String word)
	{
		word = word.toLowerCase();
		return entries.get(word).verboseToString();
	}

	public void dictionaryAddViaFile()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("addwords.txt"));
			String line = br.readLine();
			while (line != null)
			{
				line = line.trim().toLowerCase();

				posAndWordgrabber(line);
				//				sb.append(line);
				//				sb.append('\n');
				line = br.readLine();
				br.close();
			}

		} catch (Exception e)
		{
			System.out.println("No file for adding words outside the Core Wordset.");
			//System.out.println(e);
		}
	}

	public void posAndWordgrabber(String line)
	{
		Pattern p = Pattern.compile("[a-z] (.+?) (.+?)");
		Matcher m = p.matcher(line);
		while (m.find())
		{
			String word = m.group(1);
			//String pos = m.group(2);

			entries.put(word, wordParser(word));

		}
	}

	private static Word wordParser(String word)
	{
		char[] characters = word.toCharArray();
		for (int i = 0; i < characters.length; i++)
		{
			try
			{

			} catch (Exception e)
			{

			}
		}

		return null;
	}

	/**
	 * Always treat de-serialization as a full-blown constructor, by validating the final state of the de-serialized
	 * object.
	 */
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
	{
		//always perform the default de-serialization first
		aInputStream.defaultReadObject();
	}

	/**
	 * This is the default implementation of writeObject. Customise if necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException
	{
		//perform the default serialization for all non-transient, non-static fields
		aOutputStream.defaultWriteObject();
	}
}
