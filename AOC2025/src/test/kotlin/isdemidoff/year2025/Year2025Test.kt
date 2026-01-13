package isdemidoff.year2025

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import isdemidoff.utility.solution.sampleFile
import isdemidoff.year2025.day1.day1
import isdemidoff.year2025.day2.day2

class Year2025Test : FreeSpec({
    "Day 1: Secret Entrance" - {
        val builder = day1.parseInput(sampleFile())
        "Part one check" { builder.solvePart1().get() shouldBe 3 }
        "Part two check" { builder.solvePart2().get() shouldBe 6 }
    }

    "Day 2: Gift Shop" - {
        val builder = day2.parseInput(sampleFile())
        "Part one check" { builder.solvePart1().get() shouldBe 1227775554 }
        "Part two check" { builder.solvePart2().get() shouldBe 4174379265 }
    }
})