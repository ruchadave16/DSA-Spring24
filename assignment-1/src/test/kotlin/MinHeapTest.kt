package org.assignment1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MinHeapTest {
    @Test
    fun get() {
        // Make sure get/indexing function returns correct node
        val heap = MinHeap()
        val node1 = Node(1, 10, null, null)
        val node2 = Node(2, 1, null, null)
        heap.insert(node1)
        heap.insert(node2)
        assertEquals(heap.get(0).freq, node2.freq) // Insert switches order because binary tree
        assertEquals(heap.get(1).symbol, node1.symbol)
    }

    @Test
    fun swap() {
        // Ensure swap function works outside of insert as well
        val heap = MinHeap()
        val node1 = Node(1, 10, null, null)
        val node2 = Node(2, 1, null, null)
        heap.insert(node1)
        heap.insert(node2)
        heap.swap(0, 1)
        assertEquals(heap.get(0).freq, 10)
        assertEquals(heap.get(1).symbol, 2)
    }

    @Test
    fun insert() {
        // Make sure inserting a node of lower frequency places it into the correct position in the binary tree
        val heap = MinHeap()
        val node1 = Node(1, 10, null, null)
        val node2 = Node(2, 1, null, null)
        val node3 = Node(3, 8, null, null)
        heap.insert(node1)
        heap.insert(node2)
        assertEquals(heap[0].freq, 1)
        assertEquals(heap[1].freq, 10)
        heap.insert(node3)
        assertEquals(heap[2].freq, 8)
        assertEquals(heap[1].freq, 10)
    }

    @Test
    fun pop() {
        // Make sure pop removes first item of list
        val heap = MinHeap()
        val node1 = Node(1, 10, null, null)
        val node2 = Node(2, 1, null, null)
        heap.insert(node1)
        heap.insert(node2)
        heap.pop()
        assertEquals(heap.size, 1)
        assertEquals(heap[0].freq, 10)
    }
}