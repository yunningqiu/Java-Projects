package hw2;
import java.util.Random;

public class MatchCardGame{
	public final int n;
	Card[] cards;

	Card previouscard;
	Card nextcard;
	int flips = 0;

	
	public MatchCardGame(int n)
	{
		this.n = n;
		cards = new Card[n];
		// create an array of cards of size n

		int counter = 0;
		char type = 'a';

		for (int i=0; i<n; i++)
		{
			cards[i] = new Card(type);
			counter++;

			if (counter >= 4)
			{
				type++;
				counter = 0;
			}
			// increment char type every 4 rounds
		}
		// fill the array with cards of certain types
	}


	public String boardToString()
	{
		int count = 0;
		for (int i = 0; i < n; i++)
		{
			count++;
			if (!cards[i].facedown)
				System.out.print("\t" + i + ": " + cards[i].type + "         ");
			else
				System.out.print("\t" + i + ": ?" + "         ");
			//print types as 2D array

			if (count==4 || count==8 || count==12 || count==16)
				System.out.println("");
			//insert new line
		}
		System.out.println("There are " + n + " card on the board.");
		System.out.println("Number of flips you have made: " + flips);

		return "";
	}

	// two most recent flips made (at least after an even flip)

	public void shuffleCards()
	{
		Random randnum = new Random();
		Card temp = new Card();
		for (int i=n-1; i < 0; i--){
			int j = randnum.nextInt(i-1);
			temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
	}
	// called before flip is made

	public boolean flip(int i)
	{
		if (i < 0 || i > n-1)
			return false;
		// if i invalid, return false.

		if (cards[i].facedown)
		{
			flips++;
			cards[i].facedown = false;
			if (previouscard == null)
				previouscard = cards[i];
			else
				nextcard = cards[i];
			return true;
		}
		// if the i card is facing down, flip it over and return true.
		return false;
		// otherwise if card is facing up, return false.
	}


	public boolean wasMatch()
	{
		if (previouscard == null || nextcard == null)
			return false;
		// if it's not an even flip, return false

		if (previouscard.type == nextcard.type){
			previouscard = null;
			nextcard = null;
			return true;
		}

		else
			return false;
	}

	public char previousFlipIdentity()
	{
		if(nextcard != null)
		{
			return nextcard.type;
		}
		else
		{
			return previouscard.type;
		}
	}

	public void flipMismatch()
	{
		if(previouscard == null || nextcard == null)
			return;
		// if it's not after 2 calls of flip, exit

		previouscard.facedown = true;
		nextcard.facedown = true;

		previouscard = null;
		nextcard = null;

	}

	public boolean gameOver()
	{
		for (int i = 0; i < n; i++){
			if (cards[i].facedown == true)
				return false;
		}
		// if any card is still facing down, the game is not over
		return true;
	}

	public int getFlips()
	{
		return flips;
	}
}


class Card{
	char type;
	boolean facedown;

	Card(){
		this.type = type;
		facedown = true;
	}

	Card(char type){
		this.type = type;
		facedown = true;
	}
}













