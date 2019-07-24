package other.O1Point3Acre.ms;

/**
 * 给定字符串数组，将大写字母移到所有小写字母后面，并且不改变大/小字母的相对位置。
 * 冒泡排  O(N^2)
 */
public class M_013 {
    private static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
    public static char[] sortString(char[] chs) {
        int i = 0;
        while (i < chs.length) {
            if (Character.isLowerCase(chs[i])) {
                i++;
            } else {
                int j = i+1;
                while (j < chs.length && Character.isUpperCase(chs[j])) {
                    j++;
                }
                if (j == chs.length) break;
                while (j > i) {
                    swap(chs, j, j-1);
                    j--;
                }
                i++;
            }
        }
        return chs;
    }

    public static void main(String ...args) {
        String str = "bAcJSHcbS";
        System.out.println(String.valueOf(sortString(str.toCharArray())));
    }
}
