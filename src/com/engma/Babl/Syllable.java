package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Syllable implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5030287156300987746L;
	public String	debug	= "";
	public Onset	onset	= new Onset();
	public Nucleus	nucleus	= new Nucleus();
	public Coda		coda	= new Coda();

	//Syllable		thisSyllable	= new Syllable();

	Random			random	= new Random();

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
	
	
	public Syllable(ArrayList<Consonant> onset, ArrayList<Vowel> nucleus, ArrayList<Consonant> coda)
	{
		this.onset = new Onset(onset);
		this.nucleus = new Nucleus(nucleus);
		this.coda = new Coda(coda);
	}

	public Syllable()
	{
		this.onset = new Onset();
		this.nucleus = new Nucleus();
		this.coda = new Coda();
	}

	public Syllable(int onsetConsonants, int nucleiVowels, int codaConsonants, Properties settings,
			List<Consonant> consonants, List<Vowel> vowels, SonorityScale sonorityScale)
	{
		//int currentSonorityTier = 0;

		//int highestSonorityTier = sonorityScale.sonorityScale.size();

		//make the onset first
		if ((settings.getProperty("syllableOnsetOptional").equals("required")) || (random.nextInt(2) == 1))
		{
			this.onset = new Onset(onsetConsonants, consonants, new ArrayList<Consonant>(), 0, settings);
			//System.out.println("1:"+this.onset);
			//			System.out.print("\t");
			//			for (Consonant onsetConsonant : this.onset.onsetConsonants)
			//			{
			//				System.out.print(onsetConsonant.SonorityTier+" ");
			//			}
			//			System.out.println();
		}

		this.nucleus = new Nucleus(nucleiVowels, vowels, new ArrayList<Vowel>(), settings);
		//System.out.println("2:"+this.nucleus);
		if ((settings.getProperty("allowClosedSyllables").equals("true")) || (random.nextInt(3) == 1))
		{
			this.coda = new Coda(codaConsonants, consonants, new ArrayList<Consonant>(), 10, settings);
//			System.out.println("3:"+this.coda);
//			System.out.print("\t");
//						for (Consonant codaConsonant : this.coda.codaConsonants)
//						{
//							System.out.print(codaConsonant.SonorityTier+" ");
//						}
//						System.out.println();
		}

	}

	@Override
	public String toString()
	{
		String onsetString = "";
		String nucleusString = "";
		String codaString = "";
		for (Consonant each : onset.onsetConsonants)
		{
			onsetString += each.utf8Char;
		}
		for (Vowel each : nucleus.nucleusVowels)
		{
			nucleusString += each.utf8Char;
		}
		for (Consonant each : coda.codaConsonants)
		{
			codaString += each.utf8Char;
		}
		//TODO: PUT OODLES MORE DEBUG INFO HERE

		return onsetString + "" + nucleusString + "" + codaString;
	}
	public String verboseToString()
	{
		String returnable = "";
		for (Consonant each : onset.onsetConsonants)
		{
			returnable += each.verboseToString() +"\n";
		}
		for (Vowel each : nucleus.nucleusVowels)
		{
			returnable += each.verboseToString() +"\n";
		}
		for (Consonant each : coda.codaConsonants)
		{
			returnable += each.verboseToString() +"\n";
		}
		
		
		return returnable;
	}

}
