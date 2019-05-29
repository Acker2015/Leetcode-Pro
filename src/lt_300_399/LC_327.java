package lt_300_399;

/**
 * [327] Count of Range Sum
 * hard
 * 利用BST进行范围查找，缩短查找时间
 * 通过前缀和，从后往前将加入BST，查找preSum[i]-preSum[j]在[lower, upper]范围的节点个数
 * 遍历节点为j，相当于在BST中查找范围在[lower+preSum[j], upper+preSum[j]]之间的节点
 */
public class LC_327 {
    private class Node {
        Node left, right;
        long val;
        int repeat; // 节点值重复次数
        public Node(long val) {this.val=val; this.repeat = 1;}
    }
    private void insert(Node parent, Node root, long val) {
        if (root == null) {
            Node node = new Node(val);
            if (val >= parent.val) {
                parent.right = node;
            } else {
                parent.left = node;
            }
            return;
        }
        if (root.val == val) {
            root.repeat++;
        } else if (root.val < val) {
            insert(root, root.right, val);
        } else {
            insert(root, root.left, val);
        }
    }
    private int query(Node node, long lower, long upper) {
        if (node == null || lower > upper) return 0;
        if (node.val < lower) {
            return query(node.right, lower, upper);
        } else if (node.val > upper) {
            return query(node.left, lower, upper);
        } else {
            return query(node.left, lower, node.val-1) + query(node.right, node.val+1, upper) + node.repeat;
        }
    }
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums.length <= 0) return 0;
        int len = nums.length;
        long[] preSum = new long[len];
        for (int i = 0; i < len; ++i) {
            preSum[i] = i > 0 ? preSum[i-1]+nums[i]:nums[i];
        }
        int ans = 0;
        Node root = new Node(Long.MIN_VALUE);
        insert(root, root.right, preSum[len-1]);
        for (int i = len-2; i >= -1; --i) {
            long n_low = i > -1 ? lower+preSum[i]:lower;
            long n_upper = i > -1 ? upper+preSum[i]:upper;
            ans += query(root.right, n_low, n_upper);
            if (i >= 0) {
                insert(root, root.right, preSum[i]);
            }
        }
        return ans;
    }

    /**
     *
     * @param args
     */
    public static void main(String ...args) {
        int[] nums = {-2147483647,0,-2147483647,2147483647};
        System.out.println(new LC_327().countRangeSum(nums, -564, 3864));
    }
}
