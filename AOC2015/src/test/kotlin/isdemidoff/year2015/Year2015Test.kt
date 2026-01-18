package isdemidoff.year2015

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import isdemidoff.year2015.day1.day1
import isdemidoff.year2015.day10.nextApply
import isdemidoff.year2015.day11.getNextValidPassword
import isdemidoff.year2015.day12.day12
import isdemidoff.year2015.day13.day13
import isdemidoff.year2015.day14.day14
import isdemidoff.year2015.day14.entity.Reindeer
import isdemidoff.year2015.day15.day15
import isdemidoff.year2015.day17.day17
import isdemidoff.year2015.day18.day18
import isdemidoff.year2015.day19.day19
import isdemidoff.year2015.day2.day2
import isdemidoff.year2015.day21.entity.BattleSimulation
import isdemidoff.year2015.day21.entity.SimpleCharacter
import isdemidoff.year2015.day22.entity.DrainSpell
import isdemidoff.year2015.day22.entity.InProgressGameState
import isdemidoff.year2015.day22.entity.MagicMissileSpell
import isdemidoff.year2015.day22.entity.PoisonEffect
import isdemidoff.year2015.day22.entity.PoisonSpell
import isdemidoff.year2015.day22.entity.RechargeEffect
import isdemidoff.year2015.day22.entity.RechargeSpell
import isdemidoff.year2015.day22.entity.ShieldEffect
import isdemidoff.year2015.day22.entity.ShieldSpell
import isdemidoff.year2015.day22.entity.Stats
import isdemidoff.year2015.day22.entity.Victory
import isdemidoff.year2015.day23.day23
import isdemidoff.year2015.day24.day24
import isdemidoff.year2015.day25.findCodeAtPosition
import isdemidoff.year2015.day3.day3
import isdemidoff.year2015.day4.day4
import isdemidoff.year2015.day5.day5
import isdemidoff.year2015.day6.day6
import isdemidoff.year2015.day7.day7
import isdemidoff.year2015.day8.day8
import isdemidoff.year2015.day9.day9

