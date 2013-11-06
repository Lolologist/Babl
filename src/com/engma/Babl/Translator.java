package com.engma.Babl;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator
{
	static Dictionary				dictionary;
	static HashMap<String, Word>	dict;
	static boolean					changedDictionary	= false;

	public static void main(String[] args) throws Exception
	{

		System.out.println("Translating in.txt");
		Load load = new Load();
		load.load();

		dictionary = load.loadedDictionary;
		dict = dictionary.entries;

		//File file = new File(args[0]);
		File file = new File("resources/in.txt");
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");
		String sentence = "";
		try
		{
			while (scanner.hasNextLine())
			{
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			sentence = fileContents.toString();
		} finally
		{
			scanner.close();
		}
		System.out.println("S:" + sentence);

		//		System.out.println(dict.get("-3PL-"));
		//		System.out.println(dict.get("-PRES-"));
		//		System.out.println(dict.get("be"));
		//		System.out.println("Lars"+dict.get("-GEN-")+" "+dict.get("potato")+dict.get("-PL-")+ " "+dict.get("-3PL-")+dict.get("-PRES-")+dict.get("be")+" "+dict.get("huge"));
		//String sentence = "human-PL gender 1 and human-PL gender 2, we 1PL-PRES-have a language.";
		translate(sentence);
	}

	private static void translate(String sentence) throws Exception
	{
		//handleNullWords("wordswap");
		ArrayList<String> sent = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(sentence, ".,';!?:\" \n\r", true);
		while (tokenizer.hasMoreTokens())
		{
			sent.add(tokenizer.nextToken());
		}

		String outsentence = "";
		//print it out
		for (String each : sent)
		{
			if (each.contains("-"))
			{
				String[] each2 = each.split("-");
				for (String each3 : each2)
				{
					//System.out.println(each3+": "+dict.get(each3));
					//handle unknown words
					if (dict.get(each3) == null)
					{
						changedDictionary = true;
						//handleNullWords(each3);
						System.out.println(dict.get(each3));
					}
					outsentence += dict.get(each3);
				}
			} else
			{
				ArrayList<String> skips = new ArrayList<String>(Arrays.asList(" ", ",", "!", "?", ";", ";", "'", "\"",
						".", "\n", "\r","@","#","$","%","^","&","*","(",")","[","]","{","}","`","``","/","<",">","~"));
				if (!skips.contains(each))
				{
					if (dict.get(each) == null)
					{
						changedDictionary = true;
						handleNullWords(each,skips);
					}
					outsentence += dict.get(each);
					//System.out.println(each+": "+dict.get(each));
				} else
				{
					if (dict.get(each) == null)
					{
						changedDictionary = true;
						handleNullWords(each,skips);
					}
					outsentence += each;
				}
			}

		}
		//now that we have fully-formed words, alter them as necessary
		System.out.println("Going to swap any words in dictionarySwaps.txt");
		dictionarySwaps();
		//System.out.println("archaic, finish");
		//System.out.println(dictionary.entries.get("archaic")+":2 "+dictionary.entries.get("finish"));
		//System.out.println(sentence);
		//System.out.println(outsentence);
		String ASCIIoutsentence = outsentence;
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ɱ", "M");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ǁ", "K");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ɗ", "D");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ŋ", "N");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ɓ", "B");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ʔ", "H");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ɭ", "L");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ə", "E");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ʃ", "S");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ʒ", "Z");
		ASCIIoutsentence = ASCIIoutsentence.replaceAll("ɠ", "G");
		//System.out.println(ASCIIoutsentence);
		outsentence += "\n" + ASCIIoutsentence;
		ArrayList<Word> w1 = new ArrayList<Word>();
		for (String s : dictionary.entries.keySet())
		{
			w1.add(dictionary.entries.get(s));
		}
		Dictionary d = new Dictionary(w1, dictionary.singleSyllableWords, dictionary.extraSingleSyllableWords, dictionary.extraTwoSyllableWords, dictionary.extraThreeSyllableWords);
		new Save(d);
		try
		{
			PrintWriter out = new PrintWriter(new File("resources/out.txt"), "UTF-8");
			out.print(outsentence);
			System.out.println("file written to resources/out.txt");
			out.close();
			System.out
					.println("IF YOU SEE NEW WORDS ABOVE: New words means new dictionary. Please email lolologist@gmail.com your dictionary.ser and dictionary.txt file.");
			
		} catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}

		
	}

	private static void handleNullWords(String newWord, ArrayList<String> skips) throws Exception
	{
		if (skips.contains(newWord))
		{
			return;
		}
		WordGenerator wordGen = new WordGenerator(newWord, dictionary.extraSingleSyllableWords,
				dictionary.extraTwoSyllableWords, dictionary.extraThreeSyllableWords);
		ArrayList<Word> newW = wordGen.words;
		for (Word w : newW)
		{
			System.out.println("NEW WORD: " + w.english + "=" + w.toString());
			dictionary.entries.put(w.english, w);
			//System.out.println("removed the word successfully1");
			dictionary.extraSingleSyllableWords.remove(w.word);

			//System.out.println("removed the word successfully2");
			dictionary.extraTwoSyllableWords.remove(w.word);

			//System.out.println("removed the word successfully3");
			dictionary.extraThreeSyllableWords.remove(w.word);

		}
	}

	public static void dictionarySwaps() throws IOException
	{
		//HashMap<String, String> loaded = new HashMap<String, String>();
		InputStreamReader dictionaryEditsInputFile = new InputStreamReader(new FileInputStream(
				"Resources\\dictionarySwaps.txt"), "UTF-8");
		BufferedReader br = new BufferedReader(dictionaryEditsInputFile);
		String line;
		Pattern p = Pattern.compile("(.+?) (.+?)$");//1=operator, 2=English word, 3=pos it needs to be/non-english word you want it set as

		HashMap<String, String> swapWords = new HashMap<String,String>();
		while ((line = br.readLine()) != null)
		{
			// process the line.
			line = line.toLowerCase();
			Matcher m = p.matcher(line);
			while (m.find())
			{
				System.out.println(m.group(1) + ":" + dictionary.entries.get(m.group(1)));
				System.out.println(m.group(2)+": "+dictionary.entries.get(m.group(2)));
				swapWords.put(m.group(1), m.group(2));//want to change, scapegoat
				
			}
		}

		br.close();
		//words = (ArrayList<Word>) dictionary.entries.values();//set the words we have from an existing file (if any so far)
		System.out.println("******************************");
		
		
		for (String w : swapWords.keySet())
		{
			Word originalWord = new Word(dictionary.entries.get(w).getWord(),swapWords.get(w),dictionary.entries.get(swapWords.get(w)).getPos());
			Word wordToSwapWith = new Word(dictionary.entries.get(swapWords.get(w)).getWord(),w,dictionary.entries.get(w).getPos());
			//System.out.println("For "+w+", which we want to swap with "+swapWords.get(w));
			//Word tempWord2 = dictionary.entries.get(swapWords.get(w));
			
			dictionary.entries.put(w, wordToSwapWith);
			dictionary.entries.put(swapWords.get(w), originalWord);
			
			System.out.println(w+":"+dictionary.entries.get(w));
			System.out.println(swapWords.get(w)+":"+dictionary.entries.get(swapWords.get(w)));
		}
		System.out.println("******************************");
	}
	

}
