package com.engma.Babl;

import java.util.Properties;
import java.util.Random;

public class PickNumberOfConsonants
{
	int numberOfConsonants = 0;
	
	public PickNumberOfConsonants(Properties prop)
	{
		Random rand = new Random();
		// http://stackoverflow.com/questions/5853187/skewing-java-random-number-generation-toward-a-certain-number
		int lowEndCons = Integer.parseInt(prop.getProperty("minimumConsonants"));
		int highEndCons = Integer.parseInt(prop.getProperty("maximumConsonants"));
		int normalCons = Integer.parseInt(prop.getProperty("normalConsonants"));
		// TODO: Once percentages are set, set the number of consonants. 6-81,
		// bell curve. Raw consonants. Nasalized, etc. comes later
		int rangeAboveNormal = highEndCons - normalCons;// 60
		int rangeBelowNormal = normalCons - lowEndCons;// 20

		double range = highEndCons - lowEndCons;
		double unitGaussian = rand.nextGaussian();
		double biasFactor = Math.exp((rangeAboveNormal - rangeBelowNormal) / 40);// TODO:
		// Explore
		// bias
		double skew = 1;// the higher this is, the closer the number is to the
		// normalCons value. Even 5 is unacceptably high.
		int retval = (int) (normalCons + (range * (biasFactor / (biasFactor + Math.exp(-unitGaussian / skew)) - 0.5)));
		if (retval > highEndCons || retval < lowEndCons)
		{
			//return PickNumberOfConsonants(prop);
		} else
		{
			numberOfConsonants = retval;
		}

	}
}
