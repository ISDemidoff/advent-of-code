@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.other

fun <E> List<List<E>>.transpose(): List<List<E>> {
    if (this.isEmpty()) return this
    require(all { it.size == this.first().size }) { "Expected all rows to be same size." }
    return (0..<this.first().size).map { index -> this.map { it[index] } }
}

fun List<List<Char>>.joinToImage(): String = this.joinToString(separator = "\n") { it.joinToString(separator = "") }

// ------------------------------
// Extensions for matrix: forEach
// ------------------------------

fun <E> List<List<E>>.forEachIndexedInMatrix(op: (index1: Int, index2: Int, elem: E) -> Unit): Unit {
    this.forEachIndexed { index1, elems ->
        elems.forEachIndexed { index2, elem ->
            op(index1, index2, elem)
        }
    }
}

fun <E> List<List<E>>.forEachInMatrix(op: (E) -> Unit): Unit {
    this.forEach { it.forEach { op(it) } }
}

fun <E> Array<Array<E>>.forEachIndexedInMatrix(op: (index1: Int, index2: Int, elem: E) -> Unit): Unit {
    this.forEachIndexed { index1, elems ->
        elems.forEachIndexed { index2, elem ->
            op(index1, index2, elem)
        }
    }
}

fun <E> Array<Array<E>>.forEachInMatrix(op: (E) -> Unit): Unit {
    this.forEach { it.forEach { op(it) } }
}

// ------------------------------
// Extensions for matrix: map
// ------------------------------

fun <E, R> List<List<E>>.mapIndexedMatrix(transform: (index1: Int, index2: Int, elem: E) -> R): List<List<R>> =
    this.mapIndexed { index1, elems ->
        elems.mapIndexed { index2, elem ->
            transform(index1, index2, elem)
        }
    }

fun <E, R> List<List<E>>.mapMatrix(transform: (E) -> R): List<List<R>> =
    this.map { it.map { transform(it) } }

fun <E, R> Array<Array<E>>.mapIndexedMatrix(transform: (index1: Int, index2: Int, elem: E) -> R): List<List<R>> =
    this.mapIndexed { index1, elems ->
        elems.mapIndexed { index2, elem ->
            transform(index1, index2, elem)
        }
    }

fun <E, R> Array<Array<E>>.mapMatrix(transform: (E) -> R): List<List<R>> =
    this.map { it.map { transform(it) } }

// ------------------------------
// Extensions for matrix: find
// ------------------------------

/**
 * Returns first coordinates that satisfies predicate.
 * Walks every inner list one by one.
 */
fun <E> List<List<E>>.indexOfInMatrix(predicate: (E) -> Boolean): Pair<Int, Int> =
    this.flatMapIndexed { index1, elements ->
        elements.mapIndexed { index2, elem ->
            if (predicate(elem)) index1 to index2 else null
        }.filterNotNull()
    }.firstOrNull() ?: (-1 to -1)

fun <E> List<List<E>>.firstOrNullInMatrix(predicate: (E) -> Boolean): E? =
    this.indexOfInMatrix(predicate).takeUnless { it == -1 to -1 }?.let { this[it.first][it.second] }

fun <E> List<List<E>>.firstInMatrix(predicate: (E) -> Boolean): E =
    firstOrNullInMatrix(predicate) ?: throw NoSuchElementException("Element not found")

fun <E> Array<Array<E>>.indexOfInMatrix(predicate: (E) -> Boolean): Pair<Int, Int> =
    this.flatMapIndexed { index1, elements ->
        elements.mapIndexed { index2, elem ->
            if (predicate(elem)) index1 to index2 else null
        }.filterNotNull()
    }.firstOrNull() ?: (-1 to -1)

fun <E> Array<Array<E>>.firstOrNullInMatrix(predicate: (E) -> Boolean): E? =
    this.indexOfInMatrix(predicate).takeUnless { it == -1 to -1 }?.let { this[it.first][it.second] }

fun <E> Array<Array<E>>.firstInMatrix(predicate: (E) -> Boolean): E =
    firstOrNullInMatrix(predicate) ?: throw NoSuchElementException("Element not found")