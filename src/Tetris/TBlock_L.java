package Tetris;

import java.awt.Point;

public class TBlock_L extends TBlock{

	public TBlock_L(TBlockBox box) {
		super(box);
	}
	
	public TBlock_L() {
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
		Point p = new Point(0, 0);
		sq[0].setSqCoordinate(p);
		sq[0].setColor(this.getColor());
		int num = this.getNumSquare();
		for (int i = 0; i < num - 1; i++) {
			p = new Point(i, -1);
			sq[i+1].setSqCoordinate(p);
			sq[i+1].setColor(this.getColor());
		}
	}

}
