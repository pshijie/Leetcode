/**
 * @author psj
 * @date 2022/4/6 9:31
 * @File: add36Strings.java
 * @Software: IntelliJ IDEA
 */
// https://mp.weixin.qq.com/s/XcKQwnwCh5nZsz-DLHJwzQ
// 36进制由0-9，a-z，共36个字符表示。要求按照加法规则计算出任意两个36进制正整数的和

public class add36Strings {
    public String add36Strings(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        StringBuilder result = new StringBuilder();
        int carry = 0;
        while (m >= 0 || n >= 0 || carry != 0) {
            int x = m >= 0 ? getInt(num1.charAt(m)) : 0;
            int y = n >= 0 ? getInt(num2.charAt(n)) : 0;
            int temp = x + y + carry;
            result.append(getChar(temp % 36));
            carry = temp / 36;
            m--;
            n--;
        }
        return result.reverse().toString();
    }

    public int getInt(char ch) {
        if ('0' <= ch && ch <= '9') {
            return ch - '0';
        } else {
            return ch - 'a' + 10;
        }
    }

    public char getChar(int n) {
        if (n <= 9) {
            return (char) (n + 48);
        } else {
            return (char) (n - 10 + 97);
        }
    }

}
