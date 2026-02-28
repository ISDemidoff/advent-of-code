@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.matching

import isdemidoff.utility.exception.validationError

infix fun <T> Regex.yields(extractor: (MatchResult.Destructured) -> T) = RegexMatcher(this, extractor)

fun <T> regexMatch(first: RegexMatcher<T>, vararg other: RegexMatcher<T>): (String) -> T = { input ->
    (listOf(first) + other)
        .firstNotNullOfOrNull { it.matchAndExtract(input) }
        ?: validationError { "Input string didn't match any of given regexp: $input" }
}