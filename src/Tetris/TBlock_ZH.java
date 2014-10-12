package Tetris;

import java.awt.Point;

import Tetris.TBlock.RotateDirection;

public class TBlock_ZH extends TBlock {

	public TBlock_ZH(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	public TBlock_ZH() {
		super();		
	}
	
	@Override
	public void setNumSquare(int numSquare) {
		super.setNumSquare(numSquare);
		sqNumWidth = 3;
		sqNumHeight = 2;
	}

	@Override
	public void init() {
		int num = this.getNumSquare();
		for (int i = 0; i < num; i++) {
			Point p;
			if (inRow1(i)) {
				//+2: coordinate is based on 1 -> +1, the most left is not occupied -> +1 
				p = new Point(i + 2, 1);
			}
			else {
				int newIndex = i - num/2;
				//+1: coordinate is based on 1 -> +1, the most left is occupied -> +0 
				p = new Point(newIndex + 1, 2);
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	private boolean inRow1(int index) {
		return (index < this.getNumSquare()/2) ? true : false;
	}

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		TBlock rotatedBlk = new TBlock_ZV();
		x = x + 1;
		y = y - 1;			
		rotatedBlk.setContainer(this.container);
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}

}
