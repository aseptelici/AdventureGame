/*
*	Adventure Game PROGRAM
*
*	Program that uses Item, Hero, Room and Warehouse programs,
*	to generate an adventure game.
*	Accepting commands from user and executing.
*
*	ANDREEA PERRY-GARDNER
*	11/12/2017
*	UNIVERSITY OF LIVERPOOL, UK
*/


import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class AdventureGame
{
	// initialising here to be used by all methods:
	
	// variables holding info about position of hero:
	private int heroRow;
	private int heroColumn;
	private Room currentRoom = new Room();
	
	// creating the dungeon:
	private Warehouse dungeon = new Warehouse();
	
	// creating the hero:
	private Hero hero = new Hero();
	
	private Scanner keyboard = new Scanner(System.in);
	
	
	// constructor:
	private AdventureGame()
	{
		System.out.println("\nStarting the game ...");
		System.out.println("\nGoal: Maximize value of backpack.");
		
		// printing control info at start:
		help();

		// place hero in a room:
		placeHeroInRoom();
		
		// ask user for instructions:
		askInstructions();
	}
	
	
	// placing hero in a random room:
	private void placeHeroInRoom()
	{
		// random row and column:
		heroRow = (int)(Math.random() * Warehouse.size);
		heroColumn = (int)(Math.random() * Warehouse.size);
		
		// print room details:
		printRoomDetails();
	}
	
	
	// print details about current room:
	private void printRoomDetails()
	{
		System.out.println("\nYou are in room (" + (heroRow+1) + ", " + (heroColumn+1) + ")");
		
		// avoiding long code repetition:
		currentRoom = dungeon.rooms[heroRow][heroColumn];
		
		// print info about doors:
		currentRoom.whatDoors();
		
		// print info about items:
		currentRoom.whatItems();
	}
	
	
	// asking for instructions:
	private void askInstructions()
	{
		System.out.print("\nWhat would you like to do? ");	
		
		// getting value from user:
		String instruction = keyboard.next();
		
		
		// if instruction n, s, e, w:
		if (instruction.equals("n") || instruction.equals("e") 
			|| instruction.equals("s") || instruction.equals("w"))
		{
			// move hero:
			move(instruction);
		}
		
		// if q:
		else if (instruction.equals("q"))
		{
			// exit game:
			System.exit(1);
		}
		
		// if p:
		else if (instruction.equals("p"))
		{
			// if there are items in room:
			if (currentRoom.items.size() > 0)
			{
				// show pick up items menu:
				pickItem();
			}
			// else alert user:
			else
			{
				System.out.println("\nThere are no items in this room.");
			}
		}
		
		// if d:
		else if (instruction.equals("d"))
		{
			// if there are items in backpack:
			if (hero.backpack.size() > 0)
			{
				// drop items menu:
				dropItem();
			}
			// else alert user:
			else
			{
				System.out.println("\nYour backpack is empty.");
			}
		}
		
		// if i:
		else if (instruction.equals("i"))
		{
			// show items in backpack:
			hero.showBackpack();
		}
		
		// if h:
		else if (instruction.equals("h"))
		{
			// show help menu with controls:
			help();
		}
		
		// if m:
		else if (instruction.equals("m"))
		{
			// print dungeon:
			showMap();
		}
		
		
		// if not directions or exit:
		if (!instruction.equals("n") || !instruction.equals("e") || !instruction.equals("s") 
			|| !instruction.equals("w") || !instruction.equals("q"))
		{
			// ask instructions again after executing:
			askInstructions();
		}
	}
	
	
	
	// move method:
	private void move(String direction)
	{	
		// in north picked:
		if (direction.equals("n"))
		{
			// check if there is a door:
			if (currentRoom.doorN == true)
			{
				// move in that direction:
				heroRow--;
				printRoomDetails();
			}
			// if no door alert user:
			else
			{
				System.out.println("You can't go that way.");
			}
		}
		
		// if east picked, do the same:
		else if (direction.equals("e"))
		{
			if (currentRoom.doorE == true)
			{
				heroColumn++;
				printRoomDetails();
			}
			else
			{
				System.out.println("You can't go that way.");
			}
		}
		
		// if south picked, do the same:
		else if (direction.equals("s"))
		{
			if (currentRoom.doorS == true)
			{
				heroRow++;
				printRoomDetails();
			}
			else
			{
				System.out.println("You can't go that way.");
			}
		}
		
		// if west picked, do the same:
		else if (direction.equals("w"))
		{
			if (currentRoom.doorW == true)
			{
				heroColumn--;
				printRoomDetails();
			}
			else
			{
				System.out.println("You can't go that way.");
			}
		}
	}
	
	
	// method to pick up item:
	private void pickItem()
	{
		// print items in room:
		System.out.println("\nOK. Here are the items you can pick up:");
		currentRoom.getAllItems();
			
		int pickedItem;
			
		System.out.print("Which item would you like? ");
		
		try
		{
			// next int is the item from list at position-1 in list:
			pickedItem = keyboard.nextInt() - 1;
			
			// if the number typed is in no of items range:
			if (pickedItem >= 1 || pickedItem <= currentRoom.noOfItems)
			{
				// add item to backpack:
				hero.addItem(currentRoom.items.get(pickedItem));
				
				// if item was picked up:
				if (hero.itemPicked == true)
				{
					// let user know:
					System.out.println("\nOK. Item taken.");
					
					// remove item from room:
					currentRoom.items.remove(pickedItem);
				}
			}
			// else item picked doesn't exist
			else
			{
				System.out.println("\nNo such item.");
			}
		}
		
		// catch exceptions:
		catch (IndexOutOfBoundsException | InputMismatchException exception)
		{
			System.out.println("\nNo such item.");
			
			/* delete entry if char typed,
			to avoid char being used next: */
			String deleteBadEntry = keyboard.nextLine();
		}
	}
	
	
	// drop item method:
	private void dropItem()
	{	
		int pickedItem;
		
		// show options to drop:
		hero.showBackpack();
		
		System.out.print("Which item would you like to drop? ");
		
		try
		{
			// position in list is picked -1:
			pickedItem = keyboard.nextInt() - 1;
			
			// if no exists:
			if (pickedItem >= 1 || pickedItem <= currentRoom.noOfItems)
			{
				// add item back in the room:
				currentRoom.items.addLast(hero.backpack.get(pickedItem));
				
				// remove item from backpack:
				hero.removeItem(pickedItem);
			
				System.out.println("\nOK. Item dropped.");
			}
			else
			{
				System.out.println("\nNo such item.");
			}
		}
		catch (IndexOutOfBoundsException | InputMismatchException exception)
		{
			System.out.println("\nNo such item.");
			
			/* delete entry if char typed,
			to avoid char being used next: */
			String deleteBadEntry = keyboard.nextLine();
		}
	}
	
	
	// help menu:
	private void help()
	{
		// telling user available commands:
		System.out.println("\nCommands:" + "\nn,e,s,w: directions;" + "\ni: inventory;"
		+ "\np: pick up item;" + "\nd: drop item;" + "\nm: show map;" + "\nh: help;" + "\nq: quit;");
	}
	
	
	// printing map of dungeon:
	private void showMap()
	{
		dungeon.printWarehouse();
	}
	
	
	// main running method:
	public static void main(String[] args)
	{
		AdventureGame game1 = new AdventureGame();
	}
}