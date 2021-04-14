/**
 * 
 * @author matteo
 *
 * @param <T>
 */
public class DLList<T> implements DLListADT<T> {

	private DLNode<T> front; //Instance Variable
	private DLNode<T> rear; //Instance Variable
	private int count; //Instance Variable

	/**
	 * 
	 */
	public DLList() {
		this.front = null;
		this.count = 0;
		this.rear = null;

	}

	/**
	 * 
	 * @param dataItem
	 * @param value
	 */
	public void insert(T dataItem, int value) {

		if (isEmpty())
			rear = front = new DLNode<T>(dataItem, value);
		else {
			DLNode<T> next = new DLNode<T>(dataItem, value);
			next.setPrevious(rear);
			rear.setNext(next);
			rear = next;
		}
		count++;

	}

	/**
	 * 
	 * @param dataItem
	 * @return int
	 */
	public int getDataValue(T dataItem) throws InvalidDataItemException {

		DLNode<T> current = checkData(dataItem);
		if (current == null)
			throw new InvalidDataItemException(((String) (dataItem)));
		else
			return current.getValue();

	}

	/**
	 * 
	 * @param dataItem
	 * @param newValue
	 */
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException {

		DLNode<T> current = checkData(dataItem);
		if (current == null)
			throw new InvalidDataItemException(((String) (dataItem)));
		else
			current.setValue(newValue);

	}

	/**
	 * 
	 * @param dataItem
	 * @return
	 */
	private DLNode<T> checkData(T dataItem) {
		DLNode<T> current = front;
		DLNode<T> result = null;
		T check;
		while (current != null) {
			check = current.getData();
			if (check.equals(dataItem)) {
				result = current;
				break;
			} else
				current = current.getNext();
		}
		return result;

	}

	/**
	 * 
	 */
	public T getSmallest() throws EmptyListException {

		if (isEmpty())
			throw new EmptyListException("List is empty");
		DLNode<T> current = front;
		DLNode<T> result = current;
		int smallest = front.getValue();
		for (int i = 0; i < size(); i++) {
			int temp = current.getValue();
			if (temp < smallest) {
				smallest = temp;
				result = current;
			}
			current = current.getNext();
		}

		DLNode<T> previous = result.getPrevious();
		DLNode<T> next = result.getNext();

		if (previous == null && next == null)
			rear = front = null;
		else if (previous == null) {
			front = next;
			front.setPrevious(null);
		} else if (next == null) {
			rear = previous;
			rear.setNext(null);
		} else {
			previous.setNext(next);
			next.setPrevious(previous);
		}

		count--;
		return result.getData();

	}

	/**
	 * 
	 * @return String
	 */
	public String toString() {
		String toString = null;
		DLNode<T> current = front;
		while (!current.equals(rear)) {
			toString += current.getData() + "," + current.getValue() + "; ";
			current = current.getNext();
		}
		toString += rear.getData() + "," + current.getValue();
		return toString;
	}

	/**
	 * 
	 * @return int
	 */
	public int size() {
		return count;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		if (size() == 0)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public DLNode<T> getfront() {
		return front;
	}

	/**
	 * 
	 * @param dataItem
	 * @return
	 */
	public T findData(T dataItem) {
		DLNode<T> current = checkData(dataItem);
		if (current == null)
			return null;
		else
			return current.getData();
	}

}