package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Onset implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3698834418592611430L;
	private Random			rand								= new Random();
	private List<Consonant>	availableHigherSonorityConsonants	= new ArrayList<Consonant>();
	ArrayList<Consonant>	onsetConsonants						= new ArrayList<Consonant>();
	private int				minimumSonorityTierForNextConsonant	= 0;

	public Onset(int consonantsNeeded, List<Consonant> consonants, ArrayList<Consonant> chosenConsonantsSoFar,
			int minimumSonorityTierForNextConsonant, Properties settings)
	{//only use this if 1+ consonants need to be added
		//System.out.println(consonants);
		this.onsetConsonants = chosenConsonantsSoFar;
		this.minimumSonorityTierForNextConsonant = minimumSonorityTierForNextConsonant;
		Consonant firstConsonant = consonants.get(rand.nextInt(consonants.size()));
		this.minimumSonorityTierForNextConsonant = firstConsonant.SonorityTier
				+ Integer.parseInt(settings.getProperty("minimumSonorityJump"));
		//System.out.println("just selected "+firstConsonant+" at sonority "+firstConsonant.Sonority+" and tier "+firstConsonant.SonorityTier+", and next consonant must be of at least sonority tier "+this.minimumSonorityTierForNextConsonant+", because of minimum sonority jump "+settings.getProperty("minimumSonorityJump")+" and sonority plateau being "+settings.getProperty("sonorityPlateau"));

		onsetConsonants.add(firstConsonant);
		//check that you can get something of a lower sonority if needed

		int adjustment = 0;
		if (settings.getProperty("sonorityPlateau").equals("true"))
		{
			adjustment = 1;
		}

		higherSonorityChecker(firstConsonant, consonants, this.minimumSonorityTierForNextConsonant, adjustment);

		while ((availableHigherSonorityConsonants.size() > 1) && (consonantsNeeded > 1) && rand.nextInt(3) != 1)//TODO: make another method that takes 2 integers:minimum and maximum onset size. Make this one remove the last && so that it always makes onsets of the correct, specified, size 
		{
			//System.out.println("we have to go DERPer");//There is at least one available to use and we need at least one more consonant
			Consonant nextConsonant = availableHigherSonorityConsonants.get(rand.nextInt(availableHigherSonorityConsonants.size()));
			onsetConsonants.add(nextConsonant);
			consonantsNeeded -= 1;
			this.minimumSonorityTierForNextConsonant = nextConsonant.SonorityTier+Integer.parseInt(settings.getProperty("minimumSonorityJump"));
			//System.out.println("just selected "+nextConsonant+" at sonority "+nextConsonant.Sonority+" and tier "+nextConsonant.SonorityTier+", and next consonant must be of at least sonority tier "+this.minimumSonorityTierForNextConsonant+", because of minimum sonority jump "+settings.getProperty("minimumSonorityJump")+" and sonority plateau being "+settings.getProperty("sonorityPlateau"));
			higherSonorityChecker(nextConsonant, consonants, this.minimumSonorityTierForNextConsonant, adjustment);
		}
		//return chosenConsonantsSoFar;
		for (Consonant each : onsetConsonants)
		{
			outerloop:
				//we allow a click to be the whole onset, or an implosive, but clicks take precedence because fun
			if (each.manner.equals(Sound.Manner.Click))
			{
				//System.out.println("!");
				Consonant addme = each;
				onsetConsonants = new ArrayList<Consonant>();
				onsetConsonants.add(addme);
				break outerloop;
			} else if (each.manner.equals(Sound.Manner.Implosive))
			{
				onsetConsonants = new ArrayList<Consonant>();
				onsetConsonants.add(each);
				break outerloop;
			}
		}
	}

	public Onset(ArrayList<Consonant> predefinedConsonants)
	{
		this.onsetConsonants = predefinedConsonants;
	}

	public Onset()
	{
		this.onsetConsonants = new ArrayList<Consonant>();
	}

	private void higherSonorityChecker(Consonant firstConsonant, List<Consonant> consonants,
			int minimumSonorityTierForNextConsonant, int adjustment)
	{
		availableHigherSonorityConsonants.clear();
		for (Consonant testedConsonant : consonants)
		{
			if (testedConsonant.SonorityTier > this.minimumSonorityTierForNextConsonant - adjustment)//the potential adjustment makes this just like a >=
			{
				//System.out.println(testedConsonant+":T"+testedConsonant.SonorityTier+" "+this.minimumSonorityTierForNextConsonant);
				availableHigherSonorityConsonants.add(testedConsonant);
			}
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
		for (Consonant each : onsetConsonants)
		{
			returnable+=each;
		}
		return returnable;
	}
}
