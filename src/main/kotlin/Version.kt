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
class Version(private val major: Int = 0, private val minor: Int = 0, private val patch: Int = 0) {
    init {
        requireNonNegative(major, "major")
        requireNonNegative(minor, "minor")
        requireNonNegative(patch, "patch")
    }

    val isStable = major > 0

    override fun toString(): String {
        return "$major.$minor.$patch"
    }

    fun bugfix(): Version = Version(major, minor, patch + 1)
    fun compatibleChange(): Version = Version(major, minor + 1, 0)
    fun breakingChange(): Version = Version(major + 1, 0, 0)
}

private fun requireNonNegative(number: Int, name: String) {
    require(number >= 0) { "$name number can't be negative: $number" }
}
