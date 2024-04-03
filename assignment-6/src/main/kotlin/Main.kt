package org.dsa.assignment6

fun main() {
    val model: MarkovModel = MarkovModel("a subdivision of a written composition that consists of one or more sentences, deals with one point or gives the words of one speaker, and begins on a new usually indented line")
    println("composition".hashCode())
    model.generateModel()
    model.print()
}