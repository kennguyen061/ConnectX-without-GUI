package cpsc2150.connectX;

/**
 * Created by kennyn on 2/3/20.
 */

/**
 * Kenny Nguyen
 * CPSC 2150 Spring 2020
 * GameBoard.java
 * Kevin Plis
 */

import java.lang.String;


/**
 * @invariant ROWMIN <= row <= ROWMAX
 * @invariant 0 <= column <= COLMAX
*/

public class GameBoard extends AbsGameBoard {


    private int ROWMAX;
    private int COLMAX;

    private int NumToWin;

    private char board[][];


    /**
     * @pre: ROWMAX > 0
     * COLMAX > 0
     * NumToWin >= 3
    @post: Creates a Gameboard object
    */
    public GameBoard(int ROWMAX, int COLMAX, int NumToWin) {
        this.ROWMAX = ROWMAX;
        this.COLMAX = COLMAX;
        this.NumToWin = NumToWin;

        board = new char[ROWMAX][COLMAX];

        //Fills up the array with blank space
        for(int i = 0; i < ROWMAX; i++) {
            for(int j = 0; j < COLMAX; j++) {
                board[i][j] = ' ';
            }
        }
    }


    /**
     @post: Returns number of rows on board
     @return ROWMAX
     */
    public int getNumRows() {
        return ROWMAX;
    }


    /**
     @post: Returns number of columns on board
     @return COLMAX
     */
    public int getNumColumns() {
        return COLMAX;
    }


    /**
     @post: Returns number of markers in a row to win
     @return NumToWin
     */
    public int getNumToWin() {
        return NumToWin;
    }

    /**
      @param: p = player token representation
              c = column to place
      @pre: 0 <= c < COLMAX
      @post: board[i][c] = p
    */
    public void placeToken(char p, int c) {

        //Function ends if there is no free space
        if(checkIfFree(c) == false) {
            return;
        }

        //Checks each row in the column in the board until there is a free space
        else {
            for(int i = 0; i < ROWMAX; i++) {
                if(board[i][c] == ' ') {
                    //Places the marker and ends the loop
                    board[i][c] = p;
                    break;
                }
            }
        }

    }

    /**
      @param: pos = BoardPosition object
              p = character representation of player
      @post: returns true iff pinarow == NumToWin
    */
    public boolean checkHorizWin(BoardPosition pos,char p){
        //Checks only until COLMAX-NumToWin-1 to prevent an out of bounds error
        int upbounds = COLMAX-(NumToWin-1);


        for(int column = 0; column < upbounds; column++) {

            int pinarow = 0;
            for(int i = 0; i < NumToWin; ++i) {
                //Checks adjacent columns
                int icolaway = column+i;
                if(board[pos.getRow()][icolaway] == p) {
                    pinarow += 1;
                }

            }



            //if the characters in a row match the number to win, true is returned
            if(pinarow == NumToWin) {
                return true;
            }

        }


        //Returns false if the loop ends without a win
        return false;

    }

    /**
      @param: pos = BoardPosition object
              p = character representation of player
      @post: returns true iff pinarow == NumToWin
    */
    public boolean checkVertWin(BoardPosition pos, char p) {
        //Checks only until COLMAX-NumToWin-1 to prevent an out of bounds error
        int upbounds = ROWMAX-(NumToWin-1);

        for(int row = 0; row < upbounds; row++) {

            int pinarow = 0;
            for(int i = 0; i < NumToWin; i++) {
                //Checks adjacent columns
                int irowaway = row+i;
                if(board[irowaway][pos.getColumn()] == p) {
                    pinarow += 1;
                }

            }

            //if the characters in a row match the number to win, true is returned
            if(pinarow == NumToWin) {
                return true;
            }
        }


        //Returns false if the loop ends without a win
        return false;

    }


    /**
      @param: pos = BoardPosition object
              p = character representation of player
      @post: returns true iff pinarow == NumToWin
    */
    public boolean checkDiagWin(BoardPosition pos, char p) {
        //Checks top left to bottom right for a diagonal win
        for(int row = ROWMAX-1; row >= 0; row--) {
            for(int col = 0; col < COLMAX; col++) {

                int pinarow = 0;
                for(int i = 0; i < NumToWin; i++) {
                    int irowaway = row-i;
                    int icolaway = col+i;

                    //The next two for loops prevent out of bound errors
                    if(irowaway > ROWMAX-1 || irowaway < 0) {
                        continue;
                    }

                    if(icolaway > COLMAX-1 || icolaway < 0) {
                        continue;
                    }


                    if(board[irowaway][icolaway] == p) {
                        pinarow += 1;
                    }

                    //if the characters in a row match the number to win, true is returned
                    if(pinarow == NumToWin) {
                        return true;
                    }
                }
            }
        }

        //Now to check bottom left to top right for matches

        for(int row = 0; row < ROWMAX; row++) {
            for(int col = 0; col < COLMAX; col++) {


                int pinarow = 0;
                for(int i = 0; i < NumToWin; i++) {
                    int irowaway = row+i;
                    int icolaway = col+i;

                    //The next two for loops prevent out of bound errors
                    if(irowaway > ROWMAX-1 || irowaway < 0) {
                        continue;
                    }

                    if(icolaway > COLMAX-1 || icolaway < 0) {
                        continue;
                    }

                    if((board[irowaway][icolaway] == board[row][col]) && (board[row][col] != ' ')) {
                        pinarow += 1;
                    }

                    //if the characters in a row match the number to win, true is returned
                    if(pinarow == NumToWin) {
                        return true;
                    }
                }

                }
            }


        //Return false otherwise
        return false;
    }


    /**
      @param: pos = BoardPosition object
      @post: whatsAtPos = GameBoard[pos.getRow()][pos.getColumn()]
    */
    public char whatsAtPos(BoardPosition pos) {

        //Sets token equal to the character at pos
        char token = board[pos.getRow()][pos.getColumn()];

        return token;

    }

}

