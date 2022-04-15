import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author psj
 * @date 2022/4/15 8:48
 * @File: LFUCache.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/lfu-cache/
//  如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量capacity时，则应该在插入新项之前，移除最不经常使用的项。
//  在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最近最久未使用的键

public class LFUCache {
    // key到val的映射
    HashMap<Integer, Integer> keyToVal;
    // key到freq的映射
    HashMap<Integer, Integer> keyToFreq;
    // freq到key列表的映射
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 最小频次
    int minFreq;
    // LFU的最大缓存
    int cap;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 查询一次就会增加一次使用次数
        increaseFreq(key);
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (this.cap <= 0) {
            return;
        }
        // key已存在就修改相应的val
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            // 修改一次就会增加一次使用次数
            increaseFreq(key);
            return;
        }
        // key不存在急需要插入：
        // 容量已满就删除freq最小的key再插入
        if (this.cap <= keyToVal.size()) {
            removeMinFreqKey();
        }
        // 更新三个HashMap
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        // 插入key后最小freq更新为1
        this.minFreq = 1;
    }

    public void removeMinFreqKey() {
        // 找到最小freq对应的key列表，在该表中找到最先插入的key
        // 最先插入的key就是列表的第一个key
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        int deletedKey = keyList.iterator().next();
        // 更新三个HashMap以及该freq对应的key列表
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            // minFreq没有必要更新为下一个最小的freq，因为后续添加新的key会置为1
            freqToKeys.remove(this.minFreq);
        }
        keyToVal.remove(deletedKey);
        keyToFreq.remove(deletedKey);
    }

    public void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        // 更新keyToFreq和freqToKeys
        keyToFreq.put(key, freq + 1);

        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        // 如果freq对应的key列表空了，就移除freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            // 如果移除的freq是minFreq，则更新minFreq
            // minFreq必定是minFreq+1,因为原来的minFreq为freq，现在在原来freq基础上+1,所以minFreq=minFreq+1
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }

    }
}
