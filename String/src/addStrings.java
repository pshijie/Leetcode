/**
 * @author psj
 * @date 2022/3/21 9:26
 * @File: addStrings.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/add-strings/
// 给定两个字符串形式的非负整数num1和num2，计算它们的和并同样以字符串形式返回。
// 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式

public class addStrings {
    // 从两个字符串的末尾进行反向遍历，重点在补零
    public String addStrings(String num1, String num2) {
        int p = num1.length() - 1;
        int q = num2.length() - 1;

        int add = 0;
        StringBuffer sb = new StringBuffer();
        while (p >= 0 || q >= 0) {
            // 如果p指针还没遍历到num1开头，则去当前位置的值。如果遍历结束，就补零
            int x = p >= 0 ? num1.charAt(p) - '0' : 0;
            // 如果q指针还没遍历到num2开头，则去当前位置的值。如果遍历结束，就补零
            int y = q >= 0 ? num2.charAt(q) - '0' : 0;

            int temp = x + y + add;
            add = temp / 10;
            temp = temp % 10;
            sb.append(temp);
            p--;
            q--;
        }
        if (add == 1) {
            sb.append('1');
        }
        sb.reverse();
        return sb.toString();
    }
}
