package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.engma.Babl.Sound.Diacritics;

public class Consonants implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7229323789107469106L;
	static ArrayList<Consonant>	consonants	= new ArrayList<Consonant>();

	public Consonants()
	{
		
		Consonant p = new Consonant("p", Sound.Voice.Minus, Sound.Place.Bilabial, Sound.Manner.Plosive);
		p.Sonority = 1;
		Consonant b = new Consonant("b", Sound.Voice.Plus, Sound.Place.Bilabial, Sound.Manner.Plosive);
		b.Sonority = 2;
		Consonant m = new Consonant("m", Sound.Voice.Plus, Sound.Place.Bilabial, Sound.Manner.Nasal);
		m.Sonority = 7;
		Consonant ʙ = new Consonant("ʙ", Sound.Voice.Plus, Sound.Place.Bilabial, Sound.Manner.Trill);
		ʙ.Sonority = 9;
		Consonant ɸ = new Consonant("ɸ", Sound.Voice.Minus, Sound.Place.Bilabial, Sound.Manner.Fricative);
		ɸ.Sonority = 5;
		Consonant β = new Consonant("β", Sound.Voice.Plus, Sound.Place.Bilabial, Sound.Manner.Fricative);
		β.Sonority = 6;
		Consonant ɱ = new Consonant("ɱ", Sound.Voice.Plus, Sound.Place.Labiodental, Sound.Manner.Nasal);
		ɱ.Sonority = 7;
		Consonant ⱱ = new Consonant("ⱱ", Sound.Voice.Plus, Sound.Place.Labiodental, Sound.Manner.Tap);
		ⱱ.Sonority = 8;
		Consonant f = new Consonant("f", Sound.Voice.Minus, Sound.Place.Labiodental, Sound.Manner.Fricative);
		f.Sonority = 5;
		Consonant v = new Consonant("v", Sound.Voice.Plus, Sound.Place.Labiodental, Sound.Manner.Fricative);
		v.Sonority = 6;
		Consonant ʋ = new Consonant("ʋ", Sound.Voice.Plus, Sound.Place.Labiodental, Sound.Manner.Approximant);
		ʋ.Sonority = 11;
		Consonant t = new Consonant("t", Sound.Voice.Minus, Sound.Place.Alveolar, Sound.Manner.Plosive);
		t.Sonority = 1;
		Consonant d = new Consonant("d", Sound.Voice.Plus, Sound.Place.Alveolar, Sound.Manner.Plosive);
		d.Sonority = 2;
		Consonant n = new Consonant("n", Sound.Voice.Plus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.Nasal);
		n.Sonority = 7;
		Consonant r = new Consonant("r", Sound.Voice.Plus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.Trill);
		r.Sonority = 9;
		Consonant ɾ = new Consonant("ɾ", Sound.Voice.Plus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.Tap);
		ɾ.Sonority = 8;
		Consonant θ = new Consonant("θ", Sound.Voice.Minus, Sound.Place.Dental, Sound.Manner.Fricative);
		θ.Sonority = 5;
		Consonant ð = new Consonant("ð", Sound.Voice.Plus, Sound.Place.Dental, Sound.Manner.Fricative);
		ð.Sonority = 6;
		Consonant s = new Consonant("s", Sound.Voice.Minus, Sound.Place.Alveolar, Sound.Manner.Fricative);
		s.Sonority = 5;
		Consonant z = new Consonant("z", Sound.Voice.Plus, Sound.Place.Alveolar, Sound.Manner.Fricative);
		z.Sonority = 6;
		Consonant ʃ = new Consonant("ʃ", Sound.Voice.Minus, Sound.Place.PalatoAlveolar, Sound.Manner.Fricative);
		ʃ.Sonority = 5;
		Consonant ʒ = new Consonant("ʒ", Sound.Voice.Plus, Sound.Place.PalatoAlveolar, Sound.Manner.Fricative);
		ʒ.Sonority = 6;
		Consonant ɬ = new Consonant("ɬ", Sound.Voice.Minus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.LateralFricative);
		ɬ.Sonority = 10;
		Consonant ɮ = new Consonant("ɮ", Sound.Voice.Plus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.LateralFricative);
		ɮ.Sonority = 10;
		Consonant ɹ = new Consonant("ɹ", Sound.Voice.Plus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.Approximant);
		ɹ.Sonority = 11;
		Consonant l = new Consonant("l", Sound.Voice.Plus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.LateralApproximant);
		l.Sonority = 10;
		Consonant ʈ = new Consonant("ʈ", Sound.Voice.Minus, Sound.Place.Retroflex, Sound.Manner.Plosive);
		ʈ.Sonority = 1;
		Consonant ɖ = new Consonant("ɖ", Sound.Voice.Plus, Sound.Place.Retroflex, Sound.Manner.Plosive);
		ɖ.Sonority = 2;
		Consonant ɳ = new Consonant("ɳ", Sound.Voice.Plus, Sound.Place.Retroflex, Sound.Manner.Nasal);
		ɳ.Sonority = 7;
		Consonant ɽ = new Consonant("ɽ", Sound.Voice.Plus, Sound.Place.Retroflex, Sound.Manner.Tap);
		ɽ.Sonority = 8;
		Consonant ʂ = new Consonant("ʂ", Sound.Voice.Minus, Sound.Place.Retroflex, Sound.Manner.Fricative);
		ʂ.Sonority = 5;
		Consonant ʐ = new Consonant("ʐ", Sound.Voice.Plus, Sound.Place.Retroflex, Sound.Manner.Fricative);
		ʐ.Sonority = 6;
		Consonant ɻ = new Consonant("ɻ", Sound.Voice.Plus, Sound.Place.Retroflex, Sound.Manner.Approximant);
		ɻ.Sonority = 11;
		Consonant ɭ = new Consonant("ɭ", Sound.Voice.Plus, Sound.Place.Retroflex, Sound.Manner.LateralApproximant);
		ɭ.Sonority = 10;
		Consonant c = new Consonant("c", Sound.Voice.Minus, Sound.Place.AlveoloPalatalPalatal, Sound.Manner.Plosive);
		c.Sonority = 1;
		Consonant ɟ = new Consonant("ɟ", Sound.Voice.Plus, Sound.Place.AlveoloPalatalPalatal, Sound.Manner.Plosive);
		ɟ.Sonority = 2;
		Consonant ɲ = new Consonant("ɲ", Sound.Voice.Plus, Sound.Place.AlveoloPalatalPalatal, Sound.Manner.Nasal);
		ɲ.Sonority = 7;
		Consonant ç = new Consonant("ç", Sound.Voice.Minus, Sound.Place.Palatal, Sound.Manner.Fricative);
		ç.Sonority = 5;
		Consonant ʝ = new Consonant("ʝ", Sound.Voice.Plus, Sound.Place.Palatal, Sound.Manner.Fricative);
		ʝ.Sonority = 6;
		Consonant j = new Consonant("j", Sound.Voice.Plus, Sound.Place.AlveoloPalatalPalatal, Sound.Manner.Approximant);
		j.Sonority = 11;
		Consonant ʎ = new Consonant("ʎ", Sound.Voice.Plus, Sound.Place.AlveoloPalatalPalatal, Sound.Manner.LateralApproximant);
		ʎ.Sonority = 10;
		Consonant k = new Consonant("k", Sound.Voice.Minus, Sound.Place.Velar, Sound.Manner.Plosive);
		k.Sonority = 1;
		Consonant ɡ = new Consonant("ɡ", Sound.Voice.Plus, Sound.Place.Velar, Sound.Manner.Plosive);
		ɡ.Sonority = 2;
		Consonant ŋ = new Consonant("ŋ", Sound.Voice.Plus, Sound.Place.Velar, Sound.Manner.Nasal);
		ŋ.Sonority = 7;
		Consonant x = new Consonant("x", Sound.Voice.Minus, Sound.Place.Velar, Sound.Manner.Fricative);
		x.Sonority = 5;
		Consonant ɣ = new Consonant("ɣ", Sound.Voice.Plus, Sound.Place.Velar, Sound.Manner.Fricative);
		ɣ.Sonority = 6;
		Consonant ɰ = new Consonant("ɰ", Sound.Voice.Plus, Sound.Place.Velar, Sound.Manner.Approximant);
		ɰ.Sonority = 11;
		Consonant ʟ = new Consonant("ʟ", Sound.Voice.Plus, Sound.Place.Velar, Sound.Manner.LateralApproximant);
		ʟ.Sonority = 10;
		Consonant q = new Consonant("q", Sound.Voice.Minus, Sound.Place.Uvular, Sound.Manner.Plosive);
		q.Sonority = 1;
		Consonant ɢ = new Consonant("ɢ", Sound.Voice.Plus, Sound.Place.Uvular, Sound.Manner.Plosive);
		ɢ.Sonority = 2;
		Consonant ɴ = new Consonant("ɴ", Sound.Voice.Plus, Sound.Place.Uvular, Sound.Manner.Nasal);
		ɴ.Sonority = 7;
		Consonant ʀ = new Consonant("ʀ", Sound.Voice.Plus, Sound.Place.Uvular, Sound.Manner.Trill);
		ʀ.Sonority = 9;
		Consonant χ = new Consonant("χ", Sound.Voice.Minus, Sound.Place.Uvular, Sound.Manner.Fricative);
		χ.Sonority = 5;
		Consonant ʁ = new Consonant("ʁ", Sound.Voice.Plus, Sound.Place.Uvular, Sound.Manner.FricativeApproximant);
		ʁ.Sonority = 6;
		Consonant ħ = new Consonant("ħ", Sound.Voice.Minus, Sound.Place.Pharyngeal, Sound.Manner.Fricative);
		ħ.Sonority = 5;
		Consonant ʕ = new Consonant("ʕ", Sound.Voice.Plus, Sound.Place.Pharyngeal, Sound.Manner.FricativeApproximant);
		ʕ.Sonority = 6;
		Consonant ʔ = new Consonant("ʔ", Sound.Voice.Minus, Sound.Place.Glottal, Sound.Manner.Plosive);
		ʔ.Sonority = 11;
		Consonant ʡ = new Consonant("ʡ", Sound.Voice.Plus, Sound.Place.Epiglottal, Sound.Manner.Plosive);
		ʡ.Sonority = 11;
		Consonant h = new Consonant("h", Sound.Voice.Minus, Sound.Place.Glottal, Sound.Manner.FricativeApproximant);
		h.Sonority = 5;
		Consonant ɦ = new Consonant("ɦ", Sound.Voice.Minus, Sound.Place.Glottal, Sound.Manner.FricativeApproximant);
		ɦ.Sonority = 6;
		Consonant ʜ = new Consonant("ʜ", Sound.Voice.Minus, Sound.Place.Epiglottal, Sound.Manner.Fricative);
		ʜ.Sonority = 5;
		Consonant ʢ = new Consonant("ʢ", Sound.Voice.Plus, Sound.Place.Epiglottal, Sound.Manner.FricativeApproximant);
		ʢ.Sonority = 6;// check fricative
		Consonant ƥ = new Consonant("ƥ", Sound.Voice.Minus, Sound.Place.Bilabial, Sound.Manner.Implosive);
		ƥ.Sonority = 1;
		Consonant ɓ = new Consonant("ɓ", Sound.Voice.Plus, Sound.Place.Bilabial, Sound.Manner.Implosive);
		ɓ.Sonority = 2;
		Consonant ƭ = new Consonant("ƭ", Sound.Voice.Minus, Sound.Place.DentalOrAlveolar, Sound.Manner.Implosive);
		ƭ.Sonority = 1;
		Consonant ɗ = new Consonant("ɗ", Sound.Voice.Plus, Sound.Place.DentalOrAlveolar, Sound.Manner.Implosive);
		ɗ.Sonority = 2;
		Consonant ƈ = new Consonant("ƈ", Sound.Voice.Minus, Sound.Place.Palatal, Sound.Manner.Implosive);
		ƈ.Sonority = 1;
		Consonant ʄ = new Consonant("ʄ", Sound.Voice.Plus, Sound.Place.Palatal, Sound.Manner.Implosive);
		ʄ.Sonority = 2;
		Consonant ƙ = new Consonant("ƙ", Sound.Voice.Minus, Sound.Place.Velar, Sound.Manner.Implosive);
		ƙ.Sonority = 1;
		Consonant ɠ = new Consonant("ɠ", Sound.Voice.Plus, Sound.Place.Velar, Sound.Manner.Implosive);
		ɠ.Sonority = 2;
		Consonant ʠ = new Consonant("ʠ", Sound.Voice.Minus, Sound.Place.Uvular, Sound.Manner.Implosive);
		ʠ.Sonority = 1;
		Consonant ʛ = new Consonant("ʛ", Sound.Voice.Plus, Sound.Place.Uvular, Sound.Manner.Implosive);
		ʛ.Sonority = 2;
		Consonant ʘ = new Consonant("ʘ", Sound.Voice.Minus, Sound.Place.Bilabial, Sound.Manner.Click);
		ʘ.Sonority = 11;
		Consonant ǀ = new Consonant("ǀ", Sound.Voice.Minus, Sound.Place.Dental, Sound.Manner.Click);
		ǀ.Sonority = 11;// check sonority of clicks
		Consonant ǁ = new Consonant("ǁ", Sound.Voice.Minus, Sound.Place.Lateral, Sound.Manner.Click);
		ǁ.Sonority = 11;
		Consonant ǃ = new Consonant("ǃ", Sound.Voice.Minus, Sound.Place.Retroflex, Sound.Manner.Click);
		ǃ.Sonority = 11;
		Consonant ǂ = new Consonant("ǂ", Sound.Voice.Minus, Sound.Place.Palatal, Sound.Manner.Click);
		ǂ.Sonority = 11;
		Consonant ʍ = new Consonant("ʍ", Sound.Voice.Minus, Sound.Place.Velar, Sound.Manner.Approximant);
		ʍ.Sonority = 7;
		ʍ.diacritics = Diacritics.Labialized;
		Consonant w = new Consonant("w", Sound.Voice.Plus, Sound.Place.Velar, Sound.Manner.Approximant);
		w.Sonority = 10;
		w.diacritics = Diacritics.Labialized;
		Consonant ɥ = new Consonant("ɥ", Sound.Voice.Plus, Sound.Place.Palatal, Sound.Manner.Approximant);
		ɥ.Sonority = 7;
		ɥ.diacritics = Diacritics.Labialized;
		Consonant ɕ = new Consonant("ɕ", Sound.Voice.Minus, Sound.Place.AlveoloPalatal, Sound.Manner.Fricative);
		ɕ.Sonority = 5;
		Consonant ʑ = new Consonant("ʑ", Sound.Voice.Plus, Sound.Place.AlveoloPalatal, Sound.Manner.Fricative);
		ʑ.Sonority = 5;
		Consonant ɺ = new Consonant("ɺ", Sound.Voice.Plus, Sound.Place.DentalToPalatoAlveolar, Sound.Manner.LateralFlap);
		ɺ.Sonority = 5;// check sonority
		// Consonant ʦ = new Consonant("ʦ", Sound.Voice., Sound.Place.,
		// Sound.Manner.); ʦ.Sonority = ; //TODO: Not sure what to do about
		// affricates
		// Consonant ʣ = new Consonant("ʣ", Sound.Voice., Sound.Place.,
		// Sound.Manner.); ʣ.Sonority = ;
		// Consonant ʧ = new Consonant("ʧ", Sound.Voice., Sound.Place.,
		// Sound.Manner.); ʧ.Sonority = ;
		// Consonant ʤ = new Consonant("ʤ", Sound.Voice., Sound.Place.,
		// Sound.Manner.); ʤ.Sonority = ;
		// Consonant ʨ = new Consonant("ʨ", Sound.Voice., Sound.Place.,
		// Sound.Manner.); ʨ.Sonority = ;
		// Consonant ʥ = new Consonant("ʥ", Sound.Voice., Sound.Place.,
		// Sound.Manner.); ʥ.Sonority = ;
		consonants = new ArrayList<Consonant>(Arrays.asList(p, b, t, d, ʈ, ɖ, c, ɟ, k, ɡ, q, ɢ, ʡ, ʔ, m, ɱ, n, ɳ, ɲ, ŋ, ɴ, ɸ, β, f, v, θ, ð, s, z, ʃ, ʒ, ʂ, ʐ, ç, ʝ, x, ɣ, χ, ʁ, ħ, ʕ, ʜ, ʢ, h, ɦ, ʙ, ⱱ, r, ɾ, ɽ, ʀ, ʋ, ɹ, ɻ, j, ɰ, ɬ, ɮ, l, ɭ, ʎ, ʟ, ƥ, ɓ, ƭ, ɗ, ƈ, ʄ, ƙ, ɠ, ʠ, ʛ, ʘ, ǀ, ǁ, ǃ, ǂ, ʍ, w, ɥ, ɕ, ʑ, ɺ));// ʦ,ʣ,ʧ,ʤ,ʨ,ʥ;
		//return consonants;
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
		for (Consonant c : consonants)
		{
			returnable+= c+" ";
		}
		return "Consonants:"+returnable;
	}
	
}
