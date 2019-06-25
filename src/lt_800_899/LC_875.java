package lt_800_899;

/**
 * [875] - minEatingSpeed
 * binary-search
 */
public class LC_875 {
    private int eatNum(int[] piles, int k) {
        int hour = 0;
        for (int num: piles) {
            hour += (num+k-1)/k;
            //Math.ceil(num/(double)k);
        }
        return hour;
    }

    /**
     * 二分K，根据不同的k需要的小时数来二分
     * O(NlogM)
     * N is length of piles
     * M is the max value of piles
     * @param piles
     * @param H
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = 1000000000;
        //Arrays.stream(piles).max().orElse(0);
        while (left < right) {
            int mid = left + (right-left)/2;
            int needH = eatNum(piles, mid);
            if(needH > H) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
