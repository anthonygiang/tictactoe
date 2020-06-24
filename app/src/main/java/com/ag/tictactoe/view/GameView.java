package com.ag.tictactoe.view;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ag.tictactoe.R;

import java.util.Collection;

/**
 * Class manages the View for this game.
 */
public class GameView {

    /**
     * Collection of views for the tiles in the game.
     */
    private Collection<View> tiles;

    /**
     * View for restarting the game.
     */
    private View restart;

    /**
     * Button to start game against AI.
     */
    private View aiButton;

    /**
     * Prompts user to decide who goes first in a game against the AI.
     */
    private AlertDialog.Builder turn;

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
        turn = new AlertDialog.Builder(app);

        turn.setMessage("Go First or Second against the AI?");
        turn.setCancelable(true);
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
     * Sets the ImageButton's image resource to a drawable resource.
     *
     * @param button
     * @param drawableResource
     */
    public void setButtonImage(ImageButton button, int drawableResource) {
        button.setImageResource(drawableResource);
    }

    /**
     * Returns the Collection of tiles in the game.
     *
     * @return
     */
    public Collection<View> getTiles() {
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
     * Returns prompt to decide who goes first in a game against the AI.
     *
     * @return
     */
    public AlertDialog.Builder getTurn() {
        return turn;
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
