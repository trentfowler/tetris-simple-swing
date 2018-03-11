package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

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

	private Piece piece;
	private Board board;

	/**
	 * Default constructor. 
	 */
	Tetris() {
		piece = new Piece();
		board = new Board();
	}

	/**
	 * Check if piece can move, and update piece
	 * location and board accordingly.
     *
	 * @param direction
	 */
	void move(Move direction) {

		// Check if piece can move, update location accordingly.
		if (board.can_move(piece, direction)) {
			piece.move(direction);
		}

		// Bottom check, adds new piece as needed.
		if (board.did_hit_bottom(piece)) {
		    board.add(piece);
		    piece = new Piece();
		}

		repaint();

		//TODO: Debug code, for eventual removal.
		System.out.println(board);

		//TODO: Debug code, for eventual removal.
        for (Point p: piece.occupies())
        	System.out.print("(" + p.x + "," + p.y + ")");
        System.out.println();
	}

	/**
	 * Draws the game board, called on each repaint. Overridden 
	 * method allows access to Graphics object needed
	 * to draw directly on JPanel. 
	 */
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw background.
        setBackground(Color.DARK_GRAY);

		// Draw board.
		for (int col = 0; col < 10; col++) {
			for (int row = 0; row < 20; row++) {
			    if (board.get()[row][col] != Color.DARK_GRAY) {
					g.setColor(board.get()[row][col]);
					g.fillRect(col*40, row*40, 40, 40);
					g.setColor(Color.BLACK);
					g.drawRect(col*40, row*40, 40, 40);
				}
			}
		}

		// Draw active piece.
        for (int i = 0; i < piece.occupies().length; i++) {
			g.setColor(piece.color());
			g.fillRect(piece.occupies()[i].x*40,
					piece.occupies()[i].y*40, 40, 40);
			g.setColor(Color.BLACK);
			g.drawRect(piece.occupies()[i].x*40,
					piece.occupies()[i].y*40, 40, 40);
		}
	}

}

