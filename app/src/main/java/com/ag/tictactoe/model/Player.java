package com.ag.tictactoe.model;

/**
 * Class represents information for a player.
 */
public class Player {

    /**
     * GamePiece that belongs to the Player.
     */
    private GamePiece gamePiece;

    /**
     * Constructor initializes the player with a Game Piece.
     *
     * @param gp
     */
    public Player(GamePiece gp) {
        gamePiece = gp;
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
