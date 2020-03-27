package com.ag.tictactoe.model;

import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.List;

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
    private static final int NUMBER_OF_TILE_ROWS = 3;

    /**
     * Constant for the number of columns for this board.
     */
    private static final int NUMBER_OF_TILE_COLS = 3;

    /**
     * 2D array for the Tile board.
     */
    private Tile[][] tileMap;

    /**
     * HashMap will map a Button id to a Tile.
     */
    private HashMap<Integer, Tile> boardHashMap;

    /**
     * Default constructor initializes the 2D array of Tile.
     */
    public GameBoard() {
        tileMap = new Tile[NUMBER_OF_TILE_ROWS][NUMBER_OF_TILE_COLS];
        boardHashMap = new HashMap<Integer, Tile>();

        // Reference the new Tile object to the board.
        for (int i = 0; i < NUMBER_OF_TILE_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_TILE_COLS; j++) {
                tileMap[i][j] = new Tile();
            }
        }
    }

    /**
     * Constructor initializes the 2D array of Tile and maps the Button to the Tile.
     */
    public GameBoard(List<View> buttons) {
        tileMap = new Tile[NUMBER_OF_TILE_ROWS][NUMBER_OF_TILE_COLS];
        boardHashMap = new HashMap<Integer, Tile>();

        int buttonCount = 0;

        // Reference the new Tile object to the board.
        for (int i = 0; i < NUMBER_OF_TILE_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_TILE_COLS; j++) {
                tileMap[i][j] = new Tile();
                // Map the Button id to the Tile.
                if (buttonCount < buttons.size()) {
                    boardHashMap.put(buttons.get(buttonCount).getId(), tileMap[i][j]);
                    buttonCount++;
                } else {
                    Log.e(TAG, "Extra buttons detected in the game board.");
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
    public HashMap<Integer, Tile> getBoardHashMap() {
        return boardHashMap;
    }

}
