package com.ag.tictactoe.controller;

import com.ag.tictactoe.model.Player;

import java.util.List;

/**
 * Class manages the players in the game.
 */
public class PlayerController {

    /**
     * List of players in the game.
     */
    private List<Player> players;

    /**
     * Constructor sets the players.
     * @param p
     */
    public PlayerController(List<Player> p) {
        players = p;
    }

    /**
     * Returns the list of players.
     *
     * @return
     */
    public List<Player> getPlayers() {
        return players;
    }

}
