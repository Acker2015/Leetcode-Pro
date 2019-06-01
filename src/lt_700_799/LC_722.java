package lt_700_799;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. 开始标志和结束标志不能出现先程序语句的字符串里
 * 2. // 和 /*同时出现的时候，先出现的优先级高
 * 3. // 和 "block结束标志"同时出现的时候, 注意同一行出现
 * 4. block注释开始和结束之外的内容如果是同一行需要合并成一行显示
 */
public class LC_722 {
    private static final char QUOTE = '"';
    private static final String ROW_COMMENT = "//";
    private static final String BLOCK_COMMENT_START = "/*";
    private static final String BLOCK_COMMENT_END = "*/";

    private int findComment(String sentence) {
        boolean quote = false;
        for (int i = 0; i < sentence.length()-1; ++i) {
            if (sentence.charAt(i) == QUOTE) {
                quote = !quote;
            }
            String tmp = sentence.substring(i, i+2);
            if (!quote && (tmp.equals(ROW_COMMENT) || tmp.equals(BLOCK_COMMENT_START))){
                return i;
            }
        }
        return -1;
    }
    private int findCommentEnd(String sentence) {
        boolean quote = false;
        for (int i = 0; i < sentence.length()-1; ++i) {
            if (sentence.charAt(i) == QUOTE) {
                quote = !quote;
            }
            if (!quote && sentence.startsWith(BLOCK_COMMENT_END, i)) {
                return i;
            }
        }
        return -1;
    }
    public List<String> removeComments1(String[] source) {
        String[] copySource = source.clone();
        List<String> retList = new ArrayList<>();
        int i = 0;
        while (i < copySource.length) {
            String sentence = copySource[i];
            if (sentence.length() <= 0) {
                i++;
                continue;
            }
            int beginIdx = findComment(sentence);
            if (beginIdx < 0) {
                i++;
                retList.add(sentence);
                continue;
            }
            boolean isRowComment = sentence.startsWith(ROW_COMMENT, beginIdx);
            if (isRowComment) {
                copySource[i] = sentence.substring(0, beginIdx);
            } else {

                StringBuilder builder = new StringBuilder(sentence.substring(0, beginIdx));
                // 重置该行记录，防止出现block注释起始和结束在同一行
                copySource[i] = sentence.substring(beginIdx+2);
                int j = i, endIdx = -1;
                while (j < source.length && (endIdx=findCommentEnd(copySource[j])) < 0) {
                    j++;
                }
                if (j == source.length) break;
                builder.append(copySource[j].substring(endIdx+2));
                copySource[j] = builder.toString();
                i = j;
            }
        }
        return retList;
    }

    /**
     * 正则表达式解法
     * /\\*(.|\n)*? 表示非贪婪匹配
     * @param source
     * @return
     */
    public List<String> removeComments(String[] source) {
        String txt = String.join("\n", source).replaceAll("//.*|/\\*(.|\n)*?\\*/", "");
        return Arrays.stream(txt.split("\n")).filter(s->s.length()>0).collect(Collectors.toList());
    }

    public static void main(String ...args) {
        String[] source1 = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        String[] source2 = {"a/*comment", "line", "more_comment*/b"};
        String[] source3 = {"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"};
        LC_722 test = new LC_722();
        test.removeComments(source3).stream().forEach(System.out::println);
    }
}
