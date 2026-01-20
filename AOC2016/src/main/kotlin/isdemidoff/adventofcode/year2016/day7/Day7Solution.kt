package isdemidoff.adventofcode.year2016.day7

import isdemidoff.utility.solution.solution

/**
 * Splits incoming IP into two groups of strings:
 * 1. Supernet sequences (outside any square bracketed sections);
 * 2. Hypernet sequences (which are contained by square brackets).
 */
internal fun splitIpToSequences(ip: String): Pair<List<String>, List<String>> =
    (mutableListOf<String>() to mutableListOf<String>()).apply {
        ip.split("[", "]")
            .forEachIndexed { index, string -> (if (index % 2 == 0) this.first else this.second).add(string) }
    }

internal fun supportsTLS(ip: String): Boolean =
    splitIpToSequences(ip).let { (supernetSequences, hypernetSequences) ->
        hypernetSequences.all { !hasAbba(it) } and supernetSequences.any { hasAbba(it) }
    }

internal fun hasAbba(part: String): Boolean =
    part.windowed(size = 4, step = 1, partialWindows = false) {
        (it[0] == it[3]) and (it[1] == it[2]) and (it[0] != it[1])
    }.any { it }

internal fun supportsSSL(ip: String): Boolean =
    splitIpToSequences(ip).let { (supernetSequences, hypernetSequences) ->
        val allAbaCandidates = supernetSequences.flatMapTo(mutableSetOf()) { abaCandidates(it) }
        val allBabCandidates = hypernetSequences.flatMapTo(mutableSetOf()) { abaCandidates(it) }
        allBabCandidates.any { bab -> allAbaCandidates.any { aba -> matchesAsAbaAndBab(bab, aba) } }
    }

internal fun matchesAsAbaAndBab(a: String, b: String): Boolean =
    (a[0] == b[1]) and (a[1] == b[0]) // Assuming that a and b are already in "ABA" form

internal fun abaCandidates(part: String): List<String> =
    part.windowed(size = 3, step = 1, partialWindows = false)
        .filter { (it[0] == it[2]) and (it[0] != it[1]) }


/**
 * [Day 7: Internet Protocol Version 7](https://adventofcode.com/2016/day/7).
 */
val day7 = solution(7) {
    inputParser = uniformLinesParser { it }

    part1Solver = solver({
        "There are $it IPs that support TLS."
    }) { ips -> ips.count { supportsTLS(it) } }

    part2Solver = solver({
        "There are $it IPs that support SSL."
    }) { ips -> ips.count { supportsSSL(it) } }
}