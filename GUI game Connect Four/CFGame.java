package hw4;

public class CFGame {
  //state[i][j]= 0 means the i,j slot is empty
  //state[i][j]= 1 means the i,j slot has red
  //state[i][j]=-1 means the i,j slot has black
  private final int[][] state;
  private boolean isRedTurn;
  private int winner;
  
  {
    state = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        state[i][j] = 0;
    isRedTurn = true; //red goes first
  }
    
  public int[][] getState() {
    int[][] ret_arr = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        ret_arr[i][j] = state[i][j];
    return ret_arr;
  }


  public void printState(){
     for (int j = 5; j > -1; j--) {
          System.out.println (state[0][j] + "\t" + state[1][j] + "\t" + state[2][j] + "\t" 
            + state[3][j] + "\t" + state[4][j] + "\t" + state[5][j] + "\t" + state[6][j]);
      }
  }
  

  public boolean isRedTurn() {
    return isRedTurn;
  }

  
  public boolean play(int column) {
    if (column < 1 || column > 7 || state[column-1][5] != 0)
      return false;
    // if column is invalid, or the top row of the input column is not empty (column is full), return false

    for (int j=0; j<6; j++){
      if (state[column-1][j] == 0){
        if(isRedTurn) {
          state[column-1][j] = 1;
          isRedTurn = false;
        }

        else{
          state[column-1][j] = -1;
          isRedTurn = true;
        }
        return true;
      }
    }
    // fill the lowest available row in the selected column with red(1) or black(-1)

    return false;
  }

  
  public boolean isGameOver() {
    for (int i=0; i<7; i++){
      for (int j=3; j<6; j++){
        if (state[i][j]!=0 && state[i][j]==state[i][j-1] && state[i][j]==state[i][j-2] && state[i][j]==state[i][j-3]){
          winner = state[i][j];
          return true;
        }
      }
    }
    // if there exists 4 in a column, return true

    for (int j=0; j<6; j++){
      for (int i=3; i<7; i++){
        if (state[i][j]!=0 && state[i][j]==state[i-1][j] && state[i][j]==state[i-2][j] && state[i][j]==state[i-3][j]){
          winner = state[i][j];
          return true;
        }
      }
    }
    // if there exists 4 in a row, return true

    for (int j=0; j<3; j++){
      for (int i=3; i<7; i++){
        if (state[i][j]!=0 && state[i][j]==state[i-1][j+1] && state[i][j]==state[i-2][j+2] && state[i][j]==state[i-3][j+3]){
          winner = state[i][j];
          return true;
        }
      }
    }
    // if there exists 4 in diagonal (bottom right to top left), return true

    for (int j=0; j<3; j++){
      for (int i=0; i<4; i++){
        if (state[i][j]!=0 && state[i][j]==state[i+1][j+1] && state[i][j]==state[i+2][j+2] && state[i][j]==state[i+3][j+3]){
          winner = state[i][j];
          return true;
        }
      }
    }
    // if there exists 4 in diagonal (bottom left to top right), return true

    boolean available = false;
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        if (state[i][j] == 0)
          available = true;
    if (!available){
      winner = 0;
      return true;
    }
    // if no more possible moves on the board, set winner to 0 and return true

    return false;
  }

  
  public int winner() {
    return winner;
  }

}
