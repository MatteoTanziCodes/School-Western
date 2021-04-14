import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Comparator {
	private static int diffs, diffc;
	
	public int compare(String theInputTestFile) throws IOException {
		Lexicon lexicon = new Lexicon();
		lexicon.processLexicon(theInputTestFile);
		
		System.out.println(System.getProperty("user.dir"));

		File studentOutput = new File(System.getProperty("user.dir") + "/output_" + theInputTestFile);
		File correctOutput = new File(System.getProperty("user.dir") + "/correct_output_" + theInputTestFile);

		boolean areFilesIdentical = true;

	    if (!studentOutput.exists()) {
	      System.out.println("Student file does not exist");
	      return -1;
	    }
	    
	    if (!correctOutput.exists()) {
		      System.out.println("Correct file does not exist");
		      return -1;
		    }

	    System.out.println("======================================");
	    if (studentOutput.length() != correctOutput.length()) {
	      System.out.println("Incorrect length of output file.");
	      System.out.println("          Correct length of output file is " + correctOutput.length());
	      System.out.println("          Length of output produced by your program is " + studentOutput.length());
	      return -1;
	    }
	    else System.out.println("Correct length of output file");

	    int pos = 1;
	    try {
	      FileInputStream fis1 = new FileInputStream(studentOutput);
	      FileInputStream fis2 = new FileInputStream(correctOutput);

	      int i1 = fis1.read();
	      int i2 = fis2.read();
	      while (i1 != -1) {
	        if (i1 != i2) {
	          areFilesIdentical = false;
	          diffs = i1;
	          diffc = i2;
	          break;
	        }
	        i1 = fis1.read();
	        i2 = fis2.read();
	        ++pos;
	      }
	      fis1.close();
	      fis2.close();
	    } catch (IOException e) {
	      System.out.println("IO exception");
	      areFilesIdentical = false;
	    }
	   
	    if (areFilesIdentical) return 0;
	    else return pos;
	}

	public static void main(String[] args) throws IOException {
		Comparator comparator = new Comparator();
		
		// if you run the tests many times does not append the results in the output file
		File theOutputFile = new File("output_"+args[0]);
		if (theOutputFile.exists()){
		     theOutputFile.delete();
		 }  
		
		// call the comparator
		int res = comparator.compare(args[0]);
		if (res == 0)
			System.out.println("Correct output");
		else if (res > 0) {
			System.out.println("Incorrect output");
			System.out.println("Your output file and the correct output file differ in byte number "+res);
			System.out.println("Your output file contains \'"+(char)diffs+"\' while the correct output file contains \'"+(char)diffc+"\'");
		}

	}

}
