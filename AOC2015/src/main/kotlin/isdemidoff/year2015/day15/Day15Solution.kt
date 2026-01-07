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
 * Helpful note: you must **always** ignore calories.
 */
class Day15SolutionBuilder(
    day15Path: String,
    private val totalSpoons: Int = 100,
) : SimpleSolutionBuilder<Long, List<Ingredient>>(
    inputsDir = day15Path,
    inputParser = { readLines(it).map { it.parseIngredient() }.also { println(it) } },
    solver = { it.getCombinations(totalSpoons).maxOf { it.calculateScore() } },
)