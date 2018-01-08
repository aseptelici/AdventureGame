/*
*	Room PROGRAM
*
*	Program that creates a 5*5 Room object 
*	with 4 closed doors.
*	Program for Adventure Game program, 
*	used through Warehouse program.
*
*	ANDREEA PERRY-GARDNER
*	24/11/2017
*	modified 11/12/2017
*	UNIVERSITY OF LIVERPOOL, UK
*/

import java.util.LinkedList;

public class Room
{
	// holding info about doors:
	public Boolean doorN = false;
	public Boolean doorE = false;
	public Boolean doorS = false;
	public Boolean doorW = false;
	
	// saving no of doors:
	public int totDoors = 0;
	
	// list containing items:
	public LinkedList<Item> items = new LinkedList<Item>();
	
	// tot no of items:
	public int noOfItems = 0;
	
	
	
	// Methods that deal with doors:
	
	// method that adds door to room:
	public void addDoor(String door)
	{
		// if door is in the north:
		if (door == "N")
		{
			// boolean becomes true:
			doorN = true;
			
			// increment no of doors:
			totDoors++;
		}
		
		// similarly for the rest:
		else if (door == "E")
		{
			doorE = true;
			totDoors++;
		}
		
		else if (door == "S")
		{
			doorS = true;
			totDoors++;
		}
		
		else if (door == "W")
		{
			doorW = true;
			totDoors++;
		}
	}
	
	
	// method that gets no of doors:
	public int getNoOfDoors()
	{
		return totDoors;
	}
	
	
	// overwriting toString method:
	public String toString()
	{
		// output starts empty:
		String output = "";
		
		// if there is a door in the N:
		output += "--" + ((doorN) ? "D" : "-") + "--\n";	// print D for door, wall otherwise
		
		// print second layer of room:
		output += "|   |\n";
		
		// third layer of room with east-west doors:
		output += (doorW ? "D" : "|") + "   ";
		output += (doorE ? "D" : "|") + "\n";
		
		// last layer of side walls:
		output += "|   |\n";
		
		// south wall:
		output += "--" + (doorS ? "D" : "-") + "--\n";	// similarly to above
		
		// return the room as string:
		return output;
	}
	
	
	// listing doors:
	public void whatDoors()
	{
		if (doorN == true)
		{
			System.out.println("There is a door going north");
		}
		
		if (doorE == true)
		{
			System.out.println("There is a door going east");
		}
		
		if (doorS == true)
		{
			System.out.println("There is a door going south");
		}
		
		if (doorW == true)
		{
			System.out.println("There is a door going west");
		}
	}
	
	
	// Methods that deal with items:
	
	// add object to room:
	public void addItem()
	{
		Item item = new Item();
		items.add(item);
		
		// increasing no of items in room:
		noOfItems++;
	}
	
	
	// telling the user items in room:
	public void whatItems()
	{
		for (int i = 0; i < items.size(); i++)
		{
			System.out.println("There is a " + items.get(i).getName() + " here" + items.get(i).getStats());
		}
	}
	
	
	// printing a list of items:
	public void getAllItems()
	{
		for (int i = 0; i < items.size(); i++)
		{
			int itemNo = i + 1;
			System.out.println(itemNo + ": " + items.get(i));
		}
	}
}