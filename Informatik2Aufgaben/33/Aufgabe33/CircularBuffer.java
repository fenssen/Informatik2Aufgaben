import java.lang.Object;
import java.lang.reflect.Array;
import java.util.Arrays;
public class CircularBuffer<E> implements DeQueue<E>
{
    //Attribute
    private int n = 0;
    private int in = 0;
    private int out = 0;
    private E[] a;
    
    /**
     * Erzeugt einen neuen zirkulaeren Puffer
     *
     * @param  c Elementtyp
     * @param  l Laenge des Puffers
     */
    public CircularBuffer(Class<E> c, int l ) {
        @SuppressWarnings("unchecked")
        final E[] a = (E[]) Array.newInstance(c, l+1);//Eine Zelle frei lassen
        this.a = a;
    }
    
    //Dienste
    /**
     * Druckt den Puffer
     */
    public void print()
    {
        System.out.println(Arrays.toString(a));
    }
    
    /**
     * Gibt an, ob der Puffer voll ist
     *
     * @return Wahrheitswert
     */
    public boolean isfull() 
    {
        return ((in + 1) % a.length == out); //
    }
    
    /**
     * Gibt an, ob der Puffer leer ist
     *
     * @return Wahrheitswert
     */
    public boolean isempty()
    {
        return (in == out);
    }
    
    /**
     * Fuegt ein Element vorne ein
     *
     * @param elem Element, welches eingefuegt werden soll
     * @throws PufferFullException falls der Puffer voll ist
     */
    public void insertfront(E elem)  throws PufferFullException
    {
        if(isfull())
        {
            throw new PufferFullException();
        }
        else
        {
            a[in] = elem;
            in = (in + 1)%a.length;
        }
    }
    
    /**
     * Fuegt ein Element hinten ein
     * @param elem Element, welches eingefuegt werden soll
     * @throws PufferFullException Falls der Puffer voll ist
     */
    public void insertback(E elem) throws PufferFullException
    {
        if (isfull())
        {
            throw new PufferFullException(); 
        }
        else
        {
            out = (out+a.length-1) % a.length; //ein Out zurueckgehen
            a[out] = elem;
        }
    }
    
    /**
     * Loescht das Frontelement
     */
    public void deletefront()
    {
        if (isempty())
        {
            throw new NullPointerException("Array ist leer!");
        }
        else 
        {
            in = (in+a.length-1) % a.length;
            a[in] = null;
        }
    }
    
    /**
     * loescht letztes Element
     */
    public void deleteback()
    {
        if (isempty())
        {
            throw new NullPointerException("Array ist leer!");
        }
        else 
        {
            a[out] = null;
            out = (out + 1) % a.length;
        }
    }
    
    /**
     * gibt Frontelement zurueck
     * @return das Frontelement
     */
    public E getfront()
    {
        return a[(in+a.length-1) % a.length];
    }
    
    /**
     * gibt letzes Element zurueck
     * @return das letzte Element
     */
    public E getback()
    {
        return a[out];
    }
}
