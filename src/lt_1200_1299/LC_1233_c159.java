package lt_1200_1299;

import java.util.*;

/**
 * 1233. Remove Sub-Folders from the Filesystem
 */
public class LC_1233_c159 {
    /**
     * 方法一：
     * 直接按照字符串排序，那么父路径子路径肯定出现在一起
     */
    static class Solution1 {
        public List<String> removeSubfolders(String[] folder) {
            LinkedList<String> list = new LinkedList<>();
            if (folder.length <= 0) return list;
            Arrays.sort(folder);
            list.add(folder[0]);
            for (int i = 1; i < folder.length; ++i) {
                if (!folder[i].startsWith(list.getLast()+'/')) {
                    list.add(folder[i]);
                }
            }
            return list;
        }
    }

    /**
     * 方法二：
     * 根据长度排序，那么可以确定子路径肯定会出现在父路径的后边
     * 比如"/a/b"肯定会出现在"/a"的后边
     * 使用set来记录已经出现的父路径，那么遍历到每一个路径的时候，只需要根据'/'寻找从0开始的子串来判断父路径是否存在即可
     *
     * O(nlogn + m*n) m为平均path长度，n为folder中path路径个数
     */
    static class Solution2 {
        public List<String> removeSubfolders(String[] folder) {
            Arrays.sort(folder, Comparator.comparingInt(String::length));
            Set<String> hashSet = new HashSet<>();
            for (String path: folder) {
                boolean found = false;
                for (int i = 2; i < path.length(); ++i) {
                    if (path.charAt(i)=='/' && hashSet.contains(path.substring(0, i))) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    hashSet.add(path);
                }
            }
            return new ArrayList<>(hashSet);
        }
    }


}
