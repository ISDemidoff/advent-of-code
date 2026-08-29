package isdemidoff.adventofcode.year2017.day11

import isdemidoff.solution.inputparser.scope.splitLineBy
import isdemidoff.solution.solution
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private data class Position(val x: Int, val y: Int) {
    fun applyMove(str: String): Position = when (str) {
        "s" -> copy(y = y - 2)
        "n" -> copy(y = y + 2)
        "nw" -> copy(x = x - 1, y = y + 1)
        "sw" -> copy(x = x - 1, y = y - 1)
        "ne" -> copy(x = x + 1, y = y + 1)
        "se" -> copy(x = x + 1, y = y - 1)
        else -> throw IllegalArgumentException("Unknown direction: $str")
    }

    fun stepsFromStart(): Int = (abs(x) to abs(y)).let { (xDist, yDist) ->
        min(xDist, yDist).let { diag ->
            if (xDist > diag) xDist else (yDist - diag) / 2 + diag
        }
    }
}

/**
 * [https://adventofcode.com/2017/day/11](Day 11: Hex Ed).
 */
val day11 = solution(11) {
    inputParser = splitLineBy(",")

    part1Solver = solver({
        "Fewest number of steps is $it."
    }) {
        var pos = Position(0, 0)
        it.forEach { pos = pos.applyMove(it) }
        pos.stepsFromStart()
    }

    part2Solver = solver({
        "Max distance were $it."
    }) {
        var (pos, maxDist) = Position(0, 0) to 0
        it.forEach {
            pos = pos.applyMove(it)
            maxDist = max(maxDist, pos.stepsFromStart())
        }
        maxDist
    }
}
