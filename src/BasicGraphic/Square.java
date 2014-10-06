package BasicGraphic;
import java.awt.geom.Point2D;


public class Square extends Rectangle{

	public Square(RectVertices vertices) {
		super(vertices);
	}

	public Square() {
		super();
	}

	public int getPixelLength() {
		double xL = Math.abs(vertices.getFirst().getX() - vertices.getSecond().getX());
		double yL = Math.abs(vertices.getFirst().getY() - vertices.getSecond().getY());
		double slope = yL/xL;
		int l = 0;
		if (slope <= 1) {
			l = (int)xL + 1;
		}
		else {
			l = (int)yL + 1;
		}
		return l;
	}

	public boolean isSmallest() {
		int pl = getPixelLength();
		return (pl == 2 ? true : false);
	}

	public boolean isSlope() {
		Point2D p1 = vertices.getFirst();
		Point2D p2 = vertices.getSecond();
		return (Double.compare(p1.getY(), p2.getY()) == 0 ? false : true);
	}


}
