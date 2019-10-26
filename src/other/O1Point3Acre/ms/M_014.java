package other.O1Point3Acre.ms;

import java.util.HashMap;
import java.util.Map;

/**
 * 中文数字转换成阿拉伯数字
 * 万和亿单独处理
 *
 * a亿b  拆成 计算(a)*1亿+计算(b)
 * a万b  拆成 计算(a)*1万+计算(b)
 *
 * 1.合法性检查 (亿、万、千、百 - 零、一、二、三、四、伍、六、七、八、九、十)
 *      1.1 输入字符只能在上述列表中
 *      1.2 输入数字顺序 亿 -> 万 -> 千 -> 百 -> 十
 *
 * 2. overflow - long
 * 3. 一兆是一万亿
 * 4. int整数最大值为 -2147483648 和2147483647 大概是2.14*10^9
 * 5. 一亿 = 100000000 = 1e8
 * 6 一千亿 = 1000*一亿 = 1e11
 *
 * 需确认：
 * 50010 读 五万零十 还是读 五万零一十
 * 5010  读 五千零十 还是读 五千零一十
 *
 *
 * 你不能依靠client输入valid的string，如果是三百二十千八百二十八这个就不行，有可能是负数。
 数量是1兆到-1兆。我最开始以为不算很难，但是用stack思想做了后漏洞百出，怎么判定是invalid？
 十一这个testcase你怎么办？
 十是算作单位还是算作一个数？
 一到九和其他单位是不是要分开考虑？
 五万零三百二十这个test case怎么办？

 还有一点我比较无语，他说int overflow怎么办？
 我用的是python，我记得是不用管的，不会有overflow的问题，但是强行让我管...我只能瞎说了。反正后面头都要炸了，说得也比较乱，强行扯了一波，其实面完就知道我要跪了。

 把一串string转化成int。具体是三百二十八万三千二百八十转化成数


 test case:
 1. 十一
 2. 二百零一
 3. 二百一
 4. 两百一十六
 5. 十六
 4. 五万零三百二十

 一系列非法描述
 三百二十千八百二十八
 */
public class M_014 {
    private static final char[] digits = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十'};
    private static final char ZERO = '零';
    private static final char TEN = '十';

    private static Map<Character, Integer> digitMap;
    private static Map<Character, Long> symbolMap;
    static {
        digitMap = new HashMap<>();
        symbolMap = new HashMap<>();
        for (int i = 0; i < digits.length; ++i) {
            digitMap.put(digits[i], i);
        }
        symbolMap.put('亿', 100000000L);
        symbolMap.put('万', 10000L);
        symbolMap.put('千', 1000L);
        symbolMap.put('百', 100L);
        symbolMap.put('十', 10L);
    }
    /**
     * 转换为单个数字
     * @param c
     * @return
     */
    private int singleDigit(char c) {
        int ans = digitMap.getOrDefault(c, -1);
        return ans <= 0 ? -1: ans; // 忽略掉零
    }

    /**
     * 对于千的数字进行判定
     * 1. 保证同一个symbol不会出现多次 (比如万只能出现一次，千也只能出现一次)
     * 2. 对于中间零位的判断-这里是symbol出现断层会用一个零位填充
     * 3. symbol之前的字符个数大于1
     * 4. 特殊判断，对于十位并且之前没有其他symbol，可使用"十" "十一" "十九"来表示
     *
     * 5. 处理个位数
     * @param s
     * @param lastFlag  上一层的symbol
     * @return
     */
    private long convertPart(String s, Character lastFlag) {
        if (s == null || s.length() <= 0) {
            return lastFlag==null ? -1: 0; //如果上一层为空，那么说明输入非法
        }
        char[] symbols = {'千', '百', '十'};
        for (char symbol: symbols) {
            int idx = s.indexOf(symbol);
            if (idx < 0) continue;
            // 1. symbol最多出现一次
            if (s.indexOf(symbol, idx+1) >= 0) return -1;
            // 2. 判断断层0
            if (lastFlag!=null && symbolMap.get(lastFlag)/symbolMap.get(symbol) > 10) {
                if (s.charAt(0) == '零') {
                    s = s.substring(1); // 去掉前置零
                    idx--;
                } else {
                    return -1;
                }
            }
            // symbol前方位数大于1非法
            if (idx > 2) return -1;
            int left = 1;
            // 4. 只有处于十位并且lastFlag为null，才有可能出现 "十"、"十五"、"十九"这样
            // 特殊判断: 一十五、一十九、一十这种就是非法输入
            if ((lastFlag == null && s.startsWith("一十")) || (lastFlag!=null && s.startsWith("十"))) {
                return -1;
            }
            if (idx == 1) {
                left = singleDigit(s.charAt(0)); // 如果前边有一位
            }
            long right = convertPart(s.substring(idx+1), symbol);
            if (left < 0 || right < 0) return -1;
            return left*symbolMap.get(symbol)+right;
        }
        // 个位处理
        int startIdx = 0;
        if (lastFlag != null && symbolMap.get(lastFlag) > 10) {
            startIdx++;
        }
        return s.length()-startIdx!=1 ? -1 : singleDigit(s.charAt(startIdx));
    }

