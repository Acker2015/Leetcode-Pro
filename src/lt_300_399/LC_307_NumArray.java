package lt_300_399;

/**
 * [307] Range Sum Query - Mutable
 *
 * Segment tree（线段树）
 */
public class LC_307_NumArray {
    static class Node {
        int l, r;
        int sum;
        Node left, right;
        Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }


    /**
     * 解法一
     *
     * 正常左右指针线段树
     */
    static class Solution1 {
        private Node head;
        // 建树
        private Node build(int[] nums, int l, int r) {
            Node node = new Node(l, r);
            if (l == r) {
                node.sum = nums[l];
                return node;
            }
            int mid = l + (r-l)/2;
            node.left = build(nums, l, mid);
            node.right = build(nums, mid+1, r);
            node.sum = node.left.sum + node.right.sum;
            return node;
        }
        // 单点更新
        private void update(Node node, int i, int val) {
            if (node.l == node.r && node.l == i) {
                node.sum = val;
                return;
            }
            int mid = node.l + (node.r - node.l)/2;
            if (i <= mid) {
                update(node.left, i, val);
            } else {
                update(node.right, i, val);
            }
            node.sum = node.left.sum + node.right.sum;
        }
        // 求和
        private int getSum(Node node, int i, int j) {
            if (i < node.l) i = node.l;
            if (j > node.r) j = node.r;
            if (i == node.l && j == node.r) {
                return node.sum;
            }
            int mid = node.l + (node.r-node.l)/2;
            if (j <= mid) {
                return getSum(node.left, i, j);
            } else if (i > mid) {
                return getSum(node.right, i, j);
            } else {
                return getSum(node.left, i, mid) + getSum(node.right, mid+1, j);
            }
        }
        public Solution1(int[] nums) {
            int len = nums.length;
            if (len <= 0) return;
            this.head = this.build(nums, 0, len-1);
        }

        public void update(int i, int val) {
            if (head == null) return;
            update(head, i, val);
        }
        public int sumRange(int i, int j) {
            return getSum(head, i, j);
        }
    }



    /**
     * 解法二
     *
     * 数组实现线段树
     */
    static class Solution2 {
        Node[] tree;
        int[] nums;
        public Solution2(int[] nums) {
            int len = nums.length;
            this.tree = new Node[4*len];
            this.nums = nums;
            if (len <= 0) return;
            this.build(0, len-1, 1);
        }
        /**
         * 线段树构建
         * build segment tree
         */
        private void build(int l, int r, int k) {
            tree[k] = new Node(l, r);
            if (l == r) {
                tree[k].sum = nums[l];
                return;
            }
            int m = l + (r - l) / 2;
            build(l, m, k<<1);
            build(m+1, r, (k<<1)+1);
            tree[k].sum = tree[k<<1].sum + tree[(k<<1)+1].sum;
        }

        // 单点更新
        private void updateSingle(int k, int i, int val) {
            Node node = tree[k];
            if (node.l == node.r) {
                node.sum = val;
                return;
            }
            int m = node.l + (node.r - node.l) / 2;
            if (i <= m) {
                updateSingle(k<<1, i, val);
            } else {
                updateSingle((k<<1)+1, i, val);
            }
            node.sum = tree[k<<1].sum + tree[(k<<1)+1].sum;
        }
        // 区间求和
        private int getSum(int i, int j, int k) {
            Node node = tree[k];
            if (i < node.l) i = node.l;
            if (j > node.r) j = node.r;
            if (i == node.l && j == node.r) {
                return node.sum;
            }
            int m = node.l + (node.r - node.l) / 2;
            if (m < i) {
                return getSum(i, j, (k<<1)+1);
            } else if (j <= m) {
                return getSum(i, j, k<<1);
            } else {
                return getSum(i, m, k<<1) + getSum(m+1, j, (k<<1)+1);
            }
        }

        public void update(int i, int val) {
            updateSingle(1, i, val);
        }

        public int sumRange(int i, int j) {
            return getSum(i, j, 1);
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        LC_307_NumArray.Solution1 numArray = new LC_307_NumArray.Solution1(nums);
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }
}
