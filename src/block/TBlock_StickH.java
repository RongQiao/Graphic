package block;

import java.awt.Point;
import java.awt.geom.Point2D;

import Tetris.TBlock;
import Tetris.TBlockBox;
import Tetris.TBlock_Stick;
import Tetris.TBlock.RotateDirection;

public class TBlock_StickH extends TBlock_Stick {

	public TBlock_StickH(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	public TBlock_StickH() {
		super();
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

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		TBlock rotatedBlk = new TBlock_StickV();
		rotatedBlk.setContainer(this.container);
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		x = x + 1;
		y = y - 3;
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}

}
