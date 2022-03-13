/**
 * @author psj
 * @date 2022/3/13 8:51
 * @File: LRUCache.java
 * @Software: IntelliJ IDEA
 */
// https://leetcode-cn.com/problems/lru-cache/
// LRUCache(int capacity):以正整数作为容量capacity初始化LRU缓存
// int get(int key):如果关键字key存在于缓存中，则返回关键字的值，否则返回-1
// void put(int key, int value):如果关键字key已经存在，则变更其数据值value；
//      如果不存在，则向缓存中插入该组key-value。
//      如果插入操作导致关键字数量超过capacity，则应该逐出最久未使用(执行了get(1)和put(1,'a')就算1被使用了)的关键字

import java.util.HashMap;

/**
 * 插入和删除操作时间复杂度为O(1)时，就要考虑到使用链表
 */
// 关键点：构建一个HashMap和双向链表，并建立map对链表中节点的映射
//        之所以要建立Map是为了快速查找到key值对应的节点，实现get方法
public class LRUCache {
    // 构建key->Node<key,val>之间的映射
    private HashMap<Integer, Node> map;

    private DoubleList cache;

    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将该key值对应的节点提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // 需要删除原先的数据
            deleteKey(key);
            addRecently(key, value);
            return;
        }

        if (cap == cache.size()) {
            // 删除最久未使用的元素，腾出空间
            removeLeastRecently();
        }

        // 添加新元素为最近使用的元素
        addRecently(key, value);

    }

    // 将某个key提升为最近使用的
    public void makeRecently(int key) {
        Node node = map.get(key);
        // 1.从链表中删除该节点
        cache.remove(node);
        // 2.重新插入队尾
        cache.addLast(node);
    }

    // 添加最近使用的元素
    public void addRecently(int key, int value) {
        Node node = new Node(key, value);
        // 链表尾部为最近使用的元素
        cache.addLast(node);
        // 同时在map中添加key的映射
        map.put(key, node);
    }

    // 删除某一个键为key的节点
    public void deleteKey(int key) {
        Node node = map.get(key);
        // 从链表中删除
        cache.remove(node);
        // 同时删除map中的映射
        map.remove(key);
    }

    // 删除最久未使用元素
    public void removeLeastRecently() {
        // 链表头部为最久未使用的节点
        Node deleteNode = cache.removeFirst();
        // 同时在map中删除该节点的key
        int deleteKey = deleteNode.key;
        map.remove(deleteKey);
    }

    // 双向链表节点
    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 构建的双向链表（靠近尾部的节点是最近使用的，靠近头部的节点是最久未使用的）
    class DoubleList {
        // 头尾虚节点
        private Node head, tail;
        // 链表节点数量
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表尾部添加节点x(即在tail节点前添加x)
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        // 删除链表中的x节点
        public void remove(Node x) {
            // x的两个指针不需要操作
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // 删除链表中的第一个节点，并返回该节点
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        // 返回链表长度
        public int size() {
            return size;
        }
    }
}
