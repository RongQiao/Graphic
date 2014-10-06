package ConcentricSquares;
import java.awt.Container;
import javax.swing.JFrame;


public class ConcentricSquaresFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConcentricSquaresFrame() {
		super("Concentric Squares");
		setSize(500, 301);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane();
		ConcentricSquares csCanvas = new ConcentricSquares();
		con.add(csCanvas);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ConcentricSquaresFrame();
	}
}
