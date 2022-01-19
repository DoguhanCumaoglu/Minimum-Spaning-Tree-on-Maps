
//-----------------------------------------------------
//Title: MinPQ class
//Author:Doruk Arslan
//Description: MinPQ class to create a  Minimum priority queue with intended objects
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key> {
	private Key[] pq;
	private int n;
	private Comparator<Key> comparator;

	// Summary: Constructor of the class
	// Precondition: Arrays are not initialized yet
	// Postcondition: Variables are initialized, capacity of the queue is defined.
	public MinPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}

	// Default constructor.
	public MinPQ() {
		this(1);
	}

	// Constructor with comparator and initial capacity.
	public MinPQ(int initCapacity, Comparator<Key> comparator) {
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}

	public MinPQ(Comparator<Key> comparator) {
		this(1, comparator);
	}

	// Constructor to create a priority queue with given key array.
	public MinPQ(Key[] keys) {
		n = keys.length;
		pq = (Key[]) new Object[keys.length + 1];
		for (int i = 0; i < n; i++)
			pq[i + 1] = keys[i];
		for (int k = n / 2; k >= 1; k--)
			sink(k);
		assert isMinHeap();
	}

	// Summary: It checks whether the queue is empty or not
	// Postcondition: It's check that whether the element size is equal to 0
	public boolean isEmpty() {
		return n == 0;
	}

	// To return the size of the array;
	public int size() {
		return n;
	}

	// Summary: Returns the minimum element in PQ
	// Precondition: Minimum Element is in the PQ.
	// Postcondition: Element with minimum priority returned.
	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	// resize the underlying array to have the given capacity
	private void resize(int capacity) {
		assert capacity > n;
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	// Summary: It inserts the given element.
	// Precondition: Given key is not added yet
	// Postcondition: Given key is added to PQ
	public void insert(Key x) {
		// double size of array if necessary
		if (n == pq.length - 1)
			resize(2 * pq.length);

		pq[++n] = x;
		swim(n);
		assert isMinHeap();
	}

	// Summary: Deletes the minimum element in PQ
	// Precondition: Minimum Element is in the PQ.
	// Postcondition: Element with minimum priority removed and returned.
	public Key delMin() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		Key min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = null; // to avoid loitering and help with garbage collection
		if ((n > 0) && (n == (pq.length - 1) / 4))
			resize(pq.length / 2);
		assert isMinHeap();
		return min;
	}

	// To perform swim operation
	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	// To perform sink operation
	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	// To check whether the i > j
	private boolean greater(int i, int j) {
		if (comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
		} else {
			return comparator.compare(pq[i], pq[j]) > 0;
		}
	}
	// To exchange given two elements

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// is pq[1..n] a min heap?
	private boolean isMinHeap() {
		for (int i = 1; i <= n; i++) {
			if (pq[i] == null)
				return false;
		}
		for (int i = n + 1; i < pq.length; i++) {
			if (pq[i] != null)
				return false;
		}
		if (pq[0] != null)
			return false;
		return isMinHeapOrdered(1);
	}

	// is subtree of pq[1..n] rooted at k a min heap?
	private boolean isMinHeapOrdered(int k) {
		if (k > n)
			return true;
		int left = 2 * k;
		int right = 2 * k + 1;
		if (left <= n && greater(k, left))
			return false;
		if (right <= n && greater(k, right))
			return false;
		return isMinHeapOrdered(left) && isMinHeapOrdered(right);
	}

	@Override
	public Iterator<Key> iterator() {
		return null;
	}
}
