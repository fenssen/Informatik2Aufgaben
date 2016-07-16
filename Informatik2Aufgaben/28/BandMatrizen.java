
/**
 * Eine Klasse zum mulitpliezeren von Bandmatrizen
 *
 * @author Felix Janssen
 * @version 1.0
 */
public class BandMatrizen
{
    /**
     * Multipliziert zwei Bandmatrizen und gibt die resultierende Bandmatrix aus.
     *
     * @param  a1 erste Bandmatrix
     * @param  a2 zweite Bandmatrix
     * @param  n Anzahl der Zeilen/Spalten der BandMatrizen
     * @param  b Anzahl der Baender
     * @return Produkt der Bandmatrizen
     */
    /*
     * Laufzeit des Algorithmus:
     * 	1. Die aussere Schleife wird n-mal durchlaufen
     * 	2. Die mittlere Schleife wird maximal 4b+1 mal durchlaufen
     *	3. Die innere Schleife wird 2b+1 mal durchlaufen
     * Insgesamt ergibt sich die Laufzeit
	 * 		T(n) = n(4b+1)(2b+1) = 8nb^2 + 6nb + n
	 * Also liegt die Laufzeit bei nb^2 
     */
    public static int[][] multipliziereBandmatrizen(int[][] a1, int[][] a2, int n, int b)
    {
		if (a1 == null || a2 == null)
			throw new NullPointerException("a1 or a2 is null!");
		if (a1.length == 0 || a2.length == 0)
			throw new IllegalArgumentException("a1 or a2 is empty!");

        int[][] c = new int[n][4*b+1];

        for (int i = 0; i < n; i++) {
			int j = 0,
				bound = 4*b+1;
			if (i < b)
				j += b-i+1;
			if (i > n-b)
				bound += n-i-b-1;
			
          	for (; j < bound; j++) {
	            c[i][j] = 0;

	            for (int k = -b; k <= b; k++){
		            int tmp;

		            // Fuer die Dummy-Elemente wird eine Umsetzung durch Exceptions implementiert
		            try {
		                tmp = a1[i][b+k];
		                tmp *= a2[i+k][j-i-k];
		            } catch (ArrayIndexOutOfBoundsException e) {
		                continue;
		            }

	              	c[i][j] += tmp;
	          	}
        	}
        }
      	return c;
    }

	// Ein paar Tests machen...
	public static void main(String[] args){

        int[][] a = {
						{ 0, 1, 2 },
						{ 3, 4, 6 },
						{ 5, 7, 0 }
					},
        		b = {
						{ 0, -1, 1 },
						{ 1, -1, 1 },
						{ -1, 1, 0 }
					},
        		c = BandMatrizen.multipliziereBandmatrizen(a, b, 3, 1);
        System.out.println("A:");
		printMatrix(a);
        System.out.println("\nB:");
		printMatrix(b);
        System.out.println("\nErgebnis:");
		printMatrix(c);
    }

	// Gibt Bandmatrizen auf der Konsole aus... Algo ist relativ kompliziert
	private static void printMatrix(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			int j = 0, bound = a[0].length;
			if (i < a[0].length/2)
				j += a[0].length/2-i;
			if (i > a.length-a[0].length/2-1)
				bound += a.length-i-1-a[0].length/2;
			for (int cnt = 0; cnt < i-a[0].length/2+j; cnt++)
				System.out.print("0 ");
			for (; j < bound; j++)
				System.out.print(a[i][j] + " ");
			for (int cnt = i + a[0].length/2+1; cnt < a.length; cnt++)
				System.out.print("0 ");
			System.out.print("\n");
		}
	}
}
