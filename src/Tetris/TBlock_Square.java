package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

public class TBlock_Square extends TBlock {

	public TBlock_Square(TBlockBox Box) {
		super(Box);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		int nCell = (int) Math.sqrt(getNumSquare());
		for (int i = 0; i < getNumSquare(); i++) {
			Point2D p;
			if (i < nCell) {
				p = new Point(i+1, 1);
			}
			else {
				p = new Point(i-nCell+1, 2);
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	@Override
	public int getSqNum_Width() {
		return (int) Math.sqrt(getNumSquare());
	}

	@Override
	public int getSqNum_Height() {
		return (int) Math.sqrt(getNumSquare());
	}

}
