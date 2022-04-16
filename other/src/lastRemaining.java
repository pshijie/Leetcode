import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/4/16 9:26
 * @File: lastRemaining.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
// 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字(删除后从下一个数字开始计数)。求出这个圆圈里剩下的最后一个数字
// 约瑟夫环问题

public class lastRemaining {
    // 方法1：链表(超时)
    // 在链表后面不断添加未被删除的数
    // 比如原始链表为1->2->3->4，m=2,即第一次需要把2删除
    // 所以把2前面的数加到末尾，即2->3->4->1，此时再删除2，整好满足排成圆圈这个特点
    public int lastRemaining_listAdd(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        while (list.size() > 1) {
            for (int i = 0; i < m; i++) {
                // 依次把需要删除元素前面的数字加到链表末尾
                if (i != m - 1) {
                    list.add(list.get(0));
                }
                list.remove(0);
            }
        }
        return list.get(0);
    }

    // 方法2：链表改进
    // 第一次删除的元素下标为c=(m-1)%n，之后每次删除元素的下标为(c+m-1)%list.size()
    // 比如原始链表为1->2->3->4，m=2,即第一次删除的下标c=1,
    // 第二次删除的下标为(1+2-1)%3=2(这个2指的是链表1->3->4中的下标2，即元素4)
    public int lastRemaining_list(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int c = (m - 1) % n;
        while (list.size() > 1) {
            list.remove(c);
            c = (c + m - 1) % list.size();
        }
        return list.get(0);
    }
}
