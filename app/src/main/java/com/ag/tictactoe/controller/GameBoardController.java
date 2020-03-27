package com.ag.tictactoe.controller;

import android.util.Log;

import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.Tile;

import java.util.HashMap;

/**
 * Class manages the {@link GameBoard}.
 */
public class GameBoardController {


    /**
     * Tag for logging messages.
     */
    private static final String TAG = GameBoardController.class.getName();

    /**
     * Reference to the GameBoard.
     */
    private GameBoard gameBoard;

    /**
     * Constructor sets the GameBoard.
     *
     * @param gb
     */
    public GameBoardController(GameBoard gb) {
        gameBoard = gb;
    }

    /**
     * Returns the GameBoard.
     *
     * @return
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Retrieves the Tile using the Button Id.
     *
     * @param id
     * @return
     */
    public Tile getTileFromId(int id) {

        HashMap<Integer, Tile> boardHashMap = gameBoard.getBoardHashMap();

        if (boardHashMap.containsKey(id)) {
            return boardHashMap.get(id);
        } else {
            Log.e(TAG, "Unable to retrieve Tile from id.");
            return null;
        }
    }

    /**
     * Returns true if the game is a tie.
     *
     * @return
     */
    public boolean getStalemateCondition() {

        Tile[][] map = gameBoard.getTileMap();

        for (int i = 0; i < gameBoard.NUMBER_OF_TILE_ROWS; i++) {
            for (int j = 0; j < gameBoard.NUMBER_OF_TILE_COLS; j++) {
                if(!map[i][j].getIsOccupied()) {
                    return false;
                }
            }
        }

        return true;
    }

}
