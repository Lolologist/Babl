package com.engma.Babl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class VowelSelector
{
	
	
	ArrayList<Vowel> chosenVowels = new ArrayList<Vowel>();
	
	Random rand = new Random();
	
	public VowelSelector(Properties prop, List<Vowel> vowels, ArrayList<Vowel> predefined, int numberOfVowels)
	{	
		
		if (predefined != null)
		{
			chosenVowels.addAll(predefined);

		}
		int vowelsSoFar = chosenVowels.size();
		
		// TODO: This will be an early, dumber version, just picking depending
		// on their percentages. Make a new version that grabs a manner and/or
		// place based on more popular manners/places and fills in appropriately
		// given the possible consonants.
		// Especially secregate things like clicks and implosives out.

		// System.out.println("1"+vowelsSoFar);
		// System.out.println("2"+numberOfVowels);
		
		
		int totalpercentage = 0;
		for (Vowel each : vowels)
		{
			// System.out.println(each.utf8Char+":"+prop.getProperty(each.utf8Char));
			totalpercentage += Integer.parseInt(prop.getProperty(each.utf8Char).replaceAll("\\s", ""));
		}

		while (vowelsSoFar < numberOfVowels)
		{

			int chosen = rand.nextInt(totalpercentage) + 1;// +1 because
			// includes 0 and
			// not the end
			// number
			int choosey = 0;
			for (Vowel each : vowels)
			{
				choosey += Integer.parseInt(prop.getProperty(each.utf8Char).replaceAll("\\s", ""));
				if (choosey >= chosen)
				{
					if (!chosenVowels.contains(each))
					{
						chosenVowels.add(each);
						vowelsSoFar += 1;
					}
					break;
				}
			}
		}
//		for (Vowel each : chosenVowels)
//		{
//			System.out.print(each.utf8Char + " ");
//		}

	}

	@Override public String toString()
	{
		String returnable = "";
		for (Vowel each : chosenVowels)
		{
			returnable += each+" ";
		}
		return returnable;
	}
	
}
