package Tetris;

import java.awt.Graphics;
import java.awt.Point;

public abstract class TBlock_T extends TBlock{

	public TBlock_T(TBlockBox box) {
		super(box);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		Point pFirstSqLeftTop = caculateFirstSqLeftTop();
		int sqSize = this.container.getSquareSize();
		for (int i = 0; i < getNumSquare(); i++) {
			int x = (int) sq[i].getSqCoordinate().getX();
			int y = (int) sq[i].getSqCoordinate().getY();
			int sqX = pFirstSqLeftTop.x + sqSize * (x - 1);
			int sqY = pFirstSqLeftTop.y - sqSize * (y - 1);
			sq[i].setFirstVertex(sqX, sqY);
			sq[i].draw(g);
		}
	}
}
