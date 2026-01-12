package isdemidoff.year2015.day7

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day7.entity.LogicalCircuit
import isdemidoff.year2015.day7.entity.LogicalWire
import isdemidoff.year2015.day7.entity.createLogicalWire

/**
 * [Day 7: Some Assembly Required](https://adventofcode.com/2015/day/7).
 *
 * Part 2 without changes to code.
 */
class Day7SolutionBuilder(day7Path: String) : SimpleSolutionBuilder<LogicalCircuit, List<LogicalWire>>(
    inputsDir = day7Path,
    inputParser = { readLines(it).map { it.createLogicalWire() } },
    solver = { wires -> LogicalCircuit().apply { importWires(wires) } },
) {
    override fun formatResult(result: LogicalCircuit): String = "Value at wire 'a' is ${result.getValue("a")}"
}