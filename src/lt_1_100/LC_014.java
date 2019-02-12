package lt_1_100;

import java.util.Arrays;

public class LC_014 {
	/**
	 * 直接对字符串数组进行字典序排序
	 * 只需要比较排序后数组第一个串和最后一个串的长度即可
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        
        if (strs!= null && strs.length > 0){
        
            Arrays.sort(strs);
            
            char [] a = strs[0].toCharArray();
            char [] b = strs[strs.length-1].toCharArray();
            
            for (int i = 0; i < a.length; i ++){
                if (b.length > i && b[i] == a[i]){
                    result.append(b[i]);
                }
                else {
                    return result.toString();
                }
            }
        }
        return result.toString();
    }
	/**
	 * 遍历数组，获取最长公共前缀的最大索引位置
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix1(String[] strs) {
		if (strs.length <= 0) return "";
        int minLen = strs[0].length();
        for (String str: strs) {
            minLen = Math.min(minLen, str.length());
        }
        int maxIndex = -1;
        for (int i = 0; i < minLen; ++i) {
            char c = strs[0].charAt(i);
            boolean flag = true;
            for (int j = 1; j < strs.length; ++j) {
                if (c != strs[j].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
            maxIndex = i;
        }
        return maxIndex >= 0 ? strs[0].substring(0, maxIndex+1) : "";
	}
        
	public static void main(String[] args) {
		
	}

}
