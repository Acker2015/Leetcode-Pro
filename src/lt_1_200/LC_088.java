package lt_1_200;

/**
 * 数组合并
 * 由于数据都要合并到nums1，而nums1数组的前边的位置都有数据，所以这个考虑从后往前存放数据
 * @author Acker
 *
 */
public class LC_088 {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m+n-1;
        while(m>0 && n>0) {
            if (nums1[m-1] >= nums2[n-1]) {
                nums1[index--] = nums1[m-1];
                m--;
            } else {
                nums1[index--] = nums2[n-1];
                n--;
            }
        }
        while(n > 0) {
            nums1[index--] = nums2[n-1];
            n--;
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
