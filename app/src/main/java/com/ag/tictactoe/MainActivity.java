package com.ag.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.ag.tictactoe.controller.TurnController;
import com.ag.tictactoe.model.CirclePiece;
import com.ag.tictactoe.model.CrossPiece;
import com.ag.tictactoe.model.GameBoard;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

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
     * Reference to the GameBoard.
     */
    private GameBoard gameBoard;

    /**
     * Manages which player gets to move next.
     */
    private TurnController turnController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnController = new TurnController();
        Player playerOne = new Player(new CrossPiece());
        Player playerTwo = new Player(new CirclePiece());

        turnController.addPlayer(playerOne);
        turnController.addPlayer(playerTwo);

        setUpTiles();
    }

    /**
     * Set up button listeners for the buttons.
     */
    private void setUpTiles() {

        // Retrieve all the buttons inside the grid layout.
        List<View> buttons = ((GridLayout) findViewById(R.id.tiles)).getTouchables();

        // Initializes a GameBoard.
        gameBoard = new GameBoard(buttons);

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
     * Sets up an on click listener for this button.
     *
     * @param button
     */
    private void setUpClickListenerForTile(ImageButton button) {

        // Retrieves the Tile referenced by this button.
        Tile tile = gameBoard.getTileFromId(button.getId());
        if(tile != null) {

            // Check if the Tile is already occupied and prompt player to select again.
            if(tile.getIsOccupied()) {
                // TODO Message user to select another tile.

            }
            // Place player's game piece on the Tile.
            else {
                // Determine which player gets to move next.
                Player player = turnController.getPlayerTurn();

                if(player != null) {

                    // Sets the player's game piece on this tile and sets the image.
                    tile.setGamePiece(player.getGamePiece());
                    button.setImageResource(player.getGamePiece().getDrawable());

                    // Check if a player has won the game.
                    if(gameBoard.getWinCondition() == true) {
                        // TODO Handle win condition.

                    }

                    // Move to the next player's turn.
                    turnController.changeTurn();
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
