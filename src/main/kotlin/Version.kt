package io.github.semver

import io.github.semver.identifier.PreRelease

/**
 * A semantic version number.
 *
 * Semantic versioning ("semver" in short) is a system of rules and requirements
 * that dictate how version numbers are assigned and incremented. They take the
 * form X.Y.Z (Major.Minor.Patch), where X, Y, and Z are non-negative integers.
 *
 * The specification is detailed at: [https://semver.org]
 *
 * @property major the major number
 * @property minor the minor number
 * @property patch the patch number
 * @author Matteo Mirk
 */
class Version(
    val major: Int = 0,
    val minor: Int = 0,
    val patch: Int = 0,
    private val pre: PreRelease? = null
) : Comparable<Version> {
    init {
        requireNonNegative(major, "major")
        requireNonNegative(minor, "minor")
        requireNonNegative(patch, "patch")
    }

    val isPreRelease = pre != null
    val isStable = major > 0 && !isPreRelease

    fun bugfix() = Version(major, minor, patch + 1)
    fun compatibleChange() = Version(major, minor + 1, 0)
    fun breakingChange() = Version(major + 1, 0, 0)

    override fun toString() = "$major.$minor.$patch" + if (isPreRelease) "-$pre" else ""

    override fun compareTo(other: Version): Int {
        val comparison = compareValuesBy(
            this, other,
            { it.major }, { it.minor }, { it.patch }
        )
        if (comparison != 0) return comparison

        return pre?.let { p1 ->
            other.pre?.let { p2 -> p1.compareTo(p2) } ?: -1
        } ?: if (other.isPreRelease) 1 else 0
    }
}

private fun requireNonNegative(number: Int, name: String) {
    require(number >= 0) { "$name number can't be negative: $number" }
}
