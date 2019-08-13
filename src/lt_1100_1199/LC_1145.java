package lt_1100_1199;

import domain.TreeNode;

/**
 * [1145] Binary Tree Coloring Game
 *
 * second选手能够获胜只需要看 第一个选手选择位置的左子树，右子树，与父节点连接其他树
 * 这三棵树中的最大节点个数的那个数就是second选手选择的节点，目的就是切断first-player后续的选择
 * 这样选手二就能是最右选择
 *
 * 比如二叉树
 * 1 2 3 4 5 6 7 8 9 10 11
 * player-one 选择3
 *
 * 那么3的左子树有只有6一个节点，右子树只有7一个节点，而父节点1连接方向有1，2，4，5，8，9，10，11这八个节点
 * 所以player-two只需要选择节点1，即可成功获胜
 *
 * follow-up 如果play-one一开始就随便选择，那么能否获胜？答案是一定能够获胜
 * A选择一个节点，如果B能够通过选择其左子树/右子树/父节点来战胜A，那么就把B选择的节点给占据就可以
 *
 * 比如3个节点 1-2-3
 * 1. A一开始选择2，B只需选择1就可以战胜A
 * 2. 那么A在一开始就选择1，此时就能一步步往获胜的方向走
 *
 */
public class LC_1145 {
    private int dfs(TreeNode node, int x, int[] record) {
        if (node == null) return 0;
        int left = dfs(node.left, x, record);
        int right = dfs(node.right, x, record);
        if (node.val == x) {
            record[0] = left;
            record[1] = right;
        }
        return left + right + 1;
    }
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root == null) return false;
        int[] record = new int[2];
        int cnt = dfs(root, x, record);
        int parent = cnt - record[0] - record[1] - 1;
        int left = record[0];
        int right = record[1];
        return Math.max(parent, Math.max(left, right)) > cnt/2;
    }
}
