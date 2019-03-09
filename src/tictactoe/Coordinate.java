/*
 * TicTacToe, a simple game coded to pass the Programming Exam
 * Jacopo Tediosi, Universita' degli Studi di Milano (UniMI) - SSRI
 */
package tictactoe;

/**
 * Coordinate class; It's a structure used to store and share coordinates data
 * along the program (x, y and score for the AI).
 * @author Jacopo Tediosi: https://facebook.com/jacopotediosi
 */
public class Coordinate {
    private int x, y, score;

    /**
     * Constructor
     * @param x
     * @param y
     * @param score
     */
    public Coordinate(int x, int y, int score) {
        this.x     = x;
        this.y     = y;
        this.score = score;
    }
    
    /**
     * Constructor without score value. Score will remain undefined.
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        this.x  = x;
        this.y  = y;
    }

    /**
     * Standard get method
     * @return value of the coordinate score stored by the AI
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Standard get method
     * @return x value of the coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Standard get method
     * @return y value of the coordinate
     */
    public int getY() {
        return this.y;
    }
}
