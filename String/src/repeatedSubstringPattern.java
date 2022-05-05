/**
 * @author psj
 * @date 2022/5/5 9:25
 * @File: repeatedSubstringPattern.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/repeated-substring-pattern/
// 给定一个非空的字符串s ，检查是否可以通过由它的一个子串重复多次构成

public class repeatedSubstringPattern {
    // 枚举
    // 假设子串s'的长度为n'，s的长度为n
    // 1.n一定是n'的倍数
    // 2.s'一定是s的前缀
    // 3.对于任意的i属于[n',n)，有s[i]=s[i-n']
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        // 简化遍历次数,因为子串至少重复一次,所以n'不会大于n的一半
        for (int i = 1; i * 2 <= n; i++) {  // i表示子串的长度
            // 判断条件1
            if (n % i == 0) {
                boolean match = true;
                // 默认前i个字符构成的子串为前缀串(满足条件2)，从第i+1个字符开始遍历判断
                for (int j = i; j < n; j++) {
                    // 判断条件3
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
}
