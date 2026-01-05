package isdemidoff.year2025.day8.entities

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

fun List<Long>.toJunctionBox(): JunctionBox {
    require(this.size == 3) { "Expected 3 this, got ${this.size}." }
    return JunctionBox(this[0], this[1], this[2])
}