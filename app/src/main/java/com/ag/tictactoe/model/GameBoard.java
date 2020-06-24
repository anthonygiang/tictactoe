package com.ag.tictactoe.model;

import android.util.Log;
import android.view.View;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class contains information for a game board.
 * Has a reference to a 2D array of {@link Tile}.
 */
public class GameBoard {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = GameBoard.class.getName();

    /**
     * Constant for the number of rows for this board.
     */
    public static final int NUMBER_OF_TILE_ROWS = 3;

    /**
     * Constant for the number of columns for this board.
     */
    public static final int NUMBER_OF_TILE_COLS = 3;

    /**
     * 2D array for the Tile board.
     */
    private Tile[][] tileMap;

    /**
     * HashMap will map a Button id to a Tile.
     */
    private Map<Integer, Tile> boardMap;

    /**
     * Default constructor initializes the 2D array of Tile.
     */
    public GameBoard() {
        tileMap = new Tile[NUMBER_OF_TILE_ROWS][NUMBER_OF_TILE_COLS];
        boardMap = new HashMap<Integer, Tile>();

        // Reference the new Tile object to the board.
        for (int i = 0; i < NUMBER_OF_TILE_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_TILE_COLS; j++) {
                tileMap[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Constructor does a deep copy of this class.
     *
     * @param gb
     */
    public GameBoard(GameBoard gb) {
        tileMap = new Tile[gb.NUMBER_OF_TILE_COLS][gb.NUMBER_OF_TILE_ROWS];
        boardMap = new HashMap<Integer, Tile>(gb.getBoardMap());
        // Reference the new Tile object to the board.
        for (int i = 0; i < gb.NUMBER_OF_TILE_COLS; i++) {
            for (int j = 0; j < gb.NUMBER_OF_TILE_ROWS; j++) {
                tileMap[i][j] = new Tile(gb.tileMap[i][j]);
            }
        }
    }

    /**
     * Constructor initializes the 2D array of Tile and maps the Button to the Tile.
     */
    public GameBoard(Collection<View> buttons) {
        tileMap = new Tile[NUMBER_OF_TILE_ROWS][NUMBER_OF_TILE_COLS];
        boardMap = new HashMap<Integer, Tile>();
        Iterator<View> iterator = buttons.iterator();

        // Reference the new Tile object to the board.
        for (int i = 0; i < NUMBER_OF_TILE_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_TILE_COLS; j++) {
                tileMap[i][j] = new Tile(i, j);
                // Map the Button id to the Tile.
                if(iterator.hasNext()) {
                    View view = iterator.next();
                    boardMap.put(view.getId(), tileMap[i][j]);
                }
                else {
                    Log.e(TAG, "Not enough buttons detected in the game board.");
                }
            }
        }
    }

    /**
     * Returns the 2D array of Tile.
     *
     * @return
     */
    public Tile[][] getTileMap() {
        return tileMap;
    }

    /**
     * Returns the HashMap that links a button resource to a Tile.
     *
     * @return
     */
    public Map<Integer, Tile> getBoardMap() {
        return boardMap;
    }

}
