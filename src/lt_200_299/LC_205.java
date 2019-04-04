package lt_200_299;

public class LC_205 {
    /**
     * [205] Isomorphic Strings
     *
     * 简单的就是在各自串中相同位置的字符，比较他们在各自串中上一次出现的位置是否相同，如果不同，就不是同构的串
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        if (len <= 1) return true;
        int[] ms = new int[256], mt = new int[256];
        for (int i = 0; i < len; ++i) {
            if (ms[s.charAt(i)] != mt[t.charAt(i)]) return false;
            ms[s.charAt(i)] = i;
            mt[t.charAt(i)] = i;
        }
        return true;
    }

    public static void main(String ...args) {
        String s = "paper", t = "title";
        LC_205 lc_205 = new LC_205();
        System.out.println(lc_205.isIsomorphic(s, t));
    }
}
