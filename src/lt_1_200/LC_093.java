package lt_1_200;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hzwanggaige
 * Date: 2019/3/18
 */
public class LC_093 {
    private void backtracking(String s, int index, String subIp, int level, List<String> ips) {
        if (index == s.length()) {
            if (level == 4) {
                ips.add(subIp);
            }
            return;
        }
        if (s.length()-index > (4-level)*3) return;
        for (int i=1; i <=3; ++i) {
            if (index+i > s.length()) break;
            int num = Integer.valueOf(s.substring(index, index+i));
            if (i == 2 && num<10) continue;
            if (i == 3 && (num < 100 || num > 255)) continue;
            backtracking(s, index+i, level==0?String.valueOf(num):subIp+"."+String.valueOf(num), level+1, ips);
        }
    }

    /**
     * 解法一
     * 回溯-注意剪枝
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses1(String s) {
        List<String> retList = new LinkedList<>();
        backtracking(s, 0, "", 0, retList);
        for (int i = 0; i < retList.size(); ++i) {
            System.out.println(retList.get(i));
        }
        return retList;
    }


    /**
     * 判断ip的子串是否合法
     * @param s
     * @return
     */
    private boolean valid(String s) {
        if (s.length()==0 || s.length() > 3 || (s.length()>1 && s.charAt(0)=='0') || Integer.parseInt(s) > 255) return false;
        return true;
    }
    /**
     * 解法二：
     * 由于s肯定需要被分为四段，直接三级循环即可，需要判断分割的子串是否合法
     * Input: "25525511135"
     * Output: ["255.255.11.135", "255.255.111.35"]
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> retList = new LinkedList<>();
        int len = s.length();
        // i, j, k means the ending position (don't include the index)
        for (int i = 1; i < 4 && i < len-2; ++i) {
            for (int j = i+1; j < i+4 && j < len-1; ++j) {
                for (int k = j+1; k < j+4 && k < len; ++k) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if (valid(s1)&&valid(s2)&&valid(s3)&&valid(s4)) {
                        retList.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return retList;
    }

}
