package lt_500_599;


import java.util.Stack;

/**
 * [591] Tag Validator
 * html snippet
 *
 * "<DIV>This is the first line <![CDATA[<div>]]></DIV>"
 *
 * <TAG_NAME>TAG_CONTENT</TAG_NAME>
 * 1. 必须被一组标签前后包围，并且标签可以有嵌套
 * 2. TAG_NAME必须由大写字母组成，长度在1-9之间
 * 3. TAG_CONTENT可以包含其他的tag组合、非'<'字符、cdata （<![CDATA[CDATA_CONTENT]]>）
 * 4. CDATA中的CDATA_CONTENT可以是任意的text文本字符，不做限制
 */
public class LC_591 {
    /**
     * <![CDATA[
     * <Tag>
     * </Tag>
     */
    private final static String CD_START = "<![CDATA[";
    private final static String CD_END = "]]>";
    private boolean verfify(Stack<String> stack, String s, boolean isEnd) {
        int len = s.length();
        if (len < 1 || len > 9) return false;
        for (int i = 0; i < len; ++i) {
            if (!Character.isUpperCase(s.charAt(i))) {
                return false;
            }
        }
        if (isEnd) {
            return !stack.isEmpty() && stack.pop().equals(s);
        } else {
            stack.push(s);
        }
        return true;
    }
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        int len = code.length();
        int i = 0;
        boolean wrapByTag = false;
        while (i < len) {
            char c = code.charAt(i);
            if (c == '<') {
                String subCode = code.substring(i);
                // 判断是否是CDATA
                if (subCode.startsWith(CD_START)) {
                    int endIdx = code.indexOf(CD_END, i+CD_START.length());
                    if (endIdx < 0) {
                        return false;
                    } else {
                        i = endIdx + CD_END.length();
                    }
                } else {
                    // 判断标签
                    int idx = code.indexOf(">", i);
                    if (idx < 0) return false;
                    // 判断是start tag还是close tag
                    boolean isEnd = false;
                    if (code.charAt(i+1) == '/') {
                        isEnd = true;
                    }
                    String tag = isEnd ? code.substring(i+2, idx) : code.substring(i+1, idx);
                    if (!verfify(stack, tag, isEnd)) {
                        return false;
                    }
                    // 是否被标签包围的标记
                    if (i == 0) {
                        wrapByTag = true;
                    }
                    i = idx + 1;
                    // 不是由一段tag包围 ex: <A></A><B></B>
                    if (stack.isEmpty() && i < len) {
                        return false;
                    }
                }
            } else {
                i++;
            }
        }
        return stack.isEmpty() && wrapByTag;
    }
}
