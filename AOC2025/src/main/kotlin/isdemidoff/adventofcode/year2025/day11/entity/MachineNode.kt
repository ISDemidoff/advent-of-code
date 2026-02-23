package isdemidoff.adventofcode.year2025.day11.entity

import isdemidoff.utility.graphs.Node

data class MachineNode(
    val id: String,
    val outputNames: List<String>,
    val outputs: MutableList<MachineNode> = mutableListOf(),
) : Node<MachineNode> {

    fun assignOutputs(allNodes: Map<String, MachineNode>) = outputNames.forEach {
        outputs.add(requireNotNull(allNodes[it]) { "Node with id $it.id does not exist." })
    }

    override fun getConnectedNodes() = outputs
    override fun getIdentity() = id
}

fun parseNode(input: String) = input.split(": ")
    .also { require(it.size == 2) { "Expected exactly 2 tokens in form \"%id%: %outputs%\", but got $input" } }
    .let { MachineNode(id = it[0], outputNames = it[1].split("""\s+""".toRegex())) }