package entities

data class JunctionBox(
    val x: Long,
    val y: Long,
    val z: Long,
) {
    val connections: MutableSet<JunctionBox> = mutableSetOf<JunctionBox>()

    fun coordinatesAsList() = listOf(x, y, z)

    infix fun distanceTo(other: JunctionBox) = this.coordinatesAsList()
        .zip(other.coordinatesAsList()) { first, second -> first - second }
        .sumOf { it * it }

    infix fun connectTo(otherBox: JunctionBox) {
        this.connections += otherBox
        otherBox.connections += this
    }

    companion object {
        fun fromCoordinates(coordinates: List<Long>): JunctionBox {
            require(coordinates.size == 3) { "Expected 3 coordinates, got ${coordinates.size}." }
            return JunctionBox(coordinates[0], coordinates[1], coordinates[2])
        }
    }
}