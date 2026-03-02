package isdemidoff.adventofcode.year2017

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import isdemidoff.adventofcode.year2017.day1.day1
import isdemidoff.adventofcode.year2017.day2.day2
import isdemidoff.adventofcode.year2017.day3.day3
import isdemidoff.adventofcode.year2017.day4.day4
import isdemidoff.adventofcode.year2017.day5.day5
import isdemidoff.adventofcode.year2017.day6.day6

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

    "Day 3: Spiral Memory" - {
        "Part 1 check" - {
            withData(
                nameFn = { (input, result) ->
                    "From data square $input we need to take $result steps"
                },
                1 to 0,
                12 to 3,
                23 to 2,
                1024 to 31,
            ) { (input, result) ->
                day3.input { raw(input) }.solvePart1() shouldBe result
            }
        }

        "Part 2 check" - {
            withData(
                nameFn = { (input, result) ->
                    "First number larger that $input is $result"
                },
                1 to 2,
                2 to 4,
                3 to 4,
                4 to 5,
                5 to 10,
                6 to 10,
                7 to 10,
                15 to 23,
            ) { (input, result) ->
                day3.input { raw(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 4: High-Entropy Passphrases" - {
        "Part 1 check" - {
            withData(
                nameFn = { (input, result) ->
                    "Passphrase $input is ${if (result) "" else "not "}valid"
                },
                "aa bb cc dd ee" to true,
                "aa bb cc dd aa" to false,
                "aa bb cc dd aaa" to true,
            ) { (input, result) ->
                day4.input { line(input) }.solvePart1() shouldBe if (result) 1 else 0
            }
        }

        "Part 2 check" - {
            withData(
                nameFn = { (input, result) ->
                    "Passphrase $input is ${if (result) "" else "not "}valid"
                },
                "abcde fghij" to true,
                "abcde xyz ecdab" to false,
                "a ab abc abd abf abj" to true,
                "iiii oiii ooii oooi oooo" to true,
                "oiii ioii iioi iiio" to false,
            ) { (input, result) ->
                day4.input { line(input) }.solvePart2() shouldBe if (result) 1 else 0
            }
        }
    }

    "Day 5: A Maze of Twisty Trampolines, All Alike" - {
        "Part 1 check" {
            day5.input { sampleFile() }.solvePart1() shouldBe 5
        }

        "Part 2 check" {
            day5.input { sampleFile() }.solvePart2() shouldBe 10
        }
    }

    "Day 6: Memory Reallocation" - {
        "Part 1 check" {
            day6.input { raw(listOf(0, 2, 7, 0)) }.solvePart1() shouldBe 5
        }

        "Part 2 check" {
            day6.input { raw(listOf(0, 2, 7, 0)) }.solvePart2() shouldBe 4
        }
    }
})
