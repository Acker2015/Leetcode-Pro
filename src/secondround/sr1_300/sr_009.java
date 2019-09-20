package secondround.sr1_300;

public class sr_009 {
    public boolean isPalindrome1(int x) {
        if (x < 0) return false;
        else if (x < 10) return true;
        int tmp = x;
        long y = 0;
        while (x != 0) {
            y = y*10 + x%10;
            x/=10;
        }
        return tmp==y;
    }

    /**
     * 不用考虑溢出 - 直接转化一半
     * 但是需要考虑10，100这种特殊情况
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x!=0&&x%10==0)) return false;
        else if (x < 10) return true;
        int y = 0;
        while (x > y) {
            y = y * 10 + x % 10;
            x /= 10;
        }
        return x==y || x == y/10;
    }

    public static void main(String ...args) {
        int x = 121;
        System.out.println(new sr_009().isPalindrome(x));
    }
}
