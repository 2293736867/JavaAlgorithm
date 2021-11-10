package com.company.traversal;

import com.company.node.TreeNode;

import java.util.LinkedList;

public class InorderTraversal extends Traversal {
    public InorderTraversal(TreeNode root) {
        super(root);
    }

    @Override
    protected void doTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        doTraversalRecursive(root.left);
        res.add(root.val);
        doTraversalRecursive(root.right);
    }

    @Override
    protected void doTraversalIterative(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode top = root;
        while (!stack.isEmpty() || top != null) {
            while (top != null) {
                stack.push(top);
                top = top.left;
            }
            top = stack.pop();
            res.add(top.val);
            top = top.right;
        }
    }

    @Override
    protected void doTraversalMorris(TreeNode root) {
        while (root != null) {
            TreeNode next = root.left;
            if(next == null){
                res.add(root.val);
                root = root.right;
                continue;
            }
            while (next.right != null && next.right != root) {
                next = next.right;
            }
            if (next.right == root) {
                next.right = null;
                res.add(root.val);
                root = root.right;
            } else {
                next.right = root;
                root = root.left;
            }
        }
    }
}
