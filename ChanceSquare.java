/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: The tile in the middle of the board that looks like a deck of chance cards
 */

import java.util.*;

public class ChanceSquare extends GameSquare {

    //constructor
	 public ChanceSquare(String newName, int newBoardRow, int newBoardCol) {
	        super(newName, newBoardRow, newBoardCol);

	        //writes all the content onto the gamesquare
            display[1][2] = "  CHANCE  ";
            display[4][2] = "Draw Card!";
            for (int i = 0; i < squareLength; i++) {
                display[i][0] = PRINTPURPLE;
            }
            display[2][8] = PRINTPURPLE;
	      
	}
        
    /* Pre: Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input
	 * Post: void
	 * Action: Draws a card from the chance deck*/
    public void squareAction (Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input) {
        chanceDeck.drawCard(player, input);
    }
}
