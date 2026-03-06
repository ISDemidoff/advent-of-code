package isdemidoff.adventofcode.year2017.day1

import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.functions.bidirectional.StringsBiDirectionalFunctions.digits
import isdemidoff.solution.inputparser.scope.singleLine
import isdemidoff.solution.solution

/**
 * [Day 1: Inverse Captcha](https://adventofcode.com/2017/day/1).
 */
val day1 = solution(1) {
    inputParser = singleLine andThen digits

    part1Solver = solver({
        "Answer for captcha is $it."
    }) { digits ->
        (digits + digits.first())
            .zipWithNext { a, b -> if (a == b) a else 0 }
            .sum()
    }

    part2Solver = solver({
        "Answer for captcha with modified instruction is $it."
    }) { digits ->
        require(digits.size % 2 == 0) { "Input string must have even length" }
        val halfLength = digits.size / 2
        (0..<halfLength)
            .map { if (digits[it] == digits[it + halfLength]) digits[it] else 0 }
            .sum() * 2
    }
}
