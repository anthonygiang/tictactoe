package com.ag.tictactoe.ai;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic class to represent a tree node in a Tree data structure.
 */
public abstract class TreeNode {

    /**
     * Reference to the parent TreeNode.
     */
    protected TreeNode parent;

    /**
     * List of all the children TreeNodes.
     */
    protected List<TreeNode> children;

    /**
     * Initialize values.
     */
    protected TreeNode() {
        parent = null;
        children = new ArrayList<TreeNode>();
    }

}
