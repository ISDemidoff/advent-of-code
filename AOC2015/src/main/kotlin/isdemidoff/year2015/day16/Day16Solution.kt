package isdemidoff.year2015.day16

import isdemidoff.utility.solution.solution
import isdemidoff.year2015.day16.entity.AuntSue
import isdemidoff.year2015.day16.entity.analyseAuntSue
import isdemidoff.year2015.day16.entity.parseAuntSueFromMemory

/**
 * [Day 16: Aunt Sue](https://adventofcode.com/2015/day/16).
 */
val day16 = solution<Pair<AuntSue, List<AuntSue>>, Int>(16) {
    inputParser = twoBlocksParser { (firstBlock, secondBlock) ->
        analyseAuntSue(firstBlock) to secondBlock.map { parseAuntSueFromMemory(it) }
    }

    part1Solver = solver({ "Using default comparison rules, seems like that was Aunt Sue #$it" }) { (analysed, all) ->
        all.find { analysed.seemsLike(it) }
            .let { requireNotNull(it) { "We must find anybody!" } }
            .id
    }

    part2Solver = solver({ "Using another set of comparison rules, seems like that was Aunt Sue #$it" }) { (analysed, all) ->
        all.find {
            analysed.seemsLike(
                it,
                "cats" to { analysis, actual -> actual > analysis },
                "trees" to { analysis, actual -> actual > analysis },
                "pomeranians" to { analysis, actual -> actual < analysis },
                "goldfish" to { analysis, actual -> actual < analysis },
            )
        }
            .let { requireNotNull(it) { "We must find anybody!" } }
            .id
    }
}