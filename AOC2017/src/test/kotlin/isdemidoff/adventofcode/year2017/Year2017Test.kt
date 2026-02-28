package isdemidoff.adventofcode.year2017

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import isdemidoff.adventofcode.year2017.day1.day1
import isdemidoff.adventofcode.year2017.day2.day2

class Year2017Test : FreeSpec({
    "Day 1: Inverse Captcha" - {
        "Part 1 check" - {
            withData(
                nameFn = { (input, result) ->
                    "Captcha $input answer is $result"
                },
                "1122" to 3,
                "1111" to 4,
                "1234" to 0,
                "91212129" to 9,
            ) { (input, result) ->
                day1.input { line(input) }.solvePart1() shouldBe result
            }
        }

        "Part 2 check" - {
            withData(
                nameFn = { (input, result) ->
                    "Captcha $input answer is $result"
                },
                "1212" to 6,
                "1221" to 0,
                "123425" to 4,
                "123123" to 12,
                "12131415" to 4,
            ) { (input, result) ->
                day1.input { line(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 2: Corruption Checksum" - {
        "Part 1 check" {
            day2.input { sampleFile("p1") }.solvePart1() shouldBe 18
        }

        "Part 2 check" {
            day2.input { sampleFile("p2") }.solvePart2() shouldBe 9
        }
    }
})
