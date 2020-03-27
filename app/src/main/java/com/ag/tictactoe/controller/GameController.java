package com.ag.tictactoe.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ag.tictactoe.model.CirclePiece;
import com.ag.tictactoe.model.CrossPiece;
import com.ag.tictactoe.model.GameBoard;
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
     * TurnController will manage the turns.
     */
    private TurnController turnController;

    /**
     * GameBoardController will manage the GameBoard.
     */
    private GameBoardController gameBoardController;

    /**
     * Constructor takes in a GameView and sets up the game.
     *
     * @param gv
     */
    public GameController(GameView gv) {
        gameView = gv;
        initializeGame();
        setUpRestart();
    }

    /**
     * Initializes the game.
     */
    private void initializeGame() {
        initializeControllers();
        setUpTiles();
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
     * Sets up the Restart button.
     */
    private void setUpRestart() {
        Button button = (Button) gameView.getRestart();
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeGame();
            }
        }));
    }

    /**
     * Set up button listeners for the buttons.
     */
    private void setUpTiles() {

        // Iterate through all the views and assign a click listener.
        for (View button : gameView.getTiles()) {
            button.setClickable(true);
            ImageButton imageButton = (ImageButton) button;
            imageButton.setImageResource(android.R.color.transparent);
            button.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkTileClick((ImageButton) v);
                }
            }));
        }
    }

    /**
     * Disable clicking on the Tiles.
     */
    private void disallowClickForTiles() {

        // Iterate through all the views and assign a click listener.
        for (View button : gameView.getTiles()) {
            button.setClickable(false);
        }
    }

    /**
     * Sets up an on click listener for this button.
     *
     * @param button
     */
    private void checkTileClick(ImageButton button) {

        // Retrieves the Tile referenced by this button.
        Tile tile = gameBoardController.getTileFromId(button.getId());
        if (tile != null) {

            // Check if the Tile is already occupied and prompt player to select again.
            if (tile.getIsOccupied()) {
                Context context = gameView.getAppCompatActivity().getApplicationContext();
                String toastText = "Select another tile.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, toastText, duration);
                toast.show();
            }
            // Place player's game piece on the Tile.
            else {
                // Determine which player gets to move next.
                Player player = turnController.getPlayerTurn(playerController.getPlayers());

                if (player != null) {

                    // Sets the player's game piece on this tile and sets the image.
                    tile.setGamePiece(player.getGamePiece());
                    button.setImageResource(player.getGamePiece().getDrawable());

                    // Check if a player has won the game.
                    if (gameBoardController.getWinConditionForPlayer(player) == true) {
                        Context context = gameView.getAppCompatActivity().getApplicationContext();
                        String toastText = "Win!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, toastText, duration);
                        toast.show();

                        disallowClickForTiles();
                    }
                    // Check if the game resulted in a tie.
                    else if (gameBoardController.getStalemateCondition()) {
                        Context context = gameView.getAppCompatActivity().getApplicationContext();
                        String toastText = "Stalemate!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, toastText, duration);
                        toast.show();

                        disallowClickForTiles();
                    } else {
                        // Move to the next player's turn.
                        turnController.changeTurn(playerController.getPlayers());
                    }
                } else {
                    Log.e(TAG, "Unable to find player.");
                }
            }
        } else {
            Log.e(TAG, "Unable to find tile.");
        }
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

    /**
     * Returns the {@link GameView}.
     *
     * @return
     */
    public GameView getGameView() {
        return gameView;
    }

}
