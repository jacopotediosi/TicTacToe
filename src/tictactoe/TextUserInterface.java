/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class contains static methods used to manage the user's text interface
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public abstract class TextUserInterface {
    private static final InputStreamReader INPUTSTREAM  = new InputStreamReader(System.in);
    private static final BufferedReader BUFFEREDINPUT   = new BufferedReader(INPUTSTREAM);
    
    /**
     * Prints menu, asks for the game mode and return a gameboard created with
     * the user choises
     * @return the gameboard
     * @throws IOException
     * @throws CellIsNotEmptyException
     * @throws IsGameOverException
     */
    public static GameBoard initGameBoard() throws IOException, CellIsNotEmptyException, IsGameOverException {
        GameBoard gameBoard;
        clearConsole();
        System.out.println("Welcome!");
        System.out.println("1 - Play against a friend");
        System.out.println("2 - Play against AI (it doesn't matter who starts)");
        System.out.println("3 - Play against AI (I want to start)");
        System.out.println("4 - Play against AI (I don't want to start)");
        System.out.print("What would you like to do? ");
        int choice = insertInteger(1, 4);
        switch(choice) {
            case 1:
                gameBoard = new GameBoard();
                break;
            case 2:
                gameBoard = new GameBoard(true);
                if( (Math.random() * 100) < 50 )
                    aiMove(gameBoard);
                break;
            case 3:
                gameBoard = new GameBoard(true);
                break;
            case 4:
                gameBoard = new GameBoard(true);
                aiMove(gameBoard);
                break;
            default:
                gameBoard = new GameBoard();
                break;
        }
        return gameBoard;
    }
    
    /**
     * Clears the console and prints the gameboard
     * @param gameBoard the gameboard to print
     */
    public static void clearConsoleAndPrintGameBoard(GameBoard gameBoard) {
        clearConsole();
        System.out.println(gameBoard);
    }
    
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }
    
    /**
     * Used when the player wants to move. It asks where the user wants to move
     * and then it calls the AI if necessary.
     * @param gameBoard the current gameboard
     * @throws IOException
     * @throws IsGameOverException
     * @throws CellIsNotEmptyException
     */
    public static void move(GameBoard gameBoard) throws IOException, IsGameOverException, CellIsNotEmptyException {
        huMove(gameBoard);
        if(gameBoard.getAgainstAi() && !gameBoard.isGameOver()) {
            aiMove(gameBoard);
        }
    }
    
    private static void huMove(GameBoard gameBoard) throws CellIsNotEmptyException, IsGameOverException, IOException {
        boolean validated;
        do {
            try {
                Coordinate insertedCoordinate = TextUserInterface.askCoordinate(gameBoard);
                gameBoard.cellChoice(insertedCoordinate.getX(), insertedCoordinate.getY());
                validated = true;
            } catch (CellIsNotEmptyException ex) {
                System.out.println("Error: the chosen cell is not empty");
                validated = false;
            }
        } while(!validated);
    }
    
    private static void aiMove(GameBoard gameBoard) throws CellIsNotEmptyException, IsGameOverException {
        Coordinate aiCoord = ArtificialIntelligence.getBestMoveCoordinate(gameBoard);
        gameBoard.cellChoice(aiCoord.getX(), aiCoord.getY());
    }
    
    private static Coordinate askCoordinate(GameBoard gameBoard) throws IOException {
        int x=0,y=0;
        boolean validated = false;
        while(!validated) {
            System.out.print("Please enter coordinates of a cell (i.e. A1): ");
            String rawCoordinateString = BUFFEREDINPUT.readLine().toLowerCase();
            if(rawCoordinateString.length() != 2) {
                System.out.println("Error: input should be of 2 chars!");
            } else {
                y = rawCoordinateString.charAt(0)-'a';
                x = rawCoordinateString.charAt(1)-'1';
                if (x<0 || x>2)
                    System.out.println("Error: second char of input should be 1, 2 or 3!");
                else if (y<0 || y>2)
                    System.out.println("Error: first char of input should be A, B or C!");
                else
                    validated = true;
            }
        }
        return new Coordinate(x, y);
    }
    
    private static int insertInteger(int min, int max) throws IOException {
        int number = 0;
        boolean validated = false;
        while(!validated) {
            try {
                number = Integer.parseInt(BUFFEREDINPUT.readLine());
                if(number<min || number>max)
                    System.out.println("Error: input should be between "+min+" and "+max+"!");
                else
                    validated = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: input is not a valid number!");
            }
        }
        return number;
    }
}
