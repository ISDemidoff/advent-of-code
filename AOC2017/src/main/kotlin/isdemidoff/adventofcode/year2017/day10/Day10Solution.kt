package isdemidoff.adventofcode.year2017.day10

import isdemidoff.solution.complexSolution
import isdemidoff.solution.inputparser.scope.singleLine
import isdemidoff.solution.solver.solver
import isdemidoff.utility.parsing.toIntOrError

/**
 * [Day 10: Knot Hash](https://adventofcode.com/2017/day/10).
 */
@OptIn(ExperimentalUnsignedTypes::class)
val day10 = complexSolution(10) {
    inputParser = singleLine

    part1Solver = solver<String, Int, Int>({ result, _ ->
        "Product of first two elements is $result."
    }) { input, size ->
        val mutations = input.split(",").map { it.toIntOrError() }
        var list = (0..<size).toMutableList()

        var lastIndex = 0
        mutations.forEachIndexed { index, rotation ->
            val newList = list.toMutableList()
            // rotate from lastIndex to lastIndex + rotation - 1
            (0..<rotation).forEach { offset ->
                newList[(lastIndex + offset) % newList.size] = list[(lastIndex + rotation - offset - 1) % list.size]
            }
            lastIndex += rotation + index
            list = newList
        }

        list[0] * list[1]
    }

    part2Solver = solver({
        "Resulting dense hash is $it."
    }) { input ->
        val mutations = input.toByteArray(Charsets.US_ASCII).map { it.toUByte().toInt() } + listOf(17, 31, 73, 47, 23)
        var list = (0..<256).toMutableList().map { it.toUByte() }

        var lastIndex = 0
        var skip = 0

        // Reimplemented rotations, list contains a spare hash
        (0..<64).forEach { _ ->
            mutations.forEach { rotation ->
                val newList = list.toMutableList()

                (0..<rotation).forEach { offset ->
                    newList[(lastIndex + offset) % newList.size] = list[(lastIndex + rotation - offset - 1) % list.size]
                }


                lastIndex += rotation + skip++
                list = newList
            }
        }

        val denseHash: MutableList<UByte> = ArrayList<UByte>(16).apply { repeat(16) { add(0.toUByte()) } }
        list.forEachIndexed { index, b ->
            denseHash[index / 16] = denseHash[index / 16] xor b
        }

        denseHash.toUByteArray().toHexString()
    }
}
