/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This program simulates monopoly with a twist! (An ethical twist)
 */

import java.io.*;
import java.util.*;

public class ethicalopolyMain {

	/*DISCLAIMER: CODE DOES NOT WORK IN IDES THAT DO NOT SUPPORT ANSI ENCODING.
	 * PLEASE USE VSCODE OR ANY EQUIVALENT TO PROPERLY VIEW THE DISPLAY */
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		ChanceCardDeck chanceDeck = new ChanceCardDeck();
		ProjectCardDeck projectDeck = new ProjectCardDeck();
		File saveFile = new File ("ethicalopolySaveFile.txt");

		//prints the introduction!
		intro (input);

		//ask them if they want to go over the rules or not
		String rules = "";
		while (!(rules.equalsIgnoreCase("y") || rules.equalsIgnoreCase("n"))) {
			System.out.println("Do you need to know the rules of Ethicalopoly? (Y/N)");
			rules = input.nextLine();

			if (rules.equalsIgnoreCase("y")) {
				printInstructions(input);
			} else if (rules.equalsIgnoreCase("n")) {
				System.out.println("Onto Ethicalopoly then!!!");
			} else {
				System.out.println("That wasn't a valid answer. Please choose either Y or N.\n");
			}
		}

		//playing the game!!!
		System.out.println("--------------------------------------------------------------------------------------------\n");
		String play = "y";
		//allows for replayability if the user wants to
		do {
			MonopolyMap gameMap = new MonopolyMap();
			play = "";
		 	String winner = "";

		 	//get the players in the game
		 	Player[] playerArr = playerSetup(input, saveFile, gameMap, projectDeck);
		 	int numOfPlayers = playerArr.length;

		 	//place them on the map
		 	setupMap(gameMap, playerArr, numOfPlayers);

		 	//play the game!
		 	while (winner.equals("")) {
		 		winner = playerTurns(playerArr, numOfPlayers, gameMap, chanceDeck, projectDeck, input, winner, saveFile);
		 	}
			//display the winning sequence
			winGame(input, winner);

			//ask to play a game again
		 	while (!(play.equalsIgnoreCase("y") || 
		 			play.equalsIgnoreCase("n"))) {

		 		System.out.println("\n\nWould you like to play again? (Y/N)");
		 		play = input.nextLine();
		 		if (play.equalsIgnoreCase("Y")) {
		 			System.out.println("Let's play again!!!");
		 			System.out.println("--------------------------------------------------------------------------------------------\n");
		 		} else if (play.equalsIgnoreCase("N")) {
		 			System.out.println("THANK YOU SO MUCH FOR PLAYING ETHICALOPOLY!! BYEEE <33");
		 		} else {
		 			System.out.println("Please enter either Y or N.");
				}
			}
		} while (play.equalsIgnoreCase("y"));
	}

	/* Pre: Player[] playerArr, File saveFile, MonopolyMap gameMap
	 * Post: void
	 * Action: Save the current player's statuses to a .txt file*/
	public static void saveFile (Player[] playerArr, File saveFile, MonopolyMap gameMap) {
		//make the save file if it doesn't exist
		if (!saveFile.exists()) {
			try {
				saveFile.createNewFile(); 
			} catch (IOException e){
				System.out.println("i... i can't... make it.... NYOOOOOOOOO!!!");
				System.err.println("The problem:" + e.getMessage());
			} 
		}
		try {
			FileWriter out = new FileWriter(saveFile);
			BufferedWriter writeInfo = new BufferedWriter(out);

			//goes through every player and writes down their name, symbol, and cash
			for (int i = 0; i < playerArr.length; i++) {
				Player currentPlayer = playerArr[i];
				String info = (currentPlayer.name + "," + currentPlayer.symbol + "," + currentPlayer.cash + "|");
				
				//also, it saves their inventory
				for (int j = 0; j < currentPlayer.inventory.length; j++) {
					info += findCardCoors(currentPlayer, currentPlayer.inventory[j], gameMap) + ":" + currentPlayer.inventory[j].name;
					if (j < currentPlayer.inventory.length-1) {
						info += ",";
					}
				}
				//write info on its own line
				writeInfo.write(info);
				writeInfo.newLine();

			}
            writeInfo.close();
        } catch (IOException e) {
            System.err.println("INPUT OUTPUT NO WORKIES: " + e);
        }

		System.out.println("Your file has been saved!");
	}

	/* Pre: Player player, ProjectCard card, MonopolyMap gameMap
	 * Post: String
	 * Action: Helps to save data by grabbing the coordinates of each project card's square*/
	public static String findCardCoors(Player player, ProjectCard card, MonopolyMap gameMap) {
		String coors = "";

		for (int i = 0; i < Player.mapCoors.length; i++) {
			if (gameMap.map[Player.mapCoors[i][0]][Player.mapCoors[i][1]].name.equals(card.name)) {
				coors = "" + Player.mapCoors[i][0] + Player.mapCoors[i][1];
			}
		}

		return coors;
	}

	/* Pre: Scanner input, File saveFile, MonopolyMap gameMap, ProjectCardDeck projectDeck
	 * Post: Player[]
	 * Action: Loads an array of all the saved players from a .txt file*/
	public static Player[] loadFile (Scanner input, File saveFile, MonopolyMap gameMap, ProjectCardDeck projectDeck) {
		Player[] playerArr = new Player [0];
			try {
				//READ THE FILE
				FileReader in = new FileReader(saveFile);
				BufferedReader readFile = new BufferedReader(in);
				String lineOfText;
				while ((lineOfText = readFile.readLine()) != null) {  //Condition holds true until it reaches the end of the file
					String[] attributeArr = lineOfText.substring(0, lineOfText.indexOf('|')).split(",");

					//links each attribute to its respective index in attribute arrays and uses it to declare a new player
					String newName = attributeArr[0];
					String newSymbol = attributeArr[1];
					int newCash = Integer.parseInt(attributeArr[2]);
					
					//makes a player
					Player newPlayer = new Player(newName, newSymbol, newCash);

					//gives them their stuff back in theinventory
					if (!lineOfText.substring((lineOfText.indexOf("|"))).equals("|")) {
						String[] inventoryArr = lineOfText.substring((lineOfText.indexOf("|"))+1).split(",");
						for (int i = 0; i < inventoryArr.length; i++) {
							String coorString = inventoryArr[i].substring(0, inventoryArr[i].indexOf(":"));
							int coor1 = Integer.parseInt(coorString.substring(0, 1));
							int coor2 = Integer.parseInt(coorString.substring(1));
							GameSquare square = gameMap.map[coor1][coor2];
							ProjectCard card = projectDeck.findSquaresCard(square);
							newPlayer.addInventory(card);
							((ProjectSquare)square).buy(newPlayer);
						}
					}
					
					//adds them to the player array
					Player[] newPlayerArr = new Player [playerArr.length + 1];
					for (int i = 0; i < playerArr.length; i++) {
						newPlayerArr[i] = playerArr[i];
					}
					newPlayerArr[playerArr.length] = newPlayer;
					playerArr = newPlayerArr;	
				}

				readFile.close();
				in.close();
			} catch (IOException e) {
				System.err.println("INPUT OUTPUT NO WORKIES: " + e);
			}

		return playerArr;
	}

	/* Pre: Scanner input
	 * Post: void
	 * Action: Prints the introduction sequence to the program*/
	public static void intro (Scanner input) {
		System.out.println("DISCLAIMER: THIS PROGRAM ONLY WORKS IN IDES THAT SUPPORT ANSI CHARACTERS.");
		System.out.println("DO NOT USE THIS IN ECLIPSE. PLEASE USE IT IN VSCODE OR ANOTHER COMPATIBLE IDE.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();

		//let them adjust their terminal to fit the game
		System.out.println("--------------------------------------------------------------------------------------------\n");
		for (int i = 0; i < 46; i ++) {
			System.out.println();
		}
		System.out.println("Please adjust the terminal until the horizontal line fits on your screen.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();

		//introduction stuffs
		System.out.println("--------------------------------------------------------------------------------------------\n");
		System.out.println("OWO? What's this?");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("Y-you are an aspiring SWE?");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("And you want to boost your resume for big tech companies at silicon valley?");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("LOOKS LIKE YOU'RE IN LUCK! WELCOME TO");
		System.out.println(
		" ______ _   _     _           _                   _       _ _ _ _ " + "\n" + 
		"|  ____| | | |   (_)         | |                 | |     | | | | |" + "\n" +
		"| |__  | |_| |__  _  ___ __ _| | ___  _ __   ___ | |_   _| | | | |" + "\n" +
		"|  __| | __| '_ \\| |/ __/ _` | |/ _ \\| '_ \\ / _ \\| | | | | | | | |" + "\n" +
		"| |____| |_| | | | | (_| (_| | | (_) | |_) | (_) | | |_| |_|_|_|_|" + "\n" +
		"|______|\\__|_| |_|_|\\___\\__,_|_|\\___/| .__/ \\___/|_|\\__, (_|_|_|_)" + "\n" +
		"\t\t\t\t     | |             __/ |" + "\n" +
		"\t\t\t\t     |_|            |___/ " + "\n\n\n"
		);

		System.out.println("[Press Enter To Continue]");
		input.nextLine();
	} 

	/* Pre: Scanner input
	 * Post: void
	 * Action: Prints the instruction sequence to the program*/
	public static void printInstructions (Scanner input) {
		System.out.println("Ethicalopoly is a multiplayer game! You can play with 2-4 people.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("Every player starts off with $1000. Their goal is to reach $2000!");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("There is a board where you can roll dice to move around on the map.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("If you land on a Project square, you can buy a project!");
		System.out.println("Green ones are $60, Pink ones are $100, Red ones are $250, and Yellow ones are $400.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("If other players land on a square you own, they have to pay you royalties.");
		System.out.println("The amount owed is dependent on the colour square you bought.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("The royalties are 1/5 of the original price.");
		System.out.println("You can also sell the project for 1/2 the original price.");	
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("However, if you get all of the projects of a certain colour,");
		System.out.println("then the royalties also become 1/2 of the original price!");
		System.out.println("So it's good to buy the same types of projects to maximize profits.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("Chance cards give you a chance to either gain or lose money.");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("Passing \"Go\" also gives the player $200!");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("And that is all~ Good luck to getting $2000!!");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
	}

	/* Pre: MonopolyMap gameMap, Player [] playerArr, int numOfPlayers
	 * Post: void
	 * Action: Sets up the monopoly board by establishing all of its squares*/
	public static void setupMap (MonopolyMap gameMap, Player [] playerArr, int numOfPlayers) {
		//everyone starts at "go"
		for (int i = 0; i < numOfPlayers; i++) {
			gameMap.map[0][0].movePlayers(playerArr[i], i);
		}
		
		gameMap.displayMap();
	}

	/* Pre: Player[] playerArr, int numOfPlayers, MonopolyMap gameMap, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input, String winner, File saveFile
	 * Post: String
	 * Action: iterates through every player and allows them to play the game. Returns the name of the winner if there is one*/
	public static String playerTurns (Player[] playerArr, int numOfPlayers, MonopolyMap gameMap, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input, String winner, File saveFile) {
		//iterates through every player
		for (int i = 0; i < numOfPlayers; i++) {
			Player currentPlayer = playerArr[i];
			
			System.out.println("It's your turn, " + currentPlayer.name + " (" + currentPlayer.symbol + ")!\n");

			//if they're in jail, they can't go!
			if (currentPlayer.isInJail()) {
				System.out.println("Except you're in jail and have to skip this turn! Onto the next person...");
				System.out.println("[Press Enter To Continue]");
				input.nextLine();
			} else {
				int inventoryLength = currentPlayer.inventory.length;
				if (inventoryLength > 0) { //if they have anything in their inventory at all
					String playerAns = "";

					//it's the menu! checks if they wrote a valid response
					while (!(playerAns.equals("1") || playerAns.equals("2") || playerAns.equals("3"))) {
						System.out.println("Would you like to...");
						System.out.println("1) View your projects\n" +
										"2) Save the game's current progress\n" +
										"3) Roll the dice");
										

						input = new Scanner (System.in);
						playerAns = input.nextLine();
						
						//displays the player's stats (money and inventory)
						if (playerAns.equals("1")) {
							System.out.println("\nBank Account: $" + currentPlayer.cash);
							currentPlayer.showInventory();

							String playerSell = "";
							//ask if they want to sell anything from their inventory
							while (!(playerSell.equalsIgnoreCase("y") || playerSell.equalsIgnoreCase("n"))) {
								System.out.println("Would you like to sell any projects? (Y/N)");

								input = new Scanner (System.in);
								playerSell = input.nextLine();

								if (playerSell.equalsIgnoreCase("y")) {
									sellProjects(currentPlayer, gameMap, input);
								} else if (playerSell.equalsIgnoreCase("n")) {
									System.out.println("Onto dice rolling, then!");
								} else {
									System.out.println("That is not a valid option. Please enter Y or N.\n");
								}
							}
						//save the data!
						} else if (playerAns.equals("2")) {
							System.out.println("Please be aware that this option only saves player stats and not board location!");
							System.out.println("Any future saves will also overwrite the file :)");
							saveFile (playerArr, saveFile, gameMap);
							System.out.println("[Press Enter To Continue]");
							input.nextLine();
						//retry upon invalid response
						} else if (!(playerAns.equals("1") || playerAns.equals("2") || playerAns.equals("3"))) {
							System.out.println("That is not a valid option. Please enter 1, 2, or 3.\n");
						}
					}
				}
				//let the player roll dice
				diceRolling(currentPlayer, gameMap, chanceDeck, projectDeck, input, i);
			}

			System.out.println("--------------------------------------------------------------------------------------------\n");
			if (winner.equals("") && currentPlayer.cash >= 2000) { //win condition
				winner = currentPlayer.name;
			}
		}
		return winner;
	}
	
	/* Pre: Player currentPlayer, MonopolyMap gameMap, Scanner input
	 * Post: void
	 * Action: Allows the player to sell a project from their inventory*/
	public static void sellProjects (Player currentPlayer, MonopolyMap gameMap, Scanner input) {

		String keepSelling = "";
		//keep looping as long as they want to keep selling
		do {
			int inventoryLength = currentPlayer.inventory.length;
			//display all possible sellable options
			for (int k = 0; k < inventoryLength; k++) {
				ProjectCard currentProperty = currentPlayer.inventory[k];
				System.out.println((k+1) + ") " + currentProperty.name + " ("+ currentProperty.colour + ")" + " - $" + currentProperty.royaltyFee);
			}
			System.out.println((inventoryLength+1) + ") Cancel\n");

			String soldProperty = "";
			int soldPropertyNum = 0;
			System.out.println("Please select which project number you wish to sell:");

			input = new Scanner (System.in);
			soldProperty = input.nextLine();
			
			System.out.println();

			//try catch to ensure the user can't break the program by entering a non-int
			try {
				soldPropertyNum = Integer.parseInt(soldProperty);
			} catch (NumberFormatException e) { //if it's not a number
				System.out.println("Your input... wasn't even a number...");
			}
			
			//checks if the user's input is valid
			if (soldPropertyNum <= 0 || soldPropertyNum > inventoryLength+1) {
				System.out.println("That is not a valid answer. Please choose a number from 1 to " + (inventoryLength+1) + ".\n");
				keepSelling = "y";
			} else if (soldPropertyNum == inventoryLength+1) {
				System.out.println("Onto dice rolling, then!");
				keepSelling = "n";
			} else {
				//SELL THE PROJECT :)))
				ProjectCard soldPropertyCard = currentPlayer.inventory[soldPropertyNum-1];
				currentPlayer.removeInventory(soldPropertyCard);
				currentPlayer.getCash(soldPropertyCard.sellingPrice);
				currentPlayer.findCardsSquare(gameMap, soldPropertyCard).sell(currentPlayer);
				keepSelling = ""; //reset the variable so the player can use it later if they want to keep selling
				
				//if they still have stuff to sell, ask them if they want to sell more
				if (currentPlayer.inventory.length > 0) {
					System.out.println("New Inventory:");
					currentPlayer.showInventory();
					while (!(keepSelling.equalsIgnoreCase("y") || keepSelling.equalsIgnoreCase("n"))) {
						System.out.println("Would you like to sell another project? (Y/N)");
						keepSelling = input.nextLine();
						System.out.println();
						if (!(keepSelling.equalsIgnoreCase("y") || keepSelling.equalsIgnoreCase("n"))) {
							System.out.println("That is not a valid option. Please enter Y or N.\n");
						}
					} 
				}
			} 
		} while (keepSelling.equalsIgnoreCase("y"));
	} 
	
	/* Player currentPlayer, MonopolyMap gameMap, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input, int i
	 * Post: void
	 * Action: Allows the player to roll dice and proceed in the gameboard*/
	public static void diceRolling (Player currentPlayer, MonopolyMap gameMap, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input, int i) {
		//moves the player according to the number they rolled on the dice
		gameMap.map[currentPlayer.getPosition()[0]][currentPlayer.getPosition()[1]].removePlayers(i);
		currentPlayer.rollDice(gameMap);
		gameMap.map[currentPlayer.getPosition()[0]][currentPlayer.getPosition()[1]].movePlayers(currentPlayer, i);
		gameMap.displayMap();

		//as they land on a square, they activate the square!
		GameSquare currentSquare = gameMap.map[currentPlayer.getPosition()[0]][currentPlayer.getPosition()[1]];
		System.out.println("You landed on... " + currentSquare.name + "! \n[Press Enter To Continue]");
		input.nextLine();
		currentSquare.squareAction(currentPlayer, currentSquare, chanceDeck, projectDeck, input);

		if (currentSquare.coors[0] == 6 && currentSquare.coors[1] == 0) { //if they landed on "go to jail"
			gameMap.map[0][6].movePlayers(currentPlayer, i); //sends the player to jail
			gameMap.map[6][0].removePlayers(i); //wipes them from the "go to jail" square
		}
		
	}

	/* Pre: Scanner input, File saveFile, MonopolyMap gameMap, ProjectCardDeck projectDeck
	 * Post: Player[]
	 * Action: Establishes an array of all the players playing*/
	public static Player[] playerSetup (Scanner input, File saveFile, MonopolyMap gameMap, ProjectCardDeck projectDeck) {
		Player[] playerArr = null;

		//if they choose to set up the game from a save file, then can do so!
		if (saveFile.exists()) {
			String save = "";

			System.out.println("There seems to be a save file!");
			while (!(save.equalsIgnoreCase("y") || save.equalsIgnoreCase("n"))) {
				System.out.println("Would you like to load up your last saved game? (Y/N)");
				save = input.nextLine();
				if (save.equalsIgnoreCase("y")) {
					System.out.println("Loading contents...");
					playerArr = loadFile(input, saveFile, gameMap, projectDeck);
					System.out.println("Your game has been loaded!");
					System.out.println("[Press Enter To Continue]");
					input.nextLine();
					return playerArr;
				} else if (save.equalsIgnoreCase("n")) {
					System.out.println("Off to a new game, then!");
				} else {
					System.out.println("That is not a valid answer. Please choose either Y or N.\n");
				}
			}
			
		}
		
		String playerCountInput = "";
		int playerCount = 0;

		//TIME TO SET UP THE NUMBER OF PEOPLE
		while (playerCount < 2 || playerCount > 4) {
			System.out.println("\nPlease input how many players are participating (2-4):");	
			playerCountInput = input.nextLine(); //it's a string to avoid errors
		
			try {
				playerCount = Integer.parseInt(playerCountInput);
			} catch (NumberFormatException e) { //if it's not a number
				System.out.println("Your input... wasn't even a number...");
			}
			
			if (playerCount < 2 || playerCount > 4) {
				System.out.println("That is not a valid number of players. Try again.");
			}
		}

		System.out.println();
		playerArr = new Player [playerCount];

		//time to fill out the array with player's names
		for (int i = 0; i < playerCount; i++) {
			String name = "";

			while (name.equals("")) {
				System.out.println("What is the name of Player #" + (i+1) + "?");
				name = input.nextLine();

				if (name.equals("")) {
					System.out.println("Please enter a name.\n");
				}
			}
			
			//capitalize the first letter of their name
			String firstLetterName = name.substring(0,1).toUpperCase();
			name = firstLetterName += name.substring(1);
			
			//assign them to a symbol
			System.out.print(name + ", you will play as ");
			if (i == 0) {
				playerArr[i] = new Player (name, "@");
				System.out.print("@");
			} else if (i == 1) {
				playerArr[i] = new Player (name, "#");
				System.out.print("#");
			} else if (i == 2) {
				playerArr[i] = new Player (name, "%");
				System.out.print("%");
			} else {
				playerArr[i] = new Player (name, "&");
				System.out.print("&");
			}

			System.out.println(" on the gameboard!\n");
		}

		System.out.println("[Press Enter To Begin The Game]");
		input.nextLine();

		return playerArr;
	}

	/* Pre: Scanner input, String winner
	 * Post: void
	 * Action: Prints the ending sequence to the program*/
	public static void winGame (Scanner input, String winner) {
		System.out.println("The game comes to an end... and the winner is...");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println(winner + "!!!!!!!!!!!");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();

		System.out.println("As a congratulations for your ethical programming pursuits...");
		System.out.println("I present to you...");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		
		System.out.println("    |\\      _,,,---,,_");
		for (int i = 0; i < 10; i ++) {
			System.out.println("   /,`.-'`'    -.  ;-;;,_\n" + 
							"  |,3   ) )-,_..;\\ (  `'-'\n" +
							" '---''(_/--'  `-'\\_)");
		}
		System.out.println("A CUTE STACK OF CATS!!");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();

		System.out.println("You really do deserve it! You did such a great job! *clap clap clap*");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();

		System.out.println(
			"                                     (" + "\n" + 
			"                      (" + "\n" + 
			"     )                    )             (" + "\n" + 
			"                  )           (o)    )" + "\n" + 
			"          (      (o)    )     ,|,            )" + "\n" + 
			"         (o)     ,|,          |~\\    (      (o)" + "\n" + 
			"         ,|,     |~\\    (     \\ |   (o)     ,|," + "\n" + 
			"         \\~|     \\ |   (o)    |`\\   ,|,     |~\\" + "\n" + 
			"         |`\\     |`\\@@@,|,@@@@\\ |@@@\\~|     \\ |" + "\n" + 
			"         \\ | o@@@\\ |@@@\\~|@@@@|`\\@@@|`\\@@@o |`\\" + "\n" + 
			"        o|`\\@@@@@|`\\@@@|`\\@@@@\\ |@@@\\ |@@@@@\\ |o" + "\n" + 
			"       o@@\\ |@@@@@\\ |@@@\\ |@@@@@@@@@@|`\\@@@@@|`\\@@o" + "\n" + 
			"     @@@@|`\\@@@@@@@@@@@|`\\@@@@@@@@@@\\ |@@@@@\\ |@@@@" + "\n" + 
			"     p@@@@@@@@@@@@@@@@@\\ |@@@@@@@@@@|`\\@@@@@@@@@@@q" + "\n" + 
			"     @@o@@@@@@@@@@@@@@@|`\\@@@@@@@@@@@@@@@@@@@@@@o@@" + "\n" + 
			"     @:@@@o@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@o@@::@" + "\n" + 
			"     ::@@::@@o@@@@@@@@@@@@@@@@@@@@@@@@@@@@o@@:@@::@" + "\n" + 
			"     ::@@::@@@@::oo@@@@oo@@@@@ooo@@@@@o:::@@@::::::" + "\n" + 
			"     %::::::@::::::@@@@:::@@@:::::@@@@:::::@@:::::%" + "\n" + 
			"     %%::::::::::::@@::::::@:::::::@@::::::::::::%%" + "\n" + 
			"     ::%%%::::::::::@::::::::::::::@::::::::::%%%::" + "\n" + 
			"   .#::%::%%%%%%:::::::::::::::::::::::::%%%%%::%::#." + "\n" + 
			"  .###::::::%%:::%:%%%%%%%%%%%%%%%%%%%%%:%:::%%:::::###." + "\n" + 
			" .#####::::::%:::::%%::::::%%%%:::::%%::::%::::::::::#####." + "\n" + 
			".######`:::::::::::%:::::::%:::::::::%::::%:::::::::'######." + "\n" + 
			".#########``::::::::::::::::::::::::::::::::::::''#########." + "\n" + 
			"`.#############```::::::::::::::::::::::::'''#############.'" + "\n" + 
			" `.######################################################.'" + "\n" + 
			"  ` .###########,._.,,,. #######<_\\##################. '" + "\n" + 
			"     ` .#######,;:      `,/____,__`\\_____,_________,_____" + "\n" + 
			"       `  .###;;;`.   _,;>-,------,,--------,----------'" + "\n" + 
			"            `  `,;' ~~~ ,'\\######_/'#######  .  '" + "\n" + 
			"                ''~`''''    -  .'/;  -    '       " + "\n" 
		);

		System.out.println("Also here is a cake! Enjoyyy <33");
		System.out.println("[Press Enter To Continue]");
		input.nextLine();
		System.out.println("--------------------------------------------------------------------------------------------\n");
	}
}
