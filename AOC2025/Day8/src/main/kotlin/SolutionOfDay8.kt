import entities.JunctionBox.Companion.toJunctionBox
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseUnescapedCsvInputLines

fun solveForFileName(fileName: String, numConnections: Int) = readLines(fileName)
    .parseUnescapedCsvInputLines { it.toLong() }
    .map { it.toJunctionBox() }
    .let { it to makeConnectionsGrid(it) }
    .also { (_, connections) -> connectClosest(connections, numConnections) }
    .let { (boxes, _) -> calculateConnectedComponentsSizes(boxes) }
    .sortedDescending()
    .take(3)
    .reduce(Int::times)

