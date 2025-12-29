package entities

data class VisitedJunctionBox(
    val junctionBox: JunctionBox,
    var visited: Boolean = false,
) {
    fun markVisited() {
        this.visited = true
    }
}