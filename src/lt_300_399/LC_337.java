package lt_300_399;


import domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * [337] House Robber III
 *
 * DFS
 */
public class LC_337 {
    static class Solution1 {
        public int rob(TreeNode root) {
            if (root == null) return 0;
            Map<TreeNode, int[]> map = new HashMap<>();
            Map<TreeNode, Integer> thiefMap = new HashMap<>();
            Map<TreeNode, Integer> noThiefMap = new HashMap<>();
            return rob(root, 1, thiefMap, noThiefMap);
        }

        /**
         *
         * @param root
         * @param thief         1表示可以偷本节点，0表示本节点不可偷
         * @param thiefMap      memory
         * @param noThiefMap
         * @return
         */
        private int rob(TreeNode root, int thief, Map<TreeNode, Integer> thiefMap, Map<TreeNode, Integer> noThiefMap) {
            if (root == null) {
                return 0;
            }
            if (thief > 0 && thiefMap.get(root)!=null) {
                return thiefMap.get(root);
            }
            if (thief == 0 && noThiefMap.get(root)!=null) {
                return noThiefMap.get(root);
            }
            int ans1 = 0, ans2 = 0;
            if (thief > 0) {
                ans1 = root.val + rob(root.left, 0, thiefMap, noThiefMap) + rob(root.right, 0, thiefMap, noThiefMap);
            }
            ans2 = rob(root.left, 1, thiefMap, noThiefMap) + rob(root.right, 1, thiefMap, noThiefMap);
            int ans = Math.max(ans1, ans2);
            if (thief > 0) {
                thiefMap.put(root, ans);
            } else {
                noThiefMap.put(root, ans);
            }
            return ans;
        }
    }

    /**
     *
     * Solution2
     * 最优结果
     *
     * 如何避免使用map来记录中间结果呢？
     * 每个节点遍历一次，同时记录下来 (visit 和 no-visit)的信息
     */
    static class Solution2 {
        public int rob(TreeNode root) {
            int[] ret = robSub(root);
            return Math.max(ret[0], ret[1]);
        }
        //

        /**
         * 返回结果为node不访问和访问的最大金钱数
         * 第一个元素表示不访问节点的最大金钱 (index=0)
         * 第二个元素表示访问节点的最大金钱 (index=1)
         * @param node
         * @return
         */
        private int[] robSub(TreeNode node) {
            if (node == null) {
                return new int[]{0, 0};
            }
            int[] ret = new int[2];
            int[] leftRet = robSub(node.left);
            int[] rightRet = robSub(node.right);
            ret[0] = Math.max(leftRet[0], leftRet[1]) + Math.max(rightRet[0], rightRet[1]);
            ret[1] = node.val + leftRet[0] + rightRet[0];
            return ret;
        }
    }
}
