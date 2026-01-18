package isdemidoff.year2015.day15

import isdemidoff.utility.discretemath.combinations
import isdemidoff.utility.solution.solution
import isdemidoff.year2015.day15.entity.calculateCalories
import isdemidoff.year2015.day15.entity.calculateScore
import isdemidoff.year2015.day15.entity.parseIngredient

/**
 * [Day 15: Science for Hungry People](https://adventofcode.com/2015/day/15).
 *
 * Helpful note: you must ignore calories when calculating score, as it is unclear in the description, they really kicks into part two.
 */
val day15 = solution(15) {
    inputParser = uniformLinesParser { parseIngredient(it) }

    val totalSpoons = 100

    part1Solver = solver({ "Best score available is $it." }) {
        combinations(it, totalSpoons).maxOf { it.calculateScore() }
    }

    val calories = 500L

    part2Solver = solver({ "Best score available keeping calories at $calories is $it." }) {
        combinations(it, totalSpoons).filter { it.calculateCalories() == calories }.maxOf { it.calculateScore() }
    }
}