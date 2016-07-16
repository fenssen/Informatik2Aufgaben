/**
 * Zeigt an, dass ein Puffer voll ist.
 */
public class PufferFullException extends RuntimeException
{
	/**
	 * Erzeugt eine einfache PufferFullException
	 */
    public PufferFullException() {}
    
    /**
     * Initialisiert die PufferFullException mit der uebergebenen Nachricht.
     *
     * @param message Die Nachricht
     */
    public PufferFullException(String message) {
        super(message);
    }
}
