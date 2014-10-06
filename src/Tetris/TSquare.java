package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import BasicGraphic.Square;

public class TSquare extends Square{
	private int size;
	private Color clr;
	/*
	 * the relative coordinate of the square in a block,
	 * the left bottom square is the original, and its relative coordinate is (1,1)
	 */
	private Point2D sqCoordinate;	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Point2D getSqCoordinate() {
		return sqCoordinate;
	}

	public void setSqCoordinate(Point2D sqCoordinate) {
		this.sqCoordinate = sqCoordinate;
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

}
