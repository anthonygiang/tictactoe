package com.ag.tictactoe.view;

import android.view.View;
import android.widget.Button;

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
        List<View> buttons = app.findViewById(R.id.tiles).getTouchables();
        Button button = app.findViewById(R.id.restart);

        setTiles(buttons);
        setRestart(button);
    }

    /**
     * Set the tiles in the game.
     *
     * @param buttons
     */
    private void setTiles(List<View> buttons) {
        tiles = buttons;
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
     * Sets the restart button in the game.
     *
     * @param button
     */
    private void setRestart(View button) {
        restart = button;
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
     * Returns the context for the application.
     *
     * @return
     */
    public AppCompatActivity getAppCompatActivity() {
        return app;
    }

}
