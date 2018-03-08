package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Container class for main method which creates a Tetris object and 
 * starts the control flow. 
 * 
 * @author Trent Fowler
 *
 */
public class Main {

	public static void main(String[] args) {
		final Tetris tetris = new Tetris();
		tetris.init();
		new Thread() {
			@Override public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {}
				}
			}
		}.start();
		JFrame frame = new JFrame(); 
		frame.setContentPane(tetris);
		frame.setSize(400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//TODO Implement key listeners
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

}

class Piece {
	
	/*
	 * Constants
	 */
	private final int NUMBER_OF_SHAPES = 7;
	private final int L_SHAPE = 0;
	private final int SQUARE_SHAPE = 1;
	private final int Z_SHAPE = 2;
	private final int LINE_SHAPE = 3;
	private final int T_SHAPE = 4;
	private final int BACKWARDS_L_SHAPE = 5;
	private final int BACKWARDS_Z_SHAPE = 6;

	private final Point[][] RIGHT_L = {
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1)},
			{new Point(0,2), new Point(1,2), new Point(1,1), new Point(1,0)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1)}
	}; //TODO More tedious shape adding. Zzzzzz....
	
	/*
	 * Variables
	 */
	private Point origin;
	private int shape;
	private int orientation;
	
	/**
	 * Default constructor, selects a random shape and uses the default origin.
	 */
	Piece() {
		this.origin = new Point(5,18);
		Random rand = new Random();
		this.shape = rand.nextInt(this.NUMBER_OF_SHAPES);
		this.orientation = 0;
	}

	void rotate() {
		if (this.orientation == 3) 
			this.orientation = 0;
		else this.orientation++;
		
	}
	
	void down() {
		this.origin.x -= 1;
	}
	
}

class Tetris extends JPanel {
	
	/*
	 * Variables
	 */
	int[][] board = new int[][]{
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0},
			 {0,0,0,0,0,0,0,0,0,0}
	};
	Piece piece = new Piece();
	Piece nextPiece = new Piece();
	List<Piece> pieces = new ArrayList<Piece>();
	
	/**
	 * Initialize the game board. 
	 */
	void init() {

	}
	
	/**
	 * Clear rows.
	 */
	void clear() {
		
	}

	/*
	 * Overridden paintComponent method to gain access to Graphics obj. 
	 * Draws the game board. 
	 * 
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override public void paintComponent(Graphics g) {
		this.setBackground(Color.WHITE);
		//TODO: 
	}
}