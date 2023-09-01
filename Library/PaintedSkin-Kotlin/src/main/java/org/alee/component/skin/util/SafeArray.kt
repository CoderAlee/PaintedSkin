package org.alee.component.skin.util

/**
 * 摘要
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/8/31
 *
 */
internal class SafeArray<T : Any> {
    /**
     * 注意这里使用 有序的Map而非SparseArray 是因为SparseArray是按照key 至进行排序的，无法保证先put 的一定在前。
     */
    private val mReferenceArray: MutableMap<Int, T> by lazy { LinkedHashMap() }

    /**
     * [ListThreadLocal]
     */
    private val mThreadLocal: ListThreadLocal<T> by lazy { ListThreadLocal() }

    /**
     * 添加一个元素
     *
     * @param element 元素
     */
    fun add(element: T) {
        add(element.memoryAddress, element)
    }

    /**
     * 添加一个元素
     *
     * @param key 元素的唯一id
     * @param element  元素
     */
    fun add(key: Int, element: T) {
        synchronized(mReferenceArray) {
            if (mReferenceArray.keys.contains(key)) {
                return
            }
            mReferenceArray.put(key, element)
        }
    }

    /**
     * 移除一个元素
     *
     * @param element 被移除的元素
     */
    fun remove(element: T) {
        remove(element.memoryAddress)
    }

    /**
     * 根据id 移除一个元素
     *
     * @param key 元素的唯一id
     */
    fun remove(key: Int) {
        synchronized(mReferenceArray) { mReferenceArray.remove(key) }
    }

    fun isEmpty(): Boolean {
        return mReferenceArray.isEmpty()
    }

    fun get(key: Int): T? {
        synchronized(mReferenceArray) {
            return mReferenceArray[key]
        }
    }

    fun contains(element: T): Boolean {
        synchronized(mReferenceArray) {
            return mReferenceArray.keys.contains(element.memoryAddress)
        }
    }

    /**
     * 使用当前存储的所有有效元素
     *
     * @return [ThrowawayList]
     */
    fun use(): ThrowawayList<T> {
        if (isEmpty()) {
            return ThrowawayList()
        }
        val tempList = mThreadLocal.get() ?: ThrowawayList()
        synchronized(mReferenceArray) {
            tempList.clear()
            tempList.addAll(mReferenceArray.map { it.value })
        }
        return tempList
    }

    /**
     * 清空所有元素
     */
    fun clear() {
        synchronized(mReferenceArray) { mReferenceArray.clear() }
        mThreadLocal.gc()
    }
}
