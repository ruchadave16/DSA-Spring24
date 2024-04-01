package org.dsa.assignment2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DoubleLinkedListNodeTest {

    @Test
    fun pushFront() {
        val list : DoubleLinkedList<Int> = DoubleLinkedList()
        // Check that the initial list is empty
        assertTrue(list.isEmpty())

        // Check that pushFront with a list of just size one sets both the head and tail correctly
        list.pushFront(3)
        assertTrue(list.head?.data == 3)
        assertTrue(list.tail?.data == 3)

        // Check that adding more values correctly changes the head of the list without changing the tail
        list.pushFront(2)
        assertTrue(list.head?.data == 2)
        assertTrue(list.tail?.data == 3)
        list.pushFront(-6)
        assertTrue(list.head?.data == -6)

        // Ensure that pointers of next and prev are correct with pushFront
        // First start by checking head's prev and tail's next are empty
        assertTrue(list.tail?.next == null)
        assertTrue(list.head?.prev == null)

        // Remaining pointers are correct
        assertTrue(list.head?.next?.next == list.tail)
        assertTrue(list.head?.next == list.tail?.prev)
        assertTrue(list.head == list.tail?.prev?.prev)
    }

    @Test
    fun pushBack() {
        val list : DoubleLinkedList<Int> = DoubleLinkedList()
        // Check that pushBack with a list of just size one sets both the head and tail correctly
        list.pushBack(3)
        assertTrue(list.head?.data == 3)
        assertTrue(list.tail?.data == 3)

        // Check that adding more values correctly changes the tail of the list without changing the head
        list.pushBack(2)
        assertTrue(list.head?.data == 3)
        assertTrue(list.tail?.data == 2)
        list.pushBack(-6)
        assertTrue(list.tail?.data == -6)

        // Ensure that pointers of next and prev are correct with pushBack
        // First start by checking head's prev and tail's next are empty
        assertTrue(list.tail?.next == null)
        assertTrue(list.head?.prev == null)

        // Remaining pointers are correct
        assertTrue(list.head?.next?.next == list.tail)
        assertTrue(list.head?.next == list.tail?.prev)
        assertTrue(list.head == list.tail?.prev?.prev)
    }

    @Test
    fun popFront() {
        val list : DoubleLinkedList<String> = DoubleLinkedList()
        list.pushFront("Hello")
        list.pushFront("World")
        list.pushBack("123")
        list.pushBack("")

        // Check that pop head removes and returns the correct head while setting the new  one
        assertTrue(list.popFront() == "World")
        assertTrue(list.head?.data == "Hello")
        assertTrue(list.tail?.data == "")
        assertTrue(list.head?.next?.next == list.tail)
        assertTrue(list.head?.prev == null)

        // Check that removing all values sets the list to empty
        list.popFront()
        list.popFront()
        list.popFront()
        assertTrue(list.head == null)
        assertTrue(list.tail == null)
    }

    @Test
    fun popBack() {
        val list : DoubleLinkedList<String> = DoubleLinkedList()
        list.pushFront("Hello")
        list.pushFront("World")
        list.pushBack("123")
        list.pushBack("")

        // Check that pop head removes and returns the correct head while setting the new  one
        assertTrue(list.popBack() == "")
        assertTrue(list.head?.data == "World")
        assertTrue(list.tail?.data == "123")
        assertTrue(list.head?.next?.next == list.tail)
        assertTrue(list.head?.prev == null)

        // Check that removing all values sets the list to empty
        list.popBack()
        list.popBack()
        list.popBack()
        assertTrue(list.head == null)
        assertTrue(list.tail == null)
    }

    @Test
    fun peekFront() {
        val list : DoubleLinkedList<Boolean> = DoubleLinkedList()

        // Check that as new data is added to the front and back, peekFront works correctly
        assertTrue(list.peekFront() == null)
        list.pushFront(true)
        assertTrue(list.peekFront() == true)
        list.pushBack(false)
        assertTrue(list.peekFront() == true)
        list.pushFront(false)
        assertTrue(list.peekFront() == false)
        list.pushBack(false)
        assertTrue(list.peekFront() == false)
    }

    @Test
    fun peekBack() {
        val list : DoubleLinkedList<Boolean> = DoubleLinkedList()

        // Check that as new data is added to the front and back, peekBack works correctly
        assertTrue(list.peekBack() == null)
        list.pushFront(true)
        assertTrue(list.peekBack() == true)
        list.pushBack(false)
        assertTrue(list.peekBack() == false)
        list.pushFront(false)
        assertTrue(list.peekBack() == false)
        list.pushBack(true)
        assertTrue(list.peekBack() == true)
    }

    @Test
    fun isEmpty() {
        val list : DoubleLinkedList<Char> = DoubleLinkedList()

        // Ensure that the list starts empty, is not empty with values in it, and is empty again once they are removed
        assertTrue(list.isEmpty())
        list.pushFront('c')
        assertFalse(list.isEmpty())
        list.popBack()
        assertTrue(list.isEmpty())

    }
}