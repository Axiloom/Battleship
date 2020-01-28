/**
 * This class contains the constants used in the Battleship program. These constants may be changed
 * when testing. So, your program should use the constants, not the values.
 * 
 * @author Marc Renault
 */
public class Config {

    /**
     * Minimum and maximum values used in the program
     */
    public static final int MIN_WIDTH       = 1;   //Minimum number of columns
    public static final int MAX_WIDTH       = 675; //Maximum number of columns
    public static final int MIN_HEIGHT      = 1;   //Minimum number of rows
    public static final int MAX_HEIGHT      = 99;  //Maximum number of rows
    public static final int MAX_COL_WIDTH   = 3;   //Maximum number of characters per column 
    public static final int MIN_SHIPS       = 1;   //Minimum number of ships
    public static final int MAX_SHIPS       = 9;   //Maximum number of ships
    public static final int MIN_SHIP_LEN    = 1;   //Minimum ship length

    /**
     * Character values for displaying the different statuses of the game board cells.
     */
    public static final char WATER_CHAR = '~'; // Water character (not yet targeted)
    public static final char HIT_CHAR   = '*'; // Hit character
    public static final char MISS_CHAR  = '@'; // Miss character

    /**
     * Constants for the random processes.
     */
    public static final long SEED            = 1234; // The random seed
    public static final int  RAND_SHIP_TRIES = 20;   // Maximum number of tries to place a ship
    
}

