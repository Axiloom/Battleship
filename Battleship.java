import java.util.Scanner;
import java.util.Random;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Battleship
// Files:           Battleship.java, TestBattleship.java, Config.java
// Course:          CS200, Spring, 2018
//
// Author:          John Bednarczyk
// Email:           jbednarczyk@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Joe Lessner
// Partner Email:   jalessner@wisc.edu
// Lecturer's Name: Marc Renault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


public class Battleship {

    /**
     * This method converts a String representing a base (or radix) 26 number into a decimal (or base 10) number. The String representation of the base 26 number uses the letters of the Latin alphabet to represent the 26 digits. That is, A represents 0, B represents 1, C 
     * represents 2, ..., Y represents 24,  and Z represents 25.
     *
     * A couple of examples:
       BAAA = 1 * 26^3 + 0 * 26^2 + 0 * 26^1 + 0 * 26^0 = 17576
     * ZERTY = 25 * 26^4 + 4 * 26^3 + 17 * 26^2 + 19 * 26^1 + 24 * 26^0 = 11506714
     *
     * For this method:
     *   - use Math.pow to calculate the powers of 26.
     *   - don't assume that the input is in any particular case; use toUpperCase().
     *   - don't check that the input is only 'A' to 'Z'.
     *   - calculate the value of each digit relative to 'A'.
     *   - start from either the first or last character, and calculate the exponent based on the
     *     index of each character.
     *
     * @param coord The coordinate value in base 26 as described above. 
     * @return The numeric representation of the coordinate.
     */
    public static int coordAlphaToNum(String coord) {
        
        //Variables
        coord = coord.toUpperCase();
        int count = 0;
        int x = 0;
        
        //Converts characters into numbers and saves number to an int
        for (int i = 0 ; i < coord.length() ; i++) {
            x = x + (int)Math.pow(26,(coord.length() - i - 1)) * ((int)(coord.charAt(count) - 65));
            count++;
        }

        return x; 
    }

    /**
     * This method converts an int value into a base (or radix) 26 number, where the digits are 
     * represented by the 26 letters of the Latin alphabet. That is, A represents 0, B represents 1,
     * C represents 2, ..., Y represents 24,  and Z represents 25. 
     * A couple of examples: 17576 is BAAA, 11506714 is ZERTY.
     *
     * The algorithm to convert an int to a String representing these base 26 numbers is as follows:
     * - Initialize res to the input integer
     * - The next digit is determined by calculating the remainder of res with respect to 26
     * - Convert this next digit to a letter based on 'A'
     * - Set res to the integer division of res and 26
     * - Repeat until res is 0
     *
     * @param coord The integer value to covert into an alpha coordinate.
     * @return The alpha coordinate in base 26 as described above. If coord is negative, an empty 
     *         string is returned.
     */
    public static String coordNumToAlpha(int coord) {
        
        //Variables
        int alphabetLetter = 0;
        char letter = '\n';
        String res = "";
        
        //Stores A to Z into an array
        char[] alphArr = new char[26];
        for (int i = 0 ; i < 26 ; i++) {
            alphArr[i] = (char)(i + 65);
        }
        
        //If negative, return empty string
        if (coord < 0) {
            return "";
        }
        
        //Stores letters to string
        while (coord >= 26){
            alphabetLetter = coord % 26;
            letter = alphArr[alphabetLetter];
            res = letter + res;
            coord = coord / 26;
        }
        
        letter = alphArr[coord];
        res = letter + res;
        
        return res;
    }

