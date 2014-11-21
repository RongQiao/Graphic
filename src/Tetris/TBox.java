package Tetris;

import java.awt.Point;
import java.awt.geom.Point2D;

import threeD.ThreeDSupportor;
import BasicGraphic.Rectangle;

public class TBox extends Rectangle{

	public Point2D getLeftTopVertex() {
		return this.getVertices().getFirst();
	}
	
	public void setLeftTopVertex(int x, int y) {
		this.setFirstVertex(x, y);
	}
	
	public Point2D getLeftBottomVertex() {
		return this.getVertices().getFourth();
	}
	
	public void setLeftBottomVertex(int x, int y) {
		this.setFourthVertex(x, y);
	}
		
	public int getWidth() {
		return (int) (this.getSecondVertex().getX() - this.getFirstVertex().getX());
	}
	
	public int getHeight() {
		return (int) (this.getFourthVertex().getY() - this.getFirstVertex().getY());
	}

	public boolean isInBox(TBox b) {
		boolean ret = false;
		Point p = (Point)b.getFirstVertex();
		ret = isInBox(p);
		if (!ret) {
			p = (Point)b.getSecondVertex();
			ret = isInBox(p);
			if (!ret) {
				p = (Point)b.getThirdVertex();
				ret = isInBox(p);
				if (!ret) {
					p = (Point)b.getFourthVertex();
					ret = isInBox(p);
				}
			}
		}
		return ret;
	}
	
	public boolean isInBox(Point p) {
		boolean ret = false;
		if (p != null) {
			ret = isInBox(p.x, p.y);
		}
		return ret;
	}
	
	public boolean isInBox(int x, int y) {
		boolean ret = false;
		ret = ((x >= (int)this.getFirstVertex().getX())
				&& (x <= (int)this.getSecondVertex().getX())
				&& (y >= (int)this.getFirstVertex().getY())
				&& (y <= (int)this.getFourthVertex().getY())
				);
		return ret;
	}
}
