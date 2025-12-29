import entities.JunctionBox
import entities.JunctionBoxConnection
import entities.JunctionBoxConnection.Companion.createConnection
import entities.VisitedJunctionBox
import isdemidoff.utility.cartesianProduct
import java.util.LinkedList

fun makeConnectionsGrid(boxes: List<JunctionBox>) =
    cartesianProduct(boxes, boxes) { it.createConnection() }
        .filter { !it.singular }
        .toSet()

fun connectClosest(connections: Set<JunctionBoxConnection>, numConnections: Int) =
    connections.sortedBy { it.length() }
        .take(numConnections)
        .forEach { it.enableConnection() }

fun calculateConnectedComponentsSizes(boxes: List<JunctionBox>): List<Int> {
    val connectedComponents = mutableListOf<MutableSet<JunctionBox>>()
    val visitedBoxes = boxes.map { VisitedJunctionBox(it) }

    visitedBoxes.forEach { visitedBox ->
        if (visitedBox.visited) {
            return@forEach
        }

        // This box is new connected component base
        val connectedComponent = mutableSetOf<JunctionBox>()

        val queue = LinkedList<VisitedJunctionBox>() // as queue
        queue.push(visitedBox)
        while (!queue.isEmpty()) {
            val current = queue.pop()

            if (current.visited) {
                continue
            }

            // Mark and add to component
            current.markVisited()
            connectedComponent.add(current.junctionBox)

            current.junctionBox.connections
                .forEach { connection ->
                    visitedBoxes.first { it.junctionBox == connection }
                        .takeUnless { it.visited }
                        ?.let { queue.push(it) }
                }
        }

        connectedComponents.add(connectedComponent)
    }

    return connectedComponents.map { it.size }
}