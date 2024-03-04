package org.dsa.assignment5

/**
 * Make matrix of zeros of given size
 *
 * @param row: An int representing the rows in the matrix
 * @param col: An int representing the columns in the matrix
 *
 * @return A MutableList<MutableList<Int>> of zeros of the size given
 */
fun matrix(row: Int, col: Int): MutableList<MutableList<Int>> {
    val matrix: MutableList<MutableList<Int>> = mutableListOf()
    for (i in 0..<row) {
        val thisRow: MutableList<Int> = MutableList(col) {0} // Initialize row to be 0s of length col
        matrix += thisRow
    }
    return matrix
}

/**
 * Scoring function for NeedlemanWunch
 *
 * This gives a score of 1 if the two match and -1 if they are different (or empty)
 *
 * @param base1: A char representing the first nucleotide to compare
 * @param base2: A char representing the second nucleotide to compare
 *
 * @return: An int representing the score of the match
 */
fun score(base1: Char, base2: Char): Int {
    if (base1 == base2) {
        return 1
    }
    return -1
}
fun needlemanWunsch(seq1: String, seq2: String): Pair<String, String> {
    val seqOneLen: Int = seq1.length
    val seqTwoLen: Int = seq2.length

    // Make matrix of scores
    var scoreMatrix = matrix(seqTwoLen + 1, seqOneLen + 1)

    // Fill in matrix of scores first row and column
    for (row in 0..<seqTwoLen + 1) {
        scoreMatrix[row][0] = row * -1 // Initiate the second column of the matrix as given in the description
    }
    for (column in 0..<seqOneLen + 1) {
        scoreMatrix[0][column] = column * -1
    }

    // Fill in rest of matrix
    for (row in 1..<seqTwoLen + 1) {
        for (column in 1..<seqOneLen + 1) {
            // Move through every cell and compare the scores of the left, top, or top-left cell and add to the maximum
            // value from the three to the score of this cell. Left and top indicate an insertion or deletion and have
            // a value of -1, diagonal depends on whether it matches or not
            val top = scoreMatrix[row - 1][column] - 1
            val left = scoreMatrix[row][column - 1] - 1
            val diag = scoreMatrix[row - 1][column - 1] + score(seq1[column - 1], seq2[row - 1])
            scoreMatrix[row][column] = maxOf(top, left, diag)
        }
    }

    // Traceback for alignment
    var alignment1: String = ""
    var alignment2: String = ""

    // Start at the bottom corner
    var row: Int = seqTwoLen
    var column: Int = seqOneLen

    while (row > 0 && column > 0) {
        val thisScore = scoreMatrix[row][column]
        val diagScore = scoreMatrix[row - 1][column - 1]
        val topScore = scoreMatrix[row - 1][column]
        val leftScore = scoreMatrix[row][column - 1]

        // Find which one matches current score and trace back to there
        if (thisScore == diagScore + score(seq1[column - 1], seq2[row - 1])) {
            alignment1 = alignment1.plus(seq1[column - 1])
            alignment2 = alignment2.plus(seq2[row - 1])
            row -= 1
            column -= 1
        } else if (thisScore == topScore - 1) {
            alignment2 = alignment2.plus(seq2[row - 1])
            alignment1 = alignment1.plus('-')
            row -= 1
        } else {
            alignment1 = alignment1.plus(seq1[column - 1])
            alignment2 = alignment2.plus('-')
            column -= 1
        }
    }

    // Finish all the way till the top corner
    while (column > 0) {
        alignment1 = alignment1.plus(seq1[column - 1])
        alignment2 = alignment2.plus('-')
        column -= 1
    }
    while (row > 0) {
        alignment2 = alignment2.plus(seq2[row - 1])
        alignment1 = alignment1.plus('-')
        row -= 1
    }

    // Need to reverse strings
    alignment1 = alignment1.reversed()
    alignment2 = alignment2.reversed()

    return(Pair(alignment1, alignment2))
}
