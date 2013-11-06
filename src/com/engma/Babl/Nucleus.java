package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Nucleus implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8916179208314514335L;
	private Random		rand							= new Random();
	private List<Vowel>	availableHigherSonorityVowels	= new ArrayList<Vowel>();
	ArrayList<Vowel>	nucleusVowels					= new ArrayList<Vowel>();
	private int			minimumSonorityTierForNextVowel	= 12;						//minimum for a vowel after all

	Nucleus(int vowelsNeeded, List<Vowel> vowels, ArrayList<Vowel> vowelsSoFar, Properties settings)
	{
		//get initial vowel
		Vowel firstVowel = vowels.get(rand.nextInt(vowels.size()));
		nucleusVowels.add(firstVowel);
		minimumSonorityTierForNextVowel = firstVowel.SonorityTier
				+ Integer.parseInt(settings.getProperty("minimumSonorityJump"));

		int adjustment = 0;
		if (settings.getProperty("sonorityPlateau").equals("true"))
		{
			adjustment = 1;
		}

		//make a list of potential higher-sonority vowels
		higherSonorityChecker(firstVowel, vowels, firstVowel.Sonority, adjustment);
		//this sets availableHigherSonorityVowels
		//	TODO: allow climbing up to highest sonority then back down.

		while ((availableHigherSonorityVowels.size() > 0) && (vowelsNeeded > 1) && (rand.nextInt(3) != 1))
		{

			//System.out.println("we have to go vDERPer");

			Vowel nextVowel = vowels.get(rand.nextInt(availableHigherSonorityVowels.size()));
			nucleusVowels.add(nextVowel);
			vowelsNeeded -= 1;
			minimumSonorityTierForNextVowel += Integer.parseInt(settings.getProperty("minimumSonorityJump"));//TODO: Note that this MUST BE AT LEAST 1
			higherSonorityChecker(nextVowel, vowels, minimumSonorityTierForNextVowel, adjustment);
		}

	}

	public Nucleus(ArrayList<Vowel> predefinedVowels)
	{
		this.nucleusVowels = predefinedVowels;
	}

	public Nucleus()
	{
		this.nucleusVowels = new ArrayList<Vowel>();
	}

	private void higherSonorityChecker(Vowel pickedVowel, List<Vowel> vowels, int minimumSonorityTierForNextVowel,
			int adjustment)
	{
		availableHigherSonorityVowels.clear();
		// TODO Auto-generated method stub
		for (Vowel testedVowel : vowels)
		{
			if (testedVowel.Sonority > minimumSonorityTierForNextVowel - adjustment)
			{
				availableHigherSonorityVowels.add(testedVowel);
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
		for (Vowel each : nucleusVowels)
		{
			returnable+=each;
		}
		return returnable;
	}
}
