package isdemidoff.adventofcode.year2016.day8.entity

import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields
import isdemidoff.utility.parsing.toIntOrError

sealed interface Command

data class RectCommand(
    val width: Int,
    val height: Int,
) : Command

data class RowShiftCommand(
    val y: Int,
    val shift: Int,
) : Command

data class ColumnShiftCommand(
    val x: Int,
    val shift: Int,
) : Command

val commandParser: (String) -> Command = regexMatch(
    """rect (\d+)x(\d+)""".toRegex() yields { (x, y) -> RectCommand(x.toIntOrError(), y.toIntOrError()) },
    """rotate row y=(\d+) by (\d+)""".toRegex() yields { (y, shift) -> RowShiftCommand(y.toIntOrError(), shift.toIntOrError()) },
    """rotate column x=(\d+) by (\d+)""".toRegex() yields { (x, shift) -> ColumnShiftCommand(x.toIntOrError(), shift.toIntOrError()) },
)
