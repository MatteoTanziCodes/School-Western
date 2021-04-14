import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

public class TestBinSearchTree {

	private static int[] readTests(String[] args, int numTests) {
		int[] tests = new int[numTests];
		int value;
		
		if (args.length == 0) 
			for (int i = 0; i < numTests; ++i) tests[i] = i + 1;
		else 
			for (int i = 0; i < args.length; ++i) {
				value = Integer.parseInt(args[i]);
				if (value >= 1 && value <= numTests)
					tests[value-1] = value;
				else System.out.println("ERROR: Test "+value+ "does not exist");
			}
		return tests;
	}
	
	
	public static void main(String[] args) {
		boolean testPassed;
		String s;
		int[] tests = readTests(args,5);
		BinSearchTree tree = new BinSearchTree();
		BinSearchTreeNode node;

		// Test 1: empty tree and tree with only one node
		// --------------------------------
		testPassed = true;
		if (tests[0] == 1) {
		try {
			node = tree.getWord("tree");
			if (node != null) testPassed = false;
			tree.insertWord("tree","file1",1);
			node = tree.getWord("tree");
			if (node == null) testPassed = false;
			node = tree.getWord("node");
			if (node != null) testPassed = false;
			if (testPassed) System.out.println("Test 1 passed");
			else System.out.println("Test 1 failed");
		} catch (Exception e) {
			System.out.println("Test 1 failed");
		}
		}

		// Test 2: insert same word several times
		// -----------------------------------
		testPassed = true;
		tree = new BinSearchTree();
		if (tests[1] == 2) {
		for (int i = 0; i < 10; ++i)
			tree.insertWord("tree","file"+i,i);
		try {
			node = tree.getWord("trees");
			if (node != null) testPassed = false;
			node = tree.getWord("tree");
			if (node == null) testPassed = false;
			else {
				LinkedList files = node.getFiles();
				Iterator<FileNode> iter = files.iterator();
				FileNode f;
				ArrayList<Integer> pos;
				int j = 0;
				while (iter.hasNext()) {
					f = iter.next();
					pos = f.getPositions();
					++j;
					if (pos.size() != 1) testPassed = false;
				}
				if (j != 10) testPassed = false;
			}
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 2 passed");
		else
			System.out.println("Test 2 failed");
		}

		// Test 3: insert words in lexicographic order
		// ---------------------------
		if (tests[2] == 3) {
		tree = new BinSearchTree();
		testPassed = true;
		try {
			for (int i = 0; i < 10; ++i)
				tree.insertWord("word"+i,"file 1",i);
			tree.insertWord("word9","file 2",1);
			for (int i = 0; i < 10; ++i) 
				if(tree.getWord("word"+i) == null) testPassed = false;
			node = tree.getWord("word9");
			LinkedList files = node.getFiles();
			FileNode f = files.getHead();
			if ((f.getNext() == null) || (f.getNext().getNext() != null))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 3 passed");
		else
			System.out.println("Test 3 failed");
		}

		// Test 4: add large number of random words and search for them
		// ----------------------
		if (tests[3] == 4) {
		tree = new BinSearchTree();
		testPassed = true;
		try {
			int[] list = new int[1000];
			Random rand = new Random(1);
			for (int i = 0; i < 1000; ++i) {
				list[i] = rand.nextInt();
				tree.insertWord("word"+list[i],"file 1",i);
			}
				
			for (int i = 0; i < 1000; ++i)
				if (tree.getWord("word"+list[i]) == null) testPassed = false;
			if (tree.getWord("word") != null) testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 4 passed");
		else
			System.out.println("Test 4 failed");
		}

	}

}
