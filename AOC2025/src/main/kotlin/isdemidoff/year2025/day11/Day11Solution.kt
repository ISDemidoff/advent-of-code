package isdemidoff.year2025.day11

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.graphs.findCountOfPaths
import isdemidoff.utility.input.readLines
import isdemidoff.year2025.day11.entity.MachineNode
import isdemidoff.year2025.day11.entity.toNode

private fun findCountOfPaths(allNodes: Map<String, MachineNode>, fromNodeId: String, toNodeId: String): Int {
    return findCountOfPaths(
        allNodes = allNodes.values,
        fromNode = requireNotNull(allNodes[fromNodeId]),
        toNode = requireNotNull(allNodes[toNodeId])
    )
}

class Day11SolutionBuilder(day11Path: String) : SimpleSolutionBuilder<Int, List<MachineNode>>(
    day11Path,
    { readLines(it).map { it.toNode() } },
    { parsedInput ->
        parsedInput.associateByTo(mutableMapOf()) { it.id }
            .also { it.computeIfAbsent(TO_NODE_ID) { MachineNode(id = it, outputNames = listOf()) } }
            .also { allNodes -> allNodes.values.forEach { it.assignOutputs(allNodes) } }
            .let { findCountOfPaths(it, FROM_NODE_ID, TO_NODE_ID) }
    },
) {
    companion object {
        const val FROM_NODE_ID = "you"
        const val TO_NODE_ID = "out"
    }
}