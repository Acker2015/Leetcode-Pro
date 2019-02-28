package lt_1_200;

/**
 * 观察下就可以发现被水填满后的形状是先升后降的塔形，
 * 因此，先遍历一遍找到塔顶，
 * 然后分别从两边开始，往塔顶所在位置遍历，水位只会增高不会减小，且一直和最近遇到的最大高度持平，这样知道了实时水位，就可以边遍历边计算面积。
 * @author Acker
 *
 */
public class LC_042 {
	public int trap(int[] height) {
		if (height.length <= 1) {
			return 0;
		}
		int maxVal=height[0], maxIndex=0;
		for(int i = 1; i < height.length; ++i) {
			if (height[i] > maxVal) {
				maxVal = height[i];
				maxIndex = i;
			}
		}
		int trapWater = 0, root = height[0];
		for (int i = 0; i < maxIndex; ++i) {
			if (height[i] > root) {
				root = height[i];
			} else {
				trapWater += (root - height[i]);
			}
		}
		root = height[height.length-1];
		for(int i = height.length - 1; i > maxIndex; --i) {
			if (height[i] > root) {
				root = height[i];
			} else {
				trapWater += (root - height[i]);
			}
		}
		return trapWater;
	}
	public int trap1(int[] height) {
		int area = 0, l = 0, r = height.length - 1;
		while(l < r) {
			int mh = Math.min(height[l], height[r]);
			if (mh == height[l]) {
				while (++l < r && height[l] <= mh) {
					area += (mh - height[l]);
				}
			} else {
				while (--r > l && height[r] <= mh) {
					area += (mh - height[r]);
				}
			}
		}
		return area;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
