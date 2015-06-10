
public class InsertionSort {

	// method to perform insertion Sort on the array of numbers
	public static void sort(int[] numbers) {
		
		int j, temp;
		
		 for ( int i = 1 ; i < numbers.length ; i++ ) {
			 temp = numbers[i];
			 j = i;
			 
			 while ((j > 0) && (numbers[j-1] > temp)) {
				 numbers[j] = numbers[j-1];
				 j -= 1;
			 }
			 numbers[j] = temp;
		 }
	}
	
}
