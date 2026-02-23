package isdemidoff.adventofcode.year2025.day8.entities

import isdemidoff.utility.graphs.Node

data class JunctionBox(
    val x: Long,
    val y: Long,
    val z: Long,
) : Node<JunctionBox> {
    val connections: MutableSet<JunctionBox> = mutableSetOf<JunctionBox>()

    fun coordinatesAsList() = listOf(x, y, z)

    infix fun distanceTo(other: JunctionBox) = this.coordinatesAsList()
        .zip(other.coordinatesAsList()) { first, second -> first - second }
        .sumOf { it * it }

    infix fun connectTo(otherBox: JunctionBox) {
        this.connections += otherBox
        otherBox.connections += this
    }

    override fun getConnectedNodes() = connections.toList()
}

internal val junctionBoxConverter: (List<Long>) -> JunctionBox = {
    require(it.size == 3) { "Expected 3 ints, got ${it.size}." }
    JunctionBox(it[0], it[1], it[2])
}
