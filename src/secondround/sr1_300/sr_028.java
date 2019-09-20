package secondround.sr1_300;

public class sr_028 {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int needleLen = needle.length();
        for (int i = 0; i < haystack.length(); ++i) {
            if (i + needleLen > haystack.length()) break;
            boolean match = true;
            for (int j = 0; j < needleLen; ++j) {
                if (needle.charAt(j) != haystack.charAt(i+j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }
}
