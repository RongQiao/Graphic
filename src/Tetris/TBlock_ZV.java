package Tetris;

import java.awt.Point;

import Tetris.TBlock.RotateDirection;

public class TBlock_ZV extends TBlock {

	@Override
	public void setNumSquare(int numSquare) {
		super.setNumSquare(numSquare);
		sqNumWidth = 2;
		sqNumHeight = 3;
	}
	
	@Override
	/*
	 * store sq via column, left to right, sq in column bottom to top
	 * @see Tetris.TBlock#init()
	 */
	public void init() {
		int num = this.getNumSquare();
		for (int i = 0; i < num; i++) {
			Point p;
			if (inColumn1(i)) {
				//+1: coordinate is based on 1 -> +1 
				p = new Point(1, i + 1);
			}
			else {
				int newIndex = i - num/2;
				//+2: coordinate is based on 1 -> +1, the most bottom is not occupied -> +1 
				p = new Point(2, newIndex + 2);
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	private boolean inColumn1(int index) {
		return (index < sqNumWidth) ? true : false;
	}

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		TBlock rotatedBlk = new TBlock_ZH();
		x = x - 1;
		y = y + 1;			
		rotatedBlk.setContainer(this.container);
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}

}
