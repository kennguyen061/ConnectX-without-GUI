package cpsc2150.connectX;

public abstract class AbsGameBoard implements IGameBoard {



    /**
     * @return a string representation of the GameBoard
     * @post
     * Ex for a 7x6 board:
     *       toString = "|0|1|2|3|4|5|6|\n
     *                   | | | | | | | |\n
     *                   | | | | | | | |\n
     *                   | | | | | | | |\n
     *                   | | | | | | | |\n
     *                   | | | | | | | |\n
     *                   | | | | | | | |\n
     @override
     */
    public String toString() {

        //Creates the top row
        String boardString = "";
        for(int i = 0; i < this.getNumColumns(); i++) {
            boardString += "|" + i;
        }
        boardString += "|";
        boardString += "\n";

        for(int i = this.getNumRows()-1; i >= 0; i--) {
            for(int j = 0; j < this.getNumColumns(); j++) {

                //If the columns are single digit, no padding is placed
                //Adds a divider and the marker at the current boardposition to the string representation
                if(j < 10) {
                    BoardPosition pos;
                    pos = new BoardPosition(i, j);
                    boardString += "|" + this.whatsAtPos(pos);
                }
                //If the columns are two digit, one space pad is placed
                else if (j >= 10 && j < 100) {
                    BoardPosition pos;
                    pos = new BoardPosition(i, j);
                    boardString += "| " + this.whatsAtPos(pos);
                }

                //If the columns are three digit, two space pads are placed
                else if(j >= 100) {
                    BoardPosition pos;
                    pos = new BoardPosition(i, j);
                    boardString += "| " + this.whatsAtPos(pos) + " ";
                }

            }

            //Once the column of that row is finished, finishes the row and goes to the next
            boardString += "|";
            boardString += "\n";
        }

        return boardString;

    }



}
