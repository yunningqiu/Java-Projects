package hw4;
import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUICF extends CFGame{
	private GameBoard this_board;
	private JFrame frame;
	private JLabel[] grids = new JLabel[42];
	private CFPlayer red;
	private CFPlayer black;
	private int[][] state = getState();
	private JPanel panel;

	{
		frame = new JFrame("Collect Four Game");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(200, 200);
		this_board = new GameBoard();
		frame.getContentPane().add(this_board, BorderLayout.CENTER);
		frame.setVisible(true);
	} 


	public GUICF(CFPlayer ai){
		Random rand = new Random();
		int r = rand.nextInt(2);
		if (r == 0){
			red = ai;
			black = new HumanPlayer();
		}
		else if (r == 1){
			red = new HumanPlayer();
			black = ai;
		}
		// decide whether human player or the ai goes first

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1,7));
		// create a panel to add arrow buttons

		JPanel buttonPanel1 = new JPanel();
		JPanel buttonPanel2 = new JPanel();
		JPanel buttonPanel3 = new JPanel();
		JPanel buttonPanel4 = new JPanel();
		JPanel buttonPanel5 = new JPanel();
		JPanel buttonPanel6 = new JPanel();
		JPanel buttonPanel7 = new JPanel();
		// create 7 buttonPanels to add individual arrow buttons

		buttonPanel1.setBackground(Color.WHITE);
		buttonPanel2.setBackground(Color.WHITE);
		buttonPanel3.setBackground(Color.WHITE);
		buttonPanel4.setBackground(Color.WHITE);
		buttonPanel5.setBackground(Color.WHITE);
		buttonPanel6.setBackground(Color.WHITE);
		buttonPanel7.setBackground(Color.WHITE);
		buttonPanel1.setPreferredSize(new Dimension(30,30));
		buttonPanel2.setPreferredSize(new Dimension(30,30));
		buttonPanel3.setPreferredSize(new Dimension(30,30));
		buttonPanel4.setPreferredSize(new Dimension(30,30));
		buttonPanel5.setPreferredSize(new Dimension(30,30));
		buttonPanel6.setPreferredSize(new Dimension(30,30));
		buttonPanel7.setPreferredSize(new Dimension(30,30));
		buttonPanel1.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		buttonPanel2.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		buttonPanel3.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		buttonPanel4.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		buttonPanel5.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		buttonPanel6.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		buttonPanel7.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		// set color, size and empty border for each buttonPanel

		panel.add(buttonPanel1);
		panel.add(buttonPanel2);
		panel.add(buttonPanel3);
		panel.add(buttonPanel4);
		panel.add(buttonPanel5);
		panel.add(buttonPanel6);
		panel.add(buttonPanel7);
		// add the 8 buttonPanels to panel1

		JButton arrow1 = new JButton("\u2193");
		JButton arrow2 = new JButton("\u2193");
		JButton arrow3 = new JButton("\u2193");
		JButton arrow4 = new JButton("\u2193");
		JButton arrow5 = new JButton("\u2193");
		JButton arrow6 = new JButton("\u2193");
		JButton arrow7 = new JButton("\u2193");
		// create 7 arrow buttons

		arrow1.setBackground(Color.WHITE);
		arrow2.setBackground(Color.WHITE);
		arrow3.setBackground(Color.WHITE);
		arrow4.setBackground(Color.WHITE);
		arrow5.setBackground(Color.WHITE);
		arrow6.setBackground(Color.WHITE);
		arrow7.setBackground(Color.WHITE);
		arrow1.setPreferredSize(new Dimension(30,20));
		arrow2.setPreferredSize(new Dimension(30,20));
		arrow3.setPreferredSize(new Dimension(30,20));
		arrow4.setPreferredSize(new Dimension(30,20));
		arrow5.setPreferredSize(new Dimension(30,20));
		arrow6.setPreferredSize(new Dimension(30,20));
		arrow7.setPreferredSize(new Dimension(30,20));
		arrow1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		arrow2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		arrow3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		arrow4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		arrow5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		arrow6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		arrow7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// set color, size, border for each arrow button

		buttonPanel1.add(arrow1);
		buttonPanel2.add(arrow2);
		buttonPanel3.add(arrow3);
		buttonPanel4.add(arrow4);
		buttonPanel5.add(arrow5);
		buttonPanel6.add(arrow6);
		buttonPanel7.add(arrow7);
		// add 7 arrow buttons to their corresponding buttonPanels

		arrow1.addActionListener(new arrowBL1());
		arrow2.addActionListener(new arrowBL2());
		arrow3.addActionListener(new arrowBL3());
		arrow4.addActionListener(new arrowBL4());
		arrow5.addActionListener(new arrowBL5());
		arrow6.addActionListener(new arrowBL6());
		arrow7.addActionListener(new arrowBL7());
		// add ActionListener to each arrow button

		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}


	public GUICF(CFPlayer ai1, CFPlayer ai2){
		Random rand = new Random();
		int r = rand.nextInt(2);
		if (r == 0){
			red = ai1;
			black = ai2;
		}
		else if (r == 1){
			red = ai2;
			black = ai1;
		}
		// decide which ai goes first

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		// create a panel for play button

		JButton playButton = new JButton("Play");
		playButton.setBackground(Color.WHITE);
		playButton.setPreferredSize(new Dimension(50, 30));
		playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// create the play button, and set the color, size, border

		panel.add(playButton, BorderLayout.CENTER);
		playButton.addActionListener(new playBL());
		// add button to panel and create action listener

		frame.setVisible(true);
		frame.revalidate();           // reset layout based on new components
		frame.repaint();              // repaint components
		frame.pack();                 // adjust layout to playform

	}

	private boolean playGUI(int c){
		state = getState();
		if (c-1<0 || c-1>6 || state[c-1][5]!=0)
			return false;
		// return false if column is invalid or top row is not empty

		play(c);
		// play column c and switch player

		state = getState();
		for (int j=0; j<6; j++){
			for (int i=0; i<7; i++){
				if (state[i][j] == 1)
					this_board.paint(i+1, j+1, 1);
				else if (state[i][j] == -1){
					this_board.paint(i+1, j+1, -1);
				}
			}
		}
		// go through each grid and paint the one played previously

		frame.revalidate();
		frame.repaint();

		if (isGameOver()){
			if (winner() == 0){
				frame.getContentPane().remove(panel);
				JPanel newPanel = new JPanel();
				frame.getContentPane().add(newPanel, BorderLayout.NORTH);
				newPanel.add(new JLabel("It's a draw."));
			}
			// if draw, remove old panel1 and attach a new panel in its place
			
			else if (winner() == 1){
				frame.getContentPane().remove(panel);
				JPanel newPanel = new JPanel();
				frame.getContentPane().add(newPanel, BorderLayout.NORTH);
				newPanel.add(new JLabel(red.getName() + " wins!"));
			}
			// if red player wins

			else if (winner() == -1){
				frame.getContentPane().remove(panel);
				JPanel newPanel = new JPanel();
				frame.getContentPane().add(newPanel, BorderLayout.NORTH);
				newPanel.add(new JLabel(black.getName() + " wins!"));
			}
			// if black player wins

			frame.setVisible(true);
			frame.revalidate();
			frame.repaint();
			frame.pack();
		}
		return true;
	}


	private class GameBoard extends javax.swing.JPanel{
		private GameBoard() {
			this.setLayout(new GridLayout(6,7));
			for (int i=0; i<42; i++){
				grids[i] = new JLabel();
				grids[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				grids[i].setPreferredSize(new Dimension(30, 30));
	            grids[i].setOpaque(true);
	            grids[i].setBackground(Color.WHITE);
	            this.add(grids[i]);
			}
		}
		// initialize empty board with 42 grids of label

		private void paint(int x, int y, int color){
			int index = (6-y)*7+x;
			if (color == 1)
				grids[index-1].setBackground(Color.RED);
			else if (color == -1)
				grids[index-1].setBackground(Color.BLACK);
		}
		// paints specified coordinate red(1) or black(-1)
		// input x is column number [1,7], y is row number [1,6]
	}


	private class HumanPlayer implements CFPlayer{
		@Override
		public int nextMove(CFGame g){
			return 0;
		}

		@Override
		public String getName() {
			return "Human Player";
		}
	}


	private class playBL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			GUICF g = GUICF.this;
			int choice;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				while (!playGUI(choice)){}
			}
			// red player's turn

			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				while (!playGUI(choice)){}
			}
			// black player's turn
		}
	}
	// implement ActionListener for ai vs ai

	private class arrowBL1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int choice;
			GUICF g = GUICF.this;
			if (g.isGameOver())
				return;

			if (g.isRedTurn())
				playGUI(1);
			// play in response to the user's demand

			if (g.isGameOver())
				return;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				playGUI(choice);
			}
			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				playGUI(choice);
			}
			// plat the ai
		}
	}
	// ActionListener for arrow button 1

	private class arrowBL2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int choice;
			GUICF g = GUICF.this;
			if (g.isGameOver())
				return;

			playGUI(2);
			// play in response to the user's demand

			if (g.isGameOver())
				return;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				playGUI(choice);
			}
			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				playGUI(choice);
			}
			// plat the ai
		}
	}
	// ActionListener for arrow button 2


	private class arrowBL3 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int choice;
			GUICF g = GUICF.this;
			if (g.isGameOver())
				return;

			playGUI(3);
			// play in response to the user's demand

			if (g.isGameOver())
				return;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				playGUI(choice);
			}
			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				playGUI(choice);
			}
			// plat the ai
		}
	}
	// ActionListener for arrow button 3

	private class arrowBL4 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int choice;
			GUICF g = GUICF.this;
			if (g.isGameOver())
				return;

			playGUI(4);
			// play in response to the user's demand

			if (g.isGameOver())
				return;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				playGUI(choice);
			}
			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				playGUI(choice);
			}
			// plat the ai
		}
	}
	// ActionListener for arrow button 4

	private class arrowBL5 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int choice;
			GUICF g = GUICF.this;
			if (g.isGameOver())
				return;

			playGUI(5);
			// play in response to the user's demand

			if (g.isGameOver())
				return;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				playGUI(choice);
			}
			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				playGUI(choice);
			}
			// plat the ai
		}
	}
	// ActionListener for arrow button 5

	private class arrowBL6 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int choice;
			GUICF g = GUICF.this;
			if (g.isGameOver())
				return;

			playGUI(6);
			// play in response to the user's demand

			if (g.isGameOver())
				return;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				playGUI(choice);
			}
			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				playGUI(choice);
			}
			// plat the ai
		}
	}
	// ActionListener for arrow button 6

	private class arrowBL7 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int choice;
			GUICF g = GUICF.this;
			if (g.isGameOver())
				return;

			playGUI(7);
			// play in response to the user's demand

			if (g.isGameOver())
				return;

			if (g.isRedTurn()){
				choice = red.nextMove(g);
				while (choice == 0)
					choice = red.nextMove(g);
				playGUI(choice);
			}
			else{
				choice = black.nextMove(g);
				while (choice == 0)
					choice = black.nextMove(g);
				playGUI(choice);
			}
			// plat the ai
		}
	}
	// ActionListener for arrow button 7

	/*
	//testing code
    public static void main(String[] args){
        CFPlayer ai1 = new YunningAI();
        CFPlayer ai2 = new RandomAI();
        GUICF game = new GUICF (ai1, ai2);
        // GUICF game = new GUICF(ai1);
    }
    */

}

