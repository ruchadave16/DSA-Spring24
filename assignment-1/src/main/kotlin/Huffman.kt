// This project is taken from Rucha and Anmol's Fall 23 Discrete Project and redone in Kotlin to learn basic syntax

package org.assignment1

fun main() {
    val map = mapFrequency("aabbbbcccccdddd")
    var heap: MinHeap = MinHeap()
    for (entry in map.entries.iterator()) {
        val node: Node = Node(entry.key, entry.value, null, null)
        heap.insert(node)
    }
    convertToTree(heap)
    printHuffman(heap)
}
