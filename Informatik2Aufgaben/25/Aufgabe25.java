import java.util.Stack;
import java.util.EmptyStackException;
import java.io.*;

/*
 * -----------------------------------------------------------------------------
 * Maximale Groesse des Stacks:
 * Der worst-case waere, dass die nach der Partitionierung ermittelte Trenn-
 * stelle der ganz rechte Index ist.
 * D.h.: Nach der ersten Partitionierung ergibt sich die Trennstelle a.length-1,
 * nach der zweiten Partitionierung a.length-2 usw.
 * Der Stack sieht dann nach n-1 Partitionierungen so aus:
 * 0
 * 2
 * 3
 * 4
 * ...
 * a.length-2
 * a.length-1
 * a.length
 * Nach der n-1ten Partitionierung wird der Stack (wie man auch dem Quellcode
 * entnehmen kann) kleiner, d.h. nach n-1 Partitionierungen erreicht er im
 * worst-case seine maximale Grosse.
 * Das heisst im Endeffekt, dass die Maximalgroesse des Stacks im worst-case n
 * ist.
 * -----------------------------------------------------------------------------
 */

/**
 * Diese Klasse besteht aus dem QuickSort-Algorithmus zum sortieren von
 * beliebigen Objekten. Die Implementierung ist iterativ.
 *
 * @author firstlady
 * @version 1.0
 */
public class Aufgabe25 {
	/**
     * Fuellt ein Eingabearray mit Zufallszahlen.
     * @param a das Eingabearray
     */
    private static void fillArray(Integer[] a) {
        java.util.Random r = new java.util.Random();
        for (int i=0; i<a.length; i++) {
            a[i] = r.nextInt(a.length) + 1;

            // dafuer sorgen, dass jeder Wert von 1 bis a.length
            // nur genau einmal im Array vorkommt
            for (int k=0; k<i; k++) {
                if (a[i] == a[k]) {
                    i--;
                    break;
                }
            }
        }
    }

	/**
	 * Ermittelt den Median von den gegebenen Objekten.
	 *
	 * @param  args Die zu ueberpruefenden Objekten
	 * @return Den Median
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T median(T... args) {
		// Sortiert das Array mittels InsertionSort
		for (int i = 0; i < args.length; i++) {
			T tmp = args[i];
			int j = i;
			for (; j > 0 && args[j-1].compareTo(tmp) > 0; j--) {
				args[j] = args[j-1];
			}
			args[j] = tmp;
		}
		// Returnt das mittlere Element
		return args[args.length/2];
	}

	/**
	 * Sortiert ein gegebenes Array von Objekten in aufsteigender Reihenfolge
	 * mittels eines iterativen QuickSort-Algorithmus.
	 *
	 * @param a Die zu sortierenden Objekte
	 * @throws IllegalArgumentException Wenn das Array keine Objekte enthaelt
	 */
	public static <T extends Comparable<T>> void quickSort(T[] a) {
		// Ueberpruefen der uebergebenen Argumente
		if (a == null)
			throw new NullPointerException("Das Array a darf nicht null sein!");
		if (a.length == 0)
			throw new IllegalArgumentException("Das Array a muss Objekte enthalten!");

		// Erstellen des Stacks, welcher die Grenzen speichert
		Stack<Integer> bounds = new Stack<Integer>();

		// Das try dient dazu, eine EmptyStackException abzufangen, die bei Ende
		// des Sortierens auftritt (bzw. sogar auftreten soll)
		try {
			// Liest die aktuellen Grenzen aus dem Stack aus
			for (int bound_right = bounds.push(a.length), bound_left = 0;
						!bounds.empty();
								bound_left = bounds.pop(), bound_right = bounds.peek()) {
				// Ueberprueft, ob mehr als zwei Objekte sortiert werden
				if (bound_right - bound_left > 2) {
					@SuppressWarnings("unchecked")
					// Ermittlung des Medians
					T med = median(a[bound_left], a[(bound_right+bound_left)/2], a[bound_right-1]);
					int	i = bound_left;

					// Partioniert das Array entsprechend dem Median
					for (int j = bound_right-1; i <= j;) {
						// Suche nach dem naechsten Objekt, welches ueber dem
						// Median liegt
						while (a[i].compareTo(med) < 0) {
							i++;
						}
						// Suche nach dem naechsten Objekt, welches unter dem
						// Median liegt
						while (a[j].compareTo(med) > 0) {
							j--;
						}
						// Tausch zweier Array-Eintraege
						if (i <= j) {
							T tmp = a[i];
							a[i]  = a[j];
							a[j]  = tmp;
							i++;
							j--;
						}
					}

					// Pusht die neuen Grenzen auf den Stack
					bounds.push(i);
					bounds.push(bound_left);
				} else {
					// Sortieren von zwei Objekten
					if (a[bound_left].compareTo(a[bound_right-1]) > 0) {
						T tmp 			 = a[bound_left];
						a[bound_left] 	 = a[bound_right-1];
						a[bound_right-1] = tmp;
					}
				}
			}
		} catch (EmptyStackException e) {}
	}

	/**
	 * Liest Integer aus einem ASCII-File und speichert diese im uebergebenen
	 * Array.
	 *
	 * @param  file Der zu lesende File
	 * @param  a Das zu beschreibende Array
	 */
	private static void readArrayFromFile(String file, Integer[] a)	{
		try (FileReader fr = new FileReader(file))
		{
			StringBuilder s = new StringBuilder("");
			for (int i = 0; i < a.length; i++)
			{
				for (int c = fr.read(); c != '\n' && c != -1; c = fr.read())
				{
					s.append((char)c);
				}
				a[i] = Integer.parseInt(s.toString());
				s = new StringBuilder("");
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ungueltige Datei: " + e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Das Eingelesene war keine Zahl: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Integer[] a = new Integer[100];

		if (args.length == 0)
			fillArray(a);
		else if (args[0].equals("asc"))
			readArrayFromFile("/home/benedikt/SourceCode/Java/wwu/informatik2/"
								+"07/Eingabedateien/aufsteigend.txt", a);
		else if (args[0].equals("desc"))
			readArrayFromFile("/home/benedikt/SourceCode/Java/wwu/informatik2/"
								+"07/Eingabedateien/absteigend.txt", a);
		else if (args[0].equals("rnd"))
			readArrayFromFile("/home/benedikt/SourceCode/Java/wwu/informatik2/"
								+"07/Eingabedateien/zufall.txt", a);
		else
			fillArray(a);

		System.out.println(java.util.Arrays.toString(a));
		System.out.println("Sorting the array with iterative quicksort...");
		quickSort(a);
		System.out.println(java.util.Arrays.toString(a));
		System.out.println("Done");
	}
}
