# Leetcode-Pro
go and record


### Stack
1. [496-next-greater-element-i](https://leetcode.com/problems/next-greater-element-i/)
2. [503-next-greater-element-ii](https://leetcode.com/problems/next-greater-element-ii/)
3. [020-Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)


### Array
1. [088-Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)
2. [026-Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) easy
3. [027-Remove Element](https://leetcode.com/problems/remove-element/) easy


### Tree
1. [653-Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/)
```
BST的two pointers，使用stack来辅助前后两个指针的移动
```


### String
1. [013-Roman to Integer](https://leetcode.com/problems/roman-to-integer/) string+map
2. [012-Integer to roman](https://leetcode.com/problems/integer-to-roman/) string map 技巧性解法
3. [014-Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)
4. [028- Implement strStr()](https://leetcode.com/problems/implement-strstr/) easy

### two pointers
1. [015-3Sum](https://leetcode.com/problems/3sum/)
2. [016-3Sum Closest](https://leetcode.com/problems/3sum-closest/)
3. [018-4Sum](https://leetcode.com/problems/4sum/)
4. [019-Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
3. 653 Two Sum IV - Input is a BST (Tree + two pointers)
	
	(1) 结合BST正向遍历和反向遍历来实现two pointers, time O(n) space O(logn)
	
	(2) 前序遍历+hashMap	time O(n) space O(n)
	
	(3) 前序遍历+BST的二分遍历搜索  time O(nlogn)
	
	(4) 中序遍历存储结果到有序数组，对有序数组进行two pointers处理 time O(n) space O(n)
	
### BackTracking
1. [017-Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) 暴力回溯
2. [022-Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) 回溯或者DP

### LinkedList&&分治&&recursion
1. [021-Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
2. [023-Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) hard-分治
3. [148-Sort List](https://leetcode.com/problems/sort-list/) - 归并排序的思路
4. [24-Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) -medium(链表成对节点swap)


### binary search
1. [029-Divide Two Integers](https://leetcode.com/problems/divide-two-integers/) 二分查找(注意数据溢出情况)
2. [034-Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/) 区间二分，查找区间 medium
3. [035-Search Insert Position](https://leetcode.com/problems/search-insert-position/description/) easy

### DP
1. [746-Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/) easy

## 特别专题

### 排列组合系列
077, 031, 040


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
