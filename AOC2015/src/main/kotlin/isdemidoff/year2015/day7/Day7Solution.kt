package isdemidoff.year2015.day7

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day7.entity.LogicalCircuit
import isdemidoff.year2015.day7.entity.LogicalWire
import isdemidoff.year2015.day7.entity.createLogicalWire

class Day7SolutionBuilder(day7Path: String) : SimpleSolutionBuilder<LogicalCircuit, List<LogicalWire>>(
    day7Path,
    { readLines(it).map { it.createLogicalWire() } },
    { wires -> LogicalCircuit().apply { importWires(wires) } },
)