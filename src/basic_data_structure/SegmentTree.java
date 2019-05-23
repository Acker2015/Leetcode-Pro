package basic_data_structure;

/**
 * 线段树
 * https://www.cnblogs.com/TheRoadToTheGold/p/6254255.html
 *
 * 空间优化：http://www.cppblog.com/MatoNo1/archive/2015/05/05/195857.html
 * 数组表示的空间分析： https://blog.csdn.net/WhereIsHeroFrom/article/details/78969718
 */
public class SegmentTree {
    private class Node {
        int l, r;
        int lazy;   // 节点懒更新数值
        int sum; // segment sum
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
            this.lazy = 0;
        }
    }
    private class SumRecord {
        int ans;
        SumRecord() {
            ans = 0;
        }
    }
    private Node[] tree;
    private int[] nums;

    public void build(int l, int r, int idx) {
        tree[idx] = new Node(l, r);
        if (l == r) {
            tree[idx].sum = nums[l];
            return;
        }
        int m = l + (r-l)/2;
        build(l, m, idx<<1);
        build(m+1, r, (idx<<1)+1);
        tree[idx].sum = tree[idx<<1].sum + tree[(idx<<1)+1].sum; // sum更新
    }
    // 单点查询
    public int query(int aimIdx, int k) {
        if (tree[k].l == tree[k].r) {
            return tree[k].sum;
        }
        // 下传
        if (tree[k].lazy > 0) {
            down(k);
        }
        int m = tree[k].l + (tree[k].r - tree[k].l)/2;
        if (aimIdx <= m) {
            return query(aimIdx, k<<1);
        } else {
            return query(aimIdx, (k<<1)+1);
        }
    }
    // 区间求和
    public int queryInterval(int x, int y, int k) {
        Node node = tree[k];
        if (x < node.l) {
            x = node.l;
        }
        if (y > node.r) {
            y = node.r;
        }
        if (x == node.l && y == node.r) {
            return node.sum;
        }
        // 下传
        if (node.lazy > 0) {
            down(k);
        }
        // 通过判断mid与[x,y]的关系来计算区间和
        int m = node.l + (node.r - node.l) / 2;
        if (y <= m) {
            return queryInterval(x, y, k << 1);
        } else if (x > m) {
            return queryInterval(x, y, (k << 1) + 1);
        } else {
            return queryInterval(x, m, k<<1) + queryInterval(m+1, y, (k << 1) + 1);
        }
    }
    public void queryInterval(int x, int y, int k, SumRecord record) {
        Node node = tree[k];
        if (node.l <= x && node.r >= y) {
            record.ans += node.sum;
            return;
        }
        // 下传
        if (node.lazy > 0) {
            down(k);
        }
        int m = node.l + (node.r - node.l) / 2;
        if (x <= m) {
            queryInterval(x, y, k<<1, record);
        }
        if (y > m) {
            queryInterval(x, y, (k<<1)+1, record);
        }
    }
    /**
     * 区间修改 - 懒更新
     * [x, y]范围的节点值增加val
     * @param x
     * @param y
     * @param val
     * @param k
     */
    public void add(int x, int y, int val, int k) {
        Node node = tree[k];
        if (node.l >= x && node.r <= y) {
            node.sum += (node.r - node.l + 1) * val;
            node.lazy += val;
            return;
        }
        if (node.lazy > 0) down(k);
        int m = node.l + (node.r - node.l) / 2;
        if (x <= m) {
            add(x, y, val, k << 1);
        }
        if (y > m) {
            add(x, y, val, (k<<1)+1);
        }
        // 更新区间状态
        node.sum = tree[k<<1].sum + tree[(k<<1)+1].sum;
    }

    /**
     * 下传操作 （当前节点如果有懒标记，那么sum值默认已经更新过）
     * 1. 当前节点的懒标记累计到子节点的懒标记中
     * 2. 修改自己诶单状态, 就是原状态 + 子节点区间点的个数*父节点传下来的懒标记
     * 3. 父节点的懒标记清零
     * @param k
     */
    private void down(int k) {
        Node leftNode = tree[k<<1], rightNode = tree[(k<<1)+1], node = tree[k];
        leftNode.lazy += node.lazy;
        leftNode.sum += node.lazy * (leftNode.r - leftNode.l + 1);
        rightNode.lazy += node.lazy;
        rightNode.sum += node.lazy * (rightNode.r - rightNode.l + 1);
        node.lazy = 0;
    }
}
