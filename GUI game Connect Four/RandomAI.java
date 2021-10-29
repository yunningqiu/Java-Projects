package hw4;

public class RandomAI implements CFPlayer{

	@Override
	public int nextMove(CFGame g){
		int r;
		int[][] state = g.getState();
		// get current state of the game

		r = ((int)(Math.random() * 7))+1;
		// generate random number ranging from 1-7

		while (r<1 || r>7 || state[r-1][5] !=0){
			r = ((int)(Math.random() * 7))+1;
		}
		// get an r until it's valid

		return r;
	}

	@Override
	public String getName(){
		return "Random Player";
	}

}