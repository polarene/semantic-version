package io.github.semver.identifier

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PreReleaseTest : StringSpec({
    "should create a pre-release with at least one identifier" {
        assertSoftly {
            shouldNotThrow<IllegalArgumentException> { PreRelease("alpha") }
            shouldNotThrow<IllegalArgumentException> { PreRelease("rc", "2") }
            shouldNotThrow<IllegalArgumentException> { PreRelease("0", "2", "5") }
        }
    }

    "should not create a pre-release without identifiers" {
        assertSoftly {
            shouldThrow<IllegalArgumentException> { PreRelease() }
            shouldThrow<IllegalArgumentException> {
                val identifiers = arrayOf<String>()
                PreRelease(*identifiers)
            }
        }
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

    "should produce a string representation consisting of a series of dot separated identifiers" {
        assertSoftly {
            "${PreRelease("alpha")}" shouldBe "alpha"
            "${PreRelease("rc", "2")}" shouldBe "rc.2"
            "${PreRelease("0", "2", "5")}" shouldBe "0.2.5"
        }
    }
})
