package com.ag.tictactoe.model;

import com.ag.tictactoe.R;

/**
 * Circle GamePiece for Tic Tac Toe.
 */
public class CirclePiece extends GamePiece {

    /**
     * Reference to the drawable resource.
     */
    private static final int DRAWABLE = R.drawable.circle;

    /**
     * Default constructor calls super class passing in the drawable resource.
     */
    public CirclePiece() {
        super(DRAWABLE);
    }

}
