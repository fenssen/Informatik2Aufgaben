public class DoublyLinkedList<E> implements DeQueue<E>
{
    //Anfangs- und Endknoten
    DListNode<E> head = new DListNode<E>();
    DListNode<E> tail = new DListNode<E>();
    
    /**
     * Konstruktor, der eine leere DoublyLinkedList erzeugt indem er head und tail verknuepft
     */
    public DoublyLinkedList()
    {
        head.setNextNode(tail);
        tail.setPrevNode(head);
    }
    
    //Dienste
    /**
     * Druckt die Liste
     */
    public void printDList()
    {
        DListNode<E> k = head;
        System.out.print("head");
        while(k.getNextNode().getData() != null)
        {
            k = k.getNextNode();
            System.out.print(" <--> " + k.getData());

        }
        System.out.println(" <--> tail");
    }

    /**
     * Fuegt nach head ein Element hinzu
     *
     * @param elem Das Element, welches hinzugefuegt werden soll
     */
    public void insertfront(E elem)
    {
        DListNode<E> k = new DListNode<E>(elem);
        k.setPrevNode(head);
        k.setNextNode(head.getNextNode());
        head.getNextNode().setPrevNode(k);
        head.setNextNode(k);
    }

    /**
     * Fuegt vor tail ein Element hinzu
     *
     * @param elem Das Element, welches hinzugefuegt werden soll
     */
    public void insertback(E elem)
    {
        DListNode<E> k = new DListNode<E>(elem);
        k.setNextNode(tail); //naechste Knoten tail
        k.setPrevNode(tail.getPrevNode());//Knoten davor von tail
        tail.getPrevNode().setNextNode(k); //vorherige Knoten von tail auf neuen setzen
        tail.setPrevNode(k); //Tail auf neuen verweisen
    }

    /**
     * Loescht das Element nach head aus der Liste
	 *
     * @param elem Das Element, welches geloescht werden soll
     */
    public void deletefront()
    {
        try {
			head.getNextNode().getNextNode().setPrevNode(head);
			head.setNextNode(head.getNextNode().getNextNode());
        } catch (NullPointerException e) {
            System.err.println("Es existiert kein Knoten, der geloescht werden kann. Das Programm wird beendet.");
            System.exit(1);
        }
    }

    /**
     * Loescht das Element vor tail aus der Liste
     *
     * @param elem Das Element, welches geloescht werden soll
     */
    public void deleteback()
    {
        try {
			tail.getPrevNode().getPrevNode().setNextNode(tail);
			tail.setPrevNode(tail.getPrevNode().getPrevNode());
        } catch(NullPointerException e) {
            System.err.println("Es existiert kein Knoten, der geloescht werden kann. Das Programm wird beendet.");
            System.exit(1);
        }
    }

    /**
     * Gibt das erste Element der Liste zurueck
     *
     * @return Erstes Element
     */
    public E getfront()
    {
        return head.getNextNode().getData();
    }

    /**
     * Gibt das letzte Element der Liste zurueck
     * @return Letztes Element
     */
    public E getback()
    {
        return tail.getPrevNode().getData();
    }
}
