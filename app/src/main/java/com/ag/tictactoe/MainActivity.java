package com.ag.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.List;

/**
 * Runs a Tic Tac Toe game against an AI.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTiles();
    }

    /**
     * Set up button listeners for the tiles.
     */
    private void setUpTiles() {

        // Retrieve all the buttons inside the grid layout.
        List<View> allTiles = ((GridLayout) findViewById(R.id.tiles)).getTouchables();

        // Iterate through all the views and assign a click listener.
        for (View tile : allTiles) {
            tile.setOnClickListener((new View.OnClickListener() {
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
