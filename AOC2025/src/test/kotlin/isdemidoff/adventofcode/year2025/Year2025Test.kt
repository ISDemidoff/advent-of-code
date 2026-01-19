package isdemidoff.adventofcode.year2025

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import isdemidoff.adventofcode.year2025.day1.day1
import isdemidoff.adventofcode.year2025.day10.day10
import isdemidoff.adventofcode.year2025.day11.day11
import isdemidoff.adventofcode.year2025.day12.day12
import isdemidoff.adventofcode.year2025.day2.day2
import isdemidoff.adventofcode.year2025.day3.day3
import isdemidoff.adventofcode.year2025.day4.day4
import isdemidoff.adventofcode.year2025.day5.day5
import isdemidoff.adventofcode.year2025.day6.day6
import isdemidoff.adventofcode.year2025.day7.day7
import isdemidoff.adventofcode.year2025.day8.day8
import isdemidoff.adventofcode.year2025.day9.day9

@Suppress("KotlinUnreachableCode")
class Year2025Test : FreeSpec({
    "Day 1: Secret Entrance" - {
        val builder = day1.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 3 }
        "Part two check" { builder.solvePart2().get() shouldBe 6 }
    }

    "Day 2: Gift Shop" - {
        val builder = day2.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 1227775554 }
        "Part two check" { builder.solvePart2().get() shouldBe 4174379265 }
    }

    "Day 3: Lobby" - {
        val builder = day3.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 357 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 4: Printing Department" - {
        val builder = day4.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 13 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 5: Cafeteria" - {
        val builder = day5.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 3 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 6: Trash Compactor" - {
        val builder = day6.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 4277556 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 7: Laboratories" - {
        val builder = day7.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 21 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 8: Playground" - {
        val builder = day8.input { sampleFile() }
        "Part one check" { builder.solvePart1(10).get() shouldBe 40 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 9: Movie Theater" - {
        val builder = day9.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 50 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 10: Factory" - {
        val builder = day10.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 7 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 11: Reactor" - {
        val builder = day11.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 5 }
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }

    "Day 12: Christmas Tree Farm" - {
        val builder = day12.input { sampleFile() }
        "Part one check" { builder.solvePart1().get() shouldBe 3 } // Lol actually not
        "!Part two check" { builder.solvePart2().get() shouldBe TODO() }
    }
})