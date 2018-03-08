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
			{new Point(0,0), new Point(0,1), new Point(0,2), new Point(1,0)},
			{new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1)},
			{new Point(0,2), new Point(1,2), new Point(1,1), new Point(1,0)},
			{new Point(0,0), new Point(1,0), new Point(2,0), new Point(2,1)}
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
		this.x = 4;
		this.y = 0;
		this.init();
	}
	
	private void init() {
		this.points = new Point[this.L[0].length];
		switch (this.shape) {
		case L:
			Point[] l = this.L[this.orientation];
			for (int i = 0; i < l.length; i++) {
				this.points[i] = new Point((l[i].x + this.x), 
						(l[i].y + this.y));
			}
			break;
		case Z:
			Point[] z = this.Z[this.orientation];
			for (int i = 0; i < z.length; i++) {
				this.points[i] = new Point((z[i].x + this.x),
						(z[i].y + this.y));
			}
			break;
		case T:
			Point[] t = this.T[this.orientation];
			for (int i = 0; i < t.length; i++) {
				this.points[i] = new Point((t[i].x + this.x),
						(t[i].y + this.y));
			}
			break;
		case SQUARE:
			Point[] s = this.SQUARE[this.orientation];
			for (int i = 0; i < s.length; i++) {
				this.points[i] = new Point((s[i].x + this.x),
						(s[i].y + this.y));
			}
			break;
		case LINE:
			Point[] li = this.LINE[this.orientation];
			for (int i = 0; i < li.length; i++) {
				this.points[i] = new Point((li[i].x + this.x),
						(li[i].y + this.y));
			}
			break;
		case REVERSE_L:
			Point[] rl = this.REVERSE_L[this.orientation];
			for (int i = 0; i < rl.length; i++) {
				this.points[i] = new Point((rl[i].x + this.x),
						(rl[i].y + this.y));
			}
			break;
		case REVERSE_Z: 
			Point[] rz = this.REVERSE_Z[this.orientation];
			for (int i = 0; i < rz.length; i++) {
				this.points[i] = new Point((rz[i].x + this.x),
						(rz[i].y + this.y));
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

	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}
	
	void rotate() {
		// tracks the current rotation
		if (this.orientation == 3)
			this.orientation = 0;
		else this.orientation++;
		
		// rotates the piece
		switch (this.shape) {
		case L:
			Point[] l = this.L[this.orientation];
			for (int i = 0; i < l.length; i++) {
				this.points[i] = new Point((l[i].x + this.x), 
						(l[i].y + this.y));
			}
			break;
		case Z:
			Point[] z = this.Z[this.orientation];
			for (int i = 0; i < z.length; i++) {
				this.points[i] = new Point((z[i].x + this.x),
						(z[i].y + this.y));
			}
			break;
		case T:
			Point[] t = this.T[this.orientation];
			for (int i = 0; i < t.length; i++) {
				this.points[i] = new Point((t[i].x + this.x),
						(t[i].y + this.y));
			}
			break;
		case SQUARE:
			Point[] s = this.SQUARE[this.orientation];
			for (int i = 0; i < s.length; i++) {
				this.points[i] = new Point((s[i].x + this.x),
						(s[i].y + this.y));
			}
			break;
		case LINE:
			Point[] li = this.LINE[this.orientation];
			for (int i = 0; i < li.length; i++) {
				this.points[i] = new Point((li[i].x + this.x),
						(li[i].y + this.y));
			}
			break;
		case REVERSE_L:
			Point[] rl = this.REVERSE_L[this.orientation];
			for (int i = 0; i < rl.length; i++) {
				this.points[i] = new Point((rl[i].x + this.x),
						(rl[i].y + this.y));
			}
			break;
		case REVERSE_Z: 
			Point[] rz = this.REVERSE_Z[this.orientation];
			for (int i = 0; i < rz.length; i++) {
				this.points[i] = new Point((rz[i].x + this.x),
						(rz[i].y + this.y));
			}
			break;
		} 
	}
}

