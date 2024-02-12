package org.dsa.assignment3

/**
 * A class that represents a priority queue that maintains a queue where the lower the priority value, the sooner
 * the element will be removed from the queue.
 *
 * @param T: a representation of the items in the queue
 */
class PriorityQueue <T> : MinPriorityQueue <T> {
    var queue: MinHeap<T> = MinHeap<T>()
    override fun isEmpty(): Boolean {
        return (queue.size == 0)
    }

    override fun addWithPriority(elem: T, priority: Double) {
        queue.insert(elem, priority)
    }

    override fun next(): T? {
        return queue.getMin()
    }

    override fun adjustPriority(elem: T, newPriority: Double) {
        queue.adjustHeapNumber(elem, newPriority)
    }

}