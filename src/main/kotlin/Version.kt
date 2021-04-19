package io.github.semver

/**
 * A semantic version number.
 *
 * Semantic versioning ("semver" in short) is a system of rules and requirements
 * that dictate how version numbers are assigned and incremented.
 * They take the form X.Y.Z (Major.Minor.Patch), where X, Y, and Z are non-negative integers.
 *
 * The specification is detailed at: [https://semver.org]
 * @author mmirk
 * @param major the major number
 * @param minor the minor number
 * @param patch the patch number
 */
class Version(major: Int, minor: Int, patch: Int) {
    init {
        requireNonNegative(major, "major")
        requireNonNegative(minor, "minor")
        requireNonNegative(patch, "patch")
    }
}

private fun requireNonNegative(number: Int, name: String) {
    require(number >= 0) { "$name number can't be negative: $number" }
}
