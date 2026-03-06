package isdemidoff.adventofcode.year2017.day9

import isdemidoff.solution.inputparser.scope.singleLine
import isdemidoff.solution.solution

/**
 * [Day 9: Stream Processing](https://adventofcode.com/2017/day/9).
 */
val day9 = solution(9) {
    inputParser = singleLine

    part1Solver = solver({
        "Total score of data flow is $it."
    }) { input ->
        var score = 0
        var insideGarbage = false
        var stack = 0

        val iter = input.iterator()
        while (iter.hasNext()) {
            val elem = iter.next()
            when (elem) {
                '!' -> iter.next()
                '<' -> insideGarbage = true
                '>' -> insideGarbage = false
                '{' -> if (!insideGarbage) stack++
                '}' -> if (!insideGarbage) {
                    score += stack
                    stack--
                }
            }
        }

        return@solver score
    }

    part2Solver = solver({
        "There are $it characters inside garbage."
    }) { input ->
        var count = 0
        var insideGarbage = false
        var stack = 0

        val iter = input.iterator()
        while (iter.hasNext()) {
            val elem = iter.next()
            when (elem) {
                '!' -> iter.next()
                '<' -> if (insideGarbage) {
                    count++
                } else {
                    insideGarbage = true
                }
                '>' -> insideGarbage = false
                '{' -> if (!insideGarbage) {
                    stack++
                } else {
                    count++
                }
                '}' -> if (!insideGarbage) {
                    stack--
                } else {
                    count++
                }
                else -> if (insideGarbage) count++
            }
        }

        return@solver count
    }
}
