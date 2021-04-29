package io.github.semver.identifier

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PreReleaseTest : StringSpec({
    "should create a pre-release with at least one identifier" {
        PreRelease("alpha")
        PreRelease("rc", "2")
    }

    "should not allow empty identifiers" {
        shouldThrow<IllegalArgumentException> { PreRelease("") }
    }
})
