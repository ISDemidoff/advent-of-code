package isdemidoff.utility

import kotlin.text.trim

/**
 * Map list of string with [toLongsList] function.
 */
fun List<List<CharSequence>>.toLongsListList(): List<List<Long>> = map { it.toLongsList() }

/**
 * Map list of string with [toLong] function.
 */
fun List<CharSequence>.toLongsList(): List<Long> = map { it.toString().toLong() }

/**
 * Take out first char of every string in a list and form a new list.
 */
fun List<CharSequence>.takeFirstChars(): List<Char> = map { it.first() }

/**
 * Parse any input with free count of whitespace delimiters, i.e. "  123  2     21 2" converts into list("123","2","21","2").
 */
fun CharSequence.parseWhitespaceDelimitedInput(): List<CharSequence> = trim().split("""\s+""".toRegex())

/**
 * Map list of string with [parseWhitespaceDelimitedInput] function.
 */
fun List<CharSequence>.parseWhitespaceDelimitedInputList(): List<List<CharSequence>> = map { it.parseWhitespaceDelimitedInput() }

/**
 * Read line in form of "123,423,235" into something like list("123","423","235") with additional transform into any other form.
 */
fun <R> CharSequence.parseUnescapedCsvInputLine(delimiter: Char = ',', transform: (CharSequence) -> R): List<R> = trim().split(delimiter).map(transform)
fun CharSequence.parseUnescapedCsvInputLine(delimiter: Char = ',') = parseUnescapedCsvInputLine(delimiter) { it }

/**
 * Map list of strings with [parseUnescapedCsvInputLine] function.
 */
fun <R> List<CharSequence>.parseUnescapedCsvInputLines(delimiter: Char = ',', transform: (CharSequence) -> R): List<List<R>> = map { it.parseUnescapedCsvInputLine(delimiter, transform) }
fun List<CharSequence>.parseUnescapedCsvInputLines(delimiter: Char = ',') = parseUnescapedCsvInputLines(delimiter) { it }

/**
 * Converts a string like a "1-10" or "23-412" to a standard LongRange with inclusive end.
 */
fun CharSequence.toLongRange(): LongRange = this.split("-").also { check(it.size == 2) { "Range must be set with exactly two boundaries" } }.let { it.first().toLong()..it.last().toLong() }

/**
 * Map list of string with [toLongRange] function.
 */
fun List<CharSequence>.toLongRanges(): List<LongRange> = map { it.toLongRange() }

/**
 * Check whether is current number is valid index for list with size [limit].
 */
fun Int.isInvalidPosition(limit: Int): Boolean = this !in 0..<limit

fun <A, B> cartesianProduct(a: List<A>, b: List<B>): List<Pair<A, B>> =
    a.flatMap { aElem -> b.map { bElem -> aElem to bElem } }

fun <A, B, R> cartesianProduct(a: List<A>, b: List<B>, transform: (Pair<A, B>) -> R): List<R> =
    a.flatMap { aElem -> b.map { bElem -> transform(aElem to bElem) } }