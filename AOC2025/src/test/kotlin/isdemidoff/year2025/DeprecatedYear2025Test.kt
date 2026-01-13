package isdemidoff.year2025

import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import isdemidoff.DeprecatedSolutionBuilder
import isdemidoff.utility.test.TestConstants
import isdemidoff.utility.test.TestConstants.INPUT_FILE_NAME
import isdemidoff.year2025.day10.Day10SolutionBuilder
import isdemidoff.year2025.day11.Day11SolutionBuilder
import isdemidoff.year2025.day12.Day12SolutionBuilder
import isdemidoff.year2025.day3.Day3SolutionBuilder
import isdemidoff.year2025.day4.Day4SolutionBuilder
import isdemidoff.year2025.day5.Day5SolutionBuilder
import isdemidoff.year2025.day6.Day6SolutionBuilder
import isdemidoff.year2025.day7.Day7SolutionBuilder
import isdemidoff.year2025.day8.Day8SolutionBuilder
import isdemidoff.year2025.day9.Day9SolutionBuilder

class DeprecatedYear2025Test : FreeSpec({
    "Day 3" - {
        val builder = Day3SolutionBuilder("day3")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 357
        }

        createTargetShowingTest { builder }
    }

    "Day 4" - {
        val builder = Day4SolutionBuilder("day4")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 13
        }

        createTargetShowingTest { builder }
    }

    "Day 5" - {
        val builder = Day5SolutionBuilder("day5")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 3
        }

        createTargetShowingTest { builder }
    }

    "Day 6" - {
        val builder = Day6SolutionBuilder("day6")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 4277556
        }

        createTargetShowingTest { builder }
    }

    "Day 7" - {
        val builder = Day7SolutionBuilder("day7")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 21
        }

        createTargetShowingTest { builder }
    }

    "Day 8" - {
        val builder = Day8SolutionBuilder("day8")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.forNumConnections(10).buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 40
        }

        createTargetShowingTest { builder.forNumConnections(1000) }
    }

    "Day 9" - {
        val builder = Day9SolutionBuilder("day9")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 50
        }

        createTargetShowingTest { builder }
    }

    "Day 10" - {
        val builder = Day10SolutionBuilder("day10")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 7
        }

        createTargetShowingTest { builder }
    }

    "Day 11" - {
        val builder = Day11SolutionBuilder("day11")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 5
        }

        createTargetShowingTest { builder }
    }

    "Day 12" - {
        val builder = Day12SolutionBuilder("day12")

        TestConstants.SAMPLE_CHECK_TEST_NAME.config(enabled = false) {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 2
        }

        createTargetShowingTest { builder }
    }
}) {
    companion object {
        suspend inline fun <I : Any> FreeSpecContainerScope.createTargetShowingTest(
            crossinline deprecatedSolutionBuilderSupplier: () -> DeprecatedSolutionBuilder<I>,
        ) = TestConstants.TARGET_CHECK_TEST_NAME { deprecatedSolutionBuilderSupplier().revealResult(INPUT_FILE_NAME) }
    }
}