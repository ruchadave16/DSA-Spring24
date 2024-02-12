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

    /**
     * Implement Dijkstra's Algorithm to find the shortest path between a given start and end node
     *
     * @param from: A VertexType representing the starting node
     * @param to: A VertexType representing the ending node of the path
     *
     * @returns: A list of the nodes visited and the weights of the corresponding weights and null if no path exists
     */
    fun dijkstra(from: VertexType, to: VertexType): List<VertexType> {
        // Make priority queue of all vertices and set their distances to inf except first
        val queue: PriorityQueue<VertexType> = PriorityQueue()
        val prevMap: MutableMap<VertexType, VertexType?> = mutableMapOf()
        val distanceMap: MutableMap<VertexType, Double> = mutableMapOf()
        val visitedList: MutableList<VertexType> = mutableListOf()

        for (vertex in vertices) {
            queue.addWithPriority(vertex, Double.POSITIVE_INFINITY)
            prevMap[vertex] = null
            distanceMap[vertex] = Double.POSITIVE_INFINITY
        }
        distanceMap[from] = 0.0
        queue.adjustPriority(from, 0.0)

        // loop through all vertices of queue
        while (!queue.isEmpty()) {
            val minVertex: VertexType = queue.next()!!
            visitedList.addLast(minVertex)
            val neighbors: Map<VertexType, Double> = getEdges(minVertex)

            // Go through neighbors of vertex still in the queue
            for (neighbor in neighbors.keys) {
                if (neighbor !in visitedList) {

                    // Calculate current distance and check if this is the shortest path to this vertex so far
                    val currDistance: Double = distanceMap[minVertex]?.plus(neighbors[neighbor]!!)!! // We know this will be valid
                    if (currDistance < distanceMap[neighbor]!!) {
                        distanceMap[neighbor] = currDistance
                        queue.adjustPriority(neighbor, currDistance)
                        prevMap[neighbor] = minVertex
                    }
                }
            }
        }

        // Reconstruct path
        val finalPath: MutableList<VertexType> = mutableListOf()
        var currNode: VertexType = to
        if (!prevMap.contains(to)) {
            return listOf()
        }
        while (currNode != from) {
            finalPath.add(0, currNode)
            currNode = prevMap[currNode]!!
        }
        finalPath.add(0, currNode)
        return finalPath
    }

}