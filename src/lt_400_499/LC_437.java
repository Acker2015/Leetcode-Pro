package lt_400_499;


import domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * easy -> medium
 * 参考two sum的解法，使用hashmap辅助求解
 * [437] Path Sum III
 */
public class LC_437 {
    /**
     * 记录前缀和，通过寻找(preSum-target)来帮助搜索路径和为target的sum
     */
    private int DFS(TreeNode root, int preSum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;
        preSum += root.val;
        int ret = map.getOrDefault(preSum-target, 0);
        map.put(preSum, map.getOrDefault(preSum, 0)+1);
        ret += DFS(root.left, preSum, target, map);
        ret += DFS(root.right, preSum, target, map);
        map.put(preSum, map.get(preSum)-1);
        return ret;
    }
    /**
     * 原理类似two sum的解题思路
     * So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how many ways get to this prefix sum) ,
     * and whenever reach a node, we check if prefix sum
     *  - target exists in hashmap or not, if it does, we added up the ways of prefix sum - target into res.
     */
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return DFS(root, 0, sum, map);
    }


    /**
     * follow-up
     * 如果修改题目要求为找打印出这些path，how to solve this
     *
     * 解法就是记录遍历到此节点的path，所以只需关注，这个几点是否为总和为sum的某条路径的末端。
     *
     * @param node
     * @param sum
     */
    public void findSum(TreeNode node, int sum) {
        int depth = depth(node);
        int[] path = new int[depth];
        findSum(node, sum, path, 0);

    }
    private void findSum(TreeNode node, int sum, int[] path, int level) {
        if (node == null)return;
        // 插入路径
        path[level] = node.val;
        int t = 0;
        // 查找以此节点为终点并且总和为sum的路径
        for (int i = level; i >= 0; --i) {
            t += path[i];
            if (t == sum) {
                print(path, i, level);
            }
        }
        // 查找此节点下的节点
        findSum(node.left, sum, path, level+1);
        findSum(node.right, sum, path, level+1);
        // 此路径中移除当前节点
        path[level] = Integer.MIN_VALUE;
    }

    private int depth(TreeNode node ) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depth(node.left), depth(node.right));
    }
    private static void print(int[] path, int start, int end) {
        for (int i = start; i <= end; ++i) {
            System.out.print(path[i]+" ");
        }
        System.out.println();
    }
}
