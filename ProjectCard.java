/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This class represents every project the player can pick up while playing
 */

public class ProjectCard {
    //variables
    public String colour;
    public String name;
    public int cost;
    public int royaltyFee;
    public int sellingPrice;

    //constructor
    public ProjectCard (String newColour, String newName) {
        colour = newColour;
        name = newName;
        
        //allocates the propers cost to each project
        if (colour.equals("Green")) {
        	cost = 60;
        } else if (colour.equals("Pink")) {
        	cost = 100;
        } else if (colour.equals("Red")) {
        	cost = 250;
        } else {
        	cost = 400;
        }
        
        royaltyFee = (cost/5);
        sellingPrice = (cost/2);
    }

    /* Pre: Null
	 * Post: void
	 * Action: Turns the card's royalty fee from 1/5 of the original cost to 1/2*/
    public void upgrade () {
        royaltyFee = (cost/2);
    }

    /* Pre: Null
	 * Post: String
	 * Action: Displays the information about the project*/
    public String toString () {
        String info = 
            "Colour: " + colour + "\n" +
            "Name: " + name + "\n" +
            "Cost: $" + cost + "\n" +
            "Royalty Fees: $" + royaltyFee + "\n" +
            "Sellling Price: $" + sellingPrice + "\n";

        return info;
    }
}
