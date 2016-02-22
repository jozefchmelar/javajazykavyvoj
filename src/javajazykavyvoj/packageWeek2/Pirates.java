/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajazykavyvoj.packageWeek2;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author jozef.chmelar.ml
 */
public class Pirates {

    private Point[][] island;
    private String[][] traversal;
    private final int n = 8;
    private final int surviorX = 5;
    private final int surviorY = 3;

    public Pirates() {
        this.island = new Point[n][n];
        this.traversal = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.island[i][j] = new Point();
                this.traversal[i][j] = "";
            }
        }
        island[surviorX][surviorY].setSurvior();
    }

    public void solve() {
        move(7, 7, 0);
    }

    private boolean move(int x, int y, int step) {
        island[x][y].setVisited();
        System.out.println(step + ":\t" + x + ", " + y);

        if (step == (n * n - 1) && surviorX == x && surviorY == y) {
            System.out.println("done");
            return true;
        }
        /*
         Odmovnina tretia x  -1 to cele / odmocnina x -1
          
 
         */
        //UP
        if (canMove(x + 1, y) && move(x + 1, y, step + 1)) {
            return true;
        }
        //rigth
        if (canMove(x, y + 1) && move(x, y + 1, step + 1)) {
            return true;
        }
        //left
        if (canMove(x, y - 1) && move(x, y - 1, step + 1)) {
            return true;
        }

        //DOWN
        if (canMove(x - 1, y) && move(x - 1, y, step + 1)) {
            return true;
        }
//System.out.println("no valid move");
        return false;
    }

    private boolean canMove(int x, int y) {
        if (x >= 0 && y >= 0 && x < n && y < n && !island[x][y].isVisited() && !island[x][y].isSurvior()) {
            return true;
        }
        return false;
    }

//    private static int[][] solution;
//    private static int path = 0;
//
//    private static void PirateTour(int N) {
//        solution = new int[N][N];
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                solution[i][j] = 0;
//            }
//        }
//    }
//
//    public static void solve() {
//        PirateTour(8);
//
//        if (findPath(0, 0, 0, solution.length)) {
//            print();
//        } else {
//            System.out.println("NO PATH FOUND");
//        }
//    }
//
//    private static void print() {
//        DecimalFormat twodigits = new DecimalFormat("00");
//        for (int i = 0; i < solution.length; i++) {
//            for (int j = 0; j < solution.length; j++) {
//                System.out.print("   " + twodigits.format(solution[i][j]));
//            }
//            System.out.println();
//        }
//    }
//
//    private static boolean findPath(int riadok, int stlpec, int index, int N) {
//
//        //http://www.wou.edu/~broegb/Cs345/KnightTour.pdf
//        // check if current is not used already
//        if (solution[riadok][stlpec] != 0) {
//            return false;
//        }
//        // mark the current cell is as used
//        solution[riadok][stlpec] = path++;
//        // if (index == 50) {
//        if (index == N * N - 1) {
//            // if we are here means we have solved the problem
//            return true;
//        }
//        // try to solve the rest of the problem recursively
//
//        // go down and right
//        if (canMove(riadok + 1, stlpec + 1, N)
//                && findPath(riadok + 1, stlpec + 1, index + 1, N)) {
//            return true;
//        }
//
//        // go right and up
//        if (canMove(riadok - 1, stlpec + 1, N)
//                && findPath(riadok - 1, stlpec + 1, index + 1, N)) {
//            return true;
//        }
//
//        // go up and left
//        if (canMove(riadok - 1, stlpec - 1, N)
//                && findPath(riadok - 1, stlpec - 1, index + 1, N)) {
//            return true;
//        }
//
//        // go left and down
//        if (canMove(riadok + 1, stlpec - 1, N)
//                && findPath(riadok + 1, stlpec - 1, index + 1, N)) {
//            return true;
//        }
//        // go down 
//        if (canMove(riadok + 1, stlpec, N)
//                && findPath(riadok + 1, stlpec, index + 1, N)) {
//            return true;
//        }
//        // go up 
//        if (canMove(riadok - 1, stlpec, N)
//                && findPath(riadok - 1, stlpec, index + 1, N)) {
//            return true;
//        }
//        // go rigt 
//        if (canMove(riadok, stlpec + 1, N)
//                && findPath(riadok, stlpec + 1, index + 1, N)) {
//            return true;
//        }
//        // go left 
//        if (canMove(riadok, stlpec - 1, N)
//                && findPath(riadok, stlpec - 1, index + 1, N)) {
//            return true;
//        }
//        // if we are here means nothing has worked , backtrack
//        solution[riadok][stlpec] = 0;
//        path--;
//        return false;
//
//    }
//
//    private static boolean canMove(int row, int col, int N) {
//        if (row >= 0 && col >= 0 && row < N && col < N) {
//            return true;
//        }
//        return false;
//    }
}
