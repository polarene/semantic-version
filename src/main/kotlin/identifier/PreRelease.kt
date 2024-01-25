package io.github.semver.identifier

private val charset = "[0-9A-Za-z-]+".toRegex()
private val numeric = "[0-9]+".toRegex()

class PreRelease(vararg val identifiers: String) : Comparable<PreRelease> {
    init {
        require(identifiers.isNotEmpty()) { "pre-release identifiers must not be empty" }
        require(identifiers.all(charset::matches)) { "pre-release identifiers must match $charset: $identifiers" }
        require(numericWithoutLeadingZeroes()) { "pre-release numeric identifiers must not have leading zeroes: $identifiers" }
    }

    private fun numericWithoutLeadingZeroes() =
        identifiers.asSequence()
            .filter(numeric::matches)
            .all { !it.startsWith('0') || it.length == 1 }

    override fun toString() = identifiers.joinToString(".")

    override fun compareTo(other: PreRelease): Int {
        //TODO("Not yet implemented")
        return 0
    }
}
