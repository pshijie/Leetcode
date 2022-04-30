import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author psj
 * @date 2022/4/30 8:40
 * @File: Trie.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/implement-trie-prefix-tree/

public class Trie {
    // 方法1:hash表
//    Map<String, Boolean> map;
//
//    public Trie() {
//        map = new HashMap<String, Boolean>();
//    }
//
//    public void insert(String word) {
//        map.put(word, true);
//    }
//
//    public boolean search(String word) {
//        return map.getOrDefault(word, false);
//    }
//
//    public boolean startsWith(String prefix) {
//        Set<String> keys = map.keySet();
//        if (map.isEmpty()){
//            return false;
//        }
//        for (String key : keys) {
//            if (key.length() >= prefix.length()) {
//                String temp = key.substring(0, prefix.length());
//                if (temp.equals(prefix)) {
//                    return true;
//                }
//
//            }
//        }
//        return false;
//    }

    // 方法2:字典树
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        // 26表示26个英文字母
        children = new Trie[26];  // 26个小写字母子节点
        isEnd = false;  // 表示是否存在以该点为结尾的单词
    }

    // 从根节点出发，沿着字符串的字符一直向下走，某个字符不存在就创建，直到走完整个单词
    public void insert(String word) {
        // 相当于新建了一个节点
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            // 如果不存在就创建
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            // 沿着字符串往下走
            node = node.children[index];
        }
        node.isEnd = true;
    }

    // 从根节点出发，沿着字符串的字符一直往下走，若某个字符不存在，直接返回false；
    // 走到最后一个位置，并判断is_end
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        // 最后一个节点不为null，并且最后一个节点为叶子节点
        return node != null && node.isEnd;
    }

    // 从根节点出发，沿着字符串的字符一直往下走，若某个字符不存在，直接返回false；
    // 走到最后一个位置则返回true
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null ? true : false;
    }

    //
    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            // 沿着prefix往下走时没有找到对应字母的节点
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        // 遍历完prefix,就返回最后一个节点
        return node;
    }
}
