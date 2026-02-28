package isdemidoff.adventofcode.year2015.day19

import isdemidoff.solution.complexSolution
import isdemidoff.solution.inputparser.functions.bidirectional.CollectionsBiDirectionalFunctions.single
import isdemidoff.solution.inputparser.functions.mapWith
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.parsing.keyValue
import java.util.*

private val replacementsParser: (List<String>) -> List<Pair<String, String>> = { it.map { it.keyValue(" => ") } }

/**
 * [Day 19: Medicine for Rudolph](https://adventofcode.com/2015/day/19).
 */
val day19 = complexSolution<Pair<List<Pair<String, String>>, String>, Set<String>, Int>(19) {
    inputParser = StringsInputParsers.twoBlocks mapWith (replacementsParser to single())

    part1Solver = solver({
        "There are ${it.size} total unique molecules."
    }) { (possibleReplacements, inputString) ->
        val possibleOutcomes = mutableSetOf<String>()

        possibleReplacements.forEach { (str, replacement) ->
            str.toRegex().findAll(inputString).forEach { match ->
                val (from, to) = match.range.first to match.range.last
                possibleOutcomes.add(inputString.substring(0, from) + replacement + inputString.substring(to + 1))
            }
        }

        return@solver possibleOutcomes
    }

    part2Solver = solver({
        "Fewest number of steps to achieve that molecule is $it."
    }) {(possibleReplacements, targetString) ->
        val sortedReplacements = possibleReplacements.sortedBy { it.second.length - it.first.length }

        val queue = LinkedList<Pair<String, Int>>()
        queue.addLast(targetString to 0)

        while (queue.isNotEmpty()) {
            val (str, depth) = queue.removeLast()

            if (str == "e") {
                return@solver depth
            }

            sortedReplacements.forEach { (replacement, replaced) ->
                replaced.toRegex().findAll(str).forEach { match ->
                    val (from, to) = match.range.first to match.range.last

                    queue.offer(str.substring(0, from) + replacement + str.substring(to + 1) to depth + 1)
                }
            }
        }

        return@solver -1
    }
}