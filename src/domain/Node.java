package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/29
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node prev;

    public Node random;
    public Node parent;
    public Node child;
    public List<Node> neighbors;
    public List<Node> children;
    public Node () {

    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
    public Node(int val, List<Node> list) {
        this.val = val;
        this.neighbors = list;
    }
}
