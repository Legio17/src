package server.TCPServer;

import java.util.Iterator;
import java.util.NoSuchElementException;

import client.network.utilities.SetADT;

/**
 * A class containing a list of ServerConnection objects.
 * 
 * @author Adam Szekely, Peter Miodrag Varanic, Filip Hudec, Signe Rasmussen,
 *         Ana Iulia Chifor
 *
 * @param <T>
 *            the type of the value being put in the ArraySet
 */
public class dbClientList<T> implements SetADT<T> {

	private T[] sets;
	private int size;
	private final int DEFAULT_CAPACITY = 1000000;

	/**
	 * A class containing an Iterator.
	 * 
	 * @author Adam Szekely, Peter Miodrag Varanic, Filip Hudec, Signe
	 *         Rasmussen, Ana Iulia Chifor
	 *
	 */
	private class ArraySetIterator implements Iterator<T> {
		private int current;

		/**
		 * Empty constructor, initializing the current variable.
		 */
		public ArraySetIterator() {
			current = 0;
		}

		/**
		 * Returns if the iterator has a next element or not.
		 * 
		 * @return true if there is a next element in the Set, else false
		 */
		public boolean hasNext() {
			return (current < size);
		}

		/**
		 * Returns the current element of the Set and increase the current
		 * variable by one.
		 * 
		 * @return the current element of the Set
		 */
		public T next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current++;
			return sets[current - 1];
		}
	}

	/**
	 * Empty constructor initializing the size and the sets array.
	 */
	@SuppressWarnings("unchecked")
	public dbClientList() {
		size = 0;
		sets = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * Constructor initializing the size and the sets array.
	 * 
	 * @param initialCapacity
	 *            value to increase the size of the sets array
	 */
	@SuppressWarnings("unchecked")
	public dbClientList(int initialCapacity) {
		size = 0;
		sets = (T[]) (new Object[initialCapacity]);
	}

	/**
	 * Returns an Iterator.
	 * 
	 * @return an ArraySetIterator.
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArraySetIterator();
	}

	/**
	 * Adds and element to the ArraySet.
	 */
	@Override
	public void add(T element) {
		if (element == null) {
			throw new IllegalArgumentException("Cannot add null!");
		}
		if (!(contains(element))) {
			sets[size] = element;
			size++;
		}
	}

	/**
	 * Removes an element from the ArraySet.
	 */
	@Override
	public T remove(T element) {
		int search = -1;
		for (int i = 0; i < size && search == -1; i++) {
			if (sets[i] == (element)) {
				search = i;
			}
		}
		if (search == -1) {
			throw new NoSuchElementException();
		}
		T result = sets[search];
		sets[search] = sets[size - 1];
		sets[size - 1] = null;
		size--;
		return result;
	}

	/**
	 * Gets a ServerConnection from the ArraySet.
	 * 
	 * @param name
	 *            of the ServerConnection to be retrieved
	 * @return a ServerConnection from the ArraySet by name
	 */
	public T getCon(ServerConnection name) {

		T search = null;
		for (int i = 0; i < size && search == null; i++) {
			if (sets[i].equals(name)) {
				search = (T) sets[i];
			}
		}
		return search;
	}

	/**
	 * Checks if an element is contained in the ArraySet.
	 * 
	 * @return true if the element is contained in the ArraySet, else false
	 */
	@Override
	public boolean contains(T element) {

		int search = -1;
		for (int i = 0; i < size && search == -1; i++) {
			if (sets[i] == (element)) {
				search = i;
			}
		}
		return (search != -1);
	}

	/**
	 * Checks if the ArraySet is empty.
	 * 
	 * @return true if the size is 0, else false
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the size of the ArraySet.
	 * 
	 * @return the size of the ArraySet.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if an ArraySet is a subset of another ArraySet.
	 * 
	 * @param set
	 *            an ArraySet that has to be checked if it is a subset of the
	 *            ArraySet on which the method was called
	 * @return true if the ArraySet is a subset of the ArraySet on which the
	 *         method was called, else false
	 */
	@Override
	public boolean isSubset(SetADT<T> set) {

		Iterator<T> iterator = iterator();
		while (iterator.hasNext()) {
			T element = iterator.next();
			if (!set.contains(element))
				return false;
		}
		return true;
	}

	/**
	 * Returns one ArraySet of 2 sets merged together.
	 * 
	 * @param set
	 *            is the ArraySet that has to be merged together with the
	 *            ArraySet on which the method was called on
	 * @return one ArraySet of 2 merged ArraySets.
	 */
	@Override
	public SetADT<T> intersection(SetADT<T> set) {
		dbClientList<T> interS = new dbClientList<T>();

		Iterator<T> scan = set.iterator();
		T temp = null;
		while (scan.hasNext()) {
			temp = scan.next();

			if (contains(temp)) {
				interS.add(temp);
			}
		}
		return interS;
	}

	/**
	 * Returns the union of two ArraySets.
	 * 
	 * @param set
	 *            is an ArraySet which has to be compared to the ArraySet on
	 *            which the method was called on
	 * @return a new ArraySet with the matching objects from the two separate
	 *         ArraySet
	 */
	@Override
	public SetADT<T> union(SetADT<T> set) {
		dbClientList<T> both = new dbClientList<T>();

		for (int index = 0; index < size; index++)
			both.add(sets[index]);

		Iterator<T> scan = set.iterator();
		while (scan.hasNext())
			both.add(scan.next());
		return both;
	}
/**
 * Returns a String of the ArraySet.
 * @return a String of the ArraySet
 */
	public String toString() {
		String result = "";

		for (int index = 0; index < size; index++)
			result = result + sets[index].toString();

		return result;
	}

}
