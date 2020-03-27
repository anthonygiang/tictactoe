package com.ag.tictactoe.controller;

import com.ag.tictactoe.model.CirclePiece;
import com.ag.tictactoe.model.CrossPiece;
import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.view.GameView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class manages the game.
 */
public class GameController {

    /**
     * GameView manages the View for the game.
     */
    private GameView gameView;

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
     * Constructor takes in a GameView
     *
     * @param gv
     */
    public GameController(GameView gv) {
        gameView = gv;

        initializeControllers();
    }

    /**
     * Initializes the controller classes for this game.
     */
    private void initializeControllers() {

        turnController = new TurnController();
        Player playerOne = new Player(new CrossPiece());
        Player playerTwo = new Player(new CirclePiece());

        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);

        playerController = new PlayerController(players);

        // Initializes a GameBoard.
        GameBoard gameBoard = new GameBoard(gameView.getTiles());
        gameBoardController = new GameBoardController(gameBoard);
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
