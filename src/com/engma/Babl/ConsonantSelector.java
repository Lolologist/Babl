package com.engma.Babl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.engma.Babl.Sound.*;

public class ConsonantSelector
{
	ArrayList<Consonant>				chosenConsonants					= new ArrayList<Consonant>();

	HashMap<Consonant.Place, Integer>	placesFilledPerPlaceOfArticulation	= new HashMap<Consonant.Place, Integer>();
	HashMap<Consonant.Manner, Integer>	mannersFilledPerPlaceOfArticulation	= new HashMap<Consonant.Manner, Integer>();

	
	Random rand = new Random();
	
	int									consonantsSoFar						= 0;

	/*
	 * 
	 */
	@SuppressWarnings("serial") public ConsonantSelector(Properties prop, List<Consonant> consonants, ArrayList<Consonant> predefined, int numberOfConsonants)
	{
		this.placesFilledPerPlaceOfArticulation.putAll(new HashMap<Consonant.Place, Integer>()
		{
			{
				put(Place.Bilabial, 0);
				put(Place.Labiodental, 0);
				put(Place.Dental, 0);
				put(Place.Alveolar, 0);
				put(Place.PalatoAlveolar, 0);
				put(Place.DentalToPalatoAlveolar, 0);
				put(Place.DentalOrAlveolar, 0);
				put(Place.Retroflex, 0);
				put(Place.AlveoloPalatal, 0);
				put(Place.Palatal, 0);
				put(Place.AlveoloPalatalPalatal, 0);
				put(Place.Velar, 0);
				put(Place.Uvular, 0);
				put(Place.Pharyngeal, 0);
				put(Place.Epiglottal, 0);
				put(Place.Glottal, 0);
				put(Place.Lateral, 0);
			}
		});// starts off at all zeroes for each place filled. As we pick things
			// the numbers go up. Don't want each getting too close to full if
			// avoidable.
		this.mannersFilledPerPlaceOfArticulation.putAll(new HashMap<Consonant.Manner, Integer>()
		{
			{
				put(Manner.Plosive, 0);
				put(Manner.Nasal, 0);
				put(Manner.Trill, 0);
				put(Manner.Tap, 0);
				put(Manner.Fricative, 0);
				put(Manner.LateralFricative, 0);
				put(Manner.Approximant, 0);
				put(Manner.LateralApproximant, 0);
				put(Manner.Click, 0);
				put(Manner.Implosive, 0);
				put(Manner.Ejective, 0);
				put(Manner.LateralFlap, 0);
				put(Manner.FricativeApproximant, 0);
			}
		});// starts off at all zeroes for each manner filled. As we pick things the numbers go up. Don't want each getting too close to full if avoidable.

		if (predefined != null)
		{
			for (Consonant each : predefined)
			{
				chosenConsonants.add(each);

				consonantsSoFar += 1;
				placesFilledPerPlaceOfArticulation.put(each.place, placesFilledPerPlaceOfArticulation.get(each.place) + 1);// TODO:test
				// to make sure predefined list of consonants, partial or full, updates correctly
				mannersFilledPerPlaceOfArticulation.put(each.manner, mannersFilledPerPlaceOfArticulation.get(each.manner) + 1);// TODO: test to make

				// sure predefined list
				// of consonants,
				// partial or full,
				// updates correctly
			}
		}

		// TODO: add up how many spots are left possible to fill from
		// maximum<PLACE>-minimum<PLACE>, as specified in settings.properties.
		// From there, random-number-generate with that number as the high. Do
		// another gaussian distribution as elsewhere in here, with more common
		// places of speech (like alveolars) in the middle.

		// TODO: This will be an early, dumber version, just picking depending
		// on their percentages. Make a new version that grabs a manner and/or
		// place based on more popular manners/places and fills in appropriately
		// given the possible consonants.
		// Especially segregate things like clicks and implosives out.

		int totalpercentage = 0;
		for (Consonant each : consonants)
		{
			// System.out.println(each.utf8Char+":"+prop.getProperty(each.utf8Char));
			totalpercentage += Integer.parseInt(prop.getProperty(each.utf8Char).replaceAll("\\s", ""));
		}

		while (consonantsSoFar < numberOfConsonants)
		{
			
			int chosen = rand.nextInt(totalpercentage) + 1;// +1 because
			// includes 0 and
			// not the end
			// number
			int choosey = 0;
			for (Consonant each : consonants)
			{
				choosey += Integer.parseInt(prop.getProperty(each.utf8Char).replaceAll("\\s", ""));
				if (choosey >= chosen)
				{
					if (!chosenConsonants.contains(each))
					{
						chosenConsonants.add(each);
						consonantsSoFar += 1;
					}
					break;
				}
			}
		}
	}

	@Override public String toString()
	{
		String returnable = "";
		for (Consonant each : chosenConsonants)
		{
			returnable += (each.utf8Char + " ");
		}
		return returnable;
	}
}