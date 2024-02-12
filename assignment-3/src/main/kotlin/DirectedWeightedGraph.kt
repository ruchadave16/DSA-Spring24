package org.dsa.assignment3

/**
 * A class that represents a directed, weighted graph using the Graph interface
 *
 * @param VertexType: a representation of the vertex of the graph
 */
class DirectedWeightedGraph<VertexType> : Graph <VertexType>{
    private var vertices = mutableSetOf<VertexType>()
    private var edges = mutableMapOf<VertexType, MutableMap<VertexType, Double>>()

    /**
     * Return the vertices of the graph as a set
     *
     * @return A Set of VertexTypes representing the vertices
     */
    override fun getVertices(): Set<VertexType> {
        return vertices.toSet()
    }

    /**
     * Add new edge to the graph
     *
     * @param from A VertexType representing the starting node of the edge to be added
     * @param to A VertexType representing the ending node of the edge to be added
     * @param cost A double representing the cost of the edge to be added
     */
    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        // If starting node already in edges, then add to the map, else create a new key
        if (edges.containsKey(from)) {
            val currEdges: MutableMap<VertexType, Double> = edges[from]!!
            currEdges[to] = cost
            edges[from] = currEdges
        } else {
            edges[from] = mutableMapOf(to to cost)
            vertices.add(from)
        }

        if (!vertices.contains(to)) {
            vertices.add(to)
        }
    }

    /**
     * Return the edges of the graph at the vertex
     *
     * @param from: a VertexType representing the node to find all the edges connecting to
     *
     * @return A Map of VertexTypes mapped to Doubles that represent all the nodes connected to the inputted node and
     *         the weight of those edges
     */
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        return edges[from]?.toMap() ?: mapOf()
    }

    /**
     * Clear the graph by setting edges and vertices to empty
     */
    override fun clear() {
        vertices = mutableSetOf<VertexType>()
        edges = mutableMapOf<VertexType, MutableMap<VertexType, Double>>()
    }

}