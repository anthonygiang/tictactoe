package com.ag.tictactoe.ai;

import com.ag.tictactoe.controller.GameController;

/**
 * Class represents a TreeNode inside of a GameTree. Each node represents a state of the game.
 */
public class GameTreeNode extends TreeNode {

    /**
     * References a state of the game.
     */
    private GameController gameController;

    /**
     * Indicates this node resulted in a win.
     */
    private boolean win;

    /**
     * Indicates this node resulted in a tie.
     */
    private boolean tie;

    /**
     * Indicates this node was visited by a search algorithm.
     */
    private boolean visited;

    /**
     * Indicates the min max value.
     */
    private int minMaxValue;

    /**
     * Constructor initializes this node with a state of the GameController.
     *
     * @param gc
     */
    public GameTreeNode(GameController gc) {
        gameController = new GameController(gc);
        win = false;
        tie = false;
        visited = false;
        minMaxValue = 0;
    }

    /**
     * Returns the GameController.
     *
     * @return
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Sets whether this node resulted in a win.
     * @param b
     */
    public void setWinNode(boolean b) {
        win = b;
    }

    /**
     * Sets whether this node resulted in a tie.
     *
     * @param b
     */
    public void setTieNode(boolean b) {
        tie = b;
    }

}
