package lt_300_399;

/**
 * [307] Range Sum Query - Mutable
 *
 * Segment tree（线段树）
 */
public class LC_307_NumArray {
    private class Node {
        int l, r;
        int sum;
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
            this.sum = 0;
        }
    }
    Node[] tree;
    int[] nums;
    public LC_307_NumArray(int[] nums) {
        int len = nums.length;
        this.tree = new Node[4*len];
        this.nums = nums;
        if (len <= 0) return;
        this.build(0, len-1, 1);
    }
    /**
     * 线段树构建
     * build segment tree
     *
     * k为数组中存放下标
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
    // 单点更新 - 更新nums[i]所在的path上节点的sum
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
        // 求和左右区间坐标修正
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
