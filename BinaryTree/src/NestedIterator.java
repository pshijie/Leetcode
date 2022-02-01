import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author psj
 * @date 2022/1/30 23:13
 * @File: NestedIterator.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/flatten-nested-list-iterator/
// 给你一个嵌套的整数列表nestedList。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
// 假设列表为[1,[1,2]]，则对应树的结构为：
//                  root
//                  /  \
//                 1   *
//                    / \
//                   1   2

interface NestedInteger {
    // 如果其中存储的是一个整数，则返回true，否则返回false
    public boolean isInteger();

    // 如果其中存储的是一个整数，则返回该整数，否则返回null
    public Integer getInteger();

    // 如果其中存储的是一个列表，则返回该列表，否则返回null
    public List<NestedInteger> getList();
}

// 其实NestedInteger就是N叉树节点类，题目含义就是遍历该N叉树的叶子节点
//public class NestedInteger{
//    private Integer val;
//    private List<NestedInteger> list;
//
//    public boolean isInteger(){
//        return val != null;
//    }
//
//    public Integer getInteger(){
//        return this.val;
//    }
//
//    public List<NestedInteger> getList(){
//        return this.list;
//    }
//}
public class NestedIterator implements Iterator<Integer> {

    private  Iterator<Integer> it;

    // 构造器输入一个NestedInteger列表
    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> result = new LinkedList<>();
        for (NestedInteger node : nestedList){
            // 以每个节点为根遍历
            traverse(node, result);
        }

        this.it = result.iterator();
    }

    // 返回下一个整数
    @Override
    public Integer next() {
        return it.next();
    }

    // 判断是否还有下一个整数
    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    // 遍历以root为根的N叉树，将叶子节点的值加入result列表
    public void traverse(NestedInteger root, List<Integer> result){
        // 如果判断为整数，说明到达叶子节点
        if (root.isInteger()){
            result.add(root.getInteger());
            return;
        }

        for (NestedInteger child : root.getList()){
            traverse(child, result);
        }
    }
}
