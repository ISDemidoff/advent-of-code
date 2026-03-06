package isdemidoff.adventofcode.year2016.day16

import isdemidoff.solution.inputparser.scope.singleLine
import isdemidoff.solution.solution
import isdemidoff.solution.solver.solver

fun invertString(str: String) = str.map { if (it == '0') '1' else '0' }.joinToString("")

fun dragonCurveString(str: String) = "${str}0${invertString(str).reversed()}"

fun checksumIteration(str: String): String {
    require(str.length % 2 == 0) { "Expecting an even length" }

    val result = StringBuilder()
    val iterator = str.iterator()
    while (iterator.hasNext()) {
        val (l, r) = iterator.next() to iterator.next()
        if (l == r) result.append('1') else result.append('0')
    }
    return result.toString()
}

/**
 * [Day 16: Dragon Checksum](https://adventofcode.com/2016/day/16).
 */
val day16 = solution(16) {
    inputParser = singleLine

    part1Solver = solver<String, String, Int>({ res, _ ->
        "Checksum of written data is $res."
    }) { input, desiredLength ->
        var result = input

        // Populate a string to fit disk
        while (result.length < desiredLength) {
            result = dragonCurveString(result).let { it.substring(0..<desiredLength.coerceAtMost(it.length)) }
        }

        // Creating a checksum
        while (result.length % 2 == 0) {
            result = checksumIteration(result)
        }

        return@solver result
    }

    part2Solver = part1Solver
}
