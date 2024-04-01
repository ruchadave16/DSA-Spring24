package org.dsa.assignment6

import org.dsa.assignment2.DoubleLinkedList
import org.dsa.assignment2.DoubleLinkedListNode

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test
class AssociativeArrayTest {
    @Test
    fun set() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()

        // Check that initially 1 bucket
        assertTrue(arr.buckets.size == 1)

        // Set a few values to be added to list and ensure list looks correct
        arr.set(1, 1)
        assertTrue(arr.buckets[0].head == DoubleLinkedListNode(Pair(1, 1), null, null))

        arr.set(2, 1)
        assertTrue(arr.buckets[0].head == DoubleLinkedListNode(Pair(2, 1), null, null))
        assertTrue(arr.buckets[1].head == DoubleLinkedListNode(Pair(1, 1), null, null))

        // Ensure changing values for a key works
        arr.set(1, 4)
        assertTrue(arr.buckets[1].head == DoubleLinkedListNode(Pair(1, 4), null, null))
        assertTrue(arr.buckets[2].head == DoubleLinkedListNode(Pair(2, 1), null, null))
    }

    @Test
    fun contains() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()
        arr.set(1, 1)
        arr.set(3, 3)
        arr.set(4, 5)

        // Ensure contains works for keys in and not in the map
        assertTrue(arr.contains(1))
        assertTrue(arr.contains(4))
        assertFalse(arr.contains(5))
    }

    @Test
    fun get() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()

        // Ensure empty list is null
        assertTrue(arr.get(1) == null)

        // Ensure list with keys gets correct value
        arr.set(1, 1)
        arr.set(3, 3)
        arr.set(4, 5)
        assertTrue(arr.get(1) == 1)
        assertTrue(arr.get(4) == 5)

        // Ensure keys that aren't there return null
        assertTrue(arr.get(5) == null)
    }

    @Test
    fun remove() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()
        arr.set(1, 1)
        arr.set(3, 3)
        arr.set(4, 5)

        // Ensure removing key there edits both values and keys list
        assertTrue(arr.remove(3))
        assertTrue(arr.buckets[0].head == DoubleLinkedListNode(Pair(4, 5), null, null))
        assertTrue(arr.buckets[1].head == DoubleLinkedListNode(Pair(1, 1), null, null))
        assertFalse(arr.buckets[3].head == DoubleLinkedListNode(Pair(3, 3), null, null))

        // Ensure removing key not there doesn't change list
        assertFalse(arr.remove(3))
        assertTrue(arr.buckets[0].head == DoubleLinkedListNode(Pair(4, 5), null, null))
        assertTrue(arr.buckets[1].head == DoubleLinkedListNode(Pair(1, 1), null, null))

        // Ensure that removing from middle of linked list doesn't cause problems
        arr.set(8, 4)
        arr.set(12, 2)
        assertTrue(arr.remove(8))

        val ansList: DoubleLinkedList<Pair<Int, Int>> = DoubleLinkedList()
        ansList.pushBack(Pair(4, 5))
        ansList.pushBack(Pair(12, 2))
        assertTrue(arr.buckets[0].head?.data  == ansList.head?.data)
        assertTrue(arr.buckets[0].head?.next?.data == ansList.head?.next?.data)
    }

    @Test
    fun size() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()

        // Ensure initial size is 0
        assertTrue(arr.size() == 0)

        // Ensure map w values gives correct size
        arr.set(1, 1)
        assertTrue(arr.size() == 1)
        arr.set(3, 3)
        assertTrue(arr.size() == 2)
        arr.set(4, 5)
        assertTrue(arr.size() == 3)
        arr.set(8, 4)
        assertTrue(arr.size() == 4)
        arr.set(12, 2)
        assertTrue(arr.size() == 5)
        arr.remove(8)
        assertTrue(arr.size() == 4)
    }

    @Test
    fun keyValuePair() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()

        // Ensure initially this returns empty list
        assertTrue(arr.keyValuePairs() == emptyList<Int>())

        // Ensure map w values gives correct results
        arr.set(1, 1)
        arr.set(3, 3)
        arr.set(4, 5)
        assertTrue(arr.keyValuePairs() == listOf(Pair(4, 5), Pair(1, 1), Pair(3, 3)))
    }
}