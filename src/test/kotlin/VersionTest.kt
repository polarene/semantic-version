package io.github.semver

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.datatest.forAll
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.beTheSameInstanceAs


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

    Given("a starting version") {
        val starting = Version(1, 0, 1)
        When("I make a bugfix") {
            val bugfix = starting.bugfix()
            Then("only the patch version is incremented") {
                "$bugfix" shouldBe "1.0.2"
            }
            And("the starting version is unaffected") {
                bugfix shouldNot beTheSameInstanceAs(starting)
                "$bugfix" shouldNotBe "$starting"
            }
        }
    }
})
