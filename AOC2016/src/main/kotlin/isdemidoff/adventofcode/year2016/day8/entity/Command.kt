package isdemidoff.adventofcode.year2016.day8.entity

import isdemidoff.utility.parsing.keyValueBy

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

fun parseCommand(command: String): Command = when {
    command.startsWith("rect") -> command.substringAfterLast(" ")
        .keyValueBy("x", String::toInt, String::toInt)
        .let { RectCommand(it.first, it.second) }
    command.startsWith("rotate row y=") -> command.substringAfterLast("=")
        .keyValueBy(" by ", String::toInt, String::toInt)
        .let { RowShiftCommand(it.first, it.second) }
    command.startsWith("rotate column x=") -> command.substringAfterLast("=")
        .keyValueBy(" by ", String::toInt, String::toInt)
        .let { ColumnShiftCommand(it.first, it.second) }
    else -> throw IllegalArgumentException("Unknown command: $command")
}