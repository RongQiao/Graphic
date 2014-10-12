package Tetris;

import java.awt.Point;

import Tetris.TBlock.RotateDirection;

public class TBlock_LVOppositeRight extends TBlock {

	@Override
	public void setNumSquare(int numSquare) {
		super.setNumSquare(numSquare);
		this.sqNumWidth = 2;
		this.sqNumHeight = 3;
	}
	
	@Override
	/*
	 * put the convex square as the last one
	 */
	public void init() {
		int num = this.getNumSquare();
		for (int i = 0; i < num - 1; i++) {
			//+1: coordinate is based on 1 -> +1
			Point p = new Point(1, i + 1);
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
		//last one
		Point p = new Point(2, 1);
		sq[num - 1].setSqCoordinate(p);
		sq[num - 1].setColor(this.getColor());
	}

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		y = y + 1;
		TBlock rotatedBlk = new TBlock_LHOppositeDown();
		if (clockwise == RotateDirection.CLOCKWISE_COUNTER) {			
			rotatedBlk = new TBlock_LHOppositeUp();
		}
		rotatedBlk.setContainer(this.container);
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}

}
