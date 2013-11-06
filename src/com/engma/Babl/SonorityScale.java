package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

public class SonorityScale implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1979408114858851047L;
	HashMap<Integer, Integer>	sonorityScale	= new HashMap<Integer, Integer>();
	Random						random			= new Random();
	String						debugInfo		= "";

	public SonorityScale(Properties settings)
	{
		String providedSonorityScale = settings.getProperty("SonorityScale");
		String[] stringArrayScale = providedSonorityScale.split(",");
		// will be in format {1-1,2-1,3-2,4-3, etc} where NUMBERS ON LEFT REPRESENT SONORITY LEVEL OF CONSONANT AND NUMBERS ON RIGHT REPRESENT SONORITY TIER OF SCALE. 
		// RIGHT NUMBER WILL NOT GO AS HIGH AS LEFT. 
		for (String each : stringArrayScale)
		{
			String[] halves = each.split("-");
			if (sonorityScale.containsValue(halves[1]))
			{
				sonorityScale.put(Integer.parseInt(halves[0]), Integer.parseInt(halves[1]));
			}
		}
		for (Integer a : sonorityScale.values())
		{
			System.out.println(a + ":" + sonorityScale.get(a));
		}
	}

	public SonorityScale()
	{

		// Next comes the Sonority Scale. Each palce of articulation to the
		// right gets higher, and certain ones are likely to stick together.
		// Especially the lot on the right, as it's hard to do consonant
		// clusters of them.
		// SONORITY SCALE
		// TODO: If specified in the properties file, set all or some of it
		// Voiceless stops = 1, voiced stops = 2, voiceless affricates = 3,
		// voiced affricated = 4, voiceless fricatives = 5, voiced fricatives
		// =6, nasals = 7, taps/flaps = 8, trills = 9, laterals = 10,
		// approximants = 11, 12 = close (and near close) vowels, 13 = close-mid
		// (and mid and open-mid), 14 = near open and open vowels

		// NOTE THAT /s/ and /z/ often are invisible to sonority and can go
		// first.

		int highestTier = 0;
		if (random.nextInt(2) == 1)
		{
			highestTier += 1;
			sonorityScale.put(1, highestTier);

			highestTier += 1;
			sonorityScale.put(2, highestTier);
		} else
		{
			highestTier +=1;
			sonorityScale.put(1,highestTier);
			sonorityScale.put(2,highestTier);
		}

		if (random.nextInt(2) == 1)
		{
			highestTier +=1;
			sonorityScale.put(3,highestTier);
			highestTier +=1;
			sonorityScale.put(4,highestTier);
		} else
		{
			highestTier +=1;
			sonorityScale.put(3,highestTier);
			sonorityScale.put(4,highestTier);
		}
		// TODO: add ability to combine 1,2,3, and 4

		if (random.nextInt(2) == 1)
		{
			highestTier +=1;
			sonorityScale.put(5,highestTier);
			highestTier +=1;
			sonorityScale.put(6,highestTier);
		} else
		{
			highestTier +=1;
			sonorityScale.put(5,highestTier);
			sonorityScale.put(6,highestTier);
		}
		//nasals can go in their own, or the one above
		int nasalNumber = random.nextInt(3);
		if (nasalNumber == 0)
		{
			highestTier +=1;
			sonorityScale.put(7, highestTier);
		} else if (nasalNumber == 1)
		{
			highestTier +=1;
			//System.out.println("JUST CHECKING: MADE IT TO AN ELSEIF STATEMENT. ISNT THAT NICE");
			sonorityScale.put(7, highestTier-1);
		} else {
			sonorityScale.put(7, highestTier+1);
		}
		
		if (random.nextInt(2) == 1)
		{
			highestTier +=1;
			sonorityScale.put(8,highestTier);
			highestTier +=1;
			sonorityScale.put(9,highestTier);
		} else
		{
			highestTier +=1;
			sonorityScale.put(8,highestTier);
			sonorityScale.put(9,highestTier);
		}
		
		if (random.nextInt(2) == 1)
		{
			highestTier +=1;
			sonorityScale.put(10,highestTier);
			highestTier +=1;
			sonorityScale.put(11,highestTier);
		} else
		{
			highestTier +=1;
			sonorityScale.put(10,highestTier);
			sonorityScale.put(11,highestTier);
		}
		
		int vowelSonority = random.nextInt(2);
		if (vowelSonority == 0)
		{
			highestTier +=1;
			sonorityScale.put(12,highestTier);
			highestTier +=1;
			sonorityScale.put(13,highestTier);
			highestTier +=1;
			sonorityScale.put(14,highestTier);
		} else {
			highestTier +=1;
			sonorityScale.put(12,highestTier);
			sonorityScale.put(13,highestTier);
			sonorityScale.put(14,highestTier);
		}
		for (Consonant each : Consonants.consonants)
		{
			each.setSonorityTier(sonorityScale.get(each.Sonority));
		}
		for (Vowel each : Vowels.vowels)
		{
			each.setSonorityTier(sonorityScale.get(each.Sonority));
		}	
		
	}

	/**
	   * Always treat de-serialization as a full-blown constructor, by
	   * validating the final state of the de-serialized object.
	   */
	   private void readObject(
	     ObjectInputStream aInputStream
	   ) throws ClassNotFoundException, IOException {
	     //always perform the default de-serialization first
	     aInputStream.defaultReadObject();
	  }

	    /**
	    * This is the default implementation of writeObject.
	    * Customise if necessary.
	    */
	    private void writeObject(
	      ObjectOutputStream aOutputStream
	    ) throws IOException {
	      //perform the default serialization for all non-transient, non-static fields
	      aOutputStream.defaultWriteObject();
	    }
	
	@Override
	public String toString()
	{
		String returnable = "";
		for (Integer each : sonorityScale.keySet())
		{
			returnable += each+":"+sonorityScale.get(each)+" ";
		}
		returnable += "\n";
		
		
		
		return returnable;
	}

}
