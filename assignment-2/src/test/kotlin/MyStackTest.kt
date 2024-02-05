package org.assignment2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MyStackTest {
    @Test
    fun push() {
        val list : MyStack<Int> = MyStack()

        // Ensure that push adds the data to the front of the stack
        list.push(3)
        assertTrue(list.stack.head?.data == 3)
        assertTrue(list.stack.tail?.data == 3)
        assertFalse(list.stack.isEmpty())

        list.push(-1)
        assertTrue(list.stack.head?.data == -1)
        assertTrue(list.stack.head?.next == list.stack.tail)
        assertTrue(list.stack.head == list.stack.tail?.prev)
    }

    @Test
    fun pop() {
        val list : MyStack<String> = MyStack()

        // Ensure that pop removes the first value of list
        list.push("Hi")
        assertTrue(list.pop() == "Hi")
        assertTrue(list.stack.head == null)

        list.push("Hi")
        list.push("World")
        assertTrue(list.pop() == "World")
    }

    @Test
    fun peek() {
        val list : MyStack<String> = MyStack()

        // Ensure that peek shows the first value of the stack
        assertTrue(list.peek() == null)
        list.push("c")
        assertTrue(list.peek() == "c")
        list.push("a")
        assertTrue(list.peek() == "a")
        list.pop()
        assertTrue(list.peek() == "c")
        list.pop()
        assertTrue(list.peek() == null)
    }

    @Test
    fun isEmpty() {
        val list : MyStack<Char> = MyStack()

        // Ensure that stack starts empty, is not empty with values in it, and is empty again once they are removed
        assertTrue(list.isEmpty())
        list.push('c')
        assertFalse(list.isEmpty())
        list.pop()
        assertTrue(list.isEmpty())
    }

    @Test
    fun reverse() {
        val list : MyStack<Int> = MyStack()

        // Ensure reverse correctly reverses the values of the stack
        list.push(2)
        list.push(3)
        list.push(-9)
        list.push(4)
        list.reverse()
        assertTrue(list.stack.head?.data == 2)
        list.pop()
        assertTrue(list.pop() == 3)
        assertTrue(list.pop() == -9)
        assertTrue(list.pop() == 4)
        list.pop()
        assertTrue(list.isEmpty())
    }
}