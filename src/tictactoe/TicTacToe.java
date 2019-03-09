/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

import java.io.IOException;
import static tictactoe.TextUserInterface.*;

/**
 * Main Class
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws tictactoe.IsGameOverException
     * @throws tictactoe.CellIsNotEmptyException
     */
    public static void main(String[] args) throws IOException, IsGameOverException, CellIsNotEmptyException {
        //Initializes the gameboard
        GameBoard gameBoard = initGameBoard();
        
        //Prints the gameboard
        clearConsoleAndPrintGameBoard(gameBoard);
        
        //Starts the game
        while(!gameBoard.isGameOver()) {            
            move(gameBoard);
            clearConsoleAndPrintGameBoard(gameBoard);
        }
    }
    
}
