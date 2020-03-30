package com.ag.tictactoe.ai;

import android.content.Context;

import com.ag.tictactoe.controller.GameBoardController;
import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.model.Tile;

import java.util.List;

/**
 * Class will use depth first search and determine all possible outcomes for the game. This new
 * will then be used to determine the best move the AI needs to take to try and win the game.
 */
public class GameTree {

    /**
     * The initial state of the game when the AI is prompted to make a move.
     * This is the root of the GameTree.
     */
    private GameTreeNode initialGameTreeNode;

    /**
     * Constructor sets the initial GameController.
     * @param gc
     */
    public GameTree(GameController gc) {
        initialGameTreeNode = new GameTreeNode(gc);
    }

    /**
     * Returns the initial GameTreeNode. This is the root of the GameTree.
     * @return
     */
    public GameTreeNode getInitialGameTreeNode() {
        return initialGameTreeNode;
    }

    /**w
     * Uses depth first search to populate the GameTree.
     *
     * @param node
     */
    private void buildGameTree(GameTreeNode node) {

//        GameController gc = node.getGameController();
//        GameBoardController gbc = gc.getGameBoardController();
//
//
//                List<Tile> possibleTiles =
    }

    /**
     * Returns a Tile that the AI will place a GamePiece on.
     *
     *
     * @param node
     * @param context
     * @return
     */
    public Tile determinePossibleMove(GameTreeNode node, Context context) {

        GameController gc = node.getGameController();
        GameBoardController gbc = gc.getGameBoardController();

        List<Tile> emptyTileList = gbc.getEmptyTileList(context);

        if(!emptyTileList.isEmpty()) {

            // TODO Find best move.
            return emptyTileList.get(0);
        }
        return null;
    }



}
