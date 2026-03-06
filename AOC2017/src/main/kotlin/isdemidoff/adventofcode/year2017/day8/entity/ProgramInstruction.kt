package isdemidoff.adventofcode.year2017.day8.entity

import isdemidoff.adventofcode.year2017.day8.entity.ProgramInstruction.Condition
import isdemidoff.adventofcode.year2017.day8.entity.ProgramInstruction.Operation
import isdemidoff.utility.enums.ParseableEnum
import isdemidoff.utility.enums.enumRegexMatchString
import isdemidoff.utility.enums.parseFromString
import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields
import isdemidoff.utility.parsing.toIntOrError

data class ProgramInstruction(
    val register: String,
    val operation: Operation,
    val operationValue: Int,
    val conditionRegister: String,
    val condition: Condition,
    val conditionValue: Int,
) {
    @Suppress("unused")
    enum class Operation(
        override val text: String,
        val operation: (Int, Int) -> Int,
    ): ParseableEnum {
        INCREASE("inc", Int::plus),
        DECREASE("dec", Int::minus),
    }

    @Suppress("unused")
    enum class Condition(
        override val text: String,
        val predicate: (Int, Int) -> Boolean,
    ): ParseableEnum {
        GREATER(">", { a, b -> a > b }),
        LESS("<", { a, b -> a < b }),
        EQUALS("==", { a, b -> a == b }),
        NOT_EQUALS("!=", { a, b -> a != b }),
        GREATER_OR_EQUALS(">=", { a, b -> a >= b }),
        LESS_OR_EQUALS("<=", { a, b -> a <= b }),
    }
}

private val instructionRegex = """([a-z]+) (${enumRegexMatchString<Operation>()}) (-?[0-9]+) if ([a-z]+) (${enumRegexMatchString<Condition>()}) (-?[0-9]+)""".toRegex()

internal val instructionParser: (String) -> ProgramInstruction = regexMatch(
    instructionRegex yields {
        ProgramInstruction(
            register = it.component1(),
            operation = parseFromString(it.component2()),
            operationValue = it.component3().toIntOrError(),
            conditionRegister = it.component4(),
            condition = parseFromString(it.component5()),
            conditionValue = it.component6().toIntOrError(),
        )
    }
)
