import java.util.Arrays;
public class PerfectHashing
{
    //main-Methode
    public static void main(String[] args)
    {
        int[] array = {23,35,56,81,86,91};
        
        System.out.println("Perfekte Hashfunktion ist gegeben durch: h(x) = (x*1000/z)%6");
        
        System.out.println("\ndie Werte " + Arrays.toString(array) + " werden auf die folgenden Werte geschickt:");
        test(array);
        
    }
    /**
     * Hashfunktion die zu testendes Array und Variable uebergeben bekommt und dann ueberprueft, ob Wertebereich der Hashtabelle die Zahlen 1-5 beinhaltet
     * @param z Zahlen im Array, für welche Hashfunktion gefunden werden soll
     * @param x in diesem Fall Primzahl, die Hashfunktion leicht veraendert
     * @return Array, welches Bedingungen erfuellt
     * @return sonst wird null-Array zurueckgegeben
     */
    private static int[] hashfunction(int[] z, int x)
    {
        int[] erg = new int[6];
        int[] baderg = null;
        for(int i = 0; i<z.length; i++)
        {
            erg[i] = (z[i]*1000/x)%6;
        }
        
        if(containsvalue(erg,0) == true && containsvalue(erg,1) == true &&containsvalue(erg,2) == true && containsvalue(erg,3) == true && containsvalue(erg,4) == true &&
        containsvalue(erg,5) == true)
        {
            return erg;
        }
        else
            return baderg;
    }
    /**
     * testet Hashfunktionsmethode mit verschiedenen Primzahlen und druckt das Array, falls die Hashfunktion perfekt ist
     * @param z Zahlen im Array, für welche Hashfunktion gefunden werden soll
     */
    private static void test(int[] a)
    {
        int[] primes = primes().clone();
        for(int i = 1; i < primes.length; i++)
        {
            if(hashfunction(a,primes[i]) != null)
            {
                System.out.println("\nFuer z=" + primes[i]+ " " + Arrays.toString(hashfunction(a,primes[i])));
            }

        }
    }
    /**
     * Methode, welches ein Primzahlenarray erzeugt
     * @return erg Primzahlenarray
     */
    private static int[] primes()
    {
        int[] erg = new int[1000];
        int z = 2;
        int counter = 0;
        boolean test = false;
        while(counter < 1000)
        {
            for(int i = 2; i < z/2;i++)
            {
                if(z%i == 0 & i!=z)
                {
                    test = true;
                }
            }
            if(test == false)
            {
                erg[counter++] = z;
                z++;
            }
            else
            {
                z++;
            }
            test = false;
        }
        return erg;
    }
    /**
     * Methode, die testet, ob eine Zahl v im Array a enthalten ist
     * @param a Array
     * @param v gesuchte Zahl
     * @return gibt einen Wahrheitswert zurueck
     */
    private static boolean containsvalue(int[] a,int v)
    {
        for(int i = 0; i<a.length;i++)
        {
            if(a[i] == v)
            {
                return true;
            }
        }
        return false;
    }
}
