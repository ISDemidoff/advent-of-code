package isdemidoff.utility.solution.inputparser.functions.bidirectional

fun <R> Function1<String, R>.toBiDirectionalFlat() =
    BiDirectionalFunction(this, { it.toString() })