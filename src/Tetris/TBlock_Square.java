package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

import transformation.Transformation2D.PositionDirection;
import Tetris.TBlock.RotateDirection;

public class TBlock_Square extends TBlock {

	public TBlock_Square(TBlockBox Box) {
		super(Box);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setNumSquare(int numSquare) {
		super.setNumSquare(numSquare);
		sqNumWidth = 2;
		sqNumHeight = 2;
	}

	@Override
	public void init() {
		int nCell = (int) Math.sqrt(getNumSquare());
		for (int i = 0; i < getNumSquare(); i++) {
			Point2D p;
			if (i < nCell) {
				p = new Point(i, 0);
			}
			else {
				p = new Point(i-nCell, -1);
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
	
	@Override
	public TBlock getRotatedBlk(RotateDirection clockwise) {
		return this;
	}
	
	@Override
	//do nothing
	public TBlock rotateClockwise() {
		return this;
	}

	@Override
	//do nothing
	public TBlock rotateClockwiseCounter() {
		return this;
	}	

	public class Person {
		private String name;
		public Person(String s) {
			this.name = s;
		}
		public boolean equals(Person p) {
			return p.name.equals(this.name);
		}
	}
}
