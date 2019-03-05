package lt_1_200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LC_049 {
	/**
     * 问题转化为 如果字母排序后的String相同，那么就符合条件放在一个list.
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> retList = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        if (strs.length <= 0) return retList;
        for (int i = 0; i < strs.length; ++i) {
            String tmp = strs[i];
            char[] charArr = tmp.toCharArray();
            Arrays.sort(charArr);
            String sortTmp = String.valueOf(charArr);
            if (!map.containsKey(sortTmp)) {
                List<String> subList = new LinkedList<>();
                map.put(sortTmp, retList.size());
                retList.add(subList);
            }
            retList.get(map.get(sortTmp)).add(tmp);
        }
        return retList;
    }
    /**
     * 使用26个素数对应26字母，计算每个字符串的字符对应素数乘积作为key
     */
    public List<List<String>> groupAnagrams(String[] strs) {
    	if (strs.length <= 0) return new ArrayList<>();
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
        List<List<String>> retList = new ArrayList<>();
        Map<Long, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < strs.length; ++i) {
            char[] chs = strs[i].toCharArray();
            Long tmp = 1L;
            for (int j = 0; j < chs.length; ++j) {
                tmp *= primes[chs[j]-'a'];
            }
            if (!indexMap.containsKey(tmp)) {
                indexMap.put(tmp, retList.size());
                retList.add(new ArrayList<>());
            }
            retList.get(indexMap.get(tmp)).add(strs[i]);
        }
        return retList;
    }
	public static void main(String[] args) {
		String[] words = {"eat","tea","tan","ate","nat","bat"};
		LC_049 lc_049 = new LC_049();
		System.out.println(lc_049.groupAnagrams(words));

	}

}
