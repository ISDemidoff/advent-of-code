package isdemidoff.year2025.day8.entities

data class JunctionBoxConnection(
    val first: JunctionBox,
    val second: JunctionBox,
) {
    val singular: Boolean = first == second

    fun enableConnection() {
        println("Connecting $first and $second")
        first connectTo second
    }

    fun length() = first distanceTo second

    override fun equals(other: Any?): Boolean {
        // Make equality symmetric: connections satisfies first to second == second to first
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JunctionBoxConnection

        if (this.first == other.first && this.second == other.second) return true
        if (this.first == other.second && this.second == other.first) return true

        return false
    }

    override fun hashCode(): Int {
        // HashCode must be symmetric too: just call symmetric numeric functions of components hash codes
        return first.hashCode() + second.hashCode()
    }
}

fun Pair<JunctionBox, JunctionBox>.createConnection() = JunctionBoxConnection(first, second)