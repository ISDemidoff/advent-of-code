package isdemidoff.year2015.day7

import isdemidoff.RealSimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day7.entity.LogicalCircuit
import isdemidoff.year2015.day7.entity.LogicalWire
import isdemidoff.year2015.day7.entity.createLogicalWire

class Day7SolutionBuilder(day7Path: String) : RealSimpleSolutionBuilder<LogicalCircuit, List<LogicalWire>>(
    inputsDir = day7Path,
    inputParser = { readLines(it).map { it.createLogicalWire() } },
    solver = { wires -> LogicalCircuit().apply { importWires(wires) } },
)