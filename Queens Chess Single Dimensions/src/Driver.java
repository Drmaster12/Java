
public class Driver {

	public static void main(String[] args) {
		QueensSD myQueens = new QueensSD();
		
		for ( int row = 0 ; row < QueensSD.BOARD_SIZE ; row++ ) {
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
