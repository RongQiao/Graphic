package Tetris;

import java.awt.Point;

public class TBlock_ZHLeft extends TBlock {

	public TBlock_ZHLeft(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
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
	public int getSqNum_Width() {
		return this.getNumSquare()/2 + 1;
	}


	@Override
	public int getSqNum_Height() {
		return 2;
	}

}
