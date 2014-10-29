package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

import transformation.Transformation2D;
import transformation.Transformation2D.PositionDirection;

public class TBlock_T extends TBlock{
	public TBlock_T(TBlockBox cont) {
		super(cont);
	}
	
	public TBlock_T() {
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
		pd = PositionDirection.CLOCK12;
		sq[0].setSqCoordinate(new Point(1, 0));
		sq[0].setColor(this.getColor());
		for (int i = 1; i < sq.length; i++) {
			Point2D p = new Point(i-1, -1);
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
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
