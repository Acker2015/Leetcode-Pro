package lt_600_699;


import java.util.*;

/**
 * [658] Find K Closest Elements
 * 三种解法
 * 1. 堆 O(nlogk)
 * 2. two pointers O(n)
 * 3. 二分查找 O(logn + k) - 更加巧妙的二分
 * 4. 同样二分查找 O(logn+k)
 */
public class LC_658 {
    /**
     * solution1
     * heap O(nlogk)
     */
    static class Solution1 {
        private class ClosestComparator implements Comparator<Integer> {
            final int x;
            ClosestComparator(int x) {
                this.x = x;
            }
            @Override
            public int compare(Integer a, Integer b) {
                int l = Math.abs(a-x);
                int r = Math.abs(b-x);
                if (l != r) {
                    return r - l;
                } else {
                    return b - a;
                }
            }
        }
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(new ClosestComparator(x));
            for (int item: arr) {
                pq.offer(item);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
            List<Integer> list = new ArrayList<>();
            list.addAll(pq);
            Collections.sort(list);
            return list;
        }
    }
    static class Solution2 {
        /**
         * solution1
         * two pointers
         * 有序数组从两头抛弃与x距离较远的一个元素，不断往中间挤压
         * O(n)
         */
        public List<Integer> findClosestElements1(int[] arr, int k, int x) {
            int i = 0, j = arr.length-1;
            while (j-i+1 > k) {
                int left = Math.abs(arr[i]-x);
                int right = Math.abs(arr[j]-x);
                if (left > right) {
                    i++;
                } else {
                    j--;
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int t = i; t <= j; ++t) {
                list.add(arr[t]);
            }
            return list;
        }
    }

    static class Solution3 {
        /**
         * solution3
         * binary-search
         * 枚举窗口起始位置,对窗口的起始位置做二分
         * 二分标准(窗口的前后位置i, i+k), 看取i还是取i+k
         * 那么结果区间就是[start, start+k)
         *
         * O(logn + k)
         */
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int left = 0, right = arr.length-k;
            while (left < right) {
                int mid = left + (right-left)/2;
                // compare mid and (mid+k)，judge the begin position of the window
                if (x-arr[mid] > arr[mid+k]-x) {
                    left = mid+1;
                } else {
                    right = mid;
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int i = left; i < left+k; ++i) {
                list.add(arr[i]);
            }
            return list;
        }
    }

    /**
     * 二分+two-pointers
     * O(logn + k)
     * 先通过二分找到第一个大于等于x的位置
     * 然后通过two-pointers往两边扩散，找到closestElements的起始位置
     */
    public static class Solution4 {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int left = 0, right = arr.length, mid;
            while (left < right) {
                mid = left + (right-left)/2;
                if (arr[mid] < x) {
                    left = mid+1;
                } else {
                    right = mid;
                }
            }
            // 两边扩散找到起始位置
            int i = left-1, j = left, start = left;
            for (int m = 1; m <= k; ++m) {
                if (j >= arr.length) {
                    start = i;
                    i--;
                } else if (i < 0) {
                    break;
                } else {
                    if (Math.abs(arr[i]-x) <= Math.abs(arr[j]-x)) {
                        start = i;
                        i--;
                    } else {
                        j++;
                    }
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int m = start; m < start+k; ++m) {
                list.add(arr[m]);
            }
            return list;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0,1,1,1,2,3,6,7,8,9};
        Solution4 solution = new Solution4();
        solution.findClosestElements(nums, 9, 4).forEach(System.out::println);
    }
}
