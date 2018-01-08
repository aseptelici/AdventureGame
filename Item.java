/*
*	Item PROGRAM
*
*	Program that generates an item for
*	Adventure Game program.
*	The program gets a random name from 
*	a file and generates random stats.
*
*	ANDREEA PERRY-GARDNER
*	11/12/2017
*	UNIVERSITY OF LIVERPOOL, UK
*/


import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;


public class Item
{
	// Item variables:
	public LinkedList<String> names = new LinkedList<String>();
	public String name;
	public int size;
	public int value;
	
	public Item()
	{
		// method that randomizes name:
		randName();
		
		// picking random stats:
		
		// size between 5 and 15:
		size = (int)(Math.random() * 11) + 5;
		// value between 1 and 20:
		value = (int)(Math.random() * 20) + 1;
	}
	
	
	// method that gets random name:
	public void randName()
	{
		// opening scanner:
		Scanner in;
		
		try
		{
			// reading from file:
			in = new Scanner(new File("names.txt"));
		}
		// catching exception if no file at path:
		catch (FileNotFoundException exception)
		{
			System.out.println("Cannot find file");
			return;
		}
		
		int i = 0;
		
		// while file has text:
		while (in.hasNextLine())
		{
			// add name to linked list:
			names.addLast(in.nextLine());
			// next position in list:
			i++;
		}
		
		// pick random from names list:
		name = names.get((int)(Math.random() * names.size()));
		
		// close scanner:
		in.close();
	}
	
	
	// To string methods:
	
	// adding int size to string:
	public String sizeToString()
	{
		switch (size)
		{
			case 5: return "5";
			case 6: return "6";
			case 7: return "7";
			case 8: return "8";
			case 9: return "9";
			case 10: return "10";
			case 11: return "11";
			case 12: return "12";
			case 13: return "13";
			case 14: return "14";
			case 15: return "15";
		}
		
		return "";
	}
	
	// adding int value to string:
	public String valueToString()
	{
		switch (value)
		{
			case 1: return "1";
			case 2: return "2";
			case 3: return "3";
			case 4: return "4";
			case 5: return "5";
			case 6: return "6";
			case 7: return "7";
			case 8: return "8";
			case 9: return "9";
			case 10: return "10";
			case 11: return "11";
			case 12: return "12";
			case 13: return "13";
			case 14: return "14";
			case 15: return "15";
			case 16: return "16";
			case 17: return "17";
			case 18: return "18";
			case 19: return "19";
			case 20: return "20";
		}
		
		return "";
	}
	
	
	// method that gets name:
	public String getName()
	{
		return name;
	}
	
	// method that gets size and value:
	public String getStats()
	{
		// the form needed in game:
		return " (size " + sizeToString() + ", value " + valueToString() + ")";
	}
	
	
	// main toString method for item:
	public String toString()
	{
		return getName() + getStats();
	}
}