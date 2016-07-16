/**
 * Java-Interface, welches eine double-ended queue fuer beliebige Typen beschreibt.
 */
public interface DeQueue<E>
{
    /**
     * Fuegt ein Element vorne ein
     */
    public void insertfront(E elem);

    /**
     * Fuegt ein Element hinten ein
     */
    public void insertback(E elem);

    /**
     * Das Element, welches vorne steht, wird geloescht
     */
    public void deletefront();

    /**
     * das Element, welches hinten steht wird geloescht
     */
    public void deleteback();

    /**
     * Frontelement wird zurueckgegeben
     */
    public E getfront();

    /**
     * Letztes Element wird zurueckgegeben
     */
    public E getback();
}
