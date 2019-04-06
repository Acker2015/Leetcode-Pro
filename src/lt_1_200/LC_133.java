package lt_1_200;

import domain.Node;

import java.util.*;

public class LC_133 {

    /**
     * DFS实现
     */
    private Map<Node, Node> map = new HashMap<>();
    public Node cloneGraphDFS(Node node) {
        if (node == null) return null;
        Node cloneNode = new Node(node.val, new ArrayList<>());
        map.put(node, cloneNode);
        for (Node ans: node.neighbors) {
            if (map.containsKey(ans)) {
                cloneNode.neighbors.add(map.get(ans));
            } else {
                cloneNode.neighbors.add(cloneGraphDFS(ans));
            }
        }
        return cloneNode;
    }

    /**
     * BFS实现
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Queue<Node> queue = new LinkedList<>();
        Node cloneNode = new Node(node.val, new ArrayList<>());
        queue.offer(node);
        Map<Node, Node> map = new HashMap<>();
        map.put(node, cloneNode);
        while (queue.size() > 0) {
            Node fromNode = queue.poll();
            Node toNode = map.get(fromNode);

            for (Node ansNode: fromNode.neighbors) {
                // 如果已经复制过，那么直接连接，不用继续搜
                if (map.containsKey(ansNode)) {
                    // connect
                    toNode.neighbors.add(map.get(ansNode));
                } else {
                    // 如果没有复制过，那么创建新节点、继续搜索
                    // create-connect-offer
                    Node newNode = new Node(ansNode.val,new ArrayList<>());
                    toNode.neighbors.add(newNode);
                    map.put(ansNode, newNode);
                    queue.offer(ansNode);
                }
            }
        }
        return cloneNode;
    }



}
