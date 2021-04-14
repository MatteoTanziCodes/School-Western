/**
 * 
 * @author matteo
 *
 * @param <T>
 */
public class ArrayStack<T> implements ArrayStackADT<T> {

	private T[] stack;
	private int top = -1;
	private int DEFAULT_CAPACITY = 20;

	/**
	 * 
	 * Default constructor - sets stack to default capacity
	 */
	public ArrayStack() {

		top = -1;
		stack = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * 
	 * @param initialCapacity
	 * 
	 * constructor - sets the stack to a set capacity
	 */
	public ArrayStack(int initialCapacity) {

		top = -1;
		stack = (T[]) (new Object[initialCapacity]);
	}

	/**
	 * 
	 * Adds a data item to the top of the stack
	 */
	public void push(T dataItem) {
		if (size() + 1 == stack.length)
			expandCapacity();

		top++;
		stack[top] = dataItem;

	}

	/**
	 * 
	 * removes the top item in the stack
	 * @return result
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();

		T result = stack[top];
		stack[top] = null;
		top--;

		if (size() < (stack.length / 3) && (length() > 20)) {
			decreaseCapacity();
		}


		return result;
	}

	/**
	 * 
	 * @throws EmptyStackException
	 * @return stack[top]
	 * 
	 * Checks the item on the top of the stack
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();

		return stack[top];
	}

	/**
	 * 
	 * Checks if the stack is empty
	 */
	public boolean isEmpty() {
		return (top == -1);
	}

	/**
	 * 
	 * Checks the size of the stack
	 */
	public int size() {

		int count = 0;

		while (stack[count] != null) {
			count++;
		}

		return count;

	}

	/**
	 * 
	 * Sets the string output for the stack
	 */
	public String toString() {
		String result = "";

		for (int scan = 0; scan < top; scan++)
			result = result + stack[scan] + ", ";

		result = result + stack[top];

		return "Stack: " + result;

	}
	
	/**
	 * 
	 * Expands the capacity of the Arraystack
	 */
	private void expandCapacity() {
		if (stack.length < 100) {
			T[] larger = (T[]) (new Object[stack.length * 2]);

			for (int index = 0; index < stack.length; index++)
				larger[index] = stack[index];

			stack = larger;
		} else {
			T[] larger = (T[]) (new Object[stack.length + 50]);

			for (int index = 0; index < stack.length; index++)
				larger[index] = stack[index];

			stack = larger;
		}
	}

	/**
	 * 
	 * Decreases the capacity of the arraystack
	 */
	private void decreaseCapacity() {

		T[] smaller = (T[]) (new Object[stack.length / 2]);

		for (int index = 0; index < stack.length / 2; index++)
			smaller[index] = stack[index];

		stack = smaller;

	}

	/**
	 * 
	 * @returns stack.length
	 */
	public int length() {
		return stack.length;
	}
}
