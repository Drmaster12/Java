
public class Driver {

	private final static int FIRST_NUMBER = 1;
	private final static int LAST_NUMBER = 100000;
	
	private final static int FIRST_STRING = 1;
	private final static int LAST_STRING = 100;

	private final static int NUMBER_OF_TEST_CASES = 10;
	
	private static enum QueueTypes {ArrayQueue, LinkedQueue};
	
	private static enum TestTypes {AddIntegers, RemoveIntegers, AddAndDisplayStrings, DisplayAndRemoveStrings};

	
	/**
	 * 
	 * This method is called by the createIntegerTestQueue() method to initialize the test queue. The test queue could either
	 * be an ArrayQueue or a LinkedQueue. In either case, it will implement the QueueInterface. 
	 * 
	 * @param 	testQueue	The testQueue to be initialized.
	 * @param 	firstNumber	The first number to be added to the queue.
	 * @param 	lastNumber	The last number to be added to the queue.
	 * @return	The initialized test queue.
	 * @see		createIntegerTestQueue()
	 * 
	 */
	public static QueueInterface<Integer> initializeIntegerTestQueue(QueueInterface<Integer> testQueue, int firstNumber, int lastNumber) {
		if ((firstNumber != 0) && (lastNumber != 0)) {
			for (int i = firstNumber; i <= lastNumber; i += 1) {
				testQueue.add(new Integer(i));
			}
		}
		return testQueue;
	}

	
	/**
	 * 
	 * This method is called by the runTestCase() method to obtain an Integer test queue. The test queue could either 
	 * be an ArrayQueue or a LinkedQueue. In either case, it will implement the QueueInterface.
	 * 
	 * @param 	queueType	The type of queue needed. This can either be an ArrayQueue or a LinkedQueue.
	 * @param 	firstNumber	The first number to be added to the queue.
	 * @param 	lastNumber	The last number to be added to the queue.
	 * @return	The created and initialized test queue.
	 * 
	 */
	public static QueueInterface<Integer> createIntegerTestQueue(QueueTypes queueType, int firstNumber, int lastNumber) {
	QueueInterface<Integer> testQueue = null;
		
		switch (queueType) {
		case ArrayQueue:
			ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
			testQueue = arrayQueue;
			break;
		case LinkedQueue:
			LinkedQueue<Integer> linkedQueue = new LinkedQueue<Integer>();
			testQueue = linkedQueue;
			break;
		}
		testQueue = initializeIntegerTestQueue(testQueue, firstNumber, lastNumber);
		
		return testQueue;
	}

	
	/**
	 * 
	 * This method is called by the createStringTestQueue() method to initialize the test queue. The test queue could either
	 * be an ArrayQueue or a LinkedQueue. In either case, it will implement the QueueInterface. 
	 * 
	 * @param 	testQueue	The testQueue to be initialized.
	 * @param 	lastString	The number of the last String to be added to the queue.
	 * @return	The initialized test queue.
	 * @see		createStringTestQueue()
	 * 
	 */
	public static QueueInterface<String> initializeStringTestQueue(QueueInterface<String> testQueue, int lastString) {
		if (lastString != 0) {
			for (int i = 1 ; i <= lastString ; i += 1) {
				testQueue.add(new String("String " + i));
			}
		}
		return testQueue;
	}

	
	/**
	 * 
	 * This method is called by the runTestCase() method to obtain an Integer test queue. The test queue could either 
	 * be an ArrayQueue or a LinkedQueue. In either case, it will implement the QueueInterface.
	 * 
	 * @param 	queueType	The type of queue needed. This can either be an ArrayQueue or a LinkedQueue.
	 * @param 	firstNumber	The first number to be added to the list.
	 * @param 	lastNumber	The last number to be added to the list
	 * @return	The created and initialized test queue.
	 * 
	 */
	public static QueueInterface<String> createStringTestQueue(QueueTypes queueType, int lastString) {
	QueueInterface<String> testQueue = null;
		
		switch (queueType) {
		case ArrayQueue:
			ArrayQueue<String> arrayQueue = new ArrayQueue<String>();
			testQueue = arrayQueue;
			break;
		case LinkedQueue:
			LinkedQueue<String> linkedQueue = new LinkedQueue<String>();
			testQueue = linkedQueue;
			break;
		}
		testQueue = initializeStringTestQueue(testQueue, lastString);
		
		return testQueue;
	}

	
	/**
	 * 
	 * This method is called by the various test case methods to display the header for the test case along with the column
	 * titles for the run time measurements.
	 * 
	 * @param	title	A String containing the description of the test case.
	 * 
	 */
	public static void showHeader(String title) {
		System.out.println();
		System.out.println();
		System.out.println(title);
		System.out.printf("\t\t%11s\t%11s\t%11s\t%11s\t%11s\t%11s\t%11s\t%11s\t%11s\t%11s\t%14s\t%20s\n",
						"Run 1", "Run 2", "Run 3", "Run 4", "Run 5",
						"Run 6", "Run 7", "Run 8", "Run 9", "Run 10",
						"Average", "Standard Deviation");
	}
	
	
	/**
	 * 
	 * THis method is called to display the contents of the given <code>QueueInterface</code>.
	 * The method makes use of the given <code>QueueInterface</code>'s <code>Iterator</code>
	 * to access all the objects in the queue.
	 * 
	 * @param	testQueue	The <code>QueueInterface</code> object that will be displayed
	 */
	public static void showTestQueue(QueueInterface<?> testQueue) {
		QueueIterator<?> qi = (QueueIterator<?>) testQueue.iterator();
		System.out.print("Queue = {");
		while (qi.hasNext()) {
			System.out.print(qi.next() + " ");
		}
		System.out.println("}");
		
	}
	
	
	/**
	 * 
	 * This method is called by the various test methods to run a particular test case. The type of test and its
	 * specifics are all specified by the parameters. 
	 * 
	 * @param 	testType		The type of test case being run. The choices are:
	 * 							<ol>
	 * 								<li>AddIntegers</li>
	 * 								<li>RemoveIntegers</li>
	 * 							</ol>
	 * @param 	firstNumber		When creating a new test queue, this parameter specifies the first number
	 * 							to initialize the queue with.
	 * @param 	lastNumber		When creating a new test queue, this parameter specifies the last number
	 * 							to initialize the test list with.
	 * 
	 */
	public static void runIntegerTestCase(TestTypes testType, int firstNumber, int lastNumber) { 
		long[] runTimes = new long[NUMBER_OF_TEST_CASES];
		QueueInterface<Integer> testQueue = null;
		long startTime;
		
		for (QueueTypes queueType : QueueTypes.values()) {
			System.out.print(queueType + "\t");
			for (int testCase = 0; testCase < NUMBER_OF_TEST_CASES; testCase++) {
				testQueue = createIntegerTestQueue(queueType, firstNumber, lastNumber);

				startTime = System.nanoTime();
				for (int i = FIRST_NUMBER; i <= LAST_NUMBER ; i += 1) {
					switch(testType) {
					case AddIntegers:
						testQueue.add(new Integer(i));
						break;
					case RemoveIntegers:
						testQueue.remove();
						break;
					case AddAndDisplayStrings:
						// do nothing this is here
						// to get rid of warning 
						break;
					case DisplayAndRemoveStrings:
						// do nothing this is here
						// to get rid of warning
						break;
					}
				}
				runTimes[testCase] = System.nanoTime() - startTime;
				System.out.printf("%11d\t", runTimes[testCase]);
			}
			System.out.printf("%14.2f\t%20.2f\n", Statistics.average(runTimes), Statistics.standardDeviation(runTimes));
		}
	}
	
	
	
