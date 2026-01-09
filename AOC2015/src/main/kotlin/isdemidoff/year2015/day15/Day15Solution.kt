package isdemidoff.year2015.day15

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.getCombinations
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day15.entity.Ingredient
import isdemidoff.year2015.day15.entity.calculateScore
import isdemidoff.year2015.day15.entity.parseIngredient

/**
 * [Day 15: Science for Hungry People](https://adventofcode.com/2015/day/15).
 *
 * Helpful note: you must ignore calories when calculating score, as it is unclear in the description, they really kicks into part two.
 */
class Day15SolutionBuilder(
    private val day15Path: String,
    private val totalSpoons: Int = 100,
    private val combinationsFilter: (Map<Ingredient, Int>) -> Boolean = { true },
) : SimpleSolutionBuilder<Long, List<Ingredient>>(
    inputsDir = day15Path,
    inputParser = { readLines(it).map { it.parseIngredient() } },
    solver = { it.getCombinations(totalSpoons).filter(combinationsFilter).maxOf { it.calculateScore() } },
) {
    fun withCombinationsFilter(filter: (Map<Ingredient, Int>) -> Boolean) = Day15SolutionBuilder(day15Path, totalSpoons, filter)
}