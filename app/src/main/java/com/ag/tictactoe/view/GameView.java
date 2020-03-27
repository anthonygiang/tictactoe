package com.ag.tictactoe.view;

import android.content.Context;
import android.view.View;

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
    private Context context;

    /**
     * Constructor takes in the Context from the application.
     * @param c
     */
    public GameView(Context c) {
        context = c;
    }

    /**
     * Set the tiles in the game.
     * @param buttons
     */
    public void setTiles(List<View> buttons) {
        tiles = buttons;
    }

    /**
     * Returns the list of tiles in the game.
     * @return
     */
    public List<View> getTiles() {
        return tiles;
    }

    /**
     * Sets the restart button in the game.
     * @param button
     */
    public void setRestart(View button) {
        restart = button;
    }

    /**
     * Returns the restart button in the game.
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
    public Context getContext() {
        return context;
    }

}
