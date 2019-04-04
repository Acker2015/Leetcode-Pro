package lt_200_299;


import java.util.HashSet;

/**
 * [202] Happy Number
 */
public class LC_202 {
    private int calHappy(int n) {
        int ans = 0;
        while (n != 0) {
            ans += (int)Math.pow(n%10, 2);
            n /= 10;
        }
        return ans;
    }

    /**
     * solution 1
     * intuition, use hashset to record the happy path.
     * @param n
     * @return
     */
    public boolean isHappy1(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        while (n != 1 && !hashSet.contains(n)) {
            hashSet.add(n);
            n = calHappy(n);
        }
        return n==1;
    }


    /**
     * solution2
     * genius
     *
     * the Floyd Cycle detection algorithm
     * 原理类似检测判断链表是否存在环
     */
    public boolean isHappy(int n) {
        int fast =n, slow = n;
        do {
            slow = calHappy(slow);
            fast = calHappy(calHappy(fast));
        } while (slow!=1 && fast!=1 && slow!=fast);
        return slow == 1 || fast == 1;
    }
}
