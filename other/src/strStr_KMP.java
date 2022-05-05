/**
 * @author psj
 * @date 2022/3/9 8:58
 * @File: strStr_KMP.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/implement-strstr/
// 给你两个字符串haystack和needle ，请你在haystack 字符串中找出needle字符串出现的第一个位置(下标从0开始)。如果不存在，则返回 -1

public class strStr_KMP {
    // 方法1：暴力，时间复杂度为O(mn)，空间复杂度为O(1)
    // 缺点：假如txt="aaacaaab",pat="aaab"
    // 第一轮：    aaab      aaacaaab
    //               i      j
    //      发现不匹配时，i指针和j指针都需要重新移动
    //            aaab      aaacaaab
    //            i          j
    //      实际上并不需要回退
    public int strStr_force(String txt, String pat) {
        int m = pat.length();
        int n = txt.length();
        for (int i = 0; i < n - m + 1; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    // 方法2：KMP算法，假设txt="aabaacdcf" pat="aabaaadcf"
    //      1.首先还是定义两个指针i和j，然后根据字母是否匹配移动指针
    //        aabaacdcf    aabaadcf
    //             i            j
    //      2.移动到上述位置后发现不匹配，此时i指针是不需要移动的，只需要移动j指针，该移动到哪里？最长相等前后缀的下一个位置
    //        2.1 确保前缀和后缀是相等的，然后找出最长且相等的前后缀：
    //            对于字符串aa，前缀为a，可以找到相同的后缀(a)
    //            对于字符串aab，前缀为aa，没有相同的后缀(ab)
    //            对于字符串aaba，前缀为aab，找不到相同的后缀(aba)
    //            ...
    //            最终求得每个子串对于的最长相等前后缀的长度
    //            注意：之所以强调是最长，是因为对于像aabaa，相等的前后缀有a和aa两种
    //        2.2 为什么是移动到最长相等前后缀的下一个位置：
    //            此时的i指针指向要匹配的位置，i指针的前两个字符为aa(即之前匹配成功部分pat的后缀)，
    //            将j指针指向和后缀相同的前缀的下一个位置，也就确保了前缀的部分和i指针的前两个字符匹配，这样j指针就不要要每次移动回到pat的开头
    //      3.移动完j指针后，继续重复步骤1-3
    //

    // 定义next数组，next数组表示主串和模式串的某一个字符不匹配的时候，**模式串**要回退的位置
    // 假设模式串p=abaabcac,j为字符下标:
    //          j: 0 1 2 3 4 5
    //          p: a b c a b f
    //    next[j]: 0 0 0 1 2 0
    //    比如next[4]=2的计算方式为next[0..4]=abaab的最长前后缀为ab(注意前后缀只局限于当前字符串的子集，即不能将abaab认为是前缀或后缀)，长度为2
    //    当模型串匹配到f时，发现和主串的字符不一致，此时将指针指向next[5-1]=next[4]=2(即从字符c开始比较即可)
    // 求next数组的伪代码：
    //      1.i定义为后缀末尾，j定义为前缀末尾
    //      2.初始化j=0,i在for循环中初始化为1，并且next[0]=0(只有一个字母时不存在前后缀的)
    //      3.for(i=1;i<pat.length;i++){
    //          // 其实比较前后缀不就是和比较模式串和主串，这里把前缀视为模式串，后缀视为主串进行操作
    //          // 之所以使用while循环而不是if是因为回退完一步后模式串指向的字符和主串指向的字符还是不匹配，继续回退操作，直到找到相同的字符或者模式串指向第一个字符
    //          // 当pat[i]!=pat[j]时
    //          while (j > 0 && pat[i] != pat[j]) {
    //                 j = next[j - 1];//进行回退操作，这里将j理解为前缀末尾；
    //          }
    //          // 当pat[i]=pat[j]时,因为j不止表示为前缀末尾的位置，也表示为最长相等前后缀的长度
    //          if (needle.charAt(j) == needle.charAt(i)) {
    //                j++;
    //          }
    //          next[i] = j;  // 这里将j理解为最长相等前后缀的长度
    //      }

    public int strStr(String haystack, String needle) {
        int needleLength = needle.length();
        // 当needle是空字符串时，返回0
        if (needleLength == 0) {
            return 0;
        }
        // next[0]默认为0
        int[] next = new int[needleLength];
        // 定义好next数组
        for (int right = 1, left = 0; right < needleLength; right++) {
            // 在for循环中初始化指针right为1，left=0,开始计算next数组，right始终在left指针的后面
            while (left > 0 && needle.charAt(left) != needle.charAt(right)) {
                // 如果不相等就让left指针回退，到0时就停止回退
                left = next[left - 1];//进行回退操作；
            }
            if (needle.charAt(left) == needle.charAt(right)) {
                left++;
            }
            next[right] = left;

        }

        for (int i = 0, j = 0; i < haystack.length(); i++) {
            // 回退操作
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 返回主串中匹配模式串的第一个位置
            if (j == needleLength) return i - needleLength + 1;
        }

        return -1;
    }

}

