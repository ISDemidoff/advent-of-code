import isdemidoff.utility.input.readLines
import isdemidoff.utility.isInvalidPosition

fun solveForFileName(fileName: String) = readLines(fileName).let { lines ->
    var splitsCount = 0
    val beams = lines.first().map { it == 'S' }.toMutableList()
    lines.drop(1).forEach { line ->
        line.forEachIndexed { index, ch ->
            if (ch == '^' && beams[index]) {
                ++splitsCount
                beams[index] = false
                // Assume there are no two splitters next to each other
                (index + 1).takeUnless { it.isInvalidPosition(line.length) }?.let { beams[it] = true }
                (index - 1).takeUnless { it.isInvalidPosition(line.length) }?.let { beams[it] = true }
            }
        }
    }
    splitsCount
}