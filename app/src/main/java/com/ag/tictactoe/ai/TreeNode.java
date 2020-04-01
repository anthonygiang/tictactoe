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

    /**
     * Adds a child TreeNode to this current TreeNode.
     * Sets the parent node of the child to this current TreeNode.
     *
     * @param n
     */
    public void addChildTreeNode(TreeNode n) {
        n.parent = this;
        children.add(n);
    }

    /**
     * Returns the root TreeNode from this node.
     *
     * @return
     */
    public TreeNode getRootNode() {
        TreeNode iterator = this;
        while (iterator.parent != null) {
            iterator = iterator.parent;
        }
        return iterator;
    }

    /**
     * Returns a List of TreeNodes from the root to the designated TreeNode.
     *
     * @return
     */
    public List<TreeNode> getNodePathFromRoot() {
        TreeNode iterator = this;
        List<TreeNode> path = new ArrayList<TreeNode>();

        while (iterator != null) {
            path.add(0, iterator);
            iterator = iterator.parent;
        }

        return path;
    }

}
