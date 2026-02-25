package isdemidoff.adventofcode.year2016.day2

import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

private fun moveToNextSmallKeypad(current: Int, direction: Char) = when (current) {
    1 -> when (direction) {
        'U', 'L' -> 1
        'D' -> 4
        'R' -> 2
        else -> 0
    }
    2 -> when (direction) {
        'U' -> 2
        'L' -> 1
        'D' -> 5
        'R' -> 3
        else -> 0
    }
    3 -> when (direction) {
        'U', 'R' -> 3
        'L' -> 2
        'D' -> 6
        else -> 0
    }
    4 -> when (direction) {
        'U' -> 1
        'L' -> 4
        'D' -> 7
        'R' -> 5
        else -> 0
    }
    5 -> when (direction) {
        'U' -> 2
        'L' -> 4
        'D' -> 8
        'R' -> 6
        else -> 0
    }
    6 -> when (direction) {
        'U' -> 3
        'L' -> 5
        'D' -> 9
        'R' -> 6
        else -> 0
    }
    7 -> when (direction) {
        'U' -> 4
        'L', 'D' -> 7
        'R' -> 8
        else -> 0
    }
    8 -> when (direction) {
        'U' -> 5
        'L' -> 7
        'D' -> 8
        'R' -> 9
        else -> 0
    }
    9 -> when (direction) {
        'U' -> 6
        'L' -> 8
        'D', 'R' -> 9
        else -> 0
    }
    else -> 0
}

private fun moveToNextBigKeypad(current: Char, direction: Char) = when (current) {
    '1' -> when (direction) {
        'U', 'L', 'R' -> current
        'D' -> '3'
        else -> '0'
    }
    '2' -> when (direction) {
        'U', 'L' -> current
        'D' -> '6'
        'R' -> '3'
        else -> '0'
    }
    '3' -> when (direction) {
        'U' -> '1'
        'L' -> '2'
        'D' -> '7'
        'R' -> '4'
        else -> '0'
    }
    '4' -> when (direction) {
        'U', 'R' -> current
        'L' -> '3'
        'D' -> '8'
        else -> '0'
    }
    '5' -> when (direction) {
        'U', 'L', 'D' -> current
        'R' -> '6'
        else -> '0'
    }
    '6' -> when (direction) {
        'U' -> '2'
        'L' -> '5'
        'D' -> 'A'
        'R' -> '7'
        else -> '0'
    }
    '7' -> when (direction) {
        'U' -> '3'
        'L' -> '6'
        'D' -> 'B'
        'R' -> '8'
        else -> '0'
    }
    '8' -> when (direction) {
        'U' -> '4'
        'L' -> '7'
        'D' -> 'C'
        'R' -> '9'
        else -> '0'
    }
    '9' -> when (direction) {
        'U', 'D', 'R' -> current
        'L' -> '8'
        else -> '0'
    }
    'A' -> when (direction) {
        'U' -> '6'
        'L', 'D' -> current
        'R' -> 'B'
        else -> '0'
    }
    'B' -> when (direction) {
        'U' -> '7'
        'L' -> 'A'
        'D' -> 'D'
        'R' -> 'C'
        else -> '0'
    }
    'C' -> when (direction) {
        'U' -> '8'
        'L' -> 'B'
        'D', 'R' -> current
        else -> '0'
    }
    'D' -> when (direction) {
        'U' -> 'B'
        'L', 'D', 'R' -> current
        else -> '0'
    }
    else -> '0'
}

/**
 * [Day 2: Bathroom Security](https://adventofcode.com/2016/day/2).
 */
val day2 = solution(2) {
    inputParser = StringsInputParsers.singleBlock

    part1Solver = solver({
        "Code to the bathroom is $it."
    }) { instructions ->
        var current = 5
        val result = StringBuilder()
        instructions.forEach { instructionLine ->
            instructionLine.forEachIndexed { index, direction ->
                current = moveToNextSmallKeypad(current, direction)
                    .also { check(it != 0) { "Something went terribly wrong at instruction line $instructionLine at direction $direction (index $index)" } }
            }
            result.append(current)
        }
        result.toString()
    }

    part2Solver = solver({
        "Actual code to the bathroom is $it."
    }) { instructions ->
        var current = '5'
        val result = StringBuilder()
        instructions.forEach { instructionLine ->
            instructionLine.forEachIndexed { index, direction ->
                current = moveToNextBigKeypad(current, direction)
                    .also { check(it != '0') { "Something went terribly wrong at instruction line $instructionLine at direction $direction (index $index)" } }
            }
            result.append(current)
        }
        result.toString()
    }
}