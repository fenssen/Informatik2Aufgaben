public class DListNode<E>
{
    //Attribute
    private E elem = null;
    private DListNode<E> next = null;
    private DListNode<E> prev = null;

    //**************************************************************************
    // Konstruktoren
    //**************************************************************************
    /**
     * Default-Konstruktor
     */
    public DListNode(){}

    /**
     * Konstruktor, wobei dem Knoten das Element uebergeben wird
     */
    public DListNode(E element)
    {
        this.elem = element;
    }

    /**
     * Konstruktor, wobei dem Knoten das Element, der Nachfolger und Vorgaenger uebergeben wird.
     */
    public DListNode(E element,DListNode<E> next,  DListNode<E> prev) //Benedikt fragen <E>
    { 
        this.elem = element;
        this.next = next;
        this.prev = prev;
    }

    //Dienste
    /**
     * Setzt naechsten Knoten
     *
     * @param next Naechster Knoten
     */
    public void setNextNode(DListNode<E> next) {
        this.next = next;
    }

    /**
     * Setzt vorherigen Knoten
     *
     * @param prev Vorheriger Knoten
     */
    public void setPrevNode(DListNode<E> prev) {
        this.prev = prev;
    }

    /**
     * Gibt naechsten Knoten zurueck
     *
     * @return Naechsten Knoten
     */
    public DListNode<E> getNextNode() {
        return next;
    }

    /**
     * Gibt vorherigen Knoten zurueck
     *
     * @return Vorheriger Knoten
     */
    public DListNode<E> getPrevNode() {
        return prev;
    }

    /**
     * Setzt den Wert eines Knotens
     * @param element Wert des Knotens
     */
    public void setData(E element) {
        this.elem = element;
    }

    /**
     * Gibt den Wert eines Knotens zurueck
     *
     * @return Wert des Knotens
     */
    public E getData() {
        return elem;
    }
}
