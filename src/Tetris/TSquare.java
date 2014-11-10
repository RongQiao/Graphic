package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import BasicGraphic.Square;

public class TSquare extends Square{
	private int size;
	private Color clr;
	/*
	 * the relative coordinate of the square in a block,
	 * the left bottom square is the origin, and its relative coordinate is (1,1)
	 */
	private Point2D sqCoordinate;	

	public TSquare(TSquare src) {
		size = src.getSize();
	}

	public TSquare() {
		// TODO Auto-generated constructor stub
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}	

	public int getX() {
		return (int)this.getSqCoordinate().getX();
	}
	
	public int getY() {
		return (int)this.getSqCoordinate().getY();
	}
	
	public Point2D getSqCoordinate() {
		return sqCoordinate;
	}

	public void setSqCoordinate(Point2D sqCoordinate) {
		this.sqCoordinate = sqCoordinate;
	}
	
	public void setSqCoordinate(int x, int y) {
		setSqCoordinate(new Point(x, y));
	}



	public void draw(Graphics g) {
		int X = (int)this.getFirstVertex().getX();
		int Y = (int)this.getFirstVertex().getY();
		g.setColor(this.getColor());
		g.fillRect(X, Y, getSize(), getSize());
		g.setColor(Color.BLACK);
		g.drawRect(X, Y, getSize(), getSize());
	}

	public Color getColor() {
		return clr;
	}

	public void setColor(Color clr) {
		this.clr = clr;
	}

	public boolean equals(TSquare sq) {
		boolean ret = false;
		if ((this.getSqCoordinate().getX() == sq.getSqCoordinate().getX())
				&& (this.getSqCoordinate().getY() == sq.getSqCoordinate().getY())) {
			ret = true;
		}
		return ret;
	}

	public boolean isInSquare(int x, int y) {
		int X = (int)this.getFirstVertex().getX();
		int Y = (int)this.getFirstVertex().getY();
		int size = getSize();
		boolean ret = false;
		
		if ((x >= X) && (x <= X+size)
			&& (y >= Y) && (y <= Y+size)) {
			ret = true;
		}
		return ret;
	}

}
