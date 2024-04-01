package org.dsa.assignment6

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test
class AssociativeArrayTest {
    @Test
    fun set() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()
        // Check that initially empty
        assertTrue(arr.kList.size == 0)
        assertTrue(arr.vList.size == 0)

        // Set a few values to be added to list and ensure list looks correct
        arr.set(1, 1)
        arr.set(2, 1)
        assertTrue(arr.kList == mutableListOf(1, 2))
        assertTrue(arr.vList == mutableListOf(1, 1))

        // Ensure changing values for a key works
        arr.set(1, 4)
        assertTrue(arr.kList == mutableListOf(1, 2))
        assertTrue(arr.vList == mutableListOf(4, 1))
    }

    @Test
    fun contains() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()
        arr.kList = mutableListOf(1, 3, 4)
        arr.vList = mutableListOf(1, 3, 5)

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
        arr.kList = mutableListOf(1, 3, 4)
        arr.vList = mutableListOf(1, 3, 5)
        assertTrue(arr.get(1) == 1)
        assertTrue(arr.get(4) == 5)

        // Ensure keys that aren't there return null
        assertTrue(arr.get(5) == null)
    }

    @Test
    fun remove() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()
        arr.kList = mutableListOf(1, 3, 4)
        arr.vList = mutableListOf(1, 3, 5)

        // Ensure removing key there edits both values and keys list
        assertTrue(arr.remove(3))
        assertTrue(arr.kList == mutableListOf(1, 4))
        assertTrue(arr.vList == mutableListOf(1, 5))

        // Ensure removing key not there doesn't change list
        assertFalse(arr.remove(3))
        assertTrue(arr.kList == mutableListOf(1, 4))
        assertTrue(arr.vList == mutableListOf(1, 5))
    }

    @Test
    fun size() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()

        // Ensure initial size is 0
        assertTrue(arr.size() == 0)

        // Ensure map w values gives correct size
        arr.kList = mutableListOf(1, 3, 4)
        arr.vList = mutableListOf(1, 3, 5)
        assertTrue(arr.size() == 3)
    }

    @Test
    fun keyValuePair() {
        val arr: AssociativeArray<Int, Int> = AssociativeArray()

        // Ensure initially this returns empty list
        assertTrue(arr.keyValuePairs() == emptyList<Int>())

        // Ensure map w values gives correct results
        arr.kList = mutableListOf(1, 3, 4)
        arr.vList = mutableListOf(1, 3, 5)
        assertTrue(arr.keyValuePairs() == listOf(Pair(1, 1), Pair(3, 3), Pair(4, 5)))
    }
}