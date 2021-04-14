public interface BinarySearchTreeADT {

	/*
	 * Returns the root of the binary search tree
	 */
	public BinaryNode getRoot();

	/*
	 * Returns the Pixel object storing the given key, if the key is stored in
	 * the tree. Returns null otherwise.
	 */
	public Pixel get(BinaryNode r, Location key);

	/*
	 * Inserts the given data in the tree if no data item with the same key is
	 * already there. If a node already stores the same key, the algorithm
	 * throws a DuplicatedKeyException.
	 */
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException;

	/*
	 * Removes the data item with the given key, if the key is stored in the
	 * tree. Throws an InexistentKeyException otherwise.
	 */
	public void remove(BinaryNode r, Location key) throws InexistentKeyException;

	/*
	 * Returns the Pixel with the smallest key larger than the given one (note
	 * that the tree does not need to store a node with the given key). Returns
	 * null if the given key has no successor.
	 */
	public Pixel successor(BinaryNode r, Location key);

	/*
	 * Returns the Pixel with the largest key smaller than the given one (note
	 * that the tree does not need to store a node with the given key). Returns
	 * null if the given key has no predecessor.
	 */
	public Pixel predecessor(BinaryNode r, Location key);

	/*
	 * Returns the Pixel with the smallest key. Throws an EmptyTreeException if
	 * the tree is empty.
	 */
	public Pixel smallest(BinaryNode r) throws EmptyTreeException;

	/*
	 * Returns the Pixel with the largest key. Throws an EmptyTreeException if
	 * the tree is empty.
	 */
	public Pixel largest(BinaryNode r) throws EmptyTreeException;
}
