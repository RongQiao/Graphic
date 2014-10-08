package Tetris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import BasicGraphic.Square;

public class TetrisCanvas extends Canvas 
	implements MouseMotionListener, MouseListener, MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int numSquareCellMainW = 10;
	private static final int numSquareCellMainH = 20;	
	private static final int numSquareCellNext = 4;
	private static final int numSquareCellPad = 2;	
	private static final int numTextLine = 3;
	private static final int FONTSIZELARGER = 5;
	
	private static int sqSize = 2;
	private boolean showGridBlockBox = true;
	private boolean pauseShowed = false;
	
	private TButton quitBtn;
	private TBlockBox mainBox;
	private TBlockBox nextBox;
	private TTextBox textBox;
	private TTextBox pauseBox;

	private static final int SHAPES_CNT = 7;
	private Random rand;
	private TBlkType blkOption[];
	private Color colorOption[];
	private TBlockFactory blkFactory;
	
	public TetrisCanvas() {
		rand = new Random();
		blkFactory = TBlockFactory.getInstance();
		initBlockOption();
		
		nextBox = new TBlockBox();
		nextBox.setNumSquareCell_Width(numSquareCellNext + numSquareCellPad);
		nextBox.setNumSquareCell_Height(numSquareCellNext + numSquareCellPad);
		
		mainBox = new TBlockBox();
		mainBox.setNumSquareCell_Width(numSquareCellMainW);
		mainBox.setNumSquareCell_Height(numSquareCellMainH);
		
		textBox = new TTextBox();
		textBox.setNumLines(numTextLine);
		String text[] = {
				"Level:  1",
				"Lines:  0",
				"Score:  0"
		};
		textBox.setText(text);
		
		pauseBox = new TTextBox();
		pauseBox.setNumLines(1);
		pauseBox.setText("PAUSE");
		
		quitBtn = new TButton();
		quitBtn.setText("QUIT");
		
		initNextBox();
		initMainBox();		
		initQuitBtn();
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
	}
	
	private void initBlockOption() {
		blkOption = new TBlkType[SHAPES_CNT];
		//init every block with different shape
		blkOption[0] = TBlkType.ZHLEFT;
		blkOption[1] = TBlkType.ZHRIGHT;
		blkOption[2] = TBlkType.LHLEFT;
		blkOption[3] = TBlkType.LHRIGHT;
		blkOption[4] = TBlkType.SQUARE;
		blkOption[5] = TBlkType.TUP;
		blkOption[6] = TBlkType.STICKH;
		//init color option
		colorOption = new Color[SHAPES_CNT];
		colorOption[0] = Color.RED;
		colorOption[1] = Color.ORANGE;
		colorOption[2] = Color.YELLOW;
		colorOption[3] = Color.GREEN;
		colorOption[4] = Color.PINK;
		colorOption[5] = Color.BLUE;
		colorOption[6] = Color.MAGENTA;
	}

	private void initQuitBtn() {
		// TODO Auto-generated method stub
		
	}

	private void initMainBox() {
		//
		//giveSomeExamples();
		//
		addNewBlock(mainBox);
	}

	//get block from nextBox
	private void addNewBlock(TBlockBox box) {
		TBlock blk = nextBox.getBlock();
		blk.moveToContainer(box);
		
		int y = box.getMaxCellCoordinateY() - blk.getSqNum_Height() + 1;
		int x = (box.getMaxCellCoordinateX() - blk.getSqNum_Width()) / 2;
		blk.init(4, 18);
		//box.addBlock(blk);		
	}

	private void giveSomeExamples() {
		//test
		TBlockBox cont = mainBox;
		TBlock blk = new TBlock_StickH(cont);
		blk.init(1, 1, Color.RED);
		cont.addBlock(blk);
		
		blk = new TBlock_StickH(cont);
		blk.init(5, 1, Color.GREEN);
		cont.addBlock(blk);
		
		blk = new TBlock_Square(cont);
		blk.init(9, 1, Color.YELLOW);
		cont.addBlock(blk);
		
		blk = new TBlock_StickV(cont);
		blk.init(1, 2, Color.BLUE);
		cont.addBlock(blk);
		
		blk = new TBlock_Tup(cont);
		blk.init(2, 2, Color.CYAN);
		cont.addBlock(blk);
		
		blk = new TBlock_ZHLeft(cont);
		blk.init(5, 2, Color.GRAY);
		cont.addBlock(blk);
		
		blk = new TBlock_LVLeft(cont);
		blk.init(9, 3, Color.MAGENTA);
		cont.addBlock(blk);
	}

	//randomly select one of the shapes to show
	private void initNextBox() {
		//test
		TBlockBox cont = nextBox;			
		TBlock blk = randomBlk(cont);
		blk.init(Color.RED);
		//cont.addBlock(blk);
	}

	private TBlock randomBlk(TBlockBox cont) {
		int id = 1;//random(1, SHAPES_CNT);
		TBlkType tp = blkOption[id-1];
		Color cl = colorOption[id-1];
		return blkFactory.createBlock(tp, cl, cont);
	}

	private int random(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

	public void paint(Graphics g) {
		Dimension d = this.getSize();
		sqSize = calculateSquareCellSize(d);
		int minSqSize = calculateSquareCellMinSize(mainBox, pauseBox, 2, g);
		if (minSqSize > sqSize) {
			sqSize = minSqSize;
		}
		mainBox.setSquareSize(sqSize);
		nextBox.setSquareSize(sqSize);
		
		drawMainArea(g);
		drawNextShape(g);
		drawButton(g);
		textBox.setFirstVertex((int)nextBox.getFourthVertex().getX(), 
				(int) nextBox.getFourthVertex().getY());
		textBox.setSecondVertex((int)nextBox.getThirdVertex().getX(), 
				(int) nextBox.getThirdVertex().getY());
		textBox.setThirdVertex((int)nextBox.getThirdVertex().getX(),
				(int) quitBtn.getFirstVertex().getY());
		drawTextArea(g);
	}

	private int calculateSquareCellMinSize(Graphics g) {
		Dimension d = calculateMinSize(g);
		return calculateSquareCellSize(d);
	}
	
	private int calculateSquareCellMinSize(TBlockBox outBox, TTextBox inBox, int sqSize, Graphics g) {
		int minW = outBox.calculateMinWidth(sqSize, inBox.calculateMinWidth(g));
		int minlSq = minW / outBox.getMaxCellCoordinateX();
		return minlSq;
	}
	
	public int calculateSquareCellSize(TBlockBox outBox, TTextBox inBox, int sqSize, Graphics g) {
		int minW = outBox.calculateMinWidth(sqSize, inBox.calculateMinWidth(g));
		int minlSq = minW / outBox.getMaxCellCoordinateX();
		return minlSq;
	}
	
	private void drawPause(Graphics g) {
		Font f = g.getFont();
		Font f4Pause = new Font(f.getFontName(), Font.BOLD, f.getSize() + TetrisCanvas.getFontsizelarger());
		Dimension d = pauseBox.calculateMinSize(f4Pause, g);
		TBox b = mainBox.getMiddleBox(d);
		pauseBox.setVertices(b);	
		g.setFont(f4Pause);
		pauseBox.draw(g, true, TTextBox.MIDDLE);
		pauseShowed = true;
		g.setFont(f);
	}
	
	private void disDrawPause(Graphics g) {
		if (pauseShowed) {
			drawMainArea(this.getGraphics());
			pauseShowed = false;			
		}
	}

	private void drawTextArea(Graphics g) {
		int X = TMargin.getPixelLen() 
				+ mainBox.getPixelWidth()
				+ TPad.getPixelLen();
		int Y = (int)mainBox.getLeftBottomVertex().getY() - quitBtn.getHeight();
		textBox.setLeftBottomVertex(X, Y);
		textBox.draw(g);
	}

	private void drawButton(Graphics g) {
		int X = TMargin.getPixelLen() 
				+ mainBox.getPixelWidth()
				+ TPad.getPixelLen();
		Point2D pLeftBottomVertex = mainBox.getLeftBottomVertex();
		int Y = (int) pLeftBottomVertex.getY();
		quitBtn.setLeftBottomVertex(X, Y);
		quitBtn.setWidth(nextBox.getWidth());
		quitBtn.draw(g);
	}

	private void drawNextShape(Graphics g) {
		int X = TMargin.getPixelLen() 
				+ mainBox.getPixelWidth()
				+ TPad.getPixelLen();
		int Y = TMargin.getPixelLen();
		nextBox.setLeftTopVertex(X, Y);
		nextBox.draw(g, this.isShowGridBlockBox());
		if (nextBox.getBlkNum() > 0) {			
			List<TBlock> blks = nextBox.getBlks();
			int sqSize = nextBox.getSquareSize();
			TBlock blk = blks.get(0); 
			blk = nextBox.setBlkMiddle(blk);
			blk.setSqSize(sqSize);
			blk.draw(g);			
		}
	}



	private void drawMainArea(Graphics g) {
		int X = TMargin.getPixelLen();
		int Y = TMargin.getPixelLen();
		mainBox.setLeftTopVertex(X, Y);
		mainBox.draw(g, this.isShowGridBlockBox());	
		if (mainBox.getBlkNum() > 0) {			
			List<TBlock> blks = mainBox.getBlks();
			int sqSize = mainBox.getSquareSize();
			for (TBlock blk:blks) {
				blk.setSqSize(sqSize);
				blk.draw(g);
			}
		}
	}

	private int calculateSquareCellSize(Dimension d) {
		int len = 0;
		
		int lSq4Width = (d.width - TMargin.getPixelLen() * 2 - TPad.getPixelLen()) 
				/ (mainBox.getMaxCellCoordinateX() + nextBox.getMaxCellCoordinateX());
		int lSq4Height = (d.height - TMargin.getPixelLen() * 2) 
				/ mainBox.getMaxCellCoordinateY();
		len = Math.min(lSq4Width, lSq4Height);
		
		return len;
	}

	public Dimension calculateMinSize(Graphics g) {	
		//the smallest square is the one which has 2 pixels length
		Square minSq = new Square();
		int lSq = minSq.getPixelLength();
		// calculate the min size of the canvas based on the smallest square and the button
		int minWidth = calculateMinWidth(g, lSq);				
		int minHeight = calculateMinHeight(g, lSq);
		//calculate the square size based on the minimum canvas size 
		int newlSq = calculateSquareCellSize(new Dimension(minWidth, minHeight));
		int minlSq = calculateSquareCellSize(mainBox, pauseBox, lSq, g);
		newlSq = Math.max(newlSq, minlSq);
		if (newlSq > lSq) {
			//if the square size changed, recalculate the minimum size 
			minWidth = calculateMinWidth(g, newlSq);
			minHeight = calculateMinHeight(g, newlSq);
		}
		Dimension d = new Dimension(minWidth, minHeight);
		
		return d;
	}

	private int calculateMinHeight(Graphics g, int lSq) {
		int minHeight = lSq * mainBox.getMaxCellCoordinateY() 
				+ TMargin.getPixelLen() * 2;
		
		int minText = textBox.calculateMinHeight(g)
				+ quitBtn.calculateMinHeight(g)
				+ nextBox.calculateMinHeight(g)
				+ TMargin.getPixelLen() * 2;
		int newMinHeight = Math.max(minHeight, minText);
		if (newMinHeight > minHeight) {
			if (doesCreateHalfSquare(newMinHeight)) {
				newMinHeight = adjustMinHeight(newMinHeight);
			}
		}
		return newMinHeight;
	}

	private int adjustMinHeight(int newMinHeight) {
		int lSq4Height = (newMinHeight - TMargin.getPixelLen() * 2) 
				/ mainBox.getMaxCellCoordinateY();
		lSq4Height += 1;
		int h = lSq4Height * mainBox.getMaxCellCoordinateY() + TMargin.getPixelLen() * 2;
		return h;
	}

	private boolean doesCreateHalfSquare(int newMinHeight) {
		boolean ret = false;
		int n = (newMinHeight - TMargin.getPixelLen() * 2) 
				% mainBox.getMaxCellCoordinateX();
		ret = (n > 0)?true:false;
		return ret;
	}

	private int calculateMinWidth(Graphics g, int lSq) {
		Font f = g.getFont();
		Font f4Pause = new Font(f.getFontName(), Font.BOLD, f.getSize() + TetrisCanvas.getFontsizelarger());
		Dimension d = pauseBox.calculateMinSize(f4Pause, g);
		int wMB = mainBox.calculateMinWidth(lSq, d.width);
		int wRight = calculateMinWidthRight(g, lSq);
		int w = wMB
				+ TMargin.getPixelLen() * 2
				+ TPad.getPixelLen()
				+ wRight;
		return w;
	}

	private int calculateMinWidthRight(Graphics g, int lSq) {
		int wQ = quitBtn.calculateMinWidth(g);
		int wN = nextBox.calculateMinWidth(lSq, 0);
		int wT = textBox.calculateMinWidth(g);
		int w = Math.max(wQ, wN);
		w = Math.max(wT, wT);
		return w;
	}

	public void mouseMoved(MouseEvent e) {
		int X = e.getX();
		int Y = e.getY();
		if (mainBox.isInBox(X, Y)) {			
			drawPause(this.getGraphics());
		}
		else {
			disDrawPause(this.getGraphics());
		}
	}


	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int X = arg0.getX();
		int Y = arg0.getY();
		if (quitBtn.isInBox(X, Y)) {
			System.exit(0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isShowGridBlockBox() {
		return showGridBlockBox;
	}

	public void setShowGridBlockBox(boolean showGridBlockBox) {
		this.showGridBlockBox = showGridBlockBox;
	}

	public static int getFontsizelarger() {
		return FONTSIZELARGER;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {	       
	       if (isForwardScroll(e)) {
	    	   System.out.println("forward");
	       } else {
	    	   System.out.println("backward");
	       }
	       
	       int unit = 0;
	       if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
	    	   unit = e.getUnitsToScroll();
	       } else { //scroll type == MouseWheelEvent.WHEEL_BLOCK_SCROLL

	       }

	}

	private boolean isForwardScroll(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		return (notches < 0);	//negative values if the mouse wheel was rotated up/away from the user, and positive values if the mouse wheel was rotated down/ towards the user
	}

}
