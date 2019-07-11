package lt_500_599;

import domain.TreeNode;

/**
 * [572] Subtree of Another Tree
 * DFS
 *
 * time O(mn)
 */
public class LC_572 {
    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val==t.val && check(s.left, t.left) && check(s.right, t.right);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        if (s == null) return false;
        if (check(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
}
