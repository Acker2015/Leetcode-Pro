package lt_400_499;

import java.util.Arrays;

/**
 * [475] Heaters
 * solution1: binary_search
 * solution2: two pointers
 */
public class LC_475 {
    private int binary_search(int[] arr, int left, int val){
        int right = arr.length;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (arr[mid] < val) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    /**
     * O(mlogn)
     * binary_search
     * m is the length of houses, and n is the length of heaters
     */
    public int findRadius1(int[] houses, int[] heaters) {
        if (houses.length <= 1) return 0;
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int radius = 0;
        int idx = 0;
        for (int house: houses) {
            idx = binary_search(heaters, idx, house);
            // 剪枝
            if (idx >= heaters.length) {
                radius = Math.max(radius, houses[houses.length-1] - heaters[heaters.length-1]);
                break;
            }
            int disRight = idx < heaters.length ? (heaters[idx]-house) : Integer.MAX_VALUE;
            int disLeft = idx > 0 ? house-heaters[idx-1] : Integer.MAX_VALUE;
            radius = Math.max(radius, Math.min(disLeft, disRight));
        }
        return radius;
    }

    /**
     * two pointers
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int j = 0, radius = 0;
        for (int house: houses) {
            // while (j+1 < heaters.length && Math.abs(house-heaters[j+1]) <= Math.abs(house-heaters[j])) {
            //     j++;
            // }
            // 这个判断有点东西，减少计算。性能更高
            while (j+1 < heaters.length && heaters[j]+heaters[j+1]<=2*house) {
                j++;
            }
            radius = Math.max(radius, Math.abs(house-heaters[j]));
        }
        return radius;
    }
}
