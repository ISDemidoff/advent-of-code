package isdemidoff.year2015

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import isdemidoff.utility.test.TestConstants
import isdemidoff.year2015.day1.Day1Solution
import isdemidoff.year2015.day1.Day1SolutionBuilder

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
            ).map { (input, result) ->
                "$input results to $result" {
                    Day1Solution(input).solve() shouldBe result
                }
            }
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            val builder = Day1SolutionBuilder("day1")
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }
})