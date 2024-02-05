/**
 * Class for Double Linked List, including Double Linked List Node class definition
 */

package com.dsa.assignment2

/**
 * A class representing a node in the double linked list
 *
 * @param data A type T input representing the data to be represented by the Node
 * @param prev A Node that precedes the current node in the double linked list. If this is null, the current node is the
 *      head of the double linked list
 * @param next A Node that follows the current node in the double linked list. If this is null, the current node is the
 *      end of the double linked list
 */

data class DoubleLinkedListNode<T> (
    var data: T,
    var prev: DoubleLinkedListNode<T>?,
    var next: DoubleLinkedListNode<T>?,
)


/**
 * A class representing a double linked list
 *
 * Constructed by setting variables head and tail to null
 *
 * This includes functions to push to the front or back, remove from the front or back, get the value of the front or
 * back, or check whether the list is empty or not
 */
class DoubleLinkedList<T> () {
    var head: DoubleLinkedListNode<T> ?= null
    var tail: DoubleLinkedListNode<T> ?= null

    /**
     * Set head of list to a node with the new data
     *
     * @param data: A type-T input that represents the data to be added to the head of the double linked list.
     */
    fun pushFront(data: T) {
        val newNode: DoubleLinkedListNode<T> = DoubleLinkedListNode<T>(data, null, head)

        // If list is empty, then set head and tail to new node
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            head?.prev = newNode
            head = newNode
        }
    }

    /**
     * Set end of list to a node with the new data
     *
     * @param data: A type-T input that represents the data to be added to the end of the double linked list.
     */
    fun pushBack(data: T) {
        val newNode: DoubleLinkedListNode<T> = DoubleLinkedListNode<T>(data, tail, null)

        // If list is empty, then set head and tail to new node
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
    }

    /**
     * Remove the first node of the double linked list
     *
     * @return The data stored in the first Node of the double linked list (null if empty)
     */
    fun popFront(): T? {
        val origHead: DoubleLinkedListNode<T>? = head
        val headNext: DoubleLinkedListNode<T>? = head?.next
        headNext?.prev = null
        head = head?.next

        if (isEmpty()) {
            tail = null
        }
        return origHead?.data
    }

    /**
     * Remove the last node of the double linked list
     *
     * @return The data stored in the last Node of the double linked list (null if empty)
     */
    fun popBack(): T? {
        val origTail: DoubleLinkedListNode<T>? = tail
        val tailPrev: DoubleLinkedListNode<T>? = tail?.prev
        tailPrev?.next = null
        tail = tail?.prev

        if (tail == null) {
            head = null
        }
        return origTail?.data
    }

    /**
     * Check the value of the first node in the list
     *
     * @return data of type T represented by the first node in the list and null if the list is empty
     */
    fun peekFront(): T? {
        return head?.data
    }

    /**
     * Check the value of the last node in the list
     *
     * @return data of type T represented by the last node in the list and null if the list is empty
     */
    fun peekBack(): T? {
        return tail?.data
    }

    /**s
     * Check if list is empty by checking if head is null
     *
     * @return True if list is empty, else False
     */
    fun isEmpty(): Boolean {
        return head == null
    }
}
