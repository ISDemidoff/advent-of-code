package isdemidoff.utility

import kotlin.text.trim

/**
 * Map list of string with [toLongsList] function.
 */
fun List<List<String>>.toLongsListList(): List<List<Long>> = map { it.toLongsList() }

/**
 * Map list of string with [toLong] function.
 */
fun List<String>.toLongsList(): List<Long> = map { it.toLong() }

/**
 * Take out first char of every string in a list and form a new list.
 */
fun List<String>.takeFirstChars(): List<Char> = map { it.first() }

/**
 * Parse any input with free count of whitespace delimiters, i.e. "  123  2     21 2" converts into list("123","2","21","2").
 */
fun String.parseWhitespaceDelimitedInput(): List<String> = trim().split("""\s+""".toRegex())

/**
 * Map list of string with [parseWhitespaceDelimitedInput] function.
 */
fun List<String>.parseWhitespaceDelimitedInputList(): List<List<String>> = map { it.parseWhitespaceDelimitedInput() }

/**
 * Read line in form of "123,423,235" into something like list("123","423","235") with additional transform into any other form.
 */
fun <R> String.parseUnescapedCsvInputLine(delimiter: Char = ',', transform: (String) -> R): List<R> = trim().split(delimiter).map(transform)
fun String.parseUnescapedCsvInputLine(delimiter: Char = ',') = parseUnescapedCsvInputLine(delimiter) { it }

/**
 * Map list of strings with [parseUnescapedCsvInputLine] function.
 */
fun <R> List<String>.parseUnescapedCsvInputLines(delimiter: Char = ',', transform: (String) -> R): List<List<R>> = map { it.parseUnescapedCsvInputLine(delimiter, transform) }
fun List<String>.parseUnescapedCsvInputLines(delimiter: Char = ',') = parseUnescapedCsvInputLines(delimiter) { it }

/**
 * Converts a string like a "1-10" or "23-412" to a standard LongRange with inclusive end.
 */
fun String.toLongRange(): LongRange = this.split("-").let { it.first().toLong()..it.last().toLong() }

/**
 * Map list of string with [toLongRange] function.
 */
fun List<String>.toLongRanges(): List<LongRange> = map { it.toLongRange() }

/**
 * Check whether is current number is valid index for list with size [limit].
 */
fun Int.isInvalidPosition(limit: Int): Boolean = this !in 0..<limit

fun <A, B> cartesianProduct(a: List<A>, b: List<B>): List<Pair<A, B>> =
    a.flatMap { aElem -> b.map { bElem -> aElem to bElem } }

fun <A, B, R> cartesianProduct(a: List<A>, b: List<B>, transform: (Pair<A, B>) -> R): List<R> =
    a.flatMap { aElem -> b.map { bElem -> transform(aElem to bElem) } }