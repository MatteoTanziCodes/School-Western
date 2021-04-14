import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * CustomPrinter is used to print messages to the console and the output file.
 * All possible cases are covered.
 * It doesn't need initialization, since its methods are static
 * 
 */
public class CustomPrinter {
	/**
	 * Prints custom message when a word is not part of the lexicon.
	 * 
	 * @param word	The word that is searched
	 * @param theTestFile It is used to create the output file name as output_"theTestFile"
	 */
	static public void wordNotFound(String word, String theTestFile) {
		PrintWriter writer = null;
		String outputFile = new String("output_"+ theTestFile);

		try {
			writer = new PrintWriter(
						new FileOutputStream(
								new File(outputFile),
								true // append at the end of file
						)
					);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		writer.println(System.getProperty("line.separator") + "Word " + word + " does not exist.");
		writer.close();

		System.out.println(System.getProperty("line.separator") + "Word " + word + " does not exist.");
	}

	/**
	 * Prints custom message when a word is found in the lexicon.
	 * 
	 * @param word The word that is searched
	 * @param theTestFile It is used to create the output file name as output_"theTestFile"
	 */
	static public void wordFound(String word, String theTestFile) {
		PrintWriter writer = null;
		String outputFile = new String("output_"+ theTestFile);

		try {
			writer = new PrintWriter(
						new FileOutputStream(
								new File(outputFile),
								true // append at the end of file
						)
					);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		writer.println(System.getProperty("line.separator") + "Word to be found: " + word);
		writer.close();

		System.out.println(System.getProperty("line.separator") + "Word to be found: " + word);
	}

	/**
	 * Prints the filename and corresponding positions for a file found to contain the searched word.
	 * 
	 * @param filename	The name of the file containing the word
	 * @param positions	The list of positions of the word in the file
	 * * @param theTestFile It is used to create the output file name as output_"theTestFile"
	 */
	static public void printPositionsPerFileFound(String filename, List<Integer> positions, String theTestFile) {
		PrintWriter writer = null;

		try {
			String outputFile = new String("output_"+ theTestFile);
			writer = new PrintWriter(new FileOutputStream(
										new File(outputFile),
										true // append at the end of file
									)
					);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer toBePrinted = new StringBuffer(
				"\tFilename: " + filename + System.getProperty("line.separator") + "\t\t");
		toBePrinted.append(positions + System.getProperty("line.separator"));

		writer.print(toBePrinted.toString());
		writer.close();
		
		System.out.print(toBePrinted.toString());
	}

}
