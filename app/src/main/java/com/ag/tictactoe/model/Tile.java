package com.ag.tictactoe.model;

/**
 * Class represents information for a single Tile.
 */
public class Tile {

    /**
     * Denotes which GamePiece occupies this Tile.
     */
    private GamePiece gamePiece;

    /**
     * Default constructor initializes player to null.
     */
    public Tile() {
        gamePiece = null;
    }

    /**
     * Returns true if this Tile is occupied by a GamePiece.
     *
     * @return
     */
    public boolean getIsOccupied() {
        if (gamePiece != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set a GamePiece onto this Tile.
     *
     * @param gp
     */
    public void setGamePiece(GamePiece gp) {
        gamePiece = gp;
    }

    /**
     * Returns the GamePiece that is on this Tile.
     */
    public GamePiece getGamePiece() {
        return gamePiece;
    }

}
