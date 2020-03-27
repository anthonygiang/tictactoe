package com.ag.tictactoe.controller;

import com.ag.tictactoe.model.CirclePiece;
import com.ag.tictactoe.model.CrossPiece;
import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.GamePiece;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;
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
