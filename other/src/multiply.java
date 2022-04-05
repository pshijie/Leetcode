/**
 * @author psj
 * @date 2022/4/5 8:50
 * @File: multiply.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/multiply-strings/
// 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
// 注意:不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

public class multiply {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        // 将num2中的数字依次和num1中的数字进行相乘
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                // num1[i]和num2[j]的乘积对应的就是res[i+j]和res[i+j+1]这两个位置
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        // 除去结果最前面的0
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();

    }

}
