/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajazykavyvoj.Week2;

import java.text.DecimalFormat;

/**
 *
 * @author jozef.chmelar.ml
 */
public class Week2 {

    private static final char queen = 'Q';
    private static final char box = '#';

    /**
     * Rozmiestnite na šachovnici 8 dám tak, aby sa navzájom neohrozovali,
     * uvažujte šachovnicu 8x8 a pravidlá pre pohyb figúr podľa bežných
     * šachových pravidiel.
     *
     * @param n
     */
    private static char[][] getEmptyChessboard(final int size) {
        char[][] board = new char[size + 1][size + 1];
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                board[i][j] = box;
            }
        }
        return board;
    }

    public static void queenProblem() {
        //If n is even and n ≠ 6k, then place queens at (i, 1 + (2i + n/2 - 3 (mod n))) and (n + 1 - i, n - (2i + n/2 - 3 (mod n))) for i = 1,2,...,n/2.
        //https://en.wikipedia.org/wiki/Eight_queens_puzzle
        int n = 8;
        int x, y;
        char board[][] = getEmptyChessboard(n);
        for (int i = 1; i <= n / 2; i++) {
            x = i;
            y = 1 + ((2 * i + n / 2 - 3) % n);
            System.out.println(numberToLetter(x) + " " + y);
            board[x][y] = queen;
            x = n + 1 - i;
            y = n - ((2 * i + n / 2 - 3) % n);
            System.out.println(numberToLetter(x) + " " + y);
            board[x][y] = queen;
        }

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static char numberToLetter(int number) {
        return (char) (number + '`');
    }
/////////////////////////////////////////////////////////////////////////
    /**
     * 2. Nájdite cestu šachového koňa po šachovnici tak, aby prešiel všetky
     * políčka
     */
    private static int[][] solution;
    private static int path = 0;
    
    private  static void KnightTour(int N) {
        solution = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = 0;
            }
        }
    }

    public static void solve() {
        KnightTour(8);
        if (findPath(0, 0, 0, solution.length)) {
                print();
        } else {
            System.out.println("NO PATH FOUND");
        }
    }

	private static void print() {
		DecimalFormat twodigits = new DecimalFormat("00");
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution.length; j++) {
				System.out.print("   " + twodigits.format(solution[i][j]));
			}
			System.out.println();
		}
	}
        
    private static  boolean findPath(int row, int column, int index, int N) {
        
        //http://www.wou.edu/~broegb/Cs345/KnightTour.pdf
        // check if current is not used already
        if (solution[row][column] != 0) {
            return false;
        }
        // mark the current cell is as used
        solution[row][column] = path++;
        // if (index == 50) {
        if (index == N * N - 1) {
            // if we are here means we have solved the problem
            return true;
        }
        // try to solve the rest of the problem recursively

        // go down and right
        if (canMove(row + 2, column + 1, N)
                && findPath(row + 2, column + 1, index + 1, N)) {
            return true;
        }
        // go right and down
        if (canMove(row + 1, column + 2, N)
                && findPath(row + 1, column + 2, index + 1, N)) {
            return true;
        }
        // go right and up
        if (canMove(row - 1, column + 2, N)
                && findPath(row - 1, column + 2, index + 1, N)) {
            return true;
        }
        // go up and right
        if (canMove(row - 2, column + 1, N)
                && findPath(row - 2, column + 1, index + 1, N)) {
            return true;
        }
        // go up and left
        if (canMove(row - 2, column - 1, N)
                && findPath(row - 2, column - 1, index + 1, N)) {
            return true;
        }
        // go left and up
        if (canMove(row - 1, column - 2, N)
                && findPath(row - 1, column - 2, index + 1, N)) {
            return true;
        }
        // go left and down
        if (canMove(row + 1, column - 2, N)
                && findPath(row + 1, column - 2, index + 1, N)) {
            return true;
        }
        // go down and left
        if (canMove(row + 2, column - 1, N)
                && findPath(row + 2, column - 1, index + 1, N)) {
            return true;
        }
        // if we are here means nothing has worked , backtrack
        solution[row][column] = 0;
        path--;
        return false;

    }

    private static boolean canMove(int row, int col, int N) {
        if (row >= 0 && col >= 0 && row < N && col < N) {
            return true;
        }
        return false;
    }

}
  