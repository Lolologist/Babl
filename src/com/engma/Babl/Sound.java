package com.engma.Babl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sound implements Serializable{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2279602220622140963L;

	public enum Place {Bilabial, Labiodental, Dental, Alveolar, PalatoAlveolar, Retroflex, Palatal, Velar, Uvular, Pharyngeal, Glottal, LabialVelar, LabialPalatal, Epiglottal, AlveoloPalatal, DentalToPalatoAlveolar, AlveoloPalatalPalatal, DentalOrAlveolar, Lateral};
	public enum Manner {Plosive, Nasal, Trill, Tap, Fricative, LateralFricative, Approximant, LateralApproximant, Click, Implosive, Ejective, LateralFlap, FricativeApproximant};
	public enum VowelOpenness {Close, NearClose, CloseMid, Mid, OpenMid, NearOpen, Open};
	public enum VowelFrontedness {Front, NearFront, Central, NearBack, Back};
	public enum VowelRoundedness {Rounded, Unrounded};
	public enum Diacritics {Voiceless, Voiced, Aspirated, MoreRounded, LessRounded, Advanced, Retracted, Centralized, BreathyVoiced, CreakyVoiced, Linguolabial, Labialized, Palatalized, Velarized, Pharyngealized, VelarizedOrPharyngealized, Dental, Apical, Laminal, Nasalized, NasalRelease, LateralResease, NoAudibleRelease, MidCentralized, Syllabic, NonSyllabic, Rhoticity, Raised, Lowered, ATR, RTR};
	public enum ToneLevel {ExtraHigh, High, Mid, Low, ExtraLow}
	public enum Syllabic {Plus, Minus};
	public enum Consonantal {Plus, Minus};
	public enum Approximant {Plus, Minus};
	public enum Sonorant {Plus, Minus};
	public enum Voice {Plus, Minus};
	public enum SpreadGlottis {Plus, Minus};
	public enum ConstrictedGlottis {Plus, Minus};
	public enum Continuant {Plus, Minus};
	public enum Nasal {Plus, Minus};
	public enum Strident {Plus, Minus};
	public enum Lateral {Plus, Minus};
	public enum DelayedRelease {Plus, Minus};
	public enum LabialRound {Plus, Minus};
	public enum CoronalAnterior {Plus, Minus};
	public enum CoronalDistributed {Plus, Minus};
	public enum DorsalHigh {Plus, Minus};
	public enum DorsalLow {Plus, Minus};
	public enum DorsalBack {Plus, Minus};
	public enum DorsalTense {Plus, Minus};
	public enum Radical {Plus, Minus};
	public enum Laryngeal {Plus, Minus};
	//TODO: figure out what to do with tone contours
	int Sonority = -1;
	
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
