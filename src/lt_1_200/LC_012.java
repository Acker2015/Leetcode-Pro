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
	public String intToRoman0(int num) {
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
	 * 从低位到高位不断取余remain，根据位置层级level(1,10,100,1000)
	 * remain=4 or remain=9, 直接map取数
	 * remain<4, 那么取对应的单元数据，连续remain次
	 * remain>=5, 那么取5*level对应的数以及(remain-5)次对应的单元数
	 *
	 * 其中单元数为map.get(level)
	 * @param num
	 * @return
	 */
    public String intToRoman(int num) {
		StringBuilder builder = new StringBuilder();
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
		int level = 1;
		while (num > 0) {
			int remain = num % 10;
			if (remain == 4 || remain == 9) {
				builder.insert(0, map.get(level*remain));
			} else if (remain < 5) {
				builder.insert(0, repeat(map.get(level), remain));
			} else {
				String tmp = map.get(5*level) + repeat(map.get(level), remain-5);
				builder.insert(0, tmp);
			}
			num /= 10;
			level *= 10;
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		LC_012 lc_012 = new LC_012();
		System.out.println(lc_012.intToRoman(1994));

	}

}
