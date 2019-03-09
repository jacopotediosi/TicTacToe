/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

/**
 * Throwed when an action cannot be executed because the game is over
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public class IsGameOverException extends Exception {

    /**
     * Creates a new instance of <code>IsGameOverException</code> without detail
     * message.
     */
    public IsGameOverException() {
    }

    /**
     * Constructs an instance of <code>IsGameOverException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public IsGameOverException(String msg) {
        super(msg);
    }
}
