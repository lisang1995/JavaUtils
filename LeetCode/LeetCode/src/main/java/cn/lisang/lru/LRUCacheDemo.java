package cn.lisang.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author lisang
 * @Date 2020/11/17 21:20
 * @Description: LinkedHashMap解决LRU算法
 * @Version 1.0
 */
public class LRUCacheDemo<k, v> extends LinkedHashMap {

    //缓存坑位
    private int capacity;

    /**
     * <b>true</b> for access-order 访问顺序
     * <b>false</b> for insertion-order 插入顺序
     * @param capacity
     */
    public LRUCacheDemo(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCache = new LRUCacheDemo(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        System.out.println(lruCache.keySet());
        lruCache.put(4, 4);
        System.out.println(lruCache.keySet());
        lruCache.put(3, 3);
        System.out.println(lruCache.keySet());
        lruCache.put(5, 5);
        System.out.println(lruCache.keySet());
    }
}
