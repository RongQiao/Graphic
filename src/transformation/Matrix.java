package transformation;

public class Matrix {
	double m[][];

	public Matrix(int rowCnt, int colCnt) {
		m = new double[rowCnt][colCnt];
	}

	public void setValue(double[][] ds) {
		m = ds;
	}

	public static Matrix multiple(Matrix m1, Matrix m2) {
		int rowCnt = m1.getRowCnt();
		int colCnt = m2.getColCnt();
		Matrix ret = new Matrix(rowCnt, colCnt);
		for (int i = 0; i < rowCnt; i++) {
			for (int j = 0; j < colCnt; j++) {
				double cell = multiple(m1.getRow(i), m2.getCol(j));
				ret.setValue(i, j, cell);
			}
		}
		return ret;
	}
	
	//i, j is based on 0
	public void setValue(int i, int j, double value) {
		m[i][j] = value;
	}

	public static double multiple(double[] row, double[] col) {
		double v = 0.0;
		int rc = row.length, cc = col.length;
		if (rc == cc) {
			for (int i = 0; i < rc; i++ ) {
				v += row[i] * col[i];
			}
		}
		return v;
	}

	private double[] getRow(int index) {
		return m[index];
	}

	private double[] getCol(int index) {
		int rowCnt = this.getRowCnt();
		double[] ret = new double[rowCnt];
		for (int i = 0; i < rowCnt; i++) {
			ret[i] = m[i][index];
		}
		return ret;
	}

	private int getColCnt() {
		return m[0].length;
	}

	private int getRowCnt() {
		return m.length;
	}

	//input is based on 0
	public double getValue(int i, int j) {
		return m[i][j];
	}

}
