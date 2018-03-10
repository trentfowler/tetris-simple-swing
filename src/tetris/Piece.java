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
		REVERSE_L,
		REVERSE_Z
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
	private final Point[][] REVERSE_L = { 
			{new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2)},
			{new Point(0,0), new Point(0,1), new Point(1,0), new Point(2,0)},
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,2)},
			{new Point(0,1), new Point(1,1), new Point(2,0), new Point(2,1)}
	}; 
	private final Point[][] REVERSE_Z = { 
			{new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2)},
			{new Point(0,1), new Point(1,0), new Point(1,1), new Point(2,1)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2)}
	}; 
	
	private Shape shape;
	private int orientation;
	private Point[] points; 
	
	/**
	 * Default constructor, selects a random shape and uses the default origin.
	 */
	Piece() {
		Random rand = new Random();
		Shape[] shapes = Shape.values();
		//this.shape = shapes[rand.nextInt(shapes.length)];
		this.shape = shapes[0]; //TODO
		this.orientation = 0;
		this.init();
	}
	
	private void init() {
		this.points = new Point[this.L[0].length];
		switch (this.shape) {
		case L:
			Point[] l = this.L[this.orientation];
			for (int i = 0; i < l.length; i++) {
				this.points[i] = new Point((l[i].x),
						(l[i].y));
			}
			break;
		case Z:
			Point[] z = this.Z[this.orientation];
			for (int i = 0; i < z.length; i++) {
				this.points[i] = new Point((z[i].x),
						(z[i].y));
			}
			break;
		case T:
			Point[] t = this.T[this.orientation];
			for (int i = 0; i < t.length; i++) {
				this.points[i] = new Point((t[i].x),
						(t[i].y));
			}
			break;
		case SQUARE:
			Point[] s = this.SQUARE[this.orientation];
			for (int i = 0; i < s.length; i++) {
				this.points[i] = new Point((s[i].x),
						(s[i].y));
			}
			break;
		case LINE:
			Point[] li = this.LINE[this.orientation];
			for (int i = 0; i < li.length; i++) {
				this.points[i] = new Point((li[i].x),
						(li[i].y));
			}
			break;
		case REVERSE_L:
			Point[] rl = this.REVERSE_L[this.orientation];
			for (int i = 0; i < rl.length; i++) {
				this.points[i] = new Point((rl[i].x),
						(rl[i].y));
			}
			break;
		case REVERSE_Z: 
			Point[] rz = this.REVERSE_Z[this.orientation];
			for (int i = 0; i < rz.length; i++) {
				this.points[i] = new Point((rz[i].x),
						(rz[i].y));
			}
			break;
		}
	}
	
	Point[] points() {
		return this.points;
	}

	int getOrientation() {
		return this.orientation;
	}

	void rotate() {

		int x_offset = points[0].x - L[orientation][0].x;
		int y_offset = points[0].y - L[orientation][0].y;

		System.out.println("x_offset= " + x_offset + ", y_offset= " + y_offset);

		if (orientation == 3)
			orientation = 0;
		else orientation++;

		int i = 0;
		for (Point point: L[orientation]) {
			points[i] = point;
			i++;
		}

		for (int j = 0; j < points.length; j++) {
			points[j].x += x_offset;
			points[j].y += y_offset;
		}
	}
}
