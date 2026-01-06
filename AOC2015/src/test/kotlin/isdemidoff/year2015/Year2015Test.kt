package isdemidoff.year2015

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import isdemidoff.SolutionBuilder
import isdemidoff.utility.test.TestConstants
import isdemidoff.year2015.day1.Day1Solution
import isdemidoff.year2015.day1.Day1SolutionBuilder
import isdemidoff.year2015.day2.Day2SolutionBuilder
import isdemidoff.year2015.day3.Day3Solution
import isdemidoff.year2015.day3.Day3SolutionBuilder
import isdemidoff.year2015.day4.Day4Solution
import isdemidoff.year2015.day4.Day4SolutionBuilder

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
}) {
    companion object {
        inline fun showTargetAnswer(solutionBuilderSupplier: () -> SolutionBuilder<*>) =
            shouldNotThrow<Throwable> { println(solutionBuilderSupplier().buildAndSolve(TestConstants.INPUT_FILE_NAME)) }

        suspend inline fun FreeSpecContainerScope.createTargetShowingTest(crossinline solutionBuilderSupplier: () -> SolutionBuilder<*>)
            = TestConstants.TARGET_CHECK_TEST_NAME { showTargetAnswer(solutionBuilderSupplier) }
    }
}