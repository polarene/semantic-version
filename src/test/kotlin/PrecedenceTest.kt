package io.github.semver

import io.github.semver.identifier.PreRelease
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.beGreaterThan
import io.kotest.matchers.comparables.beLessThan
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.should

class PrecedenceTest : BehaviorSpec({
    Given("two versions") {
        When("Major number is different") {
            Then("they are ordered numerically by Major") {
                Version(1, 0, 0) should beLessThan(Version(2, 0, 0))
            }
        }
        When("Major number is equal") {
            And("Minor number is different") {
                Then("they are ordered numerically by Minor") {
                    Version(2, 0, 0) should beLessThan(Version(2, 1, 0))
                }
            }
        }
        When("Major and Minor number are equal") {
            And("Patch number is different") {
                Then("they are ordered numerically by Patch") {
                    Version(2, 1, 0) should beLessThan(Version(2, 1, 1))
                }
            }
        }
        When("Major, Minor, and Patch are equal") {
            Then("normal versions have the same precedence") {
                Version(1, 0, 0) shouldBeEqualComparingTo Version(1, 0, 0)
            }
            Then("a pre-release version has lower precedence than a normal version") {
                Version(1, 0, 0, PreRelease("alpha")) should beLessThan(Version(1, 0, 0))
                Version(1, 0, 0) should beGreaterThan(Version(1, 0, 0, PreRelease("alpha")))
            }
        }
    }
})
