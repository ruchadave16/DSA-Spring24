package org.dsa.assignment6

import org.dsa.assignment2.DoubleLinkedList

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 */
class AssociativeArray<K, V> {
    private var size: Int = 1
    var buckets: MutableList<DoubleLinkedList<Pair<K, V>>> = mutableListOf()

    // Initialize buckets to an empty bucket
    init {
        size = 1
        buckets.add(DoubleLinkedList())
    }

    /**
     * Add a mapping from the key [k] to the value [v]
     *
     * In the case that the mapping for [k] already exists, replace the mapping
     *
     * @param k: K type key to be added or set to map
     * @param v: V type value to be added to map
     */
    operator fun set(k: K, v: V) {
        val hashKey: Int = k.hashCode() % size

        // If load is high, rehash to next prime number (load factor is 0.75)
        if ((size() / size) > 0.75) {
            rehash()
        }

        val thisBucket: DoubleLinkedList<Pair<K, V>> = buckets[hashKey]

        // Loop through all keys in the particular bucket
        var currNode = thisBucket.head
        var changed: Boolean = false
        while (currNode != null) {
            val thisKey = currNode.data.first

            // If matching key already exists, replace data in that node
            if (thisKey == k) {
                currNode.data = Pair(k, v)
                changed = true
                break
            }
            currNode = currNode.next
        }

        // If finish looping and there is no matching key, push new node to end of linked list
        if (!changed) {
            thisBucket.pushBack(Pair(k, v))
        }
    }

    /**
     * Set the size of the hashmap to be double and move all the data to be rehashed
     */
    fun rehash() {
        val newSize: Int = size * 2
        val newBuckets: MutableList<DoubleLinkedList<Pair<K, V>>> = MutableList(newSize) {DoubleLinkedList()}

        // Loop through each bucket and get every key's rehashed idx, change location to be there
        for (idx in 0..<size) {
            val thisBucket = buckets[idx]
            if (!thisBucket.isEmpty()) {
                var currNode = thisBucket.head
                while (currNode != null) {
                    val thisKey = currNode.data.first
                    val thisValue = currNode.data.second
                    val hashKey = thisKey.hashCode() % newSize // This is the new index of the key in the buckets
                    newBuckets[hashKey].pushBack(Pair(thisKey, thisValue))
                    currNode = currNode.next
                }
            }
        }
        buckets = newBuckets
        size = newSize
    }


    /**
     * @param k: K type key to check for in map
     *
     * @return true if [k] in map else false
     */
    operator fun contains(k: K): Boolean {
        val hashKey: Int = k.hashCode() % size

        // If hashKey empty, return false
        if (buckets[hashKey].isEmpty()) {
            return false
        }

        // Otherwise loop through and check each key to see if there is a match
        var currNode = buckets[hashKey].head
        while (currNode != null) {
            if (currNode.data.first == k) {
                return true
            }
            currNode = currNode.next
        }
        return false
    }

    /**
     * @param k: K type key to get value from within map
     *
     * @return type V value associated with the key or null if the key does not exist in the map
     */
    operator fun get(k: K): V? {
        if (contains(k)) {
            val hashKey: Int = k.hashCode() % size

            var currNode = buckets[hashKey].head
            while (currNode != null) {
                if (currNode.data.first == k) {
                    return currNode.data.second
                }
                currNode = currNode.next
            }
        }
        return null
    }

    /**
     * Remove the key and associated value from map
     *
     * @param k: K type key to be removed from map
     *
     * @return true if value and key were removed and false if key wasn't found in the map
     */
    fun remove(k: K): Boolean {
        if (contains(k)) {
            val hashKey: Int = k.hashCode() % size

            var currNode = buckets[hashKey].head
            var prevNode = currNode?.prev

            // If first value is the value, then remove it from the list
            if (currNode != null) {
                if (currNode.data.first == k) {
                    buckets[hashKey].popFront()
                    return true
                }
            }

            // Loop through nodes in the linked list checking for key and remove if match found
            while (currNode != null) {
                if (currNode.data.first == k) {
                    if (prevNode != null) {
                        prevNode.next = currNode.next
                    }
                    currNode.next?.prev = prevNode
                    return true
                }
                prevNode = currNode
                currNode = currNode.next
            }
        }
        return false
    }

    /**
     * @return an Int representing the size of the map (total keys)
     */
    fun size(): Int {
        var finalSize = 0

        // Loop through each non-empty bucket and count total "keys"
        for (idx in 0..<size) {
            if (!buckets[idx].isEmpty()) {
                var currNode = buckets[idx].head
                while (currNode != null) {
                    finalSize += 1
                    currNode = currNode.next
                }
            }
        }
        return finalSize
    }

    /**
     * @return A list of key value pairs for the entire array
     */
    fun keyValuePairs(): List<Pair<K, V>> {
        val pairList: MutableList<Pair<K, V>> = mutableListOf()

        // Loop through each bucket and get all key,value pairs appended to list to return
        for (idx in 0..<size) {
            if (!buckets[idx].isEmpty()) {
                var currNode = buckets[idx].head
                while (currNode != null) {
                    pairList.add(currNode.data)
                    currNode = currNode.next
                }
            }
        }
        return pairList.toList()
    }
}