package com.ag.tictactoe.model;

import com.ag.tictactoe.R;

/**
 * Cross GamePiece for Tic Tac Toe.
 */
public class CrossPiece extends GamePiece {

    /**
     * Reference to the drawable resource.
     */
    private static final int DRAWABLE = R.drawable.cross;

    /**
     * Default constructor calls super class passing in the drawable resource.
     */
    public CrossPiece() {
        super(DRAWABLE);
    }

}
