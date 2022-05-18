import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/5/18 9:35
 * @File: letterCombinations.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
// 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。

public class letterCombinations {
    private String[] letterMap = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };
    public List<String> result;

    public List<String> letterCombinations(String digits) {
        result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        findCombination(digits, new StringBuilder(), 0);
        return result;
    }

    public void findCombination(String digits, StringBuilder letter, int index) {
        // letter保存每次组合的结果
        if (index == digits.length()) {
            result.add(letter.toString());
            return;
        }
        // 获取digits[index]对应的字符串
        int pos = digits.charAt(index) - '0';
        String MapString = letterMap[pos];
        for (int i = 0; i < MapString.length(); i++) {
            // 添加当前字母
            letter.append(MapString.charAt(i));
            findCombination(digits, letter, index + 1);
            // 删除当前字母
            letter.deleteCharAt(letter.length() - 1);
        }
    }
}
