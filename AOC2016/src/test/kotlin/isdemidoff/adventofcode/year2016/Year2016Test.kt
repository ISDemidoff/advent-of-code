package isdemidoff.adventofcode.year2016

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import isdemidoff.adventofcode.year2016.day1.day1
import isdemidoff.adventofcode.year2016.day2.day2
import isdemidoff.adventofcode.year2016.day3.day3
import isdemidoff.adventofcode.year2016.day4.entity.Room

class Year2016Test : FreeSpec({
    "Day 1: No Time for a Taxicab" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "For instructions '$input' result will be $result"
                },
                "R2, L3" to 5,
                "R2, R2, R2" to 2,
                "R5, L5, R5, R3" to 12,
            ) { (input, result) ->
                day1.input { string(input) }.solvePart1() shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "For instructions '$input' result will be $result"
                },
                "R8, R4, R4, R8" to 4,
                "R2, R2, R2, R6" to 0,
                "R5, L5, R5, R3, R10" to 7,
            ) { (input, result) ->
                day1.input { string(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 2: Bathroom Security" - {
        val builder = day2.input { sampleFile() }
        "Part 1 check" { builder.solvePart1() shouldBe "1985" }
        "Part 2 check" { builder.solvePart2() shouldBe "5DB3" }
    }

    "Day 3: Squares With Three Sides" - {
        withData(
            nameFn = { (input, result) ->
                "$input is${if (result) "" else " not"} a triangle"
            },
            "5 10 25" to false,
            "3 4 5" to true,
        ) { (input, result) ->
            day3.input { string(input) }.solvePart1() shouldBe if (result) 1 else 0
        }
    }

    "Day 4: Security Through Obscurity" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Room $input is ${if (result) "real" else "a decoy"}"
                },
                "aaaaa-bbb-z-y-x-123[abxyz]" to true,
                "a-b-c-d-e-f-g-h-987[abcde]" to true,
                "not-a-real-room-404[oarel]" to true,
                "totally-real-room-200[decoy]" to false,
            ) { (input, result) ->
                Room(input).isReal() shouldBe result
            }
        }

        "Part 2 check by sample" {
            Room("qzmt-zixmtkozy-ivhz-343[zimth]").decryptName() shouldBe "very encrypted name"
        }
    }
})