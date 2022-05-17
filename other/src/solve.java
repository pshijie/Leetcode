/**
 * @author psj
 * @date 2022/5/17 9:34
 * @File: solve.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/surrounded-regions/
// 给你一个mxn的矩阵board，由若干字符'X'和'O'，找到所有被'X'围绕的区域，并将这些区域里所有的'O'用'X'填充

public class solve {
    // 方法1：并查集
    // 假设有两个集合，现在需要将两个集合进行合并:
    //    1          4                1
    //  /   \      /   \   ->      /   \  \
    // 2    3     5     6         2     3  4
    //                                   /   \
    //                                  5     6
    class UF {
        // 记录连通分类的个数
        private int count;
        // 存储每个节点的父节点
        private int[] parent;

        // n为图中节点的个数
        public UF(int n) {
            this.count = n;
            parent = new int[n];
            // 初始时每个节点的父节点为自己
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // 将点p和点q连通
        public void union(int p, int q) {
            int rootP = find(p);  // 找到p节点所在树的根节点
            int rootQ = find(q);  // 找到q节点所在树的根节点
            if (rootP == rootQ) {
                return;
            }
            parent[rootQ] = rootP;  // 将两个根节点相连，并将其中一个作为另一个的父节点
            count--;  // 将两个树连接会使得连通分量减少一个
        }

        // 找到当前节点x的所在树的根节点,并将其父节点直接指向根节点(这样查找的时间为O(1))
        public int find(int x) {
            // 方式1:这种方式并没有修改x的父节点，只不过是一直向上找根节点
            // 下次找根节点依旧需要完成一次相同的遍历
//            while (parent[x] != x){
//                x = parent[x];
//            }
//            return x;
            // 方式2:修改x节点及其沿途路径上的所有节点，将它们全部指向该树的根节点
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // 判断p和q是否连通
        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;  // 判断是否在同一个子树即可
        }

        // 返回图中连通分量数
        public int count() {
            return count;
        }
    }

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        // 二维坐标(x,y)可以转换为x*n+y
        UF uf = new UF(m * n + 1);  // m*n相等于把board拉直，加上1是给dummy的位置
        int dummy = m * n;
        // 将首列和末列的0与dummy相连
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                uf.union(i * n, dummy);  // i*n = i*n+0,因为在第一列所以y=0
            }
            if (board[i][n - 1] == 'O') {
                uf.union(i * n + n - 1, dummy);  // 因为在末列所以y=n-1
            }
        }
        // 将首行和末行的0与dummy相连
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                uf.union(i, dummy);  // 第一行的值即前n个值
            }
            if (board[m - 1][i] == 'O') {
                uf.union(n * (m - 1) + i, dummy);  // n*(m-1)表示末行的第一个值
            }
        }

        // 方向数组
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    // 将当前为O的点和四周的O相连(如果四周的O已经属于dummy的那颗树,则当前节点也会被归并到那个树)
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (board[x][y] == 'O') {
                            uf.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }
        // 不和dummy相连的O都是要被替换的
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // 方法2:DFS
    // 1.先将和四周的O点及其相连的O变为B,形成新的board
    // 2.遍历新board,找出剩余的O,并将其修改为X
    // 3.将B变回为O
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve_DFS(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        // 处理第一行和末行
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                dfs(0, i, board, row, col);
            }
            if (board[row - 1][i] == 'O') {
                dfs(row - 1, i, board, row, col);
            }
        }
        // 处理第一列和末列
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                dfs(i, 0, board, row, col);
            }
            if (board[i][col - 1] == 'O') {
                dfs(i, col - 1, board, row, col);
            }
        }
        // 完成步骤2和3
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // 将当前O点变为B，并将其相连点也变为B
    public void dfs(int i, int j, char[][] board, int row, int col) {
        board[i][j] = 'B';
        for (int[] dir : dirs) {
            int tmp_i = dir[0] + i;
            int tmp_j = dir[1] + j;
            if (tmp_i < 0 || tmp_i >= row || tmp_j < 0 || tmp_j >= col || board[tmp_i][tmp_j] != 'O') {
                continue;
            }
            // 将周围的O点也变为B
            dfs(tmp_i, tmp_j, board, row, col);
        }
    }
}
