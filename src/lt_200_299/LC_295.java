package lt_200_299;


import java.util.PriorityQueue;

public class LC_295 {
    /**
     * @lc app=leetcode id=295 lang=java
     *
     * [295] Find Median from Data Stream
     */


    /**
     * 维持两个堆
     * 1. 大顶推 - 保存值较小的一半元素
     * 2. 小顶推 - 保存值较大的一半元素
     *
     * 插入 - O(logn)
     * 查询 - O(1)
     *
     * I keep two heaps (or priority queues):
     * Max-heap small has the smaller half of the numbers.
     * Min-heap large has the larger half of the numbers.
     *
     * This gives me direct access to the one or two middle values (they're the tops of the heaps), so getting the median takes O(1) time. And adding a number takes O(log n) time.
     */
    class MedianFinder {
        private PriorityQueue<Integer> largePQ;
        private PriorityQueue<Integer> smallPQ;

        /** initialize your data structure here. */
        public MedianFinder() {
            largePQ = new PriorityQueue<>();
            smallPQ = new PriorityQueue<>((a, b) -> b-a);
        }

        public void addNum(int num) {
            largePQ.offer(num);
            smallPQ.offer(largePQ.poll());
            if (smallPQ.size() > largePQ.size()) {
                largePQ.offer(smallPQ.poll());
            }
        }

        public double findMedian() {
            if (largePQ.size() == 0) return -1;

            if (largePQ.size() > smallPQ.size()) {
                return largePQ.peek();
            } else {
                return (largePQ.peek()+smallPQ.peek())/2.0;
            }

        }
    }
}
