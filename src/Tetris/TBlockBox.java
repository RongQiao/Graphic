package Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import BasicGraphic.Square;

public class TBlockBox extends TBox{
	private int numSquareCell_Width;
	private int numSquareCell_Height;
	private int squareSize;
	private List<TBlock> blks;

	public TBlockBox() {
		blks = new ArrayList<TBlock>();
	}
	
	public void setNumSquareCell_Width(int numsquarecellmainw) {
		this.numSquareCell_Width = numsquarecellmainw;
	}

	public void setNumSquareCell_Height(int numsquarecellmainh) {
		this.numSquareCell_Height = numsquarecellmainh;
	}
	
	public int getMaxCellCoordinateX() {
		return this.numSquareCell_Width;
	}

	public int getMaxCellCoordinateY() {
		return this.numSquareCell_Height;
	}

	public void draw(Graphics g, boolean showGrid) {
		draw(g);
		if (showGrid) {
			drawGrid(g);
		}
	}
	
	private void drawGrid(Graphics g) {
		Point p = (Point) this.getLeftTopVertex();
		int X = p.x;
		int Y = p.y;
		int W = getPixelWidth();
		int H = getPixelHeight();
		int i;
		int sqSize = this.getSquareSize();
		g.setColor(Color.LIGHT_GRAY);
		for (i = 0; i < this.numSquareCell_Height-1; i++) {
			Y = Y + sqSize;			
			g.drawLine(X, Y, X+W, Y);
		}
		X = p.x;
		Y = p.y;
		for (i = 0; i < this.numSquareCell_Width-1; i++) {
			X = X + sqSize;
			g.drawLine(X, Y, X, Y + H);
		}
	}

	public void draw(Graphics g){

		Point p = (Point) this.getLeftTopVertex();
		int X = p.x;
		int Y = p.y;
		int W = getPixelWidth();
		int H = getPixelHeight();
		g.clearRect(X, Y, W, H);
		g.drawRect(X, Y, W, H);
		//set up vertices
		this.setSecondVertex(X+W, Y);
		this.setThirdVertex(X+W, Y+H);
		this.setFourthVertex(X, Y+H);
	}


	public int getSquareSize() {
		return squareSize;
	}

	public void setSquareSize(int pixelSize) {
		squareSize = pixelSize;
	}

	public int getPixelWidth() {
		return (getSquareSize() * numSquareCell_Width);
	}

	public int getPixelHeight() {
		return (getSquareSize() * numSquareCell_Height);
	}

	public int calculateMinHeight(Graphics g) {
		Square minSq = new Square();
		
		return(minSq.getPixelLength() * this.getMaxCellCoordinateY());
	}

	public List<TBlock> getBlks() {
		return blks;
	}
	
	public void removeBlock(TBlock blk) {
		blks.remove(blk);
	}
	
	public void addBlock(TBlock blk) {
		blks.add(blk);
	}

	public int getBlkNum() {
		return blks.size();
	}


	public TBox getMiddleBox(Dimension d) {
		TBox box = new TBox();
		
		int sqSize = this.getSquareSize();
		int sqW_mid = d.width / sqSize;
		int sqH_mid = d.height / sqSize;
		if ((d.width % sqSize) > 0) {
			sqW_mid++;
		}
		if ((d.height % sqSize) > 0) {
			sqH_mid++;
		}
		
		int X = (int)this.getLeftTopVertex().getX();
		int Y = (int)this.getLeftTopVertex().getY();
		int W = this.getSquareSize() * sqW_mid;
		int H = this.getSquareSize() * sqH_mid; 
		
		X = X + (this.getWidth() - W) / 2;
		Y = Y + (this.getHeight() - H) / 2;
		
		box.setFirstVertex(X, Y);
		box.setSecondVertex(X + W, Y);
		box.setThirdVertex(X + W, Y + H);
		box.setFourthVertex(X, Y + H);
		
		return box;
	}

	public boolean isBlocksInArea(TBox box) {
		boolean ret = false;
		
		for (TBlock blk:blks) {
			TBox virtualBox4Blk = blk.getBlkVirtualBox();
			ret = this.isInBox(virtualBox4Blk);
			if (ret) {
				break;
			}
		}
		
		return ret;
	}

	public TBlock setBlkMiddle(TBlock blk) {
		TBlock newBlk = blk;
		newBlk.setBlkCoordinate(1, 1);
		int sqW_Box = this.getMaxCellCoordinateX();
		int sqH_Box = this.getMaxCellCoordinateY();
		int sqW_blk = blk.getSqNum_Width();
		int sqH_blk = blk.getSqNum_Height();
		int X = (int)newBlk.getBlkCoordinate().getX();
		X = X + (sqW_Box - sqW_blk) / 2;
		int Y = (int)newBlk.getBlkCoordinate().getY();
		Y = Y + (sqH_Box - sqH_blk) / 2;
		newBlk.setBlkCoordinate(X, Y);
		return newBlk;
	}

