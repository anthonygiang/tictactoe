package com.ag.tictactoe.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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
    protected Collection<TreeNode> children;

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
    public Collection<TreeNode> getNodePathFromRoot() {
        TreeNode iterator = this;
        LinkedList<TreeNode> path = new LinkedList<TreeNode>();

        while (iterator != null) {
            path.addFirst(iterator);
            iterator = iterator.parent;
        }

        return path;
    }

}
