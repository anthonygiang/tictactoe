package com.ag.tictactoe.controller;

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

        // TODO implement

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
