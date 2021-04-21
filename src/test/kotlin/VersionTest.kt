package io.github.semver

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.datatest.forAll
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe


class VersionTest : StringSpec({
    "should create a version with positive or zero numbers" {
        Version(1, 0, 0)
    }

    "should not create a version with negative numbers" {
        forAll(
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
        Version(1, 0, 0, PreReleaseId("alpha")).isPreRelease shouldBe true
    }

    "should produce a string representation of the form X.Y.Z-P for pre-release" {
        "${Version(1, 0, 0, PreReleaseId("alpha"))}" shouldBe "1.0.0-alpha"
    }
})

class VersionPropertiesTest : BehaviorSpec({
    Given("a major version zero (0.y.z)") {
        val version = Version(0, 1, 23)
        When("I check if it's stable") {
            Then("it should be false") {
                version.isStable shouldBe false
            }
        }
    }

    Given("a pre-release version") {
        val version = Version(1, 0, 0, PreReleaseId("alpha"))
        When("I check if it's stable") {
            Then("it should be false") {
                version.isStable shouldBe false
            }
        }
    }

    Given("a starting version") {
        val starting = Version(1, 1, 1)
        When("I make a bugfix") {
            val bugfix = starting.bugfix()
            Then("only the patch version is incremented") {
                "$bugfix" shouldBe "1.1.2"
            }
            And("the starting version is unaffected") {
                "$starting" shouldNotBe "$bugfix"
            }
        }
        When("I make a backwards compatible change") {
            val compatible = starting.compatibleChange()
            Then("only the minor version is incremented, patch version is reset") {
                "$compatible" shouldBe "1.2.0"
            }
            And("the starting version is unaffected") {
                "$starting" shouldNotBe "$compatible"
            }
        }
        When("I make a breaking change") {
            val breaking = starting.breakingChange()
            Then("only the major version is incremented, minor and patch versions are reset") {
                "$breaking" shouldBe "2.0.0"
            }
            And("the starting version is unaffected") {
                "$starting" shouldNotBe "$breaking"
            }
        }
    }
})
