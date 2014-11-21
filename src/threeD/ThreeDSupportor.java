package threeD;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import transformation.Transformation2D;

public class ThreeDSupportor {
	private static ThreeDSupportor instance = null;
	private ThreeDSupportor() {
		setIs3d(true);
	}
	public static ThreeDSupportor getInstance() {
		if (instance == null) {
			instance = new ThreeDSupportor();
		}
		return instance;
	}
	
	private final int angle = -30;
	private final int zDepth = 2;
	private boolean is3d;
	/**
	 * @return the is3d
	 */
	public boolean is3d() {
		return is3d;
	}
	/**
	 * @param is3d the is3d to set
	 */
	public void setIs3d(boolean is3d) {
		this.is3d = is3d;
	}
	
	/*
	 * given lefttop vertext and width, height, single squareSize in pixel
	 */
	public void drawBox(Graphics g, Point p1, int w, int h, boolean showGrid,
			int gridSize) {
		g.clearRect(p1.x, p1.y-this.getX43D(), w + this.getX43D(), h + this.getX43D());
		drawBox(g, p1, w, h, showGrid, gridSize, zDepth);
	}
	
	public void drawBox(Graphics g, Point p1, int w, int h, boolean showGrid,
			int gridSize, int zUnit) {
		Point points[] = calculatePoint4Box(p1, w, h, gridSize, zUnit);
		Point p2 = points[1];
		Point p3 = points[2];
		Point p4 = points[3];
		Point p1Depth = points[4];
		Point p2Depth = points[5];
		Point p3Depth = points[6];
		Point p4Depth = points[7];
		g.drawRect(p1.x, p1.y, w, h);

		if (showGrid) {
			g.setColor(Color.GRAY);
			//invisible line
			//g.drawLine(p4.x, p4.y, p4Depth.x, p4Depth.y);
			//g.drawLine(p1Depth.x, p1Depth.y, p4Depth.x, p4Depth.y);
			//g.drawLine(p3Depth.x, p3Depth.y, p4Depth.x, p4Depth.y);
			//invisible face
			//drawGrid(g, p4Depth, p3Depth, p3, p4, gridSize, 0, zUnit);
			//drawGrid(g, p1, p1Depth, p4Depth, p4, 0, gridSize, zUnit);
			//drawGrid(g, p1Depth, p2Depth, p3Depth, p4Depth, gridSize, gridSize, 0);
			//visible faces
			drawGrid(g, p1Depth, p2Depth, p2, p1, gridSize, 0, zUnit);
			drawGrid(g, p2, p2Depth, p3Depth, p3, 0, gridSize, zUnit);
			drawGrid(g, p1, p2, p3, p4, gridSize, gridSize, 0);
		}
		g.setColor(Color.BLACK);
		g.drawLine(p1Depth.x, p1Depth.y, p2Depth.x, p2Depth.y);
		g.drawLine(p3Depth.x, p3Depth.y, p2Depth.x, p2Depth.y);
		g.drawLine(p1.x, p1.y, p1Depth.x, p1Depth.y);
		g.drawLine(p2.x, p2.y, p2Depth.x, p2Depth.y);		
		g.drawLine(p3.x, p3.y, p3Depth.x, p3Depth.y);
	}
	
	/*
	 * p1~p4, points of the front rectangle, p1-left top, clockwise
	 * p1Depth~p4Depth, point of the back rectangle
	 */
	private Point[] calculatePoint4Box(Point p1, int w, int h, int gridSize, int zUnit) {
		Point p4Box[] = new Point[8];
		Point p1Depth = calculateZP1(p1, gridSize * zUnit, angle);
		Point p2 = new Point(p1.x+w,  p1.y);
		Point p2Depth = new Point(p1Depth.x+w, p1Depth.y);
		Point p3 = new Point(p1.x+w,  p1.y+h);
		Point p3Depth = new Point(p1Depth.x+w, p1Depth.y+h);
		Point p4 = new Point(p1.x,  p1.y+h);
		Point p4Depth = new Point(p1Depth.x, p1Depth.y+h);
		p4Box[0] = p1;
		p4Box[1] = p2;
		p4Box[2] = p3;
		p4Box[3] = p4;
		p4Box[4] = p1Depth;
		p4Box[5] = p2Depth;
		p4Box[6] = p3Depth;
		p4Box[7] = p4Depth;
		return p4Box;
	}

	
	private void drawGrid(Graphics g, Point p1, Point p2, Point p3, Point p4, int gridSizeX, int gridSizeY, int zUnit) {
		g.setColor(Color.LIGHT_GRAY);
		if (gridSizeX > 0) {
			int x = p4.x + gridSizeX, xD = p1.x + gridSizeX;
			int y = p4.y, yD = p1.y;
			while (x < p3.x) {
				g.drawLine(x, y, xD, yD);
				x += gridSizeX;
				xD += gridSizeX;
			}
		}
		if (gridSizeY > 0) {
			int x = p1.x, xD = p2.x;
			int y = p1.y + gridSizeY, yD = p2.y + gridSizeY;
			while (y < p4.y) {
				g.drawLine(x, y, xD, yD);
				y += gridSizeY;
				yD += gridSizeY;
			}
		}
		if (zUnit > 0) {
			if (gridSizeX > 0) {	//line should be follow X
				int unitX = Math.abs(p1.x - p4.x)/zUnit;
				int unitY = Math.abs(p1.y - p4.y)/zUnit;
				int x = p4.x + unitX, xD = p3.x + unitX;
				int y = p4.y - unitY, yD = p3.y - unitY;
				while (x < p1.x) {
					g.drawLine(x, y, xD, yD);
					x += unitX;
					y -= unitY;
					xD += unitX;
					yD -= unitY;
				}				
			}
			if (gridSizeY > 0) {	//line should be follow Y
				int unitX = Math.abs(p1.x - p2.x)/zUnit;
				int unitY = Math.abs(p1.y - p2.y)/zUnit;
				int x = p1.x + unitX, xD = p4.x + unitX;
				int y = p1.y - unitY, yD = p4.y - unitY;
				while (x < p2.x) {
					g.drawLine(x, y, xD, yD);
					x += unitX;
					y -= unitY;
					xD += unitX;
					yD -= unitY;
				}				
			}

		}

	}
	/*
	 * assume p1 is origin, rotate first, then translate
	 */
	private Point calculateZP1(Point p1, int x, int rotateDegree) {
		Point tmpP = new Point(x, 0);
		Point tmpPZ = (Point) Transformation2D.calculateRotation(tmpP, rotateDegree);
		Point p1Z = (Point) Transformation2D.calculateTranlation(tmpPZ, p1);
		return p1Z;
	}
	
