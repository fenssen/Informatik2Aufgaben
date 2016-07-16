import java.math.BigInteger;

/**
 * Implementation of a simple RISC-Machine that can save three integers in one
 * cell.
 * <p>
 * The cell has variable size, so that even the greatest integers can be
 * hold. A cell is represented by a BigInteger object.
 *
 * @author firstlady
 * @version 1.1
 */
public class RISC_Machine_v11
{
	/**
	 * Reads three integers from the given cell.
	 *
	 * @param cell The cell holding the three values
	 * @return Three integers
	 */
	public int[] read(BigInteger cell)
	{
		// Abfangen ungueltiger Parameter
		if (cell == null)
			throw new NullPointerException("Argument 'cell' is null!");

		// Konvertierung der Zelle in einen String
		String num = cell.toString();

		// Ermittlung der Laenge eines Blocks
		final int blocklength = num.length()/3;

		// Rekonvertierung der Zahlen
		StringBuilder[] val = new StringBuilder[3];
		int[] numbers = new int[3];
		for (int cnt = 0; cnt < 3; cnt++)
		{
			val[cnt] = new StringBuilder( num.substring(blocklength*cnt, blocklength*(cnt+1)) );

			// Loeschen der ueberfluessigen 0en
			while (val[cnt].charAt(1) == 0)
				val[cnt].replace(1, 2, "");

			// Bestimmung des Vorzeichens
			if (val[cnt].charAt(0) == '1')
				val[cnt].setCharAt(0, '-');
			else
				val[cnt].replace(0, 1, "");

			// Konstruktion der Integer
			numbers[cnt] = Integer.parseInt(val[cnt].toString());
		}

		return numbers;
	}

	/**
	 * Writes three integers to one cell.
	 *
	 * @param numbers Three integers
	 * @return The cell that has been written to
	 * @throws IllegalArgumentException When the array does not contain exactly
	 * three values
	 */
	public BigInteger write(int[] numbers)
	{
		// Abfangen ungueltiger Parameter
		if (numbers == null)
			throw new NullPointerException("Argument 'numbers' is null!");
		if (numbers.length != 3)
			throw new IllegalArgumentException("Argument 'numbers' does not contain exactly three values!");

		// Speicherung des Vorzeichens
		StringBuilder[] val = new StringBuilder[3];
		for (int cnt = 0; cnt < 3; cnt++)
		{
			val[cnt] = new StringBuilder( String.valueOf(numbers[cnt]));
			if (val[cnt].charAt(0) == '-')
				val[cnt].replace(0, 1, "1");
			else
				val[cnt].insert(0, '0');
		}

		// Ermittlung die Laenge eines Blocks
		final int blocklength = Math.max(val[0].length(), Math.max(val[1].length(), val[2].length()));

		// Konstruktion des Gesamtstrings
		StringBuilder result = new StringBuilder();
		for (int cnt = 0; cnt < 3; cnt++)
		{
			// Auffuellen mit 0en, damit die Blocklaenge erreicht wird
			for (int i = blocklength - val[cnt].length(); i > 0; i--)
				val[cnt].insert(1, '0');
			result.append(val[cnt]);
		}

		// Konstruktion des BigIntegers
		return new BigInteger(result.toString());
	}
}
