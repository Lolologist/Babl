package com.engma.Babl;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class WordGenerator
{
	Dictionary							dictionary;
	TreeMap<String, Word>				EnglishWordMap		= new TreeMap<String, Word>();
	ArrayList<Word>						words				= new ArrayList<Word>();

	//from the PTBDictionary
	TreeMap<String, ArrayList<String>>	englishDictionary	= new TreeMap<String, ArrayList<String>>();

	//used for adding in words from the dictionaryChanges.txt file
	static HashMap<String, String>		addedWordsFromFile	= new HashMap<String, String>();			//english word, POS
	static HashMap<String, String>		swapWords			= new HashMap<String, String>();			//english word with a foreign word you don't like, english word you want to get the foreign word from

	public void dictionaryChanges() throws IOException
	{
		//HashMap<String, String> loaded = new HashMap<String, String>();
		InputStreamReader dictionaryEditsInputFile = new InputStreamReader(new FileInputStream(
				"Resources\\dictionaryChanges.txt"), "UTF-8");
		BufferedReader br = new BufferedReader(dictionaryEditsInputFile);
		String line;
		Pattern p = Pattern.compile("(.+?) (.+?)$");//1=operator, 2=English word, 3=pos it needs to be/non-english word you want it set as

		while ((line = br.readLine()) != null)
		{
			// process the line.
			line = line.toLowerCase();
			Matcher m = p.matcher(line);
			while (m.find())
			{
				System.out.println(m.group(1) + ":" + m.group(2));
				englishDictionary.put(m.group(1), new ArrayList<String>(Arrays.asList(m.group(2))));

				//TODO: in this case we're setting a word to be exactly a certain character sequence (INCOMPLETE)
				//				if (m.group(2).contains("\""))
				//				{
				//					String setAsThisCharacterSequence = m.group(2).substring(1, m.group(2).length()-1);
				//					if (!dictionary.entries.containsKey(m.group(1)))
				//					{
				//						dictionary.entries.put(m.group(1), new Word(new BaseWordForm(m.group(2)), m.group(1), "whocares"));
				//					} else {
				//						dictionary.entries.get(m.group(1)).
				//					}
				//				}
			}
		}
	}

//	public void dictionarySwaps() throws IOException
//	{
//		//HashMap<String, String> loaded = new HashMap<String, String>();
//		InputStreamReader dictionaryEditsInputFile = new InputStreamReader(new FileInputStream(
//				"Resources\\dictionarySwaps.txt"), "UTF-8");
//		BufferedReader br = new BufferedReader(dictionaryEditsInputFile);
//		String line;
//		Pattern p = Pattern.compile("(.+?) (.+?)$");//1=operator, 2=English word, 3=pos it needs to be/non-english word you want it set as
//
//		while ((line = br.readLine()) != null)
//		{
//			// process the line.
//			line = line.toLowerCase();
//			Matcher m = p.matcher(line);
//			while (m.find())
//			{
//				System.out.println(m.group(1) + ":" + m.group(2));
//				System.out.println(dictionary.entries.get(m.group(1)));
//				System.out.println(dictionary.entries.get(m.group(2)));
//				swapWords.put(m.group(1), m.group(2));//want to change, scapegoat
//				System.out.println(dictionary.entries.get(m.group(1)+" "+dictionary.entries.get(m.group(2))));
//			}
//		}
//
//		br.close();
//		//words = (ArrayList<Word>) dictionary.entries.values();//set the words we have from an existing file (if any so far)
//		for (String w : swapWords.keySet())
//		{
//			Word tempWord = dictionary.entries.get(w);
//			Word tempWord2 = dictionary.entries.get(swapWords.get(w));
//			dictionary.entries.put(w, tempWord2);
//			dictionary.entries.put(swapWords.get(w), tempWord);
//		}
//
//	}

	public WordGenerator(Load load, ArrayList<BaseWordForm> singleSyllableWords,
			ArrayList<BaseWordForm> twoSyllableWords, ArrayList<BaseWordForm> threeSyllableWords)
			throws ClassNotFoundException, IOException
	{
		this.dictionary = load.loadedDictionary;
		this.englishDictionary = load.englishDictionary;

		System.out.println("Loaded existing 'foreign' dictionary. " + words.size() + " words in loaded dictionary.");
		System.out.println("Loaded existing English dictionary of size: " + englishDictionary.size());

		dictionaryChanges();

		words.addAll(formWord(englishDictionary, singleSyllableWords, twoSyllableWords, threeSyllableWords));
		for (Word w : words)
		{
			dictionary.entries.put(w.english, w);
		}
		System.out.println("Words created. Saving and serializing.");

	}

	public WordGenerator(ArrayList<BaseWordForm> singleSyllableWords, ArrayList<BaseWordForm> twoSyllableWords,
			ArrayList<BaseWordForm> threeSyllableWords) throws Exception
	{
		System.out.println("Assuming no loaded foreign dictionary file.");
		Dictionary d = new Dictionary();
		dictionary = d;
		dictionary.entries = new HashMap<String, Word>();

		Load load = new Load();
		load.prepSerializedEnglishDictionary();
		englishDictionary = load.englishDictionary;
		System.out.println("Loaded existing English dictionary.");
		dictionaryChanges();
		System.out.println(englishDictionary.size() + " words to make.");

		if (englishDictionary.size() > 0)
		{
			words.addAll(formWord(englishDictionary, singleSyllableWords, twoSyllableWords, threeSyllableWords));
			for (Word w : words)
			{
				if (!dictionary.entries.containsKey(w.english))
				{
					dictionary.entries.put(w.english, w);
				} else
				{
					dictionary.entries.put(w.english, w);
				}
			}
		}
	}

	//	private ArrayList<Word> formWord(String english, String pos, ArrayList<BaseWordForm> singleSyllableWords,
	//			ArrayList<BaseWordForm> twoSyllableWords, ArrayList<BaseWordForm> threeSyllableWords)
	//	{
	//		TreeMap<String, ArrayList<String>> englishWord = new TreeMap<String, ArrayList<String>>();
	//		englishWord.put(english, new ArrayList<String>(Arrays.asList(pos)));
	//
	//		return formWord(englishWord, singleSyllableWords, twoSyllableWords, threeSyllableWords);
	//	}

	public WordGenerator(String newWord, ArrayList<BaseWordForm> extraSingleSyllableWords,
			ArrayList<BaseWordForm> extraTwoSyllableWords, ArrayList<BaseWordForm> extraThreeSyllableWords)
	{
		TreeMap<String, ArrayList<String>> englishWord = new TreeMap<String, ArrayList<String>>();
		englishWord.put(newWord, new ArrayList<String>(Arrays.asList("nn")));
		words = formWord(englishWord, extraSingleSyllableWords, extraTwoSyllableWords, extraThreeSyllableWords);

	}

	private static ArrayList<Word> formWord(TreeMap<String, ArrayList<String>> englishWords,
			ArrayList<BaseWordForm> singleSyllableWords, ArrayList<BaseWordForm> twoSyllableWords,
			ArrayList<BaseWordForm> threeSyllableWords)
	{
		Word word = new Word();
		ArrayList<Word> words = new ArrayList<Word>();
		ArrayList<BaseWordForm> baseWords = new ArrayList<BaseWordForm>();
		Random rand = new Random();

		for (String eachWord : englishWords.keySet())
		{
			//System.out.println("!" + englishWords.get(eachWord).toString());
			//if ((rand.nextInt(2) == 1) && (englishWords.get(eachWord).size() > 1))
			if (englishWords.get(eachWord).size() > 1)
			{

				for (int i = 0; i < englishWords.get(eachWord).size(); i++)
				{
					TreeMap<String, ArrayList<String>> resend = new TreeMap<String, ArrayList<String>>();
					resend.put(eachWord, new ArrayList<String>(Arrays.asList(englishWords.get(eachWord).get(i))));

					words.addAll(formWord(resend, singleSyllableWords, twoSyllableWords, threeSyllableWords));
				}
			} else
			{
				//System.out.println(eachWord+" "+englishWords.get(eachWord));
				//			//TODO: simple version here. Make more complex!
				//
				boolean invalidWord = true;

				while (invalidWord == true)
				{
					if (eachWord.length() <= 4)
					{
						if (rand.nextInt(10) > 8)
						{
							word = new Word(twoSyllableWords.get(rand.nextInt(twoSyllableWords.size())), eachWord,
									englishWords.get(eachWord).get(0));

						} else
						{
							word = new Word(singleSyllableWords.get(rand.nextInt(singleSyllableWords.size())),
									eachWord, englishWords.get(eachWord).get(0));

						}
					} else if ((eachWord.length() > 4) && (eachWord.length() <= 10))
					{
						if (rand.nextInt(10) >= 8)
						{

							word = new Word(threeSyllableWords.get(rand.nextInt(threeSyllableWords.size())), eachWord,
									englishWords.get(eachWord).get(0));

						} else
						{
							word = new Word(twoSyllableWords.get(rand.nextInt(twoSyllableWords.size())), eachWord,
									englishWords.get(eachWord).get(0));

						}
					} else
					{
						if (rand.nextInt(10) >= 6)
						{
							word = new Word(threeSyllableWords.get(rand.nextInt(threeSyllableWords.size())), eachWord,
									englishWords.get(eachWord).get(0));

						} else
						{
							word = new Word(twoSyllableWords.get(rand.nextInt(twoSyllableWords.size())), eachWord,
									englishWords.get(eachWord).get(0));

						}
					}
					if (!baseWords.contains(word.word))
					{
						baseWords.add(word.word);
						invalidWord = false;
					}
				}
				//System.out.println(word.english+":"+word.word);
				words.add(word);

			}
		}
		return words;
	}
}