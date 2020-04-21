package cpsc2150.connectX;

/**
 * Kenny Nguyen
 * CPSC 2150 Spring 2020
 * TestGameBoardMem.java
 * Kevin Plis
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {

    //Private integers for the expectedString function
    private int ROWMAX = 0;
    private int COLMAX = 0;

    private IGameBoard MakeAGameBoard(int ROWMAX, int COLMAX, int NumToWin) {
        IGameBoard connectx;
        connectx = new GameBoard(ROWMAX, COLMAX, NumToWin);

        //Sets the ROWMAX and COLMAX private integer variables as well for the expectedString function
        this.ROWMAX = ROWMAX;
        this.COLMAX = COLMAX;

        return connectx;
    }

    private String expectedString(char board[][]) {
        //Creates the top row
        String boardString = "";
        for (int i = 0; i < COLMAX; i++) {
            boardString += "|" + i;
        }
        boardString += "|";
        boardString += "\n";

        for (int i = ROWMAX - 1; i >= 0; i--) {
            for (int j = 0; j < COLMAX; j++) {

                //If the columns are single digit, no padding is placed
                //Adds a divider and the marker at the current boardposition to the string representation
                if (j < 10) {
                    BoardPosition pos;
                    pos = new BoardPosition(i, j);
                    boardString += "|" + board[i][j];
                }
                //If the columns are two digit, one space pad is placed
                else if (j >= 10 && j < 100) {
                    BoardPosition pos;
                    pos = new BoardPosition(i, j);
                    boardString += "| " + board[i][j];
                }

                //If the columns are three digit, two space pads are placed
                else if (j >= 100) {
                    BoardPosition pos;
                    pos = new BoardPosition(i, j);
                    boardString += "| " + board[i][j] + " ";
                }

            }

            //Once the column of that row is finished, finishes the row and goes to the next
            boardString += "|";
            boardString += "\n";
        }

        return boardString;
    }

    //3 Tests for Constructor

    @Test
    public void testsmallestBoard_constructor() {
        int rows = 3;
        int cols = 3;
        int NumToWin = 3;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        assertEquals(connectx.toString(), expectedString(board));
        assertEquals(rows, connectx.getNumRows());
        assertEquals(cols, connectx.getNumColumns());
        assertEquals(NumToWin, connectx.getNumToWin());
    }

    @Test
    public void testbiggestBoard_constructor() {
        int rows = 100;
        int cols = 100;
        int NumToWin = 25;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        assertEquals(connectx.toString(), expectedString(board));
        assertEquals(rows, connectx.getNumRows());
        assertEquals(cols, connectx.getNumColumns());
        assertEquals(NumToWin, connectx.getNumToWin());
    }

    @Test
    public void testnormalBoard_constructor() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        assertEquals(connectx.toString(), expectedString(board));
        assertEquals(rows, connectx.getNumRows());
        assertEquals(cols, connectx.getNumColumns());
        assertEquals(NumToWin, connectx.getNumToWin());
    }

    //3 Tests for checkIfFree()

    @Test
    public void testemptyBoard_checkIfFree() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        assertEquals(connectx.toString(), expectedString(board));
        assertTrue(connectx.checkIfFree(0));
    }

    @Test
    public void testfullBoard_checkIfFree() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }


        for (int i = 0; i < rows; i++) {
            connectx.placeToken('X', 0);
        }

        for (int i = 0; i < rows; i++) {
            board[i][0] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        assertFalse(connectx.checkIfFree(0));
    }

    @Test
    public void testhalffullBoard_checkIfFree() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < (rows / 2); i++) {
            connectx.placeToken('X', 0);
        }
        for (int i = 0; i < rows / 2; i++) {
            board[i][0] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        assertTrue(connectx.checkIfFree(0));
    }

    //4 Tests for checkHorizWin()

    @Test
    public void testrow0leftmost_checkHorizWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < 4; i++) {
            connectx.placeToken('X', i);
        }

        for (int i = 0; i < 4; i++) {
            board[0][i] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        BoardPosition pos = new BoardPosition(0, 2);
        assertTrue(connectx.checkHorizWin(pos, 'X'));


    }

    @Test
    public void testmaxNumToWin_checkHorizWin() {
        int rows = 100;
        int cols = 100;
        int NumToWin = 25;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < NumToWin; i++) {
            connectx.placeToken('X', i);
        }

        for (int i = 0; i < NumToWin; i++) {
            board[0][i] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        BoardPosition pos = new BoardPosition(0, 0);
        assertTrue(connectx.checkHorizWin(pos, 'X'));


    }

    @Test
    public void testlowestNumToWin_checkHorizWin() {

        int rows = 3;
        int cols = 3;
        int NumToWin = 3;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < NumToWin; i++) {
            connectx.placeToken('X', i);
        }

        for (int i = 0; i < NumToWin; i++) {
            board[0][i] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        BoardPosition pos = new BoardPosition(0, 0);
        assertTrue(connectx.checkHorizWin(pos, 'X'));


    }

    @Test
    public void testtoprightmost_checkHorizWin() {

        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        //Places 'X'es until the second to top row is the only empty section
        for (int i = 0; i < rows-2; i++) {
            for (int j = cols - NumToWin; j < cols; j++) {
                connectx.placeToken('X', j);
            }
        }

        //Then finally places 'O's to fill the second to top section
        for (int j = cols - NumToWin; j < cols; j++) {
            connectx.placeToken('O', j);
        }


        BoardPosition pos = new BoardPosition(rows-2, cols-1);
        assertTrue(connectx.checkHorizWin(pos, 'O'));

    }

    //4 Tests for checkVertWin()

    @Test
    public void testbottomleft_checkVertWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < 4; i++) {
            connectx.placeToken('X', 0);
        }

        for (int i = 0; i < 4; i++) {
            board[i][0] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        BoardPosition pos = new BoardPosition(3, 0);
        assertTrue(connectx.checkVertWin(pos, 'X'));
    }

    @Test
    public void testbottomright_checkVertWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < 4; i++) {
            connectx.placeToken('X', cols-1);
        }

        for (int i = 0; i < 4; i++) {
            board[i][cols-1] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        BoardPosition pos = new BoardPosition(0, cols-1);
        assertTrue(connectx.checkVertWin(pos, 'X'));
    }

    @Test
    public void testmiddlemiddle_checkVertWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 5;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < 5; i++) {
            connectx.placeToken('X', 3);
        }

        for (int i = 0; i < 5; i++) {
            board[i][3] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        BoardPosition pos = new BoardPosition(1, 3);
        assertTrue(connectx.checkVertWin(pos, 'X'));
    }



    @Test
    public void testtopright_checkVertWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);
        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        connectx.placeToken('O', cols-1);
        connectx.placeToken('O', cols-1);


        for (int i = 0; i < 4; i++) {
            connectx.placeToken('X', cols-1);
        }

        board[0][cols-1] = 'O';
        board[1][cols-1] = 'O';


        for (int i = 2; i < rows; i++) {
            board[i][cols-1] = 'X';
        }

        assertEquals(connectx.toString(), expectedString(board));
        BoardPosition pos = new BoardPosition(2, cols-1);
        assertTrue(connectx.checkVertWin(pos, 'X'));
    }

    //7 Test Cases for checkDiagWin()

    @Test
    public void testbottomleftAscending_checkDiagWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 0);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 3);
        connectx.placeToken('X', 3);
        connectx.placeToken('X', 3);
        connectx.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(2, 2);
        assertTrue(connectx.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testtopleftDescending_checkDiagWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 3);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(3, 0);
        assertTrue(connectx.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testbottomrightDescending_checkDiagWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', cols-1);
        connectx.placeToken('X', cols-2);
        connectx.placeToken('X', cols-2);
        connectx.placeToken('X', cols-3);
        connectx.placeToken('X', cols-3);
        connectx.placeToken('X', cols-3);
        connectx.placeToken('X', cols-4);
        connectx.placeToken('X', cols-4);
        connectx.placeToken('X', cols-4);
        connectx.placeToken('X', cols-4);

        BoardPosition pos = new BoardPosition(2, 4);
        assertTrue(connectx.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testbottommiddleAscending_checkDiagWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 3);
        connectx.placeToken('X', 4);
        connectx.placeToken('X', 4);
        connectx.placeToken('X', 5);
        connectx.placeToken('X', 5);
        connectx.placeToken('X', 5);
        connectx.placeToken('X', 6);
        connectx.placeToken('X', 6);
        connectx.placeToken('X', 6);
        connectx.placeToken('X', 6);

        BoardPosition pos = new BoardPosition(0, 3);
        assertTrue(connectx.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testmiddlemiddleDescending_checkDiagWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 0);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 3);
        connectx.placeToken('X', 3);
        connectx.placeToken('X', 3);




        BoardPosition pos = new BoardPosition(2, 3);
        assertTrue(connectx.checkDiagWin(pos, 'X'));
    }

    @Test
    public void testmiddlemiddleAscending_checkDiagWin() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('K', 3);
        connectx.placeToken('K', 3);
        connectx.placeToken('K', 3);
        connectx.placeToken('K', 4);
        connectx.placeToken('K', 4);
        connectx.placeToken('K', 4);
        connectx.placeToken('K', 4);
        connectx.placeToken('K', 5);
        connectx.placeToken('K', 5);
        connectx.placeToken('K', 5);
        connectx.placeToken('K', 5);
        connectx.placeToken('K', 5);
        connectx.placeToken('K', 6);
        connectx.placeToken('K', 6);
        connectx.placeToken('K', 6);
        connectx.placeToken('K', 6);
        connectx.placeToken('K', 6);
        connectx.placeToken('K', 6);







        BoardPosition pos = new BoardPosition(2, 3);
        assertTrue(connectx.checkDiagWin(pos, 'K'));
    }

    @Test
    public void testbottommiddleAscending_checkDiagWinFALSE() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 0);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 1);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 2);
        connectx.placeToken('X', 3);
        connectx.placeToken('X', 3);
        connectx.placeToken('X', 3);
        connectx.placeToken('O', 3);







        BoardPosition pos = new BoardPosition(0, 0);
        assertFalse(connectx.checkDiagWin(pos, 'X'));
    }


    //4 Tests for checkTie()
    @Test
    public void testnormalboard_checkTie() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                connectx.placeToken('X', j);
            }
        }


        assertTrue(connectx.checkTie());
    }

    @Test
    public void testemptyboard_checkTie() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        assertFalse(connectx.checkTie());
    }

    @Test
    public void testlargestboard_checkTie() {
        int rows = 100;
        int cols = 100;
        int NumToWin = 25;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                connectx.placeToken('P', j);
            }
        }


        assertTrue(connectx.checkTie());
    }

    @Test
    public void testhalffullboard_checkTie() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        for (int i = 0; i < (rows/2); i++) {
            for(int j = 0; j < cols; j++) {
                connectx.placeToken('X', j);
            }
        }

        assertFalse(connectx.checkTie());
    }

    //5 Tests for WhatsAtPos()

    @Test
    public void testbottomleftx_WhatsAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(0,0);
        assertEquals('X', connectx.whatsAtPos(pos));
    }

    @Test
    public void testbottomrighto_WhatsAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('O', cols-1);

        BoardPosition pos = new BoardPosition(0,cols-1);
        assertEquals('O', connectx.whatsAtPos(pos));
    }

    @Test
    public void testbottomleftblankspace_WhatsAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(' ', connectx.whatsAtPos(pos));
    }

    @Test
    public void testbottommiddlex_WhatsAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 4);


        BoardPosition pos = new BoardPosition(0,4);
        assertEquals('X', connectx.whatsAtPos(pos));
    }

    @Test
    public void testtopmiddlex_WhatsAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        for(int i = 0; i < rows; i++) {
            connectx.placeToken('X', 4);
        }


        BoardPosition pos = new BoardPosition(rows-1,4);
        assertEquals('X', connectx.whatsAtPos(pos));
    }

    //5 Tests for IsPlayerAtPos()

    @Test
    public void testbottomleftx_IsPlayerAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(0,0);
        assertTrue(connectx.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testbottomleftFalse_IsPlayerAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('O', 0);
        connectx.placeToken('X', 1);


        BoardPosition pos = new BoardPosition(0,0);
        assertFalse(connectx.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testbottomleftlowercasex_IsPlayerAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('x', 0);
        connectx.placeToken('X', 1);



        BoardPosition pos = new BoardPosition(0,0);
        assertFalse(connectx.isPlayerAtPos(pos, 'X'));
    }

    @Test
    public void testbottomrightK_IsPlayerAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('K', cols-1);

        BoardPosition pos = new BoardPosition(0,cols-1);
        assertTrue(connectx.isPlayerAtPos(pos, 'K'));
    }

    @Test
    public void testtopleftx_IsPlayerAtPos() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        for(int i = 0; i < rows; i++) {
            connectx.placeToken('X', 0);
        }

        BoardPosition pos = new BoardPosition(rows-1,0);
        assertTrue(connectx.isPlayerAtPos(pos, 'X'));
    }

    //5 Tests for PlaceToken()

    @Test
    public void testbottomleftx_PlaceToken() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('X', 0);

        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        board[0][0] = 'X';

        assertEquals(connectx.toString(), expectedString(board));

    }

    @Test
    public void testbottomrightK_PlaceToken() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        connectx.placeToken('K', cols-1);

        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }

        board[0][cols-1] = 'K';

        assertEquals(connectx.toString(), expectedString(board));

    }

    @Test
    public void almostfullcolumn_PlaceToken() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);


        connectx.placeToken('O', cols-1);
        connectx.placeToken('O', cols-1);
        connectx.placeToken('O', cols-1);
        connectx.placeToken('O', cols-1);
        connectx.placeToken('O', cols-1);



        connectx.placeToken('X', cols-1);


        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        board[0][6] = 'O';
        board[1][6] = 'O';
        board[2][6] = 'O';
        board[3][6] = 'O';
        board[4][6] = 'O';
        board[5][6] = 'X';


        assertEquals(connectx.toString(), expectedString(board));

    }

    @Test
    public void leftfullboard_PlaceToken() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        for(int i = 0; i < rows; i++) {
            connectx.placeToken('O', 0);
        }
        connectx.placeToken('X',1);

        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        for (int i = 0; i < rows; i++) {
            board[i][0] = 'O';
        }
        board[0][1] = 'X';

        assertEquals(connectx.toString(), expectedString(board));

    }

    @Test
    public void middlehalffullboard_PlaceToken() {
        int rows = 6;
        int cols = 7;
        int NumToWin = 4;
        IGameBoard connectx = MakeAGameBoard(rows, cols, NumToWin);

        for(int i = 0; i < 3; i++) {
            connectx.placeToken('O', 4);
        }
        connectx.placeToken('K', 4);

        char board[][] = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        for (int i = 0; i < 3; i++) {
            board[i][4] = 'O';
        }
        board[3][4] = 'K';

        assertEquals(connectx.toString(), expectedString(board));

    }


}
