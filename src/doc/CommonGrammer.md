1. 字符串翻转
```$java
new StringBuilder().append("hello").reverse().toString()
```
2. StringBuilder
```$java
new StringBuilder().length();
builder.deleteCharAt(int index);
builder.delete(int begin, int end);
```

3. List的反转
```$xslt
List<Integer> retList = new ArrayList<>();
Collections.reverse(retList);
```

4. TreeSet
```$java
// https://www.geeksforgeeks.org/treeset-in-java-with-examples/

1. ceiling​(E e): This method returns the least element in this set greater than or equal to the given element, or null if there is no such element.
2. floor​(E e): This method returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
3. higher​(E e): This method returns the least element in this set strictly greater than the given element, or null if there is no such element.
4. lower​(E e): This method returns the greatest element in this set strictly less than the given element, or null if there is no such element.
```

5. TreeMap
```$java
public K TreeMap.lowerKey(K key)    // This method returns the greatest key strictly less than to key, or null if there is no such key.
public K higherKey(K key)           // This method returns the least key greater than key, or null if there is no such key.
public Map.Entry lowerEntry(K key)  // This method returns an entry with the greatest key less than key, or null if there is no such key.
public Map.Entry higherEntry(K key) // This method returns an entry with the least key greater than key, or null if there is no such key.

public K floorKey(K key)            // The method call returns the greatest key less than or equal to key, or null if there is no such key.
public K ceilingKey(K key)          // This method returns the least key which is greater than or equal to the given key value. If such a key is absent, null is returned.

```

6. 数组的拷贝
```$java
int[] nums = {1,2,3,4, 5}
int[] numsClone = nums.clone();
```

7. 带符号右移和无符号右移
```java
// >>   带符号右移。正数右移高位补0，负数右移高位补1
// >>>  无符号右移。无论正数还是负数，高位通通补0
```