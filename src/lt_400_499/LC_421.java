package lt_400_499;

/**
 * [421] Maximum XOR of Two Numbers in an Array
 *
 * bit manipulation + 字典树(高位->低位)
 */
public class LC_421 {
    private static class Node {
        Node[] next;
        Node() {this.next = new Node[2];}
    }
    private void insert(Node node, int num) {
        for (int d = 1; d < 32; ++d) {
            int b = (num & (1 << (31 - d))) > 0 ? 1 : 0;
            if (node.next[b] == null) {
                node.next[b] = new Node();
            }
            node = node.next[b];
        }
    }
    private int findMaxXOR(Node node, int num) {
        for (int d = 1; d < 32; ++d) {
            int bit = (num & (1 << (31-d))) > 0 ? 1 : 0;
            if (node.next[0] != null && node.next[1] != null) {
                num |= 1 << (31-d);
                node = node.next[bit^1];
            } else if (node.next[0]!= null) {
                node = node.next[0];
            } else {
                num ^= 1 << (31-d);
                node = node.next[1];
            }
        }
        return num;
    }
    /**
     * 字典树的解法
     */
    public int findMaximumXOR(int[] nums) {
        if (nums.length <= 1) return 0;
        Node node = new Node();
        insert(node, nums[0]);
        int maxXOR = 0;
        for (int i = 1; i < nums.length; ++i) {
            maxXOR = Math.max(findMaxXOR(node, nums[i]), maxXOR);
            insert(node, nums[i]);
        }
        return maxXOR;
    }
}
