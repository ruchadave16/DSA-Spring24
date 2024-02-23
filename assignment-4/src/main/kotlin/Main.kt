package org.dsa.assignment4

import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.measureTime

/**
 * Used to benchmark the algorithms for sorting
 */

fun main() {
    // initialize data array to hold time values: rows are algorithms, columns are list size
    val timeArray = Array(4) {Array(7) {0.0} }
    var timeArrayIdx = 0

    for (i in intArrayOf(100, 500, 1000, 2500, 5000, 7500, 10000)) {
        // initialize random list of that size
        val randListOrig = (0..i).map { (0..100000).random() }
        val randListInsert = randListOrig.toMutableList()
        val randListSelection = randListOrig.toMutableList()
        val randListMerge = randListOrig.toMutableList()
        val randListBubble = randListOrig.toMutableList()

        // use sorting algorithm and measure time taken for it to run
        val timeInsert = measureTime {
            insertSort(randListInsert)
        }
        val timeSelection = measureTime {
            selectionSort(randListSelection)
        }
        val timeMerge = measureTime {
            mergeSort(randListMerge)
        }
        val timeBubble = measureTime {
            bubbleSort(randListBubble)
        }

        // save times into array
        timeArray[0][timeArrayIdx] = timeInsert.toDouble(DurationUnit.MILLISECONDS)
        timeArray[1][timeArrayIdx] = timeSelection.toDouble(DurationUnit.MILLISECONDS)
        timeArray[2][timeArrayIdx] = timeMerge.toDouble(DurationUnit.MILLISECONDS)
        timeArray[3][timeArrayIdx] = timeBubble.toDouble(DurationUnit.MILLISECONDS)
        timeArrayIdx += 1
    }

    // Printed array of time taken (Plot of a run is in folder)
    println(timeArray.contentDeepToString())
}