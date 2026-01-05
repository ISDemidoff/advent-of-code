package isdemidoff.year2025

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import isdemidoff.utility.test.TestConstants
import isdemidoff.year2025.day1.Day1SolutionBuilder
import isdemidoff.year2025.day10.Day10SolutionBuilder
import isdemidoff.year2025.day11.Day11SolutionBuilder
import isdemidoff.year2025.day12.Day12SolutionBuilder
import isdemidoff.year2025.day2.Day2SolutionBuilder
import isdemidoff.year2025.day3.Day3SolutionBuilder
import isdemidoff.year2025.day4.Day4SolutionBuilder
import isdemidoff.year2025.day5.Day5SolutionBuilder
import isdemidoff.year2025.day6.Day6SolutionBuilder
import isdemidoff.year2025.day7.Day7SolutionBuilder
import isdemidoff.year2025.day8.Day8SolutionBuilder
import isdemidoff.year2025.day9.Day9SolutionBuilder

class Year2025Test: FreeSpec({
    "Day 1" - {
        val builder = Day1SolutionBuilder("day1")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 3
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 2" - {
        val builder = Day2SolutionBuilder("day2")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 1227775554
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 3" - {
        val builder = Day3SolutionBuilder("day3")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 357
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 4" - {
        val builder = Day4SolutionBuilder("day4")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 13
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 5" - {
        val builder = Day5SolutionBuilder("day5")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 3
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 6" - {
        val builder = Day6SolutionBuilder("day6")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 4277556
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 7" - {
        val builder = Day7SolutionBuilder("day7")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 21
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 8" - {
        val builder = Day8SolutionBuilder("day8")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.forNumConnections(10).buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 40
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.forNumConnections(1000).buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 9" - {
        val builder = Day9SolutionBuilder("day9")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 50
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 10" - {
        val builder = Day10SolutionBuilder("day10")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 7
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 11" - {
        val builder = Day11SolutionBuilder("day11")

        TestConstants.SAMPLE_CHECK_TEST_NAME {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 5
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }

    "Day 12" - {
        val builder = Day12SolutionBuilder("day12")

        TestConstants.SAMPLE_CHECK_TEST_NAME.config(enabled = false) {
            builder.buildAndSolve(TestConstants.SAMPLE_FILE_NAME) shouldBe 2
        }

        TestConstants.TARGET_CHECK_TEST_NAME {
            shouldNotThrow<Throwable> { println(builder.buildAndSolve(TestConstants.INPUT_FILE_NAME)) }
        }
    }
})