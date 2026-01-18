package isdemidoff.utility

internal fun Iterable<*>.formatShort() =
    joinToString(prefix = "[", postfix = "]", separator = ", ", limit = 3, truncated = "<truncated>")

internal fun Map<*, *>.formatShort() =
    this.entries.formatShort()