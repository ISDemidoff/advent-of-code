@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility

/**
 * Parse any input with free count of whitespace delimiters, i.e. "  123  2     21 2" converts into list("123","2","21","2").
 */
fun CharSequence.parseWhitespaceDelimitedInput(): List<CharSequence> = trim().split("""\s+""".toRegex())

fun <R> CharSequence.parseWhitespaceDelimitedInput(transform: (CharSequence) -> R): List<R> = parseWhitespaceDelimitedInput().map { transform(it) }

/**
 * Map list of string with [parseWhitespaceDelimitedInput] function.
 */
fun List<CharSequence>.parseWhitespaceDelimitedInputList(): List<List<CharSequence>> = map { it.parseWhitespaceDelimitedInput() }

/**
 * Read line in form of "123,423,235" into something like list("123","423","235") with additional transform into any other form.
 */
fun <R> CharSequence.parseUnescapedCsvInputLine(delimiter: String = ",", transform: (CharSequence) -> R): List<R> = trim().split(delimiter).map(transform)
fun CharSequence.parseUnescapedCsvInputLine(delimiter: String = ",") = parseUnescapedCsvInputLine(delimiter) { it }

/**
 * Map list of strings with [parseUnescapedCsvInputLine] function.
 */
fun <R> List<CharSequence>.parseUnescapedCsvInputLines(delimiter: String = ",", transform: (CharSequence) -> R): List<List<R>> = map { it.parseUnescapedCsvInputLine(delimiter, transform) }
fun List<CharSequence>.parseUnescapedCsvInputLines(delimiter: String = ",") = parseUnescapedCsvInputLines(delimiter) { it }

/**
 * Check whether is current number is valid index for list with size [limit].
 */
fun Int.isInvalidPosition(limit: Int): Boolean = this !in 0..<limit

fun <A, B> cartesianProduct(a: List<A>, b: List<B>): List<Pair<A, B>> =
    a.flatMap { aElem -> b.map { bElem -> aElem to bElem } }

fun <A, B, R> cartesianProduct(a: List<A>, b: List<B>, transform: (Pair<A, B>) -> R): List<R> =
    a.flatMap { aElem -> b.map { bElem -> transform(aElem to bElem) } }
