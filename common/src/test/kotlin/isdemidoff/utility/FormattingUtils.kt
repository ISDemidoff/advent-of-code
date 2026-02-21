package isdemidoff.utility

internal fun Iterable<*>.formatShort(limit: Int = 3) =
    joinToString(prefix = "[", postfix = "]", separator = ", ", limit = limit, truncated = "<truncated>")

internal fun Map<*, *>.formatShort() =
    this.entries.formatShort()