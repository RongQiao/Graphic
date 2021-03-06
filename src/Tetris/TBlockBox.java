package Tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import threeD.ThreeDSupportor;
import transformation.Transformation2D;
import transformation.Transformation2D.CoordinateSystem;
import Tetris.TBlock.MoveDirection;
import BasicGraphic.Square;

public class TBlockBox extends TBox{
	private static final int MOVEDOWN = 60;
	
	private int numSquareCell_Width;
	private int numSquareCell_Height;
	private int squareSize;
	private List<TBlock> blks;
	private boolean is3d;

	public TBlockBox() {
		blks = new ArrayList<TBlock>();
		is3d = ThreeDSupportor.getInstance().is3d();
	}
	
	public void setLeftTopVertex(int x, int y) {
		if (is3d) {
			y += MOVEDOWN;
		}
		this.setFirstVertex(x, y);
		updateVertex();
	}

	
	private void updateVertex() {
		Point p = (Point) this.getLeftTopVertex();
		int X = p.x;
		int Y = p.y;
		int W = getPixelWidth();
		int H = getPixelHeight();

		//set up vertices
		this.setSecondVertex(X+W, Y);
		this.setThirdVertex(X+W, Y+H);
		this.setFourthVertex(X, Y+H);		
	}

	public void setNumSquareCell_Width(int numsquarecellmainw) {
		this.numSquareCell_Width = numsquarecellmainw;
	}

	public void setNumSquareCell_Height(int numsquarecellmainh) {
		this.numSquareCell_Height = numsquarecellmainh;
	}
	
	public int getSqNum_Width() {
		return this.numSquareCell_Width;
	}

	public int getSqNum_Height() {
		return this.numSquareCell_Height;
	}

