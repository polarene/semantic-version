package io.github.semver.identifier

private val charset = "[0-9A-Za-z-]+".toRegex()
private val numeric = "[0-9]+".toRegex()

class PreRelease(vararg val identifiers: String) {
    init {
        require(identifiers.isNotEmpty())
        require(identifiers.all(charset::matches))
        require(numericWithoutLeadingZero())
    }

    private fun numericWithoutLeadingZero() =
        identifiers.asSequence()
            .filter(numeric::matches)
            .all { !it.startsWith('0') }

    override fun toString(): String = identifiers.joinToString(".")
}
