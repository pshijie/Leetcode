import java.util.HashMap;
import java.util.Map;

/**
 * @author psj
 * @date 2022/5/23 8:50
 * @File: fractionToDecimal.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/fraction-to-recurring-decimal/
// 给定两个整数，分别表示分数的分子numerator 和分母denominator，以字符串形式返回小数
// 如果小数部分为循环小数，则将循环的部分括在括号内

public class fractionToDecimal {
    // 进行除法操作过程中如果出现多次余数是重复的,就可以认定重复的那一部分是循环部分
    public String fractionToDecimal(int numerator, int denominator) {
        // 如果分子为2^31,分母为1,相除后结果为2^31,而int型的大小在[-2^31,2^31-1]范围
        long a = numerator;
        long b = denominator;
        // 如果可以整除
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        StringBuilder sb = new StringBuilder();
        // 如果有一个为负数
        if (a * b < 0) {
            sb.append('-');
        }
        a = Math.abs(a);
        b = Math.abs(b);
        // 计算整数部分
        sb.append(String.valueOf(a / b) + ".");
        // 将余数赋值给a
        a %= b;
        // 用于记录当前余数所在位置,key:当前余数 value:所在位置
        Map<Long, Integer> map = new HashMap<>();
        while (a != 0) {
            // 记录下当前余数所在位置后,等再次出现该余数，就说明开始出现循环
            map.put(a, sb.length());
            // 模拟除法运算的过程中在余数后面加0的操作
            a *= 10;
            sb.append(a / b);
            a %= b;
            // 如果当前余数出现过,则上一次出现的位置到当前位置(就是sb的末尾)为循环部分
            if (map.containsKey(a)) {
                int lastIndex = map.get(a);
                return String.format("%s(%s)", sb.substring(0, lastIndex), sb.substring(lastIndex));
            }
        }
        return sb.toString();

    }
}
