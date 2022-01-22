/**
 * @author psj
 * @date 2022/1/22 16:29
 * @File: numTrees.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/unique-binary-search-trees/
// 给你一个整数n ，求恰由n个节点组成且节点值从1到n互不相同的二叉搜索树有多少种？返回满足题意的二叉搜索树的种数。
// 以n=5为例：
//      1. 以3为根节点，计算左子树{1,2}形成的组合个数
//      2. 计算右子树{4,5}形成的组合个数
//      3. 将上述两个组合个数进行相乘
//      3. 将每个不同的数作为根结点重复1-3步骤
public class numTrees {

    int[][] memo;

    public int numTrees(int n) {
        // 使用方法1
        // return count_1(1, n);

        // 使用方法2
        memo = new int[n + 1][n + 1];
        return count_1(1, n);

    }

    // 方法1：计算闭区间[lo, hi]组成的BST个数（不使用备忘录，存在重叠子问题）
    public static int count_1(int lo, int hi) {
        // base case
        // 当lo > hi时,[lo, hi]为空区间(即对应着空节点null)，也属于一种情况
        if (lo > hi) {
            return 1;
        }

        int res = 0;
        for (int i = lo; i <= hi; i++) {
            // 1. 以i作为根节点的值
            // 2. 计算左子树形成的组合个数
            int leftCount = count_1(lo, i - 1);
            // 3. 计算右子树形成的组合个数
            int rightCount = count_1(i + 1, hi);
            // 4. 将上述两个组合个数进行相乘
            res += leftCount * rightCount;
        }

        return res;
    }

    // 方法2：计算闭区间[lo, hi]组成的BST个数（使用备忘录，消除重叠子问题）
    int count(int lo, int hi) {
        // base case
        // 当lo > hi时,[lo, hi]为空区间(即对应着空节点null)，也属于一种情况
        if (lo > hi) {
            return 1;
        }

        // 1. 查询备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int i = lo; i <= hi; i++) {
            // 2. 以i作为根节点的值
            // 3. 计算左子树形成的组合个数
            int leftCount = count(lo, i - 1);
            // 4. 计算右子树形成的组合个数
            int rightCount = count(i + 1, hi);
            // 5. 将上述两个组合个数进行相乘
            res += leftCount * rightCount;
        }

        // 6. 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }

}
