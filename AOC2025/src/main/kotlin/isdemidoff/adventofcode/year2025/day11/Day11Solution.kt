package isdemidoff.adventofcode.year2025.day11

import isdemidoff.adventofcode.year2025.day11.Constants.FROM_NODE_ID
import isdemidoff.adventofcode.year2025.day11.Constants.TO_NODE_ID
import isdemidoff.adventofcode.year2025.day11.entity.MachineNode
import isdemidoff.adventofcode.year2025.day11.entity.parseNode
import isdemidoff.utility.graphs.findCountOfPaths
import isdemidoff.utility.solution.solution

@Suppress("SameParameterValue")
private fun findCountOfPaths(allNodes: Map<String, MachineNode>, fromNodeId: String, toNodeId: String): Int {
    return findCountOfPaths(
        allNodes = allNodes.values,
        fromNode = requireNotNull(allNodes[fromNodeId]),
        toNode = requireNotNull(allNodes[toNodeId])
    )
}

private object Constants {
    const val FROM_NODE_ID = "you"
    const val TO_NODE_ID = "out"
}

/**
 * [Day 11: Reactor](https://adventofcode.com/2025/day/11).
 */
val day11 = solution(11) {
    inputParser = uniformLinesParser(::parseNode)

    part1Solver = solver({ "Path count between $FROM_NODE_ID to $TO_NODE_ID is $it." }) {
        it.associateByTo(mutableMapOf()) { it.id }
            .also { it.computeIfAbsent(TO_NODE_ID) { MachineNode(id = it, outputNames = listOf()) } }
            .also { allNodes -> allNodes.values.forEach { it.assignOutputs(allNodes) } }
            .let { findCountOfPaths(it, FROM_NODE_ID, TO_NODE_ID) }
    }
}