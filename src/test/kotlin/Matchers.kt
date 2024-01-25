package io.github.semver

import io.kotest.matchers.shouldBe

infix fun Any.shouldHaveToString(representation: String): Any {
    toString() shouldBe representation
    return this
}
