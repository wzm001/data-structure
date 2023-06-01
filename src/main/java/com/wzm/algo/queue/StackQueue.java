package com.wzm.algo.queue;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用栈实现线程安全的队列
 *
 * @author wuzhiming@itiger.com
 */
public class StackQueue<T> {

    private final Stack<T> in = new Stack<>();
    private final Stack<T> out = new Stack<>();
    /**
     * 通过读写锁控制 transfer 和其他操作的互斥性，当线程执行 transfer 时，其他操作需要等待
     */
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /**
     * 返回队列头，不出队
     * @return 队列头
     */
    public T peek() {
        if (out.isEmpty()) {
            transfer();
        }
        return out.peek();
    }

    public int size() {
        return in.size() + out.size();
    }

    private void transfer() {
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        try {
            writeLock.lock();
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 出队列
     * @return  元素
     */
    public T poll() {
        if (out.isEmpty()) {
            transfer();
        }
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        try {
            readLock.lock();
            return out.pop();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 入队列
     * @param ele   元素
     */
    public void offer(T ele) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        try {
            readLock.lock();
            in.push(ele);
        } finally {
            readLock.unlock();
        }
    }
}
