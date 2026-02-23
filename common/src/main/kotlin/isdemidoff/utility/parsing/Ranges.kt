package isdemidoff.utility.parsing

fun longRange(input: String): LongRange = createRange(input, String::toLongOrNull, ::LongRange)
fun uLongRange(input: String): ULongRange = createRange(input, String::toULongOrNull, ::ULongRange)
fun intRange(input: String): IntRange = createRange(input, String::toIntOrNull, ::IntRange)
fun uIntRange(input: String): UIntRange = createRange(input, String::toUIntOrNull, ::UIntRange)

/**
 * Reusable function to reduce repetitions. Parses any form of ranges written as `$left-$right`.
 *
 * @param eParser parser for both ranges
 * @param rangeProducer constructor of range
 */
@Suppress("RedundantRequireNotNullCall") // Dunno why Idea calls these calls redundant
private inline fun <E : Comparable<E>, R : ClosedRange<E>> createRange(
    input: String,
    crossinline eParser: (String) -> E?,
    crossinline rangeProducer: (E, E) -> R,
): R = input.keyValueBy(
    delimiter = "-",
    keyTransformer = { requireNotNull(eParser(it)) { "Incorrect range: $input" } },
    valueTransformer = { requireNotNull(eParser(it)) { "Incorrect range: $input" } },
).let { rangeProducer(it.first, it.second) }

fun toLongRanges(strings: List<String>): List<LongRange> = strings.map { longRange(it) }
fun toULongRanges(strings: List<String>): List<ULongRange> = strings.map { uLongRange(it) }
fun toIntRanges(strings: List<String>): List<IntRange> = strings.map { intRange(it) }
fun toUIntRanges(strings: List<String>): List<UIntRange> = strings.map { uIntRange(it) }