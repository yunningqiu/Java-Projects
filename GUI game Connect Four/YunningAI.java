package hw4;

public class YunningAI implements CFPlayer{
	@Override
	public int nextMove(CFGame g){
		int[][] state = g.getState();

		int color = 0;
		if (g.isRedTurn())
			color = 1;
		else
			color = -1;
		// check whether YunningAI is playing red or black


		/* check if there is a winning move, if so return it */
		for (int j=0; j<6; j++){
			for (int i=2; i<7; i++){
				if (color==state[i][j] && state[i][j]==state[i-1][j] && state[i][j]==state[i-2][j]){
					if(i+1<7 && state[i+1][j]==0)
						return i+2;
					else if (i-3>=0 && state[i-3][j]==0)
						return i-2;
				}
			}
		}
		// check horizontally, return the spot on the right or left

		for (int i=0; i<7; i++){
			for (int j=2; j<5; j++){
				if (color==state[i][j] && state[i][j]==state[i][j-1] && state[i][j]==state[i][j-2]){
					if ((j+1)<6 && state[i][j+1]==0)
						return i+1;
				}
			}
		}
		// check vertically, return the spot on the top

		for (int j=0; j<4; j++){
			for (int i=0; i<5; i++){
				if (color==state[i][j] && state[i][j]==state[i+1][j+1] && state[i][j]==state[i+2][j+2]){
					if ((i+3)<7 && (j+3)<6 && state[i+3][j+3]==0)
						return i+4;
					else if ((i-1)>=0 && (j-1)>=0 && state[i-1][j-1]==0)
						return i;
				}
			}
		}
		// check diagonally: bottom left to upper right, return bottom left or upper right

		for (int j = 0; j < 4; j++) {
            for (int i = 2; i < 7; i++) {
                if (color==state[i][j] && state[i][j]==state[i-1][j+1] && state[i][j]==state[i-2][j+2]){
                    if ((i-3) >= 0 && (j+3) < 6 && state[i-3][j+3] == 0)
                        return i-2;
                    else if ((i+1) < 7 && (j-1) >= 0 && state[i+1][j-1] == 0)
                        return i+2;
                }
            }
        }
		// check diagonally: bottom right to upper left, return bottom right or upper left


		/* check if opponent has a winnig move, if so return it */
		int oppo_color = 0;
		if (color==1)
			oppo_color = -1;
		else
			oppo_color = 1;
		// check the opponent color

		for (int j=0; j<6; j++){
			for (int i=2; i<7; i++){
				if (oppo_color==state[i][j] && state[i][j]==state[i-1][j] && state[i][j]==state[i-2][j]){
					if(i+1<7 && state[i+1][j]==0)
						return i+2;
					else if (i-3>=0 && state[i-3][j]==0)
						return i-2;
				}
			}
		}
		// check horizontally, return the spot on the right or left

		for (int i=0; i<7; i++){
			for (int j=2; j<5; j++){
				if (oppo_color==state[i][j] && state[i][j]==state[i][j-1] && state[i][j]==state[i][j-2]){
					if ((j+1)<6 && state[i][j+1]==0)
						return i+1;
				}
			}
		}
		// check vertically, return the spot on the top

		for (int j=0; j<4; j++){
			for (int i=0; i<5; i++){
				if (oppo_color==state[i][j] && state[i][j]==state[i+1][j+1] && state[i][j]==state[i+2][j+2]){
					if ((i+3)<7 && (j+3)<6 && state[i+3][j+3]==0)
						return i+4;
					else if ((i-1)>=0 && (j-1)>=0 && state[i-1][j-1]==0)
						return i;
				}
			}
		}
		// check diagonally: bottom left to upper right, return bottom left or upper right

		for (int j = 0; j < 4; j++) {
            for (int i = 2; i < 7; i++) {
                if (oppo_color==state[i][j] && state[i][j]==state[i-1][j+1] && state[i][j]==state[i-2][j+2]){
                    if ((i-3) >= 0 && (j+3) < 6 && state[i-3][j+3] == 0)
                        return i-2;
                    else if ((i+1) < 7 && (j-1) >= 0 && state[i+1][j-1] == 0)
                        return i+2;
                }
            }
        }
		// check diagonally: bottom right to upper left, return bottom right or upper left


		/* when neither has a winning move, play the same column */
		for (int i=0; i<7; i++) {
            if (state[i][5] == 0) {
                for (int j=0; j<5; j++) {
                    if (state[i][j+1] == 0 && state[i][j] == color)
                        return i+1;
                }
            }
        }
        

		/* otherwise, return a random column */
		CFPlayer ranAI = new RandomAI();
		return ranAI.nextMove(g);
	}

	@Override
	public String getName(){
		return "Yunning's AI";
	}
}