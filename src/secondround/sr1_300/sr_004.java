package secondround.sr1_300;

/**
 * 二分
 */
public class sr_004 {
    private int findKth(int[] A, int i, int[] B, int j, int k) {
        if (i >= A.length) {
            return B[j+k-1];
        }
        if (j >= B.length) {
            return A[i+k-1];
        }
        if (k == 1) {
            return Math.min(A[i], B[j]);
        }
        int mid_A = Integer.MAX_VALUE, mid_B = Integer.MAX_VALUE;
        if (i+k/2-1 < A.length) {
            mid_A = A[i+k/2-1];
        }
        if (j+k/2-1 < B.length) {
            mid_B = B[j+k/2-1];
        }
        if (mid_A < mid_B) {
            return findKth(A, i+k/2, B, j, k-k/2);
        } else {
            return findKth(A, i, B, j+k/2, k-k/2);
        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int l = (len1+len2+1)/2;
        int r = (len1+len2+2)/2;
        return (findKth(nums1,0,nums2,0,l)+findKth(nums1,0,nums2,0,r))/2.0;
    }
    /**
     * 二分解法，考虑奇偶性
     * @param args
     */
    public static void main(String ...args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(new sr_004().findMedianSortedArrays(nums1, nums2));
    }
}
