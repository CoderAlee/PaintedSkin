package org.alee.component.skin.util

import java.io.Closeable

/**
 * 一次性的List
 *
 * <p> 详细描述
 *
 * @author MingYu.Liu
 * created in 2023/2/13
 *
 */
internal class ThrowawayList<T> : ArrayList<T>(), Closeable {
    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     *
     *  As noted in [AutoCloseable.close], cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * *mark* the `Closeable` as closed, prior to throwing
     * the `IOException`.
     *
     * @throws IOException if an I/O error occurs
     */
    override fun close() {
        clear()
    }
}
