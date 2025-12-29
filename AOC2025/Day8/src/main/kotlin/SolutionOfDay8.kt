import entities.JunctionBox.Companion.toJunctionBox
import isdemidoff.utility.graphs.extractConnectedComponents
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseUnescapedCsvInputLines

fun solveForFileName(fileName: String, numConnections: Int) = readLines(fileName)
    .parseUnescapedCsvInputLines { it.toString().toLong() }
    .map { it.toJunctionBox() }
    .let { it to makeConnectionsGrid(it) }
    .also { (_, connections) -> connectClosest(connections, numConnections) }
    .let { (boxes, _) -> extractConnectedComponents(boxes) }
    .map { it.size }
    .sortedDescending()
    .take(3)
    .reduce(Int::times)

