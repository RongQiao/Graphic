package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import transformation.Transformation2D;
import transformation.Transformation2D.PositionDirection;

public abstract class TBlock {
	public enum MoveDirection {
		DOWN,
		LEFT,
		RIGHT
	}
	
	public enum RotateDirection {
		CLOCKWISE,
		CLOCKWISE_COUNTER
	}
	/*
	 * the relative coordinate of the origin of the block coordinate system in the container, 
	 * the left bottom square in the container is (1,1)
	 */
	public Point2D blkCoordinate;
	public Color clr;
	public TSquare sq[];	//the first one is the leftTop one: row1,row2...
	public TBlockBox container;
	public int sqNumWidth;
	public int sqNumHeight;
	public PositionDirection pd;	//the direction of the convex part 

	public TBlock() {		
		setNumSquare(4);
		pd = PositionDirection.CLOCK3;
	}
	
	public TBlock(TBlockBox box) {
		setNumSquare(4);
		pd = PositionDirection.CLOCK3;
		container = box;
		container.addBlock(this);
	}	

	public abstract void init();
	
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		return null;
	}
	
	public int getSqNum_Width() {
		return this.sqNumWidth;
	}
	
	public int getSqNum_Height() {
		return this.sqNumHeight;
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

	
	
	public void draw(Graphics g) {
		Point pOrigin = calculateOrigin();
		int sqSize = this.container.getSquareSize();
		for (int i = 0; i < getNumSquare(); i++) {
			int x = (int) sq[i].getSqCoordinate().getX();
			int y = (int) sq[i].getSqCoordinate().getY() + 1;
			int sqX = pOrigin.x + sqSize * (x);
			int sqY = pOrigin.y - sqSize * (y);
			sq[i].setFirstVertex(sqX, sqY);
			sq[i].draw(g);
		}
	}
	
	//calculate screen coordinate 
	public Point calculateOrigin() {
		Point2D pLB = this.container.getLeftBottomVertex();
		Point2D location = this.getBlkCoordinate();
		int sqSize = this.container.getSquareSize();
		int blkX = (int) pLB.getX();
		blkX = blkX + sqSize * ((int)location.getX() - 1);
		int blkY = (int) pLB.getY();
		blkY = blkY - sqSize * ((int)location.getY() - 1);
		
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

	public void move(MoveDirection direction, int cnt) {
		Point coord = (Point) this.getBlkCoordinate();
		int x = coord.x;
		int y = coord.y;
		switch (direction) {
		case DOWN:
			y = calculateYMoveDown(x, y, cnt);
			break;
		case LEFT:
			x = calculateXMoveLeft(x, y, cnt);
			break;
		case RIGHT:
			x = calculateXMoveRight(x, y, cnt);
			break;
		default:
			break;			
		}
		this.setBlkCoordinate(x, y);
	}
	
	private int calculateYMoveDown(int x, int y, int cnt) {
		y = y - cnt;
		return y;
	}

	private int calculateXMoveLeft(int x, int y, int cnt) {
		x = x - cnt;
		return x;
	}
	
	private int calculateXMoveRight(int x, int y, int cnt) {
		x = x + cnt;
		return x;
	}

	public boolean reachTopEdge(TBlockBox container) {
		int y = (int)this.getBlkCoordinate().getY();
		int edge = container.getTop(y, this.getSqNum_Width(), this);
		return (y <= edge) ? true : false;
	}

	public TBlock rotateClockwise() {
		PositionDirection newPd = pd.next(3);
		return transformate(pd, newPd);
	}

	public TBlock rotateClockwiseCounter() {
		PositionDirection newPd = pd.next(3*3);
		return transformate(pd, newPd);
	}	
	
	public TBlock transformate(PositionDirection oldPd, PositionDirection newPd) {
		int degree = Transformation2D.calculateRotaDegree(pd, newPd);		
		rotate(degree);
		translate();
		pd = newPd;
		return this;
	}
	
	protected TBlock rotate(int rotateDegree) {
//		int index = container.getIndex(this);
//		TBlock rotatedBlk = getRotatedBlk(direction);
//		container.replaceBlock(index, rotatedBlk);
//		return rotatedBlk;
		/*
		 * Rewrite rotate function with Geometric Rotation
		 * the original coordinate is based on origin bottom-left, we need change to top-left 
		 */
		for (int i = 0; i < this.sq.length; i++) {
			Point2D sqCoordinate = sq[i].getSqCoordinate();
			sqCoordinate = Transformation2D.calculateRotation(sqCoordinate, rotateDegree);
			sq[i].setSqCoordinate(sqCoordinate);
		}
		
		return this;
	}

	//make sure the most left.x == 0, most top.y == 0
	public void translate() {
		translateX();
		translateY();
	}


	private void translateX() {
		int mostLeftX = 0;
		for (int i = 0; i < sq.length; i++) {
			int x = (int)sq[i].getX();
			if (x < mostLeftX) {
				mostLeftX = x;
			}
		}
		if (mostLeftX != 0) {
			for (int i = 0; i < sq.length; i++) {
				int x = (int)sq[i].getX();
				x += Math.abs(mostLeftX);
				sq[i].setSqCoordinate(new Point(x, (int)sq[i].getY()));
			}		
		}
	}

	private void translateY() {
		int mostTopY = 0;
		for (int i = 0; i < sq.length; i++) {
			int y = (int)sq[i].getY();
			if (y > mostTopY) {
				mostTopY = y;
			}
		}
		if (mostTopY != 0) {
			for (int i = 0; i < sq.length; i++) {
				int y = (int)sq[i].getY();
				y -= Math.abs(mostTopY);
				sq[i].setSqCoordinate(new Point((int)sq[i].getX(), y));
			}		
		}
	}

	//getBlkCoordinate() return the origin coordinate, which is the left top square of the block
	public int getLeftX() {
		int mostLeftX = Integer.MAX_VALUE;
		for (int i = 0; i < sq.length; i++) {
			int x = (int)sq[i].getX();
			if (x < mostLeftX) {
				mostLeftX = x;
			}
		}
		mostLeftX = (int)this.getBlkCoordinate().getX() - mostLeftX;
		return mostLeftX;
	}

	//getBlkCoordinate() return the origin coordinate, which is the left top square of the block
	public int getTopY() {
		int mostTopY = (int)this.getBlkCoordinate().getY();
		return mostTopY;
	}

	//the coordinate in box system
	public int getBottomY() {
		int mostBottomY = getBottomSqCoordinateY();
		mostBottomY += (int)this.getBlkCoordinate().getY();
		return mostBottomY;
	}
	
	//the relative coordinate in block system
	private int getBottomSqCoordinateY() {
		int mostBottomY = Integer.MAX_VALUE;
		for (int i = 0; i < this.getNumSquare(); i++) {
			int y = sq[i].getY();
			if (y < mostBottomY) {
				mostBottomY = y;
			}
		}
		return mostBottomY;
	}
	
	public TSquare[] getSquares() {
		return this.sq;
	}

	public int calculateYafterMove(int moveStep) {
		return (this.getBottomY() - moveStep);
	}

	public int calculateXAfterLeftMove(int moveStep) {
		return (this.getLeftX() - moveStep);
	}

	public int calculateXAfterRightMove(int moveStep) {
		return (this.getLeftX() + moveStep);
	}

	public List<TSquare> getBottomSquares() {
		List<TSquare> bSq = new ArrayList<TSquare>();
		int bY = getBottomSqCoordinateY();
		for (int i = 0; i < this.getNumSquare(); i++) {
			if (sq[i].getY() == bY) {
				TSquare nsq = new TSquare();
				int x = sq[i].getX();
				int y = sq[i].getY();
				x += (int)this.getBlkCoordinate().getX();	
				y += ((int)this.getBlkCoordinate().getY() - 1);	//because it matched with the next line, need -1 with the current coordinate Y
				nsq.setSqCoordinate(x, y);
				bSq.add(nsq);
			}
		}
		return bSq;
	}


	

}
