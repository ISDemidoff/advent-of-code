package isdemidoff.year2015

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import isdemidoff.SolutionBuilder
import isdemidoff.utility.test.TestConstants.INPUT_FILE_NAME
import isdemidoff.utility.test.TestConstants.PART_TWO_SUFFIX
import isdemidoff.utility.test.TestConstants.SAMPLE_CHECK_TEST_NAME
import isdemidoff.utility.test.TestConstants.SAMPLE_FILE_NAME
import isdemidoff.utility.test.TestConstants.TARGET_CHECK_TEST_NAME
import isdemidoff.year2015.day1.Day1Solution
import isdemidoff.year2015.day1.Day1SolutionBuilder
import isdemidoff.year2015.day10.Day10SolutionBuilder
import isdemidoff.year2015.day10.nextApply
import isdemidoff.year2015.day11.Day11Solution
import isdemidoff.year2015.day11.Day11SolutionBuilder
import isdemidoff.year2015.day11.isValidPassword
import isdemidoff.year2015.day12.Day12Solution
import isdemidoff.year2015.day12.Day12SolutionBuilder
import isdemidoff.year2015.day13.Day13SolutionBuilder
import isdemidoff.year2015.day14.Day14Solution
import isdemidoff.year2015.day14.Day14SolutionBuilder
import isdemidoff.year2015.day14.entity.createReindeer
import isdemidoff.year2015.day15.Day15SolutionBuilder
import isdemidoff.year2015.day2.Day2SolutionBuilder
import isdemidoff.year2015.day3.Day3Solution
import isdemidoff.year2015.day3.Day3SolutionBuilder
import isdemidoff.year2015.day4.Day4Solution
import isdemidoff.year2015.day4.Day4SolutionBuilder
import isdemidoff.year2015.day5.Day5Solution
import isdemidoff.year2015.day5.Day5SolutionBuilder
import isdemidoff.year2015.day5.RulesSet
import isdemidoff.year2015.day6.Day6SolutionBuilder
import isdemidoff.year2015.day6.entity.BrightnessLight
import isdemidoff.year2015.day7.Day7SolutionBuilder
import isdemidoff.year2015.day8.Day8Solution
import isdemidoff.year2015.day8.Day8SolutionBuilder
import isdemidoff.year2015.day9.Day9SolutionBuilder

