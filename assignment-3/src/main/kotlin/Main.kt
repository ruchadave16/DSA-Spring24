package org.dsa.assignment3

fun main() {
    // Implement Dijkstra's to find shortest path from city A to B based on time vs distance
    // Referenced data from: https://www.visitma.com/wp-content/uploads/2015/03/city-to-city-driving-distances-1.jpg
    val distGraph : DirectedWeightedGraph<String> = DirectedWeightedGraph()

    // Distance Data
    distGraph.addEdge("North Adams", "Pittsfield", 22.0)
    distGraph.addEdge("Pittsfield", "Lee", 12.0)
    distGraph.addEdge("North Adams", "Greenfield", 38.0)
    distGraph.addEdge("Lee", "Springfield", 41.0)
    distGraph.addEdge("Northampton", "Springfield", 19.0)
    distGraph.addEdge("Springfield", "Northampton", 19.0)
    distGraph.addEdge("Greenfield", "Northampton", 21.0)
    distGraph.addEdge("Northampton", "Greenfield", 21.0)
    distGraph.addEdge("Greenfield", "Fitchburg", 49.0)
    distGraph.addEdge("Greenfield", "Worchester", 61.0)
    distGraph.addEdge("Springfield", "Worchester", 55.0)
    distGraph.addEdge("Fitchburg", "Worchester", 27.0)
    distGraph.addEdge("Worchester", "Fitchburg", 27.0)
    distGraph.addEdge("Fitchburg", "Lowell", 33.0)
    distGraph.addEdge("Fitchburg", "Boston", 46.0)
    distGraph.addEdge("Worchester", "Boston", 44.0)
    distGraph.addEdge("Lowell", "Boston", 28.0)


    // Time Data
    val timeGraph : DirectedWeightedGraph<String> = DirectedWeightedGraph()

    timeGraph.addEdge("North Adams", "Pittsfield", 35.0)
    timeGraph.addEdge("Pittsfield", "Lee", 21.0)
    timeGraph.addEdge("North Adams", "Greenfield", 38.0)
    timeGraph.addEdge("Lee", "Springfield", 50.0)
    timeGraph.addEdge("Northampton", "Springfield", 25.0)
    timeGraph.addEdge("Springfield", "Northampton", 25.0)
    timeGraph.addEdge("Greenfield", "Northampton", 25.0)
    timeGraph.addEdge("Northampton", "Greenfield", 25.0)
    timeGraph.addEdge("Greenfield", "Fitchburg", 105.0)
    timeGraph.addEdge("Greenfield", "Worchester", 120.0)
    timeGraph.addEdge("Springfield", "Worchester", 105.0)
    timeGraph.addEdge("Fitchburg", "Worchester", 35.0)
    timeGraph.addEdge("Worchester", "Fitchburg", 35.0)
    timeGraph.addEdge("Fitchburg", "Lowell", 40.0)
    timeGraph.addEdge("Fitchburg", "Boston", 110.0)
    timeGraph.addEdge("Worchester", "Boston", 100.0)
    timeGraph.addEdge("Lowell", "Boston", 40.0)

    // Test Dijkstra's
    val distanceList: List<String> = distGraph.dijkstra("North Adams", "Boston")
    val timeList: List<String> = timeGraph.dijkstra("North Adams", "Boston")
    println(distanceList)
    println(timeList)



}