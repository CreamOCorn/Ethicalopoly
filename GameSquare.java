/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This sets up each game square the player can land on.
 */

import java.util.*;
public abstract class GameSquare {

    //variables
    public static int squareLength = 6;
    public String[][] display = new String [squareLength][];
    
    public String name;
    public int[] coors = new int [2];

    //ansi colour codes. only works in IDEs that support it
    protected static final String PRINTRESET = "\u001B[0m";
    protected static final String PRINTRED = "\u001B[31m";
    protected static final String PRINTGREEN = "\u001B[32m";
    protected static final String PRINTBLUE = "\u001B[34m";
    protected static final String PRINTPINK = "\u001B[35m";
    protected static final String PRINTPURPLE = "\u001B[36m";
    protected static final String PRINTYELLOW = "\u001B[33m";

    //constructors
    public GameSquare (String blankName) {
        name = blankName;
        String[][] startDisplay = {
            {PRINTRESET,"            "},
            {PRINTRESET,"            "},
            {PRINTRESET,"            "},
            {PRINTRESET,"            "},
            {PRINTRESET,"            "},
            {PRINTRESET,"            "},
        };
        
        display = startDisplay;
    }

    public GameSquare (String newName, int boardRow, int boardCol) {
        String[][] startDisplay = {
            {PRINTRESET,"╔══════════╗"},
            {PRINTRESET,"║","          ","║"},
            {PRINTRESET,"║","   ", PRINTRESET, " "," "," "," ", PRINTRESET, "   ","║"},
            {PRINTRESET,"║","          ","║"},
            {PRINTRESET,"║","          ","║"},
            {PRINTRESET,"╚══════════╝", PRINTRESET}
        };
        
        display = startDisplay;
        name = newName;
        coors[0] = boardRow;
        coors[1] = boardCol;
    }

    /* Pre: int i
	 * Post: void
	 * Action: Take the player's symbol off of a square*/
    public void removePlayers (int i) {
        display[2][i+4] = " ";
    }

    /* Pre: Player player, int i
	 * Post: void
	 * Action: Moves the player's symbol to a square*/
    public void movePlayers (Player player, int i) {
        display[2][i+4] = player.symbol;
    }

    /* Pre: Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input
	 * Post: void
	 * Action: Plays out the action that happens when the player lands on this square*/
    public abstract void squareAction (Player player, GameSquare square, ChanceCardDeck chanceDeck, ProjectCardDeck projectDeck, Scanner input); 

}
