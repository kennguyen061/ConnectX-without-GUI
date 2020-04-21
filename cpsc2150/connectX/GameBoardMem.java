package cpsc2150.connectX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Kenny Nguyen
 * CPSC 2150 Spring 2020
 * GameBoard.java
 * Kevin Plis
 */

/**
 * @invariant ROWMIN <= row <= ROWMAX
 * @invariant 0 <= column <= COLMAX
 */

public class GameBoardMem extends AbsGameBoard {

    private int ROWMAX;
    private int COLMAX;

    private int NumToWin;

    Map<Character, List<BoardPosition>> board = new HashMap<>();

    /**
     * @pre: ROWMAX > 0
     * COLMAX > 0
     * NumToWin >= 3
     * @post: Creates a Gameboard object
     */
    public GameBoardMem(int ROWMAX, int COLMAX, int NumToWin) {
        this.ROWMAX = ROWMAX;
        this.COLMAX = COLMAX;
        this.NumToWin = NumToWin;

    }


    /**
     * @return ROWMAX
     * @post: Returns number of rows on board
     */
    public int getNumRows() {
        return ROWMAX;
    }


    /**
     * @return COLMAX
     * @post: Returns number of columns on board
     */
    public int getNumColumns() {
        return COLMAX;
    }


    /**
     * @return NumToWin
     * @post: Returns number of markers in a row to win
     */
    public int getNumToWin() {
        return NumToWin;
    }

    /**
     * @param: p = player token representation
     * c = column to place
     * @pre: 0 <= c <= COLMAX
     * @post:   List<BoardPosition> plist.add(BoardPosition pos(i,c))
     *          board.put(p, plist)
     */
    public void placeToken(char p, int c) {

        //Goes through the height of the board
        for (int i = 0; i < ROWMAX; i++) {
            //Creates a boardposition object at the current position
            BoardPosition pos;
            pos = new BoardPosition(i, c);

            //If the currentposition is a blankspace
            if (this.whatsAtPos(pos) == ' ') {

                //If the key is not already created, it is made, usually for a player's first turn
                if (board.containsKey(p) == false) {
                    //Creates a new list for the p character
                    List<BoardPosition> plist;
                    plist = new ArrayList<BoardPosition>();
                    //Adds the current boardposition to the list
                    plist.add(pos);

                    //The character p is put in as the key of the map
                    //The list correspondding to p's positions is put in the value of the map
                    board.put(p, plist);
                    break;
                }

                //Otherwise, the value is added to the existing list
                else {
                    //Gets the list from the key
                    List<BoardPosition> plist = board.get(p);
                    //Adds the new position to the list
                    plist.add(pos);

                    //Removes the old list associated with the key and adds the new list
                    board.remove(p);
                    //Adds the key value pair back in with the updated list
                    board.put(p, plist);
                    break;
                }

            }
        }

    }

    /**
     * @param: pos = BoardPosition object
     * p = character representation of player
     * @post: true iff pinarow == NumToWin
     */
    public boolean checkHorizWin(BoardPosition pos, char p) {

        /*Starts checking NumToWin down from the token-NumToWin in order to find
        every possible way placing the token would win the game
         */
        int colawaystart = pos.getColumn()-NumToWin+1;
        //Loops while rowawaystart is not a greater row than the placed marker
        while(colawaystart <= pos.getColumn()) {

            //If the row being checked is out of bounds of the map, it is skipped
            if(colawaystart < 0 || colawaystart > COLMAX-1) {
            }

            //Variable to represent where the check ends
            int colawayend = colawaystart+NumToWin-1;
            //If the check ends out of bounds, it is also skipped
            if(colawayend > COLMAX-1) {
            }



            //Otherwise, the checking will start
            else {
                //Creates a variable to check if the consecutive token amount is equal to NumToWin
                int pinarow = 0;
                for(int i = 0; i < NumToWin; i++) {

                    int icolaway = colawaystart+i;

                    BoardPosition currentpos;
                    currentpos = new BoardPosition(pos.getRow(), icolaway);

                    if(this.isPlayerAtPos(currentpos, p)) {
                        pinarow += 1;
                    }

                }

                //if the characters in a row match the number to win, true is returned
                if(pinarow == NumToWin) {
                    return true;
                }
            }


            //Increments after checking
            colawaystart++;
        }


        //Finally, false is returned if the loop finishes without pinarow ever matching NumToWin
        return false;


    }

