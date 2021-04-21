package io.github.semver

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

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
