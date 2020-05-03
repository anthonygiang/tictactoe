package com.ag.tictactoe;

import com.ag.tictactoe.controller.PlayerController;
import com.ag.tictactoe.model.CirclePiece;
import com.ag.tictactoe.model.CrossPiece;
import com.ag.tictactoe.model.Player;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Contain tests for {@link PlayerController} class.
 */
public class PlayerControllerTest {

    private static PlayerController pc;
    private static Player playerTurn;

    private static Player playerOne;
    private static Player playerTwo;

    /**
     * Create {@link Player}s to be used for testing.
     */
    @BeforeClass
    public static void beforeClassSetup() {
        pc = new PlayerController();
        playerTurn = null;
        playerOne = new Player("Player One", new CrossPiece());
        playerTwo = new Player("Player Two", new CirclePiece());
    }

    /**
     * Test that the first player added gets the first turn by default.
     */
    @Test
    public void testPlayerTurn() {

        playerTurn = pc.getPlayerTurn();

        // Variation - Test that this returns null since no players have been added
        // to the game yet.
        assertTrue(playerTurn == null);

        pc.addPlayer(playerOne);
        playerTurn = pc.getPlayerTurn();

        // Variation - Test that the first player added has the next turn.
        assertTrue(playerTurn.equals(playerOne));

        pc.addPlayer(playerTwo);
        playerTurn = pc.getPlayerTurn();

        // Variation - Test that the first player added has the next turn.
        assertTrue(playerTurn.equals(playerOne));
    }

    /**
     * Tests that changing turns selects the correct player.
     */
    @Test
    public void testChangingPlayerTurn() {

        pc.addPlayer(playerOne);
        pc.addPlayer(playerTwo);

        playerTurn = pc.getPlayerTurn();

        // Variation - Test that the first player added has the next turn.
        assertTrue(playerTurn.equals(playerOne));

        pc.changeTurn();
        playerTurn = pc.getPlayerTurn();

        // Variation - Test that the next player has the turn.
        assertTrue(playerTurn.equals(playerTwo));

        pc.changeTurn();
        playerTurn = pc.getPlayerTurn();

        // Variation - Test that the next player has the turn.
        assertTrue(playerTurn.equals(playerOne));
    }

}
