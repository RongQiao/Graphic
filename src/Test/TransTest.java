package Test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;

import org.junit.Test;

import Tetris.TBlock.RotateDirection;
import transformation.Matrix;
import transformation.Transformation2D;
import transformation.Transformation2D.PositionDirection;

public class TransTest {
	@Test
	public void testRMatrix() {
		Matrix mt = Transformation2D.getRotationMatrix(90);
		assertTrue((int)mt.getValue(0, 0) == 0);
		assertTrue((int)mt.getValue(0, 1) == 1);
		assertTrue((int)mt.getValue(1, 0) == -1);
		assertTrue((int)mt.getValue(1, 1) == 0);
	}

	@Test
	public void testR90() {
		Point2D p = new Point(2, 0);
		p = Transformation2D.calculateRotation(p, 90);
		assertTrue((int)p.getX() == 0);
		assertTrue((int)p.getY() == 2);
	}
	
	@Test
	public void testR_90() {
		Point2D p = new Point(3, 0);
		p = Transformation2D.calculateRotation(p, -90);
		System.out.println(p.getX() + "," + p.getY());
		assertTrue((int)p.getX() == 0);
		assertTrue((int)p.getY() == -3);
	}

	@Test
	public void testPD() {
		PositionDirection pd = PositionDirection.CLOCK3;
		PositionDirection newPd = pd.next(3);
		int degree = Transformation2D.calculateRotaDegree(pd, newPd);
		assertTrue(newPd == PositionDirection.CLOCK6);
		assertTrue(degree == -90);
	}
	
	@Test
	public void testPD2() {
		PositionDirection pd = PositionDirection.CLOCK9;
		PositionDirection newPd = pd.next(3);
		int degree = Transformation2D.calculateRotaDegree(pd, newPd);
		assertTrue(newPd == PositionDirection.CLOCK12);
		assertTrue(degree == -90);
	}
	
	@Test
	public void testPDLinjie() {
		PositionDirection pd = PositionDirection.CLOCK12;
		PositionDirection newPd = pd.next(3);
		int degree = Transformation2D.calculateRotaDegree(pd, newPd, RotateDirection.CLOCKWISE);
		assertTrue(newPd == PositionDirection.CLOCK3);
		assertTrue(degree == -90);
	}
}
