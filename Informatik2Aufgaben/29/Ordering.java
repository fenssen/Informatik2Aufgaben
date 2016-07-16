/**
 * A total ordering on a class E of objects.
 */
public interface Ordering<E> {
	public int compare(E e1, E e2);
}
