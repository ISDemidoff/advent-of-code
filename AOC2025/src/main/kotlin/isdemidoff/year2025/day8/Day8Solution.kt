package isdemidoff.year2025.day8

import isdemidoff.Solution
import isdemidoff.SolutionBuilder
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.graphs.extractConnectedComponents
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseUnescapedCsvInputLines
import isdemidoff.year2025.day8.entities.JunctionBox
import isdemidoff.year2025.day8.entities.JunctionBoxConnection
import isdemidoff.year2025.day8.entities.createConnection
import isdemidoff.year2025.day8.entities.toJunctionBox

private fun makeConnectionsGrid(boxes: List<JunctionBox>) =
    cartesianProduct(boxes, boxes) { it.createConnection() }
        .filter { !it.singular }
        .toSet()

private fun connectClosest(connections: Set<JunctionBoxConnection>, numConnections: Int) =
    connections.sortedBy { it.length() }
        .take(numConnections)
        .forEach { it.enableConnection() }


class Day8Solution(
    val filename: String,
    val numConnections: Int,
) : Solution<Int> {
    fun parseInput(): List<JunctionBox> = readLines(filename)
        .parseUnescapedCsvInputLines { it.toString().toLong() }
        .map { it.toJunctionBox() }

    fun solveForParsedInput(parsedInput: List<JunctionBox>): Int {
        val connectionsGrid = makeConnectionsGrid(parsedInput)
        connectClosest(connectionsGrid, numConnections)

        return extractConnectedComponents(parsedInput)
            .map { it.size }
            .sortedDescending()
            .take(3)
            .reduce(Int::times)
    }

    override fun solve() = solveForParsedInput(parseInput())
}

class Day8SolutionBuilder(
    val day8Path: String,
    val numConnections: Int = 0,
) : SolutionBuilder<Int> {
    fun forNumConnections(numConnections: Int): Day8SolutionBuilder = Day8SolutionBuilder(day8Path, numConnections)
    override fun build(filename: String): Solution<Int> = Day8Solution("${this.day8Path}/$filename", this.numConnections)
}