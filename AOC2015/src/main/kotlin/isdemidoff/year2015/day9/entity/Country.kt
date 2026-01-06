package isdemidoff.year2015.day9.entity

import isdemidoff.utility.permutations

class Country(
    citiesRaw: List<String>,
) {
    private val cities = mutableMapOf<String, City>()

    init {
        citiesRaw.map { rawCity ->
            val (connection, distance) = rawCity.split(" = ")
                .also { check(it.size == 2) }
                .let { it[0] to it[1].toInt() }

            val (from, to) = connection.split(" to ")
                .also { check(it.size == 2) }
                .let { it[0] to it[1] }

            (createCity(from) to createCity(to)) to distance
        }.forEach { (connection, distance) -> connection setDistance distance }
    }

    private fun createCity(name: String) = cities.computeIfAbsent(name) { City(it) }

    private fun calculatePath(path: List<String>) =
        path.zipWithNext { from, to -> cities[from]!!.distanceTo(cities[to]!!) }.sum()

    fun findShortestPath() = cities.keys.sorted().permutations().minOf { calculatePath(it) }
}