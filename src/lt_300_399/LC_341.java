package lt_300_399;


import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * [341] Flatten Nested List Iterator
 */
public class LC_341 {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }

    /**
     * 使用栈来记录Iterator的嵌套关系
     * 使用nextVal提前记录next值，防止空list的存在
     */
    private class NestedIterator implements Iterator<Integer> {

        //private List<NestedInteger> list;
        private Stack<Iterator<NestedInteger>> stack;
        private Integer nextVal;

        public NestedIterator(List<NestedInteger> nestedList) {
            //this.list = nestedList;
            stack = new Stack<>();
            stack.push(nestedList.iterator());

        }

        @Override
        public Integer next() {
            if (nextVal != null || hasNext()) {
                int tmp = nextVal;
                nextVal = null;
                return tmp;
            } else {
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            if (nextVal != null) return true;
            while (!stack.isEmpty()) {
                Iterator<NestedInteger> iterator = stack.peek();
                if (iterator.hasNext()) {
                    NestedInteger nestedInteger = iterator.next();
                    if (nestedInteger.isInteger()) {
                        nextVal = nestedInteger.getInteger();
                        return true;
                    } else {
                        stack.push(nestedInteger.getList().iterator());
                    }
                } else {
                    stack.pop();
                }
            }
            return false;
        }
    }
}
