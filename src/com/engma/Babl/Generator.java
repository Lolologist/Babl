package com.engma.Babl;

import java.io.*;
import java.util.*;

public class Generator
{

	public static void main(String[] args) throws Exception
	{

		Random random = new Random();

		new Consonants();
		new Vowels();

		// The properties file includes a (very rough) estimate for what % of
		// the world's languages include a given letter. The better to pick them
		// with, my dear.

		Properties settings = new Properties();
		settings.load(new FileInputStream("resources/settings.properties"));

		//Consonant.ConsonantSetup(settings, consonants);
		ConsonantSetup chosenConsonantsInit = new ConsonantSetup(settings, Consonants.consonants);
		List<Consonant> chosenConsonants = chosenConsonantsInit.chosenConsonants;
		System.out.println("Chosen consonants:");
		System.out.println(chosenConsonantsInit);

		VowelSetup chosenVowelsInit = new VowelSetup(settings, Vowels.vowels);
		List<Vowel> chosenVowels = chosenVowelsInit.chosenVowels;
		System.out.println("Chosen vowels:");
		System.out.println(chosenVowelsInit);

//		System.out.println();
		SonorityScale sonorityScale = new SonorityScale();
//		System.out.println(sonorityScale);

		int randNum = random.nextInt(10);
		if (settings.getProperty("syllableOnsetOptional").equals("null"))
		{

			if (randNum < 6)
			{
				settings.setProperty("syllableOnsetOptional", "optional");
			} else if (randNum < 8)
			{
				settings.setProperty("syllableOnsetOptional", "1");// only one
			} else if (randNum < 9)
			{
				settings.setProperty("syllableOnsetOptional", "2");// 2 or 3
				// really
			} else if (randNum <= 10)
			{
				settings.setProperty("syllableOnsetOptional", "3");// 3+
				// consonants
				// really
			}

			randNum = random.nextInt(10);
			if (randNum < 6)
			{
				settings.setProperty("syllableOnsetOptional", "optional");
			} else
			{
				settings.setProperty("syllableOnsetOptional", "required");
			}
		}

		randNum = random.nextInt(11);
		if (settings.getProperty("syllableOnsetSize").equals("null"))
		{
			if ((randNum < 6) && (settings.getProperty("syllableOnsetOptional").equals("optional")))
			{
				settings.setProperty("syllableOnsetSize", "0");// none actually
			} else if (randNum < 8)
			{
				settings.setProperty("syllableOnsetSize", "1");// only one
			} else if (randNum < 9)
			{
				settings.setProperty("syllableOnsetSize", "2");// 2 or 3 really
			} else if (randNum <= 10)
			{
				settings.setProperty("syllableOnsetSize", "3");// 3+ consonants
				// really
			}
		}
		// nucleus size

		if (settings.getProperty("syllableNucleusSize").equals("null"))
		{
			randNum = random.nextInt(10);
			if (randNum < 6)
			{
				settings.setProperty("syllableNucleusSize", "1");// One vowel
				// per
				// nucleus
			} else if (randNum <= 9)
			{
				settings.setProperty("syllableNucleusSize", "2");// two vowels
				// (dipthong)
				// allowed for a
				// nucleus
			} else if (randNum == 10)
			{// TODO: TEST MAKE SURE nextInt can go to
				// 10
				settings.setProperty("syllableNucleusSize", "3");// wacky nuclei
				// allowed
			}
		}
		// coda

		if (settings.getProperty("allowClosedSyllables").equals("null"))
		{
			randNum = random.nextInt(10);
			if (randNum < 6)
			{
				settings.setProperty("allowClosedSyllables", "true");
			} else
			{
				settings.setProperty("allowClosedSyllabes", "false");
			}
		}

		if (settings.getProperty("syllableCodaSize").equals("null"))
		{
			randNum = random.nextInt(10);
			if ((randNum < 6) && (settings.getProperty("allowClosedSyllables").equals("allowed")))
			{
				settings.setProperty("syllableCodaSize", "0");// none actually
				// needed //TODO:
				// TEST THIS WORKS
			} else if (randNum < 8)
			{
				settings.setProperty("syllableCodaSize", "1");// only one
			} else if (randNum < 9)
			{
				settings.setProperty("syllableCodaSize", "2");// 2 or 3 really
			} else if (randNum <= 10)
			{
				settings.setProperty("syllableCodaSize", "3");// 3+ consonants
				// really
			}
		}

		if (settings.getProperty("sonorityPlateau").equals("null"))
		{
			randNum = random.nextInt(10);
			if (randNum < 6)
			{
				settings.setProperty("sonorityPlateau", "true");
			} else
			{
				settings.setProperty("sonorityPlateau", "false");
			}
		}

//		System.out.println();
//		System.out.println("syllableOnsetOptional: " + settings.getProperty("syllableOnsetOptional"));
//		System.out.println("syllableOnsetSize: " + settings.getProperty("syllableOnsetSize"));
//		System.out.println("syllableNucleusSize: " + settings.getProperty("syllableNucleusSize"));
//		System.out.println("allowClosedSyllables: " + settings.getProperty("allowClosedSyllables"));
//		System.out.println("syllableCodaSize: " + settings.getProperty("syllableCodaSize"));
//		System.out.println("sonorityPlateau: " + settings.getProperty("sonorityPlateau"));
//		System.out.println();

		ArrayList<BaseWordForm> singleSyllableWords = new ArrayList<BaseWordForm>();
		ArrayList<BaseWordForm> twoSyllableWords = new ArrayList<BaseWordForm>();
		ArrayList<BaseWordForm> threeSyllableWords = new ArrayList<BaseWordForm>();

		int x1 = 0;
		while (x1 < 3000)
		{

			try
			{
				Syllable syl = new Syllable(2, 2, 2, settings, chosenConsonants, chosenVowels, sonorityScale);
				BaseWordForm word = new BaseWordForm(syl);
				// Syllable.SyllableGenerator(Integer.parseInt(settings.getProperty("syllableOnsetSize")),
				// Integer.parseInt(settings.getProperty("syllableNucleusSize")),
				// Integer.parseInt(settings.getProperty("syllableCodaSize")),
				// settings, chosenConsonants, chosenVowels);
				if (!singleSyllableWords.contains(word))
				{
					singleSyllableWords.add(word);
					x1 += 1;
				}
			} catch (Exception e2)
			{
				System.out.println(e2);
			}
		}
		System.out.println("Made single syllable words.");
		
		int x2 = 0;
		while (x2 < 8000)
		{

			try
			{
				Syllable syl1 = new Syllable(2, 2, 2, settings, chosenConsonants, chosenVowels, sonorityScale);
				Syllable syl2 = new Syllable(2, 2, 2, settings, chosenConsonants, chosenVowels, sonorityScale);
				ArrayList<Syllable> wordSyllables = new ArrayList<Syllable>();
				wordSyllables.add(syl1);
				wordSyllables.add(syl2);
				BaseWordForm word = new BaseWordForm(wordSyllables);
				// Syllable.SyllableGenerator(Integer.parseInt(settings.getProperty("syllableOnsetSize")),
				// Integer.parseInt(settings.getProperty("syllableNucleusSize")),
				// Integer.parseInt(settings.getProperty("syllableCodaSize")),
				// settings, chosenConsonants, chosenVowels);
				if (!twoSyllableWords.contains(word))
				{
					twoSyllableWords.add(word);
					x2 += 1;
				}
			} catch (Exception e2)
			{
				System.out.println(e2);
			}
		}
		System.out.println("Made two-syllable words.");
		int x3 = 0;
		while (x3 < 10000)
		{
//			if (x3%10000 == 0)
//			{
//				System.out.println(x3);
//			}
			try
			{
				Syllable syl1 = new Syllable(2, 2, 2, settings, chosenConsonants, chosenVowels, sonorityScale);
				Syllable syl2 = new Syllable(2, 2, 2, settings, chosenConsonants, chosenVowels, sonorityScale);
				Syllable syl3 = new Syllable(2, 2, 2, settings, chosenConsonants, chosenVowels, sonorityScale);
				ArrayList<Syllable> wordSyllables = new ArrayList<Syllable>();
				wordSyllables.add(syl1);
				wordSyllables.add(syl2);
				wordSyllables.add(syl3);
				BaseWordForm word = new BaseWordForm(wordSyllables);
				// Syllable.SyllableGenerator(Integer.parseInt(settings.getProperty("syllableOnsetSize")),
				// Integer.parseInt(settings.getProperty("syllableNucleusSize")),
				// Integer.parseInt(settings.getProperty("syllableCodaSize")),
				// settings, chosenConsonants, chosenVowels);
				if (!threeSyllableWords.contains(word))
				{
					threeSyllableWords.add(word);
					x3 += 1;
				}
			} catch (Exception e2)
			{
				System.out.println(e2);
			}
		}
		System.out.println("Made three-syllable words.");

		ArrayList<Word> words = new WordGenerator(singleSyllableWords,twoSyllableWords,threeSyllableWords).words;

		for (Word word : words)
		{
//			if (word.english.equals("obex"))
//			{
//				System.out.println("OBEX");
//			}
			if (singleSyllableWords.contains(word.word))
			{
				//System.out.println("IT HAD IT");
				singleSyllableWords.remove(word.word);
			}
			if (twoSyllableWords.contains(word.word))
			{
				//System.out.println("IT HAD IT2");
				twoSyllableWords.remove(word.word);
			}
			if (threeSyllableWords.contains(word.word))
			{
				//System.out.println("IT HAD IT3");
				threeSyllableWords.remove(word.word);
			}
		}
		
		System.out.println("1:"+singleSyllableWords.size());
		System.out.println("2:"+twoSyllableWords.size());
		System.out.println("3:"+threeSyllableWords.size());
		
		ArrayList<BaseWordForm> extraSingleSyllableWords = new ArrayList<BaseWordForm>();
		ArrayList<BaseWordForm> extraTwoSyllableWords = new ArrayList<BaseWordForm>();
		ArrayList<BaseWordForm> extraThreeSyllableWords = new ArrayList<BaseWordForm>();
		
		extraSingleSyllableWords.addAll(singleSyllableWords.subList(0, 300));
		extraTwoSyllableWords.addAll(twoSyllableWords.subList(0, 400));
		extraThreeSyllableWords.addAll(threeSyllableWords.subList(0, 300));
		
		Dictionary dictionary = new Dictionary(words,singleSyllableWords,extraSingleSyllableWords, extraTwoSyllableWords, extraThreeSyllableWords);
		//Dictionary d2 = new Dictionary();
		//System.out.println(dictionary.entries);
		System.out.println("Saving dictionary, please wait...");
		new Save(dictionary);
		
//		for (Word word : words)
//		{
//			//System.out.println(word.english);
////			if (word.english.equals("potato"))
////			{
////				System.out.println(word.verboseToString());	
////			}
//				
//		}
		System.out.println("We now have a dictionary of words!");
		
		//System.out.println(dictionary.lookup("potato"));
		//System.out.println(dictionary.verboseLookup("potato"));
		
		System.out.println("Time for grammar!");
		//StartFromGrammar.startFromGrammar(dictionary);
		//Grammar grammar = new Grammar(settings);
		


		// TODO: STRESS

		// System.out.println(words2.get("potato"));
		// TODO: Phonological Rules/"Evolution" (here? Stretch goal? Not
		// necessary to begin with for sure)
		// TODO: particles, articles, prepositions, conjunctions, etc. formed
		// TODO: form 1000 most common words, use WordNet to separate or combine
		// words (if semantically similar enough). Print out to file, print a
		// few to terminal for examples
		// TODO: determine affixation and verb inflection, other inflections on
		// everything. Determine irregular verbs if any, especially focused on
		// most common verbs. Make sure API has setting for disabling or
		// controlling irregular verbs.
		// TODO: Phonological rules to help with agreement
		// TODO: Print out a grammar for inflecting verbs to file, a few simple
		// verbs to show.
		// TODO: Spit out the word for "People", as that'll be the name for the
		// language (subject to "evolution")
		// TODO: Determine word order, sentence structure
		// TODO: Develop English-X translator, X-English translator (may need
		// dependency parse, consituency parse, both?)
		// TODO: Public APIs for all of this
	}

	
	

