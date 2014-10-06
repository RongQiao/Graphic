package Tetris;

import java.awt.Point;

public class TBlock_LHRightDown extends TBlock {

	private static final int NUM_SQ_ROW1 = 1;

	public TBlock_LHRightDown(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		int num = this.getNumSquare();
		for (int i = 0; i < num; i++) {
			Point p;
			if (inRow1(i)) {
				//+3: coordinate is based on 1 -> +1, the most 2 left is not occupied -> +2 
				p = new Point(i + 3, 1);
			}
			else {
				int newIndex = i - NUM_SQ_ROW1;
				//+1: coordinate is based on 1 -> +1, the most left is occupied -> +0 
				p = new Point(newIndex + 1, 2);
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	private boolean inRow1(int i) {
		return (i < NUM_SQ_ROW1) ? true : false;
	}

	@Override
	public int getSqNum_Width() {
		return this.getNumSquare()-1;
	}

	@Override
	public int getSqNum_Height() {
		return 2;
	}

}
