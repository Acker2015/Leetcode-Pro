package lt_1_200;

import domain.TreeNode;

public class LC_100 {
	/**
	 * same tree
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val==q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
