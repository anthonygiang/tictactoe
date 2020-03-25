package com.ag.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.ag.tictactoe.model.GameBoard;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    setUpClickListenerForTile((Button) v);
                }
            }));
        }
    }

    /**
     * Sets up an on click listener for this button.
     *
     * @param tile
     */
    private void setUpClickListenerForTile(Button tile) {
        Log.i(TAG, "Button pressed for: " + tile.getText());
    }

}
