public class TestDLL {

	public static void main(String[] args) {
		DLList<String> dlinkedList = new DLList<String>();
		boolean testPassed;
		String s;

		// Test 1: size, isEmpty, getSmallest
		// --------------------------------
		testPassed = true;
		try {
			if (!dlinkedList.isEmpty())
				testPassed = false;
			if (dlinkedList.size() != 0)
				testPassed = false;
			s = dlinkedList.getSmallest();
			System.out.println("Test 1 failed");
		} catch (EmptyListException e) {
			if (testPassed)
				System.out.println("Test 1 passed");
			else
				System.out.println("Test 1 failed");
		} catch (Exception e) {
			System.out.println("Test 1 failed");
		}

		// Test 2: insert, getSmallest, updateMin.
		// -----------------------------------
		testPassed = true;
		dlinkedList.insert("data1", 1);
		try {
			dlinkedList.changeValue("data1", 2);
			s = dlinkedList.getSmallest(); 
			if (!s.equals("data1"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		try {
			dlinkedList.changeValue("data0", 1);
			testPassed = false;
		} catch (InvalidDataItemException e) {
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 2 passed");
		else
			System.out.println("Test 2 failed");

		// Test 3: insert, getSmallest, size, dequeue
		// ---------------------------
		dlinkedList = new DLList<String>();
		testPassed = true;
		try {
			for (int i = 1000; i > 700; --i)
				dlinkedList.insert("data" + i, i);
			for (int i = 400; i <= 700; ++i)
				dlinkedList.insert("data" + i, i);
			for (int i = 399; i > 0; --i)
				dlinkedList.insert("data" + i, i);

			for (int i = 1; i <= 20; ++i) {
				s = dlinkedList.getSmallest();
				if (!s.equals("data" + i))
					testPassed = false;
			}

			if (dlinkedList.size() != 980)
				testPassed = false;
			
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 3 passed");
		else
			System.out.println("Test 3 failed");

		// Test 4: insert, changeValue
		// ----------------------
		dlinkedList = new DLList<String>();
		testPassed = true;
		try {
			for (int i = 1000; i > 700; --i)
				dlinkedList.insert("data" + i, i);
			for (int i = 400; i <= 700; ++i)
				dlinkedList.insert("data" + i, i);
			for (int i = 399; i > 0; --i)
				dlinkedList.insert("data" + i, i);

			for (int i = 1; i <= 20; ++i) {
				dlinkedList.changeValue("data" + i, 100 + i);
			}

			s = dlinkedList.getSmallest();
			if (!s.equals("data21"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		try {
			dlinkedList.changeValue("data0", 0);
			testPassed = false;
		} catch (InvalidDataItemException e) {
		}

		if (testPassed)
			System.out.println("Test 4 passed");
		else
			System.out.println("Test 4 failed");

		// Test 5: changeValue, toString
		// ----------------
		testPassed = true;
		
		try {
			dlinkedList.changeValue("data0", 0);
			testPassed = false;
		} catch (InvalidDataItemException e) {
		}

		try {
			s = dlinkedList.toString();
			for (int i = 1000; i > 0; --i)
				if (i != 209 && !s.contains("data" + i))
					testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 5 passed");
		else
			System.out.println("Test 5 failed");
	}

}