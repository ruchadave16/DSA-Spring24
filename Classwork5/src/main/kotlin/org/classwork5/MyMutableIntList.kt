package org.classwork5

class MyMutableIntList {
    private var list = IntArray(size = 1)
    private var nextIdx = 0;

    fun add(element: Int) {
        if (nextIdx == size()) {
            var newArray = IntArray(size() * 2)
            list.copyInto(newArray)
            newArray[nextIdx] = element
            list = newArray
        } else {
            list[nextIdx] = element
        }
        nextIdx += 1
    }

    fun clear() {
        nextIdx = 0
    }

    fun size(): Int {
        return list.size
    }

    operator fun get(index: Int): Int {
        return list[index]
    }

    operator fun set(index: Int, value: Int) {
        list[index] = value
    }

}
