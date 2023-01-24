/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This class is a deck of cards that holds every project card
 */

public class ProjectCardDeck {
	//variables
	private ProjectCard[] deck = 
 			{
				new ProjectCard("Red", "Boston Dynamics Dog Robot"),
				new ProjectCard("Red", "Brain Chip"),
				new ProjectCard("Yellow", "Cancer-Curing Software"),
				new ProjectCard("Green", "Console Calculator"),
				new ProjectCard("Pink", "FullStack Website"), 
				new ProjectCard("Yellow", "Machine-Learned AI Human"),
				new ProjectCard("Pink", "Mobile Game"),
				new ProjectCard("Red", "Phone"),
				new ProjectCard("Green", "Snake Game"), 
				new ProjectCard("Pink", "Spreadsheet Sorting Algorithm"),
				new ProjectCard("Green", "Tic-Tac-Toe Program")
			};
	private int deckLength = deck.length;

	//constructor
	public ProjectCardDeck () {
		//Doesn't have any other variables to initialize
	}
	
	/* Pre: GameSquare square
	 * Post: ProjectCard
	 * Action: Uses binary search to look for a certain card in the deck depending on the name passed*/
	public ProjectCard findSquaresCard (GameSquare square) {
		int bot = 0, top = deckLength-1, mid = top/2;
		
		//keeps using binary search by comparing the name of the project square to all possible cards
		while (top >= bot) {
			if (deck[mid].name.compareTo(square.name) < 0) {
				bot = mid + 1;
			} else if (deck[mid].name.compareTo(square.name) > 0) {
				top = mid - 1;
			} else {
				return deck[mid];
			}
			mid = ((bot+top)/2);
		}
		return deck[mid];
	}
	
}
