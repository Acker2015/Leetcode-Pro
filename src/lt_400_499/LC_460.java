package lt_400_499;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * [460] LFU Cache
 */
public class LC_460 {
    /**
     * 手写双向链表，来解决
     * 元素访问，将节点插入到新频率链表的最前边
     */
    static class LFUCache1 {
        // node帮助元素优先级
        private class Node {
            int key;
            int val;
            int frequency;
            Node next, prev;
            Node(int key, int val, int frequency) {
                this.key = key;
                this.val = val;
                this.frequency = frequency;
            }
        }
        private int capacity;
        private Map<Integer, Node> memMap;
        private Node head, tail;

        // HashMap + bi-direction-list
        public LFUCache1(int capacity) {
            this.capacity = capacity;
            memMap = new HashMap<>();
            head = new Node(0,-1, Integer.MAX_VALUE);
            tail = new Node(0, -1, 0);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 增加node优先级，并往前移动到新优先级的头部位置
         * @param node
         */
        private void addPriority(Node node, boolean cutOff) {
            node.frequency += 1;
            Node prevNode = node.prev;
            if (cutOff) {
                // cut off the pointer relation
                prevNode.next = node.next;
                node.next.prev = prevNode;
            }
            while (prevNode.frequency <= node.frequency && prevNode != head) {
                prevNode = prevNode.prev;
            }
            // insert node as the next of prevNode
            node.next = prevNode.next;
            prevNode.next.prev = node;
            node.prev = prevNode;
            prevNode.next = node;
        }

        public int get(int key) {
            if (memMap.containsKey(key)) {
                Node foundNode = memMap.get(key);
                addPriority(foundNode, true);
                return foundNode.val;
            } else {
                return -1;
            }
        }
        public void put(int key, int value) {
            if (memMap.containsKey(key)) {
                Node foundNode = memMap.get(key);
                foundNode.val = value;
                addPriority(foundNode, true);
            } else {
                if (capacity <= 0) return;
                // 删除多余节点，再进行插入
                if (memMap.size() >= capacity) {
                    // delete tail node
                    Node deleteNode = tail.prev;
                    memMap.remove(deleteNode.key);
                    deleteNode.prev.next = tail;
                    tail.prev = deleteNode.prev;
                    deleteNode.prev = deleteNode.next = null;
                }

                Node newNode = new Node(key, value, 0);
                // put into map
                memMap.put(key, newNode);
                newNode.prev = tail.prev;
                // adjust priority
                addPriority(newNode, false);

            }
        }
    }

    static class LFUCache {
        Map<Integer, Integer> kvMap;        // 记录k-v关系
        Map<Integer, Integer> kfreMap;      // 记录key和frequency的关系
        Map<Integer, LinkedHashSet<Integer>> linkMap;   // 为每一个frequency准备一个linkedHashSet来存放节点，新访问的增加到最后
        int minFre = 0;
        int capacity;
        public LFUCache(int capacity) {
            this.kvMap = new HashMap<>();
            this.kfreMap = new HashMap<>();
            this.linkMap = new HashMap<>();
            this.capacity = capacity;
        }

        /**
         * 通过key获取对应的频率，然后更新频率，更新相应频率对应的访问列表
         * -- 可能需要同时更新最小频率的指针
         * @param key
         * @return
         */
        public int get(int key) {
            if (kvMap.containsKey(key)) {
                int fre = kfreMap.get(key);
                kfreMap.put(key, fre+1);
                linkMap.get(fre).remove(key);
                linkMap.computeIfAbsent(fre+1, k->new LinkedHashSet<>()).add(key);
                if (fre == minFre && linkMap.get(fre).size() <= 0) {
                    minFre++;
                }
                return kvMap.get(key);
            }
            return -1;
        }

        /**
         * 1. key已经存在，更新key-value, 更新key的访问
         * 2. key不存在
         *      2.1 capacity用完，那么删除最少最近未使用的key
         *      2.2 更新新key， 更新对应最小频率指针
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            if (this.capacity <= 0) return;
            if (kvMap.containsKey(key)) {
                kvMap.put(key, value);
                get(key);
            } else {
                if (kvMap.size() >= this.capacity) {
                    //int min = getMin();
                    int deleteKey = linkMap.get(minFre).iterator().next();
                    linkMap.get(minFre).remove(deleteKey);
                    kfreMap.remove(deleteKey);
                    kvMap.remove(deleteKey);
                }
                kvMap.put(key, value);
                kfreMap.put(key, 1);
                linkMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
                minFre = 1;
            }
        }
    }

    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    /**
     * ["LFUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
     * [[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
     *
     * [null,null,null,null,null,4,3,2,-1,null,-1,2,3,-1,5]
     * @param args
     */
    public static void main(String ...args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4

    }
}