	/**
	 *
	 * This method is called by the various test methods to run a particular test case. The type of test and its
	 * specifics are all specified by the parameters. 
	 * 
	 * @param 	testType		The type of test case being run. The choices are:
	 * 							<ol>
	 * 								<li>AddAndDisplayStrings</li>
	 * 								<li>DisplayAndRemoveStrings</li>
	 * 							</ol>
	 * 
	 * @param 	lastString		The number of the last string to add the queue.
	 */
	public static void runStringTestCase(TestTypes testType, int lastString) { 
		long[] runTimes = new long[NUMBER_OF_TEST_CASES];
		QueueInterface<String> testQueue = null;
		QueueIterator<String> sqi;
		long startTime;
		
		for (QueueTypes queueType : QueueTypes.values()) {
			for (int testCase = 0; testCase < NUMBER_OF_TEST_CASES; testCase++) {
				
				testQueue = createStringTestQueue(queueType, lastString);

				startTime = System.nanoTime();
				switch(testType) {
				case AddIntegers:
					// do nothing this is here
					// to get rid of warning
					break;
				case RemoveIntegers:
					// do nothing this is here
					// to get rid of warning
					break;
				case AddAndDisplayStrings:
					for (int i = FIRST_STRING ; i <= LAST_STRING ; i += 1) {
						testQueue.add(new String("String " + i));
					}
					break;
				case DisplayAndRemoveStrings:
					// do nothing 
					// The work is done by the iterator below
					break;
				}
				
				// Using an Iterator, display the contents of the queue
				sqi = (QueueIterator<String>) testQueue.iterator();
				System.out.print(queueType + " = {");
				while (sqi.hasNext()) {
					String myStr = sqi.next();
					System.out.print(myStr + ", ");
					if (testType == TestTypes.DisplayAndRemoveStrings) {
						sqi.remove();
					}
				}
				System.out.println("}");
				
				// calculate the runTime
				runTimes[testCase] = System.nanoTime() - startTime;

				// show that the queue is empty only if this is DisplayAndRemoveStrings
				if (testType == TestTypes.DisplayAndRemoveStrings) {
					sqi = (QueueIterator<String>) testQueue.iterator();
					System.out.print(queueType + " = {");
					while (sqi.hasNext()) {
						String myStr = sqi.next();
						System.out.print(myStr + ", ");
					}
					System.out.println("}");
				}
			}

			// show the runTimes and stats
			System.out.print(queueType + "\t");
			for (int testCase = 0; testCase < NUMBER_OF_TEST_CASES; testCase++) {
				System.out.printf("%11d\t", runTimes[testCase]);
			}
			System.out.printf("%14.2f\t%20.2f\n", Statistics.average(runTimes), Statistics.standardDeviation(runTimes));
		}
	}
	
