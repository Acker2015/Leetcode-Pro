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
55   | [241-Different Ways to Add Parentheses](https://leetcode.com/problems/different-ways-to-add-parentheses/description/) | 分治 | medium
56   | [223-Rectangle Area](https://leetcode.com/problems/rectangle-area/description/) | math | medium
57   | [371-Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/description/) | easy | 二进制实现加法运算
58   | [299-Bulls and Cows]() | medium | hashMap
59   | [306-Additive Number](https://leetcode.com/problems/additive-number/description/) | medium | hashMap
60   | [310-Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees/description/) | medium | 不断切掉叶子节点
61   | [354-Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/description/) | hard | DP(同最大上升子序列)
62   | [315-Count of Smaller Numbers After Self]() | hard | BST or merge_sort
63   | [406-Queue Reconstruction by Height]() | medium | 贪心 怎么根据一个人的身高和位置来将他插入正确的位置
64   | [239-Sliding Window Maximum]() | hard | 双端队列/链表模拟队列
65   | [480-Sliding Window Median]()  | hard | 两个优先队列各存一半
66   | [327-Count of Range Sum]() | hard | BST
67   | [843-Guess the Word]() | hard | 有点概率的意思，最小化思想
68   | [324-Wiggle Sort II]() | medium | 摇摆排序 partition
69   | [332-Reconstruct Itinerary]() | medium | 欧拉路径
70   | [334-Increasing Triplet Subsequence]() | medium | 不是严格意义的two-pointers,这里只是使用两个指针遍历记录
71   | [437-Path Sum III]() | easy-medium | tree+dfs+map
72   | [343-Integer Break]() | medium | dp or math
73   | [347-Top K Frequent Elements]()| medium | hashmap+桶排序
74   | [357-Count Numbers with Unique Digits]() | medium | DP+math(感觉这题不难，不知道为啥想不出来)
75   | [368-Largest Divisible Subset]() | medium+DP | (类似LIS的DP思路，需要做路径优化)
76   | [373-Find K Pairs with Smallest Sums]() | medium | set&priorityQueue
77   | [372-Super Pow]() | medium | math-pow
78   | [375-Guess Number Higher or Lower II]() | medium+DP | DP-理解题意
79   | [376-Wiggle Subsequence]() | medium | 贪心
80   | [10-Regular Expression Matching]() | hard | (DP、backtracking)
81   | [44-Wildcard Matching]()  | hard | pattern匹配， greedy or DP
82   | [p:list-判断链表是否回文]() | medium | 递归方法判断
83   | [410-Split Array Largest Sum]() | hard | binary-search (二分类似koko-875)
84   | [282-Expression Add Operators]() | hard | DFS (处理后续运算符的优先级)
85   | [218-The Skyline Problem]() | hard | priority-queue(如果维持开始结束之间的高度)
86   | [377-Combination Sum IV]() | medium | DFS or DP
87   | [494-Target Sum]() | medium | DP/BFS/DFS
88   | [670-Maximum Swap]() | medium | Greedy
89   | [453-Minimum Moves to Equal Array Elements]() | easy | math
90   | [463-Island Perimeter]() | easy | math
91   | [525-Contiguous Array]() | medium | hashMap
92   | [523-Continuous Subarray Sum]() | medium | hashMap




