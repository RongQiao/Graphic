package ConcentricSquares;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import Basic.BasicUtil;
import BasicGraphic.RectVertices;
import BasicGraphic.Square;


public class ConcentricSquares extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void paint(Graphics g) {
		g.setColor(Color.red);
		RectVertices vertices = drawOutSquare(g);
		while (!isSmallestSquare(vertices)) {
			vertices = drawInnerSquare(g, vertices);
		}
	}

	private boolean isSmallestSquare(RectVertices vertices) {
		Square sq = new Square(vertices);
		int pixelL = sq.getPixelLength(); 
		return (pixelL == 2 ? true : false);
	}

	private RectVertices drawInnerSquare(Graphics g, RectVertices vertices) {
		Square sq = new Square(vertices);
		RectVertices v = new RectVertices();
		{
			if (sq.isSlope()) {
				v = calculateInnerSquareVertices_OutSlope(sq);
			}
			else {
				v = calculateInnerSquareVertices_OutNotSlope(sq);
			}
			drawRect(g, v);
		}
		return v;
	}
	
	private RectVertices calculateInnerSquareVertices_OutSlope(Square outSq) {
		RectVertices v = new RectVertices();
		Point2D v1 = outSq.getVertices().getFirst();
		int lSmallSq = calculateInnerSquareLength(outSq);
		int pMove = lSmallSq - 1;
		lSmallSq *= 2;
		int vS1X = (int)(v1.getX()) + pMove;
		int vS1Y = (int)(v1.getY()) - pMove;
		v.setFirst(vS1X, vS1Y);
		v.setSecond(vS1X + lSmallSq - 1, vS1Y);
		v.setThird(vS1X + lSmallSq - 1, vS1Y + lSmallSq - 1);
		v.setFourth(vS1X, vS1Y + lSmallSq - 1);
		return v;
	}
	
	private RectVertices calculateInnerSquareVertices_OutNotSlope(Square outSq) {
		RectVertices v = new RectVertices();
		Point2D v1 = outSq.getVertices().getFirst();
		int lBigSq = outSq.getPixelLength();
		int lSmallSq = calculateInnerSquareLength(outSq);
		int pMove = lSmallSq - 1;
		lSmallSq *= 2;
		int vS1X = (int)(v1.getX());
		int vS1Y = (int)(v1.getY()) + pMove;
		int vS2X = (int)(v1.getX()) + pMove;
		int vS2Y = (int)(v1.getY());
		v.setFirst(vS1X, vS1Y);
		v.setSecond(vS2X, vS2Y);
		v.setThird(vS1X + lBigSq - 1, vS1Y);
		v.setFourth(vS2X, vS2Y + lBigSq - 1);
		return v;
	}
	

	private int calculateInnerSquareLength(Square outSq) {
		int lBigSq = outSq.getPixelLength();
		int lSmallSq = 0;
		if (BasicUtil.isOdd(lBigSq)) {
			lSmallSq = (lBigSq + 1) / 2;
		}
		else {
			lSmallSq = lBigSq / 2;
		}
		return lSmallSq;
	}

	private RectVertices drawOutSquare(Graphics g) {
		// the width and height of the rect is the real width or height - 1
		// for example g.drawRect(0, 0, 1, 1) draw a rectangle 2*2 
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		int maxLSquare = Math.min(maxX, maxY);
		
		RectVertices v = new RectVertices();
		v.setFirst(0, 0);
		v.setSecond(maxLSquare, 0);
		v.setThird(maxLSquare, maxLSquare);
		v.setFourth(0, maxLSquare);
		
		drawRect(g, v);
		
		return v;
	}

	private void drawRect(Graphics g, RectVertices v) {
		g.drawLine((int) v.getFirst().getX(), 
				(int) v.getFirst().getY(), 
				(int) v.getSecond().getX(), 
				(int) v.getSecond().getY());
		g.drawLine((int) v.getSecond().getX(), 
				(int) v.getSecond().getY(), 
				(int) v.getThird().getX(), 
				(int) v.getThird().getY());
		g.drawLine((int) v.getThird().getX(), 
				(int) v.getThird().getY(), 
				(int) v.getFourth().getX(), 
				(int) v.getFourth().getY());
		g.drawLine((int) v.getFourth().getX(), 
				(int) v.getFourth().getY(), 
				(int) v.getFirst().getX(), 
				(int) v.getFirst().getY());
	}
	
}
