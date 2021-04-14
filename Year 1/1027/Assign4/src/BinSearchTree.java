
public class BinSearchTree {

	private BinSearchTreeNode root; //Root of binary search tree

	/**
	 * 
	 * @param searchWord
	 * @return - returns the node where it was found
	 */
	public BinSearchTreeNode getWord(String searchWord) {
		
		BinSearchTreeNode nodeL = search(this.root, searchWord);
		
		return nodeL; 
		
	}
	/**
	 * 
	 * @param r
	 * @param searchWord
	 * @return - searches for the word
	 */
	private BinSearchTreeNode search(BinSearchTreeNode r, String searchWord) {
		
		if (r == null) 
			return null;
		else{
			if (r.getWord().equals(searchWord)) {
				return r;
			} else if (r.getWord().compareTo(searchWord) > 0 ) {
				BinSearchTreeNode left = search(r.getLeft(), searchWord);
				return left;
			} else {
				BinSearchTreeNode right = search(r.getRight(), searchWord);
				return right;
			}
		
		}	
		
		
	}

	/**
	 * 
	 * @param theWord
	 * @param theFileName
	 * @param thePosition - inserts the word in the file
	 */
	public void insertWord(String theWord, String theFileName, int thePosition) {
		
		BinSearchTreeNode newNode = getWord(theWord);
		
		if (newNode != null) {
			
		LinkedList newFileNode = newNode.getFiles();
		
		newFileNode.insertWord(theFileName, thePosition);
		}
		else {
		
		insert(this.root, theWord, theFileName, thePosition);

		}
		
	}

	/**
	 * 
	 * @param r
	 * @param theWord
	 * @param theFileName
	 * @param thePosition
	 * insert algorithm
	 */
	private void insert(BinSearchTreeNode r, String theWord, String theFileName, int thePosition) {
	BinSearchTreeNode newNode = new BinSearchTreeNode(theWord, theFileName, thePosition);
			
		if (r == null) {
			 this.root = newNode;
		}
		else if(theWord.compareTo(r.getWord()) < 0 ) {
			if (r.getLeft() == null) {
				r.setLeft(newNode);
			}
			else {
				insert(r.getLeft(),theWord, theFileName, thePosition);
			}
		}
		else if(r.getRight() == null) {
			r.setRight(newNode);
		}
		else {
			insert(r.getRight(), theWord, theFileName, thePosition);
		}
		
	}
	
}
