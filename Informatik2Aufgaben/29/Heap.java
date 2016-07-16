/**
 * The Heap class is the implementation of a priority queue. It structures the
 * data in a binary tree. By default, this heap is a max-heap, that means that
 * the greatest element is on top of each tree.
 *
 * @author firstlady
 * @version 1.0
 */
public class Heap<E> {
	// The heap
	private final E[] h;
	// The ordering
	private Ordering<? super E> comp;
	// Number of elements the heap contains
	private int n = 0;

	/**
	 * Creates a new heap with the specified size that orders its elements
	 * according to the specified ordering.
	 *
	 * @param  size The size of this heap
	 * @param  comp The ordering that will be used to order this heap
	 * @throws IllegalArgumentException When the size is less three
	 */
	@SuppressWarnings("unchecked")
	public Heap(int size, Ordering<? super E> comp) {
		if (size < 3)
			throw new IllegalArgumentException("Heap size must be at least three!");

		this.h = (E[]) new Object[size+1];
		this.setOrdering(comp);
	}

	/**
	 * Creates a new heap containing the elements in the specified array that
	 * orders its elements according to the specified ordering.
	 *
	 * @param  obj The array whose elements to be placed into this heap
	 * @param  comp The ordering that will be used to order this heap
	 */
	@SuppressWarnings("unchecked")
	public Heap(E[] obj, Ordering<? super E> comp) {
		if (obj == null)
			throw new NullPointerException("Argument 'obj' is null!");

		this.h = (E[]) new Object[obj.length+1];
		this.setOrdering(comp);
		for (E e: obj)
			this.insert(e);
		this.setOrdering(comp);
	}

	/**
	 * Adds the specified element and inserts it at the right position in this
	 * heap according to the specified ordering.
	 *
	 * @param  e The Element to be inserted
	 * @throws IllegalStateException When this heap is full
	 */
	public void insert(E e) {
		if (this.isFull())
			throw new IllegalStateException("Heap is full!");

		n++;
		h[n] = e;
		for (int i = n/2; i > 0 && comp.compare(h[i], h[2*i]) < 0; i = i/2) {
			this.swap(i, 2*i);
		}
	}

	/**
	 * Returns wether this heap is empty or not.
	 *
	 * @return <code>true</code> when this heap is empty, <code>false</code>
	 *		   otherwise
	 */
	public boolean isEmpty() {
		return (n == 0);
	}

	/**
	 * Returns wether this heap is full or not.
	 *
	 * @return <code>true</code> when this heap is full, <code>false</code>
	 *		   otherwise
	 */
	public boolean isFull() {
		return (n == h.length-1);
	}


	/**
	 * Returns the array representation of this heap.
	 *
	 * @return The array representation of this heap
	 */
	@SuppressWarnings("unchecked")
	public E[] getHeap() {
		E[] res = (E[]) new Object[n];
		for (int cnt = 0; cnt < n; cnt++)
			res[cnt] = h[cnt+1];
		return res;
	}

	/**
	 * Returns the ordering used to order this heap.
	 *
	 * @return The used ordering
	 */
	public Ordering<? super E> getOrdering() {
		return this.comp;
	}

	/**
	 * Retrieves and removes the head of this heap, or returns null if this heap
	 * is empty.
	 *
	 * @return The head of this heap, or null if this heap is empty
	 */
	public E poll() {
		E tmp = h[1];
		this.remove();
		return tmp;
	}

	/**
	 * Removes the head of this heap.
	 */
	public void remove() {
		h[1] = h[n];
		h[n] = null;
		n--;
		restore(1, n);
	}

	/**
	 * Sets the ordering to the specified ordering, that the the will be used to
	 * order the elements of this heap.
	 *
	 * @param  comp The ordering used to order the elements of this heap
	 */
	public void setOrdering(Ordering<? super E> comp) {
		if (comp == null)
			throw new NullPointerException("Argument 'comp' is null!");

		this.comp = comp;
		for (int i = n/2; i > 0; i--)
			restore(i, n);
	}

	/**
	 * Sorts this heap with the heapsort sorthing algorithm. The order of the
	 * sorted heap is according to the specified ordering.
	 */
	public void sort() {
		for (int i = n; i > 1; i--) {
			this.swap(i, 1);
			restore(1, i-1);
		}
	}

	// Restores the binary tree, which root is specified by the index l
	private void restore(int l, int r) {
		int j,
			i = l;
		while (i <= r/2) {
			if ((2*i+1 < r) && comp.compare(h[2*i], h[2*i+1]) < 0)
				j = 2*i+1;
			else
				j = 2*i;
			if (comp.compare(h[i], h[j]) < 0) {
				this.swap(i, j);
				i = j;
			} else
				break;
		}
	}

	// Swaps two elements, which are specified by the indices i and j
	private void swap(int i, int j) {
		E tmp = h[i];
		h[i] = h[j];
		h[j] = tmp;
	}
}
