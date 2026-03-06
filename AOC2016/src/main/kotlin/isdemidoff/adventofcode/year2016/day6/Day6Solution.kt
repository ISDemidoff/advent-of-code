package isdemidoff.adventofcode.year2016.day6

import isdemidoff.solution.inputparser.functions.bidirectional.StringsBiDirectionalFunctions.toCharList
import isdemidoff.solution.inputparser.scope.uniformLinesParser
import isdemidoff.solution.solution
import isdemidoff.utility.other.transpose
import isdemidoff.utility.strings.countCharacterStatistics

/**
 * [Day 6: Signals and Noise](https://adventofcode.com/2016/day/6).
 */
val day6 = solution(6) {
    inputParser = uniformLinesParser(toCharList)

    part1Solver = solver({
        "Error-corrected message by repetition code algo is $it."
    }) { input ->
        input.transpose()
            .map { line -> countCharacterStatistics(line.joinToString(separator = "")).maxBy { it.value }.key }
            .joinToString(separator = "")
    }

    part2Solver = solver({
        "Error-corrected message by modified repetition code algo is $it."
    }) { input ->
        input.transpose()
            .map { line -> countCharacterStatistics(line.joinToString(separator = "")).minBy { it.value }.key }
            .joinToString(separator = "")
    }
}
