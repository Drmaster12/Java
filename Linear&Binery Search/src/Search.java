
public class Search {

	public static int[] numbers = new int[10000000];
	
	public static int[] targets = {500, 10000, 100000, 1000000, 5000000, 7000000, 10000000};

	public static enum SearchType {LinearSearch, BinarySearch};

	
	// method to initialize the array of numbers to search
	public static void initNumbers() {
		for ( int i = 0 ; i < numbers.length ; i++ ) {
			numbers[i] = i + 1;
		}
	}
	
	// linear search method
	public static int linearSearch(int target) {
		int returnCode = -1;
		
		for ( int i = 0 ; i < numbers.length ; i++ ) {
			if (numbers[i] == target) {
				returnCode = i;
				break;
			}
		}
		
		return returnCode;
	}

	// binary search method
	public static int binarySearch(int target) {
		int returnCode = -1;
		int low = 0;
		int high = numbers.length - 1;
		int mid;
		
		while (low <= high) {
			 mid = (high + low)/2;

			 if (numbers[mid] == target) {
				returnCode = mid;
				break;
			} else if (numbers[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		
		return returnCode;
	}
	
	
	
	public static void main(String[] args) {
		
		// initialize the array of numbers
		initNumbers();

		int searchReturnCode = 0;
		
		long startTime;
		
		// create an array to store the run times for each case
		long[] runTimes = new long[10];
		
		System.out.println();
		for ( int i = 0 ; i < targets.length ; i++ ) {

			System.out.println("Search array of " + numbers.length + " elements for target = " + targets[i]);
			System.out.printf("\t\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\t%10s\t%18s\n", "Run 1", "Run 2", "Run 3", "Run 4", "Run 5",
																									  "Run 6", "Run 7", "Run 8", "Run 9", "Run 10",
																									  "Average", "Standard Deviation");

			// a loop that iterates on the sortType enumeration
			for (SearchType searchType : SearchType.values()) {
			
				System.out.print(searchType + "\t");

				for ( int j = 0 ; j < 10 ; j++) {
					startTime = System.nanoTime();
				
					switch (searchType) {
					case LinearSearch:
						searchReturnCode = linearSearch(targets[i]); 
						break;
					case BinarySearch:
						searchReturnCode = binarySearch(targets[i]); 
						break;
					}

					if (searchReturnCode == -1) {
						System.out.println("Target " + targets[i] + " not found");
					} else {
						runTimes[j] = System.nanoTime() - startTime;
						System.out.printf("%8d\t", runTimes[j]);
					}
				}

				System.out.printf("%10.2f\t%18.2f\n", Statistics.average(runTimes), Statistics.standardDeviation(runTimes));
			}
			System.out.println();
			System.out.println();
		}
	}
}
