import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/5/24 8:52
 * @File: convert.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/zigzag-conversion/
// 将一个给定字符串s根据给定的行数numRows，以从上往下、从左到右进行Z字形排列
// 比如输入的字符串为:LEETCOD
// Z字形排列后为:
// L   C
// E T O
// E   D
// 按行进行遍历后输出字符串为LC + ETO + ED = LCETOED

public class convert {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        // 当前行索引(例子中LC就属于第一行的数据,依次类推)
        int i = 0;
        // 判断行索引是向上还是向下移动
        int flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            // 当前索引在第一行或者最后一行时，需要将遍历方向进行反向
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            // 当flag为负时表示行索引向上移动(即将当前字符放入上一行)
            // 当flag为正时表示行索引向下移动(即将当前字符放入下一行)
            i += flag;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}
