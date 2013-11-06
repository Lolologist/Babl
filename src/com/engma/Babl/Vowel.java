package com.engma.Babl;

import java.io.*;

public class Vowel extends Sound implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2196074904104408928L;
	Sound.VowelFrontedness	vowelfrontedness	= null;
	Sound.VowelOpenness		vowelopenness		= null;
	Sound.VowelRoundedness	vowelroundedness	= null;
	public String			utf8Char;
	public int				SonorityTier		= 0;

	public int getSonorityTier()
	{
		return SonorityTier;
	}

	public void setSonorityTier(int sonorityTier)
	{
		SonorityTier = sonorityTier;
	}

	public Vowel(String utf8Char, Sound.VowelOpenness openness, Sound.VowelFrontedness frontedness,
			Sound.VowelRoundedness vowelroundedness)
	{
		this.utf8Char = utf8Char;
		this.vowelfrontedness = frontedness;
		this.vowelopenness = openness;
		this.vowelroundedness = vowelroundedness;

	}

	public Vowel()
	{
		this.utf8Char = null;
		this.vowelfrontedness = null;
		this.vowelopenness = null;
		this.vowelroundedness = null;
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
		return this.utf8Char;
		//return "Vowel [vowelfrontedness=" + vowelfrontedness + ", vowelopenness=" + vowelopenness + ", vowelroundedness=" + vowelroundedness + ", Sonority=" + Sonority + "]";
	}

	public String verboseToString()
	{
		
		String returnable = "Vowel "+utf8Char+" [vowelfrontedness=" + vowelfrontedness + ", vowelopenness=" + vowelopenness + ", vowelroundedness=" + vowelroundedness + ", Sonority=" + Sonority + "]";
		return returnable;
	}

}
