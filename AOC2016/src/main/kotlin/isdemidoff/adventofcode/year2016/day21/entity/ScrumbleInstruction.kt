package isdemidoff.adventofcode.year2016.day21.entity

import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields
import isdemidoff.utility.other.sorted
import isdemidoff.utility.parsing.toIntOrError

sealed interface ScrumbleInstruction {
    fun applyToString(input: String): String
    fun reverseInstruction(): ScrumbleInstruction
}

abstract class SwapScrumbleInstruction : ScrumbleInstruction {
    abstract fun getIndices(input: String): Pair<Int, Int>
    override fun reverseInstruction() = this
    override fun applyToString(input: String) =
        getIndices(input)
            .sorted()
            .let { (lIndex, rIndex) ->
                input.substring(0..<lIndex) +
                        input[rIndex] +
                        input.substring(lIndex + 1..<rIndex) +
                        input[lIndex] +
                        input.substring(rIndex + 1..<input.length)
            }
}

class SwapByPositionsScrumbleInstruction(
    private val lIndex: Int,
    private val rIndex: Int,
) : SwapScrumbleInstruction() {
    override fun getIndices(input: String)= lIndex to rIndex
}

class SwapByLettersScrumbleInstruction(
    private val lLetter: Char,
    private val rLetter: Char,
) : SwapScrumbleInstruction() {
    override fun getIndices(input: String) = input.indexOfFirst { it == lLetter } to input.indexOfFirst { it == rLetter }
}

abstract class RotateScrumbleInstruction : ScrumbleInstruction {
    abstract fun getRotateValue(input: String): Int
    override fun applyToString(input: String) =
        getRotateValue(input)
            .let { if (it > 0) it % input.length else correctNegativeShift(it, input.length) }
            .let { input.takeLast(it) + input.dropLast(it) }

    private fun correctNegativeShift(shift: Int, size: Int): Int =
        (shift - (shift / size * size) + size) % size
}

class RotateRightScrumbleInstruction(
    private val shift: Int,
) : RotateScrumbleInstruction() {
    override fun reverseInstruction() = RotateLeftScrumbleInstruction(shift)
    override fun getRotateValue(input: String) = shift
}

class RotateLeftScrumbleInstruction(
    private val shift: Int,
) : RotateScrumbleInstruction() {
    override fun reverseInstruction() = RotateRightScrumbleInstruction(shift)
    override fun getRotateValue(input: String) = shift.unaryMinus()
}

class RotateBasedOnLetterScrumbleInstruction(
    private val letter: Char,
) : RotateScrumbleInstruction() {
    override fun reverseInstruction() = UnscrambleRotateBasedOnLetterScrumbleInstruction(this)
    override fun getRotateValue(input: String) =
        input.indexOfFirst { it == letter }
            .let { if (it >= 4) it + 2 else it + 1 }
}

class UnscrambleRotateBasedOnLetterScrumbleInstruction(
    private val instruction: RotateBasedOnLetterScrumbleInstruction
) : ScrumbleInstruction {
    override fun reverseInstruction() = instruction
    override fun applyToString(input: String) =
        (0..<input.length)
            .reversed()
            .map { RotateRightScrumbleInstruction(it) }
            .map { it.applyToString(input) }
            .first { this.instruction.applyToString(it) == input }
}

class ReverseScrumbleInstruction(
    private val lIndex: Int,
    private val rIndex: Int,
) : ScrumbleInstruction {
    override fun reverseInstruction() = this
    override fun applyToString(input: String) =
        (lIndex to rIndex).sorted()
            .let { (lIndex, rIndex) ->
                input.substring(0..<lIndex) +
                        input.substring(lIndex..rIndex).reversed() +
                        input.substring(rIndex + 1..<input.length)
            }
}

class MovePositionScrumbleInstruction(
    private val from: Int,
    private val to: Int,
) : ScrumbleInstruction {
    override fun reverseInstruction() = MovePositionScrumbleInstruction(to, from)
    override fun applyToString(input: String) =
        if (from == to) {
            input
        } else if (to > from) {
            input.substring(0..<from) +
                    input.substring(from + 1..to) +
                    input[from] +
                    input.substring(to + 1..<input.length)
        } else {
            input.substring(0..<to) +
                    input[from] +
                    input.substring(to..<from) +
                    input.substring(from + 1..<input.length)
        }
}

internal fun String.applyInstructions(instructions: List<ScrumbleInstruction>): String {
    var result = this
    instructions.forEach { result = it.applyToString(result) }
    return result
}

internal fun String.unscrambleByInstructions(instructions: List<ScrumbleInstruction>): String {
    var result = this
    instructions.reversed().forEach {
        result = it.reverseInstruction().applyToString(result)
    }
    return result
}

val scrumbleInstructionParser: (String) -> ScrumbleInstruction = regexMatch(
    """move position (\d+) to position (\d+)""".toRegex() yields { (from, to) ->
        MovePositionScrumbleInstruction(from.toIntOrError(), to.toIntOrError())
    },
    """reverse positions (\d+) through (\d+)""".toRegex() yields { (l, r) ->
        ReverseScrumbleInstruction(l.toInt(), r.toInt())
    },
    """rotate based on position of letter ([a-z])""".toRegex() yields { (ch) ->
        RotateBasedOnLetterScrumbleInstruction(ch.first())
    },
    """rotate (left|right) (\d+) steps?""".toRegex() yields { (dir, shift) ->
        if (dir == "left") {
            RotateLeftScrumbleInstruction(shift.toIntOrError())
        } else {
            RotateRightScrumbleInstruction(shift.toIntOrError())
        }
    },
    """swap letter ([a-z]) with letter ([a-z])""".toRegex() yields { (a, b) ->
        SwapByLettersScrumbleInstruction(a.first(), b.first())
    },
    """swap position (\d+) with position (\d+)""".toRegex() yields { (a, b) ->
        SwapByPositionsScrumbleInstruction(a.toIntOrError(), b.toIntOrError())
    },
)