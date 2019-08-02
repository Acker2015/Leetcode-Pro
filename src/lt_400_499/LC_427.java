package lt_400_499;


public class LC_427 {
    /**
     * @lc app=leetcode id=427 lang=java
     *
     * [427] Construct Quad Tree
     * recursive
     */

    // Definition for a QuadTree node.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    class Solution {
        private Node build(int[][] grid, int x, int y, int w) {
            Node node = new Node();
            if (w == 1) {
                node.isLeaf = true;
                node.val = grid[x][y]==1;
                return node;
            }
            boolean same = true;
            for (int i = x; i < x + w; ++i) {
                for (int j = y; j < y + w; ++j) {
                    if (grid[i][j] != grid[x][y]) {
                        same = false;
                        break;
                    }
                }
            }
            if (same) {
                node.isLeaf = true;
                node.val = grid[x][y]==1;
            } else {
                node.isLeaf = false;
                node.topLeft = build(grid, x, y, w/2);
                node.topRight = build(grid, x, y + w/2, w/2);
                node.bottomLeft = build(grid, x+w/2,y,w/2);
                node.bottomRight = build(grid, x+w/2, y+w/2, w/2);
            }
            return node;
        }
        public Node construct(int[][] grid) {
            int n = grid.length;
            if (n <= 0) return null;
            return build(grid, 0, 0, n);
        }
    }
}
