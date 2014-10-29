package block;

import java.awt.Point;
import java.awt.geom.Point2D;

import Tetris.TBlock;
import Tetris.TBlockBox;
import Tetris.TBlock_T;
import Tetris.TBlock.RotateDirection;

public class TBlock_Tup extends TBlock_T {

	public TBlock_Tup(TBlockBox cont) {
		super(cont);
		// TODO Auto-generated constructor stub
	}
	
	public TBlock_Tup() {
		super();
	}

	@Override
	public void init() {
		int num = getNumSquare();
		for (int i = 0; i < num - 1; i++) {
			Point2D p = new Point(i+1, 1);
			if (isMiddleOne(i)) {
				//set the convex square, which is the last one in the sq[]
				Point2D cP = new Point(i + 1, 2);
				sq[num-1].setSqCoordinate(cP);
				sq[num-1].setColor(this.getColor());
			}
			sq[i].setSqCoordinate(p);
			sq[i].setColor(this.getColor());
		}
	}

	//T block has a convex square in the middle of the base
	private boolean isMiddleOne(int x) {
		int midX = this.getNumSquare()/2 - 1;
		return (midX == x) ? true : false;
	}

	@Override
	public int getSqNum_Width() {
		return getNumSquare() - 1;
	}

	@Override
	public int getSqNum_Height() {
		return 2;
	}

	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		int x = (int)this.getBlkCoordinate().getX();
		int y = (int)this.getBlkCoordinate().getY();
		TBlock rotatedBlk = new TBlock_TRight();
		if (clockwise == RotateDirection.CLOCKWISE) {
			x = x + 1;
			y = y - 1;			
		}
		else {
			rotatedBlk = new TBlock_TLeft();
			y = y - 1;
		}
		rotatedBlk.setContainer(this.container);
		rotatedBlk.init(x, y, this.getColor());
		return rotatedBlk;
	}

}
