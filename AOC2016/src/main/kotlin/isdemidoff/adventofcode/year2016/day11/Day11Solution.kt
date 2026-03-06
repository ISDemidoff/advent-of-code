package isdemidoff.adventofcode.year2016.day11

import isdemidoff.adventofcode.year2016.day11.entity.Chip
import isdemidoff.adventofcode.year2016.day11.entity.Component
import isdemidoff.adventofcode.year2016.day11.entity.Generator
import isdemidoff.adventofcode.year2016.day11.entity.LabState
import isdemidoff.adventofcode.year2016.day11.entity.componentsParser
import isdemidoff.solution.inputparser.scope.uniformLinesParser
import isdemidoff.solution.solution
import java.util.*
import kotlin.math.min

/**
 * [Day 11: Radioisotope Thermoelectric Generators](https://adventofcode.com/2016/day/11).
 */
val day11 = solution(11) {
    inputParser = uniformLinesParser(componentsParser)

    fun findMinimalMoves(componentsOnFloors: List<Set<Component>>): Int {
        val startingPosition = LabState(floors = componentsOnFloors)
        var minimalCount = Int.MAX_VALUE
        val recentPositions = mutableSetOf<LabState>()

        val queue = LinkedList<Pair<LabState, Int>>().apply { offer(startingPosition to 0) }
        while (queue.isNotEmpty()) {
            val (state, depth) = queue.poll()
            if (state.isWinningPosition()) {
                minimalCount = min(minimalCount, depth)
                continue
            }

            if (depth > minimalCount) {
                // No reason to search further
                continue
            }

            if (!recentPositions.add(state)) {
                // Skip because we've seen this position
                continue
            }

            state.generateAllNextMoves().forEach { queue.offer(it to depth + 1) }
        }

        return minimalCount
    }

    part1Solver = solver({
        "Minimal count of elevator moves is $it."
    }) { inputData -> findMinimalMoves(inputData) }

    val extraComponents = setOf(
        Generator("elerium"),
        Chip("elerium"),
        Generator("dilithium"),
        Chip("dilithium"),
    )

    part2Solver = solver({
        "Minimal count of elevator moves with extra components is $it."
    }) { inputData ->
        findMinimalMoves(inputData.mapIndexed { index, components -> if (index == 0) components + extraComponents else components })
    }
}
