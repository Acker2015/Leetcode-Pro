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

	/**
	 * Here is my idea: instead of calculating area by height*width, we can think it in a cumulative way. In other words, sum water amount of each bin(width=1).
	 Search from left to right and maintain a max height of left and right separately, which is like a one-side wall of partial container.
	 Fix the higher one and flow water from the lower part.

	 For example, if current height of left is lower, we fill water in the left bin. Until left meets right, we filled the whole container.

     two pointers， left - right
     两个变量分别表示左边的最大值和右边的最大值，每次都先处理left和right中坐标比较小的。

     可以得到 如果height[left] <= height[right], 此时如果leftMax比height[left]小，则leftMax肯定小于Math.max(rightMax, height[right])
     如果leftMax一直是最大的，那么在height[left]=leftMax的时候，一直在处理right指针
     所以
        1. 处理左指针的时候， 右边肯定有值比leftMax大
        2. 处理右指针的时候，左边肯定有值比rightMax大
     因为每次都优先处理 height[left]和height[right]中的更小的值
	 * @param height
	 * @return
	 */
	public int trap1(int[] height) {
	    int left = 0, right = height.length-1, area = 0;
	    int leftMax = 0, rightMax = 0;
	    while (left <= right) {
	        if (height[left] <= height[right]) {
	            if (height[left] >= leftMax) {
	                leftMax = height[left];
                } else {
	                area += (leftMax - height[left]);   // 此时可以确定rightMax >= leftMax
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    area += (rightMax - height[right]);
                }
                right--;
            }
        }
        return area;
    }




	public int trap2(int[] height) {
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
