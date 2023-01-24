/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This class is a stack that holds every chance card.
 */

import java.util.*;
public class ChanceCardDeck {
	//variables
    private ChanceCard[] deck = 
    		{
				new ChanceCard("1.1"), new ChanceCard("1.2"), new ChanceCard("1.3"), new ChanceCard("1.4"), new ChanceCard("1.5"), new ChanceCard("1.6"), new ChanceCard("1.7"),
				new ChanceCard("2.1"), new ChanceCard("2.2"), new ChanceCard("2.3"), new ChanceCard("2.4"), new ChanceCard("2.5"), new ChanceCard("2.6"), new ChanceCard("2.7"), new ChanceCard("2.8"), new ChanceCard("2.9"),
				new ChanceCard("3.1"), new ChanceCard("3.2"), new ChanceCard("3.3"), new ChanceCard("3.4"), new ChanceCard("3.5"), new ChanceCard("3.6"), new ChanceCard("3.7"),
				new ChanceCard("4.1"), new ChanceCard("4.2")                  
			};
    
    private int top;
    private int size;
	
	//constructor
	public ChanceCardDeck () {
		top = 0;
		size = deck.length;
		shuffleDeck();
	}
	
	/* Pre: Player player, Scanner input
	 * Post: void
	 * Action: Pops the top element*/
	public void drawCard (Player player, Scanner input) {
		deck[top].displayCardInfo(player, input);
		top ++;
		if (top == size) {
			shuffleDeck();
			top = 0;
		}
	}

	/* Pre: Null
	 * Post: void
	 * Action: Shuffles all of the deck's elements*/
	public void shuffleDeck () {
		ChanceCard tempCard;
		Random rand = new Random();

		// shuffles the deck by switching elements in the array
		for (int i = 0; i < size; i++) {
			int random = rand.nextInt(size);
			tempCard = deck[random];
			deck[random] = deck[i];
			deck[i] = tempCard;
		}
	}
}