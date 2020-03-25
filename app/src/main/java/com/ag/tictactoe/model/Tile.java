package com.ag.tictactoe.model;

/**
 * Class represents information for a single Tile.
 */
public class Tile {

    /**
     * Indicates different players.
     */
    public enum PLAYER {
        ONE, TWO
    }

    /**
     * Denotes which player occupies this Tile.
     */
    private PLAYER player;

    /**
     * Default constructor initializes player to null.
     */
    public Tile() {
        player = null;
    }

    /**
     * Returns true if this Tile is occupied.
     *
     * @return
     */
    public boolean getIsOccupied() {
        if (player != null) {
            return true;
        } else {
            return false;
        }
    }

}
