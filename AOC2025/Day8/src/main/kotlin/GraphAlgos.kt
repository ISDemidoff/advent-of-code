import entities.JunctionBox
import entities.JunctionBoxConnection
import entities.JunctionBoxConnection.Companion.createConnection
import isdemidoff.utility.cartesianProduct

fun makeConnectionsGrid(boxes: List<JunctionBox>) =
    cartesianProduct(boxes, boxes) { it.createConnection() }
        .filter { !it.singular }
        .toSet()

fun connectClosest(connections: Set<JunctionBoxConnection>, numConnections: Int) =
    connections.sortedBy { it.length() }
        .take(numConnections)
        .forEach { it.enableConnection() }
