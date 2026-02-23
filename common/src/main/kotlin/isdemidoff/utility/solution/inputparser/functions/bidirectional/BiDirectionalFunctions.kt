package isdemidoff.utility.solution.inputparser.functions.bidirectional

import isdemidoff.utility.parsing.toIntOrError
import isdemidoff.utility.parsing.toLongOrError
import isdemidoff.utility.parsing.toUIntOrError
import isdemidoff.utility.parsing.toULongOrError

object BiDirectionalFunctions {
    val toInt: BiDirectionalFunction<String, Int> = String::toIntOrError.toBiDirectionalFlat()
    val toLong: BiDirectionalFunction<String, Long> = String::toLongOrError.toBiDirectionalFlat()
    val toUInt: BiDirectionalFunction<String, UInt> = String::toUIntOrError.toBiDirectionalFlat()
    val toULong: BiDirectionalFunction<String, ULong> = String::toULongOrError.toBiDirectionalFlat()

    fun <E: Comparable<E>, R : ClosedRange<E>> toRange(rangeParser: (String) -> R): BiDirectionalFunction<String, R> =
        BiDirectionalFunction(
            fn = { rangeParser(it) },
            inv = { "${it.start}-${it.endInclusive}" },
        )
}