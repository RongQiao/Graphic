package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

public abstract class TBlock {
	/*
	 * the relative coordinate of the left bottom square of the block in the container, 
	 * the left bottom square in the container is (1,1)
	 */
	public Point2D blkCoordinate;
	public Color clr;
	public TSquare sq[];
	public TBlockBox container;

	public TBlock() {
		setNumSquare(4);
	}
	
	public TBlock(TBlockBox box) {
		setNumSquare(4);
		container = box;
		container.addBlock(this);
	}
	
	protected void deepCopy(TBlock src) {
		sq = new TSquare[src.getNumSquare()];
		for (int i = 0; i < sq.length; i++) {
			sq[i] = new TSquare(src.sq[i]);
		}
		this.setBlkCoordinate((int)src.getBlkCoordinate().getX(), (int)src.getBlkCoordinate().getY());
		this.setColor(src.getColor());
	}
	
	public int getNumSquare() {
		return sq.length;
	}

	public void setNumSquare(int numSquare) {
		sq = new TSquare[numSquare];
		for (int i = 0; i < sq.length; i++) {
			sq[i] = new TSquare();
		}
	}

	public Point2D getBlkCoordinate() {
		return blkCoordinate;
	}

	public void setBlkCoordinate(int X, int Y) {
		this.blkCoordinate = new Point(X, Y);
	}

	public void setSqSize(int size) {
		for (int i = 0; i < getNumSquare(); i++) {
			sq[i].setSize(size);
		}
	}

	public abstract void init();
	
	public void draw(Graphics g) {
		Point pFirstSqLeftTop = caculateFirstSqLeftTop();
		int sqSize = this.container.getSquareSize();
		for (int i = 0; i < getNumSquare(); i++) {
			int x = (int) sq[i].getSqCoordinate().getX();
			int y = (int) sq[i].getSqCoordinate().getY();
			int sqX = pFirstSqLeftTop.x + sqSize * (x - 1);
			int sqY = pFirstSqLeftTop.y - sqSize * (y - 1);
			sq[i].setFirstVertex(sqX, sqY);
			sq[i].draw(g);
		}
	}
	
	public Point caculateFirstSqLeftTop() {
		Point2D pLB = this.container.getLeftBottomVertex();
		Point2D location = this.getBlkCoordinate();
		int sqSize = this.container.getSquareSize();
		int blkX = (int) pLB.getX();
		blkX = blkX + sqSize * ((int)location.getX() - 1);
		int blkY = (int) pLB.getY();
		blkY = blkY - sqSize * ((int)location.getY());
		
		return new Point(blkX, blkY);
	}

	public void setColor(Color c) {
		this.clr = c;
	}
	
	public Color getColor() {
		return this.clr;
	}

	public void init(int x, int y, Color c) {
		this.setBlkCoordinate(x, y);
		this.setColor(c);
		this.init();
	}

	public void init(int x, int y) {
		this.setBlkCoordinate(x, y);
		this.init();
	}
	
	public void init(Color c) {
		init(1, 1, c);
	}

	public abstract int getSqNum_Width();
	public abstract int getSqNum_Height();

	public TBox getBlkVirtualBox() {
		TBox b = new TBox();
		int xMin = 0, xMax = 0, yMin = 0, yMax = 0;
		//find the min & max relative coordinate first
		for (int i = 0; i < this.getNumSquare(); i++) {
			Point p = (Point)sq[i].getSecondVertex();
			if (p.x >= xMin) {
				xMin = p.x;
			}
			if (p.x <= xMax) {
				xMax = p.x;
			}
			if (p.y >= yMin) {
				yMin = p.y;
			}
			if (p.y <= yMax) {
				yMax = p.y;
			}
		}
		//caculate the real coordinate
		int X = (int)this.getBlkCoordinate().getX();
		int Y = (int)this.getBlkCoordinate().getY();
		int sqSize = this.container.getSquareSize();
		int W = sqSize * (xMax - xMin + 1);
		int H = sqSize * (yMax - yMin + 1);
		b.setFirstVertex(X, Y - H);
		b.setSecondVertex(X + W, Y - H);
		b.setThirdVertex(X + W, Y);
		b.setFourthVertex(X, Y);
		return b;
	}

	public void setContainer(TBlockBox box) {
		this.container = box;
	}

	public TBlockBox getContainer() {
		return this.container;
	}
	
	public void moveToContainer(TBlockBox box) {
		//remove from previous container first
		this.getContainer().removeBlock(this);
		//set new container
		this.setContainer(box);
		//add into new container
		box.addBlock(this);
	}

}
