package lt_1_200;

public class LC_066 {
	public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        while (i >= 0) {
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            } else {
                digits[i]=0;
            }
            i--;
        }
        // default 0
        int[] newDigits = new int[digits.length+1];
        newDigits[0]=1;
        return newDigits;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
