package io.github.semver

import io.github.semver.identifier.PreRelease
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class VersionTest : FreeSpec({
    "should create a version with positive and zero numbers" {
        shouldNotThrowAny { Version(1, 0, 0) }
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
        Version(1, 2, 3) shouldHaveToString "1.2.3"
    }

    "should create an all-zero version by default" {
        Version() shouldHaveToString "0.0.0"
    }

    "should create a pre-release version" {
        Version(1, 0, 0, PreRelease("alpha")).isPreRelease shouldBe true
    }

    "should produce a string representation of the form X.Y.Z-P for a pre-release version" - {
        withData(
            PreRelease("alpha"),
            PreRelease("alpha", "1"),
            PreRelease("0", "3", "7"),
            PreRelease("x", "7", "z", "92"),
            PreRelease("x-y-z", "-"),
        ) { pre ->
            Version(1, pre = pre) shouldHaveToString "1.0.0-$pre"
        }
    }
})
