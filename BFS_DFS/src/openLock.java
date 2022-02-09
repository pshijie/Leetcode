import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author psj
 * @date 2022/2/9 21:30
 * @File: openLock.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/open-the-lock/
// 有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'。每次旋转都只能旋转一个拨轮的一位数字。
// 锁的初始数字为'0000'，列表deadends包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定
// 字符串target代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回-1

// 假设从"0000"开始，转一次可以穷举出八个密码："1000","9000","0100","0900","0010","0090","0001","0009"
// 所以可以视为每个节点有8个相邻节点，采用BFS框架处理

public class openLock {
    public int openLock(String[] deadends, String target) {
        Queue<String> q = new LinkedList<>();
        // 记录已经穷举过的密码(比如从"0001"转到"0000",如果不记录穷举过的密码，再次转动会从"0000"再次转到"0001")
        Set<String> visited = new HashSet<>();
        // 将deadends数组转为set集合便于判断
        Set<String> deads = new HashSet<>();
        for (String s : deadends) {
            deads.add(s);
        }

        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                // 判断是否在dead数组中
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)){
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)){
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public String minusOne(String cur, int j) {
        char[] ch = cur.toCharArray();
        if (ch[j] == '0') {
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }

    public String plusOne(String cur, int j) {
        char[] ch = cur.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }

        return new String(ch);
    }
}
