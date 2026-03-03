package isdemidoff.adventofcode.year2017.day7.entity

data class ProgramNode(
    val name: String,
    val value: Int,
) {
    var parent: ProgramNode? = null
    val children: MutableList<ProgramNode> = mutableListOf()
    val totalWeight: Int by lazy { value + children.sumOf { it.totalWeight } }

    fun isUnbalancedSubTree(): Boolean =
        this.children.size > 2 && this.children.map { it.totalWeight }.toSet().size > 1

    infix fun makeParentOf(other: ProgramNode) {
        this.children.add(other)
        other.parent = this
    }
}

data class ProgramGraph(private val nodes: List<ProgramNode>) {
    fun findBottomProgram(): ProgramNode = nodes.first { it.parent == null }

    fun findUnbalancedProgramDesiredState(): Int = nodes
        .first { it.isUnbalancedSubTree() && it.children.all { !it.isUnbalancedSubTree() } }
        .children
        .groupBy(ProgramNode::totalWeight)
        .let { unbalancedSubTree ->
            require(unbalancedSubTree.size == 2) { "There must be only two values" }
            val targetWeight = unbalancedSubTree.entries.first { it.value.size > 1 }.key
            val unbalancedNode = unbalancedSubTree.entries.first { it.key != targetWeight }.value.single()

            targetWeight + unbalancedNode.value - unbalancedNode.totalWeight
        }
}

internal val createGraph: (List<ProgramNodeDefinition>) -> ProgramGraph = { definitions ->
    val nodesByName = definitions.associateBy(ProgramNodeDefinition::name) { ProgramNode(it.name, it.value) }

    fun getNode(name: String): ProgramNode = requireNotNull(nodesByName[name]) { "There must be node with name $name" }

    definitions.forEach { def ->
        def.childrenNames.forEach { childName -> getNode(def.name) makeParentOf getNode(childName) }
    }

    ProgramGraph(nodesByName.values.toList())
}
