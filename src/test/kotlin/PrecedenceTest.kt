package io.github.semver

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.beLessThan
import io.kotest.matchers.should

class PrecedenceTest : BehaviorSpec({
    Given("two versions") {
        When("they differ by Major number") {
            Then("they are ordered numerically by Major") {
                Version(1, 0, 0) should beLessThan(Version(2, 0, 0))
            }
        }
        When("they have the same Major number") {
            And("they differ by Minor number") {
                Then("they are ordered numerically by Minor") {
                    Version(2, 0, 0) should beLessThan(Version(2, 1, 0))
                }
            }
        }
        When("they have the same (Major, Minor) number") {
            And("they differ by Patch number") {
                Then("they are ordered numerically by Patch") {
                    Version(2, 1, 0) should beLessThan(Version(2, 1, 1))
                }
            }
        }
    }
})
