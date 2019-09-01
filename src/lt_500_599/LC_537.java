package lt_500_599;

/**
 * [537] Complex Number Multiplication
 */
public class LC_537 {

    static class Solution1 {
        private static class Expression {
            int a;
            int b;
            public Expression(int a, int b) {
                this.a = a;
                this.b = b;
            }
            public String cal(Expression e) {
                int left = this.a * e.a - this.b*e.b;
                int right = this.a*e.b + this.b*e.a;
                return String.valueOf(left)+"+"+String.valueOf(right)+"i";
            }

        }
        private boolean isSymbol(char c) {
            return c == '+' || c == '-';
        }
        private Expression convert(String s) {
            int start = 0;
            int a = 0, b = 0;
            if (isSymbol(s.charAt(start))) {
                start++;
            }
            while (start < s.length() && !isSymbol(s.charAt(start))) {
                start++;
            }
            a = Integer.parseInt(s.substring(0, start));
            if (start < s.length()) {
                boolean flag = s.charAt(start++) == '+';
                b = Integer.parseInt(s.substring(start, s.length()-1));
                if (!flag) {
                    b = -b;
                }
            }
            return new Expression(a,b);
        }
        public String complexNumberMultiply(String a, String b) {
            if (a == null || a.length() <= 0) return "0";
            if (b == null || b.length() <= 0) return "0";
            Expression one = convert(a);
            Expression two = convert(b);
            return one.cal(two);
        }
    }

    static class Solution {
        public String complexNumberMultiply(String a, String b) {
            if (a == null || a.length() <= 0) return "0";
            if (b == null || b.length() <= 0) return "0";
            int m = 0, n = 0, x = 0, y = 0;
            int idx1 = a.indexOf("+"), idx2 = b.indexOf("+");
            m = Integer.parseInt(a.substring(0, idx1));
            n = Integer.parseInt(a.substring(idx1+1, a.length()-1));
            x = Integer.parseInt(b.substring(0, idx2));
            y = Integer.parseInt(b.substring(idx2+1, b.length()-1));
            return String.valueOf(m*x-n*y)+"+"+String.valueOf(m*y+n*x)+"i";
        }
    }

}
