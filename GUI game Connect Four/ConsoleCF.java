package hw4;
import java.util.Random;
import java.util.Scanner;

public class ConsoleCF extends CFGame{
	private CFPlayer red;
	private CFPlayer black;

	public ConsoleCF(CFPlayer ai){
		Random rand = new Random();
		int r = rand.nextInt(2);

		if (r==0){
			red = ai;
			black = new HumanPlayer();
		}

		else if (r==1){
			red = new HumanPlayer();
			black = ai;
		}
		// decide whether human or ai goes first (red)
	}

	public ConsoleCF(CFPlayer ai1, CFPlayer ai2){
		Random rand = new Random();
		int r = rand.nextInt(2);

		if (r==0){
			red = ai1;
			black = ai2;
		}

		else if (r==1){
			red = ai2;
			black = ai1;
		}
		// decide whether ai1 or ai2 goes first (red)
	}

	public void playOut(){
		int[][] state = getState();

		while (!isGameOver()) {
			if (isRedTurn()){
				int nextColumn = 0;
				while (!play(nextColumn)){
					nextColumn = red.nextMove(this);
				}
			}
			// play the valid nextMove if it's red's turn

			else if (!isRedTurn()){
				int nextColumn = 0;
				while (!play(nextColumn)){
					nextColumn = black.nextMove(this);
				}
			}
			// play the valid nextMove if it's black's turn
		}
		// play until the game is over
	}

	public String getWinner(){
		if (winner() == 1){
			return red.getName();
		}
		// if red player wins, return the name of red player

		if (winner() == -1){
			return black.getName();
		}
		// if black player wins, return the name of the black player

		return "draw";
	}

	private class HumanPlayer implements CFPlayer{
		@Override
		public int nextMove(CFGame g){
			int[][] state = g.getState();
			g.printState();
			System.out.println("Please enter a valid column number for your next move: ");
			Scanner reader = new Scanner(System.in);
			int move=reader.nextInt();
			
			while (move<1 || move>7 || state[move-1][5]!=0){
				System.out.println("Column number is invalid");
				System.out.println("Please enter a valid column number for your next move: ");
				move = reader.nextInt();
			}
			// keep asking the user to provide input until we get a valid column number

			return move;
		}

		@Override
		public String getName(){
			return "Human Player";
		}
	}


	public static void main(String[] args) { 
		CFPlayer ai1 = new YunningAI ();
		CFPlayer ai2 = new RandomAI ();
		int n = 10000;
		int winCount = 0;
		for (int i=0; i<n; i++) {
			ConsoleCF game = new ConsoleCF(ai1, ai2); 
			game.playOut();
			if (game.getWinner()==ai1.getName())
				winCount ++; 
		}
		System.out.print(ai1.getName() + " wins "); 
		System.out.print(((double) winCount)/((double) n)*100 + "%"); 
		System.out.print(" of the time.");
	}
}