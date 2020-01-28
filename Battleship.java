import java.util.Scanner;
import java.util.Random;

public class Battleship {

    /**
     * This method converts a String representing a base (or radix) 26 number into a decimal (or base 10) number.
     *
     * @param coord The coordinate value in base 26.
     * @return The numeric representation of the coordinate.
     */
    public static int coordAlphaToNum(String coord) {

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
     * represented by the 26 letters of the Latin alphabet.
     *
     * @param coord The integer value to covert into an alpha coordinate.
     * @return The alpha coordinate in base 26. If coord is negative, an empty string is returned.
     */
    public static String coordNumToAlpha(int coord) {

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
     * Prompts the user for an integer value.
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
     * Prompts the user for an String value.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param valName The name of the value for which the user is prompted.
     * @param min The minimum acceptable String value (inclusive).
     * @param min The maximum acceptable String value (inclusive).
     * @return Returns the value read from the user.
     */
    public static String promptStr(Scanner sc, String valName, String min, String max) { // Rewrite to accept multiple characters

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
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If 
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {

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
     * @param board The board to print.
     * @param caption The board caption.
     */
    public static void printBoard(char board[][], String caption) {

        int x = 65;
        int tracker = board[0].length;

        //Prints caption
        System.out.println(caption);
        
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

        int counter = 0;
        int totalSpaces = board.length * board[0].length;

        for (char[] aBoard : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (aBoard[j] == Config.MISS_CHAR || aBoard[j] == Config.HIT_CHAR || aBoard[j] == Config.WATER_CHAR) {
                    counter++;
                }
            }
        }

        return counter == totalSpaces;

    }

    /**
     * Places a ship into a game board. The coordinate passed in the parameters xcoord and ycoord
     * represent the top-left coordinate of the ship. The ship is represented on the game board by
     * the Character representation of the ship id.
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

        char shipId = (char)(id + 48);
        
        if (checkWater(board, xcoord, ycoord, len, dir) == 1) {
            
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
     * Randomly attempts to place a ship into a game board.
     * 
     * @param board The game board to search.
     * @param len The length of the ship.
     * @param id The ship id, assumed to be 1 to 9..
     * @param rand The Random object.
     * @return true if the ship is placed successfully, false otherwise.
     */
    public static boolean placeRandomShip(char board[][], int len, int id, Random rand) {

        int placementTest = 0; 
        int xcoord;
        int ycoord;
        
        for (int i = 0 ; i < Config.RAND_SHIP_TRIES ; i++) {
            
            boolean dir = rand.nextBoolean();
            
            if (dir) {
                
                //Gets random x-coordinate that fits on board
                xcoord = rand.nextInt(board[0].length);
                
                //Gets random y-coordinate that fits on board
                if (len > board.length) {
                    ycoord = rand.nextInt(1);
                }
                else {
                    ycoord = rand.nextInt((board.length  - len) + 1);
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
                    xcoord = rand.nextInt((board[0].length + 1) - len);
                }

                //Gets random y-coordinate that fits on board
                ycoord = rand.nextInt(board.length);
                
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
     * the computer opponent.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param boardPrime The human player board.
     * @param boardOpp The opponent board.
     * @param id The ship id, assumed to be 1 to 9.
     * @param rand The Random object.
     * @return true if ship placed successfully by player and computer opponent, false otherwise.
     */
    public static boolean addShip(Scanner sc, char boardPrime[][], char boardOpp[][], int id, Random rand) {

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

                dir = direction == 'v';
        
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
        	 	else {
        
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
        }
        return true;
    }

    /**
     * Checks the state of a targeted cell on the game board.
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
     * Interacts with the user to take a shot.
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
     * Takes a random shot on the game board.
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
     * This is the main method for the Battleship game.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {

        boolean playAgain = true;
        char selection;

        //Creates a scanner object
        Scanner sc = new Scanner(System.in);
        
        //Creates a random object
        Random rand = new Random(Config.SEED);
    
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
