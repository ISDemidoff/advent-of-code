package isdemidoff.adventofcode.year2025.day8

import isdemidoff.adventofcode.year2025.day8.entities.JunctionBox
import isdemidoff.adventofcode.year2025.day8.entities.JunctionBoxConnection
import isdemidoff.adventofcode.year2025.day8.entities.createConnection
import isdemidoff.adventofcode.year2025.day8.entities.junctionBoxConverter
import isdemidoff.solution.inputparser.functions.mapLines
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.solution.solver.solver
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.graphs.extractConnectedComponents
import isdemidoff.utility.parsing.toLongsList

/**
 * [Day 8: Playground](https://adventofcode.com/2025/day/8).
 */
private fun makeConnectionsGrid(boxes: List<JunctionBox>) =
    cartesianProduct(boxes, boxes) { it.createConnection() }
        .filter { !it.singular }
        .toSet()

private fun connectClosest(connections: Set<JunctionBoxConnection>, numConnections: Int) =
    connections.sortedBy { it.length() }
        .take(numConnections)
        .forEach { it.enableConnection() }


val day8 = solution(8) {
    inputParser = StringsInputParsers.splitLinesBy(",")
        .mapLines(::toLongsList)
        .mapLines(junctionBoxConverter)

    part1Solver = solver<List<JunctionBox>, Int, Int>({ result, num ->
        "Product of sizes of 3 largest circuits after $num closest connections is $result."
    }) { boxes, numConnections ->
        val connectionsGrid = makeConnectionsGrid(boxes)
        connectClosest(connectionsGrid, numConnections)

        extractConnectedComponents(boxes)
            .map { it.size }
            .sortedDescending()
            .take(3)
            .reduce(Int::times)
    }
}