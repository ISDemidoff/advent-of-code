package isdemidoff.adventofcode.year2017

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import isdemidoff.adventofcode.year2017.day1.day1
import isdemidoff.adventofcode.year2017.day10.day10
import isdemidoff.adventofcode.year2017.day11.day11
import isdemidoff.adventofcode.year2017.day2.day2
import isdemidoff.adventofcode.year2017.day3.day3
import isdemidoff.adventofcode.year2017.day4.day4
import isdemidoff.adventofcode.year2017.day5.day5
import isdemidoff.adventofcode.year2017.day6.day6
import isdemidoff.adventofcode.year2017.day7.day7
import isdemidoff.adventofcode.year2017.day8.day8
import isdemidoff.adventofcode.year2017.day9.day9

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

    "Day 7: Recursive Circus" - {
        "Part 1 check" {
            day7.input { sampleFile() }.solvePart1() shouldBe "tknk"
        }

        "Part 2 check" {
            day7.input { sampleFile() }.solvePart2() shouldBe 60
        }
    }

    "Day 8: I Heard You Like Registers" - {
        "Part 1 check" {
            day8.input { sampleFile() }.solvePart1() shouldBe 1
        }

        "Part 2 check" {
            day8.input { sampleFile() }.solvePart2() shouldBe 10
        }
    }

    "Day 9: Stream Processing" - {
        "Part 1 check" - {
            withData(
                nameFn = { (input, result) ->
                    "Data '$input' has score $result"
                },
                "{}" to 1,
                "{{{}}}" to 6,
                "{{},{}}" to 5,
                "{{{},{},{{}}}}" to 16,
                "{<a>,<a>,<a>,<a>}" to 1,
                "{{<ab>},{<ab>},{<ab>},{<ab>}}" to 9,
                "{{<!!>},{<!!>},{<!!>},{<!!>}}" to 9,
                "{{<a!>},{<a!>},{<a!>},{<ab>}}" to 3,
            ) { (input, result) ->
                day9.input { raw(input) }.solvePart1() shouldBe result
            }
        }

        "Part 2 check" - {
            withData(
                nameFn = { (input, result) ->
                    "Data '$input' has $result garbage characters"
                },
                "<>" to 0,
                "<random characters>" to 17,
                "<<<<>" to 3,
                "<{!>}>" to 2,
                "<!!>" to 0,
                "<!!!>>" to 0,
                "<{o\"i!a,<{i<a>" to 10,
            ) { (input, result) ->
                day9.input { raw(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 10: Knot Hash" - {
        "Part 1 check" {
            day10.input { line("3,4,1,5") }.solvePart1(5) shouldBe 12
        }

        "Part 2 check" - {
            withData(
                nameFn = { (input, result) ->
                    "For string '$input' knot hash is $result"
                },
                "" to "a2582a3a0e66e6e86e3812dcb672a272",
                "AoC 2017" to "33efeb34ea91902bb2f59c9920caa6cd",
                "1,2,3" to "3efbe78a8d82f29979031a4aa0b16a9d",
                "1,2,4" to "63960835bcdc130f0b66d7ff4f6a5a8e",
            ) { (input, result) ->
                day10.input { line(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 11: Hex Ed" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Path '$input' least to hex $result steps away"
                },
                "ne,ne,ne" to 3,
                "ne,ne,sw,sw" to 0,
                "ne,ne,s,s" to 2,
                "se,sw,se,sw,sw" to 3,
                "ne,se" to 2,
            ) { (input, result) ->
                day11.input { line(input) }.solvePart1() shouldBe result
            }
        }
    }
})
