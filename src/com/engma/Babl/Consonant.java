package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Consonant extends Sound implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1402766489700164442L;
	Sound.Voice			voicing			= null;
	Sound.Place			place			= null;
	Sound.Manner		manner			= null;
	Sound.Diacritics	diacritics		= null;
	public String		utf8Char;
	public int			SonorityTier	= 0;

	/*
	 * A Consonant consists of a UTF-8 character representing it, its voicing, place, manner, and any associated
	 * diacritics.
	 */
	public Consonant(String utf8Char, Voice voicing, Place place, Manner manner, Diacritics diacritics)
	{
		this.utf8Char = utf8Char;
		this.voicing = voicing;
		this.place = place;
		this.manner = manner;
		this.diacritics = diacritics;
		this.SonorityTier = 0;
	}

	public Consonant()
	{
		this.utf8Char = null;
		this.voicing = null;
		this.place = null;
		this.manner = null;
		this.diacritics = null;
		this.SonorityTier = -1;
	}
	
	public Consonant(String utf8Char, Voice voicing, Place place, Manner manner)
	{
		this.utf8Char = utf8Char;
		this.voicing = voicing;
		this.place = place;
		this.manner = manner;
		this.diacritics = null;
		this.SonorityTier = 0;
	}

	public int getSonorityTier()
	{
		return SonorityTier;
	}

	public void setSonorityTier(int sonorityTier)
	{
		SonorityTier = sonorityTier;
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
		//return "Consonant [voicing=" + voicing + ", place=" + place + ", manner=" + manner + ", diacritics=" + diacritics + ", Sonority=" + Sonority + "]";
	}

	public String verboseToString()
	{
		return "Consonant "+utf8Char+" [voicing=" + voicing + ", place=" + place + ", manner=" + manner + ", diacritics=" + diacritics + ", Sonority=" + Sonority + "]";
	}
}
