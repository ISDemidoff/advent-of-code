package isdemidoff.year2015

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import isdemidoff.utility.test.TestConstants
import isdemidoff.year2015.day1.Day1Solution
import isdemidoff.year2015.day1.Day1SolutionBuilder
import isdemidoff.year2015.day2.Day2SolutionBuilder
import isdemidoff.year2015.day3.Day3Solution
import isdemidoff.year2015.day3.Day3SolutionBuilder

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

        TestConstants.TARGET_CHECK_TEST_NAME {
            val builder = Day1SolutionBuilder("day1")
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
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

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
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

        TestConstants.TARGET_CHECK_TEST_NAME {
            val builder = Day3SolutionBuilder("day3")
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }
})