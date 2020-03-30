package com.ag.tictactoe.view;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ag.tictactoe.R;

import java.util.List;

/**
 * Class manages the View for this game.
 */
public class GameView {

    /**
     * List of views for the tiles in the game.
     */
    private List<View> tiles;

    /**
     * View for restarting the game.
     */
    private View restart;

    /**
     * Button to start game against AI.
     */
    private View aiButton;

    /**
     * Context for the application.
     */
    private AppCompatActivity app;

    /**
     * Constructor takes in the Context from the application.
     *
     * @param a
     */
    public GameView(AppCompatActivity a) {
        app = a;

        // Retrieve all the buttons inside the grid layout.
        tiles = app.findViewById(R.id.tiles).getTouchables();
        restart = app.findViewById(R.id.restart);
        aiButton = app.findViewById(R.id.ai);
    }

    /**
     * Disable clicking on the Tiles.
     */
    public void disallowClickForTiles() {
        // Iterate through all the buttons and make them not clickable.
        for (View button : tiles) {
            button.setClickable(false);
        }
    }

    /**
     * Returns the list of tiles in the game.
     *
     * @return
     */
    public List<View> getTiles() {
        return tiles;
    }

    /**
     * Returns the restart button in the game.
     *
     * @return
     */
    public View getRestart() {
        return restart;
    }

    /**
     * Returns the AI button in the game.
     *
     * @return
     */
    public View getAIButton() {
        return aiButton;
    }

    /**
     * Returns the context for the application.
     *
     * @return
     */
    public AppCompatActivity getAppCompatActivity() {
        return app;
    }

}
