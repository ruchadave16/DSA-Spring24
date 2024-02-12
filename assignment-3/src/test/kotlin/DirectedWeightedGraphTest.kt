package org.dsa.assignment3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DirectedWeightedGraphTest {
    @Test
    fun getVertices() {
        val graph : DirectedWeightedGraph<String> = DirectedWeightedGraph()

        // Check that initializes to empty set (NOT MUTABLE)
        assertTrue(graph.getVertices().isEmpty())

        // Check that if graph has new vertices added, it correctly gets the new set of vertices
        graph.addEdge("Hi", "Rucha", 10.0)
        assertTrue(graph.getVertices() == setOf("Hi", "Rucha"))
        graph.addEdge("Rucha", "!", 0.0)
        assertTrue(graph.getVertices() == setOf("Hi", "Rucha", "!"))
        graph.addEdge("Hi", "Krishna", 100.0)
        assertTrue(graph.getVertices() == setOf("Hi", "Rucha", "!", "Krishna"))
    }

    @Test
    fun addEdge() {
        val graph : DirectedWeightedGraph<String> = DirectedWeightedGraph()

        // Check that adding an edge to an empty graph updates correctly (vertices and edges)
        graph.addEdge("Hello", "World", 1.0)
        assertTrue(graph.getVertices() == setOf("Hello", "World"))
        assertTrue(graph.getEdges("Hello") == mapOf("World" to 1.0))
        assertTrue(graph.getEdges("World") == mapOf<String, Double>())

        // Check that adding a new edge to a vertex already in the graph updates the old vertex's map
        graph.addEdge("Hello", "Krishna", 1.0)
        assertTrue(graph.getVertices() == setOf("Hello", "World", "Krishna"))
        assertTrue(graph.getEdges("Hello") == mapOf("World" to 1.0, "Krishna" to 1.0))
        assertTrue(graph.getEdges("World") == mapOf<String, Double>())
        assertTrue(graph.getEdges("Krishna") == mapOf<String, Double>())

        // Check that adding a new edge to a vertex in a graph already (but not associated with an edge) works correctly
        graph.addEdge("Krishna", "World", 2.0)
        assertTrue(graph.getVertices() == setOf("Hello", "World", "Krishna"))
        assertTrue(graph.getEdges("Hello") == mapOf("World" to 1.0, "Krishna" to 1.0))
        assertTrue(graph.getEdges("World") == mapOf<String, Double>())
        assertTrue(graph.getEdges("Krishna") == mapOf("World" to 2.0))
    }

    @Test
    fun getEdges() {
        val graph : DirectedWeightedGraph<String> = DirectedWeightedGraph()

        // Ensure that getting edges of a new graph is empty
        assertTrue(graph.getEdges("Hi") == mapOf<String, Double>())

        // Ensure that getting edges of a vertex in the graph works correctly
        graph.addEdge("Hello", "World", 1.0)
        graph.addEdge("Hello", "Krishna", 1.0)
        graph.addEdge("Krishna", "World", 2.0)
        assertTrue(graph.getEdges("Hello") == mapOf("World" to 1.0, "Krishna" to 1.0))
        assertTrue(graph.getEdges("Krishna") == mapOf("World" to 2.0))

        // Ensure getting edges of a vertex with no edges is empty
        assertTrue(graph.getEdges("World") == mapOf<String, Double>())

        // Ensure getting edges of a vertex not in the graph is empty
        assertTrue(graph.getEdges("Rucha") == mapOf<String, Double>())
    }

    @Test
    fun clear() {
        val graph : DirectedWeightedGraph<String> = DirectedWeightedGraph()

        graph.addEdge("Hello", "World", 1.0)
        graph.addEdge("Hello", "Krishna", 1.0)
        graph.addEdge("Krishna", "World", 2.0)
        graph.clear()

        // Ensure clearing sets everything to empty
        assertTrue(graph.getVertices() == setOf<String>())
        assertTrue(graph.getEdges("Hello") == mapOf<String, Double>())
    }

    @Test
    fun dijkstra() {
        // Use example from class to test dijkstras
        val graph : DirectedWeightedGraph<String> = DirectedWeightedGraph()
        graph.addEdge("start", "a", 2.0)
        graph.addEdge("start", "b", 1.0)
        graph.addEdge("start", "c", 2.0)
        graph.addEdge("a", "e", 1.0)
        graph.addEdge("b", "d", 3.0)
        graph.addEdge("c", "d", 1.0)
        graph.addEdge("e", "h", 1.0)
        graph.addEdge("e", "f", 2.0)
        graph.addEdge("h", "end", 1.0)
        graph.addEdge("d", "end", 5.0)

        assertEquals(graph.dijkstra("start", "end"), listOf("start", "a", "e", "h", "end"))
    }
}