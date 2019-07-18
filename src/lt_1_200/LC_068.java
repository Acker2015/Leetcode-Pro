package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [68] Text Justification
 * 常规贪心
 */
public class LC_068 {
    private String fixLengthStr(int len) {
        char[] chs = new char[len];
        Arrays.fill(chs, ' ');
        return String.valueOf(chs);
    }
    private String generate(String[] words, int from, int end, int allWordWidth, int maxWidth) {
        StringBuilder builder = new StringBuilder();
        int wordNum = end - from;
        int mod = wordNum == 1 ? 0 : (maxWidth - allWordWidth) % (wordNum - 1);
        int base = wordNum == 1 ? (maxWidth - allWordWidth) : (maxWidth - allWordWidth) / (wordNum - 1);
        for (int i = from; i < end; ++i) {
            builder.append(words[i]);
            if (i < end - 1) {
                if (end == words.length) {
                    builder.append(' ');
                } else {
                    int spaceNum = mod-- > 0 ? (base + 1) : base;
                    builder.append(fixLengthStr(spaceNum));
                }
            }
        }
        // 后续使用空格补全 - 对于最后一行的情况
        if (builder.length() < maxWidth) {
            builder.append(fixLengthStr(maxWidth-builder.length()));
        }
        return builder.toString();
    }
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> retList = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int ans = words[i].length();
            if (ans > maxWidth) {
                return null;
            }
            int j = i + 1;
            while (j < words.length && ans+words[j].length()+(j-i) <= maxWidth) {
                ans += words[j].length();
                j++;
            }
            retList.add(generate(words, i, j, ans, maxWidth));
            i = j;
        }
        return retList;
    }

    public static void main(String ...args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        LC_068 lc_068 = new LC_068();
        lc_068.fullJustify(words, 16).stream().forEach(System.out::println);
    }
}
