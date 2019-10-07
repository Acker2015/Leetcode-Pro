package lt_200_299;

import domain.TreeNode;
import javafx.util.Pair;

import java.util.*;

/**
 * [236] Lowest Common Ancestor of a Binary Tree
 *
 * solution1 和 solution2 比较通用
 */
public class LC_236 {
    static class Solution1 {
        /**
         * solution 1
         * DFS深搜，搜索左右子树是否存在p，q
         * 1. 左右子树都存在，那么返回root就是LCA
         * 2. 只存在左子树或者右子树，那么将找到的节点继续返回
         *      2.1 p或者q的一条子树上，那么最终会返回结果
         *      2.2 找到了p或者q继续搜索找另一个节点，此类型属于1
         *
         * 注意两个情况
         *  1. p和q是否出现在树结构中，如果只出现一个，题目有可能会返回p or q
         *      这个问题有两种解决办法 1.1 提前判断树中会包含p和q
         *                          1.2 返回结果中带标志位，表示是否找到祖先节点
         *  2. p和q是否是同一个节点
         *
         * O(N)  beats 100 %
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            if (root == p || root == q) {
                return root;
            }
            TreeNode leftFoundNode = lowestCommonAncestor1(root.left, p, q);
            TreeNode rightFoundNode = lowestCommonAncestor1(root.right, p, q);
            if (leftFoundNode!=null && rightFoundNode!=null) {
                return root;
            }
            return leftFoundNode==null ? rightFoundNode:leftFoundNode;
        }
    }

    static class Solution2 {
        /**
         * solution 2
         * 使用recursiveTree协助搜索
         * 每一轮递归都表示在该节点root是否找到p, q
         * 需要考虑的情况就是左子树、右子树、根节点，如果在其中两个找到了p或者q，那么此时的root就是LCA，思路intuition
         *
         * O(N)
         */
        private TreeNode LCA;
        private boolean recursiveTree(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return false;
            int mid = (root == p || root == q) ? 1 : 0;
            int left = recursiveTree(root.left, p, q) ? 1 : 0;
            int right = recursiveTree(root.right, p, q) ? 1 : 0;
            if (mid+left+right >= 2) {
                LCA = root;
            }
            return mid+left+right > 0;
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            recursiveTree(root, p, q);
            return LCA;
        }
    }


    static class Solution3 {
        /**
         * solution 3
         *
         * 这种思路就是想像一下倒着遍历
         * LCA是p和q的lowest common ancestor, 那么从p和q向根节点方向走，肯定会遇到公共节点，第一个公共节点就是LCA
         * 所以可以通过map在搜索过程中记录child-parent的映射关系
         *
         * 最后在从p、q网上搜索找到第一个公共节点即可
         *
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            Map<TreeNode, TreeNode> map = new HashMap<>();
            map.put(root, null);
            boolean pVisited = false, qVisited = false;
            while (queue.size() > 0 && (!pVisited || !qVisited)) {
                TreeNode ansNode = queue.poll();
                if (ansNode == p) pVisited = true;
                if (ansNode == q) qVisited = true;
                if (ansNode.left != null) {
                    queue.offer(ansNode.left);
                    map.put(ansNode.left, ansNode);
                }
                if (ansNode.right != null) {
                    queue.offer(ansNode.right);
                    map.put(ansNode.right, ansNode);
                }
            }
            Set<TreeNode> set = new HashSet<>();
            while (p != null) {
                set.add(p);
                p = map.get(p);
            }
            while (q != null && !set.contains(q)) {
                q = map.get(q);
            }
            return q;
        }
    }


    static class Solution4 {
        /**
         * solution4
         * parentState
         *      0 代表左右孩子均没有访问过
         *      1 代表左孩子已经访问过
         *      2 代表左右孩子均已经访问过
         * 通过stack和节点孩子的访问状态记录，来模拟访问整棵树，并跟踪功能头结点
         * 如果发现第一个节点，设置一个指针指向栈顶节点的值，帮助节点回溯，记录LCA
         *
         * 比如
         *          1
         *       2     3
         *     4   5 6   7
         *
         * 在查找4，5的相同LCA时，可以通过模拟DFS
         *
         * https://leetcode.com/articles/lowest-common-ancestor-of-a-binary-tree/
         * @param root
         * @param p
         * @param q
         * @return
         */
        public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            // state, 0 means it can visit left node, 1 mean it can visit right, 2 means it should be polled.
            Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
            stack.push(new Pair<>(root, 0));
            boolean oneVisited = false;
            TreeNode recordNode = null;
            while (stack.size() > 0) {
                Pair<TreeNode, Integer> pair = stack.peek();
                TreeNode parentNode = pair.getKey();
                Integer parentState = pair.getValue();

                TreeNode node;
                if (parentState < 2) {
                    // 表示左右都可以，这时候选择左边先走
                    if (pair.getValue() == 0) {
                        if (parentNode == p || parentNode == q) {
                            if (oneVisited) {
                                return recordNode;
                            } else {
                                oneVisited = true;
                                recordNode = parentNode;
                            }
                        }
                        node = parentNode.left;

                    } else {
                        // 左边已经访问过，可以访问右子树
                        node = pair.getKey().right;
                    }
                    stack.pop();
                    stack.push(new Pair<>(parentNode, parentState+1));
                    if (node != null) {
                        stack.push(new Pair<>(node, 0));
                    }
                } else {
                    // 出栈 选择性替换标记节点
                    if (stack.pop().getKey() == recordNode && oneVisited) {
                        recordNode = stack.peek().getKey();
                    }
                }
            }
            return null;
        }
    }
}
