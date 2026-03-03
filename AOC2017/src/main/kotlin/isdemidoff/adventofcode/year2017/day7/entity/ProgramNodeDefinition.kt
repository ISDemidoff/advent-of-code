package isdemidoff.adventofcode.year2017.day7.entity

import isdemidoff.utility.parsing.keyValueBy
import isdemidoff.utility.parsing.toIntOrError

data class ProgramNodeDefinition(
    val name: String,
    val value: Int,
    val childrenNames: List<String> = emptyList(),
)

private val nodeDefRegex = """([a-z]+) \((\d+)\)""".toRegex()

private fun extractNodeNameAndValue(input: String): Pair<String, Int> =
    nodeDefRegex.matchEntire(input)
        .let { requireNotNull(it?.destructured) { "Node definition must match regex" } }
        .let { (name, value) -> name to value.toIntOrError() }

internal val programNodeDefinitionParser: (String) -> ProgramNodeDefinition = { input ->
    if (input.contains("->")) {
        input.keyValueBy(" -> ", ::extractNodeNameAndValue) { it.split(", ") }
            .let { ProgramNodeDefinition(it.first.first, it.first.second, it.second) }
    } else {
        extractNodeNameAndValue(input).let { ProgramNodeDefinition(it.first, it.second) }
    }
}
