package lt_200_299;


import domain.TreeNode;

public class LC_235 {
    /**
     * [235] Lowest Common Ancestor of a Binary Search Tree
     *
     * LCA of BST
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val > q.val) {
            return lowestCommonAncestor(root, q, p);
        }
        if (root.val == p.val || root.val == q.val) return root;
        if (root.val > p.val && root.val < q.val) return root;
        if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }
}
