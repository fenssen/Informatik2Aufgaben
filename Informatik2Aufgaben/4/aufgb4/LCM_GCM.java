import java.util.ArrayList;

/**
 * Diese Klasse besitzt zwei Methoden zur Berechnung des lcm (lowest common multiple) und des gcd (greatest common divisor).
 *
 * @author firstlady
 */
public class LCM_GCM
{
	/**
	 * Gibt den gcd (greatest common divisor) zurück.
	 *
	 * @param a Die erste Zahl
	 * @param b Die zweite Zahl
	 * @return Den gcd von a und b
	 */
	public static int gcd(int a, int b)
	{
		if (a < 0 || b < 0)
			throw new IllegalArgumentException("a und b muessen >=0 sein!");
		
		if (a == 0)
			return b;
		else
			while (b != 0)
				if (a > b)
					a = a - b;
				else
					b = b - a;
		
		return a;
	}
	
	/**
	 * Gibt den lcm (lowest common multiple) zurück.
	 *
	 * @param a Die erste Zahl
	 * @param b Die zweite Zahl
	 * @return Den lcm von a und b
	 */
	public static int lcm(int a, int b)
	{
		if (a < 0 || b < 0)
			throw new IllegalArgumentException("a und b muessen >=0 sein!");
		
		return (a * b) / gcd(a, b);
	}
	
	/**
	 * Gibt den lcm von den Zahlen 3 und 4 auf den Bildschirm aus.
	 *
	 * @param args Optionale Argumente
	 */
	public static void main(String[] args)
	{
		System.out.println( lcm(3, 4) );
	}
}