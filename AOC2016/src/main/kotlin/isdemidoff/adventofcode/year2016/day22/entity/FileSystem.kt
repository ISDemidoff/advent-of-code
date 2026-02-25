package isdemidoff.adventofcode.year2016.day22.entity

import isdemidoff.adventofcode.year2016.day22.entity.FileSystem.NodeInfo
import isdemidoff.utility.other.joinToImage
import isdemidoff.utility.other.mapIndexedMatrix
import isdemidoff.utility.other.transpose

data class FileSystem(
    val nodes: Array<Array<NodeInfo>>,
    val xSize: Int,
    val ySize: Int,
) {
    data class NodeInfo(
        val used: Int,
        val total: Int,
        val specialData: Boolean = false,
    ) {
        val available = total - used
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileSystem

        if (xSize != other.xSize) return false
        if (ySize != other.ySize) return false
        if (!nodes.contentDeepEquals(other.nodes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = xSize
        result = 31 * result + ySize
        result = 31 * result + nodes.contentDeepHashCode()
        return result
    }

    /**
     * Debug method.
     */
    @Suppress("unused")
    fun asText(): String =
        nodes.mapIndexedMatrix { x, y, it ->
            when {
                it.used > 100 -> '#'
                it.specialData -> 'G'
                it.used == 0 -> '_'
                x == 0 && y == 0 -> '!'
                else -> '.'
            }
        }
            .transpose()
            .joinToImage()
}

fun createFileSystem(nodesList: List<FileSystemNode>): FileSystem {
    val xSize = nodesList.maxOf { it.xCoordinate } + 1
    val ySize = nodesList.maxOf { it.yCoordinate } + 1
    val nodes = Array(xSize) { Array(ySize) { NodeInfo(0, 0) } }
    nodesList.forEach { nodes[it.xCoordinate][it.yCoordinate] = NodeInfo(used = it.used, total = it.available + it.used) }
    nodes[xSize - 1][0] = nodes[xSize - 1][0].copy(specialData = true)
    return FileSystem(nodes = nodes, xSize = xSize, ySize = ySize)
}