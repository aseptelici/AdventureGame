/*
*	Hero PROGRAM
*
*	Program that generates and holds items for
*	the hero(user) for Adventure Game program.
*
*	ANDREEA PERRY-GARDNER
*	11/12/2017
*	UNIVERSITY OF LIVERPOOL, UK
*/


import java.util.LinkedList;

public class Hero
{
	// creating backpack:
	public LinkedList<Item> backpack = new LinkedList<Item> ();
	
	// no value for empty backpack:
	public int worth = 0;
	
	// total space of backpack:
	public int space = 50;
	
	// telling if the hero picked an item:
	public Boolean itemPicked = false;
	
	
	// picking up an item:
	public void addItem(Item newItem)
	{
		// if backpack is empty:
		if (backpack.size() == 0)
		{
			// item picked becomes first in list:
			backpack.addFirst(newItem);
			
			// item has been picked:
			itemPicked = true;
			
			// increasing worth with item value:
			worth += newItem.value;
			
			// decreasing space with item size:
			space -= newItem.size;
		}
		// else if item bigger that remaining space:
		else if (newItem.size > space)
		{
			// let the user know:
			System.out.println("\nThere is no space for this item.");
			
			// can't pick up item:
			itemPicked = false;
		}
		// other situation:
		else
		{
			// go through the backpack:
			for (int i = 0; i < backpack.size(); i++)
			{
				// if new item is smaller than the one inside:
				if (backpack.get(i).size > newItem.size)
				{
					// add item at that position:
					backpack.add(i, newItem);
					
					// item has been picked up:
					itemPicked = true;
					
					// increase worth:
					worth += newItem.value;
					
					// decrease space:
					space -= newItem.size;
					
					// get out of loop:
					break;
				}
				// if new item has the same size:
				else if (backpack.get(i).size == newItem.size)
				{
					// but if it has smaller value:
					if (backpack.get(i).value > newItem.value)
					{
						// do similar to above:
						backpack.add(i, newItem);
						itemPicked = true;
						worth += newItem.value;
						space -= newItem.size;
						break;
					}
				}	
				// if there aren;t more items to compare:
				else if (i == (backpack.size() - 1))
				{
					// add item at the end:
					backpack.addLast(newItem);
					
					// and do the same as above:
					itemPicked = true;
					worth += newItem.value;
					space -= newItem.size;
					break;
				}
			}
		}
	}
	
	
	// method that removes an item:
	public void removeItem(int i)
	{
		// the worth of backpack decreases:
		worth -= backpack.get(i).value;
		
		// the space increases:
		space += backpack.get(i).size;
		
		// remove item:
		backpack.remove(i);
	}
	
	
	// showing items in backpack:
	public void showBackpack()
	{
		// if the backpack is empty:
		if (backpack.size() == 0)
		{
			// tell the user:
			System.out.println("\nYour backpack is empty.");
		}
		else
		{
			System.out.println("\nHere are your items:");
			
			// showing the items in backpack:
			for (int i = 0; i < backpack.size(); i++)
			{
				int itemNo = i + 1;
				System.out.println(itemNo + ": " + backpack.get(i));	
			}
			
			System.out.println("Total worth of items is " + worth);
			System.out.println("Space left in your backpack is " + space);
		}
	}
}
