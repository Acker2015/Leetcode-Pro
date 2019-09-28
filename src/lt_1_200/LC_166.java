package lt_1_200;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC_166 {
    /**
     * Math
     * 注意点
     * 1. 边界情况 使用long来进行除法运算，防止用例-1,-2147483648的出现
     * 2. 如何判断循环点，这里不能只判断小数点后的每次除法余数来判断，而应该结合该轮(被除数+余数)来进行判断，这时候才是循环点出现的时机, 如用例1, 333
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0 || denominator == 0) return "0";
        StringBuilder builder = new StringBuilder();
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) {
            builder.append("-");
        }
        long dividend = Math.abs((long)numerator);
        long divisor = Math.abs((long)denominator);
        builder.append(dividend/divisor);
        dividend = dividend%divisor;
        if (dividend == 0) return builder.toString();
        builder.append(".");

        List<Pair<Long, Long>> remainList = new ArrayList<>();
        Set<String> remainSet = new HashSet<>();
        String circleKey = null;

        while (dividend != 0) {
            dividend *= 10;
            Pair<Long, Long> pair = new Pair<>(dividend/divisor, dividend);
            String key = String.valueOf(pair.getKey())+"_"+String.valueOf(pair.getValue());
            dividend %= divisor;
            if (remainSet.contains(key)) {
                circleKey = key;
                break;
            }
            remainList.add(pair);
            remainSet.add(key);
        }
        for (Pair<Long, Long> pair: remainList) {
            String key = String.valueOf(pair.getKey())+"_"+String.valueOf(pair.getValue());
            if (key.equals(circleKey)) {
                builder.append("(");
            }
            builder.append(pair.getKey());
        }
        if (circleKey != null) {
            builder.append(")");
        }
        return builder.toString();
    }


    /**
     * numerator=0, denominator=0
     * numerator=1, denominator=2
     * numerator=2, denominator=1
     * numerator=2, denominator=-1
     * numerator=-2, denominator=-1
     *
     * numerator=5, denominator=6
     * numerator=1, denominator=333
     *
     * 4, 9 -> "0.(4)"
     * 4, 333 -> "0.(012)"
     * 1, 17 -> "0.(0588235294117647)"
     * 1, 333 -> "0.(003)"
     * -1,-2147483648 -> "0.0000000004656612873077392578125"
     * @param args
     */
    public static void main(String ...args) {
        LC_166 lc_166 = new LC_166();
        System.out.println(lc_166.fractionToDecimal(1, 333));
    }
}
