package io.github.semver

import io.github.semver.identifier.PreRelease
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe


class VersionTest : StringSpec({
    "should create a version with positive or zero numbers" {
        Version(1, 0, 0)
class VersionTest : FreeSpec({
    "should create a version with positive and zero numbers" {
    }

    "should not create a version with negative numbers" - {
        withData(
            row(-1, 0, 0),
            row(0, -1, 0),
            row(0, 0, -1),
        ) { (maj, min, pat) ->
            shouldThrow<IllegalArgumentException> { Version(maj, min, pat) }
        }
    }

    "should produce a string representation of the form X.Y.Z" {
        "${Version(1, 2, 3)}" shouldBe "1.2.3"
    }

    "should create an all-zero version by default" {
        "${Version()}" shouldBe "0.0.0"
    }

    "should create a pre-release version" {
        Version(1, 0, 0, PreRelease("alpha")).isPreRelease shouldBe true
    }

    "should produce a string representation of the form X.Y.Z-P for a pre-release version" {
        "${Version(1, 0, 0, PreRelease("alpha"))}" shouldBe "1.0.0-alpha"
        "${Version(1, 0, 0, PreRelease("alpha", "1"))}" shouldBe "1.0.0-alpha.1"
        "${Version(1, 0, 0, PreRelease("0", "3", "7"))}" shouldBe "1.0.0-0.3.7"
        "${Version(1, 0, 0, PreRelease("x", "7", "z", "92"))}" shouldBe "1.0.0-x.7.z.92"
        "${Version(1, 0, 0, PreRelease("x-y-z", "-"))}" shouldBe "1.0.0-x-y-z.-"
    }
})