class Year2015Test : FreeSpec({
    "Day 1: Not Quite Lisp" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Given \"$input\" as instruction, Santa ends up at $result floor"
                },
                "(())" to 0,
                "()()" to 0,
                "(((" to 3,
                "(()(()(" to 3,
                "))(((((" to 3,
                "())" to -1,
                "))(" to -1,
                ")))" to -3,
                ")())())" to -3,
            ) { (input, result) ->
                day1.input { string(input) }.solvePart1().get() shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Given \"$input\" as instruction, Santa first time enters basement at $result index"
                },
                ")" to 1,
                "()())" to 5,
            ) { (input, result) ->
                day1.input { string(input) }.solvePart2().get() shouldBe result
            }
        }
    }

    "Day 2: I Was Told There Would Be No Math" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Box with dimensions $input need $result wrapping"
                },
                "2x3x4" to 58,
                "1x1x10" to 43,
            ) { (input, result) ->
                day2.input { string(input) }.solvePart1().get() shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Box with dimensions $input need $result ribbon"
                },
                "2x3x4" to 34,
                "1x1x10" to 14,
            ) { (input, result) ->
                day2.input { string(input) }.solvePart2().get() shouldBe result
            }
        }
    }

    "Day 3: Perfectly Spherical Houses in a Vacuum" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Single courier with instruction $input visit $result houses"
                },
                ">" to 2,
                "^>v<" to 4,
                "^v^v^v^v^v" to 2,
            ) { (input, result) ->
                day3.input { raw(input) }.solvePart1().get() shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "Two couriers with instruction $input visit $result houses"
                },
                "^v" to 3,
                "^>v<" to 3,
                "^v^v^v^v^v" to 11,
            ) { (input, result) ->
                day3.input { raw(input) }.solvePart2().get() shouldBe result
            }
        }
    }

    "Day 4: The Ideal Stocking Stuffer" - {
        withData(
            nameFn = { (input, result) ->
                "For input string \"$input\" answer is $result"
            },
            "abcdef" to 609043,
            "pqrstuv" to 1048970,
        ) { (input, result) ->
            day4.input { raw(input) }.solvePart1("00000").get() shouldBe result
        }
    }

    "Day 5: Doesn't He Have Intern-Elves For This?" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "String \"$input\" is ${if (result) "nice" else "naughty"}"
                },
                "ugknbfddgicrmopn" to true,
                "aaa" to true,
                "uuuuuuu" to true,
                "jchzalrnumimnmhp" to false,
                "haegwjzuvuyypxyu" to false,
                "dvszwmarrgswjxmb" to false,
            ) { (input, result) ->
                val expectedResult = if (result) 1 else 0
                day5.input { string(input) }.solvePart1().get() shouldBe expectedResult
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (input, result) ->
                    "String \"$input\" is ${if (result) "nice" else "naughty"}"
                },
                "qjhvhtzxzqqjkmpb" to true,
                "xxyxx" to true,
                "uurcxstgmygtbstg" to false,
                "ieodomkazucvgmuy" to false,
            ) { (input, result) ->
                val expectedResult = if (result) 1 else 0
                day5.input { string(input) }.solvePart2().get() shouldBe expectedResult
            }
        }
    }

    "Day 6: Probably a Fire Hazard" - {
        val builder = day6.input { sampleFile() }
        "Part 1 check" { builder.solvePart1().get() shouldBe 998000 }
        "Part 2 check" { builder.solvePart2().get() shouldBe 1000998 }
    }

    "Day 7: Some Assembly Required" - {
        "Part 1 checks" - {
            val logicalCircuit = day7.input { sampleFile() }.solvePart1().get()

            withData(
                nameFn = { (key, value) ->
                    "Wire '$key' has value $value"
                },
                "d" to 72.toUShort(),
                "e" to 507.toUShort(),
                "f" to 492.toUShort(),
                "g" to 114.toUShort(),
                "h" to 65412.toUShort(),
                "i" to 65079.toUShort(),
                "x" to 123.toUShort(),
                "y" to 456.toUShort(),
            ) { (key, value) ->
                logicalCircuit.getValue(key) shouldBe value
            }
        }
    }

    "Day 8: Matchsticks" - {
        val builder = day8.input { sampleFile() }

        "Part 1 checks" - {
            "Sample file" { builder.solvePart1().get() shouldBe 12 }

            withData(
                nameFn = { (str, result) ->
                    "$str has result $result"
                },
                """""""" to 2,
                """"abc"""" to 2,
                """"aaa\"aaa"""" to 3,
                """"\x27"""" to 5,
                """"\\\xa6"""" to 6,
                """"p\"zqyw"""" to 3,
                """"\\\\"""" to 4,
            ) { (str, result) ->
                day8.input { string(str) }.solvePart1().get() shouldBe result
            }
        }

        "Part 2 checks" - {
            "Sample file" { builder.solvePart2().get() shouldBe 19 }

            withData(
                nameFn = { (str, result) ->
                    "$str has result $result"
                },
                """""""" to 4,
                """"abc"""" to 4,
                """"aaa\"aaa"""" to 6,
                """"\x27"""" to 5,
                """"\\\xa6"""" to 7,
                """"p\"zqyw"""" to 6,
                """"\\\\"""" to 8,
            ) { (str, result) ->
                day8.input { string(str) }.solvePart2().get() shouldBe result
            }
        }
    }

    "Day 9: All in a Single Night" - {
        val builder = day9.input { sampleFile() }
        "Part 1 check" { builder.solvePart1().get() shouldBe 605 }
        "Part 2 check" { builder.solvePart2().get() shouldBe 982 }
    }

    "Day 10: Elves Look, Elves Say" - {
        withData(
            nameFn = { (str, result) ->
                "$str on next iteration becomes $result"
            },
            "1" to "11",
            "11" to "21",
            "21" to "1211",
            "1211" to "111221",
            "111221" to "312211",
        ) { (str, result) ->
            str.nextApply() shouldBe result
        }
    }

    "Day 11: Corporate Policy" - {
        withData(
            nameFn = { (str, result) ->
                "Next password after $str is $result"
            },
            "abcdefgh" to "abcdffaa",
            "ghijklmn" to "ghjaabcc",
        ) { (str, result) ->
            str.getNextValidPassword() shouldBe result
        }
    }

    "Day 12: JSAbacusFramework.io" - {
        "Part 1 checks" - {
            withData(
                nameFn = { (str, result) ->
                    "$str has total sum $result"
                },
                "[1,2,3]" to 6,
                """{"a":2,"b":4}""" to 6,
                """[1,{"c":"red","b":2},3]""" to 6,
                "[[[3]]]" to 3,
                """{"a":{"b":4},"c":-1}""" to 3,
                """{"a":[-1,1]}""" to 0,
                """[-1,{"a":1}]""" to 0,
                "[]" to 0,
                "{}" to 0,
            ) { (str, result) ->
                day12.input { string(str) }.solvePart1().get() shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (str, result) ->
                    "$str has total sum $result when counting except red"
                },
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
            ) { (str, result) ->
                day12.input { string(str) }.solvePart2().get() shouldBe result
            }
        }
    }

    "Day 13: Knights of the Dinner Table" - {
        "Part 1 check" { day13.input { sampleFile() }.solvePart1().get() shouldBe 330 }
    }

    "Day 14: Reindeer Olympics" - {
        val builder = day14.input { sampleFile() }
        val sampleRaceTime = 1000
        fun Map<Reindeer, Int>.findReindeerResults(name: String) = this.filterKeys { it.name == name }.values.single()

        "Part 1 checks" - {
            withData(
                nameFn = { (str, result) ->
                    "$str has result of $result km after $sampleRaceTime seconds"
                },
                "Comet" to 1120,
                "Dancer" to 1056,
            ) { (str, result) ->
                builder.solvePart1(sampleRaceTime).get().findReindeerResults(str) shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (str, result) ->
                    "$str has result of $result points after $sampleRaceTime seconds"
                },
                "Comet" to 312,
                "Dancer" to 689,
            ) { (str, result) ->
                builder.solvePart2(sampleRaceTime).get().findReindeerResults(str) shouldBe result
            }
        }
    }

    "Day 15: Science for Hungry People" - {
        val builder = day15.input { sampleFile() }
        "Part 1 check" { builder.solvePart1().get() shouldBe 62842880 }
        "Part 2 check" { builder.solvePart2().get() shouldBe 57600000 }
    }

    "Day 16: Aunt Sue" - {
        // There are no any examples provided in problem.
        1 shouldBe 1
    }

    "Day 17: No Such Thing as Too Much" - {
        val builder = day17.input { sampleFile() }

        builder.solvePart1(25).get().let {
            it shouldHaveSize 4

            val minSize = it.minOf { it.size } shouldBe 2
            it.filter { it.size == minSize } shouldHaveSize 3
        }
    }

    "Day 18: Like a GIF For Your Yard" - {
        val builder = day18.input { sampleFile() }

        "Part 1 checks" - {
            withData(
                nameFn = { (iterations, result) ->
                    "Sample grid after $iterations iterations has $result turned on lights."
                },
                1 to 11,
                2 to 8,
                3 to 4,
                4 to 4,
                5 to 4,
            ) { (iterations, result) ->
                builder.solvePart1(iterations).get() shouldBe result
            }
        }

        "Part 2 checks" - {
            withData(
                nameFn = { (iterations, result) ->
                    "Sample grid after $iterations iterations has $result turned on lights."
                },
                1 to 18,
                2 to 18,
                3 to 18,
                4 to 14,
                5 to 17,
            ) { (iterations, result) ->
                builder.solvePart2(iterations).get() shouldBe result
            }
        }
    }

    "Day 19: Medicine for Rudolph" - {
        val builder = day19.input { sampleFile() }

        "Part 1 check" {
            builder.solvePart1().get() shouldContainExactly setOf("HOOH", "HOHO", "OHOH", "HHHH")
        }
    }

    "Day 20: Infinite Elves and Infinite Houses" - {
        // There are no any examples provided in problem.
        1 shouldBe 1
    }

    "Day 21: RPG Simulator 20XX" - {
        "Check of battle simulator from sample" {
            BattleSimulation(
                SimpleCharacter("player", 8, 5, 5),
                SimpleCharacter("boss", 12, 7, 2),
                true,
            ).decideWinner() shouldBe BattleSimulation.Winner.FIRST
        }
    }

    "Day 22: Wizard Simulator 20XX" - {
        "Check of battle simulator from sample" {
            var state = InProgressGameState(
                stats = Stats(
                    playerHealth = 10,
                    currentMana = 250,
                    bossHealth = 13,
                    bossDamage = 8,
                    currentEffects = listOf(),
                ),
                totalManaSpentSoFar = 0,
                enableLogging = true,
                hardMode = false,
            ).useSpell(PoisonSpell)

            state should beInstanceOf(InProgressGameState::class)

            (state as InProgressGameState).stats.apply {
                playerHealth shouldBe 2
                currentMana shouldBe 77
                bossHealth shouldBe 7
                currentEffects shouldContainExactly listOf(PoisonEffect(4, 3))
            }

            state.useSpell(MagicMissileSpell) shouldBe Victory(226)
        }

        "Check of battle simulator from another sample" {
            // Player casts Recharge.
            var state = InProgressGameState(
                stats = Stats(
                    playerHealth = 10,
                    currentMana = 250,
                    bossHealth = 14,
                    bossDamage = 8,
                    currentEffects = listOf(),
                ),
                totalManaSpentSoFar = 0,
                enableLogging = true,
                hardMode = false,
            ).useSpell(RechargeSpell)
            state should beInstanceOf(InProgressGameState::class)
            (state as InProgressGameState).stats.apply {
                playerHealth shouldBe 2
                currentMana shouldBe 223
                bossHealth shouldBe 14
                currentEffects shouldContainExactly listOf(RechargeEffect(3, 101))
            }

            // Player casts Shield, increasing armor by 7.
            state = state.useSpell(ShieldSpell)
            state should beInstanceOf(InProgressGameState::class)
            (state as InProgressGameState).stats.apply {
                playerHealth shouldBe 1
                currentMana shouldBe 312
                bossHealth shouldBe 14
                currentEffects shouldContainExactlyInAnyOrder listOf(RechargeEffect(1, 101), ShieldEffect(4, 7))
            }

            // Player casts Drain, dealing 2 damage, and healing 2 hit points.
            state = state.useSpell(DrainSpell)
            state should beInstanceOf(InProgressGameState::class)
            (state as InProgressGameState).stats.apply {
                playerHealth shouldBe 2
                currentMana shouldBe 340
                bossHealth shouldBe 12
                currentEffects shouldContainExactlyInAnyOrder listOf(ShieldEffect(2, 7))
            }

            // Player casts Poison.
            state = state.useSpell(PoisonSpell)
            state should beInstanceOf(InProgressGameState::class)
            (state as InProgressGameState).stats.apply {
                playerHealth shouldBe 1
                currentMana shouldBe 167
                bossHealth shouldBe 6
                currentEffects shouldContainExactlyInAnyOrder listOf(PoisonEffect(4, 3))
            }

            // Player casts Magic Missile, dealing 4 damage.
            state = state.useSpell(MagicMissileSpell)
            state shouldBe Victory(641)
        }
    }

    "Day 23: Opening the Turing Lock" - {
        "Sample check" { day23.input { sampleFile() }.solvePart1("a").get() shouldBe 2u }
    }

    "Day 24: It Hangs in the Balance" - {
        "Part 1 check" { day24.input { sampleFile() }.solvePart1().get() shouldBe 99 }
        "Part 2 check" { day24.input { sampleFile() }.solvePart2().get() shouldBe 44 }
    }

    "Day 25: Let It Snow" - {
        data class TestData(
            val row: Int,
            val col: Int,
            val expectedCode: Long,
        )


        "Part 1 checks" - {
            withData(
                nameFn = { (row, col, expectedCode) ->
                    "Code at row $row, col $col is $expectedCode"
                },
                TestData(1, 1, 20151125L),
                TestData(1, 2, 18749137L),
                TestData(1, 3, 17289845L),
                TestData(1, 4, 30943339L),
                TestData(1, 5, 10071777L),
                TestData(1, 6, 33511524L),
                TestData(2, 1, 31916031L),
                TestData(2, 2, 21629792L),
                TestData(2, 3, 16929656L),
                TestData(2, 4, 7726640L),
                TestData(2, 5, 15514188L),
                TestData(2, 6, 4041754L),
                TestData(3, 1, 16080970L),
                TestData(3, 2, 8057251),
                TestData(3, 3, 1601130),
                TestData(3, 4, 7981243),
                TestData(3, 5, 11661866),
                TestData(3, 6, 16474243),
                TestData(4, 1, 24592653),
                TestData(4, 2, 32451966),
                TestData(4, 3, 21345942),
                TestData(4, 4, 9380097),
                TestData(4, 5, 10600672),
                TestData(4, 6, 31527494),
                TestData(5, 1, 77061),
                TestData(5, 2, 17552253),
                TestData(5, 3, 28094349),
                TestData(5, 4, 6899651),
                TestData(5, 5, 9250759),
                TestData(5, 6, 31663883),
                TestData(6, 1, 33071741),
                TestData(6, 2, 6796745),
                TestData(6, 3, 25397450),
                TestData(6, 4, 24659492),
                TestData(6, 5, 1534922),
                TestData(6, 6, 27995004),
            ) { (row, col, expectedCode) ->
                findCodeAtPosition(row, col) shouldBe expectedCode
            }
        }
    }
})