package com.ag.tictactoe.ai;

import com.ag.tictactoe.controller.GameBoardController;
import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.controller.PlayerController;
import com.ag.tictactoe.controller.TurnController;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

import java.util.List;

/**
 * Class will use depth first search and determine all possible outcomes for the game. This new
 * will then be used to determine the best move the AI needs to take to try and win the game.
 */
public class GameTree {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = GameTree.class.getName();

    /**
     * The initial state of the game when the AI is prompted to make a move.
     * This is the root of the GameTree.
     */
    private GameTreeNode initialGameTreeNode;

    /**
     * Constructor sets the initial GameController.
     *
     * @param gc
     */
    public GameTree(GameController gc) {
        initialGameTreeNode = new GameTreeNode(gc);
    }

    /**
     * Uses depth first search to populate the GameTree.
     * Utilizes minimax algorithm to return the best GameTreeNode.
     * This GameTreeNode stores the best possible move to make.
     *
     * @param node
     * @param isMaximizer
     * @return
     */
    private GameTreeNode buildGameTree(GameTreeNode node, boolean isMaximizer, double alpha, double beta) {

        GameController node_gc = node.getGameController();
        GameBoardController node_gbc = node_gc.getGameBoardController();
        PlayerController node_pc = node_gc.getPlayerController();
        TurnController node_tc = node_gc.getTurnController();
        Player player = node_tc.getPlayerTurn(node_pc.getPlayers());
        List<Tile> emptyTileList = node_gbc.getEmptyTileList();

        // This will be the best GameTreeNode depending on if this is the
        // maximizer or minimizer.
        GameTreeNode bestGameTreeNode = null;

        // Iterate through the possible moves.
        for (Tile tile : emptyTileList) {

            // Create a new child node.
            GameTreeNode newNode = new GameTreeNode(node_gc);
            node.addChildTreeNode(newNode);

            // Get the GameBoardController for this child.
            GameBoardController newNode_gbc = newNode.getGameController().getGameBoardController();
            Tile newTile = newNode_gbc.getTileFromOtherGameBoardTile(tile);

            // Set the new Tile on the child's GameBoardController.
            newNode.setTileMove(player, newTile);

            // Check if this was a winning move.
            if (newNode_gbc.getWinConditionForPlayer(player)) {
                newNode.setNodeMinMaxValue(isMaximizer);

                if (bestGameTreeNode == null) {
                    bestGameTreeNode = newNode;
                } else if (isMaximizer) {
                    if (bestGameTreeNode.getMinMaxValue() < newNode.getMinMaxValue()) {
                        bestGameTreeNode = newNode;
                    }
                    if (bestGameTreeNode.getMinMaxValue() > alpha) {
                        alpha = bestGameTreeNode.getMinMaxValue();
                    }
                } else {
                    if (bestGameTreeNode.getMinMaxValue() > newNode.getMinMaxValue()) {
                        bestGameTreeNode = newNode;
                    }
                    if (bestGameTreeNode.getMinMaxValue() < beta) {
                        beta = bestGameTreeNode.getMinMaxValue();
                    }
                }
            }
            // Check if this move was a tie.
            else if (newNode_gbc.getStalemateCondition()) {
                newNode.setTieNode();

                if (bestGameTreeNode == null) {
                    bestGameTreeNode = newNode;
                } else if (isMaximizer) {
                    if (bestGameTreeNode.getMinMaxValue() < newNode.getMinMaxValue()) {
                        bestGameTreeNode = newNode;
                    }
                    if (bestGameTreeNode.getMinMaxValue() > alpha) {
                        alpha = bestGameTreeNode.getMinMaxValue();
                    }
                } else {
                    if (bestGameTreeNode.getMinMaxValue() > newNode.getMinMaxValue()) {
                        bestGameTreeNode = newNode;
                    }
                    if (bestGameTreeNode.getMinMaxValue() < beta) {
                        beta = bestGameTreeNode.getMinMaxValue();
                    }
                }

            }
            // Continue to play the game and populate the GameTree.
            else {
                // Change the player's turn before creating a new Tree.
                newNode.getGameController().getTurnController().changeTurn(node_pc.getPlayers());

                // Continue building the GameTree to determine the best minimax value.
                // The maximizer becomes the minimizer and vice versa with every turn.
                buildGameTree(newNode, !isMaximizer, alpha, beta);

                if (bestGameTreeNode == null) {
                    bestGameTreeNode = newNode;
                } else if (isMaximizer) {
                    if (bestGameTreeNode.getMinMaxValue() < newNode.getMinMaxValue()) {
                        bestGameTreeNode = newNode;
                    }
                    if (bestGameTreeNode.getMinMaxValue() > alpha) {
                        alpha = bestGameTreeNode.getMinMaxValue();
                    }
                } else {
                    if (bestGameTreeNode.getMinMaxValue() > newNode.getMinMaxValue()) {
                        bestGameTreeNode = newNode;
                    }
                    if (bestGameTreeNode.getMinMaxValue() < beta) {
                        beta = bestGameTreeNode.getMinMaxValue();
                    }
                }
            }

            // Don't continue looking at the rest of the possible moves.
            if (beta <= alpha) {
                break;
            }
        }

        node.setNodeMinMaxValue(bestGameTreeNode.getMinMaxValue());

        return bestGameTreeNode;
    }

    /**
     * Returns the best possible move to make.
     *
     * @return
     */
    public Tile getBestTileMove() {
        // Populates a GameTree that looks at all possible outcomes.
        GameTreeNode best = buildGameTree(initialGameTreeNode, true, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        return initialGameTreeNode.getTileMove((best.getNextNodeMove()));
    }

}
