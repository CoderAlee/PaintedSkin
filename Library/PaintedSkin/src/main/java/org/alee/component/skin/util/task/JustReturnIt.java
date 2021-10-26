package org.alee.component.skin.util.task;

/**
 * @author PG.Xie
 * created on 2020/4/26
 */
interface JustReturnIt<P, R> {
    R onWith(P params);
}
