@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.matching

class RegexMatcher<T>(
    private val regex: Regex,
    private val extractor: (MatchResult.Destructured) -> T,
) {
    fun matchAndExtract(input: String): T? =
        regex.matchEntire(input)
            ?.destructured
            ?.let(extractor)
}