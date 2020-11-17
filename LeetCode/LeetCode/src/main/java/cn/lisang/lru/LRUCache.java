package cn.lisang.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lisang
 * @Date 2020/11/17 21:19
 * @Description: 手写LRU算法
 * @Version 1.0
 */
public class LRUCache {
    // map 负责查找，构建一个双向链表，它里面安装的是一个node节点，作为数据载体

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        System.out.println(lruCache.map.keySet());
        lruCache.put(4, 4);
        System.out.println(lruCache.map.keySet());
        lruCache.put(3, 3);
        System.out.println(lruCache.map.keySet());
        lruCache.put(5, 5);
        System.out.println(lruCache.map.keySet());
    }

    // 1.构建一个node节点作为数据节点
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }


    }

    // 2.构建一个虚拟的双向链表，里面安装node
    class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        //构造方法
        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        //添加到头
        public void addHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        //删除节点
        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        //最后一个节点
        public Node getLastNode() {
            return tail.prev;
        }
    }

    private int capacity;

    Map<Integer, Node<Integer, Integer>> map;

    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRUCache(int capacity) {
        //坑位
        this.capacity = capacity;
        //查找
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }

    void put(int key, int value) {
        //update
        if (map.containsKey(key)) {
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        } else {
            //没有位置
            if (map.size() == capacity) {
                Node<Integer, Integer> lastNode = doubleLinkedList.getLastNode();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }
    }
}
