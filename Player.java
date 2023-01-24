/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This object represents each player and it holds all of their information
 */

import java.util.*;

public class Player {
	//variables
	private Scanner input = new Scanner (System.in);
	public String name;
	public String symbol;
	public int currentSquare;

	public static int[][] mapCoors = {
		{0,0}, {0,1}, {0,2}, {0,3}, {0,4}, {0,5}, {0,6},
		{1,6}, {2,6}, {3,6}, {4,6}, {5,6}, {6,6}, 
		{6,5}, {6,4}, {6,3}, {6,2}, {6,1}, {6,0},
		{5,0}, {4,0}, {3,0}, {2,0}, {1,0}
	}; //array of every possible position the player can be in, ordered

	public ProjectCard[] inventory;
	public int cash;
	public boolean jail;
	
	//constructors
	public Player (String newName, String newSymbol, int newCash) {
		name = newName;
		symbol = newSymbol;
		currentSquare = 0;
		inventory = new ProjectCard [0];
		cash = newCash;
		jail = false;
	}

	public Player (String newName, String newSymbol) {
		name = newName;
		symbol = newSymbol;
		currentSquare = 0;
		inventory = new ProjectCard [0];
		cash = 1000;
		jail = false;
	}
	
	/* Pre: Null
	 * Post: void
	 * Action: Returns the coordinates of the player*/
	public int[] getPosition () {
		return mapCoors[currentSquare];
	}

	/* Pre: int money
	 * Post: void
	 * Action: Modify the player's money amount*/
	public void getCash (int money) {
		cash += money;
		System.out.println("(" + name + ", your bank account now has $" + cash + ").\n\n[Press Enter to Continue]");
		input.nextLine();
	}

	/* Pre: MonopolyMap gameMap, ProjectCard card
	 * Post: void
	 * Action: Find the project square associated with a card*/
	public ProjectSquare findCardsSquare (MonopolyMap gameMap, ProjectCard card) {
		int[] coors = new int [2];
		
		for (int i = 1; i < mapCoors.length; i++) {
			if (gameMap.map[mapCoors[i][0]][mapCoors[i][1]].name.equals(card.name)) {
				coors = mapCoors[i];
			}
		}
		return (ProjectSquare)gameMap.map[coors[0]][coors[1]];
	}

	/* Pre: Null
	 * Post: void
	 * Action: Give the user $200 for passing "Go"*/
	public void passGo () {
		System.out.println("Congrats on making it around the board! Please take $200!");
		getCash(200);
	}

	/* Pre: Null
	 * Post: void
	 * Action: Puts the user in the jail square and updates their status as "in jail"*/
	public void goToJail () {
		currentSquare = 6;
		jail = true;
	}

	/* Pre: Null
	 * Post: void
	 * Action: Shows whether they are in jail or not. If they are in jail, lets them out for next round.*/
	public boolean isInJail () {
		boolean currentJailState = jail;
		jail = false;

		return currentJailState;
	}

	/* Pre: Null
	 * Post: void
	 * Action: Displays all of the contents of the user's inventory*/
	public void showInventory () {
		for (int j = 0; j < inventory.length; j++) {
			System.out.println((j+1) + ") " + inventory[j]);
		}
	}

	/* Pre: ProjectCard card
	 * Post: void
	 * Action: Adds a project card and updates the player's inventory*/
	public void addInventory(ProjectCard card) {
		//creates a new array that can fit the new item
        ProjectCard[] newArr = new ProjectCard [inventory.length + 1];

		//copies everything over
        for(int i = 0; i < inventory.length; i++) {
            newArr[i] = inventory[i];
        }
		//then adds the new item at the end
        newArr[inventory.length] = card;

		//sorts the inventory and makes the new inventory the current inventory
		sortInventory(newArr, newArr.length);

        inventory = newArr;
    }

