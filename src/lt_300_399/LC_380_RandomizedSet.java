package lt_300_399;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * @lc app=leetcode id=380 lang=java
 *
 * [380] Insert Delete GetRandom O(1)
 *
 * https://leetcode.com/problems/insert-delete-getrandom-o1/description/
 *
 * algorithms
 * Medium (41.98%)
 * Total Accepted:    105.4K
 * Total Submissions: 248.8K
 * Testcase Example:  '["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]\n[[],[1],[2],[2],[],[1],[2],[]]'
 *
 * Design a data structure that supports all following operations in average
 * O(1) time.
 *
 *
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each
 * element must have the same probability of being returned.
 *
 *
 *
 * Example:
 *
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 *
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 *
 *
 * 存储数据可以使用map可以保证insert、remove、contain操作时间复杂度为O(1)
 * 那么需要使用ArrayList来实现getRandom的O(1),为什么使用ArrayList,随机读O(1).
 *      那么删数据的时候，怎么实现在ArrayList中O(1)的删数据呢？通过待删除数据与list最后一个元素交换之后，将最后一个元素删除来实现remove的O(1)
 * hashMap + ArrayList
 */
public class LC_380_RandomizedSet {
    Map<Integer, Integer> locMap;
    ArrayList<Integer> list;
    Random random;

    /** Initialize your data structure here. */
    public LC_380_RandomizedSet() {
        this.locMap = new HashMap<>();
        this.list = new ArrayList<>();
        this.random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!locMap.containsKey(val)) {
            list.add(val);
            locMap.put(val, list.size()-1);
            return true;
        }
        return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (locMap.containsKey(val)) {
            int loc = locMap.remove(val);
            // exchange with the last value, 更新替换元素下标索引
            list.set(loc, list.get(list.size()-1));
            list.remove(list.size()-1);
            if (loc < list.size()) {
                locMap.put(list.get(loc), loc);
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
