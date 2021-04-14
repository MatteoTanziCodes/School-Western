
/**
 * BinTreeNode represents the nodes of the binary search tree.
 * Each node corresponds to a single word.
 * 
 *
 */
public class BinSearchTreeNode {
	
	private BinSearchTreeNode left;
	private BinSearchTreeNode right;
	private String word;
	private LinkedList files;
	
	/**
	 * Creates a new node without children.
	 * Adds the first file and position for the word corresponding to it
	 * 
	 * @param newWord	The new word to be added to the binary search tree
	 * @param filename	The name of the file in which the word was found
	 * @param position	The word's position in the file
	 */
	public BinSearchTreeNode(String newWord, String filename, int position) {
		super();
		left = null;
		right = null;
		word = newWord;
		
		files = new LinkedList();
		files.insertWord(filename, position);
	}

	/**
	 * Returns the left child of the node
	 * @return left child
	 */
	public BinSearchTreeNode getLeft() {
		return left;
	}
	
	/**
	 * Sets the left child of the node
	 * @param left child
	 */
	public void setLeft(BinSearchTreeNode left) {
		this.left = left;
	}

	/**
	 * Returns the right child of the node
	 * @return right child
	 */
	public BinSearchTreeNode getRight() {
		return right;
	}
	
	/**
	 * Sets the right child of the node
	 * @param right child
	 */
	public void setRight(BinSearchTreeNode right) {
		this.right = right;
	}

	/**
	 * Returns the word associated with the node
	 * @return associated word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Returns a linked list with all the files containing
	 * the word associated with the node
	 * 
	 * @return list of files
	 */
	public LinkedList getFiles() {
		return files;
	}
	
}
