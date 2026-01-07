package isdemidoff.year2015

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import isdemidoff.SolutionBuilder
import isdemidoff.utility.test.TestConstants
import isdemidoff.year2015.day1.Day1Solution
import isdemidoff.year2015.day1.Day1SolutionBuilder
import isdemidoff.year2015.day10.Day10SolutionBuilder
import isdemidoff.year2015.day10.nextApply
import isdemidoff.year2015.day11.Day11Solution
import isdemidoff.year2015.day11.Day11SolutionBuilder
import isdemidoff.year2015.day11.isValidPassword
import isdemidoff.year2015.day2.Day2SolutionBuilder
import isdemidoff.year2015.day3.Day3Solution
import isdemidoff.year2015.day3.Day3SolutionBuilder
import isdemidoff.year2015.day4.Day4Solution
import isdemidoff.year2015.day4.Day4SolutionBuilder
import isdemidoff.year2015.day5.Day5Solution
import isdemidoff.year2015.day5.Day5SolutionBuilder
import isdemidoff.year2015.day6.Day6SolutionBuilder
import isdemidoff.year2015.day7.Day7SolutionBuilder
import isdemidoff.year2015.day8.Day8Solution
import isdemidoff.year2015.day8.Day8SolutionBuilder
import isdemidoff.year2015.day9.Day9SolutionBuilder

class Year2015Test : FreeSpec({
    "Day 1" - {
        TestConstants.SAMPLE_CHECK_TEST_NAME - {
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
                "\"$input\" results to $result" {
                    Day1Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day1SolutionBuilder("day1") }
    }

    "Day 2" - {
        val builder = Day2SolutionBuilder("day2")

        TestConstants.SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "sample1.txt" to 58,
                "sample2.txt" to 43,
            ).forEach { (filename, result) ->
                "file $filename results to $result" {
                    builder.buildAndSolve(filename) shouldBe result
                }
            }
        }

        createTargetShowingTest { builder }
    }

    "Day 3" - {
        TestConstants.SAMPLE_CHECK_TEST_NAME - {
            listOf(
                ">" to 2,
                "^>v<" to 4,
                "^v^v^v^v^v" to 2,
            ).forEach { (input, result) ->
                "\"$input\" results to $result" {
                    Day3Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day3SolutionBuilder("day3") }
    }

    "Day 4" - {
        TestConstants.SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "abcdef" to 609043,
                "pqrstuv" to 1048970,
            ).forEach { (input, result) ->
                "\"$input\" results to $result" {
                    Day4Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day4SolutionBuilder("day4") }
    }

    "Day 5" - {
        TestConstants.SAMPLE_CHECK_TEST_NAME - {
            listOf(
                "ugknbfddgicrmopn" to true,
                "aaa" to true,
                "uuuuuuu" to true,
                "jchzalrnumimnmhp" to false,
                "haegwjzuvuyypxyu" to false,
                "dvszwmarrgswjxmb" to false,
            ).forEach { (input, result) ->
                "\"$input\" results to $result" {
                    Day5Solution(input).solve() shouldBe result
                }
            }
        }

        createTargetShowingTest { Day5SolutionBuilder("day5") }
    }

    "Day 6" - {
        val builder = Day6SolutionBuilder("day6")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 998000
        }

        createTargetShowingTest() { builder }
    }

    "Day 7" - {
        val builder = Day7SolutionBuilder("day7")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            val completedCircuit = builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME)
            completedCircuit.getValue("d") shouldBe 72.toUShort()
            completedCircuit.getValue("e") shouldBe 507.toUShort()
            completedCircuit.getValue("f") shouldBe 492.toUShort()
            completedCircuit.getValue("g") shouldBe 114.toUShort()
            completedCircuit.getValue("h") shouldBe 65412.toUShort()
            completedCircuit.getValue("i") shouldBe 65079.toUShort()
            completedCircuit.getValue("x") shouldBe 123.toUShort()
            completedCircuit.getValue("y") shouldBe 456.toUShort()
        }

        createTargetShowingTest({ it.getValue("a") }) { builder }
    }

    "Day 8" - {
        val builder = Day8SolutionBuilder("day8")

        TestConstants.SAMPLE_CHECK_TEST_NAME - {
            "From sample file" {
                builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 12
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

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 605
        }

        createTargetShowingTest { builder }
    }

    "Day 10" - {
        TestConstants.SAMPLE_CHECK_TEST_NAME - {
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
        TestConstants.SAMPLE_CHECK_TEST_NAME - {
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
}) {
    companion object {
        inline fun <I : Any> showTargetAnswer(
            resultExtractor: (I) -> Any = { it },
            solutionBuilderSupplier: () -> SolutionBuilder<I>,
        ) = shouldNotThrow<Throwable> { println(resultExtractor(solutionBuilderSupplier().buildAndSolve(TestConstants.INPUT_FILE_NAME))) }

        suspend inline fun <I : Any> FreeSpecContainerScope.createTargetShowingTest(
            crossinline resultExtractor: (I) -> Any = { it },
            crossinline solutionBuilderSupplier: () -> SolutionBuilder<I>,
        ) = TestConstants.TARGET_CHECK_TEST_NAME { showTargetAnswer(resultExtractor, solutionBuilderSupplier) }
    }
}