	/* Pre: ProjectCard card
	 * Post: void
	 * Action: Removes a project card and updates the player's inventory*/
	public void removeInventory(ProjectCard card) {
		//creates a new array that can compensate for the removal of an item
        ProjectCard[] newArr = new ProjectCard [inventory.length - 1];
		int newInvIndex = 0;
		//duplicates everything into the new array except for the undesired card
        for(int i = 0; i < inventory.length; i++) {
			if (!inventory[i].equals(card)) {
				newArr[newInvIndex] = inventory[i];
				newInvIndex++;
			} 
        }

		//sorts the inventory and makes the new inventory the current inventory
		sortInventory(newArr, newArr.length);

        inventory = newArr;
        
        System.out.println(card.name + " has been removed from your list of project.");
    }

	/* Pre: ProjectCard[] cardArr, int invenLength
	 * Post: void
	 * Action: Recursively sorts the player's inventory using some modified gnome sort*/
	public void sortInventory(ProjectCard[] cardArr, int invenLength) {
		//swaps current and next element if out of order
		for (int i = 0; i < invenLength - 1; i++) {
			if (cardArr[i].toString().compareTo(cardArr[i + 1].toString()) < 0 ) {
				ProjectCard temp = cardArr[i];
				cardArr[i] = cardArr[i + 1];
				cardArr[i + 1] = temp;
			}
		}
	
		//continues to sort until we get to the first index of the inventory
		if (invenLength - 1 > 1) {
			sortInventory(cardArr, invenLength - 1);
		}
	}

	/* Pre: String colour
	 * Post: boolean
	 * Action: Checks if the player has collected all of a certain colour of project*/
	public boolean hasAllColour (String colour) {
		int numOfColours = 3;

		//yellow only has 2 cards
		if (colour.equalsIgnoreCase("yellow")) {
			numOfColours = 2;
		}

		//count how many times this certain colour shows up in their inventory
		int colourCounter = 0;
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].colour.equalsIgnoreCase(colour)) {
				colourCounter++;
			}
		}
		
		//if they have all of a card colour, their cards are upgraded
		if (colourCounter == numOfColours) {
			for (int i = 0; i < inventory.length; i++) {
				if (inventory[i].colour.equalsIgnoreCase(colour)) {
					inventory[i].upgrade();
				}
			}
			return true;
		}

		return false;
	}

	/* Pre: MonopolyMap gameMap
	 * Post: void
	 * Action: Allows the player to roll dice for a number of moves*/
	public void rollDice (MonopolyMap gameMap) {
		Random rand = new Random();

		System.out.println("Press enter to roll the dice. [Press Enter To Continue]");
		input.nextLine();

		//2 dice!
		int dice1 = rand.nextInt(6)+1;
		printDice(dice1);
		int dice2 = rand.nextInt(6)+1;
		printDice(dice2);

		int total = dice1+dice2;
		System.out.println("The number your rolled is... " + total + "! [Press Enter To Continue]");
		input.nextLine();

		//if they make it to the end of the map, they loop around again and pass go
		if ((currentSquare + total) > mapCoors.length-1) {
			currentSquare = currentSquare + total - mapCoors.length;
			passGo();
		} else {
			currentSquare += total;
		}

	}
	
	/* Pre: int number
	 * Post: void
	 * Action: Displays ascii versions of the dice the player just rolled*/
	public void printDice (int number) {
		//prints a different dice defpending on the number passed
		System.out.println("┌─────────┐");
		if (number == 1) {
			System.out.println("│         │");
			System.out.println("│    o    │");
			System.out.println("│         │");
		} else if (number == 2) {
			System.out.println("│      o  │");
			System.out.println("│         │");
			System.out.println("│  o      │");
		} else if (number == 3) {
			System.out.println("│      o  │");
			System.out.println("│    o    │");
			System.out.println("│  o      │");
		} else if (number == 4) {
			System.out.println("│  o   o  │");
			System.out.println("│         │");
			System.out.println("│  o   o  │");
		} else if (number == 5) {
			System.out.println("│  o   o  │");
			System.out.println("│    o    │");
			System.out.println("│  o   o  │");
		} else {
			System.out.println("│  o   o  │");
			System.out.println("│  o   o  │");
			System.out.println("│  o   o  │");
		}
		System.out.println("└─────────┘");
	}

}
