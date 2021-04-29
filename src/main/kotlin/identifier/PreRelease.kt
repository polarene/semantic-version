package io.github.semver.identifier

class PreRelease(val identifier: String, vararg val identifiers: String) {
    init {
        require(identifier.isNotBlank())
    }

    override fun toString(): String = identifier
}
