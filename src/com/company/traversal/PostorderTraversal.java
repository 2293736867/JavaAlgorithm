package com.company.traversal;

import com.company.node.TreeNode;

import java.util.Collections;
import java.util.LinkedList;

public class PostorderTraversal extends Traversal {
    public PostorderTraversal(TreeNode root) {
        super(root);
    }

    @Override
    protected void doTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        doTraversalRecursive(root.left);
        doTraversalRecursive(root.right);
        res.add(root.val);
    }

    @Override
    protected void doTraversalIterative(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            if (top != null) {
                res.add(top.val);
                stack.push(top.left);
                stack.push(top.right);
            }
        }
        Collections.reverse(res);
    }

    @Override
    protected void doTraversalMorris(TreeNode root) {
        TreeNode rootBak = root;
        while (root != null) {
            TreeNode next = root.left;
            if (next == null) {
                root = root.right;
                continue;
            }
            while (next.right != null && next.right != root) {
                next = next.right;
            }
            if (next.right == root) {
                next.right = null;
                print(root.left);
                root = root.right;
            } else {
                next.right = root;
                root = root.left;
            }
        }
        print(rootBak);
    }

    private void print(TreeNode root) {
        TreeNode list = reverse(root);
        while (list != null) {
            res.add(list.val);
            list = list.right;
        }
        reverse(root);
    }

    private TreeNode reverse(TreeNode root) {
        TreeNode dummy = new TreeNode();
        while (root != null) {
            TreeNode next = root.right;
            root.right = dummy.right;
            dummy.right = root;
            root = next;
        }
        return dummy.right;
    }
}
