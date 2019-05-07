package lt_200_299;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LC_290 {
    /**
     * pattern与str模式需要一一对应
     * 即str中一个word只能对应pattern中唯一的char. -> (abb, dog dog cat)
     * 同事pattern中一个char只能对应str中唯一的一个word. -> (aa, dog cat)
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        if (pattern.length() != arr.length) return false;
        Map<String, Character> map = new HashMap<>();
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < arr.length; ++i) {
            char c = pattern.charAt(i);
            // word对应的char不一致 (同一word对应的char不一致)
            if (map.containsKey(arr[i]) && map.get(arr[i])!=c) {
                return false;
            }
            // word不存在，但是对应的word已经存在(同一char对应的word不一致)
            if (!map.containsKey(arr[i]) && hashSet.contains(c)) {
                return false;
            }
            map.put(arr[i], c);
            hashSet.add(c);
        }
        return true;
    }
}