    /**
     * Prompts the user for an integer value, displaying the following:
     *     "Enter the valName (min to max): "
     * Note: There should not be a new line terminating the prompt. valName should contain the 
     * contents of the String referenced by the parameter valName. min and max should be the values 
     * passed in the respective parameters.
     *
     * After prompting the user, the method will read an int from the console and consume an entire
     * line of input. If the value read is between min and max (inclusive), that value is returned.
     * Otherwise, "Invalid value." terminated by a new line is output and the user is prompted 
     * again. 
     *
     * @param sc The Scanner instance to read from System.in.
     * @param valName The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param min The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String valName, int min, int max) {
        
        int value = 0;
                
        System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
        
        //Detects if character is an integer
        while (!sc.hasNextInt()){
            System.out.print("Invalid value.\n" + "Enter the " + valName + " (" + min + " to " + max + "): ");
            sc.nextLine(); //Resets Scanner
        }
        
        //Stores the integer to variable x
        value = sc.nextInt();
        sc.nextLine(); //Resets Scanner
        
        //Checks to see if (x < min) or (x > max)
        while (value < min || value > max) {
            System.out.print("Invalid value.\n" + "Enter the " + valName + " (" + min + " to " + max + "): ");
            while (!sc.hasNextInt()) {
                System.out.print("Invalid value.\n" + "Enter the " + valName + " (" + min + " to " + max + "): ");
                sc.nextLine(); //Resets Scanner
            }
            value = sc.nextInt();
            sc.nextLine(); //Resets Scanner
        }
        return value;
        }

    /**
     * Prompts the user for an String value, displaying the following:
     *     "Enter the valName (min to max): "
     * Note: There should not be a new line terminating the prompt. valName should contain the 
     * contents of the String referenced by the parameter valName. min and max should be the values 
     * passed in the respective parameters.
     *
     * After prompting the user, the method will read an entire line of input, trimming any trailing 
     * or leading whitespace. If the value read is (lexicographically ignoring case) between min and
     * max (inclusive), that value is returned. Otherwise, "Invalid value." terminated by a new line 
     * is output and the user is prompted again. 
     *
     * @param sc The Scanner instance to read from System.in.
     * @param valName The name of the value for which the user is prompted.
     * @param min The minimum acceptable String value (inclusive).
     * @param min The maximum acceptable String value (inclusive).
     * @return Returns the value read from the user.
     */
    public static String promptStr(Scanner sc, String valName, String min, String max) { // Rewrite to accept multiple characters
        
        //Variables
        String string;
        
        //Prompts the user and stores input into string
        System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
        string = sc.nextLine().trim().toUpperCase();
        
        //Convert string input to num value
        int stringAsNum = coordAlphaToNum(string);
        int newMin = coordAlphaToNum(min);
        int newMax = coordAlphaToNum(max);
        
        //Tests to see if the string is within max and min
        while ((stringAsNum < newMin) || (stringAsNum > newMax)) {
            System.out.println("Invalid value.");
            System.out.print("Enter the " + valName + " (" + min + " to " + max + "): ");
            string = sc.nextLine().trim().toUpperCase();
            
            //Takes the character and converts it into a string
            stringAsNum = coordAlphaToNum(string);
        }
        return string;
        
    }

