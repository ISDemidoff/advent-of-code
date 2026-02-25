@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.graphs

import isdemidoff.utility.graphs.VisitedPoint.Companion.toVisitedNode
import java.util.*

data class VisitedPoint<T : Node<T>>(
    val node: T,
    var visited: Boolean = false,
) {
    fun markVisited() {
        this.visited = true
    }

    companion object {
        fun <T : Node<T>> T.toVisitedNode() = VisitedPoint(this)
    }
}

fun <T : Node<T>> findVisitedNodeFromCollection(visitedNodes: Iterable<VisitedPoint<T>>, nodeToSearch: T) =
    visitedNodes.first { it.node.getIdentity() == nodeToSearch.getIdentity() }

fun <T : Node<T>> performBfs(
    allNodes: Iterable<T>,
    fromNode: T,
    onVisited: (currentNode: T, visitedFrom: T?, wasVisitedBefore: Boolean) -> Unit
) {
    val visitedNodes = allNodes.map { it.toVisitedNode() }
    // Visited point with source of visit
    val queue = LinkedList<Pair<VisitedPoint<T>, T?>>()

    queue.addFirst(findVisitedNodeFromCollection(visitedNodes, fromNode) to null)

    while (queue.isNotEmpty()) {
        val (current, visitedFrom) = queue.pollLast()
        val (currentNode, visitedBefore) = current

        onVisited(currentNode, visitedFrom, visitedBefore)

        if (visitedBefore) {
            continue
        }

        current.markVisited()

        currentNode.getConnectedNodes().forEach { connection ->
            findVisitedNodeFromCollection(visitedNodes, connection)
                .let { queue.push(it to currentNode) }
        }
    }
}


fun <T : Node<T>> extractConnectedComponents(allNodes: Iterable<T>): List<Set<T>> {
    val connectedComponents = mutableListOf<MutableSet<T>>()
    val visitedNodes = allNodes.map { it.toVisitedNode() }

    visitedNodes.forEach { visitedNode ->
        if (visitedNode.visited) {
            return@forEach
        }

        val connectedComponent = mutableSetOf<T>()

        performBfs(allNodes, visitedNode.node) { currentNode, _, _ ->
            connectedComponent.add(currentNode)
            findVisitedNodeFromCollection(visitedNodes, currentNode).markVisited()
        }

        connectedComponents.add(connectedComponent)
    }

    return connectedComponents
}