	public void draw(Graphics g, boolean showGrid) {
		ThreeDSupportor spt3d = ThreeDSupportor.getInstance();
		if (is3d) {
			Point p = (Point) this.getLeftTopVertex();
			int W = getPixelWidth();
			int H = getPixelHeight();
			spt3d.drawBox(g, p, W, H, showGrid, this.getSquareSize());
		}
		else {
			draw(g);
			if (showGrid) {
				drawGrid(g);
			}
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
		
		return(minSq.getPixelLength() * this.getSqNum_Height());
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
		int sqW_Box = this.getSqNum_Width();
		int sqH_Box = this.getSqNum_Height();
		int sqW_blk = blk.getSqNum_Width();
		int sqH_blk = blk.getSqNum_Height();
		int X = (int)newBlk.getBlkCoordinate().getX();
		X = X + (sqW_Box - sqW_blk) / 2;
		int Y = (int)newBlk.getBlkCoordinate().getY();
		Y = Y + (sqH_Box - sqH_blk) / 2 + blk.getSqNum_Height();
		newBlk.setBlkCoordinate(X, Y);
		return newBlk;
	}

	public int calculateMinWidth(int sqSize, int widthLimit) {
		int sqsizeLimit = widthLimit / this.getSqNum_Width();
		if ((widthLimit % this.getSqNum_Width()) > 0) {
			sqsizeLimit++;
		}
		int lSq = Math.max(sqsizeLimit, sqSize);
		int w = lSq * this.getSqNum_Width();
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
				TSquare[] sqs = blk.getSquares();
				for (int i = 0; i < sqs.length; i++) {					
					Point2D pInContainer = blk.getSqCoordinate(i);				
					int x = (int)pInContainer.getX();
					int y = (int)pInContainer.getY();
					//if the x of the sq is in the [xLeft, xRight), choose the maximum of sq.y and maxY
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
				TSquare[] sqs = blk.getSquares();
				for (int i = 0; i < sqs.length; i++) {					
					Point2D pInContainer = blk.getSqCoordinate(i);						
					int x = (int)pInContainer.getX();
					int y = (int)pInContainer.getY();
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
				TSquare[] sqs = blk.getSquares();
				for (int i = 0; i < sqs.length; i++) {					
					Point2D pInContainer = blk.getSqCoordinate(i);					
					int x = (int)pInContainer.getX();
					int y = (int)pInContainer.getY();
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
		boolean ret = false;
		int leftX = blk.getLeftX();
		int rangeX = blk.getSqNum_Width();
		int edge = this.getTop(leftX, rangeX, blk);
		if (y <= edge) {
			//check if the new block match with the current empty shape
			if (isShapeMatched(leftX, rangeX, blk)) {
				ret = false;	//if it's matched, means it can continue moving, doesn't reach the edge
			}
			else {
				ret = true;		//if it isn't matched, means it cannot continue moving, reach the edge
			}
		}
		return ret;
	}

	private boolean isShapeMatched(int xLeft, int xRange, TBlock blk) {
		boolean ret = false;
		int lineY = blk.getBottomY()-1;
		List<TSquare> sqEmpty = getEmptySq(xLeft, xRange, lineY);
		if (!sqEmpty.isEmpty()) {
			List<TSquare> bottomSq = blk.getBottomSquares();
			if (isMatched(sqEmpty, bottomSq)) {
				ret = true;
			}
		}
		return ret;
	}	

	private boolean isMatched(List<TSquare> sqEmpty, List<TSquare> bottomSq) {
		boolean ret = (sqEmpty.size() > 0);
		for (int i = 0; i < bottomSq.size(); i++) {
			boolean match = false;
			for (int j = 0; j < sqEmpty.size(); j++) {
				if (bottomSq.get(i).equals(sqEmpty.get(j))) {
					match = true;
					break;
				}
			}
			if (!match) {
				ret = false;
				break;
			}
		}
		return ret;
	}

	//check num of occupied squares at the line
	private boolean isFulled(int xLeft, int xRange, int lineY) {
		List<TSquare> occupiedSqs = this.getOccupiedSq(xLeft, xRange, lineY);
		return (occupiedSqs.size() == xRange);
	}

	private List<TSquare> getEmptySq(int xLeft, int xRange, int lineY) {
		List<TSquare> sqOccupied = getOccupiedSq(xLeft, xRange, lineY);
		List<TSquare> sqEmpty = new ArrayList<TSquare>();
		if (!sqOccupied.isEmpty()) {	//only consider occupied situation, otherwise, it's the edge
			int xRight = xLeft + xRange;
			for (int x = xLeft; x < xRight; x++) {
				boolean isEmpty = true;
				for (TSquare sq: sqOccupied) {
					int sqX = (int)sq.getX();
					if (sqX == x) {
						isEmpty = false;
					}
				}
				if (isEmpty) {
					TSquare nsq = new TSquare();
					nsq.setSqCoordinate(x, lineY);
					sqEmpty.add(nsq);
				}
			}
			
		}
		return sqEmpty;
	}

	private List<TSquare> getOccupiedSq(int xLeft, int xRange, int lineY) {
		List<TSquare> occupiedSqs = new ArrayList<TSquare>();
		int xRight = xLeft + xRange;
		for (TBlock blk: blks) {
			if (blk.getcSystem() == CoordinateSystem.CONTAINER_SYSTEM) {
				TSquare[] sqs = blk.getSquares();
				for (TSquare sq: sqs) {
					int y = sq.getY();
					if (y == lineY) {
						int x = sq.getX();
						//if the x of the sq is in the [xLeft, xRight)
						if ((x >= xLeft) && (x < xRight)) {
							TSquare s = new TSquare();
							s.setSqCoordinate(x, y);
							occupiedSqs.add(s);
						}						
					}
				}
			}
			else {
				int xBase = blk.getLeftX();
				if (((xBase + blk.getSqNum_Width()) < xLeft) 	//left
						|| (xBase > xRight)) {
					continue;
				}
				int yBase = blk.getTopY();
				TSquare[] sqs = blk.getSquares();
				for (TSquare sq: sqs) {
					int y = yBase + sq.getY();
					if (y == lineY) {
						int x = xBase + sq.getX();
						//if the x of the sq is in the [xLeft, xRight)
						if ((x >= xLeft) && (x < xRight)) {
							TSquare s = new TSquare();
							s.setSqCoordinate(x, y);
							occupiedSqs.add(s);
						}						
					}
				}
			}
		}
		return occupiedSqs;
	}

	/*
	 *scoreMng recorded all fulled lines 
	 */
	public List<Integer> checkFulledLine(int leftX, int rangeX, int bottomY, TBlock blk) {
		List<Integer> lines = new ArrayList<Integer>();
		for (int i = 0; i < blk.getSqNum_Height(); i++) {
			int currentLineY = bottomY + i;
			if (isFulled(leftX, rangeX, currentLineY)) {
				lines.add(currentLineY);
			}
		}
		return lines;
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
		int xRight = x + blk.getSqNum_Width() - 1;	//x is the most right coordinate
		return (xRight < edge) ? false : true;
	}

	public void setDisAppearLine(List<Integer> fulledLines, TScoreManager scoreMng) {
		//remove squares in the fulled line
		int cnt = fulledLines.size();
		for (TBlock blk: blks) {
			List<TSquare> sqRemove = new ArrayList<TSquare>();
			TSquare[] sqs = blk.getSquares();
			for (TSquare sq: sqs) {
				int x = (int)sq.getSqCoordinate().getX();
				int y = (int)sq.getSqCoordinate().getY();
				boolean needRemove = false;
				
				for (Integer disLineY: fulledLines) {
					if (y == disLineY) {
						needRemove = true;
				
						break;
					}
				}
				if (needRemove) {
					sqRemove.add(sq);
				}
			}
			blk.removeSquare(sqRemove);
			//make square move down if the square is above the removed line
			for (Integer disLineY: fulledLines) {
				TSquare sqLeft[] = blk.getSquares();
				for (TSquare s: sqLeft) {
					int y = (int)s.getSqCoordinate().getY();
					if (y > disLineY) {
						int newY = y - cnt;
						s.setSqCoordinate((int)s.getSqCoordinate().getX(), newY);
					}
				}
			}
		}
		
		
		
	}

	public void removeBlocks(List<TBlock> outBlks) {
		for (TBlock b: outBlks) {
			this.blks.remove(b);
		}
	}

	//the coordinate is based on container coordinate system
	public boolean isInContainer(Point p) {
		boolean ret = false;
		if ((p.x > 0) && (p.x <= this.getSqNum_Width())) {
			if ((p.y > 0) && (p.y <= this.getSqNum_Height())) {
				ret = true;
			}
		}
				
		return ret;
	}

	//merge all block into the fixed anomalistic block
	public void mergeBlocks() {
		TBlock_A anomalBlk = new TBlock_A();
		anomalBlk.setContainer(this);
		int x = this.getSqNum_Width(), y = 0;
		for (TBlock blk:blks) {
			anomalBlk.add(blk);		
			//update origin
			Point2D bOrigin = blk.getBlkCoordinate();
			x = Math.min((int)bOrigin.getX(), x);
			y = Math.max((int)bOrigin.getY(), y);
		}
		anomalBlk.setBlkCoordinate(x, y);
		blks.clear();
		blks.add(anomalBlk);
	}

	//x,y is screen coordinate
	public boolean isInMovingBlock(int x, int y) {
		boolean ret = false;
		TBlock movingBlk = getMovingBlock();
		TSquare sqs[] = movingBlk.getSquares();
		for (int i = 0; i < sqs.length; i++) {
			if (sqs[i].isInSquare(x, y)) {
				ret = true;
			}
		}
		return ret;
	}

	public TBlock getMovingBlock() {
		TBlock movingBlk = null;
		for (TBlock blk:blks) {
			String className = blk.getClass().getName();
			if (className.indexOf("TBlock_A") < 0) {
				movingBlk = blk;
				break;
			}		
		}
		return movingBlk;
	}
	
}
