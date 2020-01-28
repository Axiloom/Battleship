/**
 /**
 * This file contains testing methods for the Battleship project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Battleship methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Battleship project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Random;
import java.util.Scanner;
import java.awt.datatransfer.*;

/**
 * This class contains a few methods for testing methods in the Battleship
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author Joe Lessner
 * @author John Bednarczyk
 *
 */
public class TestBattleship {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when
     * you are ready for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        
        //Milestone 1
        testCoordAlphaToNum();
        testCoordNumToAlpha();
        
        //Milestone 2
        testCheckWater();
        testPlaceShip();
        testPlaceRandomShip();
        
        //Milestone 3
        testTakeShot();
        testCheckLost();
        
        
    }
    
    private static void testCoordAlphaToNum() {
        
        //Variables
        int numTests = 6;
        int passed = numTests;
        int res;
        
        //Test 1
        if((res = Battleship.coordAlphaToNum("BAAA")) != 17576) {
            System.out.println("FAILED: Battleship.coordAlphaToNum(\"BAAA\") != 17576, but " + res);
            passed--;
        }
        
        //Test 2
        if((res = Battleship.coordAlphaToNum("ZERTY")) != 11506714) {
            System.out.println("FAILED: Battleship.coordAlphaToNum(\"ZERTY\") != 11506714, but " + res);
            passed--;
        }
        
        //Test 3
        if((res = Battleship.coordAlphaToNum("zerty")) != 11506714) {
            System.out.println("FAILED: Battleship.coordAlphaToNum(\"zerty\") != 11506714, but " + res);
            passed--;
        }
        
        //Test 4
        if((res = Battleship.coordAlphaToNum("&é\"")) != -14747) {
            System.out.println("FAILED: Battleship.coordAlphaToNum(\"&é\\\"\") != -14747, but " + res);
            passed--;
        }
        
        //Test 5
        if((res = Battleship.coordAlphaToNum("HeLlO")) != 3276872) {
            System.out.println("FAILED: Battleship.coordAlphaToNum(\"HeLlO\") != 3276872, but " + res);
            passed--;
        }
        
        //Test 6
        if((res = Battleship.coordAlphaToNum("AAAAA")) != 0) {
            System.out.println("FAILED: Battleship.coordAlphaToNum(\"AAAAA\") != 0, but " + res);
            passed--;
        }
        
        // # of tests passed
        System.out.println("testCoordAlphatoNum: Passed " + passed + " of " + numTests + " tests.");
    }

    private static void testCoordNumToAlpha() {
        
        //Variables
        int numTests = 2;
        int passed = numTests;
        String res;
        
        //Test 1
        if(!((res = Battleship.coordNumToAlpha(3276872)).equals("HELLO"))) {
            System.out.println("FAILED: Battleship.coordNumToAlpha(3276872) != HELLO, but " + res);
            passed--;
        }
        
        //Test 2
        if(!((res = Battleship.coordNumToAlpha(0)).equals("A"))) {
            System.out.println("FAILED: Battleship.coordNumToAlpha(0) != A, but " + res);
            passed--;
        }
        
        // # of tests passed
        System.out.println("testCoordNumToAlpha: Passed " + passed + " of " + numTests + " tests.");
    }
    
    
    private static void testCheckWater() {
      
        //Variables
        int numTests = 2;
        int passed = numTests;
        int res;
        char[][] boardTest = new char[6][6];
        
        //Initializes new board for testing purposes
        Battleship.initBoard(boardTest);
        
        //Test 1
        if((res = Battleship.checkWater(boardTest, 0, 0, 3, true)) != 1) {
            System.out.println("FAILED: Battleship.checkWater(testBoard, 0, 0, 3, true) != (1), but " + res);
            passed--;
        }
        
        //Test 2
        if((res = Battleship.checkWater(boardTest, 0, 0, 7, true)) != -2) {
            System.out.println("FAILED: Battleship.checkWater(testBoard, 0, 0, 7, true) != (-2), but " + res);
            passed--;
        }
        
        // # of tests passed
        System.out.println("testCheckWater: Passed " + passed + " of " + numTests + " tests.");
    
    
    }
    private static void testPlaceShip() {
        
        //Variables
        int numTests = 2;
        int passed = numTests;
        boolean res;
        char primaryBoard[][] = new char[6][6];
        
        Battleship.initBoard(primaryBoard);
        
        //Test 1
        if((res = Battleship.placeShip(primaryBoard, 1, 1, 3, true, 1)) != true) {
            System.out.println("FAILED: Battleship.placeShip(primaryBoard, 1, 1, 3, true, 1) != true, but " + res);
            passed--;
        }
        
        //Test 2
        if((res = Battleship.placeShip(primaryBoard, 6, 6, 3, false, 9)) != false) {
            System.out.println("FAILED: Battleship.placeShip(primaryBoard, 6, 6, 3, false, 1) != false, but " + res);
            passed--;
        }
        // # of tests passed
        System.out.println("testPlaceShip: Passed " + passed + " of " + numTests + " tests.");
        
    }
    
    private static void testPlaceRandomShip() {
    	
        //Variables
        int numTests = 3;
        int passed = numTests;
        boolean res;
        char boardTest[][] = new char[6][6];
        Random rand = new Random(Config.SEED);
        
        //Fills test board with ships
        for (int i = 0 ; i < boardTest.length ; i++) {
            for (int j = 0 ; j < boardTest[i].length ; j++) {
                boardTest[i][j] = '1';
            }
        }
        
        //Test 1 (Placing on a full board)
        if((res = Battleship.placeRandomShip(boardTest, 3, 1, rand)) != false) {
            System.out.println("FAILED: Battleship.placeRandomShip(primaryBoard, 3, 1, rand) != false, but " + res);
            passed--;
        }
        
        //Fills test board with water
        for (int i = 0 ; i < boardTest.length ; i++) {
            for (int j = 0 ; j < boardTest[i].length ; j++) {
                boardTest[i][j] = Config.WATER_CHAR;
            }
        }
        
        //Test 2 (Placing on an empty board)
        if((res = Battleship.placeRandomShip(boardTest, 3, 1, rand)) != true) {
            System.out.println("FAILED: Battleship.placeRandomShip(primaryBoard, 3, 1, rand) != false, but " + res);
            passed--;
        }
        
        for (int i = 0 ; i < boardTest.length ; i++) {
            for (int j = 0 ; j < boardTest[i].length ; j++) {
                boardTest[i][j] = Config.WATER_CHAR;
            }
        }
        
        //Test 3 (Placing on a 1x1 board)
        if((res = Battleship.placeRandomShip(boardTest, 1, 1, rand)) != true) {
            System.out.println("FAILED: Battleship.placeRandomShip(primaryBoard, 1, 1, rand) != false, but " + res);
            passed--;
        }
        
        
        // # of tests passed
        System.out.println("testPlaceRandomShip: Passed " + passed + " of " + numTests + " tests.");
        
        
    }
    
    private static void testTakeShot() {
        
        //Variables
        int numTests = 5;
        int passed = numTests;
        int res;
        
        //Objects
        char board[][] = new char[6][6];
        
        //Fills board with water
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[i].length ; j++) {
                board[i][j] = Config.WATER_CHAR;
            }
        }
        
        //Places a ship on the board
        board[0][2] = '1';
        board[1][2] = '1';
        board[2][2] = '1';
        board[3][2] = '1';
        
        //Test 1 (Take shot at no ship)
        if((res = Battleship.takeShot(board, 0, 1)) != 2) {
            System.out.println("FAILED: Battleship.takeShot(board, 0, 1) != 2, but " + res);
            passed--;
        }
        
        //Test 2 (Take shot at ship)
        if((res = Battleship.takeShot(board, 2, 0)) != 1) {
            System.out.println("FAILED: Battleship.takeShot(board, 2, 0) != 1, but " + res);
            passed--;
        }
        
        //Test 3 (Take shot off board)
        if((res = Battleship.takeShot(board, 7, 0)) != -1) {
            System.out.println("FAILED: Battleship.takeShot(board, 7, 0) != -1, but " + res);
            passed--;
        }
        
        //Test 4 (Take shot at place already shot (*))
        board[0][2] = Config.HIT_CHAR;
        if((res = Battleship.takeShot(board, 2, 0)) != 3) {
            System.out.println("FAILED: Battleship.takeShot(board, 2, 0) != 3, but " + res);
            passed--;
        }
        
        //Test 5 (Take shot at place already shot (@))
        board[0][1] = Config.MISS_CHAR;
        if((res = Battleship.takeShot(board, 1, 0)) != 3) {
            System.out.println("FAILED: Battleship.takeShot(board, 1, 0) != 3, but " + res);
            passed--;
        }
        
        // # of tests passed
        System.out.println("testTakeShot: Passed " + passed + " of " + numTests + " tests.");
    }
    
    private static void testCheckLost() {

        //Variables
        int numTests = 2;
        int passed = numTests;
        boolean res;
        
        //Objects
        char board[][] = new char[6][6];
        
        //Fills board with water
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[i].length ; j++) {
                board[i][j] = Config.WATER_CHAR;
            }
        }
        
        //Places a ship on the board
        board[0][2] = '1';
        
        //Test 1
        board[0][2] = '1';
        board[0][3] = Config.MISS_CHAR;
        board[0][4] = Config.HIT_CHAR;
        if((res = Battleship.checkLost(board)) != false) {
            System.out.println("FAILED: Battleship.checkLost(board) != false, but " + res);
            passed--;
        }
        
        //Test 2
        board[0][2] = Config.HIT_CHAR;
        if((res = Battleship.checkLost(board)) != true) {
            System.out.println("FAILED: Battleship.checkLost(board) != true, but " + res);
            passed--;
        }
        
        // # of tests passed
        System.out.println("testcheckLost: Passed " + passed + " of " + numTests + " tests.");
        
    }
}
