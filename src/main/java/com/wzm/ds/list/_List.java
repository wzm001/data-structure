package com.wzm.ds.list;

/**
 * 列表
 *
 * @param <E> 包含的元素类型
 * @author wuzhiming@itiger.com
 */
public interface _List<E> extends Iterable<E> {

    // 查询操作

    /**
     * 返回列表包含的元素数量
     * @return 列表包含的元素数量
     */
    int size();

    /**
     * 如果列表不包含任何元素，返回true，等价于列表长度为0
     * @return 如果列表不包含任何元素，返回true
     */
    boolean isEmpty();

    /**
     * 如果列表包含指定元素，返回true
     * @param target    指定元素，可以为null
     * @return  如果列表包含指定元素，返回true
     */
    boolean contains(E target);

    /**
     * 返回指定元素的索引
     * @param target    指定元素，可以为null
     * @return 指定元素的索引
     */
    int indexOf(E target);

    /**
     * 返回指定索引下的元素
     * @param index 索引，从0开始
     * @return 索引下的元素
     */
    E get(int index);

    // 修改操作

    /**
     * 向列表追加元素
     * @param element   元素
     */
    void add(E element);

    /**
     * 修改指定索引下的元素
     * @param index     索引
     * @param element   新元素
     */
    void set(int index, E element);

    /**
     * 移除指定索引下的元素
     * @param index 索引
     * @return  被移除的元素
     */
    E remove(int index);

}
