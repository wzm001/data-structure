package com.wzm.ds.hash;

/**
 * 哈希表
 *
 * @author wuzhiming@itiger.com
 */
public interface _HashTable<K, V> {

    /**
     * 获取数据
     * @param k   key
     * @return value
     */
    V get(K k);

    /**
     * 是否包含key
     * @param k key
     * @return 是否包含key
     */
    boolean contains(K k);

    /**
     * 是否为空
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 获取元素个数
     * @return  元素个数
     */
    int size();

    // 修改操作

    /**
     * 添加数据
     * @param k key
     * @param v value
     */
    void put(K k, V v);

    /**
     * 移除指定的Key
     * @param k key
     * @return  是否移除过元素
     */
    boolean remove(K k);

    interface Entry<K, V> {
        K key();
        V value();
    }
}
