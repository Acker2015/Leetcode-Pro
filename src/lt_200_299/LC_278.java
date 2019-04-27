package lt_200_299;


public class LC_278 {
    // implement to judge if the version is bad.
    public boolean isBadVersion(int n) {
        return true;
    }
    /**
     * binary-search
     */
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
