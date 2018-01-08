/*
*	Warehouse PROGRAM
*
*	Program that uses Room program
*	in order to create a warehouse of 8*8 rooms.
*	The program creates warehouse with 
*	random placed interconnected doors,
*	and gives information about location.
*
*	ANDREEA PERRY-GARDNER
*	24/11/2017
*	modified 11/12/2017
*	UNIVERSITY OF LIVERPOOL, UK
*/


import java.util.Random;


public class Warehouse
{
	// size=8 for AdventureGame:
	public static int size = 8;	// to be accessed from AdventureGame
	
	private static int row;
	private static int column;
	
	// used in methods that implement doors:
	private String door;
	private Boolean doorIsGood = false;
	
	// warehouse main arrays:
	public Room rooms[][];
	private Room tempRoom[][];
	
	// arrays used in printing warehouse:
	private String warehouse[][][];
	private String roomDivision[];
	
	
	// constructor method:
	public Warehouse()
	{
		createWarehouse();
		createDoors();
		scatterItems();
		warehouseToString();
	}
	
	
	// Methods that deal with doors:
	
	// randomise door:
	private String randDoor()
	{	
		int nr = (int)(Math.random() * 4);
		
		// door is saved in string:
		switch ( nr )
		{
			case 0: door = "N";
					break;
			case 1: door = "E";
					break;
			case 2: door = "S";
					break;
			case 3: door = "W";
					break;
		}
		return door;
	}
	
	
	// check if the door is good:
	private void checkRandDoor(Room room)
	{
		// if the random door is N:
		if (door == "N")
		{
			/* check if it can be placed on the row
			   check if the door already exists: */
			if (row == 0 || room.doorN == true)
			{
				// if not good, return false:
				doorIsGood = false;
			}
			else
			{
				// if good return true:
				doorIsGood = true;
			}
		}
		
		// similarly for the rest:
		else if (door == "E")
		{
			if(column == (size-1) || room.doorE == true)
			{
				doorIsGood = false;
			}
			else
			{
				doorIsGood = true;
			}
		}
		else if (door == "S")
		{
			if(row == (size-1) || room.doorS == true)
			{
				doorIsGood = false;
			}
			else
			{
				doorIsGood = true;
			}
		}
		else if (door == "W")
		{
			if(column == 0 || room.doorW == true)
			{
				doorIsGood = false;
			}
			else
			{
				doorIsGood = true;
			}
		}
	}
	
	
	// adding door to room:
	private void addRandDoor(Room room)
	{
		// do once:
		do
		{
			// randomise door:
			door = randDoor();
			
			// check if door can be placed:
			checkRandDoor(room);
		}
		// repeat if random door isn't good:
		while (doorIsGood == false);
		
		// add door to room:
		room.addDoor(door);
		
		// reset values for later use:
		door = "";
		doorIsGood = false;
	}
	
	
	// method that creates the warehouse:
	private void createWarehouse()
	{	
		// String array for printing:
		warehouse = new String[size][size][5];	// 5 is the number of room layers
		
		// warehouse:
		rooms = new Room[size+1][size+1];	
		
		// temp warehouse for updating doors:
		tempRoom = new Room[size+1][size+1];
		
		
		// assign empty rooms to each element:
		for(row = 0; row <= size; row++)	// creating larger array to avoid errors
		{
			for(column = 0; column <= size; column++)
			{
				rooms[row][column] = new Room();
				//rooms[row][column].addItem();
			}
		}
		
		// assign empty rooms to each element:
		for(row = 0; row <= size; row++)
		{
			for(column = 0; column <= size; column++)
			{
				tempRoom[row][column] = new Room();
			}
		}
	}
	
	
	// adding doors to rooms:
	private void createDoors()
	{	
		// once add doors, twice check if any isolated rooms:
		for(int times = 0; times < 2; times++)
		{
			for(row = 0; row < size; row++)
			{
				for(column = 0; column < size; column++)
				{
					// if no doors:
					if(rooms[row][column].totDoors < 2)
					{
						// add door:
						addRandDoor(rooms[row][column]);
					}
					
					// temp gets values of rooms:
					tempRoom[row][column] = rooms[row][column];	
					
					
					// Update rooms from left to right:
					
					// if the room has a door to E:
					if(rooms[row][column].doorE == true)
					{
						// update next room to W:
						tempRoom[row][column+1].addDoor("W");	// with the help of temp
						rooms[row][column+1] = tempRoom[row][column+1];
					}
					
					// similarly up-down:
					if(rooms[row][column].doorS == true)
					{
						tempRoom[row+1][column].addDoor("N");
						rooms[row+1][column] = tempRoom[row+1][column];
					}
					
					// checking if all prev doors have been updated:
					if(rooms[row][column].doorW == true)
					{
						rooms[row][column-1].addDoor("E");			
					}
					
					// similarly:
					if(rooms[row][column].doorN == true)
					{
						rooms[row-1][column].addDoor("S");			
					}
				}
			}	
		}
	}
	
	
	// adding 50 rand items to warehouse:
	public void scatterItems()
	{
		int randRow;
		int randColumn;

		int noOfItems = 50;
		
		// if there are still items to be added:
		while (noOfItems > 0)
		{
			// randomise a room in warehouse:
			randRow = (int)(Math.random() * Warehouse.size);
			randColumn = (int)(Math.random() * Warehouse.size);
			
			// add item to that room:
			rooms[randRow][randColumn].addItem();
			
			// dicrement no of items to be added:
			noOfItems--;
		}
	}
	
	
	
	// Methods to print warehouse:
	
	// divigind rooms into layers:
	private void divideRoom(Room room)
	{
		// add room to string:
		String roomAsString = room.toString();
		
		// take each layer until "\n":
		roomDivision = roomAsString.split("\\n");
	}
	
	// combining layers into warehouse string:
	private void warehouseToString()
	{
		for(row = 0; row < size; row++)
		{
			for(column = 0; column < size; column++)
			{
				// dividing each room:
				rooms[row][column].toString();
				divideRoom(rooms[row][column]);
				
				// placing into String array:
				for(int i = 0; i < 5; i++)
				{
					warehouse[row][column][i] = roomDivision[i];
				}
			}
		}
	}
	
	
	// printing warehouse:
	public void printWarehouse()
	{
		// for each row:
		for(row = 0; row < size; row++)
		{
			// for each layer of room:
			for(int i = 0; i < 5; i++)
			{
				// for each column:
				for(column = 0; column < size; column++)
				{
					// print:
					System.out.print(warehouse[row][column][i]);
					
					// if end of sequence:
					if (column == (size - 1))
					{
						// print line:
						System.out.println();
					}
				}
			}
		}
	}
}