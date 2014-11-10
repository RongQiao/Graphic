package Tetris;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import transformation.Transformation2D;
import transformation.Transformation2D.CoordinateSystem;
import transformation.Transformation2D.PositionDirection;

public class TBlock_A extends TBlock{
	List<TSquare> mixedSq;

	public TBlock_A(TBlockBox box) {
		super(box);
		mixedSq = new ArrayList<TSquare>();
	}
	
	public TBlock_A() {
		super();
		mixedSq = new ArrayList<TSquare>();
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void checkPD() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TBlock rotateClockwise() {
		return this;
	}

	@Override
	public TBlock rotateClockwiseCounter() {
		return this;
	}	

	public void add(TBlock blk) {
		TSquare[] sqs = blk.getSquares();
		for (int i = 0; i < sqs.length; i++) {					
			Point2D pInContainer = blk.getSqCoordinate(i);					
			int x = (int)pInContainer.getX();
			int y = (int)pInContainer.getY();
			sqs[i].setSqCoordinate(x, y);
			mixedSq.add(sqs[i]);
		}
		this.sq = mixedSq.toArray(sq);
		setcSystem(CoordinateSystem.CONTAINER_SYSTEM);
	}

	//
	public void updateCoordinate() {
		Point2D pOrigin = this.getBlkCoordinate();
		for (int i = 0; i < this.getNumSquare(); i++) {
			Point2D pContainer = sq[i].getSqCoordinate();
			Point2D p = Transformation2D.calculateTranlationConverse(pContainer, pOrigin);
			sq[i].setSqCoordinate(p);
		}
	}

}
