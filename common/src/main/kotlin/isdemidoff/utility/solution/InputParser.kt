package isdemidoff.utility.solution

class InputParser<INNER_DATA>(
    private val fn: (List<List<String>>) -> INNER_DATA,
) : (List<List<String>>) -> INNER_DATA by fn {
    fun parse(input: List<List<String>>) = fn(input)
}