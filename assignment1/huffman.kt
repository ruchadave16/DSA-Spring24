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
