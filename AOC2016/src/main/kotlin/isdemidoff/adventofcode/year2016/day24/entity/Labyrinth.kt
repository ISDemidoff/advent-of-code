package isdemidoff.adventofcode.year2016.day24.entity

import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.discretemath.permutations
import isdemidoff.utility.other.forEachIndexedInMatrix

class Labyrinth(
    val cells: Array<Array<Boolean>>, // free is true, wall is false
    val pointsOfInterest: Array<Point>,
) {
    data class Point(val x: Int, val y: Int)

    fun createLengthsTable(): Array<Array<Int>> {
        val countOfPoints = pointsOfInterest.size
        val lengthsTable = Array(countOfPoints) { Array(countOfPoints) { 0 } }
        cartesianProduct(0..<countOfPoints, 0..<countOfPoints) { (from, to) ->
            lengthsTable[from][to] = when {
                from == to -> 0
                lengthsTable[to][from] != 0 -> lengthsTable[to][from]
                else -> findShortestPathLength(pointsOfInterest[from], pointsOfInterest[to])
            }
        }
        return lengthsTable
    }

    fun findShortestPathOutOfOrderWithReturn(): Int =
        with(createLengthsTable()) {
            permutations(1..<pointsOfInterest.size).minOf { order ->
                (listOf(0) + order + listOf(0)).zipWithNext { l, r -> this[l][r] }.sum()
            }
        }

    fun findShortestPathOutOfOrder(): Int =
        with(createLengthsTable()) {
            permutations(1..<pointsOfInterest.size).minOf { order ->
                (listOf(0) + order).zipWithNext { l, r -> this[l][r] }.sum()
            }
        }

    private fun findShortestPathLength(from: Point, to: Point): Int {
        val queue = ArrayDeque<Pair<Point, Int>>()
        queue.addLast(from to 0)
        val seen = mutableSetOf<Point>()
        seen.add(from)

        while (queue.isNotEmpty()) {
            val (p, depth) = queue.removeFirst()
            if (p == to) return depth
            getFreeAdjacentPoints(p)
                .filter { seen.add(it) }
                .forEach { queue.add(it to depth + 1) }
        }

        error("Not found path from $from to $to")
    }

    private fun getFreeAdjacentPoints(point: Point): List<Point> =
        listOf(
            point.copy(x = point.x - 1),
            point.copy(x = point.x + 1),
            point.copy(y = point.y - 1),
            point.copy(y = point.y + 1),
        ).filter { cells[it.x][it.y] } // Not filtering by index cause there are always walls
}

val labyrinthParser: (List<String>) -> Labyrinth = { input ->
    val cells = Array(input.size) { Array(input.first().length) { false } }
    val pointsOfInterest = mutableListOf<Pair<Int, Labyrinth.Point>>()

    input.map { it.toCharArray().toList() }
        .forEachIndexedInMatrix { index1, index2, ch ->
            cells[index1][index2] = ch != '#'
            if (ch.isDigit()) pointsOfInterest.add(ch.digitToInt() to Labyrinth.Point(index1, index2))
        }

    Labyrinth(
        cells = cells,
        pointsOfInterest = pointsOfInterest.sortedBy { it.first }.map { it.second }.toTypedArray(),
    )
}