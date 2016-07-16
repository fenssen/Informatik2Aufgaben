public class TestPuffer
{
    /**
     * Hauptprogramm
     */
    public static void main(String[] args)
    {
        Integer[] ar = new Integer[1];
        Class componentType = ar.getClass().getComponentType();
        CircularBuffer<Integer> meinPuffer = new CircularBuffer<Integer>(componentType, 4);

        //Test1-fuellen
        try
        {
            meinPuffer.insertfront(1);
            meinPuffer.insertfront(2);
            meinPuffer.insertfront(3);
            meinPuffer.insertback(-1);
        }
        catch(PufferFullException e)
        {
            System.exit(1);
        }
        meinPuffer.print();

        //Test 2-loeschen
        System.out.println("\nNun wird das erste und letzte Element geloescht. In diesem Fall 3(vorne) und -1(hinten)");
        meinPuffer.deleteback();
        meinPuffer.deletefront();
        meinPuffer.print();

        //Test3- Elemente zurueckgeben
        System.out.println("\ndas Frontelement ist: " + meinPuffer.getfront() + " und das letzte Element ist: "
        + meinPuffer.getback());
        
        //Test4-error
        System.out.println("\nin 4 Sekunden wird der Puffer ueberfuellt");
        //Pause fuer 4s
        try{
            Thread.sleep(4000);
        }
        catch(InterruptedException e)
        {
        }
        meinPuffer.insertfront(1);
        meinPuffer.insertfront(1);
        meinPuffer.insertfront(1);
        meinPuffer.print();

        //Test5 es wird zu viel geloescht
        /*
        meinPuffer.deleteback();
        meinPuffer.deleteback();
        meinPuffer.deleteback();
        meinPuffer.deleteback();
        */
    }
}
