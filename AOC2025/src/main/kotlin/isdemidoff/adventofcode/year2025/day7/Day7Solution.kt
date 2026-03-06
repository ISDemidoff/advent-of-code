package isdemidoff.adventofcode.year2025.day7

import isdemidoff.solution.inputparser.scope.singleBlock
import isdemidoff.solution.solution
import isdemidoff.utility.isInvalidPosition

/**
 * [Day 7: Laboratories](https://adventofcode.com/2025/day/7).
 */
val day7 = solution(7) {
    inputParser = singleBlock

    part1Solver = solver({ "Beam was split $it times." }) { lines ->
        var splitsCount = 0
        val beams = lines.first().map { it == 'S' }.toMutableList()
        lines.drop(1).forEach { line ->
            line.forEachIndexed { index, ch ->
                if (ch == '^' && beams[index]) {
                    ++splitsCount
                    beams[index] = false
                    // Assume there are no two splitters next to each other
                    (index + 1).takeUnless { it.isInvalidPosition(line.length) }?.let { beams[it] = true }
                    (index - 1).takeUnless { it.isInvalidPosition(line.length) }?.let { beams[it] = true }
                }
            }
        }
        splitsCount
    }
}
