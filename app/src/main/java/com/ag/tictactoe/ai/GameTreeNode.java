package com.ag.tictactoe.ai;

import com.ag.tictactoe.controller.GameController;

/**
 * Class represents a TreeNode inside of a GameTree. Each node represents a state of the game.
 */
public class GameTreeNode extends TreeNode {

    private GameController gameController;
    private boolean win;
    private boolean tie;
    private boolean visited;
    private int minMaxValue;

    public GameTreeNode(GameController gc) {
        // TODO need to do a deep copy of the GameController.
        gameController = gc;
        win = false;
        tie = false;
        visited = false;
        minMaxValue = 2;
    }

}
