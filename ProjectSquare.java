/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This represents every project square that the user can land on.
 */

import java.util.*;

public class ProjectSquare extends GameSquare {
    //variables
    public String colour;
    public boolean isOwned;
    public Player owner;

    //constructor
    public ProjectSquare (String newName, String newColour, int newBoardRow, int newBoardCol) {
        super(newName, newBoardRow, newBoardCol);
        colour = newColour;

        //colours the square in the display according to what colour they should be
        String printColour;
        if (newColour.equals("RED")) {
            printColour = PRINTRED;
            display[4][2] = "   $250   ";
        } else if (newColour.equals("GREEN")) {
            printColour = PRINTGREEN;
            display[4][2] = "   $60    ";
        } else if (newColour.equals("YELLOW")) {
            printColour = PRINTYELLOW;
            display[4][2] = "   $400   ";
        } else {
            printColour = PRINTPINK;
            display[4][2] = "   $100   ";
        }

        for (int i = 0; i < squareLength; i++) {
            display[i][0] = printColour;
        }
        display[2][8] = printColour;

        //the game square's label
        display[1][2] = " PROJECT! ";

        isOwned = false;
    }
    
    /* Pre: Player player
	 * Post: void
	 * Action: Puts the owner's name on the square they just bought*/
    public void buy (Player player) {
        owner = player;
        display[3][2] = "Owned by " + player.symbol;
        isOwned = true;
    }

    /* Pre: Player player
    * Post: void
    * Action: Removes the owner's name from the square they just sold*/
    public void sell (Player player) {
        owner = null;
        display[3][2] = "          ";
        isOwned = false;
    }

    /* Pre: Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input
    * Post: void
    * Action: Allows the player to buy this project if it is possible.*/
    public void squareAction (Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input) {
        ProjectCard card = projectDeck.findSquaresCard(square);
        	
        //You have to pay royalties if someone else owns it
        if (((ProjectSquare)square).isOwned) {
            if (!player.name.equals((((ProjectSquare)square).owner.name))) {
                System.out.println("Omg... looks like someone already owns this project.\nIt's unethical to plagiarise! You need to pay royalty fees of $" + card.royaltyFee + ".\n");
                player.getCash(-card.royaltyFee);
                ((ProjectSquare)square).owner.getCash(card.royaltyFee);
            } else {
                System.out.println("You already own this... So.... it shouldn't matter too much...");
            }
        //asks you if you want to buy as long as you can afford it
        } else {
            if (player.cash < card.cost) {
                System.out.println("It seems as though this project costs $" + card.cost + " while you only have $" + player.cash + "."); 
                System.out.println("So, you cannot affort this."); 
                System.out.println("[Press Enter To Continue]");
                input.nextLine();
            } else {
                System.out.println(card); 
                String playerAns = "";
                
                while (!(playerAns.equalsIgnoreCase("y") || playerAns.equalsIgnoreCase("n")) ) {
                    System.out.println("You currently have $" + player.cash + ".");
                    System.out.println("Would you like to purchase the resources to create this project for $" + card.cost + "? (Y/N)"); 
                    
                    playerAns = input.nextLine();
                    System.out.println();

                    //if they buy it, it goes into their invenotry and the square now has their name on it
                    if (playerAns.equalsIgnoreCase("y")) {
                        System.out.println("You have now made a " + card.name + "!");
                        player.addInventory(card);
                        player.getCash(-card.cost);
                        ((ProjectSquare)square).buy(player);
                        System.out.println(card.name + " has been added to your list of projects.");
                        if (player.hasAllColour(card.colour)) {
                            System.out.println("Congratulations! By collecting all projects of the same colour,\nthe royalty fees of all " + card.colour + " projects have increased to $" + card.royaltyFee + "!" );
                            System.out.println("You will now earn more money if other players land on your project :)");
                            System.out.println("[Press Enter To Continue]");
                            input.nextLine();
                        } 
                    } else if (playerAns.equalsIgnoreCase("n")) {
                        System.out.println("You have chosen not to make a " + card.name + ".");
                        System.out.println("[Press Enter To Continue]");
                        input.nextLine();
                    } else {
                        System.out.println("That is not a valid input. Please choose either Y or N.\n");
                    }
                }
            }
        }
    }
}
