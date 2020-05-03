package com.ag.tictactoe.controller;

import android.util.Log;

import com.ag.tictactoe.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Class manages the players in the game.
 */
public class PlayerController {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = PlayerController.class.getName();

    /**
     * List of players in the game.
     */
    private List<Player> players;

    /**
     * Player who has the current turn.
     */
    private Player playerTurn;

    /**
     * Constructor allocates a new list of potential players.
     */
    public PlayerController() {
        players = new ArrayList<Player>();
        playerTurn = null;
    }

    /**
     * Constructor copies the contents of the {@link PlayerController} passed in.
     *
     * @param pc
     */
    public PlayerController(PlayerController pc) {
        players = new ArrayList<Player>();
        for (Player p : pc.players) {
            Player copiedPlayer = new Player(p);
            players.add(copiedPlayer);
        }
        int indexOfTurn = pc.players.indexOf(pc.playerTurn);
        playerTurn = players.get(indexOfTurn);
    }

    /**
     * Adds a player to the game.
     *
     * @param p
     */
    public void addPlayer(Player p) {
        players.add(p);
        // If this is the first player added to the game then give them the first turn by default.
        if (playerTurn == null) {
            playerTurn = p;
        }
    }

    /**
     * Returns the Player who has the current turn.
     *
     * @return
     */
    public Player getPlayerTurn() {
        // Make sure players still exist in the game.
        if (!players.isEmpty()) {
            if (playerTurn != null) {
                return playerTurn;
            } else {
                Log.e(TAG, "Unable to determine the player who has the current turn.");
            }
        } else {
            Log.e(TAG, "No players remaining in the game.");
        }

        return playerTurn;
    }

    /**
     * Move the turn to the next player on the list.
     */
    public void changeTurn() {

        // Check if there any remaining players left.
        if (!players.isEmpty()) {
            // Check if only one player exists in the game.
            // Can't change turns if there's only one player left, give that single player the
            // next turn and log a warning.
            if (players.size() == 1) {
                Log.w(TAG, "Unable to change turns because only one player remains in the game. Defaulting turn to that player.");
                playerTurn = players.get(0);
            } else {
                // Check that we still have the reference to the last player who had a turn.
                // If we lost it, then default to the first player in the list of players and
                // log an error.
                if (playerTurn != null) {
                    int index = players.indexOf(playerTurn);
                    // The next player who has a turn is the next player in the list of added
                    // players. Once the turn reaches the end of the list, the first player
                    // has the next turn.
                    if (index < (players.size() - 1)) {
                        index++;
                    } else {
                        index = 0;
                    }
                    playerTurn = players.get(index);
                } else {
                    Log.e(TAG, "Unable to determine the player who has the current turn, defaulting to the first player added.");
                    playerTurn = players.get(0);
                }
            }
        } else {
            Log.e(TAG, "No players remaining in the game.");
        }
    }

}
