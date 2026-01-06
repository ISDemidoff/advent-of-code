package isdemidoff.year2015.day9.entity

data class City(
    val name: String,
) {
    private val distances = mutableMapOf<City, Int>()

    fun distanceTo(other: City) = distances.getOrDefault(other, 10000)

    internal fun assignDistance(other: City, distance: Int) {
        distances[other] = distance
    }
}

infix fun Pair<City, City>.setDistance(distance: Int) {
    this.first.assignDistance(this.second, distance)
    this.second.assignDistance(this.first, distance)
}