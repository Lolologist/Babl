package com.engma.Babl;

import java.io.*;

public class Word implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6848207056634612722L;
	String pos = "";
	BaseWordForm word = null;
	String english = "";


	public Word(BaseWordForm word, String english, String pos)
	{
		this.english = english;
		this.word = word;
		this.pos = pos;
	}
	public Word()
	{
		this.english = "null";
		this.word = new BaseWordForm();
		this.pos = "null";
	}
	public String getPos()
	{
		return pos;
	}
	public String getEnglish()
	{
		return english;
	}
	public void setPos(String pos)
	{
		this.pos = pos;
	}
	public BaseWordForm getWord()
	{
		return word;
	}
	public void setWord(BaseWordForm word)
	{
		this.word = word;
	}
	public void setEnglish(String english)
	{
		this.english = english;
	}
	@Override
	public String toString()
	{
		String returnable = "";
		for (Syllable s : word.syllables)
		{
			returnable += s.toString();
		}
		return returnable;
		//return word.syllables.toString();//+":"+english+"::"+pos;
	}
	public String verboseToString()
	{
		String returnable = "";
		returnable +=word+":"+english+"::"+pos+"\n";
		for (Syllable each : this.word.syllables)
		{
			returnable += each.verboseToString();
		}
		return returnable;
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


}
