package org.dsa.assignment4

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SortingFunctionsKtTest {

    @Test
    fun insertSort() {
        // Ensure singular value results same when sorted
        val list = mutableListOf(1)
        insertSort(list)
        assertEquals(list, mutableListOf(1))

        // Ensure that longer lists are sorted correctly
        val list2 = mutableListOf(1, 3, 2)
        insertSort(list2)
        assertEquals(list2, mutableListOf(1, 2, 3))

        val list3 = mutableListOf(1, 3, 4, 3, 2)
        insertSort(list3)
        assertEquals(list3, mutableListOf(1, 2, 3, 3, 4))
    }

    @Test
    fun selectionSort() {
        // Ensure singular value results same when sorted
        val list = mutableListOf(1)
        selectionSort(list)
        assertEquals(list, mutableListOf(1))

        // Ensure that longer lists are sorted correctly
        val list2 = mutableListOf(1, 3, 2)
        selectionSort(list2)
        assertEquals(list2, mutableListOf(1, 2, 3))

        val list3 = mutableListOf(1, 3, 4, 3, 2)
        selectionSort(list3)
        assertEquals(list3, mutableListOf(1, 2, 3, 3, 4))
    }

    @Test
    fun mergeSort() {
        // Ensure singular value results same when sorted
        val list = mutableListOf(1)
        mergeSort(list)
        assertEquals(list, mutableListOf(1))

        // Ensure that longer lists are sorted correctly
        val list2 = mutableListOf(1, 3, 2)
        mergeSort(list2)
        assertEquals(list2, mutableListOf(1, 2, 3))

        val list3 = mutableListOf(1, 3, 4, 3, 2)
        mergeSort(list3)
        assertEquals(list3, mutableListOf(1, 2, 3, 3, 4))
    }

    @Test
    fun bubbleSort() {
        // Ensure singular value results same when sorted
        val list = mutableListOf(1)
        bubbleSort(list)
        assertEquals(list, mutableListOf(1))

        // Ensure that longer lists are sorted correctly
        val list2 = mutableListOf(1, 3, 2)
        bubbleSort(list2)
        assertEquals(list2, mutableListOf(1, 2, 3))

        val list3 = mutableListOf(4, 3, 4, 3, 2)
        bubbleSort(list3)
        assertEquals(list3, mutableListOf(2, 3, 3, 4, 4))
    }
}