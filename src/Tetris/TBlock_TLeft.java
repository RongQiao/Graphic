package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

import Tetris.TBlock.RotateDirection;

public class TBlock_TLeft extends TBlock {

	@Override
	/*
	 * change the sq sequence from TBlock, 
	 * put the base from left to right/bottom to top, only the convex one is the last one
	 * @see Tetris.TBlock#init()
	 */
	public void init() {
		int num = getNumSquare();
		for (int i = 0; i < 3; i++) {
			Point2D p = new Point(2, i+1);
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
		
		//set the convex square, which is the last one in the sq[]
		Point2D cP = new Point(1, 2);
		sq[num-1].setSqCoordinate(cP);
		sq[num-1].setColor(this.getColor());
	}

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		TBlock rotatedBlk = new TBlock_Tup();
		if (clockwise == RotateDirection.CLOCKWISE) {
			y = y + 1;		
		}
		else {
			rotatedBlk = new TBlock_TDown();
			y = y + 1;
		}
		rotatedBlk.setContainer(this.container);
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}

	@Override
	public int getSqNum_Width() {
		return 2;
	}

	@Override
	public int getSqNum_Height() {
		return 3;
	}

}
