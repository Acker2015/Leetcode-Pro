package lt_1_200;

public class LC_038 {
	/**
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     */
    public String countAndSay(int n) {
        if (n <= 0) return "";
        String ret = "1";
        int seq = 1;
        while (seq < n) {
            String tmp = "";
            int len = ret.length();
            for (int i = 0; i < len;) {
                char c = ret.charAt(i);
                int j = i + 1;
                while (j < len && ret.charAt(j)==c) j++;
                tmp = tmp + String.valueOf(j-i) + c;
                i = j;
            }
            ret = tmp;
            seq++;
        }
        return ret;
    }
	
	public static void main(String[] args) {
		LC_038 lc_038 = new LC_038();
		System.out.println(lc_038.countAndSay(6));

	}

}
