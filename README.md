# Leetcode-Pro
go and record

### 模拟
1. [038-Count and Say](https://leetcode.com/problems/count-and-say/description/) easy
2. [048-Rotate Image](https://leetcode.com/problems/rotate-image/) medium
3. [054-Spiral Matrix](https://leetcode.com/problems/spiral-matrix/description/)
4. [419-Battleships in a Board](https://leetcode.com/problems/battleships-in-a-board/description/) 模拟
5. [284-Peeking Iterator]()
6. [412-Fizz Buzz]() 简单模拟

### Math & bit operation
1. [043-Multiply Strings](https://leetcode.com/problems/multiply-strings/description/) 竖式乘法-新思路(Medium)  
Start from right to left, perform multiplication on every pair of digits, and add them together. Let's draw the process! From the following draft, we can immediately conclude:
```
 `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
```
![竖式乘法计算](https://drscdn.500px.org/photo/130178585/m%3D2048/300d71f784f679d5e70fadda8ad7d68f)

2. [060-Permutation Sequence](https://leetcode.com/problems/permutation-sequence/description/) math+(Kth-permutation)
3. [136-Single Number](https://leetcode.com/problems/single-number/description/) easy 
4. [050-Pow(x, n)](https://leetcode.com/problems/powx-n/description/) medium 快速幂
5. [089-Gray Code](https://leetcode.com/problems/gray-code/description/) medium 二进制 格雷码
6. [*** 898-Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays/) medium - 时间复杂度分析
7. [067-Add Binary](https://leetcode.com/problems/add-binary/description/) easy 二进制加法 
8. [168-Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/description/) easy excel表格列位置计算
9. [171-Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number/description/) easy easy表格列位置计算
10. [172-Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes/) easy math 阶乘的因子个数
11. [191- Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/description/) easy math n&(n-1) bit
12. [190-Reverse Bits](https://leetcode.com/problems/reverse-bits/description/) easy bit math
13. [202-Happy Number](https://leetcode.com/problems/happy-number/description/) easy the Floyd Cycle detection algorithm
14. [205-Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/description/) easy 字符数据hash
15. [204-Count Primes](https://leetcode.com/problems/count-primes/description/) easy 素数表
16. [137-Single Number II](https://leetcode.com/problems/single-number-ii/description/) medium bit
17. [260-Single Number III](https://leetcode.com/problems/single-number-iii/description/) medium bit
18. [263-Ugly Number](https://leetcode.com/problems/ugly-number/description/) easy
19. [258-Add Digits](https://leetcode.com/problems/add-digits/description/) easy formula
20. [231-Power of Two](https://leetcode.com/problems/power-of-two/submissions/) easy bit-manipulation or binary search
21. [268-Missing Number](https://leetcode.com/problems/missing-number/description/) easy big manipulation
22. [397-Integer Replacement](https://leetcode.com/problems/integer-replacement/description/) medium bit-manipulation/greedy
23. [396-Rotate Function](https://leetcode.com/problems/rotate-function/description/) medium 公式递推
24. [273-Integer to English Words](https://leetcode.com/problems/integer-to-english-words/description/) hard 数字转英文表示
25. [365-Water and Jug Problem](https://leetcode.com/problems/water-and-jug-problem/description/) medium GCD
26. [650-2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard/description/) medium DP+Math
27. [166-Fraction to Recurring Decimal](https://leetcode.com/problems/fraction-to-recurring-decimal/) math+hashtable
28. [187-Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/description/) medium+DNA Sequence+(hashMap, bit) 
29. [201-Bitwise AND of Numbers Range](https://leetcode.com/problems/bitwise-and-of-numbers-range/) bit-manipulation
30. [223-Rectangle Area](https://leetcode.com/problems/rectangle-area/description/) math medium
31. [326-Power of Three](https://leetcode.com/problems/power-of-three/description/) easy pow-of-three
32. [342-Power of Four](https://leetcode.com/problems/power-of-four/description/) easy pow-of-four
33. [371-Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/description/) easy-二进制实现加法运算
34. [389-Find the Difference]() easy bit-manipulation or hashmap
35. [400-Nth Digit]() easy 
36. [318-Maximum Product of Word Lengths]() medium bit+hash
37. [319-Bulb Switcher]() medium math
38. [405-Convert a Number to Hexadecimal]() easy Hexadecimal转换
39. [343-Integer Break]() medium dp or math
40. [372-Super Pow]() medium math
41. [453-Minimum Moves to Equal Array Elements]() easy math
42. [461-Hamming Distance]() easy bit
43. [463-Island Perimeter]() easy math
44. [476-Number Complement]() easy bit
45. [390-Elimination Game]() medium 数学思想
46. [504-Base 7]() easy math
47. [507-Perfect Number]() easy math
48. [478-Generate Random Point in a Circle]() math, circle, random
49. [233-Number of Digit One]() hard math 1的个数
50. [1238-Circular Permutation in Binary Representation]() medium gray-code


### String
1. [013-Roman to Integer](https://leetcode.com/problems/roman-to-integer/) string+map
2. [012-Integer to roman](https://leetcode.com/problems/integer-to-roman/) string map 技巧性解法
3. [014-Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)
4. [028-Implement strStr()](https://leetcode.com/problems/implement-strstr/) easy
5. [242-Valid Anagram](https://leetcode.com/problems/valid-anagram/description/) easy math&string&ascii
6. [151-Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/description/) medium 字符串单词位置翻转
7. [165-Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/description/) medium deserve-review
8. [443-String Compression](https://leetcode.com/problems/string-compression/description/) easy 字符压缩
9. [306-Additive Number](https://leetcode.com/problems/additive-number/description/) medium
10.[434-Number of Segments in a String]() easy
11.[459-Repeated Substring Pattern]() easy String
12.[482-License Key Formatting]() easy String
13.[008-String to Integer - atoi]() hard 边界情况
14.[423-Reconstruct Original Digits from English]() medium 从唯一性数字对应的字符串开始
15.[520-Detect Capital]() easy 
16.[468-Validate IP Address]() medium String 
17.[65-Valid Number]() hard String 
18.[541-Reverse String II]() easy String
19.[551-Student Attendance Record I]() easy string
20.[557-Reverse Words in a String III]() easy string
21.[537-Complex Number Multiplication]() medium String
22.[481-Magical String]() medium String google 字符串前边部分影响字符串后续的生长


### Stack
1. [496-next-greater-element-i](https://leetcode.com/problems/next-greater-element-i/)
2. [503-next-greater-element-ii](https://leetcode.com/problems/next-greater-element-ii/)
3. [020-Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
4. [071-Simplify Path](https://leetcode.com/problems/simplify-path/description/) medium
5. [155-Min Stack](https://leetcode.com/problems/min-stack/description/) easy 模拟栈
6. [150-Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/description/) 后缀表达式rpn计算
7. [224-Basic Calculator](https://leetcode.com/problems/basic-calculator/description/) hard 带括号的加减运算
8. [227-Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/description/) medium 无括号的加减乘除操作
9. [232-Implement Queue using Stacks](https://leetcode.com/articles/implement-queue-using-stacks/) medium 使用栈模拟队列
10.[225-Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/description/) easy 使用队列模拟栈
11.[445-Add Two Numbers II](https://leetcode.com/problems/add-two-numbers-ii/description/) medium
12.[591-Tag Validator](https://leetcode.com/problems/tag-validator/description/) hard stack+string（判断标签代码段是否合法）
13.[456-132 Pattern]() medium Stack(29%)

### Queue & priorityQueue
1. [281-Zigzag Iterator](https://blog.csdn.net/magicbean2/article/details/74926011) medium 加锁题
2. [373-Find K Pairs with Smallest Sums]() medium set&priorityQueue
3. [218-The Skyline Problem]() hard priority-queue(如果维持开始结束之间的高度)
4. [378-Kth Smallest Element in a Sorted Matrix]() medium priorityQueue or binarySearch

### Array
1. [088-Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)
2. [026-Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) easy
3. [027-Remove Element](https://leetcode.com/problems/remove-element/) easy
4. [066-Plus One](https://leetcode.com/problems/plus-one/description/) easy
5. [041-First Missing Positive](https://leetcode.com/problems/first-missing-positive/description/) hard 技巧性-通过数组的索引处的值的正负来记录数字是否存在
6. [073-Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/description/) medium matrix(in-place)
7. [080-Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/) medium
8. [189-Rotate Array](https://leetcode.com/problems/rotate-array/description/) easy 数组旋转
9. [228-Summary Ranges](https://leetcode.com/problems/summary-ranges/description/) medium 
10.[289-Game of Life]() medium
11.[304-Range Sum Query 2D-Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/description/) medium 
12.[485-Max Consecutive Ones]() array easy
13.[1122-Relative Sort Array]() easy array+sort(桶排序) 或者 arrays.sort
14.[442-Find All Duplicates in an Array]() medium 值映射索引解法
15.[454-4Sum II]() medium 
16.[1144-Decrease Elements To Make Array Zigzag]() medium array
17.[581-Shortest Unsorted Continuous Subarray]()easy array (O(n)的解法应该是medium)


### List
1. [061-Rotate List](https://leetcode.com/problems/rotate-list/description/) medium
2. [141-Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/description/) easy
3. [142-Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/description/) medium
	存在环的链表系列链接(https://www.cnblogs.com/xudong-bupt/p/3667729.html)
4. [287-Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/description/) medium 虽然是数组，但是思想类似142环形链表
5. [082-Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/) medium
6. [086-Partition List](https://leetcode.com/problems/partition-list/description/) medium
7. [092-Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/description/) medium 区间翻转
8. [160-Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/description/) easy 两个链表相遇（两种解法，核心思想让两个链表的遍历指针同步）
9. [203-Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/description/) easy 
10.[237-Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/description/) easy
11.[206-Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/description/) easy 迭代和递归实现
12.[138-Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer/description/) medium
13.[143-Reorder List](https://leetcode.com/problems/reorder-list/description/) medium
14.[146-LRU Cache](https://leetcode.com/problems/lru-cache/description/) hard 双向链表+hash LRU
15.[239-Sliding Window Maximum]() hard 双端队列/链表模拟队列
16.[480-Sliding Window Median]() hard 两个优先队列各存一半
17.[430-Flatten a Multilevel Doubly Linked List]() medium 递归与迭代解法 ***

### Map
1. [049-Group Anagrams](https://leetcode.com/problems/group-anagrams/) medium
2. [217-Contains Duplicate](https://leetcode.com/problems/contains-duplicate/description/) easy
3. [036-Valid Sudoku](https://leetcode.com/problems/valid-sudoku/description/) medium
4. [387-First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/description/) medium
5. [290-Word Pattern](https://leetcode.com/problems/word-pattern/submissions/) easy map
6. [299-Bulls and Cows]() medium hashMap
7. [383-Ransom Note]() hashmap
8. [409-Longest Palindrome]() hashmap
9. [347-Top K Frequent Elements]() hashmap+桶排序
10.[438-Find All Anagrams in a String]() easy slide-window&hashMap
11.[447-Number of Boomerangs]() easy hashMap
12.[128-Longest Consecutive Sequence]() hard hashmap
13.[525-Contiguous Array]() medium hashMap
14.[523-Continuous Subarray Sum]() medium hashMap
15.[554-Brick Wall]() medium hashMap
16.[1124-Longest Well-Performing Interval]() medium hashMap(有点贪心的意思)
17.[1224-Maximum Equal Frequency]() hard mem+数学推理
18.[560-Subarray Sum Equals K]() medium map(解法类似two-sum)

### Tree & Graph
1. [100-Same Tree](https://leetcode.com/problems/same-tree/description/) easy
2. [101-Symmetric Tree](https://leetcode.com/problems/symmetric-tree/submissions/) easy
3. [104-Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) easy (BFS DFS)
4. [653-Two Sum IV - Input is a BST](https://leetcode.com/problems/two-sum-iv-input-is-a-bst/) medium 树的遍历-经典
```
BST的two pointers，使用stack来辅助前后两个指针的移动
```
5. [095-Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii/description/) medium 构建BST
6. [096-Unique Binary Search Trees](https://leetcode.com/problems/unique-binary-search-trees/description/) medium 095的变体，卡特兰数
7. [098-Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/description/) medium 判断二叉树是否为BST
8. [102-Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/description/) medium level traversal
9. [103-Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/) medium zigzag level traversal
10.[105-Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/) medium 中序前序构建二叉树
11.[106-Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/) medium 中序后序构建二叉树
12.[109-Convert Sorted List to Binary Search Tree](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/) medium
13.[113-Path Sum II](https://leetcode.com/problems/path-sum-ii/description/) medium dfs
14.[114-Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/) medium in-place flatten tree
15.[116-Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/) medium
16.[117-Populating Next Right Pointers in Each Node II](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/) medium
17.[129-Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/description/) medium
18.[235-Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/) Easy
19.[236-Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/) medium 最近公共祖先
20.[257-Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/description/) easy
21.[226-InverFt Binary Tree](https://leetcode.com/problems/invert-binary-tree/description/) easy
22.[652-Find Duplicate Subtrees](https://leetcode.com/problems/find-duplicate-subtrees/description/) medium 如何唯一表达子树
23.[331-Verify Preorder Serialization of a Binary Tree](https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/) medium 出度入度
24.[654-Maximum Binary Tree](https://leetcode.com/problems/maximum-binary-tree/description/) medium 重新构建树divide
25.[222-Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes/description/) medium 完全二叉树节点数量
26.[310-Minimum Height Trees](https://leetcode.com/problems/minimum-height-trees/description/) medium 不断切掉叶子节点
27.[315-Count of Smaller Numbers After Self]() hard BST or merge_sort
28.[327-Count of Range Sum]() hard BST
29.[332-Reconstruct Itinerary]() medium 欧拉路径
30.[753-Cracking the Safe]() hard 欧拉路经&DFS
31.[437-Path Sum III]() easy-medium tree+dfs+map
32.[1123-Lowest Common Ancestor of Deepest Leaves]() medium DFS 树
33.[538-Convert BST to Greater Tree]() easy DFS树
34.[285-Inorder Successor in BST]() medium  BST的中序后继
35.[510-Inorder Successor in BST II]() medium   BST的中序后继
36.[352-Data Stream as Disjoint Intervals]() hard TreeMap


### Trie Tree
1. [208-Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/description/) medium Trie 字典树
2. [212-Word Search II](https://leetcode.com/problems/word-search-ii/description/) hard 字典树+回溯
3. [421-Maximum XOR of Two Numbers in an Array]() medium bit+Trie
4. [336-Palindrome Pairs]() hard trie+search


### Segment tree
1. 307-Range Sum Query - Mutable medium 线段树




### DFS && BFS
1. [140-Word Break II](https://leetcode.com/problems/word-break-ii/description/) hard DFS+记忆搜索
2. [127-Word Ladder](https://leetcode.com/problems/word-ladder/description/) medium bidirectional 双向BFS
3. [126-Word Ladder II]() BFS + backtracking
3. [130-Surrounded Regions](https://leetcode.com/problems/surrounded-regions/description/) medium dfs先解决边缘
4. [133-Clone Graph](https://leetcode.com/problems/clone-graph/description/) medium BFS/DFS
5. [394-Decode String](https://leetcode.com/problems/decode-string/description/) medium DFS+stack+map
6. [513-Find Bottom Left Tree Value](https://leetcode.com/problems/find-bottom-left-tree-value/description/) Medium DFS
7. [207-Course Schedule](https://leetcode.com/problems/course-schedule/description/) DFS&BFS&拓扑排序
8. [210-Course Schedule II](https://leetcode.com/problems/course-schedule-ii/description/) medium DFS&BFS&拓扑排序
9. [404-Sum of Left Leaves]() DFS
10.[429-N-ary Tree Level Order Traversal]() easy BFS
11.[282-Expression Add Operators]() hard DFS (处理后续运算符的优先级)
12.[637-Average of Levels in Binary Tree]() easy BFS
13.[543-Diameter of Binary Tree]() DFS intuition
14.[572-Subtree of Another Tree]() DFS easy
15.[386-Lexicographical Numbers]() medium DFS+Math
16.[385-Mini Parser]()
17.[417-Pacific Atlantic Water Flow]() medium DFS or BFS
18.[515-Find Largest Value in Each Tree Row]() medium BFS
19.[529-Minesweeper]() medium DFS/BFS 扫雷游戏
20.[675-Cut Off Trees for Golf Event]() hard BFS golf Event
21.[690-Employee Importance]() easy BFS
22.[787-Cheapest Flights Within K Stops]() medium BFS+Dijkstra Algorithm
23.[803-Bricks Falling When Hit]() hard DFS+有点union-find的意思 非常好
24.[433-Minimum Genetic Mutation]() medium BFS, 双向BFS
25.[450-Delete Node in a BST]() medium DFS 
26.[1145-Binary Tree Coloring Game]() medium DFS+简单推导
27.[473-Matchsticks to Square]() medium DFS 
28.[698-Partition to K Equal Sum Subsets]() medium DFS
29.[530-Minimum Absolute Difference in BST]() easy DFS
30.[623-Add One Row to Tree]() medium BFS
31.[337-House Robber III]() hard DFS
32.[407-Trapping Rain Water II]() hard BFS（剥洋葱式BFS、从外围到里边）




### BackTracking
1. [017-Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) 暴力回溯
2. [022-Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) 回溯或者DP
3. [078-Subsets](https://leetcode.com/problems/subsets/description/) medium
4. [079-Word Search](https://leetcode.com/problems/word-search/description/) medium
5. [090-Subsets II](https://leetcode.com/problems/subsets-ii/description/) medium
6. [093-Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/description/) medium 回溯or三级循环(注意剪枝条件)
7. [130-Surrounded Regions](https://leetcode.com/problems/palindrome-partitioning/description/) medium intuition backtracking
8. [131-Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/description/) medium
9. [124-Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/description/) hard dfs
10.[401-Binary Watch]() easy backtracking
11.[51-N-Queens]() hard 回溯
12.[301-Remove Invalid Parentheses]() hard backtracking
13.[1125. Smallest Sufficient Team]() 迭代-选与不选
14.[37-Sudoku Solver]() hard backtracking
15.[52-N-Queens II]() hard N皇后 backtracking经典问题
16.[526-Beautiful Arrangement]() medium backtracking
17.[216-Combination Sum III]() medium backtracking
18.[1219-Path with Maximum Gold]() medium backtracking
19.[1239-Maximum Length of a Concatenated String with Unique Characters]() medium backtracking

### two pointers
1. [015-3Sum](https://leetcode.com/problems/3sum/)
2. [016-3Sum Closest](https://leetcode.com/problems/3sum-closest/)
3. [018-4Sum](https://leetcode.com/problems/4sum/)
4. [019-Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
5. [075-Sort Colors](https://leetcode.com/problems/sort-colors/description/) medium
6. [653 Two Sum IV - Input is a BST]() (Tree + two pointers)
	
	(1) 结合BST正向遍历和反向遍历来实现two pointers, time O(n) space O(logn)
	
	(2) 前序遍历+hashMap	time O(n) space O(n)
	
	(3) 前序遍历+BST的二分遍历搜索  time O(nlogn)
	
	(4) 中序遍历存储结果到有序数组，对有序数组进行two pointers处理 time O(n) space O(n)
7. [395-Longest Substring with At Least K Repeating Characters](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/) medium 好题应该多体会，重点看
8. [392-Is Subsequence](https://leetcode.com/problems/is-subsequence/description/) medium O(m+n)
9. [567-Permutation in String](https://leetcode.com/problems/permutation-in-string/description/) 滑动窗口
10.[209-Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/description/) medium two-pointers||binary-search
11.[344-Reverse String](https://leetcode.com/problems/reverse-string/description/) easy easy
12.[345-Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string/description/) easy-easy
13.[349-Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/solution/) easy
14.[350-Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-II)
15.[334-Increasing Triplet Subsequence]() medium 不是严格意义的two-pointers,这里只是使用两个指针遍历记录
16.[413-Arithmetic Slices]() easy two-pointers
17.[457-Circular Array Loop]() medium two-pointers 快慢指针 题目不咋地
18.[532-K-diff Pairs in an Array]() easy two-pointers


### LinkedList&&分治&&recursion&&Divide-Conquer
1. [021-Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
2. [023-Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) hard-分治
3. [148-Sort List](https://leetcode.com/problems/sort-list/) - 归并排序的思路
4. [24-Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) -medium(链表成对节点swap)
5. [395-Longest Substring with At Least K Repeating Characters](https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/) medium 分支可解 好题应该多体会，重点看
6. [215-Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/description/) medium 堆 or Conquer
7. [241-Different Ways to Add Parentheses](https://leetcode.com/problems/different-ways-to-add-parentheses/description/) 分治 medium
8. [324-Wiggle Sort II]() medium 摇摆排序 partition
9. [427-Construct Quad Tree]() medium 递归

### binary search
1. [029-Divide Two Integers](https://leetcode.com/problems/divide-two-integers/) 二分查找(注意数据溢出情况)
2. [034-Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/) 区间二分，查找区间 medium
3. [035-Search Insert Position](https://leetcode.com/problems/search-insert-position/description/) easy
4. [033-Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/description/) medium
5. [074-Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/description/) medium
6. [240-Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/description/) medium
7. [081-Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/) medium
8. [004-Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/description/) hard 二分-需要思考如果找到中心点
9. [153-Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/) medium 二分
10.[162-Find Peak Element](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/) medium 二分
11.[278-First Bad Version](https://leetcode.com/problems/first-bad-version/description/) easy binary-search
12.[220-Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/description/) medium 19.6% treeset/bucket桶(window)
13.[230-Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/) BST-binarySearch medium
14.[367-Valid Perfect Square](https://leetcode.com/problems/valid-perfect-square/description/) binary-search
15.[374-Guess Number Higher or Lower]() easy binary-search
16.[441-Arranging Coins]() easy binary-search
17.[875-Koko Eating Bananas]() medium binary-search
18.[410-Split Array Largest Sum]() hard binary-search (二分类似koko-875)
19.[475-Heaters]() easy binary-search or two pointers
20.[744-Find Smallest Letter Greater Than Target]() easy binary_search
21.[719-Find K-th Smallest Pair Distance]() hard binary-search(枚举距离)
22.[658-Find K Closest Elements]() medium 二分/双指针/堆
23.[436-Find Right Interval]() medium 二分，treeMap
24.[540-Single Element in a Sorted Array]() medium 二分 

### union-find
1. [721-Accounts Merge]() medium 并查集
2. [547-Friend Circles]() medium 并查集-找朋友


### Greedy
1. [055-Jump Game](https://leetcode.com/problems/jump-game/description/) 贪心 medium
2. [045-Jump Game II](https://leetcode.com/problems/jump-game-ii/description/) 贪心 hard(045的升级版本)
3. [056-Merge Intervals](https://leetcode.com/problems/merge-intervals/description/)
4. [134-Gas Station](https://leetcode.com/problems/gas-station/description/) medium **
5. [121-Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/) easy 买卖股票
6. [406-Queue Reconstruction by Height]() medium 贪心
7. [376-Wiggle Subsequence]() medium 贪心
8. [57-Insert Interval]() hard interval-贪心
9. [44-Wildcard Matching]() hard pattern匹配， greedy or DP
10.[670-Maximum Swap]() medium Greedy
11.[455-Assign Cookies]() easy 贪心
12.[621-Task Scheduler]() medium 任务调度 math+greedy
13.[68-Text Justification]() hard 常规贪心
14.[402-Remove K Digits]() medium greedy+stack
15.[435-Non-overlapping Intervals]() medium 排序+贪心
16.[1147-Longest Chunked Palindrome Decomposition]() hard DP-贪心
17.[561-Array Partition I]() greedy 贪心
18.[135-Candy]() greedy hard
19.[1217-Play with Chips]() easy greedy
20.[330-Patching Array]() hard greedy




### DP
0. [5-Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/description/) 最大回文子串 medium O(N^2)
1. [746-Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/) easy
2. [053-Maximum Subarray](https://leetcode.com/problems/maximum-subarray/description/) easy
3. [064-Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/description/) medium
4. [091-Decode Ways](https://leetcode.com/problems/decode-ways/description/) medium dp
4-1. [639-Decode Ways II]() hard dp
5. [198-House Robber](https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.) easy 递推 dp
6. [213-House Robber II](https://leetcode.com/problems/house-robber-ii/description/) medium 同198
7. [264-Ugly Number II](https://leetcode.com/problems/ugly-number-ii/description/) medium DP,Math
7-1. [313-Super Ugly Number]() medium DP math
8. [139-Word Break](https://leetcode.com/problems/word-break/description/) medium dp (backtracking会超时)
9. [673-Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/) medium DP
10.[674-Longest Continuous Increasing Subsequence](https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/) easy DP
11.[300-Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/) medium DP+binarySearch
12.[650-2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard/description/) medium DP+Math
13.[303-Range Sum Query - Immutable](https://leetcode.com/problems/range-sum-query-immutable/description/) easy
14.[152-Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) DP medium
15.[292-Nim Game](https://leetcode.com/problems/nim-game/discuss/73760/One-line-O(1)-solution-and-explanation) medium Math,DP 
16.[221-Maximal Square](https://leetcode.com/problems/maximal-square/description/) DP
17.[354-Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/description/) hard DP(同最大上升子序列)
18.[877-Stone Game]() medium DP
19.[322-Coin Change]() medium DP
20.[338-Counting Bits]() medium DP bit-dp
21.[357-Count Numbers with Unique Digits]() medium+DP+math 感觉这题不难，不知道为啥想不出来
22.[368-Largest Divisible Subset]() medium+DP (类似LIS的DP思路，需要做路径优化)
23.[375-Guess Number Higher or Lower II]() medium+DP 理解题意
24.[10-Regular Expression Matching]() hard+(DP、backtracking) 
25.[377-Combination Sum IV]() medium DFS or DP
26.[416-Partition Equal Subset Sum]() medium DP 类似0-1背包
27.[494-Target Sum]() medium DP
28.[689-Maximum Sum of 3 Non-Overlapping Subarrays]() medium DP 题目很好
29.[887-Super Egg Drop]() hard DP  扔鸡蛋问题
30.[764-Largest Plus Sign]() medium DP 
31.[801-Minimum Swaps To Make Sequences Increasing]() medium DP 两个数组同事保持升序的最小交换次数
32.[1140-Stone Game II]() medium dp+memorization
33.[509-Fibonacci Number]() easy DP 斐波那契数列
34.[467-Unique Substrings in Wraparound String]() medium DP
35.[464-Can I Win]() medium top-down DP, memorization
36.[1147-Longest Chunked Palindrome Decomposition]() hard DP-贪心
37.[474-Ones and Zeroes]() medium DP 类似0-1背包
38.[32-Longest Valid Parentheses]() hard DP 
39.[516-Longest Palindromic Subsequence]() medium DP
40.[72-Edit Distance]() hard DP
41.[115-Distinct Subsequences]() hard DP
42.[91-Decode Ways]() medium DP
43.[486-Predict the Winner]() medium DP
44.[120-Triangle]() medium DP (top-down or down-top)
45.[132-Palindrome Partitioning II]() DP 回文串的最小割+记忆化
46.[174-Dungeon Game]() DP down-top + mem
47.[238-Product of Array Except Self]() DP medium
48.[279-Perfect Squares]() medium DP/BFS/math
49.[1220-Count Vowels Permutation]() hard DP 找到来源位置
50.[1218-Longest Arithmetic Subsequence of Given Difference]() medium DP
51.[312-Burst Balloons]() hard DP 烧气球-DP or divide-conquer
52.[1223-Dice Roll Simulation]() medium DP（需理解题意 dp or dfs+mem）
53.[1235-Maximum Profit in Job Scheduling]() hard DP
54.[403-Frog Jump]() hard DP 青蛙跳



### 单调队列
1. [84-Largest Rectangle in Histogram]() hard    单调队列
2. [85-Maximal Rectangle]() hard  单调队列(参考lc-84)






## 特别专题

### 排列组合系列 (组合的下一个组合或者上一个组合)
077, 031, 040, 046


### 积水系列
1. [011-container-with-most-water](https://leetcode.com/problems/container-with-most-water/submissions/)
2. [042-Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
3. [407-Trapping Rain Water II]() BFS 剥洋葱


```
https://www.cnblogs.com/felixfang/p/3713197.html
观察下就可以发现被水填满后的形状是先升后降的塔形，因此，先遍历一遍找到塔顶，然后分别从两边开始，往塔顶所在位置遍历，水位只会增高不会减小，且一直和最近遇到的最大高度持平，这样知道了实时水位，就可以边遍历边计算面积。
```

### best time to buy and sell stock

1. [121-Best Time to Buy and Sell Stock]() Greedy 只能进行一次交易（贪心降低成本或者提高利润）
2. [122-Best Time to Buy and Sell Stock II]() Greedy 不限次数的交易，只有有盈利就交易
3. [123-Best Time to Buy and Sell Stock III]() DP 最多进行K次交易
4. [188-Best Time to Buy and Sell Stock IV]() DP 极端情况会转化为问题122，常规情况使用123解法
5. [309-Best Time to Buy and Sell Stock with Cooldown]() DP 状态转移
6. [714-Best Time to Buy and Sell Stock with Transaction Fee]() DP or greedy (这个比较好)


### 蓄水池抽样算法(Reservoir Sampling Algorithm)
定理：该算法保证每个元素以 k / n 的概率被选入蓄水池数组。
https://blog.csdn.net/u010150046/article/details/77017145#commentBox

```
/*
	replace elements with gradually decreasing probability
	算法首先创建一个长度为K的数组（蓄水池）用来存放结果，初始化为S的前k个元素，然后从k+1个元素开始迭代直到数组结束
	算法生成一个随机数j∈[1, i]，如果 j ≤ k，那么蓄水池的第 j 个元素被替换为S的第i个元素。
	
	定理：该算法保证每个元素以 k / n 的概率被选入蓄水池数组。

	证明：首先，对于任意的 i，第 i 个元素进入蓄水池的概率为 k / i；而在蓄水池内每个元素被替换的概率为 1 / k; 
	因此在第 i 轮第j个元素被替换的概率为 (k / i ) * (1 / k) = 1 / i。 
	
	接下来用数学归纳法来证明，当循环结束时每个元素进入蓄水池的概率为 k / n.
	假设在 (i-1) 次迭代后，任意一个元素进入 蓄水池的概率为 k / (i-1)。有上面的结论，在第 i 次迭代时，该元素被替换的概率为 1 / i， 那么其不被替换的概率则为 1 - 1/i = (i-1)/i；
	在第i 此迭代后，该元素在蓄水池内的概率为 k / (i-1) * (i-1)/i = k / i. 归纳部分结束。
	
	因此当循环结束时，每个元素进入蓄水池的概率为 k / n. 命题得证。
*/


Problem:
Choose k entries from n numbers. Make sure each number is selected with the probability of k/n
Basic idea:
Choose 1, 2, 3, ..., k first and put them into the reservoir.
For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
Repeat until k+i reaches n
Proof:
For k+i, the probability that it is selected and will replace a number in the reservoir is k/(k+i)
For a number in the reservoir before (let's say X), the probability that it keeps staying in the reservoir is
P(X was in the reservoir last time) × P(X is not replaced by k+i)
= P(X was in the reservoir last time) × (1 - P(k+i is selected and replaces X))
= k/(k+i-1) × （1 - k/(k+i) × 1/k）
= k/(k+i)
When k+i reaches n, the probability of each number staying in the reservoir is k/n


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

### interactive problem [minimize]
1. [843-Guess the Word]() hard 有点概率的意思，最小化思想

### design
1. [341-Flatten Nested List Iterator]() 
2. [705-Design HashSet]() 
3. [706-Design HashMap]()
4. [707-Design Linked List]()
5. [355-Design Twitter]() OOP design
6. [380-Insert Delete GetRandom O(1)]() design data structure
7. [535-Encode and Decode TinyURL]() 系统设计
8. [460-LFU Cache]() design LFU structure
9. [1146-Snapshot Array]() design  how to reduce using space

### two pointers系列
1. 
2. 653

### slide window
[通用模板](https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.)
1. [076-Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) hard slide window
2. [003-longest substring without repeating characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)  
3. [030-Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)
4. [159-Longest Substring with At Most Two Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/)
5. [438-Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
6. [424-Longest Repeating Character Replacement]() medium slide-window
7. [1208-Get Equal Substrings Within Budget]() medium sliding-window
8. [1234-Replace the Substring for Balanced String]() medium sliding-window min-substring

### 回文-Palindromic
1. [647-Palindromic Substrings]() medium  DP or expand from center

### random
1. [384-Shuffle an Array]() medium shuffle
2. [470-Implement Rand10() Using Rand7()]() medium random

### 词频统计 (计数排序 桶排序)
1. [692-Top K Frequent Words]() medium HashMap+BucketSort+TreeSet or HashMap+PriorityQueue
2. [451-Sort Characters By Frequency]() medium HashMap+BucketSort
3. [347-Top K Frequent Elements]() medium PriorityQueue or TreeMap or BucketSort
4. [164-Maximum Gap]() hard 桶排序
5. [220-Contains Duplicate III]() 有桶排序的解法

### 游戏环节-偶吼吼
1. [464-Can I Win]() Top-Down DP（记忆化搜索） 
2. [1140-Stone Game II]() 

### 快慢指针
1. [202-Happy Number]() 快慢指针
2. [287-Find the Duplicate Number]() 快慢指针，找到循环点


### 数组求和问题
1. [209-Minimum Size Subarray Sum]() 全整数数组中求子数组和大于s的最小长度 two-pointers

### KMP
1. [214-Shortest Palindrome]() KMP hard

### merge-sort思想、divide-conquer
1. 逆序对
2. [327-Count of Range Sum]()
3. [395-Longest Substring with At Least K Repeating Characters)[] 根据频次分割 divide-conquer

### 子集&组合系列
1. https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)