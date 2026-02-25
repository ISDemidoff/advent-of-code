@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.strings

import java.security.MessageDigest

/**
 * Just classic [MD5 digest](https://en.wikipedia.org/wiki/MD5) of a given [string].
 */
fun md5hex(string: String): String = MessageDigest.getInstance("MD5")
    .digest(string.toByteArray(Charsets.UTF_8))
    .toHexString()