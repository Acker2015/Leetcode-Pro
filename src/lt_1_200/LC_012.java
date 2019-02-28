package lt_1_200;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

	Symbol       Value
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000
	For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
	
	Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
	
	I can be placed before V (5) and X (10) to make 4 and 9. 
	X can be placed before L (50) and C (100) to make 40 and 90. 
	C can be placed before D (500) and M (1000) to make 400 and 900.
	Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
	
	Example 1:
	
	Input: 3
	Output: "III"
	Example 2:
	
	Input: 4
	Output: "IV"
	Example 3:
	
	Input: 9
	Output: "IX"
	Example 4:
	
	Input: 58
	Output: "LVIII"
	Explanation: L = 50, V = 5, III = 3.
	Example 5:
	
	Input: 1994
	Output: "MCMXCIV"
	Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * @author Acker
 *
 */
public class LC_012 {
	public String intToRoman(int num) {
		String[] m = {"", "M", "MM", "MMM"};
		String[] c = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		return m[num/1000]+c[(num%1000)/100] + x[(num%100)/10] + i[num%10];
	}
	
	/**
	 * 重复字符串
	 * @param str
	 * @param n
	 * @return
	 */
	public String repeat(String str, int n) {
		StringBuilder stringBuilder = new StringBuilder();
		IntStream.range(0,  n).forEach(t -> stringBuilder.append(str));
		return stringBuilder.toString();
	}
	/**
	 * 获取除数的级别，百位 十位 个位
	 * @param divide
	 * @return
	 */
	public int level(int divide) {
		return divide>=100 ? 100 : (divide>=10 ? 10 : 1);
	}
	/**
	 * 从千位到百位再到十位个位依次获取roman子串
	 * 对于每个余数
	 * 1.除以相对应的级别, 如果结果为4或者9，那么可以直接从map获取特殊处理结果
	 * 2.除以对应的symbols，重复symbol对应的结果
	 * 拼接为结果roman串
	 * @param num
	 * @return
	 */
	public String intToRoman1(int num) {
		StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");
        int divide = 1000;
        int r = num / divide;
        stringBuilder.append(repeat(map.get(divide), r));
        int start = 0;
        int remain = num % divide;
        while (remain > 0) {
        		divide = divide / (start++ % 2 == 0 ? 2 : 5);
        		int levelDivide = level(divide); // 除数的级别 1、10、100 用于判断4,9
        		r = remain / levelDivide;
        		if (r == 4 || r == 9) {
        			stringBuilder.append(map.get(r * level(divide)));
        			remain = remain % levelDivide;
        			divide = levelDivide;
        			start = 0;
        		} else {
        			r = remain / divide;
        			stringBuilder.append(repeat(map.get(divide), r));
        			remain = remain % divide;
        		}
        }
        return stringBuilder.toString();
    }
	
	
	public static void main(String[] args) {
		LC_012 lc_012 = new LC_012();
		System.out.println(lc_012.intToRoman(1994));

	}

}
