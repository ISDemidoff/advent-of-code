@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.graphs

interface Node<T : Node<T>> {
    fun getConnectedNodes() : List<T>
    fun getIdentity(): Any = this
}