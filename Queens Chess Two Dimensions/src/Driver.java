
public class Driver {

	public static void main(String[] args) {
		Queens myQueens = new Queens();
		
		for ( int row = 0 ; row < Queens.BOARD_SIZE ; row++ ) {
			myQueens.clearBoard();
			myQueens.setQueen(row, 0);

			myQueens.placeQueens(1);
			
			myQueens.displayBoard();
			
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

}
