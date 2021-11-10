package com.company.traversal;

import com.company.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal extends Traversal {
    public PreorderTraversal(TreeNode root) {
        super(root);
    }

    @Override
    protected void doTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        doTraversalRecursive(root.left);
        doTraversalRecursive(root.right);
    }

    @Override
    protected void doTraversalIterative(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            if (top != null) {
                res.add(top.val);
                stack.push(top.right);
                stack.push(top.left);
            }
        }
    }

    @Override
    protected void doTraversalMorris(TreeNode root) {
        while (root != null) {
            TreeNode next = root.left;
            if (next == null) {
                res.add(root.val);
                root = root.right;
                continue;
            }
            while (next.right != null && next.right != root) {
                next = next.right;
            }
            if (next.right == root) {
                next.right = null;
                root = root.right;
            } else {
                next.right = root;
                res.add(root.val);
                root = root.left;
            }
        }
    }
}
