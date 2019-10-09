package lt_200_299;

import java.util.Arrays;

/**
 * [274] H-Index
 *
 * "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 */
public class LC_274 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = citations.length-1; i >= 0; --i) {
            int ans = citations.length - i;
            // 引用论文数大于等于paper个数ans (这里主要是为了保证论文引用数大于等于h的要求)
            // 并且 剩下论文的引用数都小于当前引用数 (这里是为了保证剩下论文引用数小于等于h的要求)
            if (citations[i] >= ans && (i == 0 || citations[i-1] <= ans)) {
                h = ans;
            }
        }
        return h;
    }

    /**
     * 桶排序
     */
    public static class Solution2 {
        /**
         * This type of problems always throw me off, but it just takes some getting used to. The idea behind it is some bucket sort mechanisms.
         * First, you may ask why bucket sort. Well, the h-index is defined as the number of papers with reference greater than the number.
         * So assume n is the total number of papers, if we have n+1 buckets, number from 0 to n,
         * then for any paper with reference corresponding to the index of the bucket, we increment the count for that bucket.
         * The only exception is that for any paper with larger number of reference than n, we put in the n-th bucket.
         *
         *
         * Then we iterate from the back to the front of the buckets,
         * whenever the total count exceeds the index of the bucket, meaning that we have the index number of papers that have reference greater than or equal to the index.
         * Which will be our h-index result.
         * The reason to scan from the end of the array is that we are looking for the greatest h-index.
         *
         * For example, given array [3,0,6,5,1],
         * we have 6 buckets to contain how many papers have the corresponding index. Hope to image and explanation help.
        */
        public int hIndex(int[] citations) {
            int n = citations.length;
            int[] buckets = new int[n+1];
            for(int c : citations) {
                if(c >= n) {
                    buckets[n]++;
                } else {
                    buckets[c]++;
                }
            }
            int count = 0;
            for(int i = n; i >= 0; i--) {
                count += buckets[i];    // paper个数

                // 如果paper个数大于等于当前最低的引用数目，则h有效
                if(count >= i) {
                    return i;
                }
            }
            return 0;
        }
    }
}