    /**
     * @param: pos = BoardPosition object
     * p = character representation of player
     * @post: true iff pinarow == NumToWin
     */
    public boolean checkVertWin(BoardPosition pos, char p) {

        /*Starts checking NumToWin down from the token-NumToWin in order to find
        every possible way placing the token would win the game
         */
        int rowawaystart = pos.getRow()-NumToWin+1;
        //Loops while rowawaystart is not a greater row than the placed marker
        while(rowawaystart <= pos.getRow()) {

            //If the row being checked is out of bounds of the map, it is skipped
            if(rowawaystart < 0 || rowawaystart > ROWMAX-1) {
            }

            //Variable to represent where the check ends
            int rowawayend = rowawaystart+NumToWin-1;
            //If the check ends out of bounds, it is also skipped
            if(rowawayend > ROWMAX-1) {
            }



            //Otherwise, the checking will start
            else {
                //Creates a variable to check if the consecutive token amount is equal to NumToWin
                int pinarow = 0;
                for(int i = 0; i < NumToWin; i++) {

                    int irowaway = rowawaystart+i;

                    BoardPosition currentpos;
                    currentpos = new BoardPosition(irowaway, pos.getColumn());

                    if(this.isPlayerAtPos(currentpos, p)) {
                        pinarow += 1;
                    }

                }

                //if the characters in a row match the number to win, true is returned
                if(pinarow == NumToWin) {
                    return true;
                }
            }


            //Increments after checking
            rowawaystart++;
        }


        //Finally, false is returned if the loop finishes without pinarow ever matching NumToWin
        return false;
    }

    /**
     * @param: pos = BoardPosition object
     * p = character representation of player
     * @post: true iff pinarow == NumToWin
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

                    BoardPosition currentpos;
                    currentpos = new BoardPosition(irowaway, icolaway);


                    if(isPlayerAtPos(currentpos, p)) {
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

                    BoardPosition currentpos;
                    currentpos = new BoardPosition(irowaway, icolaway);

                    if(isPlayerAtPos(currentpos, p)) {
                        pinarow += 1;
                    }

                    //if the characters in a row match the number to win, true is returned
                    if(pinarow == NumToWin) {
                        return true;
                    }
                }

            }
        }



        //Finally, false is returned if the loop finishes without pinarow ever matching NumToWin
        return false;
    }

    /**
     * @param: pos = BoardPosition object
     * @post: token = currentchar or ' '
     * @return: token
     */
    public char whatsAtPos(BoardPosition pos) {

        //Initializes a token variable
        char token = ' ';

        //Loops through the board map
        for (Map.Entry<Character, List<BoardPosition>> m : board.entrySet()) {
            //Creates a variable equal to the current key in the list
            char currentchar = m.getKey();
            //Creates a variable equal to the current value in the list (a list)
            List<BoardPosition> currentList = m.getValue();
            //If the list contains the position, the token is set equal to the character that has it
            if (currentList.contains(pos)) {
                token = currentchar;
            }
        }

        return token;

    }


    /**
     * @param: pos = BoardPosition object
     * player = character representation of player token
     * @pre char player exists in the game
     * @post: true iff playerposlist.contains(pos)
     * @override
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {


        //Creates a value equal to the list of the player list
        List<BoardPosition> playerposlist = board.get(player);

        //If the list contains the position, true is returned
        if (playerposlist.contains(pos)) {
            return true;
        }
        else {
            return false;
        }
    }




}
