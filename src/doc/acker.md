# Leetcode-Pro
go and record

## 重点题目(好题)回顾
    (主要记录解法nice、自己刷题过程中觉得很好的题目)

序号    |    题号    |      难度             | 解法归类    
---     |---        |--- | --- 
0      | 罗马数字系列 | --- | ---          
1       |[079-Word Search](https://leetcode.com/problems/word-search/description/) | medium                  | backtracking
2    | [140-Word Break II](https://leetcode.com/problems/word-break-ii/description/) | hard | DFS+记忆搜索
3    | [Pow(x, n)](https://leetcode.com/problems/powx-n/description/) | medium | Math 快速幂
4    | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/description/) | medium | Array(环形链表) 
5    | [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/) | medium | binary search
6    | [089-Gray Code](https://leetcode.com/problems/gray-code/description/) | medium | Math 二进制 格雷码
7    | [092-Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/description/) | medium | 链表区间翻转
8    | [093-Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/description/) | medium | 回溯or三级循环(注意剪枝条件)
9    | [898-Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays/) | medium | 时间复杂度分析
10   | [155-Min Stack](https://leetcode.com/problems/min-stack/description/) | easy | 栈模拟(min的保持)
11   | [114-Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/) | medium | dfs(原地操作-链表平坦化)
12   | [117-Populating Next Right Pointers in Each Node II](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/) | medium | 增加虚拟节点层序遍历
13   | [127-Word Ladder](https://leetcode.com/problems/word-ladder/description/) | medium | BFS bidirectional
14   | [213-House Robber II](https://leetcode.com/problems/house-robber-ii/description/) | medium | dp
15   | [236-Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/) | medium | 最近公共祖先 多解法
16   | [202-Happy Number](https://leetcode.com/problems/happy-number/description/) | easy |the Floyd Cycle detection algorithm
17   | [205-Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/description/) | easy | 字符数据hash
18   | [204-Count Primes](https://leetcode.com/problems/count-primes/description/) | easy | 素数表
19   | [206-Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/description/) | easy | 迭代和递归实现，recursive version is tricker
20   | [130-Surrounded Regions](https://leetcode.com/problems/surrounded-regions/description/) | medium | dfs先解决边缘
21   | [134-Gas Station](https://leetcode.com/problems/gas-station/description/) | medium | 贪心
22   | [138-Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/description/) | medium | 链表拷贝，space O(1)
23   | [137-Single Number II](https://leetcode.com/problems/single-number-ii/description/) | medium |  bit操作
24   | [264-Ugly Number II](https://leetcode.com/problems/ugly-number-ii/description/) | medium | DP,Math
25   | [139-Word Break](https://leetcode.com/problems/word-break/description/)| medium | dp (backtracking会超时)
26   | [224-Basic Calculator](https://leetcode.com/problems/basic-calculator/description/) | hard | 带括号的加减运算
27   | [397-Integer Replacement](https://leetcode.com/problems/integer-replacement/description/) | medium | bit-manipulation/greedy
28   | [396-Rotate Function](https://leetcode.com/problems/rotate-function/description/) | medium | 公式递推
29   | [395-Longest Substring with At Least K Repeating Characters](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/) medium 多解法 好题应该多体会，重点看
30   | [394-Decode String](https://leetcode.com/problems/decode-string/description/) | medium | DFS+stack+map 做这种好题有收获
31   | [004-Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/description/) | hard | 二分-需要思考如果找到中心点
32   | [146-LRU Cache](https://leetcode.com/problems/lru-cache/description/) | hard | 双向链表+hash LRU
33   | [124-Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/description/) | hard | dfs
34   | [162-Find Peak Element](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/) | medium | 二分
35   | [212-Word Search II](https://leetcode.com/problems/word-search-ii/description/) | hard | 字典树+回溯
36   | [673-Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/) | medium | DP
37   | [215-Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/description/) | medium | 堆 or selection algorithm
38   | [300-Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) | medium | LIS - DP+binarySearch
39   | [273-Integer to English Words](https://leetcode.com/problems/integer-to-english-words/description/) | hard | 数字转英文表示（考虑情况较多，trim）
40   | [365-Water and Jug Problem](https://leetcode.com/problems/water-and-jug-problem/description/) | medium | GCD， BFS会超时
41   | [043-Multiply Strings](https://leetcode.com/problems/multiply-strings/description/) | medium | 竖式乘法-新思路(Medium)
42   | [419-Battleships in a Board](https://leetcode.com/problems/battleships-in-a-board/description/) | medium | 模拟 constant space
43   | [445-Add Two Numbers II](https://leetcode.com/problems/add-two-numbers-ii/description/) | medium | 链表表示整数相加(Stack or 用常数空间)
44   | [567-Permutation in String](https://leetcode.com/problems/permutation-in-string/description/) | medium | 滑动窗口
45   | [591-Tag Validator](https://leetcode.com/problems/tag-validator/description/) | hard | stack+string（判断标签代码段是否合法）
46   | [650-2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard/description/) | medium | DP+Math
47   | [652-Find Duplicate Subtrees](https://leetcode.com/problems/find-duplicate-subtrees/description/) | medium | 如何唯一表达子树
48   | [331-Verify Preorder Serialization of a Binary Tree](https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/) | medium | 出度入度
49   | [290-Word Pattern](https://leetcode.com/problems/word-pattern/submissions/) | easy | map-模式匹配要注意两个方向
50   | [166-Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal/) | medium | math+hashtable(测试用例设计)
51   | [201-Bitwise AND of Numbers Range](https://leetcode.com/problems/bitwise-and-of-numbers-range/) | medium | bit-manipulation
52   | [207-Course Schedule](https://leetcode.com/problems/course-schedule/description/) | medium | DFS&BFS&拓扑排序
53   | [210-Course Schedule II](https://leetcode.com/problems/course-schedule-ii/description/) | medium | DFS&BFS&拓扑排序
54   | [220-Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/description/) | medium 19.6% | treeset/bucket桶(window)




