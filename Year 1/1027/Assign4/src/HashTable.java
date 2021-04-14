
/**
 * HashTable represents the hash table used to add the words in the first place.
 * It serves as an inverted positional index.
 * Every word is mapped, through its hash value, to a specific position in the table.
 * Every position in the table contains a binary search tree for every word that is 
 * mapped to this place.
 * 
 *
 */
public class HashTable {
	private BinSearchTree[] table = new BinSearchTree[1031];
	
	/**
	 * Adds a word to the hash table
	 * 
	 * @param word		The word to be added
	 * @param filename	The name of the file in which the word was found
	 * @param position	The word's position in the file 
	 */
	public void addWord(String word, String filename, int position) {
		int positionInTable = computeIndex(word);
		
		BinSearchTree tree;
		if (table[positionInTable] == null) {
			// If the position computed has never been used before,
			// create a new binary search tree
			tree = new BinSearchTree();
			table[positionInTable] = tree;
		} else {
			tree = table[positionInTable];
		}
		
		// Insert the word in the corresponding binary search tree
		tree.insertWord(word, filename, position);
	} 

	/**
	 * Computes the hash value of a word
	 * 
	 * @param word 	The word to be added
	 * @return		the word's hash value
	 */
	public int computeIndex(String word) {
		char[] characters = word.toCharArray();
		int B = 8;
		int W = 1031;
		int n = characters.length;
		int sum = 0;
		
		for (int i = 0; i < n; i++) {
			sum = sum + (int)Math.pow(B, n-i+1) * (int)characters[i];	
			sum = sum % W;
		}
		sum = Math.abs(sum);
		int result = sum;
		//System.out.println("The hash for " + word + " is " + result + "\n");
		
		return result;
	}

	/**
	 * Returns the hash table
	 * 
	 * @return	the hash table containing all of the words
	 */
	public BinSearchTree[] getTable() {
		return table;
	}
	
}
