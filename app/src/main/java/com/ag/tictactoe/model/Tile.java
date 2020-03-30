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
    public Tile() {
        gamePiece = null;
        button = null;
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
     * Constructor copies this class.
     *
     * @param t
     */
    public Tile(Tile t) {
        gamePiece = t.gamePiece;
        button = t.getButton();
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
        Log.i(TAG, "Setting game piece - " + gp.toString() + " to Tile ");
    }

    /**
     * Returns the GamePiece that is on this Tile.
     */
    public GamePiece getGamePiece() {
        return gamePiece;
    }

}
