package org.inner.circle.o2oserver.order.domain

interface OrderStore {
    fun saveOrder(order: Order): Order
}