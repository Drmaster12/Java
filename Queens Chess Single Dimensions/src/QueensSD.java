public class QueensSD {

	// squares per row or column
	public static final int BOARD_SIZE = 8;

	// used to indicate an empty square
	public static final int EMPTY = -1;

	private int board[]; // chess board

	private int removeQueen, isUnderAttack, placeQueens;
	
	
	
	// -------------------------------------------------
	// Constructor: Creates an empty square board.
	// -------------------------------------------------

	public QueensSD() {
		board = new int[BOARD_SIZE];
	} // end constructor

	
	// -------------------------------------------------
	// Clears the board.
	// Precondition: None.
	// Postcondition: Sets all squares to EMPTY.
	// -------------------------------------------------

	public void clearBoard() {
		for (int column = 0; column < BOARD_SIZE; column++) {
			board[column] = EMPTY;
		}
		removeQueen = 0;
		isUnderAttack = 0;
		placeQueens = -1;
	} // end clearBoard

	
	// -------------------------------------------------
	// Displays the board.
	// Precondition: None.
	// Postcondition: Board is displayed as follows
	// with a blank indicating an EMPTY square, and a Q
	// is a square containing a queen.
	//
	// +---+---+---+---+---+---+---+---+
	// | Q |   |   |   |   |   |   |   |
	// +---+---+---+---+---+---+---+---+
	// |   |   |   |   |   |   | Q |   |
	// +---+---+---+---+---+---+---+---+
	// |   |   |   |   | Q |   |   |   |
	// +---+---+---+---+---+---+---+---+
	// |   |   |   |   |   |   |   | Q |
	// +---+---+---+---+---+---+---+---+
	// |   | Q |   |   |   |   |   |   |
	// +---+---+---+---+---+---+---+---+
	// |   |   |   | Q |   |   |   |   |
	// +---+---+---+---+---+---+---+---+
	// |   |   |   |   |   | Q |   |   |
	// +---+---+---+---+---+---+---+---+
	// |   |   | Q |   |   |   |   |   |
	// +---+---+---+---+---+---+---+---+

	public void displaySeperator() {
		System.out.println("+---+---+---+---+---+---+---+---+");
	}

	public void displayBoard() {
		displaySeperator();

		for (int row = 0; row < BOARD_SIZE; row++) {
			System.out.print("|");
			for (int column = 0; column < BOARD_SIZE; column++) {
				if (board[column] == row) {
					System.out.print(" Q |");

				} else {
					System.out.print("   |");
				}
			}
			System.out.println();
			displaySeperator();
		}
		System.out.println("removeQueen Calls = " + removeQueen);
		System.out.println("isUnderAttack Calls = " + isUnderAttack);
		System.out.println("Recursive placeQueens Calls = " + placeQueens);
	} // end displayBoard

	
	// -------------------------------------------------
	// Places queens in columns of the board beginning
	// at the column specified.
	// Precondition: Queens are placed correctly in
	// columns 1 through column-1.
	// Postcondition: If a solution is found, each
	// column of the board contains one queen and method
	// returns true; otherwise, returns false (no
	// solution exists for a queen anywhere in column
	// specified).
	// -------------------------------------------------
	
	public boolean placeQueens(int column) {
		placeQueens++;
		if (column >= BOARD_SIZE) {
			return true; // base case
		} else {
			boolean queenPlaced = false;
			int row = 0; // number of square in column
			while (!queenPlaced && (row < BOARD_SIZE)) {
				// if square can be attacked
				if (isUnderAttack(row, column)) {
					++row; // consider next square in column
				} // end if
				else { // place queen and consider next column
					setQueen(row, column);
					queenPlaced = placeQueens(column + 1);
					// if no queen is possible in next column,
					if (!queenPlaced) {
						// backtrack: remove queen placed earlier
						// and try next square in column
						removeQueen(row, column);
						++row;
					} // end if
				} // end if
			} // end while
			return queenPlaced;
		} // end if
	} // end placeQueens

	
	// --------------------------------------------------
	// Sets a queen at square indicated by row and
	// column.
	// Precondition: None.
	// Postcondition: Sets the square on the board in a
	// given row and column to QUEEN.
	// --------------------------------------------------

	public void setQueen(int row, int column) {
		board[column] = row;
	} // end setQueen

	
	// --------------------------------------------------
	// Removes a queen at square indicated by row and
	// column.
	// Precondition: None.
	// Postcondition: Sets the square on the board in a
	// given row and column to EMPTY.
	// --------------------------------------------------

	public void removeQueen(int row, int column) {
		board[column] = EMPTY;
		removeQueen++;
	} // end removeQueen


	// --------------------------------------------------
	// Determines whether the square on the board at a
	// given row and column is under attack by any queens
	// in the columns 1 through column-1.
	// Precondition: Each column between 1 and column-1
	// has a queen placed in a square at a specific row.
	// None of these queens can be attacked by any other
	// queen.
	// Postcondition: If the designated square is under
	// attack, returns true; otherwise, returns false.
	// --------------------------------------------------

	private boolean isUnderAttack(int row, int column) {
		int r, c;
		
		isUnderAttack++;
		
		// check for a horizontal attack
		for ( c = 0 ; c < column ; c++ ) {
			if (board[c] == row) {
				return true;
			}
		}
		
		// check for upper left diagonal attack
		c = column - 1;
		r = row - 1;
		while ((r >= 0) && (c >= 0)) {
			if (board[c--] == r--) {
				return true;
			}
		}
		
		// check for lower left diagonal attack
		c = column - 1;
		r = row + 1;
		while ((r < BOARD_SIZE) && (c >= 0)) {
			if (board[c--] == r++) {
				return true;
			}
		}
		
		return false;
	} // end isUnderAttack
} // end Queens
