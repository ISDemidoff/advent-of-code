package isdemidoff.adventofcode.year2015.day15

import isdemidoff.adventofcode.year2015.day15.entity.calculateCalories
import isdemidoff.adventofcode.year2015.day15.entity.calculateScore
import isdemidoff.adventofcode.year2015.day15.entity.parseIngredient
import isdemidoff.utility.discretemath.combinationsWithRepetitions
import isdemidoff.utility.solution.solution

/**
 * [Day 15: Science for Hungry People](https://adventofcode.com/2015/day/15).
 *
 * Helpful note: you must ignore calories when calculating score, as it is unclear in the description, they really kicks into part two.
 */
val day15 = solution(15) {
    inputParser = uniformLinesParser(::parseIngredient)

    val totalSpoons = 100

    part1Solver = solver({ "Best score available is $it." }) {
        combinationsWithRepetitions(it, totalSpoons).maxOf { it.calculateScore() }
    }

    val calories = 500L

    part2Solver = solver({ "Best score available keeping calories at $calories is $it." }) {
        combinationsWithRepetitions(it, totalSpoons).filter { it.calculateCalories() == calories }.maxOf { it.calculateScore() }
    }
}