package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Coda implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7706473034071297623L;
	private Random			rand								= new Random();
	private List<Consonant>	availableLowerSonorityConsonants	= new ArrayList<Consonant>();
	ArrayList<Consonant>	codaConsonants						= new ArrayList<Consonant>();
	ArrayList<Consonant>	consonants							= new ArrayList<Consonant>();
	private int				maximumSonorityTierForNextConsonant	= 10;

	public Coda(int consonantsNeeded, List<Consonant> consonants, ArrayList<Consonant> chosenConsonantsSoFar,
			int maximumSonorityTierForNextConsonant, Properties settings)
	{//only use this if 1+ consonants need to be added

		List<String> prohibitedSounds = Arrays.asList(settings.getProperty("prohibitedCodaSounds").split(","));

		if (settings.getProperty("allowClosedSyllables").equals("true"))
		{
			this.codaConsonants = chosenConsonantsSoFar;
			for (int i = 0; i < consonants.size(); i++)
			{
				if (!prohibitedSounds.contains(consonants.get(i).utf8Char))
				{
					this.consonants.add(consonants.get(i));
				}
			}
			this.maximumSonorityTierForNextConsonant = maximumSonorityTierForNextConsonant;
			Consonant firstConsonant = this.consonants.get(rand.nextInt(this.consonants.size()));
			this.maximumSonorityTierForNextConsonant = firstConsonant.SonorityTier
					- Integer.parseInt(settings.getProperty("minimumSonorityJump"));

			codaConsonants.add(firstConsonant);
			//System.out.println("just selected " + firstConsonant + " at sonority " + firstConsonant.Sonority
			//		+ " and tier " + firstConsonant.SonorityTier
			//		+ ", and next consonant must be of at most sonority tier "
			//		+ this.maximumSonorityTierForNextConsonant + ", because of minimum sonority jump "
			//		+ settings.getProperty("minimumSonorityJump") + " and sonority plateau being "
			//		+ settings.getProperty("sonorityPlateau"));
			//check that you can get something of a lower sonority if needed

			int adjustment = 0;
			if (settings.getProperty("sonorityPlateau").equals("true"))
			{
				adjustment = 1;
			}

			lowerSonorityChecker(firstConsonant, this.consonants, this.maximumSonorityTierForNextConsonant, adjustment);

			while ((availableLowerSonorityConsonants.size() > 1) && (consonantsNeeded > 1) && rand.nextInt(3) != 1)//TODO: make another method that takes 2 integers:minimum and maximum onset size. Make this one remove the last && so that it always makes onsets of the correct, specified, size 
			{
				//System.out.println("we have to go DERPer");//There is at least one available to use and we need at least one more consonant
				Consonant nextConsonant = availableLowerSonorityConsonants.get(rand
						.nextInt(availableLowerSonorityConsonants.size()));
				codaConsonants.add(nextConsonant);
				consonantsNeeded -= 1;
				this.maximumSonorityTierForNextConsonant = nextConsonant.SonorityTier
						- Integer.parseInt(settings.getProperty("minimumSonorityJump"));
//				System.out.println("just selected " + nextConsonant + " at sonority " + nextConsonant.Sonority
//						+ " and tier " + nextConsonant.SonorityTier
//						+ ", and next consonant must be of at most sonority tier "
//						+ this.maximumSonorityTierForNextConsonant + ", because of minimum sonority jump "
//						+ settings.getProperty("minimumSonorityJump") + " and sonority plateau being "
//						+ settings.getProperty("sonorityPlateau"));
				lowerSonorityChecker(nextConsonant, this.consonants, this.maximumSonorityTierForNextConsonant,
						adjustment);
			}
		}

		for (Consonant each : codaConsonants)
		{
			outerloop:
			//we allow an implosive in the coda, but it takes the whole coda
			if (each.manner.equals(Sound.Manner.Implosive))
			{
				codaConsonants = new ArrayList<Consonant>();
				codaConsonants.add(each);
				break outerloop;
			}
		}

	}

	public Coda(ArrayList<Consonant> predefinedConsonants)
	{
		this.codaConsonants = predefinedConsonants;
	}

	public Coda()
	{
		this.codaConsonants = new ArrayList<Consonant>();
	}

	private void lowerSonorityChecker(Consonant firstConsonant, List<Consonant> consonants1,
			int minimumSonorityTierForNextConsonant, int adjustment)
	{
		availableLowerSonorityConsonants.clear();
		for (Consonant testedConsonant : consonants1)
		{
			if (testedConsonant.SonorityTier < this.maximumSonorityTierForNextConsonant - adjustment)//the potential adjustment makes this just like a >=
			{
				availableLowerSonorityConsonants.add(testedConsonant);
			}
		}
	} //Include const

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
		for (Consonant each : codaConsonants)
		{
			returnable += each;
		}
		return returnable;
	}
}
