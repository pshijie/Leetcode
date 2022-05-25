/**
 * @author psj
 * @date 2022/5/25 9:11
 * @File: titleToNumber.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/excel-sheet-column-number/
// 给你一个字符串columnTitle，表示Excel表格中的列名称。返回该列名称对应的列序号
// A -> 1  B -> 2...Z -> 26  AA -> 27  AB -> 28

public class titleToNumber {
    // 以ZY为例，Z的值为26，Y的值为25，则结果为26 * 26 + 25=701
    // 本质就是26进制转换为10进制
    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int result = 0;
        for (int i = 0; i < n; i++) {
            int num = columnTitle.charAt(i) - 'A' + 1;
            result = result * 26 + num;
        }
        return result;
    }
}
