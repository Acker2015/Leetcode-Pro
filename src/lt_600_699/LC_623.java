package lt_600_699;

import domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [623] Add One Row to Tree
 *
 *Given the root of a binary tree, then value v and depth d,
 * you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

 The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1,
 create two tree nodes with value v as N's left subtree root and right subtree root.
 And N's original left subtree should be the left subtree of the new left subtree root,
 its original right subtree should be the right subtree of the new right subtree root.
 If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree,
 and the original tree is the new root's left subtree.

 BFS 解决
 */
public class LC_623 {
    private void operateNode(TreeNode node, int v) {
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = new TreeNode(v);
        node.left.left = left;
        node.right = new TreeNode(v);
        node.right.right = right;
    }
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return new TreeNode(v);
        }
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode p = queue.poll();
                if (depth == d-1) {
                    operateNode(p, v);
                } else {
                    if (p.left != null) {
                        queue.offer(p.left);
                    }
                    if (p.right != null) {
                        queue.offer(p.right);
                    }
                }
            }
            if (depth == d-1) {
                break;
            }
            depth++;
        }
        return root;
    }
}
