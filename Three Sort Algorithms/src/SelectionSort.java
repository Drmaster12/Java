
public class SelectionSort {
	
	
	// method to perform selection sort on the array of numbers
	public static void sort(int[] numbers) {
		
		for ( int i = 0 ; i < numbers.length ; i++) {
			int curMin = numbers[i];
			int curMinIndex = i;
			for ( int j = i+1 ; j < numbers.length ; j++) {
				if (numbers[j] < curMin) {
					curMin = numbers[j];
					curMinIndex = j;
				}
			}
			
			numbers[curMinIndex] = numbers[i];
			numbers[i] = curMin;
		}
	}

}
