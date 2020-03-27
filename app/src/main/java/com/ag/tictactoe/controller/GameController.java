package com.ag.tictactoe.controller;

import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.GamePiece;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

/**
 * Class manages the game.
 */
public class GameController {

    /**
     * PlayerController will manage the Player.
     */
    private PlayerController playerController;

    /**
     * TurnController will manage the turns.
     */
    private TurnController turnController;

    /**
     * GameBoardController will manage the GameBoard.
     */
    private GameBoardController gameBoardController;

    /**
     * Constructor sets the PlayerController, TurnController, and GameBoardController.
     *
     * @param pc
     * @param tc
     * @param gbc
     */
    public GameController(PlayerController pc, TurnController tc, GameBoardController gbc) {
        playerController = pc;
        turnController = tc;
        gameBoardController = gbc;
    }

    /**
     * Determines if a player has won the game.
     *
     * @return
     */
    public boolean getWinCondition() {
        Player player = turnController.getPlayerTurn(playerController.getPlayers());
        return(checkWinConditionForPlayer(player));
    }

    /**
     * Checks if this player won the game.
     *
     * @param player
     * @return
     */
    private boolean checkWinConditionForPlayer(Player player) {

        GameBoard gameBoard = gameBoardController.getGameBoard();
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
     * Returns the {@link PlayerController}.
     *
     * @return
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    /**
     * Returns the {@link TurnController}.
     *
     * @return
     */
    public TurnController getTurnController() {
        return turnController;
    }

    /**
     * Returns the {@link GameBoardController}.
     *
     * @return
     */
    public GameBoardController getGameBoardController() {
        return gameBoardController;
    }

}
