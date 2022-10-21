package com.wzm.ds.hash;

import com.wzm.ds.list.Lists;
import com.wzm.ds.list._LinkedList;
import com.wzm.ds.list._List;

import java.util.Objects;

/**
 * 拉链法实现的散列表
 *
 * @author wuzhiming@itiger.com
 */
public class LinkedHashTable<K, V> implements _HashTable<K, V> {

    private static final int TABLE_SIZE = 1000;
    /*算法演示用，暂时先固定数组长度*/
    @SuppressWarnings("unchecked")
    private final _List<Entry<K, V>>[] table = (_List<Entry<K, V>>[]) new _List[TABLE_SIZE];
    private int size;

    private int index(K key) {
        return Objects.hash(key) % TABLE_SIZE;
    }

    @Override
    public V get(K k) {
        _List<Entry<K, V>> list = table[index(k)];
        if (list == null || list.isEmpty()) return null;
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.key(), k)) return entry.value();
        }
        return null;
    }

    @Override
    public boolean contains(K k) {
        _List<Entry<K, V>> list = table[index(k)];
        if (list == null || list.isEmpty()) return false;
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.key(), k)) return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void put(K k, V v) {
        int index = index(k);
        _List<Entry<K, V>> list = table[index];
        Entry<K, V> entry = new Node<>(k, v);
        if (list == null) {
            list = Lists.newLinkedList(new Entry[] {entry});
            table[index] = list;
            size++;
        } else {
            int i = list.indexOf(entry);
            if (i > -1) {
                list.set(i, entry);
            } else {
                list.add(entry);
                size++;
            }
        }
    }

    @Override
    public boolean remove(K k) {
        if (!contains(k)) return false;
        _List<Entry<K, V>> list = table[index(k)];
        Entry<K, V> entry = new Node<>(k, null);
        list.remove(list.indexOf(entry));
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Empty";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            _List<Entry<K, V>> entries = table[i];
            if (entries != null && !entries.isEmpty()) {
                sb.append(i).append(": ").append(entries).append("\n");
            }
        }
        return sb.toString();
    }

    public final static class Node<K, V> extends _LinkedList.ListNode<Node<K, V>> implements Entry<K, V> {

        final K key;
        final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
