package isdemidoff.adventofcode.year2016.day4

import isdemidoff.adventofcode.year2016.day4.entity.Room
import isdemidoff.utility.solution.solution

/**
 * [Day 4: Security Through Obscurity](https://adventofcode.com/2016/day/4).
 *
 * Second part solved correctly just by chance, may be right idea to search explicit decrypted name `northpole object storage`.
 */
val day4 = solution(4) {
    inputParser = uniformLinesParser { Room(it) }

    part1Solver = solver({
        "Sum of real rooms sector IDs is $it."
    }) { it.filter { it.isReal() }.sumOf { it.sectorId } }

    part2Solver = solver({
        "Seems like North Pole objects stored at room in sector id $it."
    }) { it.filter { it.isReal() }.single { it.decryptName().contains("north") }.sectorId }
}