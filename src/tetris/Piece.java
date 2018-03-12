package tetris;

import java.awt.*;
import java.util.Random;

public class Piece {

	// Constants - Shape definitions.
	enum Shape {L, SQUARE, Z, LINE, T, BACK_L, BACK_Z}
	private final Point[][] L = {
        {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2)},
        {new Point(0,0), new Point(1,0), new Point(2,0), new Point(0,1)},
        {new Point(0,0), new Point(1,1), new Point(1,0), new Point(1,2)},
        {new Point(0,1), new Point(1,1), new Point(2,1), new Point(2,0)}
	}; 
	private final Point[][] SQUARE = { 
        {new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1)},
        {new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1)},
        {new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1)},
        {new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1)}
	}; 
	private final Point[][] Z = { 
        {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)},
        {new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1)},
        {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)},
        {new Point(0,1), new Point(0,2), new Point(1,0), new Point(1,1)}
	}; 
	private final Point[][] LINE = { 
        {new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3)},
        {new Point(0,1), new Point(1,1), new Point(2,1), new Point(3,1)},
        {new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3)},
        {new Point(0,1), new Point(1,1), new Point(2,1), new Point(3,1)}
	};
	private final Point[][] T = { 
        {new Point(1,0), new Point(0,1), new Point(1,1), new Point(2,1)},
        {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,1)},
        {new Point(0,0), new Point(1,0), new Point(2,0), new Point(1,1)},
        {new Point(1,0), new Point(0,1), new Point(1,1), new Point(1,2)}
	}; 
	private final Point[][] BACK_L = {
        {new Point(1,0), new Point(1,1), new Point(1,2), new Point(0,2)},
        {new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1)},
        {new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0)},
        {new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1)}
	}; 
	private final Point[][] BACK_Z = {
        {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)},
        {new Point(1,0), new Point(1,1), new Point(0,1), new Point(0,2)},
        {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)},
        {new Point(1,0), new Point(1,1), new Point(0,1), new Point(0,2)}
	};

	// Variables.
	private int orientation;
	private Shape shape;
	private Color color;
	private Point[] points;

	/**
	 * Default constructor, initializes the piece with random shape.
	 */
	Piece() { init(); }

	/**
	 * Initializes the piece.
	 *
	 * Method:
	 * 1. Creates a random piece with the default rotation.
	 * 2. Sets color based on piece type.
	 * 3. Stages shape in the starting location.
	 */
	private void init() {
	    // #1
		shape = Shape.values()[(new Random()).nextInt(Shape.values().length)];
		orientation = 0;
		// #2
		for (int i = 0; i < Shape.values().length; i++)
			if (shape == Shape.values()[i])
				color = (new Color[]{Color.BLUE, Color.ORANGE, Color.YELLOW,
						Color.GREEN, Color.RED, Color.CYAN, Color.PINK})[i];
		// #3
		points = new Point[L[0].length];
		for (int i = 0; i < points.length; i++)
			points[i] = new Point(
					get_base_shape_for(shape)[orientation][i].x,
                    get_base_shape_for(shape)[orientation][i].y
			);
	}

	/**
     * Rotates the piece. Rotation is clockwise, 90 degrees per method call.
	 */
	void rotate() {
		Point[][] base = get_base_shape_for(shape);

		if (orientation == 3) orientation = 0;
		else orientation++;

		int x_offset = points[0].x - base[orientation][0].x;
		int y_offset = points[0].y - base[orientation][0].y;

		//TODO: Debug code, for eventual removal.
		System.out.println("x_offset= " + x_offset + ", y_offset= " + y_offset);

		int i = 0;
		for (Point point: base[orientation]) {
			points[i] = point;
			i++;
		}

		for (int j = 0; j < points.length; j++) {
			points[j].x += x_offset;
			points[j].y += y_offset;
		}
	}

	/**
	 * Performs the requested move operation on piece.
	 * Checking for collisions is not done here, but should
	 * be done before calling this method.
	 *
	 * @param move
	 */
	void move(Tetris.Move move) {
        if (move == Tetris.Move.LEFT)
        	for (int i = 0; i < points.length; i++)
        		points[i].x--;
        else if (move == Tetris.Move.RIGHT)
			for (int i = 0; i < points.length; i++)
				points[i].x++;
        else if (move == Tetris.Move.DOWN)
        	for (int i = 0; i < points.length; i++)
				points[i].y++;
        else if (move == Tetris.Move.ROTATE)
        	rotate();
	}

	/**
	 * Returns the piece color.
	 *
	 * @return	java.awt.Color value.
	 */
	Color color() { return color; }

	/**
	 * Returns java.awt.Point array containing
	 * the (x,y) indexes that this piece occupies.
	 *
 	 * @return	Point[].
	 */
	Point[] occupies() { return points; }

	/*
	 * Helper method, takes shape enum name and
	 * returns the actual shape.
	 */
	private Point[][] get_base_shape_for(Shape s) {
		switch (this.shape) {
			case L: return L;
			case SQUARE: return SQUARE;
			case Z: return Z;
			case LINE: return LINE;
			case T: return T;
			case BACK_L: return BACK_L;
			case BACK_Z: return BACK_Z;
			default: return null;
		}
	}

}
