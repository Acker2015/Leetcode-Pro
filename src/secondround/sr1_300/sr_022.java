package secondround.sr1_300;


import java.util.ArrayList;
import java.util.List;

public class sr_022 {
    private void generate(int n, int left, StringBuilder builder, List<String> list) {
        if (builder.length()+left > 2*n) return;
        if (left == 0 && builder.length() == 2*n) {
            list.add(builder.toString());
        } else {
            // select 1
            builder.append('(');
            generate(n, left+1, builder, list);
            builder.delete(builder.length()-1, builder.length());
            // select 2
            if (left > 0) {
                builder.append(')');
                generate(n, left-1, builder, list);
                builder.delete(builder.length()-1, builder.length());
            }
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> parenthese = new ArrayList<>();
        generate(n, 0, new StringBuilder(), parenthese);
        return parenthese;
    }

    public static void main(String[] args) {
        int n = 3;
        new sr_022().generateParenthesis(n);
    }
}
