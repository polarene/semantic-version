package io.github.semver.identifier

class PreRelease(vararg val identifiers: String) {
    init {
        require(identifiers.isNotEmpty())
        require(identifiers.all { it.isNotBlank() })
    }

    override fun toString(): String = identifiers.joinToString(".")
}
