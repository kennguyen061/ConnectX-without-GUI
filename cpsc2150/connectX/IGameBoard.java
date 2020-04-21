package cpsc2150.connectX;

/**
 * Created by kennyn on 2/21/20.
 */

/**
 * Kenny Nguyen
 * CPSC 2150 Spring 2020
 * IGameBoard.java
 * Kevin Plis
 */


/**
 * Holds information about the GameBoard and provides functions on checking the board for wins
 * Defines: ROWMAX = Maximum number of Rows on the board
 *          COLMAX = Maximum number of Columns on the board
 *          NumToWin = Required number of tokens in a row to win
 *
 *
 * Initialization Ensures: a GameBoard object will be created
 * Constraints 0 <= rows <= ROWMAX
 *             0 <= columns <= COLMAX
 *             0 < NumToWin
 *             ROWMAX > 0
 *             COLMAX > 0
 *
 */
public interface IGameBoard {

    /**
     @post: Returns number of rows on board
     */
    public int getNumRows();

    /**
     @post: Returns number of columns on board
     */
    public int getNumColumns();

    /**
     @post: Returns number of markers in a row to win
     */
    public int getNumToWin();

    /**
     @param: c = column to check
     @pre: 0 <= c < COLMAX
     @post: true iff tokencount == ROWMAX
     */
    default public boolean checkIfFree(int c) {
        int tokencount = 0;

        for(int i = 0; i < this.getNumRows(); i++) {
            //Creates a BoardPosition object
            BoardPosition pos;
            pos = new BoardPosition(i, c);
            if(this.whatsAtPos(pos) != ' ') {
                //Increments a token count if there is a token in the column
                tokencount += 1;
            }
        }

        //If there are tokens in all of the row spots in the column, returns false
        if(tokencount == this.getNumRows()) {
            return false;
        }

        //Otherwise returns true stating that there is room left
        else {
            return true;
        }
    }

    /**
     @param: c = column to check
     @pre: 0 <= c < COLMAX, placeToken must be called before calling checkForWin
     @post: true if checkHorizWin(currentpos, p) || checkVertWin(currentpos, p) || checkDiagWin(currentpos, p)
     */
    default public boolean checkForWin(int c) {

        //i will represent the last object with a marker
        for(int i = 0; i < this.getNumRows(); i++) {

            BoardPosition pos;
            pos = new BoardPosition(i, c);

            //If the board is blank at i,c
            if (this.whatsAtPos(pos) == ' ') {
                //Creates a new BoardPosition at i, c
                BoardPosition currentpos;
                currentpos = new BoardPosition(i-1, c);

                //creates a character token equal to the token at pos one row lower
                char p = whatsAtPos(currentpos);

                //Checks every possible win
                if (checkHorizWin(currentpos, p)) {
                    return true;
                }

                if (checkVertWin(currentpos, p)) {
                    return true;
                }

                if (checkDiagWin(currentpos, p)) {
                    return true;
                }

                //Gets out of loop
                break;
            }
        }

        //Returns false otherwise since there is no win
        return false;
    }



    /**
     @param: p = player token representation
     c = column to place
     @pre: 0 <= c <= COLMAX
     @post: places token in board
     */
    public void placeToken(char p, int c);

    /**
     @param: pos = BoardPosition object
     p = character representation of player
     @post: returns true if NumToWin tokens are found horizontally consecutively, false otherwise
     */
    public boolean checkHorizWin(BoardPosition pos, char p);

    /**
     @param: pos = BoardPosition object
     p = character representation of player
     @post: returns true if NumToWin tokens are found vertically consecutively, false otherwise
     */
    public boolean checkVertWin(BoardPosition pos, char p);

    /**
     @param: pos = BoardPosition object
     p = character representation of player
     @post: returns true if NumToWin tokens are found diagonally consecutively, false otherwise
     */
    public boolean checkDiagWin(BoardPosition pos, char p);

    /**
     @param: pos = BoardPosition object
     @post: character at pos is returned
     */
    public char whatsAtPos(BoardPosition pos);


    /**
     @param: pos = BoardPosition object
     player = character representation of player token
     @post: true iff player == token

     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {
        //Sets token equal to the character at pos
        char token = whatsAtPos(pos);

        //If the player is at that position, returns true, otherwise false
        if(token == player) {
            return true;
        }

        else{
            return false;
        }
    }


    /**
     * @return a string representation of the GameBoard
     * @post Ex]
     *       toString = "|0|1|2|3|4|5|6|" with filled columns
     *                   | | | | | | | |
     *                   | | | | | | | |
     *                   | | | | | | | |
     *                   | | | | | | | |
     *                   | | | | | | | |
     *                  | | | | | | | |
     @override
     */
    public String toString();

    /**
     * @pre: checkforWin MUST be called before checkTie()
     @post: true iff placedtokens == maxtokens
     */
    default public boolean checkTie() {

        //Int to represent all the placed tokens
        int placedtokens = 0;

        //Represents max tokens
        int maxtokens = this.getNumColumns()*this.getNumRows();



        for(int i = 0; i < this.getNumRows(); i++) {
            for(int j = 0; j < this.getNumColumns(); j++) {
                BoardPosition pos;
                pos = new BoardPosition(i,j);
                if(this.whatsAtPos(pos) != ' ') {
                    placedtokens += 1;
                }
            }
        }

        if(placedtokens == maxtokens) {
            return true;
        }

        else {
            return false;
        }
    }

}