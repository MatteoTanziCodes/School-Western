import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * InputFileReader is used to read all input files and add all words to the index.
 * It is assumed that all files are in a folder called "input", which is located 
 * in the working directory.
 * 
 *
 */
public class InputFileReader {

	/**
	 * Reads all files in the input folder and adds all of the words to the hash table
	 * 
	 * @param wordHashTable	The hash table serving as the inverted positional index.
	 */
	public void readAllFiles(HashTable wordHashTable) {
		final File folder = new File(System.getProperty("user.dir") + "/input/");

		BufferedReader br;
		try {
			for (final File fileEntry : folder.listFiles()) {
				int position = 0;

				try {
					br = new BufferedReader(new FileReader(fileEntry));

					String line;
					while ((line = br.readLine()) != null) {
						// The following takes off the special characters
						// String[] words = line.split("[\\p{Punct}\\s]+");

						// The following just splits on white space
						String[] words = line.split("[\\s]+");
						for (String word : words) {
							position++;
							wordHashTable.addWord(word, fileEntry.getName(), position);
						}
					}
				} catch (IOException e) {
					System.out.println("Error opening file "+fileEntry);
				}
			}
		}
		catch (NullPointerException e) {
			System.out.println("Input files must be placed in a folder called \'input\' inside the project folder");
			System.exit(0);
		}
	}
}
