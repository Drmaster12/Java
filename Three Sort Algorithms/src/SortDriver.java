import java.util.Random;

public class SortDriver {
	
	public static int[] numbers;
	public static int[] shortNumbers = new int[1000];
	public static int[] longNumbers = new int[10000];

	public static enum ArrayType {Constant, Random, Ascending, Descending, MostlyAscending};
	public static enum SortType {BubbleSort, SelectionSort, InsertionSort};

	public static final int MAX_TEST_CASES = 10;
	
	// method to print out the array of numbers
	public static void showNumbers() {
		for ( int i = 0 ; i < numbers.length ; i++ ) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
		System.out.println();
	}


	// method to initialize the numbers array according to the type of array we want
	// The choices are: {Constant, Random, Ascending, Descending, MostlyAscending}
	public static void initNumbers(ArrayType type) {
		
		Random randomNumber = new Random();
		int maxRandomNumber = 1000000;

		final int randomRange = 100;

		for ( int i = 0 ; i < numbers.length ; i++ ) {
			switch (type) {
			case Constant:
				numbers[i] = 5;
				break;
			case Random:
				numbers[i] = randomNumber.nextInt() % maxRandomNumber;
				break;
			case Ascending:
				numbers[i] = i + 1;
				break;
			case Descending:
				numbers[i] = numbers.length - i;
				break;
			case MostlyAscending: 
				if (i < (numbers.length - randomRange)) {
					numbers[i] = i + 1;
				} else {
					numbers[i] = randomNumber.nextInt() % maxRandomNumber;
				}
				break;
			}
		}
//		showNumbers();
	}
	
	
	// main driver for HW2
	public static void main(String[] args) {

		long startTime, runTime;

		// create an array to store the run times for each case
		long[] runTimes = new long[10];

		// start out with numbers being the short list of numbers
		numbers = shortNumbers;
		
		// a loop of count 2
		// 		i = 0 : numbers = shortNumbers
		//      i = 1 : numbers = longNumbers
		for ( int i = 0 ; i < 2 ; i++ ) {
			
			
			// a loop that iterates on the ArrayType enumeration
			for (ArrayType arrayType : ArrayType.values()) {

				System.out.println("Sorting array of " + numbers.length + " " + arrayType + " elements: ");
				System.out.printf("\t\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%12s\t%18s\n", "Run 1", "Run 2", "Run 3", "Run 4", "Run 5",
						  																			    "Run 6", "Run 7", "Run 8", "Run 9", "Run 10",
						  																			    "Average", "Standard Deviation");

				// a loop that iterates on the sortType enumeration
				for (SortType sortType : SortType.values()) {
				
					System.out.print(sortType + "\t");

					// a loop of count MAX_TEST_CASES

					for ( int testCaseCount = 0 ; testCaseCount < MAX_TEST_CASES ; testCaseCount++) {

						// initialize the array with the correct type of values
						// this needs to be done before running each sort
						initNumbers(arrayType);

						// note the start time of the sort
						startTime = System.nanoTime();
						
						switch (sortType) {
						case BubbleSort:
							BubbleSort.sort(numbers);
//							showNumbers();
							break;
						case SelectionSort:
							SelectionSort.sort(numbers);
//							showNumbers();
							break;
						case InsertionSort:
							InsertionSort.sort(numbers);
//							showNumbers();
							break;
						}

						// calculate the run time for this run
						runTimes[testCaseCount] = System.nanoTime() - startTime;
						System.out.printf("%8d\t", runTimes[testCaseCount]);
					}
					System.out.printf("%12.2f\t%18.2f\n", Statistics.average(runTimes), Statistics.standardDeviation(runTimes));
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
			// switch numbers to be the long list of numbers
			numbers = longNumbers;
		}
	}
}
