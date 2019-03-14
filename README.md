# Leetcode-Pro
go and record

### 模拟
1. [038-Count and Say](https://leetcode.com/problems/count-and-say/description/) easy
2. [048-Rotate Image](https://leetcode.com/problems/rotate-image/) medium
3. [054-Spiral Matrix](https://leetcode.com/problems/spiral-matrix/description/)

### Map
1. [049-Group Anagrams](https://leetcode.com/problems/group-anagrams/) meduim

### Math
1. [043-Multiply Strings](https://leetcode.com/problems/multiply-strings/description/) 竖式乘法-新思路(Medium)
    
Start from right to left, perform multiplication on every pair of digits, and add them together. Let's draw the process! From the following draft, we can immediately conclude:
```
 `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
```

2. [060-Permutation Sequence](https://leetcode.com/problems/permutation-sequence/description/) math+(Kth-permutation)
3. [136-Single Number](https://leetcode.com/problems/single-number/description/) easy 
4. [050-Pow(x, n)](https://leetcode.com/problems/powx-n/description/) medium 快速幂


![竖式乘法计算](https://drscdn.500px.org/photo/130178585/m%3D2048/300d71f784f679d5e70fadda8ad7d68f)

### String
1. [013-Roman to Integer](https://leetcode.com/problems/roman-to-integer/) string+map
2. [012-Integer to roman](https://leetcode.com/problems/integer-to-roman/) string map 技巧性解法
3. [014-Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)
4. [028- Implement strStr()](https://leetcode.com/problems/implement-strstr/) easy

### Stack
1. [496-next-greater-element-i](https://leetcode.com/problems/next-greater-element-i/)
2. [503-next-greater-element-ii](https://leetcode.com/problems/next-greater-element-ii/)
3. [020-Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
4. [071-Simplify Path](https://leetcode.com/problems/simplify-path/description/) medium


### Array
1. [088-Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)
2. [026-Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) easy
3. [027-Remove Element](https://leetcode.com/problems/remove-element/) easy
4. [066-Plus One](https://leetcode.com/problems/plus-one/description/) easy
5. [041-First Missing Positive](https://leetcode.com/problems/first-missing-positive/description/) hard 技巧性-通过数组的索引处的值的正负来记录数字是否存在
6. [073-Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/description/) medium matrix(in-place)
7. [080-Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/) medium

### List
1. [061-Rotate List](https://leetcode.com/problems/rotate-list/description/) medium
2. [141-Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/description/) easy
3. [142-Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/description/) medium
	存在环的链表系列链接(https://www.cnblogs.com/xudong-bupt/p/3667729.html)
4. [287-Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/description/) medium 虽然是数组，但是思想类似142唤醒链表
5. [082-Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/) medium


### Tree
1. [100-Same Tree](https://leetcode.com/problems/same-tree/description/) easy
2. [101-Symmetric Tree](https://leetcode.com/problems/symmetric-tree/submissions/) easy
3. [104-Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) easy (BFS DFS)
4. [653-Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/) medium 数的遍历-经典
```
BST的two pointers，使用stack来辅助前后两个指针的移动
```


### two pointers
1. [015-3Sum](https://leetcode.com/problems/3sum/)
2. [016-3Sum Closest](https://leetcode.com/problems/3sum-closest/)
3. [018-4Sum](https://leetcode.com/problems/4sum/)
4. [019-Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
5. [075-Sort Colors](https://leetcode.com/problems/sort-colors/description/) medium


3. 653 Two Sum IV - Input is a BST (Tree + two pointers)
	
	(1) 结合BST正向遍历和反向遍历来实现two pointers, time O(n) space O(logn)
	
	(2) 前序遍历+hashMap	time O(n) space O(n)
	
	(3) 前序遍历+BST的二分遍历搜索  time O(nlogn)
	
	(4) 中序遍历存储结果到有序数组，对有序数组进行two pointers处理 time O(n) space O(n)
	
### BackTracking
1. [017-Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) 暴力回溯
2. [022-Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) 回溯或者DP
3. [078-Subsets](https://leetcode.com/problems/subsets/description/) medium
4. [079-Word Search](https://leetcode.com/problems/word-search/description/) medium

### DFS && BFS
1. [140-Word Break II](https://leetcode.com/problems/word-break-ii/description/) hard DFS+记忆搜索


### LinkedList&&分治&&recursion
1. [021-Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
2. [023-Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) hard-分治
3. [148-Sort List](https://leetcode.com/problems/sort-list/) - 归并排序的思路
4. [24-Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) -medium(链表成对节点swap)


### binary search
1. [029-Divide Two Integers](https://leetcode.com/problems/divide-two-integers/) 二分查找(注意数据溢出情况)
2. [034-Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/) 区间二分，查找区间 medium
3. [035-Search Insert Position](https://leetcode.com/problems/search-insert-position/description/) easy
4. [033-Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/description/) medium
5. [074-Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/description/) medium
6. [240-Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/description/) medium
7. [081-Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/) medium

### Greedy
1. [055-Jump Game](https://leetcode.com/problems/jump-game/description/) 贪心 medium
2. [045-Jump Game II](https://leetcode.com/problems/jump-game-ii/description/) 贪心 hard(045的升级版本)
3. [056-Merge Intervals](https://leetcode.com/problems/merge-intervals/description/)

### DP
1. [746-Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/) easy
2. [053-Maximum Subarray](https://leetcode.com/problems/maximum-subarray/description/) easy
3. [064-Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/description/) medium


## 特别专题

### 排列组合系列 (组合的下一个组合或者上一个组合)
077, 031, 040, 046


### 积水系列
1. [011-container-with-most-water](https://leetcode.com/problems/container-with-most-water/submissions/)
2. [042-Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
```
https://www.cnblogs.com/felixfang/p/3713197.html
观察下就可以发现被水填满后的形状是先升后降的塔形，因此，先遍历一遍找到塔顶，然后分别从两边开始，往塔顶所在位置遍历，水位只会增高不会减小，且一直和最近遇到的最大高度持平，这样知道了实时水位，就可以边遍历边计算面积。
```



### 蓄水池抽样算法(Reservoir Sampling Algorithm)
定理：该算法保证每个元素以 k / n 的概率被选入蓄水池数组。
https://blog.csdn.net/u010150046/article/details/77017145#commentBox

```
/*
	replace elements with gradually decreasing probability
	算法首先创建一个长度为K的数组（蓄水池）用来存放结果，初始化为S的前k个元素，然后从k+1个元素开始迭代直到数组结束
	算法生成一个随机数j∈[1, i]，如果 j ≤ k，那么蓄水池的第 j 个元素被替换为S的第i个元素。
	
	定理：该算法保证每个元素以 k / n 的概率被选入蓄水池数组。

	证明：首先，对于任意的 i，第 i 个元素进入蓄水池的概率为 k / i；而在蓄水池内每个元素被替换的概率为 1 / k; 因此在第 i 轮第j个元素被替换的概率为 (k / i ) * (1 / k) = 1 / i。 接下来用数学归纳法来证明，当循环结束时每个元素进入蓄水池的概率为 k / n.
	
	假设在 (i-1) 次迭代后，任意一个元素进入 蓄水池的概率为 k / (i-1)。有上面的结论，在第 i 次迭代时，该元素被替换的概率为 1 / i， 那么其不被替换的概率则为 1 - 1/i = (i-1)/i；在第i 此迭代后，该元素在蓄水池内的概率为 k / (i-1) * (i-1)/i = k / i. 归纳部分结束。
	
	因此当循环结束时，每个元素进入蓄水池的概率为 k / n. 命题得证。
*/
for each i in k+1 to length(S) do
    j := random(1, i);   // important: inclusive range
    if j <= k then
        R[j] := S[i]
    fi
done
```

1. [382- Linked List Random Node](https://leetcode.com/problems/linked-list-random-node/)
2. [398- Random Pick Index](https://leetcode.com/problems/random-pick-index/)

### 累计概率分布
1. [528- Random Pick with Weight](https://leetcode.com/problems/random-pick-with-weight/)

### two pointers系列
1. 
2. 653
