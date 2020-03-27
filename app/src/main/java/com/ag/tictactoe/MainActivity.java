package com.ag.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ag.tictactoe.controller.GameBoardController;
import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.controller.PlayerController;
import com.ag.tictactoe.controller.TurnController;
import com.ag.tictactoe.model.CirclePiece;
import com.ag.tictactoe.model.CrossPiece;
import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

import java.util.ArrayList;
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
     * PlayerController manages the player.
     */
    private PlayerController playerController;

    /**
     * TurnController manages which player gets to move next.
     */
    private TurnController turnController;

    /**
     * GameBoardController manages the GameBoard.
     */
    private GameBoardController gameBoardController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnController = new TurnController();
        Player playerOne = new Player(new CrossPiece());
        Player playerTwo = new Player(new CirclePiece());

        List<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);

        playerController = new PlayerController(players);

        setUpTiles();
    }

    /**
     * Set up button listeners for the buttons.
     */
    private void setUpTiles() {

        // Retrieve all the buttons inside the grid layout.
        List<View> buttons = findViewById(R.id.tiles).getTouchables();

        // Initializes a GameBoard.
        GameBoard gameBoard = new GameBoard(buttons);
        gameBoardController = new GameBoardController(gameBoard);

        gameController = new GameController(playerController, turnController, gameBoardController);

        // Iterate through all the views and assign a click listener.
        for (View button : buttons) {
            button.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setUpClickListenerForTile((ImageButton) v);
                }
            }));
        }
    }

    /**
     * Disable clicking on the Tiles.
     */
    private void disallowClickForTiles() {

        // Retrieve all the buttons inside the grid layout.
        List<View> buttons = findViewById(R.id.tiles).getTouchables();

        // Iterate through all the views and assign a click listener.
        for (View button : buttons) {
            button.setClickable(false);
        }
    }

    /**
     * Sets up an on click listener for this button.
     *
     * @param button
     */
    private void setUpClickListenerForTile(ImageButton button) {

        // Retrieves the Tile referenced by this button.
        Tile tile = gameBoardController.getTileFromId(button.getId());
        if(tile != null) {

            // Check if the Tile is already occupied and prompt player to select again.
            if(tile.getIsOccupied()) {
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

                if(player != null) {

                    // Sets the player's game piece on this tile and sets the image.
                    tile.setGamePiece(player.getGamePiece());
                    button.setImageResource(player.getGamePiece().getDrawable());

                    // Check if a player has won the game.
                    if(gameController.getWinCondition() == true) {
                        Context context = getApplicationContext();
                        String toastText = "Win!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, toastText, duration);
                        toast.show();

                        disallowClickForTiles();
                    }
                    else {
                        // Move to the next player's turn.
                        turnController.changeTurn(playerController.getPlayers());
                    }
                }
                else {
                    Log.e(TAG, "Unable to find player.");
                }
            }
        }
        else {
            Log.e(TAG, "Unable to find tile.");
        }
    }

}
