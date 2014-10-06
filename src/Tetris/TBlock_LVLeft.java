package Tetris;

import java.awt.Point;

public class TBlock_LVLeft extends TBlock {

	private static final int NUM_SQ_COLUM1 = 1;

	public TBlock_LVLeft(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		int num = this.getNumSquare();
		for (int i = 0; i < num; i++) {
			Point p;
			if (inColum1(i)) {
				//+1: coordinate is based on 1 -> +1 
				p = new Point(i + 1, 1);
			}
			else {
				int newIndex = i - NUM_SQ_COLUM1;
				//+1: coordinate is based on 1 -> +1 
				p = new Point(2, newIndex + 1);
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	private boolean inColum1(int i) {
		return (i < NUM_SQ_COLUM1) ? true : false;
	}

	@Override
	public int getSqNum_Width() {
		return 2;
	}

	@Override
	public int getSqNum_Height() {
		return this.getNumSquare()-1;
	}

}
