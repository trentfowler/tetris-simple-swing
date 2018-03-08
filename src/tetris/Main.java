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
		new Thread() {
			@Override public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						tetris.move(Tetris.Move.DOWN);
					} catch (Exception e) { }
				}
			}
		}.start();
		JFrame frame = new JFrame(); 
		frame.setContentPane(tetris);
		frame.setSize(400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) { }
			@Override public void keyPressed(KeyEvent e) { 
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					tetris.move(Tetris.Move.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					tetris.move(Tetris.Move.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					tetris.move(Tetris.Move.RIGHT);
					break;
				case KeyEvent.VK_UP:
					tetris.move(Tetris.Move.ROTATE);
					break;
				}
			}
			@Override public void keyReleased(KeyEvent e) { }
		});
	}

}

class Piece {
	enum Shape {
		L,
		SQUARE,
		Z,
		LINE,
		T,
		REVERSE_L,
		REVERSE_Z
	}
	// Constants
	private final int DEFAULT_X = 4;
	private final int DEFAULT_Y = 0;
	private final Point[][] L = { 
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1)},
			{new Point(0,2), new Point(1,2), new Point(1,1), new Point(1,0)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1)}
	}; 
	//TODO More tedious shape adding. Zzzzzz....
	private int x; 
	private int y;
	private Shape shape;
	private int orientation;
	private Point[] points; 
	
	/**
	 * Default constructor, selects a random shape and uses the default origin.
	 */
	Piece() {
		Random rand = new Random();
		Shape[] shapes = Shape.values();
		this.shape = shapes[rand.nextInt(shapes.length)];
		this.orientation = 0;
		this.x = this.DEFAULT_X;
		this.y = this.DEFAULT_Y;
		this.init();
	}
	
	private void init() {
		switch (this.shape) {
		case L:
			List<Point> ps = new ArrayList<Point>();
			for (int i = 0; i < this.L[this.orientation].length; i++) {
				ps.add(new Point(this.L[this.orientation][i]));
			}
			this.points = new Point[ps.size()];
			this.points = ps.toArray(this.points);
			break;
		case SQUARE:
			this.points = this.L[this.orientation]; //TODO
			break;
		case Z:
			this.points = this.L[this.orientation]; //TODO
			break;
		case LINE:
			this.points = this.L[this.orientation]; //TODO
			break;
		case T: 
			this.points = this.L[this.orientation]; //TODO
			break;
		case REVERSE_L:
			this.points = this.L[this.orientation]; //TODO
			break; 
		case REVERSE_Z: 
			this.points = this.L[this.orientation]; //TODO
			break; 
		}
		int i = 0;
		for (Point p: this.points) {
			this.points[i] = new Point((p.x + this.x),(p.y + this.y));
			i++;
		}
	}
	
	Point[] points() {
		return this.points;
	}

	int getOrientation() {
		return this.orientation;
	}

	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}
	
	void rotate() {
		if (this.orientation == 3)
			this.orientation = 0;
		else this.orientation++;
		switch (this.shape) {
		case L:
			Point[] p = this.L[this.orientation];
			for (int i = 0; i < p.length; i++) {
				this.points[i] = new Point((p[i].x + this.x), 
						(p[i].y + this.y));
			}
			break;
		case Z:
			break;
		case T:
			break;
		case SQUARE:
			break;
		case LINE:
			break;
		case REVERSE_L:
			break;
		case REVERSE_Z: 
			break;
		} 
	}
}

class Tetris extends JPanel {

	enum Move {
		LEFT,
		RIGHT,
		DOWN,
		DROP,
		ROTATE
	}
	
	Piece curr_piece = new Piece();
	Piece next_piece = new Piece();

	List<Piece> pieces = new ArrayList<Piece>();
	
	/**
	 * Default constructor. 
	 */
	Tetris() {
		curr_piece = new Piece();
		next_piece = new Piece();
		pieces.add(curr_piece);
	}
	
	/**
	 * Check for complete rows and clear them.
	 */
	void clear() {

	}
	
	/**
	 * Move the pieces every 1 second. 
	 */
	void move(Move move) {
		for (Move m: Move.values()) {
			if (m == move) {
				Point[] p = this.curr_piece.points();
				switch (m) {
				case LEFT:
					// if no collision
					for (int i = 0; i < p.length; i++) 
						p[i].x -= 1;
					break;
				case RIGHT:
					// if no collision
					for (int i = 0; i < p.length; i++)
						p[i].x += 1;
					break;
				case DOWN:
					// if no collision
					for (int i = 0; i < p.length; i++)
						p[i].y += 1;
					break;
				case DROP:
					break;
				case ROTATE:
					this.curr_piece.rotate();
					break;
				}
			}
		}
		repaint();
	}
	
	/*
	 * Overridden paintComponent method to gain access to Graphics obj. 
	 * Draws the game board. 
	 * 
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override public void paintComponent(Graphics g) {
		//draw the board
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 20; row++) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(col*40, row*40, 40, 40);
				g.setColor(Color.GRAY);
				g.drawRect(col*40, row*40, 40, 40);
			}
		}
		//add the pieces
		for (Piece p: this.pieces) {
			for (Point t: p.points()) {
				g.setColor(Color.RED);
				g.fillRect((int)(40*t.getX()), (int)(40*t.getY()), 40, 40);
				g.setColor(Color.BLACK);
				g.drawRect((int)(40*t.getX()), (int)(40*t.getY()), 40, 40);
			}
		}
	}
}