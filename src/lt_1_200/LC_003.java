package lt_1_200;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 *
Example 1:
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

遍历字符串，遇到已经出现过的字符就取上一个字符到现在位置的字符串长。
注意
1. 需要使用start标记上一次出现重复字符的地方，防止重复从头计算
2. 出现重复字符，需要更新start标记

two pointers

 * @author Acker
 *
 */
public class LC_003 {
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() <= 0) {
			return 0;
		}
		Map<Character, Integer> locMap = new HashMap<>();
		int index=0, start=0, maxLen=0;
		while(index < s.length()) {
			// make sure the area at right of start
			if (locMap.containsKey(s.charAt(index)) && locMap.get(s.charAt(index)) >= start) {
				maxLen = Math.max(maxLen, index - start);
				start = locMap.get(s.charAt(index)) + 1;
			}
			locMap.put(s.charAt(index), index++);
		}
		return Math.max(maxLen, s.length() - start);
    }
	
	public static void main(String[] args) {
		LC_003 lc_003 = new LC_003();
		System.out.println(lc_003.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(lc_003.lengthOfLongestSubstring("aab"));
		System.out.println(lc_003.lengthOfLongestSubstring("abba"));
		System.out.println(lc_003.lengthOfLongestSubstring("cdd"));
		System.out.println(lc_003.lengthOfLongestSubstring("abcb"));
	}

}
