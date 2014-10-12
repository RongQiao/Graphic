package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

public class TBlock_StickV extends TBlock {
	public TBlock_StickV(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	public TBlock_StickV() {
		super();
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

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		TBlock rotatedBlk = new TBlock_StickH();
		rotatedBlk.setContainer(this.container);
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		x = x - 1;
		y = y + 3;
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}	

}
