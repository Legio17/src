package client.network.utilities;

public class LinkedList<T> implements ListADT<T> {

	private static final long serialVersionUID = 1L;
	private int count;
	private LinearNode<T> front;
	private LinearNode<T> rear;

	public LinkedList() {
		count = 0;
	}

	@Override
	public String toString() {
		String temp = "{";
		for (int i = 0; i < count; i++) {
			if (i < count - 1) {
				temp += getNode(i).getElement() + ", ";
			} else {
				temp += getNode(i).getElement();
			}
		}
		temp += "}";
		return temp;
	}

	private LinearNode<T> getNode(int index) {
		LinearNode<T> current = front;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
	}

	public void add(int index, T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("index does not exist");
		}
		if (count == 0) {
			front = newNode;
			rear = front;
		}

		if (index == 0) {
			newNode.setNext(front);
			front = newNode;
		} else if ((index == count - 1)) {
			LinearNode<T> previous = getNode(index - 1);
			previous.setNext(newNode);
			newNode.setNext(rear);
		}

		else if (index == count) {
			rear.setNext(newNode);
			rear = newNode;
		}

		else {
			LinearNode<T> previous = getNode(index - 1);
			newNode.setNext(previous.getNext());
			previous.setNext(newNode);
		}

		count++;
	}

	public void add(T element) {

		if (count == 0) {
			front = new LinearNode<T>(element);
			rear = new LinearNode<T>(element);
		}

		else if (count == 1) {
			LinearNode<T> temp = new LinearNode<T>(element);
			front.setNext(temp);
			rear = temp;
		}

		else {
			LinearNode<T> temp = new LinearNode<T>(element);
			rear.setNext(temp);
			rear = temp;
		}

		count++;

	}

	public void set(int index, T element) {
		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException();
		}

		getNode(index).setElement(element);

	}

	public T get(int index) {
		if(count == 0) throw new IllegalStateException();

		if (index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("index does not exist");
		}

		return getNode(index).getElement();
	}

	public T remove(int index) {
		T temp = null;
		if (count==0) throw new IllegalStateException();
		if (count == 0 || index < 0 || index >= count) {
			throw new IndexOutOfBoundsException("empty list");
		} else if (index == 0) {
			temp=get(index);
			front = getNode(index + 1);
			count--;

		}

		else {
			temp = get(index);

			LinearNode<T> previous = getNode(index - 1);
			LinearNode<T> after = getNode(index + 1);
			previous.setNext(after);
			count--;

		}

		return temp;
	}

	public T remove(T element) {
		if (count == 0) {
			throw new IndexOutOfBoundsException("empty list");
		}

		if (!(contains(element))) {
			throw new IllegalStateException();
		}
		T removed = null;

		for (int i = 0; i < count; i++) {

			if (getNode(i).getElement().equals(element)
					|| getNode(i).getElement() == null) {
				if (i == 0) {
					removed = getNode(i).getElement();
					front = getNode(i + 1);
					count--;
					break;

				}

				if (i == count - 1) {
					removed = getNode(i).getElement();
					rear = getNode(i - 1);
					count--;
					break;

				}

				removed = getNode(i).getElement();
				LinearNode<T> previous = getNode(i - 1);
				LinearNode<T> after = getNode(i + 1);
				previous.setNext(after);
				count--;
				break;

			}
		}

		return removed;
	}

	public int indexOf(T element) {
		for (int i = 0; i < count; i++) {
			if (getNode(i).getElement() != null
					&& getNode(i).getElement().equals(element)) {
				return i;
			}

			if (getNode(i).getElement() == null
					&& getNode(i).getElement() == (element)) {
				return i;
			}
		}
		return -1;
	}

	public boolean contains(T element) {
		for (int i = 0; i < count; i++) {
			if (getNode(i).getElement() != null
					&& getNode(i).getElement().equals(element)) {
				return true;
			}

			if (getNode(i).getElement() == null
					&& getNode(i).getElement() == (element)) {
				return true;
			}
		}

		return false;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int size() {

		return count;
	}

}
