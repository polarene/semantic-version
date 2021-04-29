package io.github.semver.identifier

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PreReleaseTest : StringSpec({
    "should create a pre-release with at least one identifier" {
        PreRelease("alpha")
        PreRelease("rc", "2")
        PreRelease("0", "2", "5")
    }

    "should not allow empty identifiers" {
        assertSoftly {
            shouldThrow<IllegalArgumentException> { PreRelease("") }
            shouldThrow<IllegalArgumentException> { PreRelease("rc", "") }
            shouldThrow<IllegalArgumentException> { PreRelease("alpha", "1", "") }
        }
    }

    "should not allow blank identifiers" {
        assertSoftly {
            shouldThrow<IllegalArgumentException> { PreRelease(" ") }
            shouldThrow<IllegalArgumentException> { PreRelease("rc", " ") }
            shouldThrow<IllegalArgumentException> { PreRelease("alpha", "1", " ") }
        }
    }
})
