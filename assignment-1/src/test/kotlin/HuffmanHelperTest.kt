package org.dsa.assignment1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class HuffmanHelperTest {
    val strings: List<String> = listOf("", "aaa", "aba", "aaabbbbbbbbbbbcccdcccdewleiew")
    val map_answers: List<Map<Int, Int>> = listOf(mapOf(), mapOf(Pair(97, 3)), mapOf(Pair(97, 2), Pair(98, 1)),
        mapOf(Pair(97, 3), Pair(98, 11), Pair(99, 6), Pair(100, 2), Pair(101, 3), Pair(105, 1), Pair(108, 1),
            Pair(119, 2)))
    @Test
    fun lengthMapFrequency() {
        // Check that when mapping frequencies, total unique characters are each being converted into new keys
        val map_lengths: List<Int> = listOf(0, 1, 2, 8)
        for (i in 0..<4) {
            assertEquals(mapFrequency(strings[i]).count(), map_lengths[i])
        }
    }

    @Test
    fun mapFrequency() {
        // Check that correct frequency counts for each key are done
        for (i in 0..<4) {
            assertEquals(mapFrequency(strings[i]), map_answers[i])
        }
    }

    @Test
    fun convertTreeFrequency() {
        // Ensure that when converting to tree, all are accounted for 
        for (i in 0..<4) {
            val thisMap: Map<Int, Int> = mapFrequency(strings[i])
            val thisHeap: MinHeap = MinHeap()
            for (entry in thisMap.entries.iterator()) {
                val node: Node = Node(entry.key, entry.value, null, null)
                thisHeap.insert(node)
            }
            convertToTree(thisHeap)

            var freq: Int = 0
            if (thisHeap.size > 0) {
                freq = thisHeap[0].freq
            }
            assertEquals(freq, strings[i].count())
        }
    }
}