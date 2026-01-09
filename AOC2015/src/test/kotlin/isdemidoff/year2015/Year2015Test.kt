package isdemidoff.year2015

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import isdemidoff.SolutionBuilder
import isdemidoff.utility.test.TestConstants.INPUT_FILE_NAME
import isdemidoff.utility.test.TestConstants.PART_ONE
import isdemidoff.utility.test.TestConstants.PART_TWO
import isdemidoff.utility.test.TestConstants.SAMPLE_CHECK_TEST_NAME
import isdemidoff.utility.test.TestConstants.SAMPLE_FILE_NAME
import isdemidoff.utility.test.TestConstants.TARGET_CHECK_TEST_NAME
import isdemidoff.year2015.day1.Day1Solution
import isdemidoff.year2015.day1.Day1SolutionBuilder
import isdemidoff.year2015.day10.Day10SolutionBuilder
import isdemidoff.year2015.day10.nextApply
import isdemidoff.year2015.day11.Day11Solution
import isdemidoff.year2015.day11.Day11SolutionBuilder
import isdemidoff.year2015.day12.CountingRules
import isdemidoff.year2015.day12.Day12Solution
import isdemidoff.year2015.day12.Day12SolutionBuilder
import isdemidoff.year2015.day13.Day13SolutionBuilder
import isdemidoff.year2015.day14.Day14SolutionBuilder
import isdemidoff.year2015.day14.entity.RaceConditions
import isdemidoff.year2015.day14.entity.Reindeer
import isdemidoff.year2015.day15.Day15SolutionBuilder
import isdemidoff.year2015.day15.entity.calculateCalories
import isdemidoff.year2015.day16.Day16SolutionBuilder
import isdemidoff.year2015.day16.entity.ComparingRules
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

        PART_ONE - {
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
        }

        PART_TWO - {
            SAMPLE_CHECK_TEST_NAME - {
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

            createTargetShowingTest { builder.forNumberOfCouriers(2) }
        }
    }

    "Day 4" - {
        val builder = Day4SolutionBuilder("day4")

        PART_ONE - {
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
        }

        PART_TWO - {
            createTargetShowingTest { builder.withStartingPattern("0".repeat(6)) }
        }
    }

    "Day 5" - {
        val builder = Day5SolutionBuilder("day5")

        PART_ONE - {
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
        }

        PART_TWO - {
            SAMPLE_CHECK_TEST_NAME - {
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

            createTargetShowingTest { builder.forRulesSet(RulesSet.PART_TWO) }
        }
    }

    "Day 6" - {
        val builder = Day6SolutionBuilder("day6")

        PART_ONE - {
            SAMPLE_CHECK_TEST_NAME {
                builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 998000
            }

            createTargetShowingTest { builder }
        }

        PART_TWO - {
            createTargetShowingTest { builder.withLightGenerator { BrightnessLight() } }
        }
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
                builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe (12 to 19)
            }

            "Just texts" - {
                listOf(
                    """""""" to (2 to 4),
                    """"abc"""" to (2 to 4),
                    """"aaa\"aaa"""" to (3 to 6),
                    """"\x27"""" to (5 to 5),
                    """"\\\xa6"""" to (6 to 7),
                    """"p\"zqyw"""" to (3 to 6),
                    """"\\\\"""" to (4 to 8),
                ).forEach { (input, result) ->
                    "$input has $result as a result" {
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
            builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe (605 to 982)
        }

        createTargetShowingTest { builder }
    }

    "Day 10" - {
        val builder = Day10SolutionBuilder("day10")

        PART_ONE - {
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

            createTargetShowingTest { builder }
        }

        PART_TWO - {
            createTargetShowingTest { builder.withRepetitions(50) }
        }
    }

    "Day 11" - {
        val builder = Day11SolutionBuilder("day11")

        PART_ONE - {
            SAMPLE_CHECK_TEST_NAME - {
                listOf(
                    "abcdefgh" to "abcdffaa",
                    "ghijklmn" to "ghjaabcc",
                ).forEach { (input, result) ->
                    "Next password after \"$input\" is \"$result\"" {
                        Day11Solution(input).solve() shouldBe result
                    }
                }
            }

            createTargetShowingTest { builder }
        }

        PART_TWO - {
            createTargetShowingTest { builder.searchingPosition(2) }
        }
    }

    "Day 12" - {
        val builder = Day12SolutionBuilder("day12")

        PART_ONE - {
            SAMPLE_CHECK_TEST_NAME - {
                listOf(
                    "[1,2,3]" to 6,
                    """{"a":2,"b":4}""" to 6,
                    """[1,{"c":"red","b":2},3]""" to 6,
                    "[[[3]]]" to 3,
                    """{"a":{"b":4},"c":-1}""" to 3,
                    """{"a":[-1,1]}""" to 0,
                    """[-1,{"a":1}]""" to 0,
                    "[]" to 0,
                    "{}" to 0,
                ).forEach { (input, result) ->
                    "\"$input\" has sum of $result" {
                        Day12Solution(input, CountingRules.COUNT_ALL).solve() shouldBe result
                    }
                }
            }

            createTargetShowingTest { builder }
        }

        PART_TWO - {
            "$SAMPLE_CHECK_TEST_NAME $PART_TWO" - {
                listOf(
                    "[1,2,3]" to 6,
                    """[1,{"c":"red","b":2},3]""" to 4,
                    "[[[3]]]" to 3,
                    """{"a":{"b":4},"c":-1}""" to 3,
                    """{"d":"red","e":[1,2,3,4],"f":5}""" to 0,
                    """{"a":[-1,1]}""" to 0,
                    """[-1,{"a":1}]""" to 0,
                    """[1,"red",5]""" to 6,
                    "[]" to 0,
                    "{}" to 0,
                ).forEach { (input, result) ->
                    "\"$input\" has sum of $result when counting all except red objects" {
                        Day12Solution(input, CountingRules.EXCEPT_RED).solve() shouldBe result
                    }
                }
            }

            createTargetShowingTest { builder.withCountingRules(CountingRules.EXCEPT_RED) }
        }
    }

    "Day 13" - {
        val builder = Day13SolutionBuilder("day13")

        PART_ONE - {
            SAMPLE_CHECK_TEST_NAME {
                builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 330
            }

            createTargetShowingTest { builder }
        }

        PART_TWO - {
            createTargetShowingTest { builder.shouldAddIgnorantMan(true) }
        }
    }

    "Day 14" - {
        var builder = Day14SolutionBuilder("day14")
        val sampleRaceDuration = 1000
        val targetRaceDuration = 2503

        fun Map<Reindeer, Int>.findReindeerResults(name: String) = this.filterKeys { it.name == name }.values.single()
        fun Map<Reindeer, Int>.findBestScore() = this.maxOf { it.value }

        PART_ONE - {
            SAMPLE_CHECK_TEST_NAME - {
                val raceResults = builder.forSeconds(sampleRaceDuration).buildAndSolve(SAMPLE_FILE_NAME)

                listOf(
                    "Comet" to 1120,
                    "Dancer" to 1056,
                ).forEach { (reindeer, result) ->
                    "$reindeer has result of $result km after $sampleRaceDuration seconds" {
                        raceResults.findReindeerResults(reindeer) shouldBe result
                    }
                }
            }

            createTargetShowingTest(resultExtractor = { it.findBestScore() }) { builder.forSeconds(targetRaceDuration) }
        }

        builder = builder.withRaceConditions(RaceConditions.TOTAL_LEAD_TIME)

        PART_TWO - {
            SAMPLE_CHECK_TEST_NAME - {
                val raceResults = builder.forSeconds(sampleRaceDuration).buildAndSolve(SAMPLE_FILE_NAME)

                listOf(
                    "Comet" to 312,
                    "Dancer" to 689,
                ).forEach { (reindeer, result) ->
                    "$reindeer has result of $result points after $sampleRaceDuration seconds" {
                        raceResults.findReindeerResults(reindeer) shouldBe result
                    }
                }
            }

            createTargetShowingTest(resultExtractor = { it.findBestScore() }) { builder.forSeconds(targetRaceDuration) }
        }
    }

    "Day 15" - {
        var builder = Day15SolutionBuilder("day15")

        PART_ONE - {
            SAMPLE_CHECK_TEST_NAME {
                builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 62842880
            }

            createTargetShowingTest { builder }
        }

        builder = builder.withCombinationsFilter { it.calculateCalories() == 500L }

        PART_TWO - {
            SAMPLE_CHECK_TEST_NAME {
                builder.buildAndSolve(SAMPLE_FILE_NAME) shouldBe 57600000
            }

            createTargetShowingTest { builder }
        }
    }

    "Day 16" - {
        val builder = Day16SolutionBuilder("day16")

        PART_ONE - {
            createTargetShowingTest { builder }
        }

        PART_TWO - {
            createTargetShowingTest { builder.withComparingRules(ComparingRules.COMPLICATED) }
        }
    }
}) {
    companion object {
        inline fun <I : Any> showTargetAnswer(
            resultExtractor: (I) -> Any = { it },
            solutionBuilderSupplier: () -> SolutionBuilder<I>,
        ) = shouldNotThrow<Throwable> { println(resultExtractor(solutionBuilderSupplier().buildAndSolve(INPUT_FILE_NAME))) }

        suspend inline fun <I : Any> FreeSpecContainerScope.createTargetShowingTest(
            crossinline resultExtractor: (I) -> Any = { it },
            crossinline solutionBuilderSupplier: () -> SolutionBuilder<I>,
        ) = TARGET_CHECK_TEST_NAME { showTargetAnswer(resultExtractor, solutionBuilderSupplier) }
    }
}