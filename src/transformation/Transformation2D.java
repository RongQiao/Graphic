package transformation;

import java.awt.Point;
import java.awt.geom.Point2D;

import Tetris.TBlock.RotateDirection;
import transformation.Transformation2D.PositionDirection;

public class Transformation2D {
	public enum PositionDirection {
		CLOCK3(3),
		CLOCK6(6),
		CLOCK9(9),
		CLOCK12(12);
		private int value;
		PositionDirection(int v) {
			this.value = v;
		}
		public int getValue() {
			return this.value;
		}
		public PositionDirection next(int gap) {
			PositionDirection newPd = null;
			int v = getValue() + gap;
			if (v > 12) {
				v %= 12;
			}
			switch (v) {
			case 6:
				newPd = CLOCK6;
				break;
			case 9:
				newPd = CLOCK9;
				break;
			case 12:
				newPd = CLOCK12;
				break;
			case 3:
			default:
				newPd = CLOCK3;
				break;
			}
			return newPd;			
		}
	}

	public static Point2D calculateRotation(Point2D sqCoordinate, int degree) {
		Matrix coorValue = new Matrix(1, 2);
		coorValue.setValue(0, 0, sqCoordinate.getX());
		coorValue.setValue(0, 1, sqCoordinate.getY());
		Matrix R = getRotationMatrix(degree);
		coorValue = Matrix.multiple(coorValue, R);
		int x = (int)coorValue.getValue(0, 0), y = (int)coorValue.getValue(0, 1);
		Point2D p = new Point(x, y);
		return p;
	}

	public static Matrix getRotationMatrix(int degree) {	
		Matrix Mr = new Matrix(2, 2);
		double dRad = Math.toRadians(degree);
		double r11 = (int)Math.cos(dRad);		
		double r12 = (int)Math.sin(dRad);
		double r21 = -r12;
		double r22 = r11;
		double values[][] = {
				{r11, r12},
				{r21, r22}};
		Mr.setValue(values);
		return Mr;
	}

	public static int calculateRotaDegree(PositionDirection oldPd,
			PositionDirection newPd, RotateDirection rd ) {
		int gap = oldPd.getValue() - newPd.getValue();
		if ((rd == RotateDirection.CLOCKWISE) && (gap > 0)) {
			gap = oldPd.getValue() - (newPd.getValue() + 12);
		}
		int degree = (gap/3) * 90;
		return degree;
	}

	public static int calculateRotaDegree(PositionDirection oldPd,
			PositionDirection newPd) {
		return calculateRotaDegree(oldPd, newPd, RotateDirection.CLOCKWISE);
	}

	public static Point2D calculateTranlation(Point2D p,
			Point2D origin) {
		int x = (int) (p.getX() + origin.getX());
		int y = (int) (p.getY() + origin.getY());
		return (new Point(x, y));
	}

	public static Point2D calculateTranlation(Point2D pInContainer,
			Point2D containerOrigin, int xStep, int yStep) {
		int x = (int) (containerOrigin.getX() + xStep * (pInContainer.getX()-1));	//-1 because the base is 1
		int y = (int) (containerOrigin.getY() + yStep * pInContainer.getY());	
		return (new Point(x, y));
	}

}
