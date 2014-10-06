package BasicGraphic;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Rectangle {

	public RectVertices vertices;
	
	public Rectangle(RectVertices vertices) {
		this.vertices = vertices;
	}
	
	//create the smallest rectangle which is located at the left top
	public Rectangle() {
		vertices = new RectVertices();
		vertices.setFirst(0, 0);
		vertices.setSecond(1, 0);
		vertices.setThird(1, 1);
		vertices.setFourth(0, 1);		
	}

	public RectVertices getVertices() {
		return vertices;
	}
	
	public void setFirstVertex(int x, int y) {
		vertices.setFirst(x, y);
	}	
	
	public void setFirstVertex(Point2D vertex) {
		Point p = (Point) vertex;
		vertices.setFirst(p.x, p.y);
	}

	public void setThirdVertex(int x, int y) {
		vertices.setThird(x, y);
	}
	
	public void setThirdVertex(Point2D vertex) {
		Point p = (Point) vertex;
		vertices.setThird(p.x, p.y);
	}	

	public void setSecondVertex(int x, int y) {
		vertices.setSecond(x, y);
	}
	
	public void setSecondVertex(Point2D vertex) {
		Point p = (Point) vertex;
		vertices.setSecond(p.x, p.y);
	}	
	
	public void setFourthVertex(int x, int y) {
		vertices.setFourth(x, y);
	}
	
	public void setFourthVertex(Point2D vertex) {
		Point p = (Point) vertex;
		vertices.setFourth(p.x, p.y);
	}	
	
	public Point2D getFirstVertex() {
		return vertices.getFirst();
	}	

	public Point2D getThirdVertex() {
		return vertices.getThird();
	}

	public Point2D getSecondVertex() {
		return vertices.getSecond();
	}
	
	public Point2D getFourthVertex() {
		return vertices.getFourth();
	}
	
	public void setVertices(Rectangle b) {
		this.setFirstVertex(b.getFirstVertex());
		this.setSecondVertex(b.getSecondVertex());
		this.setThirdVertex(b.getThirdVertex());
		this.setFourthVertex(b.getFourthVertex());
	}
}
