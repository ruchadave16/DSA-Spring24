package org.dsa.assignment3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class PriorityQueueTest {
    @Test
    fun isEmpty() {
        val queue: PriorityQueue<String> = PriorityQueue()

        // Ensure queue with nothing initially shows as empty
        assertTrue(queue.isEmpty())

        // Ensure queue with something shows as not empty
        queue.addWithPriority("Hi", 10.0)
        assertFalse(queue.isEmpty())

        // Ensure queue with element removed shows as empty
        queue.next()
        assertTrue(queue.isEmpty())
    }

    @Test
    fun addWithPriority() {
        val queue = PriorityQueue<Int>()

        // Check that adding to an empty queue sets the new value to the min
        queue.addWithPriority(1, 10.0)
        assertEquals(queue.next(), 1)

        // Check that adding differing priorities properly sorts
        queue.addWithPriority(1, 10.0)
        queue.addWithPriority(2, 1.0)
        queue.addWithPriority(3, 8.0)
        assertEquals(queue.queue.get(1), 1)
        assertEquals(queue.queue.get(2), 0)
        assertEquals(queue.queue.get(3), 2)
    }

    @Test
    fun next() {
        val queue = PriorityQueue<Int>()

        // Check that empty queue's next is null
        assertEquals(queue.next(), null)

        // Check that next of single value is it
        queue.addWithPriority(1, 10.0)
        assertEquals(queue.next(), 1)

        // Check that adding differing priorities properly sorts
        queue.addWithPriority(1, 10.0)
        queue.addWithPriority(2, 1.0)
        queue.addWithPriority(3, 8.0)
        queue.addWithPriority(4, 15.0)

        assertEquals(queue.next(), 2)
        assertEquals(queue.queue.get(1), 1)
        assertEquals(queue.queue.get(3), 0)
        assertEquals(queue.queue.get(4), 2)

        assertEquals(queue.next(), 3)
        assertEquals(queue.queue.get(1), 0)
        assertEquals(queue.queue.get(4), 1)

        assertEquals(queue.next(), 1)
        assertEquals(queue.queue.get(4), 0)

        // Ensure removing last sets to empty
        assertEquals(queue.next(), 4)
        assertEquals(queue.isEmpty(), true)
    }

    @Test
    fun adjustPriority() {
        val queue = PriorityQueue<Int>()

        queue.addWithPriority(1, 10.0)
        queue.addWithPriority(2, 1.0)
        queue.addWithPriority(3, 8.0)
        queue.addWithPriority(4, 15.0)

        // Ensure changing priority changes positions in min heap correctly
        queue.adjustPriority(2, 9.0)
        assertEquals(queue.queue.get(1), 1)
        assertEquals(queue.queue.get(2), 2)
        assertEquals(queue.queue.get(3), 0)
        assertEquals(queue.queue.get(4), 3)

        assertEquals(queue.next(), 3)
        assertEquals(queue.queue.get(2), 0)
        assertEquals(queue.queue.get(4), 2)
        assertEquals(queue.queue.get(1), 1)
    }
}