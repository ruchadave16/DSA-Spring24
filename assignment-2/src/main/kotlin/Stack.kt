package org.dsa.assignment2

interface Stack<T> {
    fun push(data: T)
    fun pop(): T?
    fun peek(): T?
    fun isEmpty(): Boolean
    fun reverse()
}

/**
 * An implementation of the Stack structure using a DoubleLinkedList as the base
 */
class MyStack<T>: Stack<T> {
    var stack: DoubleLinkedList<T> = DoubleLinkedList()

    /**
     * Add data to the top of the stack
     *
     * @param data: A type-T input that represents the data to be added to the top of the stack
     */
    override fun push(data: T) {
        stack.pushFront(data)
    }

    /**
     * Remove data from the top of the stack
     *
     * @return The value at the top of the stack or null if the stack is empty
     */
    override fun pop(): T? {
        return stack.popFront()
    }

    /**
     * Show the top of the stack
     *
     * @return The value at the top of the stack or null if the stack is empty
     */
    override fun peek(): T? {
        return stack.peekFront()
    }

    /**
     * Check whether the stack is empty or not
     *
     * @return A boolean True if the stack is empty and False if not
     */
    override fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

    /**
     * Reverse the stack by creating a new stack and placing the top value of the current stack to the bottom of the
     * new stack
     */
    override fun reverse() {
        val newStack : MyStack<T> = MyStack()
        var currNode : DoubleLinkedListNode<T>? = stack.head

        while (currNode != null) {
            newStack.push(currNode.data)
            currNode = currNode.next
        }
        stack = newStack.stack
    }


}