package isdemidoff.year2015.day19

import isdemidoff.utility.keyValue
import isdemidoff.utility.solution.solution
import kotlin.sequences.forEach

/**
 * [Day 19: Medicine for Rudolph](https://adventofcode.com/2015/day/19).
 */
val day19 = solution<Pair<List<Pair<String, String>>, String>, Set<String>>(19) {
    inputParser = twoBlocksParser { (firstBlock, secondBlock) ->
        firstBlock.map { it.keyValue(" => ") } to secondBlock.single()
    }

    part1Solver = solver({
        "There are ${it.size} total unique molecules."
    }) { (possibleReplacements, inputString) ->
        val possibleOutcomes = mutableSetOf<String>()

        possibleReplacements.forEach { (str, replacement) ->
            str.toRegex().findAll(inputString).forEach { match ->
                val (from, to) = match.range.first to match.range.last // Assume it inclusive
                possibleOutcomes.add(inputString.substring(0, from) + replacement + inputString.substring(to + 1))
            }
        }

        return@solver possibleOutcomes
    }
}