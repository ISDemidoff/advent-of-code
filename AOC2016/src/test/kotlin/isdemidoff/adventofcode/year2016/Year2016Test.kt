package isdemidoff.adventofcode.year2016

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.Matcher
import io.kotest.matchers.equals.beEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave
import isdemidoff.adventofcode.year2016.day1.day1
import isdemidoff.adventofcode.year2016.day10.day10
import isdemidoff.adventofcode.year2016.day11.day11
import isdemidoff.adventofcode.year2016.day12.day12
import isdemidoff.adventofcode.year2016.day13.day13
import isdemidoff.adventofcode.year2016.day14.day14
import isdemidoff.adventofcode.year2016.day14.stretchedHash
import isdemidoff.adventofcode.year2016.day15.day15
import isdemidoff.adventofcode.year2016.day16.checksumIteration
import isdemidoff.adventofcode.year2016.day16.day16
import isdemidoff.adventofcode.year2016.day16.dragonCurveString
import isdemidoff.adventofcode.year2016.day17.day17
import isdemidoff.adventofcode.year2016.day2.day2
import isdemidoff.adventofcode.year2016.day3.day3
import isdemidoff.adventofcode.year2016.day4.entity.Room
import isdemidoff.adventofcode.year2016.day5.day5
import isdemidoff.adventofcode.year2016.day6.day6
import isdemidoff.adventofcode.year2016.day7.supportsSSL
import isdemidoff.adventofcode.year2016.day7.supportsTLS
import isdemidoff.adventofcode.year2016.day8.entity.Screen
import isdemidoff.adventofcode.year2016.day8.entity.parseCommand
import isdemidoff.adventofcode.year2016.day9.day9
import isdemidoff.utility.strings.md5hex

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

    "Day 5: How About a Nice Game of Chess?" - {
        val builder = day5.input { string("abc") }
        "Part 1 check" { builder.solvePart1() shouldBe "18f47a30" }
        "Part 2 check" { builder.solvePart2() shouldBe "05ace8e3" }
    }

    "Day 6: Signals and Noise" - {
        val builder = day6.input { sampleFile() }
        "Part 1 check" { builder.solvePart1() shouldBe "easter" }
        "Part 2 check" { builder.solvePart2() shouldBe "advent" }
    }

    "Day 7: Internet Protocol Version 7" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "IP $input ${if (result) "supports" else "does not support"} TLS"
                },
                "abba[mnop]qrst" to true,
                "abcd[bddb]xyyx" to false,
                "aaaa[qwer]tyui" to false,
                "ioxxoj[asdfgh]zxcvbn" to true,
            ) { (input, result) ->
                supportsTLS(input) shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "IP $input ${if (result) "supports" else "does not support"} SSL"
                },
                "aba[bab]xyz" to true,
                "xyx[xyx]xyx" to false,
                "aaa[kek]eke" to true,
                "zazbz[bzb]cdb" to true,
            ) { (input, result) ->
                supportsSSL(input) shouldBe result
            }
        }
    }

    "Day 8: Two-Factor Authentication" - {
        "Part 1 check by sample" {
            val screen = Screen(7, 3)

            screen.apply { executeCommands(listOf(parseCommand("rect 3x2"))) }
                .showGrid() shouldBe """
                                        ###....
                                        ###....
                                        .......
                                        """.trimIndent()

            screen.apply { executeCommands(listOf(parseCommand("rotate column x=1 by 1"))) }
                .showGrid() shouldBe """
                                        #.#....
                                        ###....
                                        .#.....
                                        """.trimIndent()

            screen.apply { executeCommands(listOf(parseCommand("rotate row y=0 by 4"))) }
                .showGrid() shouldBe """
                                        ....#.#
                                        ###....
                                        .#.....
                                        """.trimIndent()

            screen.apply { executeCommands(listOf(parseCommand("rotate column x=1 by 1"))) }
                .showGrid() shouldBe """
                                        .#..#.#
                                        #.#....
                                        .#.....
                                        """.trimIndent()
        }
    }

    "Day 9: Explosives in Cyberspace" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "$input decompresses into $result"
                },
                "ADVENT" to 6,
                "A(1x5)BC" to 7,
                "(3x3)XYZ" to 9,
                "A(2x2)BCD(2x2)EFG" to 11,
                "(6x1)(1x3)A" to 6,
                "X(8x2)(3x3)ABCY" to 18,
            ) { (input, result) ->
                day9.input { string(input) }.solvePart1() shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "$input decompresses into string with length $result"
                },
                "ADVENT" to 6,
                "(6x1)(1x3)A" to 3,
                "X(8x2)(3x3)ABCY" to 20,
                "(27x12)(20x12)(13x14)(7x10)(1x12)A" to 241920,
                "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN" to 445,
            ) { (input, result) ->
                day9.input { string(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 10: Balance Bots" - {
        "Part 1 check" {
            day10.input { sampleFile() }.solvePart1(5, 2) shouldBe 2
        }
    }

    "Day 11: Radioisotope Thermoelectric Generators" - {
        "Part 1 check" {
            day11.input { sampleFile() }.solvePart1() shouldBe 11
        }
    }

    "Day 12: Leonardo's Monorail" - {
        "Part 1 check" {
            day12.input { sampleFile() }.solvePart1().getRegisterValue("a") shouldBe 42
        }
    }

    "Day 13: A Maze of Twisty Little Cubicles" - {
        "Part 1 check" {
            day13.input { sampleFile() }.solvePart1() shouldBe 11
        }
    }

    "Day 14: One-Time Pad" - {
        "Part 1 check" - {
            "MD5 works fine" {
                md5hex("abc0") shouldBe "577571be4de9dcce85a041ba0410f29f"
            }

            "Sample check" {
                day14.input { string("abc") }.solvePart1() shouldBe 22728
            }
        }

        "Part 2 checks" - {
            "Hash stretch is correct" {
                stretchedHash("abc0") shouldBe "a107ff634856bb300138cac6568c0f24"
            }

            "Sample check" {
                day14.input { string("abc") }.solvePart2() shouldBe 22551
            }
        }
    }

    "Day 15: Timing is Everything" - {
        "Part 1 check" {
            day15.input { sampleFile() }.solvePart1() shouldBe 5
        }
    }

    "Day 16: Dragon Checksum" - {
        "Utility checks" - {
            withData(
                nameFn = { (input, result) ->
                    "'$input' populates into '$result' via dragon curve iteration"
                },
                "1" to "100",
                "0" to "001",
                "11111" to "11111000000",
                "111100001010" to "1111000010100101011110000",
            ) { (input, result) ->
                dragonCurveString(input) shouldBe result
            }

            withData(
                nameFn = { (input, result) ->
                    "'$input' iterated info '$result' after checksum iteration"
                },
                "110010110100" to "110101",
                "110101" to "100",
            ) { (input, result) ->
                checksumIteration(input) shouldBe result
            }
        }

        "Sample check" {
            day16.input { string("10000") }.solvePart1(20) shouldBe "01100"
        }
    }

    "Day 17: Two Steps Forward" - {
        "Part 1 check" - {
            withData(
                nameFn = { (input, result) ->
                    "For passcode '$input' shortest path is $result"
                },
                "ihgpwlah" to "DDRRRD",
                "kglvqrro" to "DDUDRLRRUDRD",
                "ulqzkmiv" to "DRURDRUDDLLDLUURRDULRLDUUDDDRR",
            ) { (input, result) ->
                day17.input { string(input) }.solvePart1() shouldBe result
            }
        }

        "Part 2 check" - {
            withData(
                nameFn = { (input, result) ->
                    "For passcode '$input' the longest path would be $result steps long."
                },
                "ihgpwlah" to 370,
                "kglvqrro" to 492,
                "ulqzkmiv" to 830,
            ) { (input, result) ->
                day17.input { string(input) }.solvePart2() shouldBe result
            }
        }
    }
})