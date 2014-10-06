package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import BasicGraphic.Square;

public class BasicGraphicTest {

	@Test
	public void rectLength() {
		Square sq = new Square();
		int l = sq.getPixelLength();
		assertTrue(l == 2);
	}

}
