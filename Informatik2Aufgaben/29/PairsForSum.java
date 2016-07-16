import java.util.Arrays;

public class PairsForSum {
	/**
	 * Findet zu einer gegebenen Menge val von paarw. versch. natuerlichen
	 * Zahlen die Menge aller geordneten Paare (a, b) mit a+b = sum.
	 *
	 * @param  values Die Menge der paarw. versch. natuerlichen Zahlen
	 * @param  sum Die gesuchte Summe
	 * @return Die Menge der geordneten Paare
	 */
	public static int[][] computePairsForSum(int[] values, int sum) {
		if (values == null)
			throw new NullPointerException("'values' is null!");
		if (values.length == 0)
			throw new IllegalArgumentException("'values' is empty!");

		// Array kopieren
		int[] val = values.clone();

		// Die totale Ordnung auf Ganzzahlen
		Ordering<Integer> ord = new EuclidicIntegerOrdering(EuclidicIntegerOrdering.NATURAL);

		// int[] in Integer[] konvertieren
		Integer[] tmp = new Integer[val.length];
		for (int cnt = 0; cnt < val.length; cnt++)
			tmp[cnt] = val[cnt];

		// Heap initalisieren und sortieren
		Heap<Integer> heap = new Heap<Integer>(tmp, ord);
		heap.sort();

		// Integer[] in int[] konvertieren
		Object[] temp = heap.getHeap();
		for (int cnt = 0; cnt < tmp.length; cnt++)
			val[cnt] = (Integer) temp[cnt];


		// Geordnete Paare mit binaerer Suche finden
		int[][] res = new int[val.length][2];
		int i = 0;
		for (int cnt = 0; cnt < val.length; cnt++) {
			int complement = sum - val[cnt];
			if (complement == val[cnt])
				continue;
			if (Arrays.binarySearch(val, cnt+1, val.length, complement) >= 0) {
				int[] t1 = { val[cnt], complement },
					  t2 = { complement, val[cnt] };
				res[i] = t1;
				res[i+1] = t2;
				i += 2;
			}
		}

		// Laenge des Ergebnis-Arrays anpassen
		res = Arrays.copyOf(res, i);

		return res;
	}

	// Tests...
	public static void main(String[] args) {
		// Ein Zufallszahlengenerator
		java.util.Random rand = new java.util.Random();
		int[] ints = new int[10];

		// Ein Array mit paarweise verschiedenen Zahlen generieren
		flag:
		for (int i = 0; i < ints.length;) {
			ints[i] = rand.nextInt(25)+1;
			for (int cnt = 0; cnt < i; cnt++)
				if (ints[cnt] == ints[i])
					continue flag;
			i++;
		}

		int sum = 25;

		// Die geordneten Paare ausgeben
		System.out.println("Zahlen:\n" + Arrays.toString(ints));
		System.out.println("Gesuchte Summe:\n" + sum);
		System.out.println("Geordnete Paare:\n" + Arrays.deepToString(computePairsForSum(ints, sum)));
	}
}
