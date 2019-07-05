package lt_400_499;


import java.util.Arrays;

/**
 * [455] Assign Cookies
 * 贪心
 * 对于每个孩子的g，在cookie中找到第一个比g大的就最优找法
 */
public class LC_455 {
    private int binary_search(int[] arr, int left, int right, int val) {
        while (left < right) {
            int mid = left + (right-left)/2;
            if (arr[mid] < val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /**
     * solution1
     * two pointers
     * 排序之后双指针 O(m+n)
     */
    public int findContentChildren1(int[] g, int[] s) {
        Arrays.sort(s);
        Arrays.sort(g);
        int contentChild = 0;
        int gIdx = 0, sIdx = 0;
        while (gIdx < g.length && sIdx < s.length) {
            if (g[gIdx] <= s[sIdx]) {
                contentChild++;
                gIdx++;
                sIdx++;
            } else {
                sIdx++;
            }
        }
        return contentChild;
    }

    /**
     * solution2
     * 二分方法找到第一个大于等于gi的cookie
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(s);
        Arrays.sort(g);
        int contentChild = 0, idx = 0;
        for (int val: g) {
            idx = binary_search(s, idx, s.length, val);
            if (idx >= s.length) break;
            contentChild++;
            idx++;
        }
        return contentChild;
    }
}
