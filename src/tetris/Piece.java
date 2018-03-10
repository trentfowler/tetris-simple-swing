package tetris;

import java.awt.Point;
import java.util.Random;

public class Piece {

	enum Shape {
		L,
		SQUARE,
		Z,
		LINE,
		T,
		BACK_L,
		BACK_Z
	}

	private final Point[][] L = {
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2)},
			{new Point(2,0), new Point(1,0), new Point(0,0), new Point(0,1)},
			{new Point(1,2), new Point(1,1), new Point(1,0), new Point(0,0)},
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
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0)}
	}; 
	private final Point[][] T = { 
			{new Point(1,0), new Point(0,1), new Point(1,1), new Point(2,1)},
			{new Point(1,0), new Point(1,1), new Point(1,2), new Point(0,1)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(1,1)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,1)}
	}; 
	private final Point[][] BACK_L = {
			{new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2)},
			{new Point(0,0), new Point(0,1), new Point(1,0), new Point(2,0)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2)},
			{new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1)}
	}; 
	private final Point[][] BACK_Z = {
			{new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2)},
			{new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2)}
	};

	private int orientation;
	private Shape shape;
	private Point[] points;

	/**
	 * Default constructor: Selects a random shape type.
	 */
	Piece() {
		orientation = 0;
		shape = Shape.values()[(new Random()).nextInt(Shape.values().length)];
		init();
	}

	/**
	 * Initializes the piece by setting the starting location
	 * and repainting the board.
	 */
	private void init() {
		points = new Point[L[0].length];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(
					get_base_shape_for(shape)[orientation][i].x,
                    get_base_shape_for(shape)[orientation][i].y
			);
		}
	}

	/**
     * Rotates the piece. Rotation is clockwise, 90 degrees.
	 */
	void rotate() {
		Point[][] base = get_base_shape_for(shape);

		if (orientation == 3)
			orientation = 0;
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

        if (move == Tetris.Move.RIGHT)
			for (int i = 0; i < points.length; i++)
				points[i].x++;

        if (move == Tetris.Move.DOWN)
        	for (int i = 0; i < points.length; i++)
			points[i].y++;

        if (move == Tetris.Move.ROTATE)
        	rotate();
	}

	/**
	 * TODO: Needs attention.
	 * @return
	 */
	Point[] points() {
		return points;
	}

	/*
	 * Helper method, takes shape enum name and
	 * returns the actual shape.
	 */
	private Point[][] get_base_shape_for(Shape s) {
		switch (this.shape) {
			case L:
				return L;
			case SQUARE:
				return SQUARE;
			case Z:
				return Z;
			case LINE:
				return LINE;
			case T:
				return T;
			case BACK_L:
				return BACK_L;
			case BACK_Z:
				return BACK_Z;
			default: return null;
		}
	}

}
