package Tetris;

import java.awt.Point;

public class TBlock_ZOpposite extends TBlock{
	public TBlock_ZOpposite(TBlockBox box) {
		super(box);
	}
	
	public TBlock_ZOpposite() {
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
				//coordinate is based on 0
				p = new Point(i, -1);
			}
			else {
				int newIndex = i - num/2;
				//+1: coordinate is based on 0, the most left is occupied -> +1 
				p = new Point(newIndex + 1, 0);
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	private boolean inRow1(int index) {
		return (index < this.getNumSquare()/2) ? true : false;
	}

	@Override
	protected void checkPD() {
		switch (pd) {		
		case CLOCK6:
		case CLOCK12:
			sqNumWidth = 2;
			sqNumHeight = 3;
			break;
		case CLOCK3:
		case CLOCK9:
		default:
			sqNumWidth = 3;
			sqNumHeight = 2;
			break;
		}
	}

}