	//reserve x for 3D X direction
	public int getX43D() {		
		return 40;
	}
	
	public void drawCube(Graphics g, int x, int y, int w, int h) {
		Color cl = g.getColor();
		drawBoxFace(g, x, y, w, h);
		g.setColor(Color.BLACK);
		drawBox(g, new Point(x, y), w, h, false, Math.min(w, h), 1);		
	}
	
	private void drawBoxFace(Graphics g, int x, int y, int w, int h) {
		Point points[] = calculatePoint4Box(new Point(x, y), w, h, Math.min(w, h), 1);
		Point p1 = points[0];
		Point p2 = points[1];
		Point p3 = points[2];
		Point p4 = points[3];
		Point p1Depth = points[4];
		Point p2Depth = points[5];
		Point p3Depth = points[6];
		Point p4Depth = points[7];
		
		g.fillRect(x, y, w, h);
		fillParallelogram(g, p1Depth, p2Depth, p2, p1);
		fillParallelogram(g, p2, p2Depth, p3Depth, p3);
	}
	
	private void fillParallelogram(Graphics g, Point p1, Point p2, Point p3, Point p4) {
		g.fillRect(p1.x, p1.y, p3.x-p1.x, p3.y-p1.y);
		if (p1.x > p4.x) {//p4 is the most left
			fillRightTriangle(g, p1, p4, true);
			fillRightTriangle(g, p2, p3, false);
		}
		else {	//p1 is the most left
			fillRightTriangle(g, p1, p2, true);
			fillRightTriangle(g, p3, p4, false);
		}
	}
	
	private void fillRightTriangle(Graphics g, Point p1, Point p2, boolean isUp) {
		Point left = (p1.x < p2.x) ? p1 : p2;
		Point right = (p1.x > p2.x) ? p1 : p2;
		if (isUp) {
			fillRightTriangleUp(g, left, right);
		}
		else {
			fillRightTriangleDown(g, left, right);
		}
	}
	
	/*
	 * draw small rectangle to fill triangle
	 * x, fixed; y, increase; w, decrease; h, fixed
	 */
	private void fillRightTriangleDown(Graphics g, Point left, Point right) {
		int yIncrease = 2;
		int cnt = Math.abs(right.y - left.y)/yIncrease;
		int xIncrease = Math.abs(right.x - left.x)/cnt;
		int x = left.x;
		int y = right.y;
		int w = right.x - left.x;
		for (int i = 0; i < cnt; i++) {
			g.fillRect(x, y, w, yIncrease);
			w -= xIncrease;
			y += yIncrease;
		}
	}
	
	/*
	 * draw small rectangle to fill triangle
	 * x, increase; y, decrease; w, decrease; h, fixed
	 */
	private void fillRightTriangleUp(Graphics g, Point left, Point right) {
		int yIncrease = 2;
		int cnt = Math.abs(right.y - left.y)/yIncrease;
		int xIncrease = Math.abs(right.x - left.x)/cnt;
		int x = left.x;
		int y = left.y;
		int w = right.x - left.x;
		for (int i = 0; i < cnt; i++) {
			 x += xIncrease;
			 y -= yIncrease;
			 w -= xIncrease;
			 g.fillRect(x, y, w, yIncrease);
		}
	}
}
