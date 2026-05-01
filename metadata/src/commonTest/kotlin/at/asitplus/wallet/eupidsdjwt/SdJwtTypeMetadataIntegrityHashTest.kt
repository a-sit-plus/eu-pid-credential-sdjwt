package at.asitplus.wallet.eupidsdjwt

import at.asitplus.csp.ContentSecurityPolicySourceExpressionHashAlgorithm
import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe

val SdJwtTypeMetadataIntegrityHashTest by testSuite {
    /**
     * just making sure that the enum names remain consistent with the specification
     */
    test("values") {
        SdJwtTypeMetadataIntegrityHash(
            "sha384-H8BRh8j48O9oYatfu5AZzq6A9RINhZO5H16dQZngK7T62em8MUt1FLm52t+eX6xO"
        ).expression.run {
            algorithm shouldBe ContentSecurityPolicySourceExpressionHashAlgorithm.sha384
            hashValue.toString() shouldBe "H8BRh8j48O9oYatfu5AZzq6A9RINhZO5H16dQZngK7T62em8MUt1FLm52t+eX6xO"
        }
        SdJwtTypeMetadataIntegrityHash(
            "sha384-H8BRh8j48O9oYatfu5AZzq6A9RINhZO5H16dQZngK7T62em8MUt1FLm52t+eX6xO"
        ).expression.run {
            algorithm shouldBe ContentSecurityPolicySourceExpressionHashAlgorithm.sha384
            hashValue.toString() shouldBe "H8BRh8j48O9oYatfu5AZzq6A9RINhZO5H16dQZngK7T62em8MUt1FLm52t+eX6xO"
        }
    }
}