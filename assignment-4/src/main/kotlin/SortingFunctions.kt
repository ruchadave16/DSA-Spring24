package org.dsa.assignment4

/**
 * Functions for sorting algorithms on a MutableList<T>
 */

/**
 * Insertion Sorting Algorithm
 *
 * This n^2 algorithm starts with the first two values of the list and swaps them if the value to the left is smaller.
 * Then, it moves to the 2nd and 3rd values of the list (knowing that the values before index 2 are in order). If they
 * are out of order, it swaps them and compares values at position 1 and 2 again. This repeats again and again until
 * the list is in order
 *
 * @param list: A Mutable List of type Int that represents the list to be sorted inplace
 */
fun insertSort(list: MutableList<Int>) {
    for (i in 1..<list.size) {
        // Start with previous value and keep swapping backwards until end is reached or the remaining of the list is
        // in ascending order
        var prevIdx: Int = i - 1
        while (prevIdx != -1) {
            val prevVal: Int = list[prevIdx]
            if (prevVal > list[prevIdx + 1]) {
                list[prevIdx] = list[prevIdx + 1]
                list[prevIdx + 1] = prevVal
                prevIdx -= 1
            }
            else {
                prevIdx = -1
            }
        }
    }
}

/**
 * Selection Sorting Algorithm
 *
 * This n^2 algorithm finds the minimum value in the unsorted list and moves it to the front of the sorted list. It
 * continues this again and again until all values have been sorted
 *
 * @param list: A Mutable List of type Int that represents the list to be sorted inplace
 */
fun selectionSort(list: MutableList<Int>) {
    for (i in 0..<list.size) {
        var minIdx: Int = i
        // Loop through all the remaining values in the list and find the index of the minimum one
        for (j in i+1..<list.size) {
            if (list[minIdx] > list[j]) {
                minIdx = j
            }
        }

        // Swap the min value from the remaining list to the next portion of the list
        val minIdxValue: Int = list[minIdx]
        list[minIdx] = list[i]
        list[i] = minIdxValue
    }
}

/**
 * Merge Sort Algorithm
 *
 * This n log n algorithm recursively splits the list into two halfs, the right and the left evenly. It then uses
 * merge sort on each of the individual lists and merges them together after. The merge is done by looping
 * through the left and right lists (already sorted) from smallest to largest value. The smaller of the two is placed
 * in the final list.
 *
 * @param list: A Mutable List of type Int that represents the list to be sorted
 */
fun mergeSort(list: MutableList<Int>) {
    // If larger lists, split them and merge sort individual portions
    if (list.size > 1) {
        val mid: Int = list.size / 2
        val leftList: MutableList<Int> = list.subList(0, mid)
        val rightList: MutableList<Int> = list.subList(mid, list.size)

        mergeSort(leftList)
        mergeSort(rightList)

        // Once individual left and right lists are merge sorted in place (both are in order), merge them
        var leftIdx = 0
        var rightIdx = 0
        var sortedIdx = 0

        // Loop through the left and right list value by value
        while (leftIdx < leftList.size && rightIdx < rightList.size) {

            // If left is smaller, then put the smallest value in actual list as left[i] otherwise right
            // This works because the left and right lists individually have already been sorted
            if (leftList[leftIdx] <= rightList[rightIdx]) {
                list[sortedIdx] = leftList[leftIdx]
                leftIdx += 1
            } else {
                list[sortedIdx] = rightList[rightIdx]
                rightIdx += 1
            }
            sortedIdx += 1
        }

        // Once one list is over, append the other list to the end of the current list
        while (leftIdx < leftList.size) {
            list[sortedIdx] = leftList[leftIdx]
            leftIdx += 1
            sortedIdx += 1
        }
        while (rightIdx < rightList.size) {
            list[sortedIdx] = rightList[rightIdx]
            rightIdx += 1
            sortedIdx += 1
        }
    }
}

/**
 * Bubble Sort Algorithm
 *
 * This n^2 algorithm compares two adjacent values of the list as it moves forward and swaps them, essentially
 * "bubbling" the maximum value to the end of the list. This is done again and again, ignoring the already sorted
 * values at the end of the list until there are no more swaps made (meaning all values are in order)
 *
 * @param list: A Mutable List of type Int that represents the list to be sorted inplace
 */
fun bubbleSort(list: MutableList<Int>) {
    for (i in 0..<list.size) {
        var swapped: Boolean = false
        // Since last i elements are already in place, traverse the entire array up till i
        for (j in 0..<list.size - i - 1) {
            println(list[j])
            println(list[j+1])
            // If the adjacent value is smaller, swap them
            if (list[j] > list[j+1]) {
                val currValue: Int = list[j]
                list[j] = list[j+1]
                list[j+1] = currValue
                swapped = true
            }
        }
        println(swapped)
        println(list)
        // If completed the entire pass but still have not swapped any elements, then they are in order
        if (!swapped) {
            break
        }
    }
}
