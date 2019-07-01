package basic_data_structure.Recursive;

import java.util.Map;

/**
 * 给定一个布尔表达式，由0、1、&、|和&等符号组成，以及一个想要的布尔结果result，
 * 实现一个函数，算出有几种括号的放法可使得该表达式得出result值。
 */
public class BoolWays {
    public int f(String exp, boolean result, int s, int e, Map<String, Integer> map) {
        String key = s+ "_" + e +result;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (s == e) {
            if (exp.charAt(s)=='1' && result) {
                return 1;
            }
            if (exp.charAt(s)=='0' && !result) {
                return 1;
            }
            return 0;
        }
        int c = 0;
        if (result) {
            for (int i = s+1; i <= e; i+=2) {
                char op = exp.charAt(i);
                if (op == '&') {
                    c += f(exp, true, s, i-1, map) * f(exp, true,  i+1, e,map);
                } else if (op == '|') {
                    int t1 = f(exp, true, s, i-1,map);
                    int t2 = f(exp, false, s, i-1,map);
                    int t3 = f(exp, true, i+1,e,map);
                    int t4 = f(exp, false, i+1, e,map);
                    c = c + t1*t3 +t1*t4 + t2*t3;
                } else if (op == '^') {
                    c += f(exp, true, s, i-1, map) * f(exp, false, i+1, e, map);
                    c += f(exp, false, s, i-1, map) * f(exp, true, i+1, e, map);
                }
            }
        } else {
            for (int i = s+1; i <= e; i+=2) {
                char op = exp.charAt(i);
                if (op == '&') {
                    c += f(exp, false, s, i-1, map) * f(exp, false,  i+1, e, map);
                    c += f(exp, false, s, i-1, map) * f(exp, true,  i+1, e, map);
                    c += f(exp, true, s, i-1, map) * f(exp, false,  i+1, e, map);
                } else if (op == '|') {
                    c += f(exp, false, s, i-1, map) * f(exp, false,  i+1, e, map);
                } else if (op == '^') {
                    c += f(exp, true, s, i-1, map) * f(exp, true, i+1, e, map);
                    c += f(exp, false, s, i-1, map) * f(exp, false, i+1, e, map);
                }
            }
        }
        map.put(key, c);
        return c;
    }

    /**
     * 要知道一个表达式有多少种括号计算的解法，完全可以借助total(exp)-f(exp=true)=f(exp=false)
     * 其中total(exp)为卡特兰数，若n为操作符的个数 total(exp) =  (2n)!/((n+1)!n!) = C(2n, n)/(n+1) = C(2n, n)-C(2n, n+1)
     * C(n)表示卡特兰数
     * C(n) = (2n)!/((n+1)!n!) = C(2n, n)/(n+1) = C(2n, n)-C(2n, n+1)
     * C(0) = 1
     * C(n+1) = C(i)*C(n-i), i >= 0 && i <= n
     */
}
