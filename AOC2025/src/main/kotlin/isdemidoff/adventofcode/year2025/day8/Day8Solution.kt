package isdemidoff.adventofcode.year2025.day8

import isdemidoff.adventofcode.year2025.day8.entities.JunctionBox
import isdemidoff.adventofcode.year2025.day8.entities.JunctionBoxConnection
import isdemidoff.adventofcode.year2025.day8.entities.createConnection
import isdemidoff.adventofcode.year2025.day8.entities.toJunctionBox
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.graphs.extractConnectedComponents
import isdemidoff.utility.parseUnescapedCsvInputLine
import isdemidoff.utility.solution.solution
import isdemidoff.utility.solution.solver.solver

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
    inputParser = uniformLinesParser { it.parseUnescapedCsvInputLine { it.toString().toLong() }.toJunctionBox() }

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