package lt_200_299;


import java.util.Stack;

public class LC_227 {
    /**
     * [227] Basic Calculator II
     * 优先计算* /,其他的都放到栈中 最后计算
     * @param s
     * @return
     */
    public int calculate(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        if (len <= 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; ++i) {
            if (Character.isDigit(chs[i])) {
                num = num*10 + (chs[i]-'0');
            }
            if (!Character.isDigit(chs[i])&&chs[i]!=' ' || i == len-1) {
                if ('+' == sign) {
                    stack.push(num);
                } else if ('-' == sign) {
                    stack.push(-num);
                } else if ('*' == sign) {
                    stack.push(stack.pop()*num);
                } else {
                    stack.push(stack.pop()/num);
                }
                sign = chs[i];
                num = 0;
            }
        }
        int ret = 0;
        while (!stack.isEmpty()) ret += stack.pop();
        return ret;
    }


    public static void main(String ...args) {
        LC_227 lc_227 = new LC_227();
        System.out.println(lc_227.calculate("1*2-3/4+5*6-7*8+9/10"));
    }
}
