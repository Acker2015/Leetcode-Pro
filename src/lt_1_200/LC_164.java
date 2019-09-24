package lt_1_200;


import java.util.ArrayList;

// 164. Maximum Gap
public class LC_164 {
    /**
     * bucket-sort
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
     Return 0 if the array contains less than 2 elements.

     题目的要求是用linear time。对于排序，能达到linear的算法有：bucket sort, radix sort 和 counting sort.这道题里用到了bucket sort。
     引自leetcode的solution
     “Suppose there are N elements and they range from A to B.
     Then the maximum gap will be no smaller than ceiling[(B – A) / (N – 1)]
     Let the length of a bucket to be len = ceiling[(B – A) / (N – 1)],
     then we will have at most num = (B – A) / len + 1 of bucket
     for any number K in the array,
     we can easily find out which bucket it belongs by calculating loc = (K – A) / len and therefore maintain the maximum and minimum elements in each bucket.”
     所以最大间距不会出现在桶里只会出现的桶之间
     */
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if (len <= 1) return 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num: nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        // 数组中值全部相等的特殊比较
        if (max == min) return 0;
        int gap = (max-min)%len == 0 ? (max-min)/len: (max-min)/len+1;
        int bucketNum = (max-min)/gap+1;
        ArrayList[] bucket = new ArrayList[bucketNum];
        for (int num: nums) {
            int bucketIdx = (num-min)/gap;
            if (bucket[bucketIdx] == null) {
                bucket[bucketIdx] = new ArrayList<Integer>();
                bucket[bucketIdx].add(num);
                bucket[bucketIdx].add(num);
            } else {
                bucket[bucketIdx].set(0, Math.min((Integer) bucket[bucketIdx].get(0), num));
                bucket[bucketIdx].set(1, Math.max((Integer) bucket[bucketIdx].get(1), num));
            }
        }
        int maximumGap = -1;
        int lastMaxGap = -1;
        for (int i = 0; i < bucketNum; ++i) {
            ArrayList list = bucket[i];
            if (list == null || list.size() <= 0) continue;
            if (maximumGap == -1) {
                maximumGap = (int)list.get(1) - (int)list.get(0);
                lastMaxGap = (int)list.get(1);
            } else {
                maximumGap = Math.max(maximumGap, (int)list.get(0)-lastMaxGap);
                lastMaxGap = (int)list.get(1);
            }
        }
        return maximumGap;
    }

    public static void main(String[] args) {
        int[] arr = {3,6,9,1};
        System.out.println(new LC_164().maximumGap(arr));
    }
}
