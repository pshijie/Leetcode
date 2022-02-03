import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/2/3 21:06
 * @File: permute.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/permutations/
// 给定一个不含重复数字的数组nums ，返回其所有可能的全排列
// 全排列树如下：
//                    *
//              1/   2|   \3
//              #     ~    #
//            2/ 3\ 1/ 3\1/ 2\
//            #   # #   # #   #
//           3|  2|3|  1|2|  1|
//            #   # #   # #   #
// 每个节点有两个属性：；路径和选择列表，以~节点为例，路径为[2]，选择列表为[1,3]; *节点的路径为[]，选择列表为[1,2,3]。
// *节点选择2到~节点，~节点撤销选择回到*节点
/**
 * 多叉树的遍历框架：
 *     void traverse(TreeNode root){
 *         for(TreeNode child:root.children){
 *             // 前序遍历需要的操作
 *             traverse(child)
 *             // 后序遍历需要的操作
 *         }
 *     }
 * 回溯算法框架:
 *      void backtrace(路径，选择列表){
 *          for 选择 in 选择列表：
 *              # 1.做出选择(前序遍历需要的操作)
 *              1.1 将该选择从选择列表中移除
 *              1.2 路径.add(选择)
 *
 *              # 2.进入下一层决策树
 *              backtrace(路径，选择列表)
 *
 *              # 3.撤销选择
 *              3.1 路径.remove(选择)
 *              3.2 该选择加入到选择列表中
 *
 * tips:
 *      1. track是一个外部引用，track的内容在不断修改，如果不复制直接加入，之前加入的内容也会随之改变
 *      2. 回溯算法不同于动态规划存在重叠子问题可以优化，该算法就是纯暴力穷尽
 */
public class permute {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        // track为路径路径，nums为当前选择列表
        LinkedList<Integer> track = new LinkedList<>();
        backtrace(nums, track);
        return res;
    }

    // nums为当前选择列表，track为当前路径
    public void backtrace(int[] nums, LinkedList<Integer> track){
        // 结束条件
        if (track.size() == nums.length){
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 路径中已经含有的数字不会加入到选择列表中
            if (track.contains(nums[i])){
                continue;
            }
            // 做选择
            track.add(nums[i]);

            // 进入下一层决策树
            backtrace(nums, track);

            // 撤销选择
            track.removeLast();
        }
    }

}
