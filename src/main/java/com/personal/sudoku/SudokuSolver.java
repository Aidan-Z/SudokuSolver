package com.personal.sudoku;

// From YouTube Video: "Create a Sudoku Solver In Java In 20 Minutes - Full Tutorial"

public class SudokuSolver {

	// Creating constant for 9x9 grid:
	private static final int GRID_SIZE = 9;
	
	public static void main(String[] args) {
		
		// Making game board (grid) - 2D int array:
		// 9 x 9. 0's = blanks
		int[][] board = {
				{7, 0, 2, 0, 5, 0, 6, 0, 0},
				{0, 0, 0, 0, 0, 3, 0, 0, 0},
				{1, 0, 0, 0, 0, 9, 5, 0, 0},
				{8, 0, 0, 0, 0, 0, 0, 9, 0},
				{0, 4, 3, 0, 0, 0, 7, 5, 0},
				{0, 9, 0, 0, 0, 0, 0, 0, 8},
				{0, 0, 9, 7, 0, 0, 0, 0, 5},
				{0, 0, 0, 2, 0, 0, 0, 0, 0},
				{0, 0, 7, 0, 4, 0, 2, 0, 3}
			};
		
		System.out.println("Original Board:");
		System.out.println("                             ");

		
		printBoard(board);
		
		// If board can't be solved, that means the starting board (combination of numbers) was impossible to solve
		if (solveBoard(board)) {
			System.out.println("                             ");
			System.out.println("Board Was Successfully Solved");
			System.out.println("                             ");
		}
		else {
			System.out.println("                             ");
			System.out.println("Unsovalbe Board");
			System.out.println("                             ");
		}
		
		printBoard(board);
	}
	
	private static void printBoard(int[][] board) {
		for (int row = 0; row < GRID_SIZE; row++) {
			if (row % 3 == 0 && row != 0) {
				System.out.println("-----------");
			}
			for (int column = 0; column < GRID_SIZE; column++) {
				if (column % 3 == 0 && column != 0)  {
					System.out.print("|");
				}
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
		
	}

	// Helper Methods:
	
	// 1) NUMBER IN ROW?
	// Boolean so that can return 'true' if number exists in row and 'false' if doesn't:
	// Takes in parameter 'int[][] board' and an int for the number we want to check if in row
	// And another int for row number
	private static boolean isNumberInRow(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			// If board, in row passed in, in current column 'i' (that we're iterating threw),
			// equals number that we're checking, then we want to return true
			if (board[row][i] == number) {
				return true;
			}
		}
		// if get through for loop and don't find number, return false
		return false;	
	}
	
	// 2) NUMBER IN COLUMN?
	private static boolean isNumberInColumn(int[][] board, int number, int column) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		return false;	
	}
	
	// 3) NUMBER IN 3X3 BOX?
	private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
		// take 'row' passed in and subtract 'row mod 3' 
		// mod takes first number and divides it by second number and gives remainder 
		int localBoxRow	= row - row % 3;
		int localBoxColumn = column - column % 3;
		
		// nested forLoop:
		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				// if row i at column j = number we're looking for:
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		return false;	
	}
	
	// One method that checks all 3 helper methods
	// int row and int column are the coordinates we want to put number in
	private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
		return !isNumberInRow(board, number, row) &&
				!isNumberInColumn(board, number, column) &&
				!isNumberInBox(board, number, row, column);	
	}
	
	// MEthod to solve the board:
	private static boolean solveBoard(int[][] board) {
		// Nested for loop, so that we can loop through entire grid
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				// If 'board' at that 'row' and 'column' is equal to 0, do THIS (see bellow):
				if (board[row][column] == 0) {
					// Code that loops threw numbers 1-9 if blank space on board:
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column)) {
							// If placement is valid, place number there: 'board at row/column set = to numberToTry'
							board[row][column] = numberToTry; // if this number works for grid but cant be used to solve rest of board, we clear it (else statement) and set back to zero
							// If first number you try isn't valid, it will go to the next one and loop threw 1-9.
							
							// Recursion of Algorithm: (will recursively call the solveBoard method until it fills out the grid)
							if (solveBoard(board)) {
								return true;
							}
							else {
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	
	
}
