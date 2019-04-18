package lt_1_200;

/**
 * [165] Compare Version Numbers
 * 版本大小比较
 *
 * 注意点
 * 1. 版本后缀0， 1.2.0.0.0 等同于 1.2
 * 2. 子版本数字的表达 1.002.0.0 等同于 1.2
 */
public class LC_165 {
    private String clearPostfix0(String v) {
        int i = v.length()-1;
        while (i>=0) {
            if (v.charAt(i)=='.') {
                i--;
            } else {
                int j = i-1;
                while (j >= 0 && Character.isDigit(v.charAt(j))) j--;
                if (Integer.parseInt(v.substring(j+1, i+1)) > 0) {
                    return v.substring(0, i+1);
                } else {
                    i = j;
                }
            }
        }
        return "";
    }
    private int compare(String v1, int idx1, String v2, int idx2) {
        if (v1.length()<=idx1 || v2.length()<=idx2) {
            if (v1.length()<=idx1 && v2.length()<=idx2) return 0;
            return idx1 < v1.length() ? 1 : -1;
        }
        int i = idx1+1, j = idx2+1;
        while (i < v1.length() && Character.isDigit(v1.charAt(i))) i++;
        while (j < v2.length() && Character.isDigit(v2.charAt(j))) j++;
        int ans1 = Integer.parseInt(v1.substring(idx1, i));
        int ans2 = Integer.parseInt(v2.substring(idx2, j));
        if (ans1 > ans2) {
            return 1;
        } else if (ans1 < ans2) {
            return -1;
        } else {
            return compare(v1, i+1, v2, j+1);
        }
    }
    public int compareVersion(String version1, String version2) {
        version1 = clearPostfix0(version1);
        version2 = clearPostfix0(version2);
        return compare(version1, 0, version2, 0);
    }

    public static void main(String ...args) {
        String v1 = "1.0", v2="1.0.0";
        System.out.println(new LC_165().compareVersion(v1, v2));
    }
}
