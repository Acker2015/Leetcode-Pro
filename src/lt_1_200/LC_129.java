package lt_1_200;

import domain.TreeNode;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/29
 */
public class LC_129 {
    int sum = 0;
    private void dfs(TreeNode root, int ans) {
        if (root == null) return;
        if (root.left==null && root.right == null) {
            sum = sum + ans*10 + root.val;
            return;
        }
        dfs(root.left, ans*10+root.val);
        dfs(root.right, ans*10+root.val);
    }

    /**
     * [129] Sum Root to Leaf Numbers
     * DFS
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
}
