package com.engma.Babl;

import java.io.*;
import java.util.*;

public class Vowels implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3953109070553394839L;
	public static ArrayList<Vowel>	vowels	= new ArrayList<Vowel>();

	public Vowels()
	{
		Vowel i = new Vowel("i", Sound.VowelOpenness.Close, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Unrounded);
		i.Sonority = 12;
		Vowel y = new Vowel("y", Sound.VowelOpenness.Close, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Rounded);
		y.Sonority = 12;
		Vowel ɨ = new Vowel("ɨ", Sound.VowelOpenness.Close, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Unrounded);
		ɨ.Sonority = 12;
		Vowel ʉ = new Vowel("ʉ", Sound.VowelOpenness.Close, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Rounded);
		ʉ.Sonority = 12;
		Vowel ɯ = new Vowel("ɯ", Sound.VowelOpenness.Close, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Unrounded);
		ɯ.Sonority = 12;
		Vowel u = new Vowel("u", Sound.VowelOpenness.Close, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Rounded);
		u.Sonority = 12;
		Vowel ɪ = new Vowel("ɪ", Sound.VowelOpenness.NearClose, Sound.VowelFrontedness.NearFront, Sound.VowelRoundedness.Unrounded);
		ɪ.Sonority = 12;
		Vowel ʏ = new Vowel("ʏ", Sound.VowelOpenness.NearClose, Sound.VowelFrontedness.NearFront, Sound.VowelRoundedness.Rounded);
		ʏ.Sonority = 12;
		Vowel ʊ = new Vowel("ʊ", Sound.VowelOpenness.NearClose, Sound.VowelFrontedness.NearBack, Sound.VowelRoundedness.Rounded);
		ʊ.Sonority = 12;
		Vowel e = new Vowel("e", Sound.VowelOpenness.CloseMid, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Unrounded);
		e.Sonority = 13;
		Vowel ø = new Vowel("ø", Sound.VowelOpenness.CloseMid, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Rounded);
		ø.Sonority = 13;
		Vowel ɘ = new Vowel("ɘ", Sound.VowelOpenness.CloseMid, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Unrounded);
		ɘ.Sonority = 13;
		Vowel ɵ = new Vowel("ɵ", Sound.VowelOpenness.CloseMid, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Rounded);
		ɵ.Sonority = 13;
		Vowel ɤ = new Vowel("ɤ", Sound.VowelOpenness.CloseMid, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Unrounded);
		ɤ.Sonority = 13;
		Vowel o = new Vowel("o", Sound.VowelOpenness.CloseMid, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Rounded);
		o.Sonority = 13;
		Vowel ə = new Vowel("ə", Sound.VowelOpenness.Mid, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Unrounded);
		ə.Sonority = 13;
		Vowel ɛ = new Vowel("ɛ", Sound.VowelOpenness.OpenMid, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Unrounded);
		ɛ.Sonority = 13;
		Vowel œ = new Vowel("œ", Sound.VowelOpenness.OpenMid, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Rounded);
		œ.Sonority = 13;
		Vowel ɜ = new Vowel("ɜ", Sound.VowelOpenness.OpenMid, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Unrounded);
		ɜ.Sonority = 13;
		Vowel ɞ = new Vowel("ɞ", Sound.VowelOpenness.OpenMid, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Rounded);
		ɞ.Sonority = 13;
		Vowel ʌ = new Vowel("ʌ", Sound.VowelOpenness.OpenMid, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Unrounded);
		ʌ.Sonority = 13;
		Vowel ɔ = new Vowel("ɔ", Sound.VowelOpenness.OpenMid, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Rounded);
		ɔ.Sonority = 13;
		Vowel æ = new Vowel("æ", Sound.VowelOpenness.NearOpen, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Unrounded);
		æ.Sonority = 14;
		Vowel ɐ = new Vowel("ɐ", Sound.VowelOpenness.NearOpen, Sound.VowelFrontedness.Central, Sound.VowelRoundedness.Unrounded);
		ɐ.Sonority = 14;
		Vowel a = new Vowel("a", Sound.VowelOpenness.Open, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Unrounded);
		a.Sonority = 14;
		Vowel ɶ = new Vowel("ɶ", Sound.VowelOpenness.Open, Sound.VowelFrontedness.Front, Sound.VowelRoundedness.Rounded);
		ɶ.Sonority = 14;
		Vowel ɑ = new Vowel("ɑ", Sound.VowelOpenness.Open, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Unrounded);
		ɑ.Sonority = 14;
		Vowel ɒ = new Vowel("ɒ", Sound.VowelOpenness.Open, Sound.VowelFrontedness.Back, Sound.VowelRoundedness.Rounded);
		ɒ.Sonority = 14;
		vowels = new ArrayList<Vowel>(Arrays.asList(i, y, ɨ, ʉ, ɯ, u, ɪ, ʏ, e, ø, ɘ, ɵ, ʊ, ɤ, o, ə, ɛ, œ, ɜ, ɞ, ʌ, ɔ, æ, ɐ, a, ɶ, ɑ, ɒ));

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
	public String toString()
	{
		String returnable = "";
		for (Vowel v : vowels)
		{
			returnable+= v+" ";
		}
		return "Vowels:"+returnable;
	}
}
