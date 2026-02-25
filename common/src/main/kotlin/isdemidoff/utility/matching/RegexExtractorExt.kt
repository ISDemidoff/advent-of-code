@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.matching

import isdemidoff.utility.exception.validationError

fun <T> regexMatcher(regex: Regex, extractor: (MatchResult.Destructured) -> T) =
    RegexMatcher(regex, extractor)

infix fun <T> Regex.yields(extractor: (MatchResult.Destructured) -> T) = regexMatcher(this, extractor)

fun <T> matchAny(first: RegexMatcher<T>, vararg other: RegexMatcher<T>): (String) -> T = { input ->
    (listOf(first) + other)
        .firstNotNullOfOrNull { it.matchAndExtract(input) }
        ?: validationError { "Input string didn't match any of given regexp: $input" }
}