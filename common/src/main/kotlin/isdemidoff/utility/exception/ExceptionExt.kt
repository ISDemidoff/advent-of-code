@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.exception

fun validationError(lazyMessage: () -> Any): Nothing = throw IllegalArgumentException(lazyMessage().toString())