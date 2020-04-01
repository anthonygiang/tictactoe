package com.ag.tictactoe.ai;

import android.util.Log;

import com.ag.tictactoe.controller.GameBoardController;
import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.controller.PlayerController;
import com.ag.tictactoe.controller.TurnController;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

import java.util.ArrayList;
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
     * Name of the alpha player. This will be used to identify this player
     * during alpha beta pruning.
     */
    private String alphaName;

    /**
     * Name of the beta player. This will be used to identify this player
     * during alpha beta pruning.
     */
    private String betaName;

    /**
     * The name of the maximizer for this game.
     */
    private String maximizer;

    /**
     * List of all the winning nodes for the maximizer.
     */
    private List<GameTreeNode> maximizerWinningNodes;

    /**
     * List of all the winning nodes for the minimizer.
     */
    private List<GameTreeNode> minimizerWinningNodes;

    /**
     * Constructor sets the initial GameController.
     *
     * @param gc
     */
    public GameTree(GameController gc) {
        initialGameTreeNode = new GameTreeNode(gc);
        PlayerController pc = gc.getPlayerController();
        List<Player> players = pc.getPlayers();
        maximizer = gc.AI_NAME;
        alphaName = players.get(0).getName();
        betaName = players.get(1).getName();

        maximizerWinningNodes = new ArrayList<GameTreeNode>();
        minimizerWinningNodes = new ArrayList<GameTreeNode>();
    }

    /**
     * Uses depth first search to populate the GameTree.
     *
     * @param node
     */
    private void buildGameTree(GameTreeNode node) {

        GameController node_gc = node.getGameController();
        GameBoardController node_gbc = node_gc.getGameBoardController();
        PlayerController node_pc = node_gc.getPlayerController();
        TurnController node_tc = node_gc.getTurnController();
        Player player = node_tc.getPlayerTurn(node_pc.getPlayers());

        List<Tile> emptyTileList = node_gbc.getEmptyTileList();

        // TODO Implement min max algorithm.
        int value = 2;

        // Iterate through the possible moves.
        for (Tile tile : emptyTileList) {

            // Create a new child node.
            GameTreeNode newNode = new GameTreeNode(node_gc);
            node.addChildTreeNode(newNode);

            GameBoardController newNode_gbc = newNode.getGameController().getGameBoardController();
            Tile newTile = newNode_gbc.getTileFromOtherGameBoardTile(tile);

            newNode.setTileMove(player, newTile);

            // Check if this was a winning move.
            if (newNode_gbc.getWinConditionForPlayer(player)) {
                if (player.getName().equals(maximizer)) {
                    newNode.setWinNode();
                    maximizerWinningNodes.add(newNode);
                } else {
                    newNode.setLoseNode();
                    minimizerWinningNodes.add(newNode);
                }
            }
            // Check if this was a tie.
            else if (newNode_gbc.getStalemateCondition()) {
                newNode.setTieNode();
            }
            // Continue to play the game and populate the Tree.
            else {

                // Change the player's turn before creating a new Tree.
                newNode.getGameController().getTurnController().changeTurn(node_pc.getPlayers());
                buildGameTree(newNode);
            }
        }
    }

    /**
     * Returns the best possible move to make.
     *
     * @return
     */
    public Tile getBestTileMove() {

        // Populates a GameTree that looks at all possible outcomes.
        buildGameTree(initialGameTreeNode);

        // Initialize variables.
        GameTreeNode maximizerWinNode = null;
        GameTreeNode minimizerWinNode = null;
        int maxSize = 0;
        int minSize = 0;

        // If the maximizer has any winning solutions, determine the shortest path to that
        // winning solution.
        if (maximizerWinningNodes.size() > 0) {
            maximizerWinNode = maximizerWinningNodes.get(0);
            for (GameTreeNode node : maximizerWinningNodes) {
                if (node.getNodePathFromRoot().size() < maximizerWinNode.getNodePathFromRoot().size()) {
                    maximizerWinNode = node;
                }
            }
            maxSize = maximizerWinNode.getNodePathFromRoot().size();
        }
        // If the minimizer has any winning solutions, determine the shortest path to that
        // winning solution.
        if (minimizerWinningNodes.size() > 0) {
            minimizerWinNode = minimizerWinningNodes.get(0);
            for (GameTreeNode node : minimizerWinningNodes) {
                if (node.getNodePathFromRoot().size() < minimizerWinNode.getNodePathFromRoot().size()) {
                    minimizerWinNode = node;
                }
            }
            minSize = minimizerWinNode.getNodePathFromRoot().size();
        }

        // Priority of moves for AI:
        // - Win game if possible in next move.
        // - Prevent user from winning game if they can win in next move.
        // - Make most optimal move for AI to get to a win if it exists.
        // - Prevent most optimal user move if it exists.
        // - Choose a move if it exists.

        // Select the winning move for the AI.
        // TODO This size variable is likely hard coded based on who is moving first.
        if (maximizerWinNode != null && maxSize == 2) {
            GameTreeNode root2 = maximizerWinNode.getNextNodeMove();
            Tile tile = initialGameTreeNode.getTileMove(root2);
            return tile;
        }
        // Block the user from winning on the next move.
        // TODO This size variable is likely hard coded based on who is moving first.
        if (minimizerWinNode != null && minSize == 3) {
            Tile tile = initialGameTreeNode.getTileMove(minimizerWinNode);
            return tile;
        }
        // Make the most optimal move to try and win.
        if (maximizerWinNode != null) {
            GameTreeNode root2 = maximizerWinNode.getNextNodeMove();
            Tile tile = initialGameTreeNode.getTileMove(root2);
            return tile;
        }
        // Make the most optimal move to block the user from winning.
        else if (minimizerWinNode != null) {
            Tile tile = initialGameTreeNode.getTileMove(minimizerWinNode);
            return tile;
        }
        // Make any possible move since optimal moves don't exist.
        else {
            Log.i(TAG, "No optimal winning moves available for AI.");
            List<Tile> emptyTiles = initialGameTreeNode.getGameController().getGameBoardController().getEmptyTileList();
            if (!emptyTiles.isEmpty()) {
                return (emptyTiles.get(0));
            } else {
                Log.e(TAG, "No moves available for AI.");
            }
        }

        return null;
    }

}
