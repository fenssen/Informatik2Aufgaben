public class TestDList
{
    /**
     * Mainmethode, die eine Liste erstellt, Elemente loescht und welche ausliesst_ Aufgabe d)
     */
    public static void main(String[] args)
    {
        DoublyLinkedList<Long> meineListe = new DoublyLinkedList();
        DoublyLinkedList<Long> secondList = new DoublyLinkedList();
        
        for(long i=1; i<=5;i++){
            meineListe.insertfront(i);
        }
        
        meineListe.insertback(0L);
        meineListe.insertback(-1L);
        meineListe.printDList();
        System.out.println("\nletztes/erstes Element wird geloescht: ");
        meineListe.deletefront();
        meineListe.deleteback();
        meineListe.printDList();
        System.out.println("\nFrontelement ist: " + meineListe.getfront() 
        + "\nBackelement ist: " + meineListe.getback());

        System.out.println("in 4 Sekunden wird auf einer leeren Liste versucht,\ndas letzte Element zu loeschen");
        //Pause fuer 4s
        try {
            Thread.sleep(4000);
        } catch(InterruptedException e) {}
        secondList.deleteback();
    }
}