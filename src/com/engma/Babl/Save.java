package com.engma.Babl;

import java.io.*;
import java.util.*;

public class Save
{

	public Save(Dictionary dictionary) throws IOException, ClassNotFoundException
	{
		
		File f = new File("Resources\\dictionary.ser");
		if (f.exists())
		{
			//System.out.println("Deleting old dictionary.");
			f.delete();
			//System.out.println("Deleted old dictionary.");
		}

		
		// Write to disk with FileOutputStream
		FileOutputStream f_out = new FileOutputStream(f);

		// Write object with ObjectOutputStream
		ObjectOutputStream obj_out = new ObjectOutputStream(f_out);

		// Write object out to disk
		try {
			obj_out.writeObject(dictionary);
		} catch (Exception e)
		{
			System.out.println(e);
		}
		f_out.close();
		System.out.println("Dictionary probably saved correctly.");
		
		
		File file = new File("Resources\\dictionary.txt");
		if (file.exists())
		{
			//System.out.println("Deleting current dictionary.txt to ensure proper writing.");
			file.delete();
			//System.out.println("Deleted old dictionary.txt.");
		}
		
		Writer out = new BufferedWriter(new OutputStreamWriter(
			    new FileOutputStream("Resources\\dictionary.txt"), "UTF-8"));
		
//		FileWriter fw = new FileWriter(file.getAbsoluteFile());
//		BufferedWriter bw = new BufferedWriter(fw);
		
		Collection<Word> words =  dictionary.entries.values();
		for (Word word : words)
		{			
			out.write(word.word+":"+word.english+"::"+word.pos + " \r\n");
		}
		System.out.println("Readable dictionary saved to dictionary.txt");
		//bw.close();
		out.close();
	}
	
}
