package at.asitplus.wallet.csp

import at.asitplus.csp.ContentSecurityPolicySourceExpressionHash
import at.asitplus.csp.ContentSecurityPolicySourceExpressionHashAlgorithm
import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe

val ContentSecurityPolicySourceExpressionHashTest by testSuite {
    /**
     * just making sure that the enum names remain consistent with the specification
     */
    test("values") {
        ContentSecurityPolicySourceExpressionHash(
            "'sha384-H8BRh8j48O9oYatfu5AZzq6A9RINhZO5H16dQZngK7T62em8MUt1FLm52t+eX6xO'"
        ).run {
            algorithm shouldBe ContentSecurityPolicySourceExpressionHashAlgorithm.sha384
            hashValue.toString() shouldBe "H8BRh8j48O9oYatfu5AZzq6A9RINhZO5H16dQZngK7T62em8MUt1FLm52t+eX6xO"
        }
    }
}