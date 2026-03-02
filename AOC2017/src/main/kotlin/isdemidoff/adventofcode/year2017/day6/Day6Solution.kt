package isdemidoff.adventofcode.year2017.day6

import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toInt
import isdemidoff.solution.inputparser.scope.StringsInputParsers.splitLineBy
import isdemidoff.solution.solution

/**
 * [Day 6: Memory Reallocation](https://adventofcode.com/2017/day/6).
 */
val day6 = solution(6) {
    inputParser = splitLineBy("""\s+""".toRegex()) andThenOnEveryLine toInt

    fun calculateNextDistribution(dataBank: List<Int>): List<Int> {
        // Not optimal, but ok here
        val indexToDistribute = dataBank.max().let { max -> dataBank.indexOfFirst { it == max } }
        val result = dataBank.mapIndexedTo(mutableListOf()) { index, value ->
            value.takeUnless { index == indexToDistribute } ?: 0
        }
        (1..dataBank[indexToDistribute]).forEach { i ->
            result[(indexToDistribute + i) % dataBank.size] += 1
        }
        return result.toList()
    }

    part1Solver = solver({
        "Debugger script detects infinite loop after $it distributions."
    }) { inputData ->
        mutableSetOf<List<Int>>().apply {
            generateSequence(inputData) { calculateNextDistribution(it) }
                .any { !this.add(it) }
        }.size
    }

    part2Solver = solver({
        "Infinite loop has length of $it."
    }) { input ->
        val seen = mutableMapOf<List<Int>, Int>()
        generateSequence(input) { calculateNextDistribution(it) }
            .forEachIndexed { index, it ->
                if (seen.containsKey(it)) {
                    return@solver index - seen[it]!!
                } else {
                    seen[it] = index
                }
            }
    }
}
