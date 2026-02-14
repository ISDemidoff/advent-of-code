package isdemidoff.utility.solution.inputparser

class InputParser<INNER_DATA>(
    private val fn: (List<List<String>>) -> INNER_DATA,
) : (List<List<String>>) -> INNER_DATA by fn

