package com.engma.Babl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class VowelSetup
{	
	static int				numberOfVowels;
	static ArrayList<Vowel>	predefinedVowels	= new ArrayList<Vowel>();
	ArrayList<Vowel>		chosenVowels		= new ArrayList<Vowel>();
	
	
	public VowelSetup(Properties settings, ArrayList<Vowel> vowels) throws Exception
	{
		Properties vowelProperties = new Properties();
		vowelProperties.load(new FileInputStream("resources/Vowels.properties"));
		
		if (settings.getProperty("specifiedVowelAmount").equals("null"))
		{
			PickNumberOfVowels numberOfVowelsInit = new PickNumberOfVowels(settings);
			numberOfVowels = numberOfVowelsInit.numberOfVowels;
		} else
		{
			numberOfVowels = Integer.parseInt(settings.getProperty("specifiedVowelAmount"));
		}
		//System.out.println("V:" + numberOfVowels);
		if (!settings.getProperty("specifiedVowels").equals("null"))
		{
			List<String> predefinedString = Arrays.asList(settings.getProperty("specifiedVowels").split(","));

			for (String each : predefinedString)
			{
				for (Vowel each2 : vowels)
				{
					if (each.equals(each2.utf8Char))
					{
						predefinedVowels.add(each2);
					}
				}
			}
			// check if specified is longer than the number, if so throw error
			// and do it from scratch.
			// if same as number, set it. If shorter, add until full? Pass along
			// a set of Consonants to the Consonant Picker, usually empty, but
			// can be partially filled
			
		}
		VowelSelector chosenVowelsInit = new VowelSelector(vowelProperties, vowels, predefinedVowels, numberOfVowels);
		chosenVowels = chosenVowelsInit.chosenVowels;
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
