package isdemidoff.adventofcode.year2016.day22.entity

import isdemidoff.utility.matching.matchAny
import isdemidoff.utility.matching.yields
import isdemidoff.utility.parsing.toIntOrError

data class FileSystemNode(
    val xCoordinate: Int,
    val yCoordinate: Int,
    val used: Int,
    val available: Int,
) {
    infix fun isViableWith(other: FileSystemNode): Boolean =
        this != other &&
                this.used > 0 &&
                this.used < other.available
}

val nodeParser = matchAny(
    """/dev/grid/node-x(\d+)-y(\d+)\s+\d+T\s+(\d+)T\s+(\d+)T\s+\d+%""".toRegex() yields {(x, y, used, avail) ->
        FileSystemNode(
            xCoordinate = x.toIntOrError(),
            yCoordinate = y.toIntOrError(),
            used = used.toIntOrError(),
            available = avail.toIntOrError(),
        )
    }
)