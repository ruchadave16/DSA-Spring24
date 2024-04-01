package org.dsa.assignment2

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class MyQueueTest {
    @Test
    fun enqueue() {
        val list : MyQueue<Int> = MyQueue()

        // Ensure that enqueue adds the data to the front of the queue
        list.enqueue(3)
        assertTrue(list.queue.head?.data == 3)
        assertTrue(list.queue.tail?.data == 3)
        assertFalse(list.queue.isEmpty())

        list.enqueue(-1)
        assertTrue(list.queue.head?.data == 3)
        assertTrue(list.queue.tail?.data == -1)
        assertTrue(list.queue.head?.next == list.queue.tail)
        assertTrue(list.queue.head == list.queue.tail?.prev)
    }

    @Test
    fun dequeue() {
        val list : MyQueue<String> = MyQueue()

        // Ensure that dequeue removes the last value of queue
        list.enqueue("Hi")
        assertTrue(list.dequeue() == "Hi")
        assertTrue(list.queue.head == null)

        list.enqueue("Hi")
        list.enqueue("World")
        assertTrue(list.dequeue() == "Hi")
    }

    @Test
    fun peek() {
        val list : MyQueue<String> = MyQueue()

        // Ensure that peek shows the first value of the queue
        assertTrue(list.peek() == null)
        list.enqueue("c")
        assertTrue(list.peek() == "c")
        list.enqueue("a")
        assertTrue(list.peek() == "c")
        list.dequeue()
        assertTrue(list.peek() == "a")
        list.dequeue()
        assertTrue(list.peek() == null)
    }

    @Test
    fun isEmpty() {
        val list : MyQueue<Char> = MyQueue()

        // Ensure that queue starts empty, is not empty with values in it, and is empty again once they are removed
        assertTrue(list.isEmpty())
        list.enqueue('c')
        assertFalse(list.isEmpty())
        list.dequeue()
        assertTrue(list.isEmpty())
    }
}