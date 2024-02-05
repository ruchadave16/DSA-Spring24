package com.dsa.assignment2

interface Queue<T> {
    fun enqueue(data: T)

    fun dequeue(): T?

    fun peek(): T?

    fun isEmpty(): Boolean
}

/**
 * An implementation of the Queue structure using a DoubleLinkedList as the base
 */
class MyQueue<T> : Queue<T> {
    var queue: DoubleLinkedList<T> = DoubleLinkedList()

    /**
     * Add data to the end of the queue
     *
     * @param data: A type-T input that represents the data to be added to the end of the stack
     */
    override fun enqueue(data: T) {
        queue.pushBack(data)
    }

    /**
     * Remove data from the front of the queue
     *
     * @return The value at the front of the queue or null if the stack is empty
     */
    override fun dequeue(): T? {
        return queue.popFront()
    }

    /**
     * Show the front of the queue
     *
     * @return The value at the front of the queue or null if the stack is empty
     */
    override fun peek(): T? {
        return queue.peekFront()
    }

    /**
     * Check whether the queue is empty or not
     *
     * @return A boolean True if the stack is empty and False if not
     */
    override fun isEmpty(): Boolean {
        return queue.isEmpty()
    }

}