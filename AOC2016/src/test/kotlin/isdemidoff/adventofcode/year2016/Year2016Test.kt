package isdemidoff.adventofcode.year2016

import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.test.Enabled
import io.kotest.core.test.EnabledOrReasonIf
import io.kotest.core.test.config.TestConfig
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
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
import isdemidoff.adventofcode.year2016.day18.calculateNextRow
import isdemidoff.adventofcode.year2016.day18.day18
import isdemidoff.adventofcode.year2016.day19.day19
import isdemidoff.adventofcode.year2016.day2.day2
import isdemidoff.adventofcode.year2016.day20.day20
import isdemidoff.adventofcode.year2016.day21.day21
import isdemidoff.adventofcode.year2016.day22.day22
import isdemidoff.adventofcode.year2016.day22.entity.FileSystemNode
import isdemidoff.adventofcode.year2016.day22.entity.nodeParser
import isdemidoff.adventofcode.year2016.day23.day23
import isdemidoff.adventofcode.year2016.day23.entity.MapBasedProgramState
import isdemidoff.adventofcode.year2016.day23.entity.instructionReader
import isdemidoff.adventofcode.year2016.day24.day24
import isdemidoff.adventofcode.year2016.day3.day3
import isdemidoff.adventofcode.year2016.day4.entity.Room
import isdemidoff.adventofcode.year2016.day5.day5
import isdemidoff.adventofcode.year2016.day6.day6
import isdemidoff.adventofcode.year2016.day7.supportsSSL
import isdemidoff.adventofcode.year2016.day7.supportsTLS
import isdemidoff.adventofcode.year2016.day8.entity.Screen
import isdemidoff.adventofcode.year2016.day8.entity.commandParser
import isdemidoff.adventofcode.year2016.day9.day9
import isdemidoff.solution.inputparser.scope.uniformLinesParser
import isdemidoff.solution.solution
import isdemidoff.utility.strings.md5hex

