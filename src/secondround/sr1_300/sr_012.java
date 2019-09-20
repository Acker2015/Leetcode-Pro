package secondround.sr1_300;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class sr_012 {
    /**
 * 重复字符串
 * @param str
 * @param n
 * @return
 */
public String repeat(String str, int n) {
    StringBuilder stringBuilder = new StringBuilder();
    IntStream.range(0,  n).forEach(t -> stringBuilder.append(str));
    return stringBuilder.toString();
}

    /**
     * 从低位到高位不断取余remain，根据位置层级level(1,10,100,1000)
     * remain=4 or remain=9, 直接map取数
     * remain<4, 那么取对应的单元数据，连续remain次
     * remain>=5, 那么取5*level对应的数以及(remain-5)次对应的单元数
     *
     * 其中单元数为map.get(level)
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");
        int level = 1;
        while (num > 0) {
            int remain = num % 10;
            if (remain == 4 || remain == 9) {
                builder.insert(0, map.get(level*remain));
            } else if (remain < 5) {
                builder.insert(0, repeat(map.get(level), remain));
            } else {
                String tmp = map.get(5*level) + repeat(map.get(level), remain-5);
                builder.insert(0, tmp);
            }
            num /= 10;
            level *= 10;
        }
        return builder.toString();
    }
}
