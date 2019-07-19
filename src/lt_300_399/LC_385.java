package lt_300_399;

import java.util.ArrayList;
import java.util.List;

/**
 * [385] Mini Parser
 */
public class LC_385 {
     public class NestedInteger {
         private Integer val;
         private List<NestedInteger> list;
          // Constructor initializes an empty nested list.

          public NestedInteger() {
              this.list = new ArrayList<>();
          }

          // Constructor initializes a single integer.
          public NestedInteger(int value) {
              this.val = value;
              this.list = new ArrayList<>();
          }

          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger() {
              return val!=null && this.list.size()==0;
          }

          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger() {
              return this.val;
          }

          // Set this NestedInteger to hold a single integer.
          public void setInteger(int value) {
              this.val = value;
          }

          // Set this NestedInteger to hold a nested list and adds a nested integer to it.
          public void add(NestedInteger ni) {
              this.list.add(ni);
          }

          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return null if this NestedInteger holds a single integer
          public List<NestedInteger> getList() {
              return this.list;
          }
     }

    /**
     * DFS 深搜
     * 不断分解成子问题
     * 注意点：
     * 1. 记录左右括号匹配来找到同一层级的逗号来分割同一层的
     * 2. 空串不做转换
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) {
        NestedInteger nestedInteger = new NestedInteger();
        if (s == null || s.length() <= 0) return nestedInteger;
        if (s.charAt(0) != '[') {
            int val = Integer.parseInt(s);
            nestedInteger.setInteger(val);
        } else {
            int idx = 1, i = 1, leftNum = 0;
            while (i < s.length() - 1) {
                char cur = s.charAt(i);
                if (cur == '[') {
                    leftNum++;
                } else if (cur == ']') {
                    leftNum--;
                }
                if (cur==',' && leftNum==0) {
                    // 空字符串不用处理 直接跳过
                    if (idx < i) {
                        nestedInteger.add(deserialize(s.substring(idx, i)));
                    }
                    idx = i+1;
                }
                i++;
            }
            // 处理最后一个
            if (idx < i) {
                nestedInteger.add(deserialize(s.substring(idx, i)));
            }
        }
        return nestedInteger;
    }
}
