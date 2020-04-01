package com.ag.tictactoe.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.GamePiece;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * Constructor does a deep copy of this class.
     *
     * @param gbc
     */
    public GameBoardController(GameBoardController gbc) {
       gameBoard = new GameBoard(gbc.getGameBoard());
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
     * Returns a List of empty tiles. These are tiles that a player can still make a move on.
     *
     * @return
     */
    public List<Tile> getEmptyTileList() {

        List<Tile> emptyTileList = new ArrayList<Tile>();

        for (int i = 0; i < gameBoard.NUMBER_OF_TILE_ROWS; i++) {
            for (int j = 0; j < gameBoard.NUMBER_OF_TILE_COLS; j++) {
                Tile tile = gameBoard.getTileMap()[i][j];
                if(!tile.getIsOccupied()) {
                    emptyTileList.add(tile);
                }
            }
        }

        return emptyTileList;
    }

    /**
     * Sets the button referenced from each Tile.
     * @param buttons
     */
    public void mapButtonsToTiles(List<View> buttons) {

        for (View button : buttons) {
            Tile tile = getTileFromId(button.getId());
            if(tile != null) {
                tile.setButton((ImageButton) button);
            }
            else {
                Log.e(TAG, "Unable to find Tile from Button id.");
            }
        }
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
     * Retrieves the Tile from this GameBoard from the coordinates
     * of another Tile.
     *
     * @param t
     * @return
     */
    public Tile getTileFromOtherGameBoardTile(Tile t) {
        int x = t.getXCoordinate();
        int y = t.getYCoordinate();

        Tile[][] tileMap = gameBoard.getTileMap();

        return tileMap[x][y];
    }


    /**
     * Checks if this player won the game.
     *
     * @param player
     * @return
     */
    public boolean getWinConditionForPlayer(Player player) {

        Tile[][] map = gameBoard.getTileMap();
        GamePiece gamePiece = player.getGamePiece();

        // Check rows.
        if(map[0][0].getGamePiece() == gamePiece &&
                map[0][1].getGamePiece() == gamePiece &&
                map[0][2].getGamePiece() == gamePiece) {
            return true;
        }
        if(map[1][0].getGamePiece() == gamePiece &&
                map[1][1].getGamePiece() == gamePiece &&
                map[1][2].getGamePiece() == gamePiece) {
            return true;
        }
        if(map[2][0].getGamePiece() == gamePiece &&
                map[2][1].getGamePiece() == gamePiece &&
                map[2][2].getGamePiece() == gamePiece) {
            return true;
        }

        // Check columns.
        if(map[0][0].getGamePiece() == gamePiece &&
                map[1][0].getGamePiece() == gamePiece &&
                map[2][0].getGamePiece() == gamePiece) {
            return true;
        }
        if(map[0][1].getGamePiece() == gamePiece &&
                map[1][1].getGamePiece() == gamePiece &&
                map[2][1].getGamePiece() == gamePiece) {
            return true;
        }
        if(map[0][2].getGamePiece() == gamePiece &&
                map[1][2].getGamePiece() == gamePiece &&
                map[2][2].getGamePiece() == gamePiece) {
            return true;
        }

        // Check diagonals.
        if(map[0][0].getGamePiece() == gamePiece &&
                map[1][1].getGamePiece() == gamePiece &&
                map[2][2].getGamePiece() == gamePiece) {
            return true;
        }
        if(map[2][0].getGamePiece() == gamePiece &&
                map[1][1].getGamePiece() == gamePiece &&
                map[0][2].getGamePiece() == gamePiece) {
            return true;
        }

        return false;
    }

    /**
     * Displays the current GameBoard.
     * Used for debugging.
     */
    public void displayGameBoard() {
        String gameBoardString = "Current Game Board:\n";
        for (int i = 0; i < gameBoard.NUMBER_OF_TILE_ROWS; i++) {
            for (int j = 0; j < gameBoard.NUMBER_OF_TILE_COLS; j++) {
                if(gameBoard.getTileMap()[i][j].getGamePiece() == null) {
                    gameBoardString += "[ ]";
                }
                else {
                    gameBoardString += "[";
                    String gamePiece = gameBoard.getTileMap()[i][j].getGamePiece().toString();
                    if(gamePiece.contains("Cross")) {
                        gameBoardString += "X";
                    }
                    else if(gamePiece.contains("Circle")) {
                        gameBoardString += "O";
                    }
                    gameBoardString += "]";
                }
            }
            gameBoardString += "\n";
        }
        Log.d(TAG, gameBoardString);
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
