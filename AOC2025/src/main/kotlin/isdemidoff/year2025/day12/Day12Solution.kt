package isdemidoff.year2025.day12

import isdemidoff.utility.solution.solution
import isdemidoff.year2025.day12.entity.GiftBox
import isdemidoff.year2025.day12.entity.GiftRegion
import isdemidoff.year2025.day12.entity.toGiftBoxExtendedInput
import isdemidoff.year2025.day12.entity.toGiftRegion

/**
 * [Day 12: Christmas Tree Farm](https://adventofcode.com/2025/day/12).
 */
val day12 = solution<Pair<List<GiftBox>, List<GiftRegion>>, Int>(12) {
    inputParser = inputParser { blocks ->
        require(blocks.size == 7) { "There must bt 7 blocks in input." }

        val giftBoxes = blocks.take(6).map { it.toGiftBoxExtendedInput() }
        val giftRegions = blocks.drop(6).single().map { it.toGiftRegion() }

        giftBoxes to giftRegions
    }

    part1Solver = solver({ "There are $it regions that fitting all boxes." }) { (boxes, regions) ->
        regions.count { it.isFitting(boxes) }
    }
}