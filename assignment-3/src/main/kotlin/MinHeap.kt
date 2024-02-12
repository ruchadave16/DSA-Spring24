package org.dsa.assignment3

/**
 * Data class representing a node from the MinHeap
 *
 * @param data: Type T representing the data the node represents
 * @param priority: A Double representing the priority of the data
 */
data class MinHeapNode<T> (
    var data: T,
    var priority: Double = 0.0
)


/**
 * A class implementing a min heap
 *
 * @param T the type of the heap elements
 * @property size: an Int representing the total elements in the heap
 * @property vertices: A list holding the heap structure as nodes of the element T and its priority
 * @property indexMap: A lookup map to find existing vertices
 */
class MinHeap<T> {
    var size: Int = 0
    private var vertices: MutableList<MinHeapNode<T>> = mutableListOf()
    private var indexMap: MutableMap<T, Int> = mutableMapOf()

    /**
     * Check if the heap is empty
     *
     * @return True if the heap is empty and false otherwise
     */
    fun isEmpty(): Boolean {
        return (size == 0)
    }

    /**
     * Get the index of an element in the heap
     *
     * @param vertex: A type T element to get the index of in the map
     *
     * @return An Int representing the index of the element in the map if it exists and null otherwise
     */
    fun get(vertex: T): Int? {
        return indexMap[vertex]
    }

    /**
     * Swap two vertexes at the indexes provided in the heap structure
     *
     *  @param index1: An int representing the first index of the node to be swapped
     *  @param index2: An int representing the second index of the node to be swapped
     */
    private fun swap(index1: Int, index2: Int) {
        val temp: MinHeapNode<T> = vertices[index1]
        vertices[index1] = vertices[index2]
        vertices[index2] = temp

        // Set indexes in map right
        indexMap[vertices[index1].data] = index1
        indexMap[vertices[index2].data] = index2
    }

    /**
     * Insert the inputted data into the correct position in the min heap if it isn't there already
     *
     * This first appends the vertex to the end of the heap and then compared the
     * frequency of the new vertex to the parent vertex (at index (i - 1) / 2). This
     * is because the min heap is stored layer by layer, listing the nodes of the
     * tree from left to right. If the new node is at a lower frequency than the
     * parent, then they are swapped.
     *
     * @param data: Data of type T to be added to the heap
     * @param heapNumber: A Double representing the priority of the data to be added to the minheap
     *
     * @return True if the data was inserted and False if it was already in the heap
     */
    fun insert(data: T, heapNumber: Double): Boolean {
        if (indexMap.contains(data)) {
            return false
        }

        vertices.addLast(MinHeapNode(data, heapNumber))
        size += 1

        // If first value, then set to index 0
        if (size == 1) {
            indexMap[data] = 0
        }

        var i: Int = size - 1
        while (i >= 1) {
            val parent: Int = (i - 1) / 2
            if (vertices[parent].priority > vertices[i].priority) {
                swap(parent, i)
            }
            i = parent
        }
        return true
    }

    /**
     * Find the vertex with the minimum value in the heap (top value of heap) and remove it
     *
     * @return The data T associated with the vertex with the lowest priority and null if the heap is empty
     */
    fun getMin(): T? {
        if (size == 0) {
            return null
        }
        if (size == 1) {
            size = 0
            vertices = mutableListOf()
            indexMap = mutableMapOf()
            return vertices[0].data
        }
        else {
            val temp: MinHeapNode<T> = vertices[0]
            swap(0, size-1)
            vertices.removeLast()
            indexMap.remove(temp.data)
            bubbleDown(0, vertices[0].priority)
            return temp.data
        }
    }

    /**
     * Change the priority of a value
     *
     * @param vertex: An element to change the priority of
     * @param newNumber: A Double representing the new priority of the element
     */
    fun adjustHeapNumber(vertex: T, newNumber: Double) {
        val vertexIdx: Int = get(vertex)!!
        val currPriority: Double = vertices[vertexIdx].priority
        vertices[vertexIdx].priority = newNumber

        // If new priority is larger than current, then sort downwards
        if (currPriority < newNumber) {
            bubbleDown(vertexIdx, newNumber)
        }

        // Otherwise, continue checking up like in add
        if (currPriority > newNumber) {
            var i: Int = vertexIdx
            while (i >= 1) {
                val parent: Int = (i - 1) / 2
                if (vertices[parent].priority > vertices[i].priority) {
                    swap(parent, i)
                }
                i = parent
            }
        }
    }

    /**
     * Move a higher priority vertex down the min heap
     *
     * @param vertexIdx: An int representing the index of the vertex to move down the min heap
     * @param priority: A Double representing the priority of the vertex to move through the heap
     */
    private fun bubbleDown(vertexIdx: Int, priority: Double) {
        val child1Idx: Int = (vertexIdx * 2) + 1
        val child2Idx: Int = (vertexIdx * 2) + 2
        val child1Priority: Double = if (child1Idx >= size) Double.POSITIVE_INFINITY else vertices[child1Idx].priority
        val child2Priority: Double = if (child2Idx >= size) Double.POSITIVE_INFINITY else vertices[child2Idx].priority

        // Check both children for the one with the priority that this falls above
        if ((child1Priority < priority) && (child2Priority < priority)) {
            return
        } else if (child1Priority < child2Priority) {
            swap(child1Idx, vertexIdx)
            bubbleDown(child1Idx, priority)
            return
        } else {
            swap(child2Idx, vertexIdx)
            bubbleDown(child2Idx, priority)
            return
        }
    }
}
