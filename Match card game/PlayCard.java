package hw2;
import java.util.Random;

public class PlayCard {
  public static void main(String[] args) 
  {

    java.util.Scanner reader = new java.util.Scanner (System.in);
    //set up reader to take inputs
    
    int n = 16; //game size
    MatchCardGame g1 = new MatchCardGame(n);
    //g1.shuffleCards();
    
    while(!g1.gameOver()) 
    {
      
      System.out.println(g1.boardToString());
      //print board status
      
      System.out.println("Which card to play?");
      while(!g1.flip(reader.nextInt())) {}
      //ask for a card to flip until we get a valid one

      System.out.println(g1.boardToString());
      //print board status
      
      while(!g1.flip(reader.nextInt())) {}
      //ask for a card to flip until we get a valid one
      
      if(g1.wasMatch())    
      {
        System.out.println("Was a match!");
      } 
         //say whether the 2 cards were a match
      else 
      {
        System.out.println(g1.boardToString());   
        System.out.println("Was not a match.");
        //print board to show mismatched cards
        g1.flipMismatch();
        //flip back the mismatched cards
      }
    }
    
    //Report the score
    System.out.println("The game took " + g1.getFlips() + " flips.");



    //Using the AIs
    int count;
    MatchCardGame g2 = new MatchCardGame(n);
    g2.shuffleCards();
    count = playRandom(g2);
    System.out.println("The bad AI took " + count + " flips."); 


    MatchCardGame g3 = new MatchCardGame(n);
    g3.shuffleCards();
    count = playGood(g3);
    System.out.println("The good AI took " + count + " flips.");


    //Using MCs aa
    int N = 1000;
    System.out.println("The bad AI took " + randomMC(N) + " flips on average.");
    System.out.println("The good AI took " + goodMC(N) + " flips on average.");
  }

  public static int playRandom (MatchCardGame g)
  {
    Random rand = new Random();

    while(!g.gameOver()) 
    {

      while(!g.flip(rand.nextInt(g.n))) {}
      //randomly flip a card

      while(!g.flip(rand.nextInt(g.n))) {}
      //randomly flip another card

      if(!g.wasMatch())
      {
        g.flipMismatch();
        //flip back the mismatched cards
      }
    }
    return g.getFlips();
  }


  public static int playGood (MatchCardGame g)
  {
    Random rand = new Random();
    int rand1 =0;
    int rand2 =0;
    int rand3 =0;
    boolean known = true;
    boolean knowall = true;
    char[] types = new char[g.n];
    boolean[] facedowns = new boolean[g.n];

    for (int i = 0; i<g.n; i++)
    {
      types[i] = '/';
      facedowns[i] = true;
    }

    while(!g.gameOver()) 
    {

      // make a random flip of index that is unknown '/' in the array
      knowall = true;
      for(int i=0; i<g.n; i++)
      {
        if (types[i] == '/')
          knowall = false;        
      }

      // if all are known, randomly generate an index to flip
      if (knowall==true)
      {
        while(!g.flip(rand.nextInt(g.n))) {}
        types[rand1] = g.previousFlipIdentity();
      }
      // if not all are known, randomly flip one of the unknown card
      else
      {
        while (known==true)
        {
          rand1 = rand.nextInt(g.n);
          if (types[rand1] == '/')
          {
            known = false;
          }       
        }

      while(!g.flip(rand1)) {}
      types[rand1] = g.previousFlipIdentity();
      known = true;
      }
      
      

      // after odd flip, check if there is an existing match to this random flip
      if (g.getFlips() %2 ==1)
      {
        for (int i = 0; i<g.n; i++)
        {
          //if there is an existing match, flip the according position of the array
          if(types[i] == types[rand1] && facedowns[i]==true && i!=rand1)
          {
            if(g.flip(i))
            {
              types[i] = g.previousFlipIdentity();
              facedowns[i] = false;
              facedowns[rand1] = false;
            }
          }

          if (!g.wasMatch())
          {
            g.flipMismatch();
            facedowns[i] = true;
            facedowns[rand1] = true;
          }
          
        }
      }


      //after even flip, check if there exist a pair match that's facing down. if yes flip these two.
      if (g.getFlips() %2 ==0)
      {
        for (int i = 0; i<g.n; i++)
        {
          for (int j = 0; j<g.n; j++)
          {
            if (i!=j && types[i]!='/' && types[j]!='/' && types[i]==types[j] 
                && facedowns[i] ==true && facedowns[j] ==true)
            {

              g.flip(i);
              facedowns[i] = false;

              g.flip(j);
              facedowns[j] = false;

              g.wasMatch();
            }
          }
        }
      }
        
    }

    return g.getFlips();
  }


  public static double randomMC(int N)
  {
    double sum = 0;
    double average;
    MatchCardGame g = new MatchCardGame(32);

    for (int i = 0; i< N; i++)
    {
      g.shuffleCards();
      sum += playRandom(g);
    }

    average = sum / (double) N;
    return average;
  }


  public static double goodMC(int N)
  {
    double sum = 0;
    double average;
    MatchCardGame g = new MatchCardGame(32);
    
    for (int i = 0; i< N; i++)
    {
      g.shuffleCards();
      sum += playGood(g);
    }

    average = sum / (double) N;
    return average;
  }
}
