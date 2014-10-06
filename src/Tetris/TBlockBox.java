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
	
	public int getNumSquareCell_Width() {
		return this.numSquareCell_Width;
	}

	public int getNumSquareCell_Height() {
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
		
		return(minSq.getPixelLength() * this.getNumSquareCell_Height());
	}

	public void addBlock(TBlock blk) {
		blks.add(blk);
	}

	public int getBlkNum() {
		return blks.size();
	}

	public List<TBlock> getBlks() {
		return blks;
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
		int sqW_Box = this.getNumSquareCell_Width();
		int sqH_Box = this.getNumSquareCell_Height();
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
		int sqsizeLimit = widthLimit / this.getNumSquareCell_Width();
		if ((widthLimit % this.getNumSquareCell_Width()) > 0) {
			sqsizeLimit++;
		}
		int lSq = Math.max(sqsizeLimit, sqSize);
		int w = lSq * this.getNumSquareCell_Width();
		return w;
	}

}
