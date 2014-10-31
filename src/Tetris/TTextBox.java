package Tetris;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TTextBox extends TBox{
	public static final int MIDDLE = 1;
	public static final int LEFT = 2;
	private String[] lines;

	public TTextBox() {
		lines = new String[1];
		lines[0] = "";
	}
	
	public int getNumLines() {
		return lines.length;
	}

	public void setNumLines(int numLines) {
		lines = new String[numLines];
		for (int i = 0; i < numLines; i++) {
			lines[i] = "";
		}
	}

	public void setText(String[] text) {
		lines = text;
	}
	
	public void setText(String str) {
		lines = new String[1];
		lines[0] = str;
	}

	public String getText() {
		return lines[0];
	}
	
	private void drawBoarder(Graphics g) {
		int X = (int) this.getLeftTopVertex().getX();
		int Y = (int) this.getLeftTopVertex().getY();
		int W = this.getWidth();
		int H = this.getHeight();
		g.drawRect(X, Y, W, H);
	}
	
	private void clear(Graphics g) {
		int X = (int) this.getLeftTopVertex().getX();
		int Y = (int) this.getLeftTopVertex().getY();
		int W = this.getWidth();
		int H = this.getHeight();
		g.clearRect(X, Y, W, H);
	}
	
	private void drawText(Graphics g, int alignStyle) {
		Dimension d = this.calculateMinSize(g.getFont(), g);
		int X = (int) this.getLeftBottomVertex().getX();
		int Y = (int) this.getLeftBottomVertex().getY();
		int W = this.getWidth();
		int H = this.getHeight();	
		
		if (alignStyle == TTextBox.MIDDLE) {
			int wPad = (W - d.width) / 2;
			X = X + wPad;			
		}
		
		int hFont = g.getFontMetrics().getHeight();
		int hAllText = hFont * lines.length;
		int hPad = (H - hAllText)/2;		
		Y = Y - hPad;		
		
		for (int i = lines.length-1; i >= 0; i--) {
			g.drawString(lines[i], X, Y);
			Y -= hFont;
		}		
	}
	
	public void draw(Graphics g, boolean showBorder, int alignStyle) {
		if (showBorder) {
			drawBoarder(g);
		}
		drawText(g, alignStyle);
	}

	public void draw(Graphics g) {
		clear(g);
		draw(g, false, TTextBox.LEFT);
	}

	public int calculateMinHeight(Graphics g) {
		int hFont = g.getFontMetrics().getHeight();
		int h = hFont * this.getNumLines();
		return h;
	}

	public Dimension calculateMinSize(Font f, Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D bounds = f.getStringBounds(this.getText(), g2.getFontRenderContext());
		int W = (int) bounds.getWidth();
		int H = (int) bounds.getHeight();
		Dimension d = new Dimension(W, H);
		return d;
	}

	public int calculateMinWidth(Graphics g) {
		Font f = g.getFont();
		Font f4Pause = new Font(f.getFontName(), Font.BOLD, f.getSize() + TetrisCanvas.getFontsizelarger());
		Dimension d = this.calculateMinSize(f4Pause, g);
		return d.width;
	}


}
