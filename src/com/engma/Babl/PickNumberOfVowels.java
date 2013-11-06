package com.engma.Babl;

import java.util.Properties;
import java.util.Random;

public class PickNumberOfVowels
{
	int numberOfVowels = 0;
	public  PickNumberOfVowels(Properties prop)
	{
		Random rand = new Random();
		// http://stackoverflow.com/questions/5853187/skewing-java-random-number-generation-toward-a-certain-number
		int lowEndVows = Integer.parseInt(prop.getProperty("minimumVowels"));
		int highEndVows = Integer.parseInt(prop.getProperty("maximumVowels"));
		int normalVows = Integer.parseInt(prop.getProperty("normalVowels"));
		int rangeAboveNormal = highEndVows - normalVows;// 60
		int rangeBelowNormal = normalVows - lowEndVows;// 20

		double range = highEndVows - lowEndVows;
		// System.out.println(range);
		double unitGaussian = rand.nextGaussian();
		double biasFactor = Math.exp((rangeAboveNormal - rangeBelowNormal) / 40);// TODO:
		// Explore
		// bias
		double skew = 1;// the higher this is, the closer the number is to the
		// normalCons value. Even 5 is unacceptably high.
		while (numberOfVowels > highEndVows || numberOfVowels < lowEndVows)
		{
			numberOfVowels = (int) (normalVows + (range * (biasFactor / (biasFactor + Math.exp(-unitGaussian / skew)) - 0.5)));
		}
	}
	@Override public String toString()
	{
		return "V#="+numberOfVowels;
	}
	
}
