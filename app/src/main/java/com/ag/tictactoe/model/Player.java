package com.ag.tictactoe.model;

/**
 * Class represents information for a player.
 */
public class Player {

    /**
     * Name of the player.
     */
    private String name;

    /**
     * GamePiece that belongs to the Player.
     */
    private GamePiece gamePiece;

    /**
     * Constructor initializes the player with a Game Piece.
     *
     * @param gp
     */
    public Player(String n, GamePiece gp) {
        name = n;
        gamePiece = gp;
    }

    /**
     * Constructor copies a {@link Player} instance.
     * @param p
     */
    public Player(Player p) {
        name = p.getName();
        gamePiece = p.getGamePiece();
    }

    /**
     * Returns the name of the player.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the GamePiece that the Player owns.
     *
     * @return
     */
    public GamePiece getGamePiece() {
        return gamePiece;
    }

}
