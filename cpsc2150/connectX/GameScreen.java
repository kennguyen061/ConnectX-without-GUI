package cpsc2150.connectX;
import java.util.*;
import java.lang.*;

/**
 * Created by kennyn on 2/3/20.
 */

/**
 * Kenny Nguyen
 * CPSC 2150 Spring 2020
 * GameScreen.java
 * Kevin Plis
 */

import java.lang.String;


public class GameScreen {

    public static void main(String [] args) {

        //Creates a scanner for userinput
        Scanner input = new Scanner(System.in);

        //Bool to represent whether the player wants to stop playing the game
        Boolean endgame = false;

        //Integer to represent the current active player, starts at 0
        int activeplayerint = 0;

        while(endgame == false) {

            Boolean validplayernum = false;
            int players = 2;
            while (validplayernum == false) {
                System.out.println("How many players?");
                players = input.nextInt();
                if (players < 2) {
                    System.out.println("Must be at least 2 players");
                } else if (players > 10) {
                    System.out.println("Must be 10 players or fewer");
                } else {
                    validplayernum = true;
                }
            }

            List<Character> charList = new ArrayList<>();
            for (int i = 1; i <= players; i++) {
                Character tempchar;
                Boolean validchar = false;
                while (validchar == false) {
                    System.out.println("Enter the character to represent player " + i);
                    tempchar = input.next().charAt(0);
                    if (charList.contains(tempchar)) {
                        System.out.println(tempchar + " is already taken as a player token!");
                    } else if (tempchar == ' ') {
                        System.out.println("Character can not be a blank space!");
                    } else {
                        charList.add(tempchar);
                        validchar = true;
                    }
                }

            }

            Boolean validrows = false;
            int rows = 6;
            while (validrows == false) {
                System.out.println("How many rows should be on the board?");
                rows = input.nextInt();
                if (rows < 3) {
                    System.out.println("Must have at least 3 rows");
                } else if (rows > 100) {
                    System.out.println("Must have less than 100 rows");
                } else {
                    validrows = true;
                }
            }
            Boolean validcols = false;
            int cols = 7;
            while (validcols == false) {
                System.out.println("How many columns should be on the board?");
                cols = input.nextInt();
                if (cols < 3) {
                    System.out.println("Must have at least 3 columns");
                } else if (cols > 100) {
                    System.out.println("Must have less than 100 columns");
                } else {
                    validcols = true;
                }
            }

            Boolean validnumtowin = false;
            int numtowin = 4;
            while (validnumtowin == false) {
                System.out.println("How many in a row to win?");
                numtowin = input.nextInt();
                if (numtowin < 3) {
                    System.out.println("Must have at least 3 in a row to win");
                } else if (numtowin > 25) {
                    System.out.println("Must have less than 25 in a row to win");
                } else if (numtowin > rows) {
                    System.out.println("In a row to win can not exceed the number of rows on the board");
                } else if (numtowin > cols) {
                    System.out.println("In a row to win can not exceed the number of columns on the board");
                } else {
                    validnumtowin = true;
                }
            }

            IGameBoard connectx = null;

            Boolean validgametype = false;
            while(validgametype == false) {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");
                char userinput = input.next().charAt(0);
                if(userinput == 'F' || userinput == 'f') {
                    //Creates a new fast gameboard object
                    connectx = new GameBoard(rows,cols,numtowin);
                    validgametype = true;
                }

                else if(userinput == 'M' || userinput == 'm') {
                    //Creates a memory efficient gameboard object
                    connectx = new GameBoardMem(rows,cols,numtowin);
                    validgametype = true;

                }

                else {
                    System.out.println("Please enter F or M");
                }
        }


            //Prints the blank board
            System.out.println(connectx);

            char activeplayer = charList.get(activeplayerint);

            Boolean gamefinished = false;

            while(gamefinished == false) {
                Boolean validcolumn = false;

                int playerchoice = -1;

                while(validcolumn == false) {
                    System.out.println("Player " + activeplayer + ", what column do you want to place your marker in?");

                    playerchoice = input.nextInt();

                    if(playerchoice < 0) {
                        System.out.println("Column can not be less than 0");
                    }

                    else if(playerchoice >= connectx.getNumColumns()) {
                        System.out.println("Column can not be greater than " + (connectx.getNumColumns()-1));
                    }

                    else if(connectx.checkIfFree(playerchoice) == false) {
                        System.out.println("Column must not be full");
                    }

                    else {
                        validcolumn = true;
                    }
                }

                connectx.placeToken(activeplayer, playerchoice);

                System.out.println(connectx);

                //Checks for a win state
                if(connectx.checkForWin(playerchoice) == true) {
                    //If someone has won, sets the game to finished
                    gamefinished = true;

                    System.out.println("Player " + activeplayer + " Won!");

                    Boolean validans = false;
                    while(validans == false) {
                        System.out.println("Would you like to play again? Y/N");
                        char userinput = input.next().charAt(0);

                        if(userinput == 'Y' || userinput == 'y') {
                            //Escapes loop to play another game
                            validans = true;
                        }

                        else if(userinput == 'N' || userinput == 'n') {
                            //Escapes both loops and ends the program
                            validans = true;
                            endgame = true;
                        }
                    }
                }

                //Checks for a tie state if the win status is not reached
                else if(connectx.checkTie() == true) {
                    System.out.println("Tie Game!");

                    Boolean validans = false;
                    while(validans == false) {
                        System.out.println("Would you like to play again? Y/N");
                        char userinput = input.next().charAt(0);

                        if(userinput == 'Y' || userinput == 'y') {
                            //Escapes loop to play another game
                            validans = true;
                        }

                        else if(userinput == 'N' || userinput == 'n') {
                            //Escapes both loops and ends the program
                            validans = true;
                            endgame = true;
                            System.exit(0);
                        }
                    }
                }

//                Otherwise keeps playing


                //Alternates players for next turn
                //If the current active player is the last one, the first player goes again next turn
                if(activeplayerint == charList.size()-1) {
                    activeplayerint = 0;
                }
                //Otherwise, the active player counter is incremented
                else{
                    activeplayerint += 1;
                }

                activeplayer = charList.get(activeplayerint);




            }





        }









    }

}
