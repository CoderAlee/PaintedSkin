package org.alee.component.skin.util

/**
 * 发布者
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/2/13
 *
 */
internal class Publisher<Observer : Any> {

    private val mSafeObserverArray: SafeWeakArray<Observer> = SafeWeakArray()

    /**
     * 订阅.
     *
     * @param observer 观察者
     */
    fun subscribe(observer: Observer) {
        mSafeObserverArray.add(observer)
    }

    /**
     * 退订.
     *
     * @param observer 观察者
     */
    fun unsubscribe(observer: Observer) {
        mSafeObserverArray.remove(observer)
    }

    fun isEmpty(): Boolean {
        return mSafeObserverArray.isEmpty()
    }

    fun clear() {
        mSafeObserverArray.clear()
    }

    fun use(): ThrowawayList<Observer> {
        return mSafeObserverArray.use()
    }

    /**
     * 通知观察者
     * @param notifier Function1<Observer, Unit>
     */
    inline fun notifyObservers(notifier: (Observer) -> Unit) {
        if (isEmpty()) {
            return
        }
        use().use {
            it.forEach { observer -> safeCall { notifier(observer) } }
        }
    }
}
