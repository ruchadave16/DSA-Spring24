package org.dsa.assignment5

/**
 *
 */
class SquareMatrix () {
    private var matrix: MutableList<MutableList<Int>> = mutableListOf<MutableList<Int>>()

    /**
     * Create an empty square matrix of size n filled with 0s
     *
     * @param n: An int representing the size of the matrix rows and columns
     */
    fun createMatrix(n: Int) {
        matrix = mutableListOf<MutableList<Int>>()
        for (i in 0..<n) {
            val thisRow: MutableList<Int> = MutableList(n) {0} // Initialize row to be 0s
            matrix += thisRow
        }
    }

    /**
     * Get the value at a certain row and column from the matrix. This indexes at 0
     *
     * @param row: An int representing the row of the matrix to get the value from
     * @param col: An int representing the col of the matrix to get the value from
     *
     * @return: An int representing the value at the chosen row and columns
     */
    fun getValue(row: Int, col: Int): Int {
        return matrix[row][col]
    }

    /**
     * Set the value at a certain row and column to the value provided. This indexes at 0
     *
     * @param row: An int representing the row of the matrix to set the value at
     * @param col: An int representing the col of the matrix to set the value at
     * @param value: An int representing the value to set the position to
     */
    fun setValue(row: Int, col: Int, value: Int) {
        matrix[row][col] = value
    }
}