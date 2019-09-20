package secondround.sr1_300;


import java.util.ArrayList;
import java.util.List;

/**
 * 回溯法解决
 */
public class sr_017 {
    private static final String[] letters = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public void backtracking(int idx, int[] digitArr, StringBuilder builder, List<String> combinations) {
        if (idx >= digitArr.length) {
            combinations.add(builder.toString());
            return;
        }
        String letter = letters[digitArr[idx]];
        if (letter.length() > 0) {
            for (int i = 0; i < letter.length(); ++i) {
                builder.append(letter.charAt(i));
                backtracking(idx+1, digitArr, builder, combinations);
                builder.deleteCharAt(builder.length()-1);
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        char[] chs = digits.toCharArray();
        int[] digitArr = new int[chs.length];
        if (chs.length <= 0) return combinations;
        for (int i = 0; i < chs.length; ++i) {
            digitArr[i] = chs[i]-'0';
        }

        backtracking(0, digitArr, new StringBuilder(), combinations);
        return combinations;
    }

    public static void main(String[] args) {
        String str = "23";
        new sr_017().letterCombinations(str).forEach(System.out::println);
    }
}
