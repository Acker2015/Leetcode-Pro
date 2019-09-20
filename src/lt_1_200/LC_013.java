package lt_1_200;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Symbol       Value
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000
 * @author Acker
 *
 */
public class LC_013 {
	/**
	 * 遍历检查
	 * @param s
	 * @return
	 */
	public int romanToInt2(String s) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		int sum = 0;
		Character pre = s.charAt(0);
		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length; ++i) {
			sum += map.get(chs[i]);
			if (map.get(pre) < map.get(chs[i])) {
				sum -= 2 * map.get(pre);
			}
			pre = chs[i];
		}
		return sum;
	}
	/**
	 * count every Symbol and add its value to the sum, and minus the extra part of special cases.
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		int sum = 0;
		if (s.contains("IV")) sum -= 2;
		if (s.contains("IX")) sum -= 2;
		if (s.contains("XL")) sum -= 20;
		if (s.contains("XC")) sum -= 20;
		if (s.contains("CD")) sum -= 200;
		if (s.contains("CM")) sum -= 200;
		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length; ++i) {
			char c = chs[i];
			if (c == 'I') {
				sum += 1;
			} else if (c == 'V') {
				sum += 5;
			} else if (c == 'X') {
				sum += 10;
			} else if (c == 'L') {
				sum += 50;
			} else if (c == 'C') {
				sum += 100;
			} else if (c == 'D') {
				sum += 500;
			} else if (c == 'M') {
				sum += 1000;
			}
		}
		return sum;
    }
	public static void main(String[] args) {
		LC_013 lc = new LC_013();
		System.out.println(lc.romanToInt("III"));
		System.out.println(lc.romanToInt("IV"));
		System.out.println(lc.romanToInt("IX"));
		System.out.println(lc.romanToInt("LVIII"));
		System.out.println(lc.romanToInt2("MCMXCIV"));
	}

}
