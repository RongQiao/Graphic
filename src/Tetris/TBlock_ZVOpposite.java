package Tetris;

import java.awt.Point;

public class TBlock_ZVOpposite extends TBlock {

	@Override
	public void setNumSquare(int numSquare) {
		super.setNumSquare(numSquare);
		sqNumWidth = 3;
		sqNumHeight = 2;
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
				//+2: coordinate is based on 1 -> +1, the most bottom is not occupied -> +1 
				p = new Point(1, i + 2);
			}
			else {
				int newIndex = i - num/2;
				//+1: coordinate is based on 1 -> +1, the most bottom is occupied -> +0 
				p = new Point(2, newIndex + 1);
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}
	
	private boolean inColumn1(int index) {
		return (index < sqNumHeight) ? true : false;
	}

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		TBlock rotatedBlk = new TBlock_ZHOpposite();
		y = y + 1;			
		rotatedBlk.setContainer(this.container);
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}

}
