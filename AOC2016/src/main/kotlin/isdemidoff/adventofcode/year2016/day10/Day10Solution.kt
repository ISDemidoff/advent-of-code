package isdemidoff.adventofcode.year2016.day10

import isdemidoff.adventofcode.year2016.day10.entity.CompareChipsOperation
import isdemidoff.adventofcode.year2016.day10.entity.Instruction
import isdemidoff.adventofcode.year2016.day10.entity.ProcessingField
import isdemidoff.adventofcode.year2016.day10.entity.ProcessingUnitType
import isdemidoff.adventofcode.year2016.day10.entity.ReceiveChipOperation
import isdemidoff.adventofcode.year2016.day10.entity.parseInstruction
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.solution.solver.solver

/**
 * [Day 10: Balance Bots](https://adventofcode.com/2016/day/10).
 */
val day10 = solution(10) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine ::parseInstruction

    part1Solver = solver<List<Instruction>, Int, Int, Int>({ result, val1, val2 ->
        "Bot which compared $val1 and $val2 has number $result."
    }) { input, val1, val2 ->
        ProcessingField().apply { processInstructions(input) }.getAllLogs()
            .find { it.operation is CompareChipsOperation && it.operation.chipValues == setOf(val1, val2) }
            .let { requireNotNull(it) { "Required comparing operation is not found!" } }
            .id
    }

    part2Solver = solver<List<Instruction>, Int, Int, Int, Int>({ result, val1, val2, val3 ->
        "Multiplying results in outputs $val1, $val2 and $val3, we get $result."
    }) { input, val1, val2, val3 ->
        ProcessingField().apply { processInstructions(input) }.getAllLogs()
            .filter { it.type == ProcessingUnitType.OUTPUT && it.id in setOf(val1, val2, val3) }
            .mapNotNull { it.operation as? ReceiveChipOperation }
            .map { it.chipValue }
            .reduce(Int::times)
    }
}