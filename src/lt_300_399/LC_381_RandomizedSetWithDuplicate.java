package lt_300_399;

import java.util.*;

/**
 * LC380
 * follow up
 * 允许重复数出现
 * Map<Integer, Set<Integer>>
 */
public class LC_381_RandomizedSetWithDuplicate {
    private Map<Integer, Set<Integer>> locMap;
    private ArrayList<Integer> list;
    private Random random;

    /** Initialize your data structure here. */
    public LC_381_RandomizedSetWithDuplicate() {
        this.locMap = new HashMap<>();
        this.list = new ArrayList<>();
        this.random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!locMap.containsKey(val)) {
            locMap.put(val, new HashSet<>());
        }
        locMap.get(val).add(list.size());
        list.add(val);
        return locMap.get(val).size()==1;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (locMap.containsKey(val) && locMap.get(val).size() > 0) {
            int loc = locMap.get(val).iterator().next();
            locMap.get(val).remove(loc);

            list.set(loc, list.get(list.size()-1));
            list.remove(list.size()-1);
            // 替换新旧坐标
            if (loc < list.size()) {
                locMap.get(list.get(loc)).remove(list.size());
                locMap.get(list.get(loc)).add(loc);
            }
            return true;
        }
        return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if (list.size() <= 0) return -1;
        return list.get(random.nextInt(list.size()));
    }
}
