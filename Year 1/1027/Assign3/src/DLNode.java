/**
 * 
 * @author matteo
 *
 * @param <T>
 */
public class DLNode<T> {

	private T dataItem; //Instance Variable
	private DLNode<T> next; //Instance Variable
	private DLNode<T> previous; //Instance Variable
	private int value; //Instance Variable
	
	/**
	 * 
	 * @param data
	 * @param v
	 */
	public DLNode(T data, int v) {
		this.dataItem = data;
		this.value = v;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @return
	 */
	public T getData() {
		return dataItem;
	}

	/**
	 * 
	 * @return
	 */
	public DLNode<T> getNext() {
		return next;
	}

	/**
	 * 
	 * @return
	 */
	public DLNode<T> getPrevious() {
		return previous;
	}

	/**
	 * 
	 * @param newData
	 */
	public void setData(T newData) {
		this.dataItem = newData;
	}
	
	/**
	 * 
	 * @param newNext
	 */
	public void setNext(DLNode<T> newNext) {
		this.next = newNext;
	}

	/**
	 * 
	 * @param newPrevious
	 */
	public void setPrevious(DLNode<T> newPrevious) {
		this.previous = newPrevious;
	}

	/**
	 * 
	 * @param newValue
	 */
	public void setValue(int newValue) {
		this.value = newValue;
	}

}