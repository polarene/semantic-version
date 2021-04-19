package io.github.semver

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.datatest.forAll
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row


class VersionTest : StringSpec({
    "should create a version from 3 ints" {
        Version(1, 0, 0)
    }

    "should not create a version from negative numbers" {
        forAll(
            row(-1, 0, 0),
            row(0, -1, 0),
            row(0, 0, -1),
        ) { (maj, min, pat) ->
            shouldThrow<IllegalArgumentException> { Version(maj, min, pat) }
        }
    }
})
