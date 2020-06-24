package com.ag.tictactoe.ai;

import android.util.Log;

import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class represents a {@link TreeNode} inside of a GameTree. Each node represents a state of the game.
 */
public class GameTreeNode extends TreeNode {

    /**
     * Tag for logging messages.
     */
    private static final String TAG = GameTreeNode.class.getName();

    /**
     * Undetermined min max value of this node.
     */
    private final int UNDETERMINED = 2;

    /**
     * Min max value of this node for a tie.
     */
    private final int TIE = 0;

    /**
     * Min max value of this node for a win.
     */
    private final int WIN = 1;

    /**
     * Min max value of this node for a lost.
     */
    private final int LOSE = -1;

    /**
     * References a state of the game.
     */
    private GameController gameController;

    /**
     * Indicates the min max value.
     */
    private double minMaxValue;

    /**
     * This is the Tile move used on the parent Node that resulted in this Node.
     */
    private Tile tileMove;

    /**
     * Constructor initializes this node with a state of the GameController.
     *
     * @param gc
     */
    public GameTreeNode(GameController gc) {
        gameController = new GameController(gc);
        minMaxValue = UNDETERMINED;
    }

    /**
     * Sets the new Tile move for the Player for this GameTreeNode.
     *
     * @param p
     * @param t
     */
    public void setTileMove(Player p, Tile t) {
        tileMove = t;
        gameController.makePlayerMove(p, t);
    }

    /**
     * Retrieves the next move for the AI to do.
     *
     * @return
     */
    public GameTreeNode getNextNodeMove() {
        GameTreeNode node = null;
        Collection<TreeNode> path = this.getNodePathFromRoot();
        Iterator<TreeNode> iterator = path.iterator();

        if (iterator.hasNext()) {
            node = (GameTreeNode) iterator.next();
        } else {
            Log.e(TAG, "Unable to determine next move for AI.");
        }
        if (iterator.hasNext()) {
            node = (GameTreeNode) iterator.next();
        } else {
            Log.e(TAG, "Unable to determine next move for AI.");
        }

        return node;
    }

    /**
     * Returns the Tile to make the move on.
     *
     * @param node
     * @return
     */
    public Tile getTileMove(GameTreeNode node) {
        return (gameController.getGameBoardController().getTileFromOtherGameBoardTile(node.tileMove));
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
     * Sets the min max value of this Node.
     *
     * @param value
     */
    public void setNodeMinMaxValue(double value) {
        minMaxValue = value;
    }

    /**
     * Returns the min max value of this Node.
     *
     * @return
     */
    public double getMinMaxValue() {
        return minMaxValue;
    }

    /**
     * Sets the min max value of this node based on if this node is maximizing.
     * Utilize the depth of this node to keep the shortest length path from root to leaf node.
     *
     * @param isMaximizer
     */
    public void setNodeMinMaxValue(boolean isMaximizer) {
        if (isMaximizer) {
            minMaxValue = WIN + (1.0 / this.getNodePathFromRoot().size());
        } else {
            minMaxValue = LOSE - (1 - 1.0 / this.getNodePathFromRoot().size());
        }
    }

    /**
     * Sets this node resulted in a tie.
     */
    public void setTieNode() {
        minMaxValue = TIE;
    }

    /**
     * Find's the quicker path to getting to a win if it exists.
     *
     * @deprecated Use {@link GameTree#getBestTileMove()}.
     *
     * @return
     */
    public GameTreeNode findQuickestWinNode() {

        // Only want to compute this if a win was even possible.
        if (minMaxValue == WIN) {

            GameTreeNode root = (GameTreeNode) this.getRootNode();
            Queue<GameTreeNode> remainingNodes = new LinkedList<GameTreeNode>();
            remainingNodes.add(root);

            // Keep looking while children nodes remain.
            while (!remainingNodes.isEmpty()) {

                GameTreeNode currentNode = remainingNodes.remove();
                Collection<TreeNode> children = currentNode.children;

                // Return this node if it is a win and there are no children. This node
                // is the fastest to a win.
                if (children.isEmpty() && currentNode.minMaxValue == WIN) {
                    return currentNode;
                } else {
                    for (TreeNode child : children) {
                        GameTreeNode gameTreeNodeChild = (GameTreeNode) child;
                        if (gameTreeNodeChild.minMaxValue == WIN) {
                            remainingNodes.add(gameTreeNodeChild);
                        }
                    }
                }
            }
        }

        return null;
    }

}
