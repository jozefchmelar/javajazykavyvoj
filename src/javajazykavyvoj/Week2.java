/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajazykavyvoj;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.Console;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author jozef.chmelar.ml
 */
public class Week2 {

    private static final char QUEEN = '♛';
    private static final char KNIGHT = '♞';
    private static final char BLACK_SQUARE = '■';
    private static final char WHITE_SQUARE = '□';
    private static char[][] chessboardForKnight = getEmptyChessboard(8);
    private static int[][] solution;
    private static int path = 0;

    private static char[][] getEmptyChessboard(final int size) {
        char[][] board = new char[size + 1][size + 1];
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < size + 1; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = WHITE_SQUARE;
                } else {
                    board[i][j] = BLACK_SQUARE;
                }
            }
        }
        return board;
    }

    public static void queenProblem() {
        //If n is even and n ≠ 6k, then place queens at (i, 1 + (2i + n/2 - 3 (mod n))) and (n + 1 - i, n - (2i + n/2 - 3 (mod n))) for i = 1,2,...,n/2.
        //https://en.wikipedia.org/wiki/Eight_queens_puzzle
        final int chessboardSize = 8;
        int x, y;
        char board[][] = getEmptyChessboard(chessboardSize);
        for (int i = 1; i <= chessboardSize / 2; i++) {
            x = i;
            y = 1 + ((2 * i + chessboardSize / 2 - 3) % chessboardSize);
            board[x][y] = QUEEN;
            x = chessboardSize + 1 - i;
            y = chessboardSize - ((2 * i + chessboardSize / 2 - 3) % chessboardSize);
            board[x][y] = QUEEN;
        }
        System.out.println();
        for (int i = 8; i >= 1; i--) {
            for (int j = 8; j >= 1; j--) {
                String test = "" + board[i][j];
                System.out.printf("%1s ", test);
            }
            System.out.println();
        }
    }

    /**
     * Converts a->1 b->2 etc.
     *
     * @param number
     * @return
     */
    private static char numberToLetter(int number) {
        char toLetter = ((char) (number + '`'));
        return Character.toUpperCase(toLetter);
    }
///////////////////////////////////////////////////////////

    /**
     * Nájdite cestu šachového koňa po šachovnici tak, aby prešiel všetky horse
     * can move x+-2 y+-1 or x+-1 y+-2 políčka
     */
    private static void initChessboard(int sizeOfChessBoard) {
        solution = new int[sizeOfChessBoard][sizeOfChessBoard];
        for (int i = 0; i < sizeOfChessBoard; i++) {
            for (int j = 0; j < sizeOfChessBoard; j++) {
                solution[i][j] = 0;
            }
        }
    }

    public static void solveKingsTraversal() {
        initChessboard(8);
        if (findPath(0, 0, 0, solution.length)) {
            getCoordinates();
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

    //  private static LinkedList<int x, int y> points = new LinkedList<>();
    private static boolean findPath(int row, int column, int step, int sizeOfChessboard) {
        //http://www.wou.edu/~broegb/Cs345/KnightTour.pdf
        // check if current is not used already
        if (solution[row][column] != 0) {
            return false;
        }
        // mark the current cell is as used
        solution[row][column] = path++;
        // if (index == 50) {
        if (step == sizeOfChessboard * sizeOfChessboard - 1) {
            // if we are here means we have solved the problem
            return true;
        }
        // horse can move x+-2 y+-1 or x+-1 y+-2   ...try to solve the rest of the problem recursively
        // it's really bad, and hungry algorthim :)
        // go down and right
        if (canMove(row + 2, column + 1, sizeOfChessboard)
                && findPath(row + 2, column + 1, step + 1, sizeOfChessboard)) {
            return true;
        }
        // go right and down
        if (canMove(row + 1, column + 2, sizeOfChessboard)
                && findPath(row + 1, column + 2, step + 1, sizeOfChessboard)) {
            return true;
        }
        // go right and up
        if (canMove(row - 1, column + 2, sizeOfChessboard)
                && findPath(row - 1, column + 2, step + 1, sizeOfChessboard)) {
            return true;
        }
        // go up and right
        if (canMove(row - 2, column + 1, sizeOfChessboard)
                && findPath(row - 2, column + 1, step + 1, sizeOfChessboard)) {
            return true;
        }
        // go up and left
        if (canMove(row - 2, column - 1, sizeOfChessboard)
                && findPath(row - 2, column - 1, step + 1, sizeOfChessboard)) {
            return true;
        }
        // go left and up
        if (canMove(row - 1, column - 2, sizeOfChessboard)
                && findPath(row - 1, column - 2, step + 1, sizeOfChessboard)) {
            return true;
        }
        // go left and down
        if (canMove(row + 1, column - 2, sizeOfChessboard)
                && findPath(row + 1, column - 2, step + 1, sizeOfChessboard)) {
            return true;
        }
        // go down and left
        if (canMove(row + 2, column - 1, sizeOfChessboard)
                && findPath(row + 2, column - 1, step + 1, sizeOfChessboard)) {
            return true;
        }
        // if we are here means nothing has worked , backtrack
        solution[row][column] = 0;
        path--;
        return false;
    }

    private static boolean canMove(int row, int col, int sizeOfChessboard) {
        return row >= 0 && col >= 0 && row < sizeOfChessboard && col < sizeOfChessboard;
    }

    private static void getCoordinates() {
        // class for holding value of point beacsue I coded it really bad...
        class Point {

            private int x;
            private int y;

            //public Point(){};
            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Point() {

            }

            public int getX() {
                return x;
            }

            public int getY() {
                return y;
            }

            public boolean is00() {
                return (this.x == 0 && this.y == 0);
            }
        }
        Point[] points = new Point[8 * 8];
        //here I go through through the solution array
        //put the step as index of array and the actual position as value.
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                points[solution[x][y]] = new Point(x, y);
            }
        }
        // now my points array looks like 
        // points[0] - first move of knight  ; postion[1] second move etc.
        for (Point point : points) {
            printPositionOfKnight(point.getX(), point.getY(),true);
        }
    }

    private static void printPositionOfKnight(int x, int y, boolean markVisitedPosition) {
        chessboardForKnight[x][y] = KNIGHT;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessboardForKnight[i][j] + " ");
            }
            System.out.println();
        }
        if (markVisitedPosition) {
            chessboardForKnight[x][y] = 'X';
        }
        System.out.println("-------------------------");
    }

}
