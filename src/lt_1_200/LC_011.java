package lt_1_200;
/**
 *
 * [11] Container With Most Water
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 *
 * 认真研究一下寻找过程，
 * 我们从第一个高度为起始容器壁，那么我们直接以最后一个高度为终止壁，如果a1 <= an，那么以a1为起始的容器最大是a1 * （n - 1），以a1为容器壁的最大容器计算出来的。
 * 那么以a1为壁的所有情况不需要再考虑，接着考虑a2的；
 * 同理，如果a1 > an,an不再考虑，考虑an-1，这有点类似"夹逼定理"。比较ai和aj（i<j）如果ai <= aj，i++；否者j++。直到i == j。这个算法的时间复杂度是（O（n））。
 * @author Acker
 *
 */
public class LC_011 {
	
	public int maxArea(int[] height) {
        int area = 0, left = 0, right = height.length-1;
        while(left < right) {
        		area = Math.max(area, Math.min(height[left],height[right])*(right-left));
        		if (height[left] < height[right]) {
        			left++;
        		} else {
        			right--;
        		}
        }
        return area;
    }
	public static void main(String[] args) {
		int[] test = {1,8,6,2,5,4,8,3,7};
		System.out.println(new LC_011().maxArea(test));
	}
}