	public int calculateMinWidth(int sqSize, int widthLimit) {
		int sqsizeLimit = widthLimit / this.getMaxCellCoordinateX();
		if ((widthLimit % this.getMaxCellCoordinateX()) > 0) {
			sqsizeLimit++;
		}
		int lSq = Math.max(sqsizeLimit, sqSize);
		int w = lSq * this.getMaxCellCoordinateX();
		return w;
	}

	//the first one
	public TBlock getBlock() {
		if (blks.size() > 0) {
			return blks.get(0);
		}
		else {
			return null;
		}
	}

	//the last one
	public TBlock getLatestBlk() {		
		int cnt = blks.size();
		if (cnt > 0) {
			return blks.get(cnt -1);
		}
		else {
			return null;
		}
	}

	public int getTop(TBlock blk) {
		int xLeft = blk.getLeftX();
		int range = blk.getSqNum_Width();
		return getTop(xLeft, range, blk);
	}
	//the top edge of the x~x+range column
	public int getTop(int xLeft, int range, TBlock movingBlk) {		
		int maxY = 0;	//if no blks, the value should be 0
		int xRight = xLeft + range;
		for (TBlock blk: blks) {
			if (!blk.equals(movingBlk)) {
				int xBase = blk.getLeftX();
				int yBase = blk.getBottomY();
				TSquare[] sqs = blk.getSquares();
				for (TSquare sq: sqs) {
					int x = xBase + sq.getX() - 1;
					int y = yBase + sq.getY() - 1;
					//if the x of the sq is in the [xLeft, xRight), choose the maximum of sq.x and maxY
					if ((x >= xLeft) && (x < xRight)) {
						maxY = Math.max(maxY, y);
					}
				}
			}
		}
		return maxY;
	}

	//the left edge of the y~y+range row
	public int getLeft(int yBottom, int range, TBlock movingBlk) {
		int maxX = 0;
		int yTop = yBottom + range;
		for (TBlock blk: blks) {
			if (!blk.equals(movingBlk)) {
				int xBase = blk.getLeftX();
				int yBase = blk.getBottomY();
				TSquare[] sqs = blk.getSquares();
				for (TSquare sq: sqs) {
					int x = xBase + sq.getX();
					int y = yBase + sq.getY();
					//if the x of the sq is in the [yBottom, yRight), choose the maximum of sq.x and maxX
					if ((y >= yBottom) && (y < yTop)) {
						maxX = Math.max(maxX, x);
					}
				}
			}
		}
		return maxX;
	}
	//the right edge of the y~y+range row
	public int getRight(int yBottom, int range, TBlock movingBlk) {
		int minX = 11;
		int yTop = yBottom + range;
		for (TBlock blk: blks) {
			if (!blk.equals(movingBlk)) {
				int xBase = blk.getLeftX();
				int yBase = blk.getBottomY();
				TSquare[] sqs = blk.getSquares();
				for (TSquare sq: sqs) {
					int x = xBase + sq.getX() - 1;
					int y = yBase + sq.getY() - 1;
					//if the x of the sq is in the [yBottom, yRight), choose the maximum of sq.x and maxX
					if ((y >= yBottom) && (y < yTop)) {
						minX = Math.min(minX, x);
					}
				}
			}
		}
		return minX;
	}

	public int getIndex(TBlock blk) {
		int index = 0;
		for (int i = 0; i < blks.size(); i++) {
			if (blks.get(i).equals(blk)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public void replaceBlock(int index, TBlock blk) {
		blks.set(index, blk);
	}

	public boolean reachTopEdge(int y, TBlock blk) {
		int edge = this.getTop(blk.getLeftX(), blk.getSqNum_Width(), blk);
		return (y <= edge) ? true : false;
	}

	public boolean reachMaxTopEdge(int y, TBlock blk) {
		int edge = 20 - blk.sqNumHeight;
		return (y >= edge) ? true : false;
	}

	public boolean reachLeftEdge(int x, TBlock blk) {
		int edge = this.getLeft(blk.getBottomY(), blk.getSqNum_Height(), blk);
		return (x <= edge) ? true : false;
	}

	public boolean reachRightEdge(int x, TBlock blk) {
		int edge = this.getRight(blk.getBottomY(), blk.getSqNum_Height(), blk);
		x = x + blk.getSqNum_Width() - 1;	//x is the most right coordinate
		return (x < edge) ? false : true;
	}

}
