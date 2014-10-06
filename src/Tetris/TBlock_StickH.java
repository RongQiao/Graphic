package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

public class TBlock_StickH extends TBlock_Stick {

	public TBlock_StickH(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		for (int i = 0; i < getNumSquare(); i++) {
			Point2D p = new Point(i+1, 1);
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	@Override
	public int getSqNum_Width() {
		return getNumSquare();
	}

	@Override
	public int getSqNum_Height() {
		return 1;
	}	


}
