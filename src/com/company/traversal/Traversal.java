package com.company.traversal;

import com.company.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

public abstract class Traversal {
    private TreeNode root = null;
    protected List<Integer> res;

    public Traversal(TreeNode root) {
        setRoot(root);
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    private void reset() {
        res = new ArrayList<>();
    }

    public List<Integer> traversalRecursive() {
        reset();
        doTraversalRecursive(root);
        return res;
    }

    protected abstract void doTraversalRecursive(TreeNode root);

    public List<Integer> traversalIterative() {
        reset();
        doTraversalIterative(root);
        return res;
    }

    protected abstract void doTraversalIterative(TreeNode root);

    public List<Integer> traversalMorris() {
        reset();
        doTraversalMorris(root);
        return res;
    }

    protected abstract void doTraversalMorris(TreeNode root);
}
