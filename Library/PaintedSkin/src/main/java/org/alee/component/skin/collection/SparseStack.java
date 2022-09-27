package org.alee.component.skin.collection;

import android.util.SparseArray;

import java.util.Stack;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2021/2/14
 * @description: xxxx
 *
 *********************************************************/
public final class SparseStack<T> {

    private final Stack<String> mKeyStack;

    private final SparseArray<T> mDataArray;


    public SparseStack() {
        mKeyStack = new Stack<>();
        mDataArray = new SparseArray<>();
    }

    public synchronized void toTop(int key) {
        T value = get(key);
        remove(key);
        put(key, value);
    }

    public synchronized T get(int key) {
        return mDataArray.get(key);
    }

    public synchronized void remove(int key) {
        String sKey = String.valueOf(key);
        mKeyStack.remove(sKey);
        mDataArray.delete(key);
    }

    public synchronized void put(int key, T value) {
        String sKey = String.valueOf(key);
        mKeyStack.remove(sKey);
        mKeyStack.push(sKey);
        mDataArray.delete(key);
        mDataArray.put(key, value);
    }

    public synchronized T peek() {
        return get(Integer.parseInt(mKeyStack.peek()));
    }

    public synchronized T pop() {
        int key = Integer.parseInt(mKeyStack.pop());
        T value = get(key);
        mDataArray.delete(key);
        return value;
    }

    public synchronized void clear() {
        mDataArray.clear();
        mKeyStack.clear();
    }

    public synchronized int size() {
        return mKeyStack.size();
    }

    public synchronized T valueAt(int index) {
        return get(Integer.parseInt(mKeyStack.get(index)));
    }

    public synchronized int keyAt(int index) {
        return Integer.parseInt(mKeyStack.get(index));
    }

    public synchronized boolean contains(int key) {
        return mKeyStack.contains(String.valueOf(key));
    }

    public synchronized boolean isEmpty() {
        return mKeyStack.isEmpty();
    }


}
