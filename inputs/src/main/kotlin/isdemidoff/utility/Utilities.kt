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