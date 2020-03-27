package com.ag.tictactoe;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ag.tictactoe.controller.GameBoardController;
import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.controller.PlayerController;
import com.ag.tictactoe.controller.TurnController;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;
import com.ag.tictactoe.view.GameView;

import java.util.List;

/**
 * Runs a Tic Tac Toe game against a person or AI.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = MainActivity.class.getName();

    /**
     * GameController manages the game.
     */
    private GameController gameController;

    /**
     * GameView manages the View for the game.
     */
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        initializeGame();
        setUpRestart();
    }

    /**
     * Initializes the View for the game.
     */
    private void initializeView() {
        // Retrieve all the buttons inside the grid layout.
        List<View> buttons = findViewById(R.id.tiles).getTouchables();
        Button button = findViewById(R.id.restart);

        gameView = new GameView();
        gameView.setTiles(buttons);
        gameView.setRestart(button);
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
     * Initializes the controller classes for this game.
     */
    private void initializeGame() {
        gameController = new GameController(gameView);
        setUpTiles();
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

        PlayerController playerController = gameController.getPlayerController();
        TurnController turnController = gameController.getTurnController();
        GameBoardController gameBoardController = gameController.getGameBoardController();

        // Retrieves the Tile referenced by this button.
        Tile tile = gameBoardController.getTileFromId(button.getId());
        if (tile != null) {

            // Check if the Tile is already occupied and prompt player to select again.
            if (tile.getIsOccupied()) {
                Context context = getApplicationContext();
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
                    if (gameController.getWinCondition() == true) {
                        Context context = getApplicationContext();
                        String toastText = "Win!";
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

}
