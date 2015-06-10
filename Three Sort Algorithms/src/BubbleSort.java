
public class BubbleSort {
	
	// method to perform Bubble Sort on the array of numbers.
	public static void sort(int[] numbers) {
		int lastSwap;
		int lastCell = numbers.length -1;
		int temp;
		
		do {
			lastSwap = 0;

			for ( int i = 0 ; i < lastCell ; i++ ) {
				
				if (numbers[i] > numbers[i+1]) {
					temp = numbers[i];
					numbers[i] = numbers[i+1];
					numbers[i+1] = temp;
					lastSwap = i;
				}
			}
			
			lastCell = lastSwap;
		} while(lastCell > 0);
	}
}
