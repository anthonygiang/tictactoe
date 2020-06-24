package com.ag.tictactoe.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Generic class to represent a tree node in a Tree data structure.
 */
public abstract class TreeNode {

    /**
     * Reference to the parent {@link TreeNode}.
     */
    protected TreeNode parent;

    /**
     * Collection of all the children {@link TreeNode}s.
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
     * @param childNode - Child node being added to Tree.
     */
    public void addChildTreeNode(TreeNode childNode) {
        childNode.parent = this;
        children.add(childNode);
    }

    /**
     * Returns the root {@link TreeNode} from this node.
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
     * Returns a List of {@link TreeNode}s from the root to this node.
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
