package test.traversal;

import com.company.node.TreeNode;
import com.company.traversal.InorderTraversal;
import com.company.traversal.PostorderTraversal;
import com.company.traversal.PreorderTraversal;
import com.company.traversal.Traversal;

public class TraversalTest {
    private static final TreeNode root;

    static {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
    }

    public static void main(String[] args) {
        preorderTest();
        inorderTest();
        postorderTest();
    }

    private static void preorderTest() {
        testTraversal(new PreorderTraversal(root));
    }

    private static void inorderTest() {
        testTraversal(new InorderTraversal(root));
    }

    private static void postorderTest() {
        testTraversal(new PostorderTraversal(root));
    }

    private static void testTraversal(Traversal traversal) {
        System.out.println(traversal.traversalRecursive());
        System.out.println(traversal.traversalIterative());
        System.out.println(traversal.traversalMorris());
    }
}
