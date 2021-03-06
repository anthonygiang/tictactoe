package com.ag.tictactoe.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ag.tictactoe.ai.GameTree;
import com.ag.tictactoe.model.CirclePiece;
import com.ag.tictactoe.model.CrossPiece;
import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;
import com.ag.tictactoe.view.GameView;

/**
 * Class manages the game.
 */
public class GameController {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = GameController.class.getName();

    /**
     * GameView manages the View for the game.
     */
    private GameView gameView;

    /**
     * PlayerController will manage the Player.
     */
    private PlayerController playerController;

    /**
     * GameBoardController will manage the GameBoard.
     */
    private GameBoardController gameBoardController;

    /**
     * Enables the AI as an opponent.
     */
    private boolean aiEnabled;

    /**
     * Go first against the AI.
     */
    private boolean moveFirstAgainstAI;

    /**
     * Name of the AI player.
     */
    public final String AI_NAME = "AI";

    /**
     * Constructor takes in a GameView and sets up the game.
     *
     * @param gv
     */
    public GameController(GameView gv) {
        gameView = gv;
        initializeGame(false);
        setUpRestart();
        setUpAI();
    }

    /**
     * Constructor does a deep copy of the GameBoardController.
     *
     * @param gc
     */
    public GameController(GameController gc) {
        gameView = gc.gameView;
        playerController = new PlayerController(gc.playerController);
        gameBoardController = new GameBoardController(gc.gameBoardController);
    }

    /**
     * Initializes the game.
     */
    private void initializeGame(boolean ai) {
        aiEnabled = ai;
        initializeControllers();
        setUpTiles();

        // Make the AI move first if they have the first turn of the game.
        if (aiEnabled == true && moveFirstAgainstAI == false) {
            setAIMove();
            checkEndGameConditions();
        }
    }

    /**
     * Initializes the controller classes for this game.
     */
    private void initializeControllers() {

        Player playerOne = null;
        Player playerTwo = null;

        // Set up AI player.
        if (aiEnabled) {
            // Set which player goes first playing against AI.
            if (moveFirstAgainstAI) {
                playerOne = new Player("Player One", new CrossPiece());
                playerTwo = new Player(AI_NAME, new CirclePiece());
            } else {
                playerOne = new Player(AI_NAME, new CrossPiece());
                playerTwo = new Player("Player Two", new CirclePiece());
            }
        }
        // Set up the game with local players.
        else {
            playerOne = new Player("Player One", new CrossPiece());
            playerTwo = new Player("Player Two", new CirclePiece());
        }

        playerController = new PlayerController();
        playerController.addPlayer(playerOne);
        playerController.addPlayer(playerTwo);

        // Initializes a GameBoard.
        GameBoard gameBoard = new GameBoard(gameView.getTiles());
        gameBoardController = new GameBoardController(gameBoard);
    }

    /**
     * Starts the game against AI.
     */
    private void startGameAgainstAI() {

        aiEnabled = true;

        // Prompt the user to decide who goes first in a game against the AI.
        AlertDialog.Builder turn = gameView.getTurn();
        turn.setNegativeButton(
                "First",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveFirstAgainstAI = true;
                        initializeGame(true);
                        dialog.cancel();
                    }
                });
        turn.setPositiveButton(
                "Second",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveFirstAgainstAI = false;
                        initializeGame(true);
                        dialog.cancel();
                    }
                });
        AlertDialog alert1 = turn.create();
        alert1.show();
    }

    /**
     * Determine and make the AI move.
     */
    private void setAIMove() {

        // Get current state of the board.
        // Determine possible moves.
        Player player = playerController.getPlayerTurn();
        GameTree gameTree = new GameTree(this);
        Tile bestTile = gameTree.getBestTileMove();

        if (bestTile != null) {
            Tile currentTile = gameBoardController.getTileFromOtherGameBoardTile(bestTile);

            if (currentTile != null) {
                makePlayerMove(player, currentTile);

                ImageButton button = currentTile.getButton();
                gameView.setButtonImage(button, player.getGamePiece().getDrawable());
            }
        } else {
            Log.e(TAG, "Unable to determine best move for AI.");
        }

    }

    /**
     * Sets up the AI button.
     */
    private void setUpAI() {
        Button button = (Button) gameView.getAIButton();
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameAgainstAI();
            }
        }));
    }

    /**
     * Sets up the Restart button.
     */
    private void setUpRestart() {
        Button button = (Button) gameView.getRestart();
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeGame(false);
            }
        }));
    }

    /**
     * Set up button listeners for the buttons.
     */
    private void setUpTiles() {

        // Iterate through all the views and assign a click listener.
        for (final View button : gameView.getTiles()) {
            button.setClickable(true);
            final ImageButton imageButton = (ImageButton) button;
            imageButton.setImageResource(android.R.color.transparent);
            button.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieves the Tile referenced by this button.
                    Tile tile = gameBoardController.getTileFromId(button.getId());
                    if (tile != null) {
                        // Check if the Tile is unoccupied and place player's game piece on the Tile.
                        if (!tile.getIsOccupied()) {

                            // Determine which player gets to move next.
                            Player player = playerController.getPlayerTurn();

                            if (makePlayerMove(player, tile)) {
                                gameView.setButtonImage(imageButton, player.getGamePiece().getDrawable());
                                if (!checkEndGameConditions()) {
                                    if (aiEnabled) {
                                        setAIMove();
                                        checkEndGameConditions();
                                    }
                                }
                            }
                        }
                        // Display message indicating to select an unoccupied tile.
                        else {
                            Context context = gameView.getAppCompatActivity().getApplicationContext();
                            String toastText = "Select another tile.";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, toastText, duration);
                            toast.show();
                        }
                    } else {
                        Log.e(TAG, "Unable to find tile.");
                    }
                }
            }));
        }
        gameBoardController.mapButtonsToTiles(gameView.getTiles());

    }

    /**
     * Returns true if the game has ended.
     * Set end game functions in place if it has ended or move to the next
     * player's turn.
     *
     * @return
     */
    private boolean checkEndGameConditions() {

        Player player = playerController.getPlayerTurn();

        // Check if a player has won the game.
        if (gameBoardController.getWinConditionForPlayer(player) == true) {
            Context context = gameView.getAppCompatActivity().getApplicationContext();
            String toastText = player.getName() + " Wins!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, toastText, duration);
            toast.show();

            gameView.disallowClickForTiles();
            return true;
        }
        // Check if the game resulted in a tie.
        else if (gameBoardController.getStalemateCondition()) {
            Context context = gameView.getAppCompatActivity().getApplicationContext();
            String toastText = "Stalemate!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, toastText, duration);
            toast.show();

            gameView.disallowClickForTiles();
            return true;
        } else {
            // Move to the next player's turn.
            playerController.changeTurn();
        }
        return false;
    }

    /**
     * Sets the player's GamePiece on the Tile.
     *
     * @param tile
     * @return
     */
    public boolean makePlayerMove(Player player, Tile tile) {

        if (player != null) {
            // Sets the player's game piece on this Tile and sets the image.
            tile.setGamePiece(player.getGamePiece());
            return true;
        } else {
            Log.e(TAG, "Unable to find player.");
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
     * Returns the {@link GameBoardController}.
     *
     * @return
     */
    public GameBoardController getGameBoardController() {
        return gameBoardController;
    }

    /**
     * Returns the {@link GameView}.
     *
     * @return
     */
    public GameView getGameView() {
        return gameView;
    }

}
