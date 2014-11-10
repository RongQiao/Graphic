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

		TetrisFrame(int scoreFactor, int levelGate, double speedFactor) {
			super("Tetris");
			
			setSize(500, 700);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container con = this.getContentPane();
			TScoreManager scoreMng = new TScoreManager();
			scoreMng.setScoreFactor(scoreFactor);
			scoreMng.setLevelGate(levelGate);
			scoreMng.setSpeedFactor(speedFactor);
			//test
			//System.out.println(scoreMng.getScoreFactor() + "," + scoreMng.getLevelGate() + "," + scoreMng.getSpeedFactor());
			canvas = new TetrisCanvas(scoreMng);
			con.add(canvas);			
			setVisible(true);			
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

}
