/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matte
 */
public class PosPlay {
    private int row, col; /* Row and column of the play */
    private int score;    /* play's score               */  

    public PosPlay(int v, int r, int c) {
	row = r;
	col = c;
	score = v;
    }

    public int getRow() {
	return row;
    }

    public int getCol() {
	return col;
    }

    public int getScore() {
	return score;
    }
}
