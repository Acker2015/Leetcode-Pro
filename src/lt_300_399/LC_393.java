package lt_300_399;


public class LC_393 {
    public int findPrefixNum(int d) {
        int num = 0;
        while ((d&0x80)!=0) {
            num++;
            d<<=1;
        }
        return num;
    }
    public boolean validUtf8(int[] data) {
        if (data.length <= 0) return true;
        int i = 0;
        while (i < data.length) {
            int num = findPrefixNum(data[i]);
            i++;
            // 最长4个bit，那么num最大就是4，11110000 10..., 10..., 10...
            if (num==1 || num > 4) {
                return false;
            }
            if (num > 1) {
                while (--num > 0) {
                    if (i >= data.length || findPrefixNum(data[i])!=1) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }
    //11000101 10000010 00000001
    public static void main(String ...args) {
        int[] nums = {250,145,145,145,145};
        LC_393 lc_393 = new LC_393();
        System.out.println(lc_393.validUtf8(nums));
    }
}
