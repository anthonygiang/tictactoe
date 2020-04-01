package com.ag.tictactoe.model;

import android.content.Context;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Class represents information for a single Tile.
 */
public class Tile {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = Tile.class.getName();

    /**
     * X coordinate where this Tile resides.
     */
    private int xCoordinate;

    /**
     * Y coordinate where this Tile resides.
     */
    private int yCoordinate;

    /**
     * Denotes which GamePiece occupies this Tile.
     */
    private GamePiece gamePiece;

    /**
     * Button this Tile is mapped to.
     */
    private ImageButton button;

    /**
     * Default constructor initializes player to null.
     */
    public Tile(int x, int y) {
        gamePiece = null;
        button = null;
        xCoordinate = x;
        yCoordinate = y;
    }

    /**
     * Constructor copies this class.
     *
     * @param t
     */
    public Tile(Tile t) {
        gamePiece = t.gamePiece;
        button = t.getButton();
        xCoordinate = t.xCoordinate;
        yCoordinate = t.yCoordinate;
    }

    /**
     * Sets the Button this Tile is associated with.
     * @param b
     */
    public void setButton(ImageButton b) {
        button = b;
    }

    /**
     * Return the button associated with this Tile.
     */
    public ImageButton getButton() {
        return button;
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

    /**
     * Returns the x coordinate for this Tile.
     *
     * @return
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Returns the y coordinate for this Tile.
     *
     * @return
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Displays the coordinates of this Tile.
     * Used for debugging.
     */
    public void displayCoordinates() {
        Log.d(TAG, "Tile[" + xCoordinate + "," + yCoordinate + "]");
    }

}
