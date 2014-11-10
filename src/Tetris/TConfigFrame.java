package Tetris;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TConfigFrame  extends JFrame {
	JTextField mText;
	JTextField nText;
	JTextField sText;
	
	TConfigFrame(String title) {
		super(title);
		
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container con = getContentPane();
		con.setLayout(new BoxLayout(con,  BoxLayout.PAGE_AXIS));
	    
		JPanel mP = new JPanel();
		mP.setLayout(new FlowLayout());
		JLabel mL = new JLabel("score factor: ");
		mText = new JTextField("1", 8);
		mP.add(mL);
		mP.add(mText);
		
		JPanel nP = new JPanel();
		nP.setLayout(new FlowLayout());
		JLabel nL = new JLabel("level gate:    ");
		nText = new JTextField("2", 8);
		nP.add(nL);
		nP.add(nText);
		
		JPanel sP = new JPanel();
		sP.setLayout(new FlowLayout());
		JLabel sL = new JLabel("speed factor:");
		sText = new JTextField("0.1", 8);
		sP.add(sL);
		sP.add(sText);
		
		JPanel bP = new JPanel();
		bP.setLayout(new FlowLayout());
		JButton btn = new JButton("start game");
		bP.add(btn);
		
		con.add(mP);
	    con.add(nP);
	    con.add(sP);
	    con.add(bP);

	    btn.addMouseListener(new MouseListener () {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//save factor
				int scoreFactor = Integer.parseInt(mText.getText());
				int levelGate = Integer.parseInt(nText.getText());
				double speedFactor = Double.parseDouble(sText.getText());
				new TetrisFrame(scoreFactor, levelGate, speedFactor);
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        });      
	    
	    setVisible(true);
	}

	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } catch (Exception evt) {}
		
		new TConfigFrame("Tetris Configure");

	}
}