class Year2016Test : FreeSpec({
    val disableLongRunningTests = true
    val longRunningTestDisabler: EnabledOrReasonIf = {
        if (disableLongRunningTests) {
            Enabled.disabled("Takes long to compute")
        } else {
            Enabled.enabled
        }
    }

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
                day1.input { line(input) }.solvePart1() shouldBe result
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
                day1.input { line(input) }.solvePart2() shouldBe result
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
            day3.input { line(input) }.solvePart1() shouldBe if (result) 1 else 0
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

    "Day 5: How About a Nice Game of Chess?".config(config = TestConfig(enabledOrReasonIf = longRunningTestDisabler)) - {
        val builder = day5.input { line("abc") }
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

            screen.apply { executeCommands(listOf(commandParser("rect 3x2"))) }
                .showGrid() shouldBe """
                                        ###....
                                        ###....
                                        .......
                                        """.trimIndent()

            screen.apply { executeCommands(listOf(commandParser("rotate column x=1 by 1"))) }
                .showGrid() shouldBe """
                                        #.#....
                                        ###....
                                        .#.....
                                        """.trimIndent()

            screen.apply { executeCommands(listOf(commandParser("rotate row y=0 by 4"))) }
                .showGrid() shouldBe """
                                        ....#.#
                                        ###....
                                        .#.....
                                        """.trimIndent()

            screen.apply { executeCommands(listOf(commandParser("rotate column x=1 by 1"))) }
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
                day9.input { line(input) }.solvePart1() shouldBe result
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
                day9.input { line(input) }.solvePart2() shouldBe result
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
                day14.input { line("abc") }.solvePart1() shouldBe 22728
            }
        }

        "Part 2 checks" - {
            "Hash stretch is correct" {
                stretchedHash("abc0") shouldBe "a107ff634856bb300138cac6568c0f24"
            }

            "Sample check".config(config = TestConfig(enabledOrReasonIf = longRunningTestDisabler)) {
                day14.input { line("abc") }.solvePart2() shouldBe 22551
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
            day16.input { line("10000") }.solvePart1(20) shouldBe "01100"
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
                day17.input { line(input) }.solvePart1() shouldBe result
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
                day17.input { line(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 18: Like a Rogue" - {
        "Utility checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Next row after '$input' is '$result'"
                },
                "..^^." to ".^^^^",
                ".^^^^" to "^^..^",
            ) { (input, result) ->
                calculateNextRow(input) shouldBe result
            }
        }

        "Part 1 check" {
            day18.input { line(".^^.^.^^^^")}.solvePart1(10) shouldBe 38
        }
    }

    "Day 19: An Elephant Named Joseph" - {
        "Part 1 check" {
            day19.input { line("5") }.solvePart1() shouldBe 3
        }

        "Part 2 check" - {
            withData(
                nameFn = { (input, result) ->
                    "Among $input elves #$result will take all"
                },
                2 to 1,
                3 to 3,
                4 to 1,
                5 to 2,
                6 to 3,
            ) { (input, result) ->
                day19.input { line(input) }.solvePart2() shouldBe result
            }
        }
    }

    "Day 20: Firewall Rules" - {
        "Part 1 check" {
            day20.input { sampleFile() }.solvePart1() shouldBe 3.toULong()
        }

        "Part 2 check" {
            day20.input { sampleFile() }.solvePart2(9.toULong()) shouldBe 2.toULong()
        }
    }

    "Day 21: Scrambled Letters and Hash" - {
        "Part 1 check" {
            day21.input { sampleFile() }.solvePart1("abcde") shouldBe "decab"
        }

        "Part 2 check" {
            day21.input { sampleFile() }.solvePart2("decab") shouldBe "abcde"
        }
    }

    "Day 22: Grid Computing" - {
        "Check parsing" {
            nodeParser("/dev/grid/node-x0-y0     94T   72T    22T   76%") shouldBe FileSystemNode(
                xCoordinate = 0,
                yCoordinate = 0,
                used = 72,
                available = 22,
            )
        }

        "Part 2 check" {
            day22.input { sampleFile() }.solvePart2() shouldBe 7
        }
    }

    "Day 23: Safe Cracking" - {
        "Part 1 check" {
            day23.input { sampleFile() }.solvePart1().getRegisterValue("a") shouldBe 3
        }
    }

    "Day 24: Air Duct Spelunking" - {
        "Part 1 check" {
            day24.input { sampleFile() }.solvePart1() shouldBe 14
        }
        "Part 2 check" {
            day24.input { sampleFile() }.solvePart2() shouldBe 20
        }
    }

    "Day 25: Clock Signal" - {
        "Some programs check" - {
            data class ProgramInputData(
                val fileSuffix: String,
                val registers: Map<String, Int>,
                val expectedRegisters: Map<String, Int>,
            )

            withData(
                nameFn = { (program, registers, result) ->
                    "Program '$program' with predefined registers $registers result $result"
                },
                ProgramInputData(
                    "multiply",
                    mapOf(
                        "a" to 7,
                        "b" to 2,
                        "c" to 0,
                        "d" to 8,
                    ),
                    mapOf(
                        "a" to 23,
                        "b" to 2,
                        "c" to 0,
                        "d" to 0,
                    ),
                ),
                ProgramInputData(
                    "multiply",
                    mapOf(
                        "a" to 0,
                        "b" to 17,
                        "c" to 154,
                        "d" to 3,
                    ),
                    mapOf(
                        "a" to 51,
                        "b" to 17,
                        "c" to 0,
                        "d" to 0,
                    ),
                ),
                ProgramInputData(
                    "sum",
                    mapOf("a" to 5, "b" to 9),
                    mapOf("a" to 14, "b" to 0),
                ),
                ProgramInputData(
                    "sum",
                    mapOf("a" to 45, "b" to 71),
                    mapOf("a" to 116, "b" to 0),
                ),
                ProgramInputData(
                    "div",
                    mapOf(
                        "a" to 71,
                        "b" to -98746,
                        "c" to -89,
                    ),
                    mapOf(
                        "a" to 35,
                        "b" to 0,
                        "c" to 1,
                    ),
                ),
                ProgramInputData(
                    "div",
                    mapOf(
                        "a" to 150,
                        "b" to -98746,
                        "c" to -89,
                    ),
                    mapOf(
                        "a" to 75,
                        "b" to 0,
                        "c" to 2,
                    ),
                ),
            ) { (program, registers, result) ->
                val runResult = solution(42) {
                    inputParser = uniformLinesParser(instructionReader)
                    part1Solver = solver {
                        MapBasedProgramState(it).apply {
                            registers.forEach { (reg, value) -> this.updateRegisterValue(reg) { value } }
                            runProgram()
                        }
                    }
                }.input { sampleFile(program) }.solvePart1()

                result.forEach { reg, value ->
                    withClue("Register '$reg' must have value '$value'") {
                        runResult.getRegisterValue(reg) shouldBe value
                    }
                }
            }
        }
    }
})