class Year2015Test : FreeSpec({
    "Day 1" - {
        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "(())" to 0,
                "()()" to 0,
                "(((" to 3,
                "(()(()(" to 3,
                "))(((((" to 3,
                "())" to -1,
                "))(" to -1,
                ")))" to -3,
                ")())())" to -3,
            ).forEach { (input, result) ->
                "\"$input\" leads to $result floor" {
                    Day1Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day1SolutionBuilder("day1") }
    }

    "Day 2" - {
        val builder = Day2SolutionBuilder("day2")

        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "sample1.txt" to (58 to 34),
                "sample2.txt" to (43 to 14),
            ).forEach { (filename, result) ->
                "file $filename results to ${result.first} wrapping and ${result.second} ribbon" {
                    builder.buildAndSolve(filename) shouldBe result
                }
            }
        }

        createTargetShowingTest { builder }
    }

    "Day 3" - {
        val builder = Day3SolutionBuilder("day3")

        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                ">" to 2,
                "^>v<" to 4,
                "^v^v^v^v^v" to 2,
            ).forEach { (input, result) ->
                "instruction \"$input\" results to $result houses visited" {
                    Day3Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { builder }

        "$SAMPLE_CHECK_TEST_NAME $PART_TWO_SUFFIX" - {
            listOf(
                "^v" to 3,
                "^>v<" to 3,
                "^v^v^v^v^v" to 11,
            ).forEach { (input, result) ->
                "instruction \"$input\" results to $result houses visited by 2 couriers" {
                    Day3Solution(input, 2).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest(testNameSuffix = PART_TWO_SUFFIX) { builder.forNumberOfCouriers(2) }
    }

    "Day 4" - {
        val builder = Day4SolutionBuilder("day4")

        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "abcdef" to 609043,
                "pqrstuv" to 1048970,
            ).forEach { (input, result) ->
                "\"$input\" has $result as least prefix to get 5 leading zeros" {
                    Day4Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { builder }
        createTargetShowingTest(testNameSuffix = PART_TWO_SUFFIX) { builder.withStartingPattern("0".repeat(6)) }
    }

    "Day 5" - {
        val builder = Day5SolutionBuilder("day5")

        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "ugknbfddgicrmopn" to true,
                "aaa" to true,
                "uuuuuuu" to true,
                "jchzalrnumimnmhp" to false,
                "haegwjzuvuyypxyu" to false,
                "dvszwmarrgswjxmb" to false,
            ).forEach { (input, result) ->
                "\"$input\" is ${if(result) "nice" else "naughty"}" {
                    Day5Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { builder }

        "$SAMPLE_CHECK_TEST_NAME $PART_TWO_SUFFIX" - {
            listOf(
                "qjhvhtzxzqqjkmpb" to true,
                "xxyxx" to true,
                "uurcxstgmygtbstg" to false,
                "ieodomkazucvgmuy" to false,
            ).forEach { (input, result) ->
                "\"$input\" is ${if(result) "nice" else "naughty"}" {
                    Day5Solution(input, RulesSet.PART_TWO).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest(testNameSuffix = PART_TWO_SUFFIX) { builder.forRulesSet(RulesSet.PART_TWO) }
    }

    "Day 6" - {
        val builder = Day6SolutionBuilder("day6")

        SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 998000
        }

        createTargetShowingTest { builder }
        createTargetShowingTest(testNameSuffix = PART_TWO_SUFFIX) { builder.withLightGenerator { BrightnessLight() } }
    }

    "Day 7" - {
        val builder = Day7SolutionBuilder("day7")

        SAMPLE_CHECK_TEST_NAME {
            val completedCircuit = builder.buildAndSolve(SAMPLE_FILE_NAME)
            completedCircuit.getValue("d") shouldBe 72.toUShort()
            completedCircuit.getValue("e") shouldBe 507.toUShort()
            completedCircuit.getValue("f") shouldBe 492.toUShort()
            completedCircuit.getValue("g") shouldBe 114.toUShort()
            completedCircuit.getValue("h") shouldBe 65412.toUShort()
            completedCircuit.getValue("i") shouldBe 65079.toUShort()
            completedCircuit.getValue("x") shouldBe 123.toUShort()
            completedCircuit.getValue("y") shouldBe 456.toUShort()
        }

        createTargetShowingTest(resultExtractor = { it.getValue("a") }) { builder }
    }

    "Day 8" - {
        val builder = Day8SolutionBuilder("day8")

        SAMPLE_CHECK_TEST_NAME - {
            "From sample file" {
                builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 12
            }

            "Just texts" - {
                listOf(
                    """""""" to 2,
                    """"abc"""" to 2,
                    """"aaa\"aaa"""" to 3,
                    """"\x27"""" to 5,
                    """"\\\xa6"""" to 6,
                    """"p\"zqyw"""" to 3,
                    """"\\\\"""" to 4,
                ).forEach { (input, result) ->
                    "$input results to $result" {
                        Day8Solution(input).solve() shouldBe result
                    }
                }
            }
        }

        createTargetShowingTest { builder }
    }

    "Day 9" - {
        val builder = Day9SolutionBuilder("day9")

        SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 605
        }

        createTargetShowingTest { builder }
    }

    "Day 10" - {
        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "1" to "11",
                "11" to "21",
                "21" to "1211",
                "1211" to "111221",
                "111221" to "312211",
            ).forEach { (input, result) ->
                "\"$input\" iterates to \"$result\"" {
                    input.nextApply() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day10SolutionBuilder("day10") }
    }

    "Day 11" - {
        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "abcdefgh" to "abcdffaa",
                "ghijklmn" to "ghjaabcc",
            ).forEach { (input, result) ->
                "Next password after \"$input\" is \"$result\"" {
                    Day11Solution(input).solve() shouldBe result
                }
            }

            "Helper functions check" {
                "abcdffaa".isValidPassword() shouldBe true
            }
        }

        createTargetShowingTest { Day11SolutionBuilder("day11") }
    }

    "Day 12" - {
        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "[1,2,3]" to 6,
                """{"a":2,"b":4}""" to 6,
                "[[[3]]]" to 3,
                """{"a":{"b":4},"c":-1}""" to 3,
                """{"a":[-1,1]}""" to 0,
                """[-1,{"a":1}]""" to 0,
                "[]" to 0,
                "{}" to 0,
            ).forEach { (input, result) ->
                "\"$input\" has sum of $result" {
                    Day12Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day12SolutionBuilder("day12") }
    }

    "Day 13" - {
        val builder = Day13SolutionBuilder("day13")

        SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 330
        }

        createTargetShowingTest { builder }
    }

    "Day 14" - {
        SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds." to 1120,
                "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds." to 1056,
            ).forEach { (input, result) ->
                "${input.substringBefore(' ')} has result of $result km after 1000 seconds" {
                    Day14Solution(input.createReindeer(), 1000).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day14SolutionBuilder("day14").forSeconds(2503) }
    }

    "Day 15" - {
        val builder = Day15SolutionBuilder("day15")

        SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 62842880
        }

        createTargetShowingTest { builder }
    }
}) {
    companion object {
        inline fun <I : Any> showTargetAnswer(
            resultExtractor: (I) -> Any = { it },
            solutionBuilderSupplier: () -> SolutionBuilder<I>,
        ) = shouldNotThrow<Throwable> { println(resultExtractor(solutionBuilderSupplier().buildAndSolve(INPUT_FILE_NAME))) }

        suspend inline fun <I : Any> FreeSpecContainerScope.createTargetShowingTest(
            testNameSuffix: String = "",
            crossinline resultExtractor: (I) -> Any = { it },
            crossinline solutionBuilderSupplier: () -> SolutionBuilder<I>,
        ) = ("$TARGET_CHECK_TEST_NAME $testNameSuffix") { showTargetAnswer(resultExtractor, solutionBuilderSupplier) }
    }
}