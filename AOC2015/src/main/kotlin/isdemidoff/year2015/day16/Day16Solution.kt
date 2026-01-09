package isdemidoff.year2015.day16

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readTwoBlocks
import isdemidoff.year2015.day16.entity.AuntSue
import isdemidoff.year2015.day16.entity.ComparingRules
import isdemidoff.year2015.day16.entity.analysis
import isdemidoff.year2015.day16.entity.rememberAuntSue

class Day16SolutionBuilder(
    private val day16Path: String,
    private val comparingRules: ComparingRules = ComparingRules.DEFAULT,
) : SimpleSolutionBuilder<Int, Pair<AuntSue, List<AuntSue>>>(
    inputsDir = day16Path,
    inputParser = {
        readTwoBlocks(it)
            .let { (firstBlock, secondBlock) ->
                firstBlock.analysis() to secondBlock.map { it.rememberAuntSue() }
            }
    },
    solver = { (analysed, all) ->
        all.find { analysed.seemsLike(it, comparingRules.overrides.toMap()) }
            .let { requireNotNull(it) { "We must find anybody!" } }
            .id
    },
) {
    fun withComparingRules(rules: ComparingRules) = Day16SolutionBuilder(day16Path, rules)
}