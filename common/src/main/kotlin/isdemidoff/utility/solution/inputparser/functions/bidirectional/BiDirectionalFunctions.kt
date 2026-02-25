package isdemidoff.utility.solution.inputparser.functions.bidirectional

import isdemidoff.utility.parsing.intRange
import isdemidoff.utility.parsing.longRange
import isdemidoff.utility.parsing.toIntOrError
import isdemidoff.utility.parsing.toLongOrError
import isdemidoff.utility.parsing.toUIntOrError
import isdemidoff.utility.parsing.toULongOrError
import isdemidoff.utility.parsing.uIntRange
import isdemidoff.utility.parsing.uLongRange

object BiDirectionalFunctions {
    val toInt: BiDirectionalFunction<String, Int> = String::toIntOrError.toBiDirectionalFlat()
    val toUInt: BiDirectionalFunction<String, UInt> = String::toUIntOrError.toBiDirectionalFlat()
    val toLong: BiDirectionalFunction<String, Long> = String::toLongOrError.toBiDirectionalFlat()
    val toULong: BiDirectionalFunction<String, ULong> = String::toULongOrError.toBiDirectionalFlat()

    val toIntRange: BiDirectionalFunction<String, IntRange> = toRange(::intRange)
    val toUIntRange: BiDirectionalFunction<String, UIntRange> = toRange(::uIntRange)
    val toLongRange: BiDirectionalFunction<String, LongRange> = toRange(::longRange)
    val toULongRange: BiDirectionalFunction<String, ULongRange> = toRange(::uLongRange)

    fun <E: Comparable<E>, R : ClosedRange<E>> toRange(rangeParser: (String) -> R): BiDirectionalFunction<String, R> =
        BiDirectionalFunction(
            fn = { rangeParser(it) },
            inv = { "${it.start}-${it.endInclusive}" },
        )
}