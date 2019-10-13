package lt_300_399;


public class LC_331 {
    /**
     * solution1
     * 使用树的入度等于出度的性质
     *  Here I use a different perspective. In a binary tree, if we consider null as leaves, then
     *  1. all non-null node provides 2 out-degree and 1 in-degree (2 children and 1 parent), except root
     *  2. all null node provides 0 out-degree and 1 in-degree (0 child and 1 parent).
     *
     *  Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree.
     *  When the next node comes, we then decrease diff by 1, because the node provides an in degree.
     *  If the node is not null, we increase diff by 2, because it provides two out degrees.
     *  If a serialization is correct, diff should never be negative and diff will be zero when finished.
     * @param preorder
     * @return
     */
    public boolean isValidSerialization1(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node: nodes) {
            if (--diff < 0) return false;
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }

    /**
     * solution2
     * 方法二
     * 从后往前遍历，由于输入是先序序列(中左右)，所以从后往前遍历一直把左右子树置成空节点
     * 最后会变成只有一个空节点的形式
     * 如 6 5 # # #
     * 遇到5，就把子树5 # #重置为#,此时变为 6 # #,这个时候又可以将这个子树置为#, 结束
     *
     * "9,3,4,#,#,1,#,#,2,#,6,#,#"
     * @param preorder
     * @return
     */
    public boolean isValidSerialization2(String preorder) {
        String[] nodes = preorder.split(",");
        int ans = 0;    // 代替栈，来记录空节点个数
        for (int i = nodes.length-1; i >= 0; --i) {
            if (!nodes[i].equals("#")) {
                if (ans < 2) {
                    return false;
                }
                ans--;
            } else {
                ans++;
            }
        }
        return ans == 1;
    }

    /**
     * 使用栈 不断把子树设置为空
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        String[] stack = new String[nodes.length];
        int idx = 0;
        for (String node: nodes) {
            stack[idx++] = node;
            while (idx >= 3 && stack[idx-1].equals("#") && stack[idx-2].equals("#")) {
                if (stack[idx-3].equals("#")) {
                    return false;
                }
                stack[idx-3] = "#";
                idx = idx-2;
            }
        }
        return idx==1 && stack[0].equals("#");
    }

    public static void main(String ...args) {
        String s = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        String ns = "#,#,#";
        System.out.println(new LC_331().isValidSerialization2(ns));
    }
}
