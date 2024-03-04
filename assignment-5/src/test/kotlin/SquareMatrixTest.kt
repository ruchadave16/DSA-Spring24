package org.dsa.assignment5

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class SquareMatrixTest {
    @Test
    fun createEmptyMatrix() {
        val matrix: SquareMatrix = SquareMatrix()

        // Check that square matrix initializes to correct size
        matrix.createEmptyMatrix(2)
        assertTrue(matrix.getSize() == Pair(2, 2))

        // Check that all values are 0 initially
        assertTrue(matrix.getValue(0, 0) == 0)
        assertTrue(matrix.getValue(0, 1) == 0)
        assertTrue(matrix.getValue(1, 0) == 0)
        assertTrue(matrix.getValue(1, 1) == 0)
    }

    @Test
    fun getValue() {
        val matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(3, 4), mutableListOf(2, 1)))

        // Check that getting value from initialized matrix works
        assertTrue(matrix.getValue(0, 0) == 3)
        assertTrue(matrix.getValue(0, 1) == 4)
        assertTrue(matrix.getValue(1, 0) == 2)
        assertTrue(matrix.getValue(1, 1) == 1)
    }

    @Test
    fun setValue() {
        val matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(3, 4), mutableListOf(2, 1)))

        // Check that setting value works correct
        matrix.setValue(0, 1, 10)
        assertTrue(matrix.getValue(0, 1) == 10)

        // Check that no other values are changed
        assertTrue(matrix.getValue(0, 0) == 3)
        assertTrue(matrix.getValue(1, 0) == 2)
        assertTrue(matrix.getValue(1, 1) == 1)
    }

    @Test
    fun getSize() {
        var matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(3, 4), mutableListOf(2, 1)))

        // Check that initialize matrix has right size
        assertTrue(matrix.getSize() == Pair(2, 2))

        // Check that empty matrix gets right size
        matrix.createEmptyMatrix(4)
        assertTrue(matrix.getSize() == Pair(4, 4))
    }

    @Test
    fun equalsMatrix() {
        var emp1 = SquareMatrix()
        var emp2 = SquareMatrix()
        var emp3 = SquareMatrix()
        emp1.createEmptyMatrix(2)
        emp2.createEmptyMatrix(2)
        emp3.createEmptyMatrix(3)

        // Ensure matrices empty of same size are counted as equal
        assertTrue(emp1.equalsMatrix(emp2))

        // Ensure empty matrices of different sizes are counted as not equal
        assertFalse(emp2.equalsMatrix(emp3))

        var matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(3, 1), mutableListOf(2, 1)))
        var matrix2: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1), mutableListOf(5, -2)))
        var matrix3: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1), mutableListOf(5, -2)))
        var matrix4: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1, 2), mutableListOf(5, -2, 3), mutableListOf(2, 2, 1)))

        // Ensure matrices filled of different sizes are counted as not equal
        assertFalse(matrix.equalsMatrix(matrix4))
        assertFalse(matrix4.equalsMatrix(matrix2))

        // Ensure matrices of same size but different values are false
        assertFalse(matrix.equalsMatrix(matrix2))

        // Ensure matrices of same size and same values are true
        assertTrue(matrix2.equalsMatrix(matrix3))
    }

    @Test
    fun subtractMatrix() {
        var matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(3, 1), mutableListOf(2, 1)))
        var matrix2: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1), mutableListOf(5, -2)))
        var answer: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(2, 0), mutableListOf(-3, 3)))

        assertTrue(matrix.subtractMatrix(matrix2).equalsMatrix(answer))
    }

    @Test
    fun subsection() {
        var matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1, 2), mutableListOf(5, -2, 3), mutableListOf(2, 2, 1)))
        val subsec = matrix.subsection(1, 3, 1, 3)
        val answer = SquareMatrix(mutableListOf(mutableListOf(-2, 3), mutableListOf(2, 1)))

        // Ensure subsection gets correct sections
        assertTrue(subsec.equalsMatrix(answer))
    }

    @Test
    fun multiply() {
        var matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(3, 4), mutableListOf(2, 1)))
        var matrix2: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1), mutableListOf(5, -2)))
        var matrix3: SquareMatrix = SquareMatrix()
        matrix3.createEmptyMatrix(4)

        val answer: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(23, -5), mutableListOf(7, 0)))

        // Check that multiplying 2 of different size returns null
        assertTrue(matrix.multiply(matrix3) == null)
        assertTrue(matrix3.multiply(matrix2) == null)

        val multiply = matrix.multiply(matrix2)

        // Check that correct matrix multiplication if compatible
        assertTrue(multiply!!.equalsMatrix(answer))
    }

    @Test
    fun strassenMultiply() {
        var matrix: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(3, 4), mutableListOf(2, 1)))
        var matrix2: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1), mutableListOf(5, -2)))
        var matrix3: SquareMatrix = SquareMatrix()
        matrix3.createEmptyMatrix(4)

        val answer: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(23, -5), mutableListOf(7, 0)))

        // Check that multiplying 2 of different size returns null
        assertTrue(matrix.multiply(matrix3) == null)
        assertTrue(matrix3.multiply(matrix2) == null)

        val multiply = matrix.strassenMultiply(matrix2)

        // Check that correct matrix multiplication for larger matrix
        assertTrue(multiply!!.equalsMatrix(answer))

        var matrix4: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1, 1, 1), mutableListOf(2, 2, 2, 2), mutableListOf(3, 3, 3, 3), mutableListOf(2, 2, 2, 2)))
        var matrix5: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(1, 1, 1, 1), mutableListOf(2, 2, 2, 2), mutableListOf(3, 3, 3, 3), mutableListOf(2, 2, 2, 2)))
        var answer2: SquareMatrix = SquareMatrix(mutableListOf(mutableListOf(8, 8, 8, 8), mutableListOf(16, 16, 16, 16 ), mutableListOf(24, 24, 24, 24), mutableListOf(16, 16, 16, 16)))

        val multiply2 = matrix4.strassenMultiply(matrix5)
        assertTrue(multiply2!!.equalsMatrix(answer2))

    }
}