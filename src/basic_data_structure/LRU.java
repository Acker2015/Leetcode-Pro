package basic_data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * 双向链表 + map
 * 1. 链表使用虚拟首尾节点固定，头部存放最新访问节点，尾部存放最久未被访问节点
 * 2. 使用map来帮助存取实现O(1)
 */
public class LRU {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }
    private Node head;
    private Node tail;
    private int capacity;
    private Map<Integer, Node> lruMap;
    public LRU(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        lruMap = new HashMap<>();
    }

    /**
     * 将访问节点调整为最新
     * @param node
     */
    private void adjustNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        // insert after head
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    /**
     * 头部插入新节点
     * @param node
     */
    private void insertNewNode(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        lruMap.put(node.key, node);
    }

    /**
     * 尾部删除旧节点
     */
    private void deleteOldNode() {
        Node prevNode = tail.prev;
        if (prevNode != head) {
            prevNode.prev.next = tail;
            tail.prev = prevNode.prev;
            prevNode.next = prevNode.prev = null;
            lruMap.remove(prevNode.key);
        }
    }

    /**
     * 1. 如果key已经存在，需要更新值，并调整key为最新访问(节点在链表中移到头部)
     * 2. 如果key不存在，插入最新节点，如果容量超过capacity，删除最久未访问节点
     * @param key
     * @param val
     */
    public void put(Integer key, Integer val) {
        if (lruMap.containsKey(key)) {
            Node node = lruMap.get(key);
            adjustNode(node);
            node.value = val;
        } else {
            Node node = new Node(key, val);
            insertNewNode(node);
            if (lruMap.size() > capacity) {
                deleteOldNode();    // 容量超过capacity，删除最久未访问节点
            }
        }
    }

    public Integer get(Integer key) {
        if (lruMap.containsKey(key)) {
            Node node = lruMap.get(key);
            adjustNode(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public static void main(String ...args) {
        LRU cache = new LRU(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
