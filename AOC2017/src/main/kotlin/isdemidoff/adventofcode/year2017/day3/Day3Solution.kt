package isdemidoff.adventofcode.year2017.day3

import isdemidoff.solution.inputparser.scope.singleInt
import isdemidoff.solution.solution
import isdemidoff.utility.cartesianProduct
import java.lang.Integer.min
import kotlin.math.abs

/**
 * [Day 3: Spiral Memory](https://adventofcode.com/2017/day/3).
 */
val day3 = solution(3) {
    inputParser = singleInt

    part1Solver = solver({
        "$it steps needed to carry the data."
    }) { targetNum ->
        var (x, y) = 0 to 0
        var (currNum, step) = 1 to 0

        generateSequence(1) { it + 1 }
            .first { it % 2 == 1 && it * it >= targetNum }
            .let { sqr ->
                if (sqr * sqr == targetNum) return@solver sqr - 1
                x = sqr / 2
                y = - x
                currNum = (sqr - 2) * (sqr - 2)
                step = sqr - 1
            }
        for (dir in (0..3)) {
            val append = min(step, targetNum - currNum)
            when (dir) {
                0 -> y += append
                1 -> x -= append
                2 -> y -= append
                3 -> x += append
            }
            currNum += append
        }

        return@solver abs(x) + abs(y)
    }

    part2Solver = solver({
        "First number greater than input is $it."
    }) { targetNum ->
        fun Int.nextDirection() = (this + 1) % 4

        data class Position(val x: Int, val y: Int) {
            infix fun nextTowards(dir: Int): Position = when (dir) {
                0 -> copy(x = x + 1)
                1 -> copy(y = y + 1)
                2 -> copy(x = x - 1)
                3 -> copy(y = y - 1)
                else -> error("Invalid direction $dir")
            }

            infix fun leftOf(dir: Int): Position = nextTowards(dir.nextDirection())
        }

        val dataGrid = mutableMapOf<Position, Int>()

        fun getSumAroundPosition(pos: Position): Int =
            cartesianProduct(-1..1, -1..1) {
                dataGrid[pos.copy(x = pos.x + it.first, y = pos.y + it.second)]
            }.filterNotNull().sum()

        var currentPosition = Position(0, 0)
        dataGrid[currentPosition] = 1
        generateSequence(0) { (it + 1) % 4 }.forEach { dir ->
            do {
                currentPosition = currentPosition nextTowards dir
                // Fill position
                dataGrid[currentPosition] = getSumAroundPosition(currentPosition).also {
                    if (it > targetNum) return@solver it
                }
            } while (dataGrid.containsKey(currentPosition leftOf dir))
        }

    }
}
