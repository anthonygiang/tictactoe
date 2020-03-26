package com.ag.tictactoe.controller;

import android.util.Log;

import com.ag.tictactoe.model.Player;

import java.util.List;

/**
 * Manages the turn of the game and decides which player gets to move next.
 */
public class TurnController {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = TurnController.class.getName();

    /**
     * Determine which player's turn it is.
     */
    private int turn;

    /**
     * Default constructor initializes the list of players and
     * sets the first player in the list to go first.
     */
    public TurnController() {
        turn = 0;
    }

    /**
     * Returns the Player whose turn is next.
     *
     * @return
     */
    public Player getPlayerTurn(List<Player> players) {
        if (turn < players.size()) {
            return players.get(turn);
        } else {
            Log.e(TAG, "Attempted to give a non existing player a turn.");
            return null;
        }
    }

    /**
     * Move the turn to the next player on the list.
     */
    public void changeTurn(List<Player> players) {
        if (turn < players.size() - 1) {
            turn++;
        } else {
            turn = 0;
        }
    }

}
