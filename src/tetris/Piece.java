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
		case BACK_L:
			Point[] rl = this.BACK_L[this.orientation];
			for (int i = 0; i < rl.length; i++) {
				this.points[i] = new Point((rl[i].x),
						(rl[i].y));
			}
			break;
		case BACK_Z:
			Point[] rz = this.BACK_Z[this.orientation];
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

	void rotate() {
	    Point[][] base = L;
		switch (this.shape) {
			case SQUARE:
				base = SQUARE;
				break;
			case Z:
				base = Z;
				break;
			case LINE:
				base = LINE;
				break;
			case T:
				base = T;
				break;
			case BACK_L:
				base = BACK_L;
				break;
			case BACK_Z:
				base = BACK_Z;
				break;
		}

		int x_offset = points[0].x - base[orientation][0].x;
		int y_offset = points[0].y - base[orientation][0].y;

		//TODO: Debug code for eventual removal.
		System.out.println("x_offset= " + x_offset + ", y_offset= " + y_offset);

		if (orientation == 3)
			orientation = 0;
		else orientation++;

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
}
