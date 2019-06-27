package lt_200_299;


import java.util.ArrayList;
import java.util.List;

/**
 * [282] Expression Add Operators
 */
public class LC_282 {
    /**
     *
     * 很单纯的DFS
     *
     * 将+-的结果和乘法分离，因为优先级不同
     * 如 1 + 2 + 3 * 4 + 5 是3*4先计算
     * 可以分别记录加减法的结果和乘的结果
     * 如 1+2+3*4, 分别记录3、12
     * 遇到1+2+3*4+5 发现最后一个是加法，那么记录 15(3+12)、5
     *
     * 单独记录后一个数，防止在后续遇到乘法，提高其参与运算的优先级
     */
    public List<String> addOperators(String num, int target) {
        List<String> retList = new ArrayList<>();
        if (num.length() <= 0) return retList;
        char[] numArr = num.toCharArray();
        long ans = 0L;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numArr.length; ++i) {
            if (i > 0 && numArr[0] == '0') break;
            ans = ans * 10 + (numArr[i]-'0');
            dfs(retList, String.valueOf(numArr, 0, i+1), numArr, i+1, 0, ans, target);
        }
        return retList;
    }
    private void dfs(List<String> retList, String exp, char[] numArr, int idx, long preRet, long lastMulti, int target) {
        if (idx == numArr.length) {
            if (target == preRet+lastMulti) {
                retList.add(exp);
            }
            return;
        }
        long ans = 0L;
        for (int i = idx; i < numArr.length; ++i) {
            if (numArr[idx] == '0' && i > idx) break;
            ans = ans*10 + (numArr[i]-'0');
            // +
            dfs(retList, exp+'+'+ans, numArr, i+1, preRet+lastMulti, ans, target);
            // - 看成是加负数
            dfs(retList, exp+'-'+ans, numArr, i+1, preRet+lastMulti, -ans, target);
            // 乘法，lastMulti*ans意味着提高优先级
            dfs(retList, exp+'*'+ans, numArr, i+1, preRet, lastMulti*ans, target);
        }
    }
}
