package com.ag.tictactoe.model;

/**
 * Class represents information for a single game piece.
 */
public abstract class GamePiece {

    /**
     * Reference to the drawable resource.
     */
    protected int drawable;

    /**
     * Constructor initializes the GamePiece with a drawable resource.
     *
     * @param d
     */
    protected GamePiece(int d) {
        drawable = d;
    }

    /**
     * Returns the drawable resource id.
     *
     * @return
     */
    public int getDrawable() {
        return drawable;
    }

}
