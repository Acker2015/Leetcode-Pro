package secondround.sr1_300;


public class sr_006 {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= 2) return s;
        char[] chs = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; ++i) {
            int idx = i;
            while (idx < chs.length) {
                builder.append(chs[idx]);
                if (i > 0 && i < numRows-1 && idx + (numRows-i-1)*2 < chs.length) {
                    builder.append(chs[idx + (numRows-i-1)*2]);
                }
                idx += 2*numRows-2;
            }
        }
        return builder.toString();
    }

    public static void main(String ...args) {
        String str = "PAYPALISHIRING";
        System.out.println(new sr_006().convert(str, 3));
    }
}
