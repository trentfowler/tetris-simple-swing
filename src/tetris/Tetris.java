package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Tetris extends JPanel {

	private static final long serialVersionUID = 1L;

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

	//TODO Implement method
	boolean canMove(Move move) {
		for (Move m: Move.values()) {
			if (m == move) {
				switch (m) {
				case LEFT:
					break;
				case RIGHT:
					break;
				case DOWN:
					break;
				case ROTATE:
					break;
				}

			}
		}
		return true;
	}
	
	/**
	 * Method for moving the currently falling piece after a 
	 * key press.
	 * 
	 * @param move
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
	
	/**
	 * Draws the game board, called on each repaint. Overridden 
	 * 'paintComponent' method allows access to Graphics object needed 
	 * to draw directly on JPanel. 
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
