package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

public class TBlock_StickV extends TBlock_Stick {
	public TBlock_StickV(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	public void init() {
		for (int i = 0; i < getNumSquare(); i++) {
			Point2D p = new Point(1, i+1);
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	@Override
	public int getSqNum_Width() {
		return 1;
	}

	@Override
	public int getSqNum_Height() {
		return getNumSquare();
	}	

}
