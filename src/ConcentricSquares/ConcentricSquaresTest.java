package ConcentricSquares;
import static org.junit.Assert.*;

import org.junit.Test;

import Basic.BasicUtil;
import BasicGraphic.RectVertices;
import BasicGraphic.Square;


public class ConcentricSquaresTest {

	@Test
	public void testSmallestSquare1() {
		RectVertices v = new RectVertices();
		v.setFirst(0, 0);
		v.setSecond(1, 0);
		v.setThird(1, 1);
		v.setFourth(0, 1);
		
		Square sq = new Square(v);
		assertTrue(sq.isSmallest());
	}

	@Test
	public void testSmallestSquare2() {
		RectVertices v = new RectVertices();
		v.setFirst(0, 1);
		v.setSecond(1, 0);
		v.setThird(2, 1);
		v.setFourth(1, 2);
		
		Square sq = new Square(v);
		assertTrue(sq.isSmallest());
	}
	
	@Test
	public void testBasicUtilisOdd() {
		assertFalse(BasicUtil.isOdd(2));
		assertTrue(BasicUtil.isOdd(3));
	}
	
	@Test
	public void testSqIsSlope() {
		RectVertices v = new RectVertices();
		v.setFirst(0, 1);
		v.setSecond(1, 0);
		v.setThird(2, 1);
		v.setFourth(1, 2);
		
		Square sq = new Square(v);
		assertTrue(sq.isSlope());
	}
}
