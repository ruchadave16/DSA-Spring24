package org.dsa.assignment1

/*
 * Helper functions for Huffman Encoding
 */

/**
 * Return a map from character (acsii) to frequency in the inputted string.
 *
 * @param input: A string to be mapped to the frequency of occurrence of each
 * character in it
 * @return: A map mapping an integer representation of each character in the
 * input string to the total occurrences of it in the string.
 */

fun mapFrequency(input: String) : MutableMap<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    val strLength = input.length - 1
    for (i in 0..strLength) {
        val value = map[input[i].code]
        map[input[i].code] = if (value != null) (value+1) else 1
    }
    return map
}

/**
 * Convert a min heap to a binary tree where each root node's frequency
 * represents the sum of the frequencies of each of the child nodes.
 *
 * This tree is created so that the higher frequencies are found at the surface
 * of the tree and the lower frequencies are nested further down in the tree.
 *
 * @param heap: A MinHeap to be converted into the binary tree
 */
fun convertToTree(heap: MinHeap) {
    while (heap.size > 1) {
        val left: Node = heap.pop()
        val right: Node = heap.pop()

        // Create new node with left and right as the two new minimum frequencies
        // and frequency as the sum of the two
        val temp: Node = Node('$'.code, left.freq + right.freq, left, right)
        heap.insert(temp)
    }
}

/**
 * Print the Huffman tree in a tree format.
 *
 * Print's each node's symbol and frequency in the tree in a tree format using
 * the helper function printHuffmanHelper.
 *
 * @param heap: A MinHeap to be printed
 */
fun printHuffman(heap: MinHeap) {
    println(" /")
    printHuffmanHelper(heap.get(0), " |  ")
    println(" //")
}

/**
 * Helper function to print the Huffman tree in a tree format.
 *
 *
 * Recursively prints the tree by traversing the tree through the left and right
 * child nodes of each node.
 *
 *
 * @param root: The root node of the Huffman tree
 * @param prefix: A string representing the prefix to be printed before
 * each node
 */
fun printHuffmanHelper(root: Node?, prefix: String) {
    if (root != null) {
        printHuffmanHelper(root.right, "$prefix|   ")
        println("$prefix ${root.symbol.toChar()} : ${root.freq}")
        printHuffmanHelper(root.left, "$prefix|   ")
    }
    println("-")
}

/**
 * Helper function to print the Huffman codes for each character in the tree.
 *
 * Recursively prints the codes for each character in the tree by traversing the
 * tree through the left and right child nodes of each node.
 *
 * @param root: A pointer to the root node of the Huffman tree
 * @param str: A string representing the prefix to be printed before the node
 */
fun printCodes(root: Node?, str: String, nodeMap: MutableMap<Node, String>) {
    if (root == null) {
        return
    }

    if (root.symbol.toChar() != '$') {
        println("${root.symbol.toChar()} : $str")
        nodeMap[root] = str
    }

    printCodes(root.left, str + "0", nodeMap)
    printCodes(root.right, str + "1", nodeMap)
}

