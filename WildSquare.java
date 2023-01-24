/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This class represents the squares that aren't projects or chance squares...
 *They're... the wild ones... (Go/Go to Jail/Jail/Promotion)
 */

import java.util.*;
public class WildSquare extends GameSquare{

    //constructor
    public WildSquare(String newName, int newBoardRow, int newBoardCol) {
        super(newName, newBoardRow, newBoardCol);

        //writes all the titles onto the gamesquares
        if (newName.equals("Chance Deck")) {
            display[1][2] = "  CHANCE  ";
            display[2][4] = "DECK";
            display[2][5] = "";
            display[2][6] = "";
            display[2][7] = "";
            display[4][2] = "Draw These";
        } else {
            if (newName.equals("Jail")) {
                display[1][2] = "   JAIL   ";
                display[4][2] = " Visiting ";
            } else if (newName.equals("Go To Jail")) {
                display[1][2] = "GO TO JAIL";
                display[4][2] = "woop woop!";
            } else if (newName.equals("Promotion")) {
                display[1][2] = " PROMOTED ";
                display[4][2] = " Get $100 ";
            } else {
                display[1][2] = "    GO    ";
                display[4][2] = " Get $200 ";
            }

            for (int i = 0; i < squareLength; i++) {
                display[i][0] = PRINTBLUE;
            }
            display[2][8] = PRINTBLUE;
        }
    }

    /* Pre: Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input
    * Post: void
    * Action: Depending on which square the player lands on, plays out different scenario and dialogue .*/
    public void squareAction (Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input) {
        //a different action happens depending on the square
        if (square.name.equals("Go To Jail")) {
            player.goToJail();
            System.out.println("Hehe you've been caught for fraud and are going to jail now!\nYour next turn will be skipped!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
        } else if (square.name.equals("Promotion")) {
            System.out.println("Congratulations, your hard work has been recognized on GitHub and \nyou have been rewarded $100 for it!");
            player.getCash(100);
        } else if (square.name.equals("Jail")) {
            System.out.println("Don't worry though, you're not actually in jail. You're just visiting!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
        }
    }
}
