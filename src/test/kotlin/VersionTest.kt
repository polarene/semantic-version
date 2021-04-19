package io.github.semver

import io.kotest.core.spec.style.StringSpec


class VersionTest : StringSpec({
    "should create a version from 3 ints" {
        Version(1, 0, 0)
    }

})
