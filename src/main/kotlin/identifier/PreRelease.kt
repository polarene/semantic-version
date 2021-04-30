package io.github.semver.identifier

private val charset = "[0-9A-Za-z-]+".toRegex()

class PreRelease(vararg val identifiers: String) {
    init {
        require(identifiers.isNotEmpty())
        require(identifiers.all(charset::matches))
    }

    override fun toString(): String = identifiers.joinToString(".")
}
