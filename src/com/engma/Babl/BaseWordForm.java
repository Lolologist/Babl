package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BaseWordForm implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4456337316617822356L;
	public ArrayList<Syllable> syllables = null;
	
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
	
	public BaseWordForm(ArrayList<Syllable> syllables)
	{
		this.syllables = syllables;
	}
	public BaseWordForm(Syllable syllable)
	{
		ArrayList<Syllable> syllables = new ArrayList<Syllable>();
		syllables.add(syllable);
		this.syllables = syllables;
	}
	public BaseWordForm()
	{
		this.syllables = null;
	}
	
	public BaseWordForm(String group)
	{
		
	}

	public Sound getFirstSound()
	{
		Syllable firstSyllable = this.syllables.get(0);
		if (firstSyllable.onset.onsetConsonants.isEmpty())
		{
			return firstSyllable.nucleus.nucleusVowels.get(0);
		} else {
			return firstSyllable.onset.onsetConsonants.get(0);
		}
	}
	public Sound getLastSound()
	{
		Syllable lastSyllable = this.syllables.get(this.syllables.size()-1);
		if (lastSyllable.coda.codaConsonants.isEmpty())
		{
			return lastSyllable.nucleus.nucleusVowels.get(lastSyllable.nucleus.nucleusVowels.size()-1);
		} else {
			return lastSyllable.coda.codaConsonants.get(lastSyllable.coda.codaConsonants.size()-1);
		}
	}
	
	public int size()
	{
		int i = 0;
		for (Syllable each : this.syllables)
		{
			i+= each.onset.onsetConsonants.size();
			i+= each.nucleus.nucleusVowels.size();
			i+= each.coda.codaConsonants.size();
		}
		
		return i;
	}
	
	@Override
	public String toString() {
		String whole = "";
		for (Syllable each : this.syllables)
		{
			whole+= each.toString();
		}
			
			return whole;
	}
	
}
