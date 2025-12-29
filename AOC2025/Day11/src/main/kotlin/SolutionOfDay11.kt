import MachineNode.Companion.toNode
import isdemidoff.utility.graphs.Node
import isdemidoff.utility.graphs.findCountOfPaths
import isdemidoff.utility.input.readLines

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

    companion object {
        fun String.toNode() = split(": ")
            .also { require(it.size == 2) { "Expected exactly 2 tokens in form \"%id%: %outputs%\", but got $this" } }
            .let { MachineNode(id = it[0], outputNames = it[1].split("""\s+""".toRegex())) }
    }
}

val FROM_NODE_ID = "you"
val TO_NODE_ID = "out"

fun solveForFileName(fileName: String) = readLines(fileName)
    .map { it.toNode() }
    .associateByTo(mutableMapOf()) { it.id }
    .also { it.computeIfAbsent(TO_NODE_ID) { MachineNode(id = TO_NODE_ID, outputNames = listOf()) } }
    .also { nodes -> nodes.values.forEach { it.assignOutputs(nodes) } }
    .let { findCountOfPaths(it, FROM_NODE_ID, TO_NODE_ID) }

fun findCountOfPaths(allNodes: Map<String, MachineNode>, fromNodeId: String, toNodeId: String): Int {
    return findCountOfPaths(
        allNodes = allNodes.values,
        fromNode = requireNotNull(allNodes[fromNodeId]),
        toNode = requireNotNull(allNodes[toNodeId])
    )
}
