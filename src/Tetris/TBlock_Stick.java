package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

import transformation.Transformation2D.PositionDirection;

public class TBlock_Stick extends TBlock{	
	public TBlock_Stick(TBlockBox box) {
		super(box);
	}

	public TBlock_Stick() {
		super();
	}

	@Override
	public void setNumSquare(int numSquare) {
		super.setNumSquare(numSquare);
		sqNumWidth = 4;
		sqNumHeight = 1;
	}
	
	@Override
	//the default position is H
	public void init() {
		pd = PositionDirection.CLOCK3;
		for (int i = 0; i < getNumSquare(); i++) {
			Point2D p = new Point(i, 0);
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	@Override
	protected void checkPD() {
		switch (pd) {		
		case CLOCK6:
		case CLOCK12:
			sqNumWidth = 1;
			sqNumHeight = 4;
			break;
		case CLOCK3:
		case CLOCK9:
		default:
			sqNumWidth = 4;
			sqNumHeight = 1;
			break;
		}
	}
	

}
