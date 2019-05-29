package lt_400_499;

import java.util.PriorityQueue;

/**
 * [480] Sliding Window Median
 * 维护两个队列，各存放窗口内数的一半
 * lower_queue 为大顶堆，存放前半段
 * upper_queue 为小顶堆，存放后半段
 * 这样可以保证lower_queue -> upper_queue相对有序，upper_queue中的数都大于等于lower_queue
 * 这样median就可以通过这两个优先队列顶部值获取
 * k为奇数 median = lower_queue.peek()
 * k为偶数 median = (upper_queue.peek() + lower_queue.peek())/2
 *
 * 通过这个思想可以来解决掉这个问题
 */
public class LC_480 {
    // 两个优先队列各维护一半的数据 lower_queue.size() - upper_queue.size() <= 1
    private PriorityQueue<Integer> lower_queue = new PriorityQueue<>((a, b)-> a<b?1:-1); // 大顶堆优先队列
    private PriorityQueue<Integer> upper_queue = new PriorityQueue<>();             // 小顶堆优先队列
    // 保持两个队列各维护一半数据，lower_queue维护前半段， upper_queue维护后半段
    private void balance() {
        while (upper_queue.size() > lower_queue.size()) {
            lower_queue.offer(upper_queue.poll());
        }
        // 保证队列各维护一半数据
        while (lower_queue.size() > upper_queue.size()+1) {
            upper_queue.offer(lower_queue.poll());
        }
    }
    private double getMedian(int k) {
        if (k % 2 == 0) {
            return ((double)lower_queue.peek() + (double)upper_queue.peek())/2;
        } else {
            return (double)lower_queue.peek();
        }
    }
    private void add(int val) {
        if (lower_queue.size() == 0 || lower_queue.peek() >= val) {
            lower_queue.offer(val);
        } else {
            upper_queue.offer(val);
        }
        balance();
    }
    private void delete(int val, int k) {
        double mediam = getMedian(k);
        if (val <= mediam) {
            lower_queue.remove(val);
        } else {
            upper_queue.remove(val);
        }
        balance();
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (k <= 0 || k > nums.length) return new double[0];
        double[] medianWindow = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; ++i) {
            if (i >= k) {
                delete(nums[i-k], k);
            }
            add(nums[i]);
            if (i >= k-1) {
                medianWindow[i-k+1] = getMedian(k);
                //System.out.println(String.valueOf(i-k+1) + " : " + medianWindow[i-k+1]);
            }
        }
        return medianWindow;
    }
}
