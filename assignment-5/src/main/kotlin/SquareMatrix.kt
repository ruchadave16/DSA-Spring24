package org.dsa.assignment5

/**
 * A class representing a square matrix using nested mutable lists.
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

    /**
     * Return the size of the matrix
     *
     * @return: A Pair of ints representing the total rows and columns in the matrix respectively.
     */
     fun getSize(): Pair<Int, Int> {
        return Pair(matrix.size, matrix[0].size)
    }

    /**
     * Multiply this matrix by another matrix.
     *
     * This method uses traditional matrix multiplication to get the result.
     *
     * @param other: A SquareMatrix representing the matrix to multiply this matrix by
     *
     * @return: A matrix representing the result of this matrix multiplied by the inputted one and null if they are
     * not compatible
     */
    fun multiply(other: SquareMatrix): SquareMatrix? {
        if (getSize().second != other.getSize().first) {
            return null
        }

        val result: SquareMatrix = SquareMatrix()
        result.createMatrix(getSize().second) // Create empty matrix to store results

        for (row1 in 0..<getSize().first) {
            for (column2 in 0 ..<other.getSize().second) {
                for (row2 in 0..<other.getSize().first) {
                    val newValue: Int = result.getValue(row1, column2) + (getValue(row1, row2) + other.getValue(row2, column2))
                    result.setValue(row1, column2, newValue)
                }
            }
        }
        return result
    }

    /**
     * Multiply this matrix by another matrix using Strassen's Algorithm
     *
     * @param other: A SquareMatrix representing the matrix to multiply this matrix by
     *
     * @return: A matrix representing the result of this matrix multiplied by the inputted one and null if they are
     * not compatible
     */
    fun strassenMultiply(other: SquareMatrix): SquareMatrix? {
        if (getSize().second != other.getSize().first) {
            return null
        }

        
    }

}