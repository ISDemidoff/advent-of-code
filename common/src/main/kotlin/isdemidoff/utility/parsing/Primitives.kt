@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.parsing

fun String.toIntOrError(): Int = requireNotNull(toIntOrNull()) { "Expected string '$this' to be valid integer value" }
fun String.toLongOrError(): Long = requireNotNull(toLongOrNull()) { "Expected string '$this' to be valid long value" }
fun String.toUIntOrError(): UInt = requireNotNull(toUIntOrNull()) { "Expected string '$this' to be valid unsigned integer value" }
fun String.toULongOrError(): ULong = requireNotNull(toULongOrNull()) { "Expected string '$this' to be valid unsigned long value" }

fun toIntsList(strings: List<String>): List<Int> = strings.map { it.toIntOrError() }
fun toLongsList(strings: List<String>): List<Long> = strings.map { it.toLongOrError() }
fun toUIntsList(strings: List<String>): List<UInt> = strings.map { it.toUIntOrError() }
fun toULongsList(strings: List<String>): List<ULong> = strings.map { it.toULongOrError() }
