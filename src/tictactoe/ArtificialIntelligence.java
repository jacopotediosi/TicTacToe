/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

/**
 *
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public abstract class ArtificialIntelligence {
    public static Coordinate getBestMoveCoordinate(GameBoard gameBoard) throws CellIsNotEmptyException, IsGameOverException {  
        return minimax(gameBoard, gameBoard.getActualPlayer());
    }

    private static Coordinate minimax(GameBoard gameBoard, Player aiPlayer) throws CellIsNotEmptyException, IsGameOverException {
        if (gameBoard.isGameOver())
            return score(gameBoard, aiPlayer);
        
        if (gameBoard.getActualPlayer() == aiPlayer)
            return getMax(gameBoard, aiPlayer);
        else
            return getMin(gameBoard, aiPlayer);
    }
    
    private static Coordinate getMax(GameBoard gameBoard, Player aiPlayer) throws CellIsNotEmptyException, IsGameOverException {
        Coordinate coordinateOfBestMove = new Coordinate(0, 0, Integer.MIN_VALUE);

        for (Coordinate coordinate : gameBoard.getEmptyCellCoordinates()) {
            GameBoard newGameBoard = new GameBoard(gameBoard);
            newGameBoard.cellChoice(coordinate.getX(), coordinate.getY());

            int score = minimax(newGameBoard, aiPlayer).getScore();

            if (score >= coordinateOfBestMove.getScore())
                coordinateOfBestMove = new Coordinate(coordinate.getX(), coordinate.getY(), score);
        }
        
        return coordinateOfBestMove;
    }
    
    private static Coordinate getMin(GameBoard gameBoard, Player aiPlayer) throws CellIsNotEmptyException, IsGameOverException {
        Coordinate coordinateOfBestMove = new Coordinate(0, 0, Integer.MAX_VALUE);

        for (Coordinate coordinate : gameBoard.getEmptyCellCoordinates()) {
            GameBoard newGameBoard = new GameBoard(gameBoard);
            newGameBoard.cellChoice(coordinate.getX(), coordinate.getY());

            int score = minimax(newGameBoard, aiPlayer).getScore();

            if (score <= coordinateOfBestMove.getScore())
                coordinateOfBestMove = new Coordinate(coordinate.getX(), coordinate.getY(), score);
        }
        
        return coordinateOfBestMove;
    }
    
    private static Coordinate score(GameBoard gameBoard, Player aiPlayer) {
        if (gameBoard.hasWinner(aiPlayer))
            return new Coordinate(0, 0, 10);
        else if (gameBoard.hasWinner())
            return new Coordinate(0, 0, -10);
        else
            return new Coordinate(0, 0, 0);
    }
}
