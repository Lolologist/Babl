package com.engma.Babl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConsonantSetup
{
	static int					numberOfConsonants;
	static ArrayList<Consonant>	predefinedConsonants	= new ArrayList<Consonant>();
	ArrayList<Consonant>	chosenConsonants		= null;

	
	public ConsonantSetup(Properties settings, ArrayList<Consonant> consonants) throws Exception
	{
	
		Properties consonantProperties = new Properties();
		consonantProperties.load(new FileInputStream("resources/Consonants.properties"));
		
		if (settings.getProperty("specifiedConsonantAmount").equals("null"))
		{
			PickNumberOfConsonants numberOfConsonantsInit = new PickNumberOfConsonants(settings);
			numberOfConsonants = numberOfConsonantsInit.numberOfConsonants;
		} else
		{

			numberOfConsonants = Integer.parseInt(settings.getProperty("specifiedConsonantAmount"));
		}
		//System.out.println("C:" + numberOfConsonants);
		if (!settings.getProperty("specifiedConsonants").equals("null"))
		{

			List<String> predefinedString = Arrays.asList(settings.getProperty("specifiedConsonants").split(","));
			if (predefinedString.size() > numberOfConsonants)
			{
				throw new Exception("You specified more consonants than the amount you wanted to have!");
			}
			
			for (String each : predefinedString)
			{
				for (Consonant each2 : consonants)
				{
					if (each.equals(each2.utf8Char))
					{
						predefinedConsonants.add(each2);
					}
				}
			}
		}
		ConsonantSelector chosenConsonantsInit = new ConsonantSelector(consonantProperties, consonants, predefinedConsonants, numberOfConsonants);
		chosenConsonants = chosenConsonantsInit.chosenConsonants;

	}

	@Override public String toString()
	{
		String returnable = "";
		for (Consonant each : chosenConsonants)
		{
			returnable += each+" ";
		}
		return returnable;
	}
}
