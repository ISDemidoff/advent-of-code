package isdemidoff.adventofcode.year2015.day7

import isdemidoff.adventofcode.year2015.day7.entity.LogicalCircuit
import isdemidoff.adventofcode.year2015.day7.entity.createLogicalWire
import isdemidoff.solution.inputparser.scope.singleBlock
import isdemidoff.solution.solution

/**
 * [Day 7: Some Assembly Required](https://adventofcode.com/2015/day/7).
 */
val day7 = solution<List<String>, LogicalCircuit>(7) {
    inputParser = singleBlock

    part1Solver = solver({
        "Value at wire 'a' is ${it.getValue("a")}"
    }) { wires -> LogicalCircuit().apply { importWires(wires.map { it.createLogicalWire() }) } }

    part2Solver = solver({
        "Value at wire 'a' is now ${it.getValue("a")}"
    }) { wires ->
        val newValue = part1Solver.validateAndSolve(wires).get().getValue("a")
        LogicalCircuit().apply {
            importWires(
                (wires.filterNot { it.endsWith(" -> b") } + "$newValue -> b").map { it.createLogicalWire() }
            )
        }
    }
}