    /**
     * a亿b  拆成 计算(a)*1亿+计算(b)
     * a万b  拆成 计算(a)*1万+计算(b)
     *
     * 处理亿、万级别
     * 1. 处理亿，只存在一个亿标志
     * 2. 处理万，只存在一个万标志（注意如果万的单位前边没有千的存在，需要插入一位零值）- 一亿零五百万(105000000)
     * 3. 处理余下位数，如果上层标志位'亿' 或者 上层标志为'万'且本层没有'千'位，那么将会需要插入一位零值 - 一亿零五千(100005000)、五万零七百八十(50780)
     *
     * @param s
     * @param lastFlag
     * @return
     */
    private long convert(String s, Character lastFlag) {
        char[] symbols = {'亿', '万'};
        if (s == null || s.length() <= 0) {
            return lastFlag != null ? 0 : -1;
        }
        for (char symbol: symbols) {
            int idx = s.indexOf(symbol);
            if (idx < 0) continue;
            if (s.indexOf(symbol, idx+1) >= 0) return -1;
            // 万之前出现没有出现千才有可能会有空0
            int thIdx = s.indexOf('千');
            if (lastFlag != null && symbol=='万' && (thIdx < 0 || thIdx > idx)) {
                if (s.charAt(0) == '零') {
                    s = s.substring(1);
                    idx--;
                } else {
                    return -1;
                }
            }
            long left = convertPart(s.substring(0, idx), null);
            long right = convert(s.substring(idx+1), symbol);
            if (left < 0 || right < 0) return -1;
            return left*symbolMap.get(symbol)+right;
        }
        // 万余下的数
        int startIdx = 0;
        if (lastFlag != null && (lastFlag == '亿' || (lastFlag=='万' && s.indexOf('千') < 0))) {
            if (s.charAt(startIdx) == '零') {
                startIdx++;
            } else {
                return -1;
            }
        }
        return convertPart(s.substring(startIdx), null);
    }

    public long solve(String s) {
        if (s == null || s.length() <= 0) {
            return -1;
        }
        if (s.equals("零")) {
            return 0;
        }
        return convert(s, null);
    }
    public static void main(String[] args) {
//        String s = "一二三四";
//        System.out.println(s.charAt(2));

        M_014 m_014 = new M_014();
        /**
         * valid：
         * 一
         * 十
         * 二十
         * 两百
         * 两千
         * 两千两百
         * 两千零五
         * 两千零一十
         * 两千零二十五
         * 两千两百
         * 两千两百零五
         * 两千两百一十
         * 两千两百一十一
         * 两千两百二十五
         *
         * invalid
         * 零一
         * 数据
         * 一千一十
         * 一千零十
         * 一千零一百
         * 一千零零一
         * 一千五百数据集
         * -------------
         *
         *
         *
         * 三千七百
         * 三千七百零二
         * 三千七百二十
         * 三千七百一十
         * 五万八千二百一十一
         *
         * invalid
         * 零
         * 五千八万六十九
         * 三千零十
         * 五万零千二百一十一
         *
         */
        System.out.println(m_014.convertPart("十", null));        // 10
        System.out.println(m_014.convertPart("十一", null));      // 11
        System.out.println(m_014.convertPart("二十五", null));      // 25
        System.out.println(m_014.convertPart("一千零二十五", null)); // 1025
        System.out.println(m_014.convertPart("五千零十", null));        // -1
        System.out.println(m_014.convertPart("三千七百", null));        // 3700
        System.out.println(m_014.convertPart("三千七百二十", null));      // 3720
        System.out.println(m_014.convertPart("三千七百一十", null));      // 3710
        System.out.println(m_014.convertPart("八千二百一十一", null)); // 8211
        System.out.println(m_014.convertPart("八千二百零十一", null)); // -1
        System.out.println(m_014.convertPart("三千七百", null));    // 3700
        System.out.println(m_014.convertPart("三千七百零", null));   // -1
        System.out.println(m_014.convertPart("三千七百零零二十五", null)); // -1

        System.out.println("-----------------------------------------");
        /**
         * valid:
         * 零
         * 五万亿零三百二十八万零二百八十
         * 五万零三百二十
         *
         * invalid:
         * 五万亿三百二十八万零二百八十
         *
         * 三百二十千八百二十八
         *
         * 一十五
         */
        System.out.println(m_014.solve("五千亿零三百二十八万零二百八十"));     // 500003280280
        System.out.println(m_014.solve("五千零二十三亿三千七百万九千零一十"));  // 502337009010
        System.out.println(m_014.solve("五千零二十三亿T三千七百万九千零一十")); // 502317009010
        System.out.println(m_014.solve("五万零三百二十")); // 50320
        System.out.println(m_014.solve("三百二十千八百二十八"));  // -1
        System.out.println(m_014.solve("一十五")); // -1

        System.out.println(m_014.solve("一万零十")); // 10010
        System.out.println(m_014.solve("一万零一十"));   // -1 (看具体场景)
        System.out.println(m_014.solve("一千"));       // 1000
        System.out.println(m_014.solve("一万"));       // 10000
        System.out.println(m_014.solve("一百万"));     // 1000000
        System.out.println(m_014.solve("一千万"));     // 10000000
        System.out.println(m_014.solve("一亿"));       // 100000000
        System.out.println(m_014.solve("十亿"));       // 1000000000
        System.out.println(m_014.solve("一百亿"));     // 10000000000
        System.out.println(m_014.solve("二百一十亿"));  // 21000000000

        System.out.println(m_014.solve("一亿零五百万")); // 105000000
        System.out.println(m_014.solve("一亿零五千"));   // 100005000
        System.out.println(m_014.solve("一亿零五万三千"));// 100053000
        System.out.println(m_014.solve("一亿零五万零三百"));// 100050300
    }
}
