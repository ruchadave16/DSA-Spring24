package com.dsa.classwork6

import com.dsa.assignment2.MyQueue

class Graph<VertexType> {
    private var vertices: MutableSet<VertexType> = mutableSetOf()
    private var edges: MutableMap<VertexType, MutableSet<VertexType>> = mutableMapOf()

    /**
     * Add the vertex [v] to the graph
     * @param v the vertex to add
     * @return true if the vertex is successfully added, false if the vertex
     *   was already in the graph
     */
    fun addVertex(v: VertexType): Boolean {
        if (vertices.contains(v)) {
            return false
        }
        vertices.add(v)
        return true
    }

    /**
     * Add an edge between vertex [from] connecting to vertex [to]
     * @param from the vertex for the edge to originate from
     * @param to the vertex to connect the edge to
     * @return true if the edge is successfully added and false if the edge
     *     can't be added or already exists
     */
    fun addEdge(from: VertexType, to: VertexType): Boolean {
        if (!vertices.contains(from) || !vertices.contains(to)) {
            return false
        }
        edges[from]?.also { currentAdjacent ->
            if (currentAdjacent.contains(to)) {
                return false
            }
            currentAdjacent.add(to)
        } ?: run {
            edges[from] = mutableSetOf(to)
        }
        return true
    }

    /**
     * Find all the neighbors of a given vertex
     *
     * @param v: A VertexType to find the neighbors for
     *
     * @return A MutableSet of VertexTypes representing all the neighbors of the vertex v
     */
    fun findNeighbors(v: VertexType?): MutableSet<VertexType>? {
        return edges[v]
    }

    /**
     * Perform a breadth first search to find the inputted vertex
     *
     * @param target: A VertexType representing the node to be found in the tree
     *
     * @return true if the target is in the tree else false
     */
    fun breadthFirst(target: VertexType): Boolean {
        val root: VertexType = vertices.first()
        val visited: MutableSet<VertexType> = mutableSetOf()
        val priority: MyQueue<VertexType> = MyQueue()

        priority.enqueue(root)
        visited.add(root)

        while (!priority.isEmpty()) {
            val nodeToBreak: VertexType? = priority.dequeue()
            if (nodeToBreak == target) {
                return true
            }

            val neighbors: MutableSet<VertexType>? = findNeighbors(nodeToBreak)
            if (neighbors != null) {
                for (neighbor in neighbors) {
                    if (!visited.contains(neighbor)) {
                        priority.enqueue(neighbor)
                        visited.add(neighbor)
                    }
                }
            }
        }
        return false
    }

    /**
     * Perform a depth first search to find the inputted vertex
     *
     * @param target: A VertexType representing the node to be found in the tree
     *
     * @return true if the target is in the tree else false
     */
    fun depthFirst(target: VertexType): Boolean {
        val root: VertexType = vertices.first()
        val visited: MutableSet<VertexType> = mutableSetOf()
        val priority: MyQueue<VertexType> = MyQueue()

        priority.enqueue(root)
        visited.add(root)

        while (!priority.isEmpty()) {
            val nodeToBreak: VertexType? = priority.dequeue()
            if (nodeToBreak == target) {
                return true
            }

            val neighbors: MutableSet<VertexType>? = findNeighbors(nodeToBreak)
            if (neighbors != null) {
                for (neighbor in neighbors) {
                    if (!visited.contains(neighbor)) {
                        priority.enqueue(neighbor)
                        visited.add(neighbor)
                        break
                    }
                }
            }
        }
        return false
    }

    /**
     * Clear all vertices and edges
     */
    fun clear() {
        vertices = mutableSetOf()
        edges = mutableMapOf()
    }
}
