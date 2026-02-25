@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.graphs

data class NodeWithPathCount<T : Node<T>>(
    val node: T,
    var pathCount: Int = 0,
)

fun <T : Node<T>> findNodeFromCollection(allNodes: Iterable<NodeWithPathCount<T>>, nodeToSearch: T) =
    allNodes.first { it.node.getIdentity() == nodeToSearch.getIdentity() }

fun <T : Node<T>> findCountOfPaths(
    allNodes: Iterable<T>,
    fromNode: T,
    toNode: T,
): Int {
    val allNodesWithPathCounts = allNodes.map { NodeWithPathCount(it) }

    val fromNodeWithPathCount = findNodeFromCollection(allNodesWithPathCounts, fromNode)

    fromNodeWithPathCount.pathCount++
    performBfs(allNodes, fromNode) { currentNode, visitedFrom, _ ->
        if (visitedFrom != null) {
            findNodeFromCollection(allNodesWithPathCounts, currentNode).pathCount += findNodeFromCollection(allNodesWithPathCounts, visitedFrom).pathCount
        }
    }
    return findNodeFromCollection(allNodesWithPathCounts, toNode).pathCount
}