package cpsc2150.connectX;

/**
 * Created by kennyn on 2/3/20.
 */

/**
 * Kenny Nguyen
 * CPSC 2150 Spring 2020
 * BoardPosition.java
 * Kevin Plis
 */

/**
 * @invariant row >= 0
 * @invariant col >= 0
*/

import java.lang.String;

public class BoardPosition {

    private Integer row;
    private Integer column;

    /**
    @pre: 0 <= r <= 6
          0 <= c <= 7
    @post: Creates a BoardPosition object
    @param: r = row position, c = column position
    */
    public BoardPosition(int r, int c) {
        row = r;
        column = c;
    }


    /**
    @post: Returns row
     @return row
    */
    public int getRow() {
        return row;
    }


    /**
    @post: Returns column
     @return column
    */
    public int getColumn() {
        return column;
    }

    /** @override
    *
    * @param obj: an existing BoardPosition object
    * @return a true or false depending on if the two BoardPositions are equal
    * @post equals = true iff row.equals(boardpos.getRow()) && column.equals(boardpos.getColumn())
    */
    public boolean equals(Object obj) {

        BoardPosition boardpos = (BoardPosition) obj;

        //Returns true if both BoardPositions share the same row and column
        if(row.equals(boardpos.getRow()) && column.equals(boardpos.getColumn())) {
            return true;
        }

        else {
            return false;
        }


    }

    /** @override
     *
     * @param boardpos: an existing BoardPosition object
      * @return a string representation of the BoardPosition object
      * @post toString = "<row>,<column>"
    */
    public String toString(BoardPosition boardpos) {
        String boardstring = null;

        //The boardposition string is filled with the row and column of the taken in BoardPosition object
        boardstring = boardpos.getRow() + "," + boardpos.getColumn();

        return boardstring;


    }


}




