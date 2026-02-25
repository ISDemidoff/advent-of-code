@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.strings

fun countCharacterStatistics(seq: CharSequence): Map<Char, Int> =
    mutableMapOf<Char, Int>()
        .apply { seq.forEach { this.compute(it) { _, v -> (v ?: 0) + 1 } } }
        .toMap()