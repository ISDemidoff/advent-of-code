package isdemidoff.year2025.day12

import isdemidoff.SimpleDeprecatedSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2025.day12.entity.GiftBox
import isdemidoff.year2025.day12.entity.GiftRegion
import isdemidoff.year2025.day12.entity.toGiftBoxExtendedInput
import isdemidoff.year2025.day12.entity.toGiftRegion

class Day12SolutionBuilder(day12Path: String) : SimpleDeprecatedSolutionBuilder<Int, Pair<List<GiftBox>, List<GiftRegion>>>(
    inputsDir = day12Path,
    inputParser = { filename ->
        readLines(filename)
            .let { it.take(30) to it.drop(30) }
            .let { (boxesRaw, regionsRaw) ->
                boxesRaw.windowed(5, 5) {
                    it.toGiftBoxExtendedInput()
                } to regionsRaw.map {
                    it.toGiftRegion()
                }
            }
    },
    solver = { (boxes, regions) -> regions.count { it.isFitting(boxes) } },
)