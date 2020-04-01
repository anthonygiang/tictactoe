package com.ag.tictactoe.ai;

import com.ag.tictactoe.controller.GameController;
import com.ag.tictactoe.model.Player;
import com.ag.tictactoe.model.Tile;

import java.util.List;

/**
 * Class represents a TreeNode inside of a GameTree. Each node represents a state of the game.
 */
public class GameTreeNode extends TreeNode {

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
     * Indicates this node was visited by a search algorithm.
     */
    private boolean visited;

    /**
     * Indicates the min max value.
     */
    private int minMaxValue;

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
        visited = false;
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
        List<TreeNode> path = this.getNodePathFromRoot();
        return (GameTreeNode) path.get(1);
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
     * Sets whether this node resulted in a win.
     */
    public void setWinNode() {
        minMaxValue = WIN;
    }

    /**
     * Sets whether this node resulted in a tie.
     */
    public void setTieNode() {
        minMaxValue = TIE;
    }

    /**
     * Sets whether this node resulted in a lost.
     */
    public void setLoseNode() {
        minMaxValue = LOSE;
    }

    /**
     * Returns if this node resulted in a win.
     *
     * @return
     */
    public boolean getWinNode() {
        if (minMaxValue == WIN) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if this node resulted in a tie.
     *
     * @return
     */
    public boolean getTieNode() {
        if (minMaxValue == TIE) {
            return true;
        }
        return false;
    }

}
