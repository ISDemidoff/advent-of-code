package isdemidoff.adventofcode.year2015.day20

import isdemidoff.utility.discretemath.getDivisors
import isdemidoff.utility.discretemath.getSumOfDivisors
import isdemidoff.utility.solution.solution

/**
 * [Day 20: Infinite Elves and Infinite Houses](https://adventofcode.com/2015/day/20).
 */
val day20 = solution(20) {
    inputParser = singleLineParser { it.toInt() }

    part1Solver = solver({
        "First house to receive that much of presents is $it."
    }) { targetSum ->
        (1..targetSum).first { getSumOfDivisors(it) * 10 >= targetSum }
    }

    part2Solver = solver({
        "First house to receive that much of presents under new rules is $it."
    }) { targetSum ->
        (1..targetSum).first { candidate ->
            getDivisors(candidate).filterNot { candidate / it > 50 }.sum() * 11 >= targetSum
        }
    }
}