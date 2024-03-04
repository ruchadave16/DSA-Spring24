package org.dsa.assignment5

/**
 * A class representing a square matrix using nested mutable lists.
 */
class SquareMatrix (matrixIn: MutableList<MutableList<Int>> = mutableListOf()) {
    private var matrix: MutableList<MutableList<Int>> = matrixIn

    /**
     * Create an empty square matrix of size n filled with 0s
     *
     * @param n: An int representing the size of the matrix rows and columns
     */
    fun createEmptyMatrix(n: Int) {
        matrix = mutableListOf()
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
     * Return row specified
     *
     * @param idx: Int representing the row to get from the matrix
     *
     * @return MutableList<Int> representing the row
     */
    fun getRow(idx: Int): MutableList<Int> {
        return matrix[idx]
    }

    /**
     * Set row to specified MutableList<Int>
     *
     * @param idx: Int representing the row to change
     * @param row: MutableList<Int> representing the value of the list to change the row to
     */
    fun setRow(idx: Int, row:MutableList<Int>) {
        matrix[idx] = row
    }

    /**
     * Check if matrix is same as this one
     *
     * @param matrix1: A Square Matrix to compare to this one
     *
     * @return A boolean stating True if they both are the same and False otherwise
     */
    fun equalsMatrix(matrix1: SquareMatrix): Boolean {
        if (getSize() != matrix1.getSize()) {
            return false
        }

        val thisSize: Pair<Int, Int> = getSize()
        for (i in 0..<thisSize.first) {
            for (j in 0..<thisSize.second) {
                if (getValue(i, j) != matrix1.getValue(i, j)) {
                    return false
                }
            }
        }
        return true
    }

    /**
     * Add matrix to current one
     *
     * @param other: A SquareMatrix representing the matrix to add to the current one
     *
     * @return A SquareMatrix representing the answer of the sum
     */
    fun addMatrix(other: SquareMatrix): SquareMatrix {
        val answer: SquareMatrix = SquareMatrix()
        answer.createEmptyMatrix(getSize().first)

        for (row in 0..<getSize().first) {
            for (col in 0..<getSize().second) {
                answer.setValue(row, col,getValue(row, col) + other.getValue(row, col))

            }
        }

        return answer
    }

    /**
     * Subtract matrix to current one
     *
     * @param other: A SquareMatrix representing the matrix to add to the current one
     *
     * @return A SquareMatrix representing the answer of the difference
     */
    fun subtractMatrix(other: SquareMatrix): SquareMatrix {
        val answer: SquareMatrix = SquareMatrix()
        answer.createEmptyMatrix(getSize().first)

        for (row in 0..<getSize().first) {
            for (col in 0..<getSize().second) {
                answer.setValue(row, col,getValue(row, col) - other.getValue(row, col))

            }
        }

        return answer
    }

    /**
     * Square section of matrix
     *
     * @param startIdxRow (included): Int representing the index to start subsection rows at
     * @param endIdxRow (not included): Int representing the index to end subsection at (not included)
     * @param startIdxCol (included): Int representing the index to start subsection columns at
     * @param endIdxCol (not included): Int representing the index to end section columns at (not included)
     *
     * @return: A SquareMatrix that is a subsection of the current matrix from the startIdx to the endIdx (not inclusive)
     */
    fun subsection(startIdxRow: Int, endIdxRow: Int, startIdxCol: Int, endIdxCol: Int): SquareMatrix {
        if ((endIdxRow - startIdxRow) != (endIdxCol - startIdxCol)) {
            return SquareMatrix()
        }

        val submatrix: MutableList<MutableList<Int>>  = mutableListOf()

        for (row in startIdxRow..<endIdxRow) {
            val included = matrix[row].subList(startIdxCol, endIdxCol)
            submatrix.add(included)
        }
        return SquareMatrix(submatrix)
    }

    /**
     * Place subsections together
     *
     * @param A: A SquareMatrix that is placed in the first quadrant
     * @param B: A SquareMatrix that is placed in the second quadrant
     * @param C: A SquareMatrix that is placed in the third quadrant
     * @param D: A SquareMatrix that is placed in the fourth quadrant
     *
     * @return: A MutableList with all 4 quadrants placed in
     */
    fun combineMatrix(A: SquareMatrix?, B: SquareMatrix?, C: SquareMatrix?, D: SquareMatrix?): SquareMatrix {
        if (A == null || B == null|| C == null || D == null) {
            return SquareMatrix()
        }

        val answer: SquareMatrix = SquareMatrix()
        answer.createEmptyMatrix(A.getSize().first + C.getSize().first)

        // Add all of A and C to rows of answer
        var thisRow: Int = 0
        for (row in 0..<A.getSize().first) {
            answer.setRow(thisRow, A.getRow(row))
            thisRow += 1
        }
        for (row in 0..<C.getSize().first) {
            answer.setRow(thisRow, C.getRow(row))
            thisRow += 1
        }

        // Add all of B and D now
        thisRow = 0
        for (row in 0..<B.getSize().first) {
            val currRow: MutableList<Int> = answer.getRow(thisRow)
            val newRow: MutableList<Int> = B.getRow(row)
            currRow.addAll(newRow)
            answer.setRow(thisRow, currRow)
            thisRow += 1
        }
        for (row in 0..<D.getSize().first) {
            val currRow: MutableList<Int> = answer.getRow(thisRow)
            val newRow: MutableList<Int> = D.getRow(row)
            currRow.addAll(newRow)
            answer.setRow(thisRow, currRow)
            thisRow += 1
        }
        return answer
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
        result.createEmptyMatrix(getSize().second) // Create empty matrix to store results

        for (row1 in 0..<getSize().first) {
            for (column2 in 0 ..<other.getSize().second) {
                for (row2 in 0..<other.getSize().first) {
                    val newValue: Int = result.getValue(row1, column2) + (getValue(row1, row2) * other.getValue(row2, column2))
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

        val answer: SquareMatrix = SquareMatrix()
        answer.createEmptyMatrix(getSize().first)

        // If at base case, return current
        if (getSize().first == 1) {
            return SquareMatrix(mutableListOf(mutableListOf(matrix[0][0] * other.getValue(0, 0))))
        }

        // If not at base case, divide and add back into position
        else {
            // Get subsections of each matrix
            val matA = subsection(0, getSize().first / 2, 0, getSize().first / 2)
            val matB = subsection(0, getSize().first / 2, getSize().first / 2, getSize().first)
            val matC = subsection(getSize().first / 2, getSize().first, 0, getSize().first / 2)
            val matD = subsection(getSize().first / 2, getSize().first, getSize().first / 2, getSize().first)
            val matE = other.subsection(0, getSize().first / 2, 0, getSize().first / 2)
            val matF = other.subsection(0, getSize().first / 2, getSize().first / 2, getSize().first)
            val matG = other.subsection(getSize().first / 2, getSize().first, 0, getSize().first / 2)
            val matH = other.subsection(getSize().first / 2, getSize().first, getSize().first / 2, getSize().first)

            // Multiply correct subsections
            val p1 = matA.strassenMultiply(matF.subtractMatrix(matH))
            val p2 = (matA.addMatrix(matB)).strassenMultiply(matH)
            val p3 = (matC.addMatrix(matD)).strassenMultiply(matE)
            val p4 = matD.strassenMultiply(matG.subtractMatrix(matE))
            val p5 = (matA.addMatrix(matD)).strassenMultiply((matE.addMatrix(matH)))
            val p6 = (matB.subtractMatrix(matD)).strassenMultiply((matG.addMatrix(matH)))
            val p7 = (matA.subtractMatrix(matC)).strassenMultiply((matE.addMatrix(matF)))

            // Get final quadrants
            val quad1 = p6?.let { (p2?.let { (p4?.let { p5!!.addMatrix(it) })?.subtractMatrix(it) })?.addMatrix(it) }
            val quad2 = p2?.let { p1?.addMatrix(it) }
            val quad3 = p3?.let { p4?.addMatrix(it)}
            val quad4 = p7?.let { (p3?.let { (p5?.let { p1?.addMatrix(it) })?.subtractMatrix(it) })?.subtractMatrix(it) }

            return combineMatrix(quad1, quad2, quad3, quad4)

        }
    }

}