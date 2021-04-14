/**
 * @author CS1027
 *
 *         Defines the interface for the doubly linked list
 */

public interface DLListADT<T> {
	/**
	 * Adds one node storing the given dataItem and value to the rear of the 
	 * doubly linked list
	 * 
	 * @param  dataItem, value to be added to the doubly linked list
	 */
	public void insert(T dataItem, int value);

	/**
	 * Removes and returns the data item stored in the linked list with smallest
	 * associated value. Throws an EmptyListException if the list is empty.
	 * 
	 * @return T data item in the list with smallest associated value
	 */
	public T getSmallest() throws EmptyListException;

	/**
	 * Changes the value associated to the given dataItem to the new value. Throws an  
	 * InvalidDataItemException if no node of the list stores the given dataItem.
	 * 
	 * @param dataItem whose value is to be changed
	 * @param newValue new value for this dataItem
	 */
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException;

	/**
	 * Returns the value of the given dataItem. Throws an InvalidDataItemException if
	 * no node of the list stores the given dataItem.
	 * 
	 * @param dataItem whose value is being sought
	 * @return value of this dataItem
	 */
	public int getDataValue(T dataItem) throws InvalidDataItemException;
	
	/**
	 * Returns true if this list is empty.
	 * 
	 * @return true if this list is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Returns the number of dataItems in this list.
	 * 
	 * @return int number of dataItems in this list
	 */
	public int size();

	/**
	 * Returns a string representation of this list.
	 * 
	 * @return String representation of this list
	 */
	public String toString();
}
