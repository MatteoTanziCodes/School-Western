import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Searcher {

	private HashTable table; // hashtable
	private String inputFile; // the input file from run configurations
	
	/**
	 * 
	 * @param t
	 * @param testFile
	 */
	public Searcher(HashTable t, String testFile){
		
		this.table = t;
		inputFile = testFile;
				
	}
	/**
	 * 
	 * @throws IOException - finds all the words in the file
	 */
	public void findAllWords() throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(inputFile)); 
		String line = in.readLine();
		
		String [] words = line.split("\\s+");
		
		for(int i = 0; i < words.length; i++) {
			findWord(words[i]);
		}
		
	}
	/**
	 * 
	 * @param searchWord - searches for the search word in the files
	 */
	public void findWord(String searchWord) {
		
		int j = this.table.computeIndex(searchWord);
		
		BinSearchTree [] tree = this.table.getTable();
		
		BinSearchTreeNode newNode = tree[j].getWord(searchWord);
		
		if (newNode == null) {
			
			CustomPrinter.wordNotFound(searchWord,  inputFile);
			
		}
		else {
			
			CustomPrinter.wordFound(searchWord, inputFile);
			LinkedList Lp = newNode.getFiles();
			FileNode current = Lp.getHead();
			while(current != null) {
				ArrayList lq = current.getPositions();
				CustomPrinter.printPositionsPerFileFound(current.getFilename(), lq, inputFile);
				current = current.getNext();
			}
			
		}
		
		
	}
	
	
}