    /**
     * Prompts the user for an char value. The prompt displayed is the contents of the String 
     * referenced by the prompt parameter. 
     * Note: There should not be a new line terminating the prompt. 
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character in lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        
        //Variables
        char character;
        String string;
        
        //Displays prompt and asks user to input char
        System.out.print(prompt);
        
        //Saves next line of text to a string
        string = sc.next();
        sc.nextLine();
        
        //Checks to see if char is empty and returns null if it is
        if (string.isEmpty()) {
            return '\n';
        }
        
        //Assigns the first character in string to character
        character = string.trim().toLowerCase().charAt(0);
    
        return character;
    }

    /**
     * Initializes a game board so that all the entries are Config.WATER_CHAR.
     *
     * @param board The game board to initialize.
     */
    public static void initBoard(char board[][]) {
        
        //Creates a board array
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                board[i][j] = Config.WATER_CHAR;
            }
        }
    }

    /**
     * Prints the game boards as viewed by the user. This method is used to print the 
     * game boards as the user is placing their ships and during the game play. 
     *
     * Some notes on the display:
     *   - Each column printed will have a width of Config.MAX_COL_WIDTH.
     *   - Each row is followed by an empty line.
     *   - The values in the headers and cells are to be right justified.
     *
     * @param board The board to print.
     * @param caption The board caption.
     */
    public static void printBoard(char board[][], String caption) {
        
        //Prints caption
        System.out.println(caption);
        
	//Variables
        int x = 65;
        int tracker = board[0].length;
        
        //Prints column header
        System.out.print("   ");
        
        //Prints corresponding column letter with stacking alphabetical letters
        if (tracker <= 26) {
            for (int i = 0 ; i < tracker ; i++) {
                for (int k = Config.MAX_COL_WIDTH ; k > 1 ; k--) {
                    System.out.print(" ");
                }
                System.out.print((char)(i + 65));
            }
        }
        else {
            for (int i = 0 ; i < 26 ; i++) {
                for (int k = Config.MAX_COL_WIDTH ; k > 1 ; k--) {
                    System.out.print(" ");
                }
                System.out.print((char)(i + 65));
                tracker--;
            }
        }
        
        for (int i = 0 ; (i < (board[0].length / 26)) && (board[0].length % 26 != 0) ; i++) {
            
            if (tracker > 26) {
                for (int j = 0 ; j < 26 ; j++) {
                    for (int y = Config.MAX_COL_WIDTH ; y > 2 ; y--) {
                        System.out.print(" ");
                    }
                    System.out.print((char)(i + 66));
                    System.out.print((char)(x));
                    x++;
                    tracker--;
                }
                x = 65;
            }
            else {
                for (int z = 0 ; z < tracker ; z++) {
                    for (int y = Config.MAX_COL_WIDTH ; y > 2 ; y--) {
                        System.out.print(" ");
                    }
                    System.out.print((char)(i + 66));
                    System.out.print((char)(z + 65));
                }
            }
        }
        System.out.println();
        
        //Prints board
        for (int i = 0 ; i < board.length ; i++) {
            for (int k = Config.MAX_COL_WIDTH ; k > 1 ; k--) {
                System.out.print(" ");
            }
            
            //Prints out corresponding number
            System.out.print(i);
            
            for (int j = 0 ; j < board[0].length ; j++) {
                for (int k = Config.MAX_COL_WIDTH ; k > 1 ; k--) {
                    System.out.print(" ");
                }
                System.out.print(board[i][j]);
            }
            System.out.println();
            System.out.println();
        }
    }
    
    /**
     * Determines if a sequence of cells of length len in a game board is clear or not. This is used
     * to determine if a ship will fit on a given game board. The x and y coordinates passed in as 
     * parameters represent the top-left cell of the ship when considering the grid.
     * 
     * @param board The game board to search.
     * @param xcoord The x-coordinate of the top-left cell of the ship.
     * @param ycoord The y-coordinate of the top-left cell of the ship.
     * @param len The length of the ship.
     * @param dir true if the ship will be vertical, otherwise horizontal
     * @return 1 if the cells to be occupied by the ship are all Config.WATER_CHAR, 
     *         -1 if the cells to be occupied are not Config.WATER_CHAR, and
     *         -2 if the ship would go out-of-bounds of the board.
     */
    public static int checkWater(char board[][], int xcoord, int ycoord, int len, boolean dir) {
        
        //This will check to see if a vertical ship will fit within the board
        if (dir) {
            if ((board.length - ycoord) < len) {
                return -2; //Will return -2 if the ship would go out-of-bounds on the board
            }
            for (int i = ycoord; i < (ycoord + len) ; i++) {
                if (board[i][xcoord] != Config.WATER_CHAR) {
                    return -1; //Will return -1 if one of the array's indexes does not have Config.WATER_CHAR
                }
            }
            return 1; //Will return 1 if all the array's indexes have Config.WATER_CHAR
        }
        
        //This will check to see if the horizontal ship will fit within the board
        if (((board[0].length + 1) - xcoord) < len) {
            return -2; //Will return -2 if the ship would go out-of-bounds on the board
        }
        for (int i = xcoord; i <= (xcoord + (len - 1)) ; i++) {
            
            if (board[ycoord][i] != Config.WATER_CHAR) {
                return -1; //Will return -1 if one of the array's indexes does not have Config.WATER_CHAR
            }
        }
        return 1; //Will return 1 if all the array's indexes have Config.WATER_CHAR
    }

    /**
     * Checks the cells of the game board to determine if all the ships have been sunk.
     *
     * @param board The game board to check.
     * @return true if all the ships have been sunk, false otherwise.
     */
    public static boolean checkLost(char board[][]) {
        
	//Variables
        int counter = 0;
        int totalSpaces = board.length * board[0].length;
        
        for (int i = 0 ; i < board.length ; i++) {
            for (int j = 0 ; j < board[0].length ; j++) {
                if (board[i][j] == Config.MISS_CHAR || board[i][j] == Config.HIT_CHAR || board[i][j] == Config.WATER_CHAR) {
                    counter++;
                }
            }
        }
        
        if (counter == totalSpaces) {
            return true;
        }
        
        return false;
    }
    /**
     * Places a ship into a game board. The coordinate passed in the parameters xcoord and ycoord
     * represent the top-left coordinate of the ship. The ship is represented on the game board by
     * the Character representation of the ship id. (For this method, you can assume that the id
     * parameter will only be values 1 through 9.)
     *
     * @param board The game board to search.
     * @param xcoord The x-coordinate of the top-left cell of the ship.
     * @param ycoord The y-coordinate of the top-left cell of the ship.
     * @param len The length of the ship.
     * @param dir true if the ship will be vertical, otherwise horizontal.
     * @param id The ship id, assumed to be 1 to 9.
     * @return false if the ship goes out-of-bounds of the board, true otherwise.
     */

    public static boolean placeShip(char board[][], int xcoord, int ycoord, int len, boolean dir, int id) {
        
	//Variables
        char shipId = (char)(id + 48);
        
        while (checkWater(board, xcoord, ycoord, len, dir) == 1) {
            
            //Vertical placement
            if (dir) {
                for (int i = ycoord ; i < (ycoord + len) ; i++) {
                    board[i][xcoord] = shipId;
                }
                return true;
            }
            
            //Horizontal placement
            else {
                for (int i = xcoord ; i < (xcoord + len) ; i++) {
                    board[ycoord][i] = shipId;
                }
                return true;
            }
        }
        return false;
    }
    /**
     * Randomly attempts to place a ship into a game board. The random process is as follows:
     *   1 - Pick a random boolean, using rand. True represents vertical, false horizontal.
     *   2 - Pick a random integer, using rand, for the x-coordinate of the top-left cell of the 
     *       ship. The number of integers to choose from should be calculated based on the width of
     *       the board and length of the ship such that the placement of the ship won't be 
     *       out-of-bounds.
     *   3 - Pick a random integer, using rand, for the y-coordinate of the top-left cell of the 
     *       ship. The number of integers to choose from should be calculated based on the height of
     *       the board and length of the ship such that the placement of the ship won't be 
     *       out-of-bounds.
     *   4 - Verify that this random location can fit the ship without intersecting another ship 
     *       (checkWater method). If so, place the ship with the placeShip method.
     *
     * It is possible for the configuration of a board to be such that a ship of a given length may
     * not fit. So, the random process will be attempted at most Config.RAND_SHIP_TRIES times.
     * 
     * @param board The game board to search.
     * @param len The length of the ship.
     * @param id The ship id, assumed to be 1 to 9..
     * @param rand The Random object.
     * @return true if the ship is placed successfully, false otherwise.
     */
    
    public static boolean placeRandomShip(char board[][], int len, int id, Random rand) {
        
    	//Variables
        int placementTest = 0; 
        int xcoord;
        int ycoord;
        
        for (int i = 0 ; i < Config.RAND_SHIP_TRIES ; i++) {
            
            boolean dir = rand.nextBoolean();
            
            if (dir == true) {
                
                //Gets random x-coordinate that fits on board
                xcoord = rand.nextInt(board[0].length) + 0;
                
                //Gets random y-coordinate that fits on board
                if (len > board.length) {
                    ycoord = rand.nextInt(1);
                }
                else {
                    ycoord = rand.nextInt((board.length  - len) + 1) + 0;
                }
                
                //Checks to see if the ship can be placed
                placementTest = checkWater(board, xcoord, ycoord, len, dir);
                
                //Places ship as long as it passes test
                if (placementTest == 1) {
                    placeShip(board, xcoord, ycoord, len, dir, id);
                    return true;
                }
            }
            else {
                
                //Gets random x-coordinate that fits on board
                if (len > board[0].length) {
                    xcoord = rand.nextInt(1);
                }
                else {
                    xcoord = rand.nextInt((board[0].length + 1) - len) + 0;
                }

                //Gets random y-coordinate that fits on board
                ycoord = rand.nextInt(board.length) + 0;
                
                //Checks to see if the ship can be placed
                placementTest = checkWater(board, xcoord, ycoord, len, dir);
                
                //Places ship as long as it passes test
                if (placementTest == 1) {
                    placeShip(board, xcoord, ycoord, len, dir, id);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * This method interacts with the user to place a ship on the game board of the human player and
     * the computer opponent. The process is as follows:
     *   1 - Print the user primary board, using the printBoard.
     *   2 - Using the promptChar method, prompt the user with "Vertical or horizontal? (v/h) ".
     *       A response of v is interpreted as vertical. Anything else is assumed to be horizontal.
     *   3 - Using the promptInt method, prompt the user for an integer representing the 
     *       "ship length", where the minimum ship length is Config.MIN_SHIP_LEN and the maximum 
     *       ship length is width or height of the game board, depending on the input of the user 
     *       from step 1.
     *   4 - Using the promptStr method, prompt the user for the "x-coord". The maximum value
     *       should be calculated based on the width of the board and the length of the ship. You 
     *       will need to use the coordAlphaToNum and coordNumToAlpha methods to covert between int
     *       and String values of coordinates.
     *   5 - Using the promptInt method, prompt the user for the "y-coord". The maximum value
     *       should be calculated based on the width of the board and the length of the ship.
     *   6 - Check if there is space on the board to place the ship. 
     *     6a - If so:
     *             - Place the ship on the board using placeShip. 
     *             - Then, call placeRandomShip to place the opponents ships of the same length.
     *             - If placeRandomShip fails, print out the error message (terminated by a new 
     *               line): "Unable to place opponent ship: id", where id is the ship id, and 
     *               return false.
     *     6b - If not:
     *             - Using promptChar, prompt the user with "No room for ship. Try again? (y/n): "
     *             - If the user enters a 'y', restart the process at Step 1. 
     *             - Otherwise, return false.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param boardPrime The human player board.
     * @param boardOpp The opponent board.
     * @param id The ship id, assumed to be 1 to 9.
     * @param rand The Random object.
     * @return true if ship placed successfully by player and computer opponent, false otherwise.
     */
    public static boolean addShip(Scanner sc, char boardPrime[][], char boardOpp[][], int id, Random rand) {
        
        //Variables
    	boolean dir = true; //true = 'v', false = 'h', Needed for checkWater method
    	char direction = '\0';
    	int shipLength = 0;
    	String xcoordStr = "";
    	int xcoord = 0;
    	int ycoord = 0;
    	int shipCheck = 0; //value returned by checkWater method for checking if ship can fit
        boolean testRandShip;
        char restartQuestion;
        
        //Places number of ships based on id input
        for (int i = 1 ; i <= id ; i++) {
            
            //Prints boardPrime
            printBoard(boardPrime, "My Ships:");
            
            //Keeps asking the user for 
            while (shipCheck != 1) {
                
          	 	//Prompts for vertical or horizontal placement via promptChar method
          	 	direction = promptChar(sc, "Vertical or horizontal? (v/h): ");
          	 
          	 	if (direction == 'v') {
          		 	dir = true;
          	 	}
          	 	else {
          		 	dir = false;
          	 	}
        
        	 	//Vertical placement
        	 	if (dir) {
        
                    //Prompts for ship length using promptInt method
            		shipLength = promptInt(sc, "ship length", Config.MIN_SHIP_LEN, boardPrime.length);
                    
                    //Prompts for x-coordinate using promptStr method
                    xcoordStr = promptStr(sc, "x-coord", "A", coordNumToAlpha(boardPrime[0].length - 1));
                    xcoord = coordAlphaToNum(xcoordStr);
                    
                    //Prompts for y-coordinate using promptInt method
                    ycoord = promptInt(sc, "y-coord", 0, boardPrime.length - shipLength);
                    
                    //Checks if ship can fit on board
                    shipCheck = checkWater(boardPrime, xcoord, ycoord, shipLength, dir);
                    
                    //Reruns loop if ship does not fit
                    if (shipCheck == 1) {
                    
                        //Places ship on boardPrime if ship fits
                        placeShip(boardPrime, xcoord, ycoord, shipLength, dir, i);
                        
                        //Places random ship on boardOpp
                        testRandShip = placeRandomShip(boardOpp, shipLength, i, rand);
                        
                        //Tests
                        if (!testRandShip) {
                            System.out.println("Unable to place opponent ship: " + i);
                            return false;
                        }
                    }
                    else {
                        restartQuestion = promptChar(sc, "No room for ship. Try again? (y/n): ");
                        if (restartQuestion != 'y') {
                            return false;
                        }
                    }
        	 	}
        	 	
        	 	//Horizontal placement
        	 	else if (!dir) {
        
                    //Prompts for ship length using promptInt method
            		shipLength = promptInt(sc, "ship length", Config.MIN_SHIP_LEN, boardPrime[0].length);
        
                    //Prompts for x-coordinate using promptStr method
                    xcoordStr = promptStr(sc, "x-coord", "A", coordNumToAlpha(boardPrime[0].length - shipLength));
            		xcoord = coordAlphaToNum(xcoordStr);
        
                    //Prompts for y-coordinate using promptInt method
                    ycoord = promptInt(sc, "y-coord", 0, boardPrime.length - 1);
                    
                    
                    //Checks if ship can fit on board
                    shipCheck = checkWater(boardPrime, xcoord, ycoord, shipLength, dir);
                    
                    //Reruns loop if ship does not fit
                    if (shipCheck == 1) {
                        
                        //Places ship on boardPrime if ship fits
                        placeShip(boardPrime, xcoord, ycoord, shipLength, dir, i);
                        
                        //Places random ship on boardOpp
                        testRandShip = placeRandomShip(boardOpp, shipLength, i, rand);
                        
                        //Tests 
                        if (!testRandShip) {
                            System.out.println("Unable to place opponent ship: " + i);
                            return false;
                        }
                    }
                    else {
                        restartQuestion = promptChar(sc, "No room for ship. Try again? (y/n): ");
                        if (restartQuestion != 'y') {
                            return false;
                        }
                    }
        	    }
            }
            shipCheck = 0;
	    direction = '\0';
        }
        return true;
    }

    /**
     * Checks the state of a targeted cell on the game board. This method does not change the 
     * contents of the game board.
     *
     * @return  3 if the cell was previously targeted.
     *          2 if the shot would be a miss.
     *          1 if the shot would be a hit.
     *         -1 if the shot is out-of-bounds.
     */
    public static int takeShot(char[][] board, int x, int y) {
        
        //Checks to see if the shot was out-of-bounds
        if ((x > board[0].length || x < 0) || (y > board.length || y < 0)) {
            return -1;
        }
        
        //Checks to see if it was previously targeted
        if (board[y][x] == Config.MISS_CHAR || board[y][x] == Config.HIT_CHAR) {
            return 3;
        }
        
        //Checks to see if it was a miss
        
        if (board[y][x] == Config.WATER_CHAR) {
            return 2;
        }
        
        //Will return 1 if it was a hit
        return 1;
    }

    /**
     * Interacts with the user to take a shot. The procedure is as follows:
     *   1 - Using the promptStr method, prompt the user for the "x-coord shot". The maximum value
     *       should be based on the width of the board. You will need to use the coordAlphaToNum 
     *       and coordNumToAlpha methods to covert between int and String values of coordinates.
    
     *   2 - Using the promptInt method, prompt the user for the "y-coord shot". The maximum value
     *       should be calculated based on the width of the board.
    
     *   3 - Check the shot, using the takeShot method. If it returns:
     *        -1: Print out an error message "Coordinates out-of-bounds!", terminated by a new line.
     *         3: Print out an error message "Shot location previously targeted!", terminated by a
     *            new line.
     *         1 or 2: Update the cells in board and boardTrack with Config.HIT_CHAR or 
     *                 Config.MISS_CHAR accordingly.
     *   This process should repeat until the takeShot method returns 1 or 2.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param board The computer opponent board (containing the ship placements).
     * @param boardTrack The human player tracking board.
     */
    public static void shootPlayer(Scanner sc, char[][] board, char[][] boardTrack) {
        
        //Gets x-coordinate of shot
        String xcoordTemp = promptStr(sc, "x-coord shot", coordNumToAlpha(0), coordNumToAlpha(board[0].length - 1));
        int xcoord = coordAlphaToNum(xcoordTemp);
        
        //Gets y-coordinate of shot
        int ycoord = promptInt(sc, "y-coord shot", 0, board.length - 1);
        
        //Checks shot
        int shotDecision = takeShot(board, xcoord, ycoord);
        
        while (shotDecision == -1) {
            System.out.println("Coordinates out-of-bounds!");
            
            //Gets x-coordinate of shot
            xcoordTemp = promptStr(sc, "x-coord shot", coordNumToAlpha(0), coordNumToAlpha(board[0].length - 1));
            xcoord = coordAlphaToNum(xcoordTemp);
            
            //Gets y-coordinate of shot
            ycoord = promptInt(sc, "y-coord shot", 0, board.length - 1);
            
            shotDecision = takeShot(board, xcoord, ycoord);
        }
        while (shotDecision == 3) {
            System.out.println("Shot location previously targeted!");
            
            //Gets x-coordinate of shot
            xcoordTemp = promptStr(sc, "x-coord shot", coordNumToAlpha(0), coordNumToAlpha(board[0].length - 1));
            xcoord = coordAlphaToNum(xcoordTemp);
            
            //Gets y-coordinate of shot
            ycoord = promptInt(sc, "y-coord shot", 0, board.length - 1);
            
            shotDecision = takeShot(board, xcoord, ycoord);
        }
        
        //Places shot if it was a hit or a miss
        if (shotDecision == 1) {
            boardTrack[ycoord][xcoord] = Config.HIT_CHAR;
            board[ycoord][xcoord] =Config.HIT_CHAR;
        }
        else {
            boardTrack[ycoord][xcoord] = Config.MISS_CHAR;
            board[ycoord][xcoord] = Config.MISS_CHAR;
        }
        
        checkLost(board);
    }

    /**
     * Takes a random shot on the game board. The random process works as follows:
     *   1 - Pick a random valid x-coordinate
     *   2 - Pick a random valid y-coordinate
     *   3 - Check the shot, using the takeShot method. 
     *   This process should repeat until the takeShot method returns 1 or 2, then update the cells
     *   in board with Config.HIT_CHAR or Config.MISS_CHAR accordingly.
     *
     * Note: Unlike the placeRandomShip method, this method continues until it is successful. This
     * may seem risky, but in this case the random process will terminate (find an untargeted cell)
     * fairly quickly. For more details, see the appendix of the Big Program 1 subject.
     *
     * @param rand The Random object.
     * @param board The human player game board.
     */
    public static void shootComputer(Random rand, char[][] board) {
        
        //Gets random x-coord of shot
        int xcoord = rand.nextInt(board[0].length);
        
        //Gets random y-coord of shot
        int ycoord = rand.nextInt(board.length);
        
        //Checks to see if shot is valid
        int shotDecision = takeShot(board, xcoord, ycoord);
        
        //If the shot is not valid, generate another x and y coord
        while (shotDecision == 3 || shotDecision == -1) {
            
            //Get new random x-coord of shot
            xcoord = rand.nextInt(board[0].length);
            
            //Get new random y-coord of shot
            ycoord = rand.nextInt(board.length);
            
            //Checks to see if shot is valid
            shotDecision = takeShot(board, xcoord, ycoord);
        }
        
        //Places shot if it was a hit or a miss
        if (shotDecision == 1) {
            board[ycoord][xcoord] = Config.HIT_CHAR;
        }
        else {
            board[ycoord][xcoord] = Config.MISS_CHAR;
        }
    }

    /**
     * This is the main method for the Battleship game. It consists of the main game and play again
     * loops with calls to the various supporting methods. When the program launches (prior to the 
     * play again loop), a message of "Welcome to Battleship!", terminated by a newline, is 
     * displayed. After the play again loop terminiates, a message of "Thanks for playing!", 
     * terminated by a newline, is displayed.
     *
     * The Scanner object to read from System.in and the Random object with a seed of Config.SEED 
     * will be created in the main method and used as arguments for the supporting methods as 
     * required.
     *
     * Also, the main method will require 3 game boards to track the play:
     * - One for tracking the ship placement of the user and the shots of the computer, called the
     *   primary board with a caption of "My Ship".
     * - One for displaying the shots (hits and misses) taken by the user, called the tracking board
     *   with a caption of "My Shots"; and one for tracking the ship placement of the computer and 
     *   the shots of the user. 
     * - The last board is never displayed, but is the primary board for the computer and is used to
     *   determine when a hit or a miss occurs and when all the ships of the computer have been 
     *   sunk. 
     * Notes:
     *   - The size of the game boards are determined by the user input.  
     *   - The game boards are 2d arrays that are to be viewed as row-major order. This means that 
     *     the first dimension represents the y-coordinate of the game board (the rows) and the 
     *     second dimension represents the x-coordinate (the columns).
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        
        //Creates a scanner object
        Scanner sc = new Scanner(System.in);
        
        //Creates a random object
        Random rand = new Random(Config.SEED);
        
        //Variables
        boolean playAgain = true;
        char selection;
    
        System.out.println("Welcome to Battleship!");
        
        //Loops until user inputs 'n' to stop playing
        while (playAgain) {
            
            //Prompts for initiating board dimensions
            int boardHeight = promptInt(sc, "board height", Config.MIN_HEIGHT, Config.MAX_HEIGHT);
            int boardWidth = promptInt(sc, "board width", Config.MIN_WIDTH, Config.MAX_WIDTH);
            
            //Creates multi-dimensional array for 3 different boards
        
            char boardPrime[][] = new char[boardHeight][boardWidth]; //Primary Board
            char boardOpp[][] = new char[boardHeight][boardWidth];   //Computer Board (never displayed)
            char boardTrack[][] = new char[boardHeight][boardWidth]; //Tracking Board
            
            //Initializes boardPrime, boardOpp, and boardTrack
            initBoard(boardPrime);
            initBoard(boardOpp);
            initBoard(boardTrack);
            System.out.println();
            
            //Prompts for number of ships for board
            int id = promptInt(sc, "number of ships", Config.MIN_SHIPS, Config.MAX_SHIPS);
            
            //Adds ships to boards and checks for errors
            if (!addShip(sc, boardPrime, boardOpp, id, rand)) {
                selection = promptChar(sc, "Error adding ships. Restart game? (y/n): ");
                if (selection == 'y') {
                    continue;
                }
                else {
                    System.out.println("Thanks for playing!");
                    playAgain = false;
                }
            }
            
            while (!(checkLost(boardOpp) || checkLost(boardPrime))){
                
                //Prints boardPrime and boardTrack
                printBoard(boardPrime, "My Ships:");
                printBoard(boardTrack, "My Shots:");
                
                //User takes a shot
                shootPlayer(sc, boardOpp, boardTrack);
                
                //Checks to see if user won
                checkLost(boardOpp);
                
                if (checkLost(boardOpp)) {
                    continue;
                }
                
                //Computer takes a shot
                shootComputer(rand, boardPrime);
                
                //Checks to see if computer won
                checkLost(boardPrime);
                
                if (checkLost(boardPrime)) {
                    continue;
                }
            }
            
            if (checkLost(boardOpp)) {
		System.out.println("Congratulations, you sunk all the computer's ships!");
                printBoard(boardPrime, "My Ships:");
                printBoard(boardTrack, "My Shots:");
            }
            else if (checkLost(boardPrime)) {
		System.out.println("Oh no! The computer sunk all your ships!");
                printBoard(boardPrime, "My Ships:");
                printBoard(boardTrack, "My Shots:");
            }
            

            //Asks the user if they would like to play again
            selection = promptChar(sc, "Would you like to play again? (y/n): ");
            if (selection == 'y') {
                continue;
            }
            else {
                System.out.println("Thanks for playing!");
                playAgain = false;
            }
            
        } 
    
    }

}
