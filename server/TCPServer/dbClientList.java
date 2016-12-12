package server.TCPServer;

import java.util.Iterator;
import java.util.NoSuchElementException;

import client.network.utilities.SetADT;

public class dbClientList<T> implements SetADT<T> {

	private T[] sets;
	private int size;
	private final int DEFAULT_CAPACITY = 1000000;

	private class ArraySetIterator implements Iterator<T> {
		private int current;

		public ArraySetIterator() {
			current = 0;
		}

		public boolean hasNext() {
			return (current < size);
		}

		public T next() {
			
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current++;
			return sets[current - 1];
		}
	}

	@SuppressWarnings("unchecked")
	public dbClientList() {
		size = 0;
		sets = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	@SuppressWarnings("unchecked")
	public dbClientList(int initialCapacity) {
		size = 0;
		sets = (T[]) (new Object[initialCapacity]);
	}

	@Override
	public Iterator<T> iterator() {
		return new ArraySetIterator();
	}

	@Override
	public void add(T element) {
		if(element==null)
		{
			throw new IllegalArgumentException("Cannot add null!");
		}
		if (!(contains(element))) {
			sets[size] = element;
			size++;
		}
	}

	@Override
	public T remove(T element) {
		int search = -1;
		for (int i = 0; i < size && search == -1; i++) {
			if (sets[i]==(element)) {
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

	public T getCon(ServerConnection name) {

		T search = null;
		for (int i = 0; i < size && search == null; i++) {
			if (sets[i].equals(name)) {
				search = (T) sets[i];
			}
		}
		return search;
	}

	@Override
	public boolean contains(T element) {

		int search = -1;
		for (int i = 0; i < size && search == -1; i++) {
			if (sets[i]==(element)) {
				search = i;
			}
		}
		return (search != -1);
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isSubset(SetADT<T> set) {

		 Iterator<T> iterator=iterator(); while(iterator.hasNext()) { T
		 element=iterator.next(); if(!set.contains(element)) return false; }
		return true;	 
	}

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

	public String toString() {
		String result = "";

		for (int index = 0; index < size; index++)
			result = result + sets[index].toString();

		return result;
	}

}
