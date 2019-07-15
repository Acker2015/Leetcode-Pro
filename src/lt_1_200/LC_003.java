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

 *
 */
public class LC_003 {
	/**
	 * 解法一：使用map记录每一个字符上一次出现的位置
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring1(String s) {
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


	/**
	 * 解法二： 滑动窗口
	 * slide window
	 */
	public int lengthOfLongestSubstring2(String s) {
		int end = 0, start = 0, count = 0, maxLen = 0;
		Map<Character, Integer> map = new HashMap<>();
		while (end < s.length()) {
			char cur = s.charAt(end);
			map.put(cur, map.getOrDefault(cur, 0) + 1);
			if (map.get(cur) > 1) {
				count++;
			}
			while (count > 0) {
				char prev = s.charAt(start);
				if (map.get(prev) > 1) {
					count--;
				}
				map.put(prev, map.get(prev)-1);
				start++;
			}
			end++;
			maxLen = Math.max(maxLen, end-start);
		}
		return maxLen;
	}
	
	public static void main(String[] args) {
		LC_003 lc_003 = new LC_003();
		System.out.println(lc_003.lengthOfLongestSubstring1("abcabcbb"));
		System.out.println(lc_003.lengthOfLongestSubstring1("aab"));
		System.out.println(lc_003.lengthOfLongestSubstring1("abba"));
		System.out.println(lc_003.lengthOfLongestSubstring1("cdd"));
		System.out.println(lc_003.lengthOfLongestSubstring1("abcb"));
	}

}
