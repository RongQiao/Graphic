package Tetris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class TButton extends TTextBox{
	private static int lPad = 2;
	private int boxWidth;
	
	public int getHeight() {
		Point2D plb = this.getLeftBottomVertex();
		Point2D plt = this.getLeftTopVertex();
		int h = (int)(plb.getY() - plt.getY());
		return h;
	}
	
	public int calculateMinHeight(Graphics g) {
		Dimension d = this.calculateMinSize(g.getFont(), g);
		return d.height;
	}
	
	public int calculateMinWidth(Graphics g) {
		Dimension d = this.calculateMinSize(g.getFont(), g);
		int w = d.width + TButton.getlPad() * 2;
		return w;
	}

	public void draw(Graphics g) {
		Dimension d = this.calculateMinSize(g.getFont(), g);
		int X = (int) this.getLeftBottomVertex().getX();
		int Y = (int) this.getLeftBottomVertex().getY();
		String s = this.getText();
		
		int H = calculateMinHeight(g);
		//set up vertices
		g.drawRect(X, Y-H, boxWidth, H);
		this.setFirstVertex(X, Y-H);
		this.setSecondVertex(X+boxWidth, Y-H);
		this.setThirdVertex(X+boxWidth, Y);

		g.drawString(s, X + (boxWidth - d.width) / 2, Y-getlPad());
	}

	public static int getlPad() {
		return lPad;
	}

	public static void setlPad(int lPad) {
		TButton.lPad = lPad;
	}

	public void setWidth(int width) {
		boxWidth = width;
	}
	
}
