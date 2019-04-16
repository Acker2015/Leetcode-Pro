package lt_1_200;


import lt_300_399.LC_392;

public class LC_004 {
    /**
     *
     * [4] Median of Two Sorted Arrays
     *
     * time O(log(min(m,n)))
     * 二分 在nums1中找到一个分割点i,在num2中找到一个分割点j
     * 使得i+j正好可以平分nums1和nums2的总集合
     * 即 (i+j)*2 = m+n
     * nums1[i-1] <= nums2[j]
     * nums2[j-1] <= nums1[i]
     *
     * 这样就能保证
     * 集合 S1->{nums1[0],...,nums1[i-1],nums2[0],...,nums2[j-1]}中的任一个数
     * 总体小于
     * 集合 S2->{nums1[i],...,nums[m-1],nums2[j],...,nums2[n-1]}中的任一个数
     * 由于两个集合各占一半，所以中位数来自于两个集合的解
     * 1. (m+n)%2==1 -> median = Max-Of-S1
     * 2. (m+n)%2==0 -> median = (Max-Of-S1 + Min-Of-S2)/2.0
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // make sure length of nums1 is greater than the length of nums2
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m, half = (m+n+1)/2;
        while (left <= right) {
            int i = (right-left)/2 + left;
            int j = half - i;
            if (i > left && nums1[i-1] > nums2[j]) {
                right = i-1; // i is too big
            } else if (i < right && nums2[j-1] > nums1[i]) {
                left = i+1; // i is too small
            } else {
                // nums1[i-1]<=nums2[j] && nums2[j-1]<=nums1[i]
                int leftMax=0;
                if (i == 0) leftMax = nums2[j-1];
                else if (j==0) leftMax = nums1[i-1];
                else leftMax = Math.max(nums1[i-1], nums2[j-1]);
                if ((m+n)%2==1) return leftMax;
                int rightMin=0;
                if (i==m) rightMin = nums2[j];
                else if (j == n) rightMin = nums1[i];
                else rightMin = Math.min(nums1[i], nums2[j]);
                return (leftMax+rightMin)/2.0;
            }
        }
        return 0.0;
    }
    public static void main(String ...args) {
        int[] nums1 = {1, 3, 5, 6, 7, 8, 9,10};
        int[] nums2 = {2, 4};
        LC_004 lc_004 = new LC_004();
        System.out.println(lc_004.findMedianSortedArrays(nums1, nums2));
    }
}
