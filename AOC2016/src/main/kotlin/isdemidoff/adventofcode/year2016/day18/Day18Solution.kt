package isdemidoff.adventofcode.year2016.day18

import isdemidoff.utility.other.zipTriple
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution
import isdemidoff.utility.solution.solver.solver

fun calculateTile(str: String): Char {
    require(str.length == 3 ) { "Expecting 3 characters, got $str" }
    return if (str == "^^." || str == ".^^" || str == "^.." || str == "..^") '^' else '.'
}

fun calculateNextRow(str: String): String {
    require(str.length > 1) { "Expecting at least two characters, got $str" }
    val result = StringBuilder()
    result.append(calculateTile("." + str.take(2)))
    result.append(str.zipTriple { a1, a2, a3 -> calculateTile("$a1$a2$a3") }.joinToString(separator = ""))
    result.append(calculateTile(str.takeLast(2) + "."))
    return result.toString()
}

/**
 * [Day 18: Like a Rogue](https://adventofcode.com/2016/day/18).
 */
val day18 = solution(18) {
    inputParser = StringsInputParsers.singleLine

    part1Solver = solver<String, Int, Int>({ res, rows ->
        "There are $res total safe tiles in $rows rows."
    }) { firstRow, rows ->
        var currentRow = firstRow
        var totalCount = 0
        repeat(rows) {
            totalCount += currentRow.count { it == '.' }
            currentRow = calculateNextRow(currentRow)
        }
        return@solver totalCount
    }

    part2Solver = part1Solver
}