/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

import java.util.ArrayList;

/**
 * GameBoard class
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public class GameBoard {
    private boolean againstAi     = false;
    private int round             = 0;
    private Player[][] chessBoard = {
        {Player.NONE, Player.NONE, Player.NONE},
        {Player.NONE, Player.NONE, Player.NONE},
        {Player.NONE, Player.NONE, Player.NONE}
    };

    /**
     * Default constructor of the gameboard. Used when you want to create a 1vs1
     * gameboard.
     */
    public GameBoard() {}
    
    /**
     * Costructor of the gameboard. Used when you want to specify if you want to
     * create a 1vs1 or a 1vsAI
     * @param againstAi false if you want to create a 1vs1, true if you want 1vsAI
     */
    public GameBoard(boolean againstAi) {
        this.againstAi = againstAi;
    }
    
    /**
     * Constructor used to create a deep copy of a given gameboard
     * @param gameBoard the gameboard object you want to copy
     */
    public GameBoard(GameBoard gameBoard) {
        this.againstAi  = gameBoard.getAgainstAi();
        this.round      = gameBoard.getRound();
        
        this.chessBoard = new Player[gameBoard.getChessBoard().length][];
        for(int i = 0; i < gameBoard.getChessBoard().length; i++)
            this.chessBoard[i] = gameBoard.getChessBoard()[i].clone();
    }
    
    /**
     * Classic get method
     * @return the current round of the gameboard (starts from 0 when the
     * gameboard is created and increment of 1 when every valid move is made
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Classic get method
     * @return the matrix of the chessboard at the actual state
     */
    public Player[][] getChessBoard() {
        return this.chessBoard;
    }
    
    /**
     * Used to know if a gameboard is 1vs1 or 1vsAI
     * @return true if the gameboard is against the AI, false if it isn't
     */
    public boolean getAgainstAi() {
        return this.againstAi;
    }
        
    @Override
    public String toString() {
        String chessBoardString = "Round: "+(round+1)+"\t";

        if(this.hasWinner()) {
            if(againstAi)
                chessBoardString += "AI has won";
            else
                chessBoardString += this.getPreviousPlayer()+" has won";
        } else if(this.isGameOver()) {
            chessBoardString += "Nobody won";
        }else {
            if(againstAi)
                chessBoardString += "You are: "+this.getActualPlayer();
            else
                chessBoardString += "It's up to: "+this.getActualPlayer();
        }

        chessBoardString += "\n\n      1     2     3\n" + "  +-------------------\n" + "A |   ";
        for (int row=0; row<this.chessBoard.length; row++) {
            for (int column=0; column<this.chessBoard[0].length; column++) {
                chessBoardString += this.chessBoard[column][row];
                if(column!=2)
                    chessBoardString += "  |  ";
            }
            if(row!=this.chessBoard.length-1)
                chessBoardString += "\n  | -----+-----+-----\n"+(char)('B'+row)+" |   ";
        }
        
        chessBoardString += "\n";
        
        return chessBoardString;
    }
    
    /**
     * Used to put the symbol of the current player on an empty cell of the
     * gameboard
     * @param x the x coordinate of the chosen cell
     * @param y the y coordinate of the chosen cell
     * @throws CellIsNotEmptyException
     * @throws IsGameOverException
     */
    public void cellChoice(int x, int y) throws CellIsNotEmptyException, IsGameOverException {
        if(!this.checkEmptyCell(x, y))
            throw new CellIsNotEmptyException(x, y);
        if(this.isGameOver())
            throw new IsGameOverException();
        this.chessBoard[x][y] = this.getActualPlayer();
        this.round++;
    }
    
    private boolean checkEmptyCell(int x, int y) {
        return this.chessBoard[x][y] == Player.NONE;
    }
    
    /**
     * Used to get an array containing coordinates of empty cells of the gameboard
     * @return an array containing coordinates of empty cells of the gameboard
     */
    public Coordinate[] getEmptyCellCoordinates() {
        ArrayList<Coordinate> result = new ArrayList<>();
        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++) {
                if(this.checkEmptyCell(x, y))
                    result.add(new Coordinate(x, y));
            }
        }
        return result.toArray(new Coordinate[0]);
    }
    
    /**
     * Used to check if someone has won
     * @return true if someone has won; false instead
     */
    public boolean hasWinner() {
        return (
            //columns
            (this.chessBoard[0][0]!=Player.NONE && this.chessBoard[0][0]==this.chessBoard[0][1] && this.chessBoard[0][0]==this.chessBoard[0][2]) ||
            (this.chessBoard[1][0]!=Player.NONE && this.chessBoard[1][0]==this.chessBoard[1][1] && this.chessBoard[1][0]==this.chessBoard[1][2]) ||
            (this.chessBoard[2][0]!=Player.NONE && this.chessBoard[2][0]==this.chessBoard[2][1] && this.chessBoard[2][0]==this.chessBoard[2][2]) ||
            //rows
            (this.chessBoard[0][0]!=Player.NONE && this.chessBoard[0][0]==this.chessBoard[1][0] && this.chessBoard[0][0]==this.chessBoard[2][0]) ||
            (this.chessBoard[0][1]!=Player.NONE && this.chessBoard[0][1]==this.chessBoard[1][1] && this.chessBoard[0][1]==this.chessBoard[2][1]) ||
            (this.chessBoard[0][2]!=Player.NONE && this.chessBoard[0][2]==this.chessBoard[1][2] && this.chessBoard[0][2]==this.chessBoard[2][2]) ||
            //diagonals
            (this.chessBoard[0][0]!=Player.NONE && this.chessBoard[0][0]==this.chessBoard[1][1] && this.chessBoard[0][0]==this.chessBoard[2][2]) ||
            (this.chessBoard[2][0]!=Player.NONE && this.chessBoard[2][0]==this.chessBoard[1][1] && this.chessBoard[2][0]==this.chessBoard[0][2])
        );
    }
    
    /**
     * Used to check if a specific player has won
     * @param player the player you want to check
     * @return true if the selected player has won; false instead
     */
    public boolean hasWinner(Player player) {
        return (
            //columns
            (this.chessBoard[0][0]==player && this.chessBoard[0][1]==player && this.chessBoard[0][2]==player) ||
            (this.chessBoard[1][0]==player && this.chessBoard[1][1]==player && this.chessBoard[1][2]==player) ||
            (this.chessBoard[2][0]==player && this.chessBoard[2][1]==player && this.chessBoard[2][2]==player) ||
            //rows
            (this.chessBoard[0][0]==player && this.chessBoard[1][0]==player && this.chessBoard[2][0]==player) ||
            (this.chessBoard[0][1]==player && this.chessBoard[1][1]==player && this.chessBoard[2][1]==player) ||
            (this.chessBoard[0][2]==player && this.chessBoard[1][2]==player && this.chessBoard[2][2]==player) ||
            //diagonals
            (this.chessBoard[0][0]==player && this.chessBoard[1][1]==player && this.chessBoard[2][2]==player) ||
            (this.chessBoard[2][0]==player && this.chessBoard[1][1]==player && this.chessBoard[0][2]==player)
        );
    }
    
    /**
     * Used to check if the game is over (someone has won or there is a parity)
     * @return true if the game is over; false instead
     */
    public boolean isGameOver() {
        return (this.hasWinner() || this.getEmptyCellCoordinates().length==0);
    }
    
    /**
     * Get the player to whom he has to move
     * @return the player to whom he has to move
     */
    public Player getActualPlayer() {
        return (this.round%2==0 ? Player.X : Player.O);
    }
    
    /**
     * Get the player who has just moved
     * @return the player who has just moved
     */
    public Player getPreviousPlayer() {
        return (this.round%2==0 ? Player.O : Player.X);
    }
}
