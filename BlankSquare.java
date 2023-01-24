/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This class represents the blank game tiles on the board (as well as the title)
 */

import java.util.Scanner;

public class BlankSquare extends GameSquare {

	//constructor
	public BlankSquare(String blankName) {
		super(blankName);

		if (blankName.equalsIgnoreCase("title1")) {
			display[1][1] = " _          ";
			display[2][1] = "|_ _|_ |_  .";
			display[3][1] = "|_  |_ | | |";
			display[4][1] = "____________";
        } else if (blankName.equalsIgnoreCase("title2")) {
			display[2][1] = " _  _. |  _ ";
			display[3][1] = "(_ (_| | (_)";
			display[4][1] = "____________";
        } else if (blankName.equalsIgnoreCase("title3")) {
			display[2][1] = "._   _  |   ";
			display[3][1] = "|_) (_) | \\/";
			display[4][1] = "|_________/_";
		}

	}

	/* Pre: Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input
	 * Post: void
	 * Action: Do nothing. This is an inherited method that is not used by a blank square*/
	public void squareAction(Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input) {
		//Do nothing. It will never reach here. Its only purpose is being a blank square
	}

}
