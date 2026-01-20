package isdemidoff.utility.strings

import java.security.MessageDigest

fun md5hex(string: String): String = MessageDigest.getInstance("MD5")
    .digest(string.toByteArray(Charsets.UTF_8))
    .toHexString()