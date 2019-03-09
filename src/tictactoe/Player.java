/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

/**
 *
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public enum Player {
    X, O, NONE;
    
    @Override
    public String toString() {
        switch(this) {
            case X:
                return "\33[31mX\33[0m";
            case O:
                return "\33[34mO\33[0m";
            case NONE:
                return " ";
            default:
                return this.toString();
        }
    }
}