	// TODO: (Stretch Goal?) Given our phonemes, determine (via external file,
	// maybe call it settings.ini or something?) % or boolean for nasalized/long
	// vowels, ejectives, etc.
	// Phonological extremes: Ubyx and Arrernte have 2 phonemic vowels, but Ngwe
	// has 14, 12 of which are long or short, making 26 oral vowels, plus 6
	// nasalized vowels, long and short, meaning 38 vowels.
	// Rotokas has only 6 consonants whereas Ubyx has 81.
	// The most common vowel system consists of the five vowels /i/, /e/, /a/,
	// /o/, /u/. The most common consonants are /p/, /t/, /k/, /m/, /n/. Very
	// few languages lack any of these: Arabic lacks /p/, standard Hawaiian
	// lacks /t/, Mohawk and Tlingit lack /p/ and /m/, Hupa lacks both /p/ and a
	// simple /k/, colloquial Samoan lacks /t/ and /n/, while Rotokas and
	// Quileute lack /m/ and /n/.
	// 22-26 consonants are average, and English's 13-21 vowels (including
	// dipthongs) is somewhat high.
	// Tones are 0-9, won't be messing with them for a while
	// phonemic unvoiced sonorants occur in about 5% of world's languages
	// Obstruents are prototypically voiceless, though voiced obstruents are
	// common. This contrasts with sonorants, which are much more rarely
	// voiceless.

	// TODO: Set the number of vowels. Raw. 2-14, make sure they spread out
	// properly. Nasalized, long/short, etc. come later (when)?

}
