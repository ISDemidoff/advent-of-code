import isdemidoff.utility.input.readLines
import kotlin.math.max

fun solveForFileName(fileName: String) = readLines(fileName)
    .sumOf { findMaxOutputJoltage(it) }

private fun findMaxOutputJoltage(battery: String): Int {
    var max = 0
    battery.forEachIndexed { leftIndex, leftChar ->
        battery.drop(leftIndex + 1).forEach { rightChar ->
            "$leftChar$rightChar".toInt().also { max = max(max, it) }
        }
    }
    return max
}
