public class TestStack {

	public static void main(String[] args) {
		boolean testPassed;
		Integer val = null;

		ArrayStack<Integer> s = new ArrayStack<Integer>(30);

		// Test 1. Test push and peek on a stack with few elements
		try {
			for (int i = 0; i < 11; ++i)
				s.push(Integer.valueOf(i));

			val = s.peek();
			if ((val.intValue() == 10) && (s.size() == 11))
			    testPassed = true;
			else testPassed = false;

		} catch (Exception e) {testPassed = false;}
		if (testPassed)
			System.out.println("Test 1 passed");
		else
			System.out.println("Test 1 failed");

		// Test 2. Test pop on a stack with few elements.
		try {
		    testPassed = true;
			for (int i = 0; i < 5; ++i)
				val = s.pop();
			if ((val.intValue() == 6) && !s.isEmpty()) 
			    testPassed = true;
			else testPassed = false;

		} catch (Exception e) {testPassed = false;}
		if (testPassed)
			System.out.println("Test 2 passed");
		else
			System.out.println("Test 2 failed");

		// Test 3. Pop on an empty stack
		try {
			for (int i = 0; i < 7; ++i)
				val = s.pop();
			testPassed = false;

		} catch (EmptyStackException e) {testPassed = true;} 
		catch (Exception e) {testPassed = false;}
		if (testPassed)
			System.out.println("Test 3 passed");
		else
			System.out.println("Test 3 failed");

		// Test 4. Test that size of stack is increased correctly
		s = new ArrayStack<Integer>(25);

		try {
		    testPassed = true;
		    for (int i = 0; i < 101; ++i) {
				s.push(Integer.valueOf(i));
				if ((i == 25) && (s.length() != 50)) {
					testPassed = false;
					break;
				}
				else if ((i == 50) && (s.length() != 100)) {
					testPassed = false;
					break;
				}
				else if ((i == 100) && (s.length() != 150)) {
					testPassed = false;
					break;
				}
		    }
		} catch (Exception e) {testPassed = false;}

		if (testPassed)
			System.out.println("Test 4 passed");
		else System.out.println("Test 4 failed");

		// Test 5. Test that the size of the stack is decreased correctly.
		s = new ArrayStack<Integer>(60);
		int result;
		try {
		    testPassed = true;
		    for (int i = 0; i <= 20; ++i)
				s.push(Integer.valueOf(i)); 
	
			for (int i = 20; i > 5; --i) {
				result = s.pop();
				if ((i == 19) && (s.length() != 30)) {
					System.out.println("19: "+s.length());
					testPassed = false;
					break;
				}
				else if ((i == 9) && (s.length() != 15)) {
										System.out.println("9: "+s.length());
					testPassed = false;
					break;
				}
			}
		    if (s.length() != 15) testPassed = false;
		} catch (Exception e) {testPassed = false;}

		if (!testPassed)
			System.out.println("Test 5 failed");
		else System.out.println("Test 5 passed");

		// Test 6. Test push, pop, size
		testPassed = true;
		try {
			s = new ArrayStack<Integer>();
			for (int i = 0; i < 990; ++i)
				s.push(Integer.valueOf(i));

			if (s.size() != 990)
				testPassed = false;
			for (int i = 989; i >= 0; --i) {
				val = s.pop();
				if (val.intValue() != i) {
					testPassed = false;
					break;
				}
			}

		} catch (Exception e) {testPassed = false;}
		if (testPassed)
			System.out.println("Test 6 passed");
		else
			System.out.println("Test 6 failed");

		// Test 7. Test toString
		testPassed = true;
		try {
		    s = new ArrayStack<Integer>(3);
		    for (int i = 0; i < 3; ++i)
				s.push(Integer.valueOf(i));
		    String out = s.toString();
		    if (out.equals("Stack: 0, 1, 2")) testPassed = true;
		    else testPassed = false;
		}
		catch(Exception e) {testPassed = false;}
		if (testPassed)
			System.out.println("Test 7 passed");
		else
			System.out.println("Test 7 failed");
	}

}