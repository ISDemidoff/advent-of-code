package isdemidoff.adventofcode.year2016.day15

import isdemidoff.utility.discretemath.lcm
import isdemidoff.utility.solution.solution

data class Disk(val index: Int, val totalPositions: Int, val startingPosition: Int) {
    fun positionForTimeStart(seconds: Int): Int = (startingPosition + seconds + index) % totalPositions
    fun isPassedForTimeStart(seconds: Int): Boolean = positionForTimeStart(seconds) == 0
    fun findMinStartTimeToPass(): Int =
        (2 * totalPositions - startingPosition - index % totalPositions) % totalPositions
}

private fun parseDisk(index: Int, str: String): Disk =
    """Disc #\d+ has (\d+) positions; at time=0, it is at position (\d+)\.""".toRegex()
        .matchEntire(str)
        .let { requireNotNull(it?.destructured) { "Must match given regex" } }
        .let { (positions, startingPositions) -> Disk(index + 1, positions.toInt(), startingPositions.toInt()) }

/**
 * [Day 15: Timing is Everything](https://adventofcode.com/2016/day/15).
 */
val day15 = solution(15) {
    inputParser = singleBlockParser { it.mapIndexed(::parseDisk) }

    fun solveForDisks(disks: List<Disk>): Int {
        val lcm = lcm(disks.map { it.totalPositions })
        val (step, start) = disks.maxBy { it.totalPositions }.let { it.totalPositions to it.findMinStartTimeToPass() }

        (start..(start + lcm) step step).forEach { time ->
            if (disks.all { it.isPassedForTimeStart(time) }) return time
        }

        error("Solution not found")
    }

    part1Solver = solver({
        "First time to press the buton is $it."
    }) { solveForDisks(it) }

    part2Solver = solver({
        "First time to press the buton with new configuration is $it."
    }) { solveForDisks(it + Disk(it.size + 1, 11, 0)) }
}