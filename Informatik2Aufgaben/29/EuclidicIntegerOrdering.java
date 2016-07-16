/**
 * Diese Klasse definiert eine totale Ordnung auf den Ganzzahlen. Entweder in
 * der natuerlichen oder der entgegengesetzten Reihenfolge.
 */
public class EuclidicIntegerOrdering implements Ordering<Integer> {
	private final int ord;
	public static final int NATURAL = 1,
							REVERSE = 0;

	/**
	 * Erzeugt eine Ordnung auf den natuerlichen Zahlen mit der spezifierten
	 * Richtung. D.h. entweder ordnet es die Zahlen natuerlich oder reverse.
	 *
	 * @param  ord Die Richtung
	 */
	public EuclidicIntegerOrdering(int ord) {
		switch (ord) {
			case NATURAL:
			case REVERSE:
				this.ord = ord;
				break;
			default:
				this.ord = NATURAL;
		}
	}

	/**
	 * Vergleicht zwei ganze Zahlen. Das Ergebnis ist kleiner, gleich oder
	 * groesser null, wenn die erste Zahl kleiner, gleich gross oder groesser
	 * als die zweite Zahl ist.
	 *
	 * @param  i1 Die erste Zahl
	 * @param  i2 Die zweite Zahl
	 * @return Eine Zahl kleiner, gleich oder groesser null
	 */
	@Override
	public int compare(Integer i1, Integer i2) {
		if (i1 == null)
			throw new NullPointerException("Argument 'i1' is null!");
		if (i2 == null)
			throw new NullPointerException("Argument 'i2' is null!");

		switch (ord) {
			case NATURAL:
				return (i1 - i2);
			case REVERSE:
				return -(i1 - i2);
			default:
				return 0;
		}
	}
}
