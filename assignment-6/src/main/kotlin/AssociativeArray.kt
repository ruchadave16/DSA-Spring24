package org.dsa.assignment6

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 */

class AssociativeArray<K, V> {
    var kList: MutableList<K> = mutableListOf()
    var vList: MutableList<V> = mutableListOf()

    /**
     * Add a mapping from the key [k] to the value [v]
     *
     * In the case that the mapping for [k] already exists, replace the mapping
     *
     * @param k: K type key to be added or set to map
     * @param v: V type value to be added to map
     */
    operator fun set(k: K, v: V) {
        if (contains(k)) {
            val idx: Int = kList.indexOf(k)
            vList[idx] = v
        } else {
            kList.add(k)
            vList.add(v)
        }
    }

    /**
     * @param k: K type key to check for in map
     *
     * @return true if [k] in map else false
     */
    operator fun contains(k: K): Boolean {
        return kList.contains(k)
    }

    /**
     * @param k: K type key to get value from within map
     *
     * @return type V value associated with the key or null if the key does not exist in the map
     */
    operator fun get(k: K): V? {
        if (contains(k)) {
            val idx: Int = kList.indexOf(k)
            return vList[idx]
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
            val idx: Int = kList.indexOf(k)
            kList.removeAt(idx)
            vList.removeAt(idx)
            return true
        }
        return false
    }

    /**
     * @return an Int representing the size of the map (total keys)
     */
    fun size(): Int {
        return kList.size
    }

    /**
     * @return A list of key value pairs for the entire array
     */
    fun keyValuePairs(): List<Pair<K, V>> {
        val pairList: MutableList<Pair<K, V>> = mutableListOf()
        for (idx in 0..<size()) {
            val thisPair = Pair(kList[idx], vList[idx])
            pairList.add(thisPair)
        }
        return pairList.toList()
    }
}