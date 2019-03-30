package lt_1_200;

import domain.Node;

public class LC_116 {

    /**
     * [116] Populating Next Right Pointers in Each Node
     *
     * 这个由于是完全二叉树，所以遍历节点要将左子树的最右路径和右子树的最左路径连接起来
     *
     * 更加通用的解法参考117题， 使用虚拟头结点来帮助层序遍历
     */
    private void opNext(Node leftNode, Node rightNode) {
        while (leftNode!=null) {
            leftNode.next = rightNode;
            leftNode = leftNode.right==null ? leftNode.left : leftNode.right;
            rightNode = rightNode.left==null ? rightNode.right : rightNode.left;
        }
    }
    public Node connect(Node root) {
        if (root == null) return root;
        opNext(root.left, root.right);
        connect(root.left);
        connect(root.right);
        return root;
    }
}
