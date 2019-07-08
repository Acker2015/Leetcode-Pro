package lt_300_399;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * [301] Remove Invalid Parentheses
 * backtracking
 */
public class LC_301 {
    /**
     * 先进行可用性测试，记录非法左右括号的个数(后边需要检测删除的个数),返回合法的最大字符长度
     */
    private int validForMaxLength(char[] chs) {
        int leftNum = 0;
        int invalidRightNum = 0;
        for (char c: chs) {
            if (c == '(') {
                leftNum+=1;
            } else if (c == ')') {
                if (leftNum > 0) {
                    leftNum--;
                } else {
                    invalidRightNum++;
                }
            }
        }
        return chs.length - leftNum - invalidRightNum;
    }

    /**
     * idx表示索引位置，leftNoMatchNum表示遍历到idx未匹配右括号的左括号个数，maxLen表示最大合法括号对的长度，builder存放当前结果
     * 1. 合法Parentheses的表现为 idx遍历结束 and builder中的字符长度等于maxLen
     * 2. 开头剪枝
     *      2.1 leftNoMatchNum<0, 表示出现了未匹配左括号的右括号,此为不合法的情况
     *      2.2 builder.length()+(chs.length - idx) < maxLen， 表示后续得到的builder中字符长度肯定会小于maxLen
     * 3. 对于回溯过程中出现的'(',')'有两种选择
     *      3.1 选择，根据括号类型对leftNoMatchNum进行增减
     *      3.2 丢弃，尝试下一个索引
     */
    private void backTracking(char[] chs, int idx, int leftNoMatchNum, StringBuilder builder, int maxLen, Set<String> retList) {
        if (leftNoMatchNum < 0 || builder.length()+(chs.length - idx) < maxLen) return;
        if (idx == chs.length) {
            // builder.length() == maxLen
            if (leftNoMatchNum == 0) {
                retList.add(builder.toString());
            }
            return;
        }
        if (chs[idx] != '(' && chs[idx] != ')') {
            builder.append(chs[idx]);
            backTracking(chs, idx+1, leftNoMatchNum, builder, maxLen, retList);
            builder.deleteCharAt(builder.length()-1);
        } else {
            builder.append(chs[idx]);
            backTracking(chs, idx+1, leftNoMatchNum+(chs[idx]=='('?1:-1), builder, maxLen, retList);
            builder.deleteCharAt(builder.length()-1);

            backTracking(chs, idx+1, leftNoMatchNum, builder, maxLen, retList);
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        Set<String> retSet = new HashSet<>();
        char[] chs = s.toCharArray();
        int maxLen = validForMaxLength(chs);
        backTracking(chs, 0, 0, new StringBuilder(), maxLen, retSet);
        if (retSet.size() == 0) {
            retSet.add("");
        }
        return new ArrayList<>(retSet);
    }
}
