package isdemidoff.year2015.day19

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readTwoBlocks
import isdemidoff.utility.keyValue

class Day19Solution(
    private val inputString: String,
    private val possibleReplacements: List<Pair<String, String>>,
) : Solution<Set<String>> {
    override fun solve(): Set<String> {
        val possibleOutcomes = mutableSetOf<String>()

        possibleReplacements.forEach { (str, replacement) ->
            str.toRegex().findAll(inputString).forEach { match ->
                val (from, to) = match.range.first to match.range.last // Assume it inclusive
                possibleOutcomes.add(inputString.substring(0, from) + replacement + inputString.substring(to + 1))
            }
        }

        return possibleOutcomes
    }
}

/**
 * [Day 19: Medicine for Rudolph](https://adventofcode.com/2015/day/19).
 */
class Day19SolutionBuilder(
    day19Path: String,
) : SimpleSolutionBuilder<Set<String>, Pair<List<Pair<String, String>>, String>>(
    inputsDir = day19Path,
    inputParser = {
        readTwoBlocks(it)
            .let { (firstBlock, secondBlock) ->
                firstBlock.map { it.keyValue(" => ") } to secondBlock.single()
            }
    },
    solutionSupplier = { Day19Solution(it.second, it.first) },
) {
    override fun formatResult(result: Set<String>) = "Total of ${result.size} unique molecules."
}