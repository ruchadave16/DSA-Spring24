package org.dsa.assignment5

fun main() {
    val seq1: String = "ATTACA"
    val seq2: String = "TATTGAATAAATTGCT"

    val answer: Pair<String, String> = needlemanWunsch(seq1, seq2)
    println(answer.first)
    println(answer.second)
}