package jianzhi_offer;

import domain.Node;

/**
 * 给定一棵二叉树和其中一个节点，如何找出中序遍历的下一个节点？树中每个节点有三个指针-左孩子、右孩子、父亲
 * 1. 如果节点存在右子树，那么找到右孩子的最左节点
 * 2. 如果没有右子树，那么找到父节点
 *      2.1 如果当前节点时父节点的左孩子，那么下一个节点就是父节点
 *      2.2 如果当前节点时父节点的右孩子，那么继续往上找，直到找到当前节点是父节点的左孩子
 */
public class IN_8 {
    public Node getNext(Node node) {
        if (node == null) return null;
        if (node.right != null) {
            Node ansNode = node.right;
            while (ansNode.left != null) {
                ansNode = ansNode.left;
            }
            return ansNode;
        } else {
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
}
