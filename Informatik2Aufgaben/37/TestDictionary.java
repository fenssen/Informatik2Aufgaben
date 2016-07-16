import java.util.ArrayList;
import java.util.Arrays;
public class TestDictionary
{
    public static void main(String[] args)
    {
        Dictionary<Integer> mybook = new Dictionary<>();
        int[] einfuegearray = {4,3,2,1,8,7,6,5,0,9};
        int[] loescharray = {9,0,5,6,7,8,1,2,3,4};

        for(int i = 1; i <= einfuegearray.length; i++)
        {
            mybook.insert(einfuegearray[i-1]);
            if(i<10)
            System.out.print(" " + i + " ");
            else
            System.out.print(i + " ");
            mybook.traverse();
        }
        for(int i = 1; i <= loescharray.length; i++)
        {
            mybook.delete(loescharray[i-1]);
            System.out.print(i+einfuegearray.length+ " ");
            mybook.traverse();
        }
        System.out.println("Der Baum ist leer.");
        
        //Ausgabe der Arraylisten
        System.out.println("An den Stellen " + Arrays.toString(mybook.linksrotation.toArray()) + "wurde eine Linksrotation durchgefuehrt");
        System.out.println("An den Stellen " +Arrays.toString(mybook.rechtsrotation.toArray())+ "wurde eine Rechtsrotation durchgefuehrt");
        System.out.println("An den Stellen " +Arrays.toString(mybook.doppellinksrotation.toArray())+ "wurde eine Doppellinksrotation durchgefuehrt");
        System.out.println("An den Stellen " +Arrays.toString(mybook.doppelrechtsrotation.toArray())+ "wurde eine DoppelRechtsrotation durchgefuehrt");

        //BSP Doppelrechtssrotation
        System.out.println("\nBeispiel fuer eine Doppelrechtsrotation");
        mybook.insert(7);
        mybook.traverse();
        mybook.insert(3);
        mybook.traverse();
        mybook.insert(5);
        mybook.traverse();
        mybook.delete(5);
        mybook.delete(3);
        mybook.delete(7);
         
        //BSP Doppellinksrotation
        System.out.println("\nBeispiel fuer eine Doppellinkssrotation");
        mybook.insert(1);
        mybook.traverse();
        mybook.insert(3);
        mybook.traverse();
        mybook.insert(2);
        mybook.traverse();
    }
}