	/**
	 * 
	 * This test case is for inserting all the numbers (1 <= n <= 100,000 into an empty queue.
	 * The numbers are added to the queue using the add(E e) method of the QueueInterface.
	 *  
	 */
	public static void addNumbers() {
		showHeader("Adding all numbers from " + FIRST_NUMBER + " to " + LAST_NUMBER + " using add(E e) to an empty queue");
		runIntegerTestCase(TestTypes.AddIntegers, 0, 0);
	}
	
	/**
	 * 
	 * This test case is for removing all the numbers (1 <= n <= 100,000 from a fully populated queue.
	 * The numbers are removed from the queue using the remove() method of the QueueInterface.
	 *  
	 */
	public static void removeNumbers() {
		showHeader("Removing all numbers from " + FIRST_NUMBER + " to " + LAST_NUMBER + " using remove() from a full queue");
		runIntegerTestCase(TestTypes.RemoveIntegers, FIRST_NUMBER, LAST_NUMBER);
	}

	
	/**
	 * 
	 * This test case is for inserting all the strings starting with "String 1", "String 2" ..... "String 100" to an empty queue.
	 * We also display all the queue entries using an Iterator.
	 *  
	 */
	public static void addAndDisplayStrings() {
		showHeader("Adding " + LAST_STRING + " Strings using add(E e) to an emopty queue, And displaying the queue");
		runStringTestCase(TestTypes.AddAndDisplayStrings, 0);
	}

	
	/**
	 * 
	 * This test case is for displaying and deleting strings starting with "String 1", "String 2" ..... "String 100" from a fully
	 * populated queue. The queue entries are all displayed and deleted using an Iterator.
	 *  
	 */
	public static void DisplayAndRemoveStrings() {
		showHeader("Displaying " + LAST_STRING + " Strings using the Iterator.hasNext(), Iterator.next() and deleting them using the Iterator.remove()");
		runStringTestCase(TestTypes.DisplayAndRemoveStrings, LAST_STRING);
	}
	
	
	
	public static void main(String[] args) {
		addNumbers();
		removeNumbers();
		addAndDisplayStrings();
		DisplayAndRemoveStrings();
	}
}
