import java.util.ArrayList;
import java.util.List;

/**
 * @author psj
 * @date 2022/4/22 9:08
 * @File: simplifyPath.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/simplify-path/

public class simplifyPath {
    public String simplifyPath(String path) {
        // 存入目录
        List<String> dirList = new ArrayList<>();
        int len = path.length();
        for (int i = 0; i < len; ) {
            // 当前字符是/则跳过
            if (path.charAt(i) == '/') {
                i++;
            } else {
                // 记录当前位置
                int nowIndex = i;
                // 直到找到/即停止遍历
                while (i < len && path.charAt(i) != '/') {
                    i++;
                }
                // 截取两个/之间的字符串
                String dir = path.substring(nowIndex, i);
                // 截取的字符串为..，说明要返回上一级
                if ("..".equals(dir) && !dirList.isEmpty()) {
                    // 返回上一级并删除最后一个目录
                    dirList.remove(dirList.size() - 1);
                    // 截取的字符串为目录则加入
                } else if (!"..".equals(dir) && !".".equals(dir)) {
                    dirList.add(dir);
                }
                // 如果是"."就不用管
            }

        }
        // 说明仅包含空目录
        if (dirList.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : dirList) {
            sb.append("/" + s);
        }
        return sb.toString();
    }
}
