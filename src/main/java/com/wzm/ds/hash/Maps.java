package com.wzm.ds.hash;

/**
 * 散列表工具类
 *
 * @author wuzhiming@itiger.com
 */
public final class Maps {

    public static <K, V> _HashTable<K, V> newHashTable() {
        return new LinkedHashTable<>();
    }

    private Maps() {}
}
