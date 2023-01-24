/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This class establishes the monopoly board the players play on
 */

public class MonopolyMap {
    //variables
    private int boardDimensions = 7;
    public GameSquare [][] map = new GameSquare [boardDimensions][boardDimensions];

    //constructor
    public MonopolyMap () {
        
        //top row
        map[0][0] = new WildSquare("Go", 0, 0);
        map[0][1] = new ProjectSquare("Console Calculator", "GREEN", 0, 1);
        map[0][2] = new ChanceSquare("Chance", 0, 2);
        map[0][3] = new ProjectSquare("Snake Game", "GREEN", 0,3);
        map[0][4] = new ChanceSquare("Chance", 0, 4);
        map[0][5] = new ProjectSquare("Tic-Tac-Toe Program", "GREEN", 0, 5);
        map[0][6] = new WildSquare("Jail", 0, 6);
        
        //right side
        map[1][6] = new ChanceSquare("Chance", 1, 6);
        map[2][6] = new ProjectSquare("FullStack Website", "PINK", 2, 6);
        map[3][6] = new ChanceSquare("Chance", 3, 6);
        map[4][6] = new ChanceSquare("Chance", 4, 6);
        map[5][6] = new ProjectSquare("Mobile Game", "PINK", 5, 6);
        
        //bottom row
        map[6][6] = new WildSquare("Promotion", 6, 6);
        map[6][5] = new ProjectSquare("Spreadsheet Sorting Algorithm", "PINK", 6, 5);
        map[6][4] = new ChanceSquare("Chance", 6, 4);
        map[6][3] = new ProjectSquare("Boston Dynamics Dog Robot", "RED", 6, 3);
        map[6][2] = new ChanceSquare("Chance", 6, 2);
        map[6][1] = new ProjectSquare("Brain Chip", "RED", 6, 1);
        map[6][0] = new WildSquare("Go To Jail", 6, 0);
       
        //left side
        map[5][0] = new ProjectSquare("Phone", "RED", 5, 0);
        map[4][0] = new ProjectSquare("Cancer-Curing Software", "YELLOW", 4, 0);
        map[3][0] = new ChanceSquare("Chance", 3, 0);
        map[2][0] = new ChanceSquare("Chance", 2, 0);
        map[1][0] = new ProjectSquare("Machine-Learned AI Human", "YELLOW", 1, 0);
        

        //the title in the middle
        map[2][2] = new BlankSquare("title1");
        map[2][3] = new BlankSquare("title2");
        map[2][4] = new BlankSquare("title3");

        //the chance deck
        map[4][3] = new WildSquare("Chance Deck", 4,3);

        //Fill out the rest of the blank places
        for (int i = 1; i < boardDimensions-1; i++) {
            for (int j = 1; j < boardDimensions-1; j++) {
                if (!(i == 4 && j == 3)) {
                    if (!(i == 2)) {
                        map[i][j] = new BlankSquare("Blank");
                    } else {
                        if (j == 1 || j == 5) {
                            map[i][j] = new BlankSquare("Blank");
                        }
                    }
                }
            }
        }
    }
    
    /* Pre: Null
	 * Post: void
	 * Action: Prints the monopoly map board*/
    public void displayMap () {
        //yes it's a quadruple nested for loop but i NEED IT
        //it prints out every gamesquare in its proper position on the board
        //starting with each game square
        for (int boardCol = 0; boardCol < boardDimensions; boardCol++) { 
            for (int projectCol = 0; projectCol < GameSquare.squareLength; projectCol++) {
                for (int boardRow = 0; boardRow < boardDimensions; boardRow++) {
                    for (int projectRow = 0; projectRow < map[boardCol][boardRow].display[projectCol].length; projectRow++) {
                        System.out.print(map[boardCol][boardRow].display[projectCol][projectRow]);
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
