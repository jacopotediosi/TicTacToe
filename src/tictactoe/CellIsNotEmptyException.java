/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

/**
 * Throwed when some method try to fill a cell that isn't empty
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public class CellIsNotEmptyException extends Exception {

    /**
     * Creates a new instance of <code>CellIsNotEmptyException</code> without
     * detail message.
     */
    public CellIsNotEmptyException() {
    }

    /**
     * Constructs an instance of <code>CellIsNotEmptyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CellIsNotEmptyException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>CellIsNotEmptyException</code> containing
     * the coordinates of the affected cell
     * @param x x coordinate of the cell
     * @param y y coordinate of the cell
     */
    public CellIsNotEmptyException(int x, int y) {
        super("Cell at ("+x+";"+y+") is not empty");
    }
    
    /**
     * Constructs an instance of <code>CellIsNotEmptyException</code> containing
     * the coordinates of the affected cell
     * @param coordinate the affected cell
     */
    public CellIsNotEmptyException(Coordinate coordinate) {
        super("Cell at ("+coordinate.getX()+";"+coordinate.getY()+") is not empty");
    }
}
