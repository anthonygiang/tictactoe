package com.ag.tictactoe.ai;

import com.ag.tictactoe.controller.GameController;

public class GameTree {

    /**
     * The initial state of the game when the AI is prompted to make a move.
     */
    private GameTreeNode initialGameTreeNode;

    /**
     * Constructor sets the initial GameController.
     * @param gc
     */
    public GameTree(GameController gc) {
        initialGameTreeNode = new GameTreeNode(gc);
    }



}
