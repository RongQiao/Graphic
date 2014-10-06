package BasicGraphic;
import java.awt.Point;
import java.awt.geom.Point2D;


public class RectVertices {
	private static int FIRST = 0, SECOND = 1, THIRD = 2, FOURTH = 3;
	private Point2D[] vertices;
	
	public RectVertices() {
		vertices = new Point2D[4];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Point(0, 0);
		}
	}

	public void setFirst(int x, int y) {
		vertices[FIRST] = new Point(x, y);
	}
	
	public Point2D getFirst() {
		return vertices[FIRST];
	}

	public void setSecond(int x, int y) {
		vertices[SECOND] = new Point(x, y);
	}


	public Point2D getSecond() {
		return vertices[SECOND];
	}

	public void setThird(int x, int y) {
		vertices[THIRD] = new Point(x, y);
	}

	public Point2D getThird() {
		return vertices[THIRD];
	}

	public void setFourth(int x, int y) {
		vertices[FOURTH] = new Point(x, y);		
	}
	
	public Point2D getFourth() {
		return vertices[FOURTH];
	}

}
