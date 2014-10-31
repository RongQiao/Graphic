package Tetris;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextField;

import javax.swing.JFrame;

public class TetrisFrame extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private TetrisCanvas canvas;

		TetrisFrame() {
			super("Tetris");
			
			setSize(500, 700);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container con = this.getContentPane();
			canvas = new TetrisCanvas();
			con.add(canvas);			
			setVisible(true);			
			TextField userText = new TextField(6);		
			this.add(userText);
		}
		
		private Dimension calculateMinSize(Graphics g) {
			Dimension d = canvas.calculateMinSize(g);
			d.width = d.width + 8 * 2;
			d.height = d.height + 30 + 8;
			return d;
		}
		
		public void paint(Graphics g) {
			Dimension minD = calculateMinSize(g);
			this.setMinimumSize(minD);
		}

		public static void main(String[] args) {
			new TetrisFrame();
		}

}
