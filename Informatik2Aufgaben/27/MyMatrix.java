/**
 * Diese Klasse dient dazu, eine antisymmetrische Matrix, effizient zu speichern.
 * Mittels des Konstruktors kann man eine antisymmetrische Matrix uebergeben,
 * welche effizient in einem einzigen Array gespeichert wird.
 *
 * @author firstlady
 * @version 1.0
 */
public class MyMatrix {
	final double[] n;
	final int width;

	/**
	 * Erzeugt ein MyMatrix-Objekt. Die uebergebene Matrix wird in einem
	 * einzigen Array gespeichert.
	 *
	 * @param  matrix Die antisymmetrische Matrix
	 * @throws IllegalArgumentException Wenn keine (antisymmetrische) Matrix
	 *		   uebergeben wird
	 */
	public MyMatrix(double[][] matrix) {
		if (matrix.length == 0)
			throw new IllegalArgumentException("Argument 'matrix' is an empty array!");
		if (!isMatrix(matrix))
			throw new IllegalArgumentException("Argument 'matrix' is not a matrix!");
		if (matrix.length != matrix[0].length)
			throw new IllegalArgumentException("Argument 'matrix' is not a square matrix!");

		n = new double[(matrix.length*(matrix.length-1))/2];
		width = matrix.length;

		int k = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i+1; j < matrix.length; j++) {
				if (matrix[i][j] != -1*matrix[j][i])
					throw new IllegalArgumentException("Argument 'matrix' is not a antisymmetric matrix!");
				n[k] = matrix[i][j];
				k++;
			}
		}
	}

	/**
	 * Ueberprueft, ob das uebergebene Array eine Matrix ist, also ob die
	 * inneren Arrays alle die gleiche Dimension (Laenge) haben.
	 *
	 * @param  d Das zu ueberpruefende Array
	 * @return <code>true</code>, wenn d eine Matrix ist, <code>false</code>
	 *		   ansonsten
	 */
	private boolean isMatrix(double[][] d) {
		final int length = d[0].length;
		for (int i = 1; i < d.length; i++)
			if (d[i].length != length)
				return false;
		return true;
	}

	/**
	 * Gibt den i-j-ten Eintrag der Matrix zurueck.
	 *
	 * @param  i Der Zeilenindex
	 * @param  j Der Spaltenindex
	 * @return Den i-j-ten Eintrag
	 * @throws IllegalArgumentException Wenn die Indizes nicht gueltig sind
	 */
	public double get(int i, int j) {
		if (i < 1 || j < 1 || i > n.length || j > n.length)
			throw new IllegalArgumentException("No valid indices!");

		if (i < j) {
			int index = (width*(width-1) - (width-i+1)*(width-i)) / 2 + (j-i-1);
			return n[index];
		} else if (i > j) {
			return -1*get(j, i);
		} else {
			return 0;
		}
	}

	/*
	 * Fuehrt ein paar Tests durch...
	 */
	public static void main(String[] args) {
		double[][] d = {
							{ 0, 2, 3, 4 },
							{ -2, 0, 5, 6 },
							{ -3, -5, 0, 7 },
							{ -4, -6, -7, 0 },
					   };
		int i = 4, j = 2;
		MyMatrix m = new MyMatrix(d);
		System.out.println("Matrix:");
		for (int k = 0; k < d.length; k++)
			System.out.println(java.util.Arrays.toString(d[k]));
		System.out.printf("a[%d][%d] = %f\n", i, j, m.get(i, j));
	}
}
