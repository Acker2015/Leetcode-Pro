package lt_1_200;
/**
 * https://leetcode.com/problems/zigzag-conversion/submissions/
 * find the regular pattern
 * row		gap1			gap2	
 * 0  		2(row-1)		2 * 0 		（gap2=0需要跳过）
 * 1			2*(row-2)	2 * 1
 * 2			2*(row-3)	2 * 2
 * ...
 * row-1		0			2*(row-1) 	（gap1=0需要跳过）
 *
 * 
 * @author Acker
 *
 */
public class LC_006 {
	
	public String convert(String s, int numRows) {
        if (s.length() <= 1 || numRows <= 1) return s;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numRows && i < s.length(); ++i) {
        		int gap1 = 2 * (numRows - i - 1);
        		int gap2 = 2 * i;
        		
        		int left = i;
        		stringBuilder.append(s.charAt(left));
        		while (left < s.length()) {
        			// （gap1=0需要跳过）
        			if (i < numRows-1) {
        				left += gap1;
        				if (left < s.length()) {
        					stringBuilder.append(s.charAt(left));
        				}
        			}
        			// (gap2=0需要跳过）
        			if (i > 0) {
        				left += gap2;
        				if (left < s.length()) {
        					stringBuilder.append(s.charAt(left));
        				}
        			}
        		}
        }
        return stringBuilder.toString();
    }

	public static void main(String[] args) {
		LC_006 lc_006 = new LC_006();
		System.out.println(lc_006.convert("PAYPALISHIRING", 3));
		System.out.println(lc_006.convert("PAYPALISHIRING", 4));
	}
}
