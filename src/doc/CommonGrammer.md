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