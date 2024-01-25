/**
 * Library containing necessary classes and functions to create a MinHeap
 */

/**
 * A class representing a node in the binary tree for Huffman Encoding
 *
 * @param symbol: An int representing the symbol associated with the node
 * @param freq: An int representing the total occurrences of the symbol in the
 * string being encoded
 * @param left: A Node that is the left "child" of the current node
 * @param right: A Node that is the right "child" of the current node
 */
data class Node (
    var symbol: Int = 0,
    var freq: Int = 0,
    var left: Node? = null,
    var right: Node? = null
)

/**
 * Class representing a minimum heap used to map the frequencies of each
 * character in the string being encoded so that each parent node is smaller
 * than or equal to the children nodes
 *
 * Constructed void by initializing size of heap to 0 and initializes an empty
 * mutable list of nodes (heap)
 *
 * This includes functions to insert a node into the min heap, remove or return
 * the first (minimum) value, and swap two nodes.
 */

class MinHeap (var size: Int = 0, var heap: MutableList<Node> = mutableListOf()) {

    /**
     * Given an index, return a pointer to the node at that index in the min heap
     *
     * @param index: An int representing the index of the node in the min heap
     * that is to be returned
     * @return: A pointer to the node found at the inputted index in the min heap
     */
    fun get(index: Int) : Node {
        return heap[index]
    }

    /**
     * Given two indexes, swap the nodes that those indexes in the min heap.
     *
     * This is done in place by switching the locations of the two nodes in the
     * min heap
     *
     * @param index1: An int representing the first index of the node to be
     * swapped
     * @param index2: An int representing the second index of the node to be
     * swapped
     */
    fun swap(index1: Int, index2: Int) {
        val temp: Node = heap[index1]
        heap[index1] = heap[index2]
        heap[index2] = temp
    }

    /**
     * Given a node, insert it in the correct position in the min heap
     *
     * This first appends the node to the end of the heap and then compared the
     * frequency of the new node to the parent node (at index (i - 1) / 2). This
     * is because the min heap is stored layer by layer, listing the nodes of the
     * tree from left to right. If the new node is at a lower frequency than the
     * parent, then they are swapped.
     *
     * @param node: A node to be inserted into the min heap
     */
    fun insert(node: Node) {
        heap.add(node)
        size += 1

        var i: Int  = size - 1
        while (i >= 1) {
            val parent: Int = (i - 1) / 2
            if (heap[parent].freq > heap[i].freq) {
                swap(parent, i)
            }
            i = parent
        }
    }

    /**
     * Remove the first node of the min heap (the node with the lowest frequency)
     * and returns the node just removed. Size is decreased by one.
     *
     * @return: The node found at the first index of the min heap (that was just
     * removed from it)
     */
    fun pop(): Node {
        size -= 1
        return heap.removeFirst()
    }

    /**
     * Print min heap in a list format
     */
    fun printSimpleHeap() {
        for (i in 0..size - 1) {
            println("${heap[i].symbol.toChar()} freq: ${heap[i].freq}")
        }
    }
}

